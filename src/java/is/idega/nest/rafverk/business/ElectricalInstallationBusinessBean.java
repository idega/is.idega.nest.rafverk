/*
 * $Id: ElectricalInstallationBusinessBean.java,v 1.24 2007/11/22 16:23:44 thomas Exp $
 * Created on Mar 16, 2007
 *
 * Copyright (C) 2007 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package is.idega.nest.rafverk.business;

import is.fmr.landskra.Fasteign;
import is.idega.nest.rafverk.bean.BaseBean;
import is.idega.nest.rafverk.bean.InitialData;
import is.idega.nest.rafverk.bean.RafverktokuListi;
import is.idega.nest.rafverk.bean.TilkynningLokVerksBean;
import is.idega.nest.rafverk.bean.TilkynningVertakaBean;
import is.idega.nest.rafverk.cache.ElectricalInstallationCache;
import is.idega.nest.rafverk.data.Maelir;
import is.idega.nest.rafverk.data.MaelirList;
import is.idega.nest.rafverk.data.RealEstateIdentifier;
import is.idega.nest.rafverk.domain.ElectricalInstallation;
import is.idega.nest.rafverk.domain.ElectricalInstallationHome;
import is.idega.nest.rafverk.domain.Meter;
import is.idega.nest.rafverk.domain.MeterHome;
import is.idega.nest.rafverk.domain.Rafverktaka;
import is.idega.nest.rafverk.domain.Rafverktaki;
import is.postur.Gata;

import java.io.IOException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.RemoveException;

import com.idega.business.IBOLookup;
import com.idega.business.IBORuntimeException;
import com.idega.business.IBOService;
import com.idega.business.IBOServiceBean;
import com.idega.core.location.data.PostalCode;
import com.idega.core.location.data.PostalCodeHome;
import com.idega.core.location.data.RealEstate;
import com.idega.core.location.data.RealEstateHome;
import com.idega.core.location.data.Street;
import com.idega.core.location.data.StreetHome;
import com.idega.data.IDOHome;
import com.idega.data.IDOLookup;
import com.idega.user.data.Group;
import com.idega.user.data.User;
import com.idega.util.StringHandler;
import com.idega.util.datastructures.list.KeyValuePair;


/**
 * 
 *  Last modified: $Date: 2007/11/22 16:23:44 $ by $Author: thomas $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.24 $
 */
public class ElectricalInstallationBusinessBean extends IBOServiceBean implements ElectricalInstallationBusiness {
	
	private ElectricalInstallationHome electricalInstallationHome;
	
	private ElectricalInstallationRendererBusiness electricalInstallationRendererBusiness;
	
	private ElectricalInstallationMessageBusiness electricalInstallationMessageBusiness;
	
	private UserMessagesBusiness userMessagesBusiness;
	
	private ElectricalInstallationCaseBusiness electricalInstallationCaseBusiness;
	
	private MeterHome meterHome;
	
	private RealEstateHome realEstateHome;
	
	private StreetHome streetHome;
	
	private PostalCodeHome postalCodeHome;
	
	private ElectricalInstallationCache electricalInstallationCache; 
	
	private ElectricalInstallationState electricalInstallationState;
	
	/**
	 * If an electrical installation has changed (by someone else) write the user that should be informed about the change into this set.
	 * This set is checked by every user when getting data from Managed Beans.
	 * (this method tries to solve the problem how shared data that is cached in managed bean is updated)
	 * 
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationBusiness#addChangeForUser(com.idega.user.data.User)
	 */
	public void addChangeForUser(User user) {
		getElectricalInstallationCache().addChangeForUser(user);
	}
	
	
	/**
	 * Check if there are changes for the specified user. If there are changes the Managed Beans for the user must be initialized again to retrieve
	 * the latest data from the database.
	 */
	public boolean changesForUser(User user) {
		return getElectricalInstallationCache().changesForUser(user);
	}

	public KeyValuePair changeElectrician(ElectricalInstallation electricalInstallation, User newOwner, RafverktokuListi rafverktokuListi) {
		ElectricalInstallation newElectricalInstallation = null;
		try {
			newElectricalInstallation = getElectricalInstallationHome().create();
		}
		catch (CreateException e) {
			e.printStackTrace();
			newElectricalInstallation = null;
		}
		// take only the working place data over
		RealEstate realEstate = electricalInstallation.getRealEstate();
		newElectricalInstallation.setRealEstate(realEstate);
		newElectricalInstallation.setEnergyConsumerName(electricalInstallation.getEnergyConsumerName());
		newElectricalInstallation.setEnergyConsumerPersonalID(electricalInstallation.getEnergyConsumerPersonalID());
		newElectricalInstallation.setEnergyConsumerHomePhone(electricalInstallation.getEnergyConsumerHomePhone());
		newElectricalInstallation.setEnergyConsumerWorkPhone(electricalInstallation.getEnergyConsumerWorkPhone());
		newElectricalInstallation.setEnergyConsumerEmail(electricalInstallation.getEnergyConsumerEmail());
		newElectricalInstallation.setElectrician(newOwner);
		// set the same for the case
		newElectricalInstallation.setOwner(newOwner);
		// set status to checked out workspace, do not keep the old one
		getElectricalInstallationState().checkOutWorkingPlace(newElectricalInstallation);
		// set old one as parent to new one (to keep track of history not used at the moment)
		newElectricalInstallation.setParentCase(electricalInstallation);
		newElectricalInstallation.store();
		
		// flag old electrical installation as changed (that is change status)
		getElectricalInstallationState().changeElectrician(electricalInstallation);
		// flag all others electrical installations as changed 
		User user = electricalInstallation.getElectrician();
		try {
			Collection coll = getElectricalInstallationHome().findNotFreeElectricalinstallationByRealEstate(realEstate, user);
			Iterator iterator = coll.iterator();
			while (iterator.hasNext()) {
				ElectricalInstallation electricalInstallationLocal = (ElectricalInstallation) iterator.next();
				getElectricalInstallationState().changeElectrician(electricalInstallationLocal);
				electricalInstallationLocal.store();
				// update rafverktaka 
				Rafverktaka rafverktaka = rafverktokuListi.getRafverktaka(electricalInstallationLocal.getPrimaryKey().toString());
				rafverktaka.initialize(electricalInstallationLocal, this);
			}
		}
		catch (FinderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		electricalInstallation.store();
		try {
			String result = getUserMessagesBusiness().sendUserMessageAfterChangingElectrician(electricalInstallation, newElectricalInstallation);
			return new KeyValuePair(newElectricalInstallation, result);
		}
		catch (RemoteException e) {
			throw new IBORuntimeException(e);
		}

	}
	
	
	
	public ElectricalInstallation getChildElectricalInstallationOrNull(ElectricalInstallation electricalInstallation) {
		try {
			if (getElectricalInstallationState().isApplicationHasNewOwner(electricalInstallation)) { 
				// if the owner has changed there should be a child
				List list = getElectricalInstallationCaseBusiness().getChildrenOfCaseAsElectricalInstallation(electricalInstallation);
				return (ElectricalInstallation) ((list.isEmpty()) ? null : list.get(0));
			}
			return null;
		}
		catch (RemoteException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public Map isSomeoneAlreadyWorkingAtThisPlace(RealEstate realEstate, ElectricalInstallation electricalInstallation) {
		return isSomeoneAlreadyWorkingAtThisPlace(realEstate, null, electricalInstallation);
	}
	
	
	public Map isSomeoneAlreadyWorkingAtThisPlace(RealEstateIdentifier realEstateIdentifier, ElectricalInstallation electricalInstallation) {
		return isSomeoneAlreadyWorkingAtThisPlace(null, realEstateIdentifier, electricalInstallation);
	}
	
	
	private Map isSomeoneAlreadyWorkingAtThisPlace(RealEstate realEstate, RealEstateIdentifier realEstateIdentifier, ElectricalInstallation electricalInstallation) {
		if ((realEstate == null || realEstate.isDummy()) &&
			(realEstateIdentifier == null || realEstateIdentifier.isDummy())) {
			return null;
		}
		Collection closed;
		Collection available;
		User user = (electricalInstallation != null) ? electricalInstallation.getElectrician() : null;
		if (user == null) {
			user = BaseBean.getCurrentUser();
		}
		// closed
		try {
			if (realEstate != null ) {
				closed = getOtherClosedElectricalInstallationByRealEstate(realEstate, user);
			}
			else {
				closed = getOtherClosedElectricalInstallationByRealEstateIdentifer(realEstateIdentifier, user);
			}
		}
		catch (FinderException e) {
			closed = null;
		}
		if (closed == null) {
			closed = new ArrayList(0);
		}
		// available
		try {
			if (realEstate != null ) {
				available = getOtherOpenElectricalInstallationByRealEstate(realEstate, user);
			}
			else {
				available = getOtherOpenElectricalInstallationByRealEstateIdentifier(realEstateIdentifier, user);
			}
		}
		catch (FinderException e) {
			available = null;
		}
		if (available == null) {
			available = new ArrayList(0);
		}
		List usersClosedCases = getUsers(closed);
		List usersAvailableCases = getUsers(available);
		Map result = new HashMap(2);
		result.put(ElectricalInstallationState.CLOSED_STATUS_KEY, usersClosedCases);
		result.put(ElectricalInstallationState.AVAILABLE_STATUS_KEY, usersAvailableCases);
		return result;
	}
	
	private List getUsers(Collection electricalInstallations) {
		List users = new ArrayList(electricalInstallations.size());
		Iterator iterator = electricalInstallations.iterator();
		while (iterator.hasNext()) {
			ElectricalInstallation electricalInstallationOther = (ElectricalInstallation) iterator.next();
			User userOther = electricalInstallationOther.getElectrician();
			users.add(userOther);
		}
		return users;
	}

	public boolean checkOutWorkingPlace(			
			Rafverktaka rafverktaka ,
			TilkynningVertakaBean tilkynningVertakaBean, 
			TilkynningLokVerksBean tilkynningLokVerksBean) {
		
		if (! createElectricalInstallationIfNecessary(rafverktaka)) {
			return false; 
		}
		ElectricalInstallation electricalInstallation = rafverktaka.getElectricalInstallation();
		getElectricalInstallationState().checkOutWorkingPlace(electricalInstallation);
		boolean result = storeDataCheckingOutWorkingPlaceStep1(rafverktaka, tilkynningVertakaBean, tilkynningLokVerksBean);
		if (result) {
			rafverktaka.initialize(electricalInstallation, this);
		}
		return result;
	}
	
	
	public boolean sendApplication(
			Rafverktaka rafverktaka) {
		ElectricalInstallation electricalInstallation = rafverktaka.getElectricalInstallation();
		getElectricalInstallationState().sendApplication(electricalInstallation);
		electricalInstallation.store();
		rafverktaka.initialize(electricalInstallation, this);
		return true;
	}
	
	public boolean sendApplicationReport(
			Rafverktaka rafverktaka, RafverktokuListi rafverktokuListi) {
		ElectricalInstallation electricalInstallation = rafverktaka.getElectricalInstallation();
		getElectricalInstallationState().sendApplicationReport(electricalInstallation);
		electricalInstallation.store();
		User user = electricalInstallation.getElectrician();
		RealEstate realEstate = electricalInstallation.getRealEstate();
		try {
			Collection coll = getElectricalInstallationHome().findNotFreeElectricalinstallationByRealEstate(realEstate, user);
			Iterator iterator = coll.iterator();
			while (iterator.hasNext()) {
				ElectricalInstallation electricalInstallationLocal = (ElectricalInstallation) iterator.next();
				getElectricalInstallationState().sendApplicationReport(electricalInstallationLocal);
				electricalInstallationLocal.store();
				// update rafverktaka 
				Rafverktaka rafverktakaLocal = rafverktokuListi.getRafverktaka(electricalInstallationLocal.getPrimaryKey().toString());
				rafverktakaLocal.initialize(electricalInstallationLocal, this);
			}
		}
		catch (FinderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rafverktaka.initialize(electricalInstallation, this);
		return true;
	}
	
	public boolean storeApplication(			
			Rafverktaka rafverktaka ,
			TilkynningVertakaBean tilkynningVertakaBean, 
			TilkynningLokVerksBean tilkynningLokVerksBean) {
		if (! createElectricalInstallationIfNecessary(rafverktaka)) {
			return false; 
		}
		ElectricalInstallation electricalInstallation = rafverktaka.getElectricalInstallation();
		getElectricalInstallationState().storeApplication(electricalInstallation);
		boolean result = storeDataStep1(rafverktaka, tilkynningVertakaBean, tilkynningLokVerksBean);
		if (result) {
			rafverktaka.initialize(electricalInstallation, this);
		}
		return result;
	}
		
	public boolean storeApplicationReport(			
			Rafverktaka rafverktaka ,
			TilkynningVertakaBean tilkynningVertakaBean, 
			TilkynningLokVerksBean tilkynningLokVerksBean) {
		if (! createElectricalInstallationIfNecessary(rafverktaka)) {
			return false; 
		}
		ElectricalInstallation electricalInstallation = rafverktaka.getElectricalInstallation();
		getElectricalInstallationState().storeApplicationReport(electricalInstallation);
		boolean result = storeDataStep1(rafverktaka, tilkynningVertakaBean, tilkynningLokVerksBean);
		if (result) {
			rafverktaka.initialize(electricalInstallation, this);
		}
		return result;
	}
		
	private boolean createElectricalInstallationIfNecessary(Rafverktaka rafverktaka) {
		ElectricalInstallation electricalInstallation = rafverktaka.getElectricalInstallation();
		// create new electrical installation
		if (electricalInstallation == null) {
			try {
				electricalInstallation = createElectricalInstallation();
				// set owner of case
				electricalInstallation.setOwner(rafverktaka.getRafverktaki().getElectrician());
				rafverktaka.setElectricalInstallation(electricalInstallation);
			}
			catch (CreateException e) {	
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
	
	private boolean storeDataCheckingOutWorkingPlaceStep1(			
			Rafverktaka rafverktaka ,
			TilkynningVertakaBean tilkynningVertakaBean, 
			TilkynningLokVerksBean tilkynningLokVerksBean) {
		ElectricalInstallation electricalInstallation = rafverktaka.getElectricalInstallation();
		RealEstateIdentifier realEstateIdentifer = tilkynningVertakaBean.getRealEstateIdentifier();
		// security check, this method should not be called if someoen is already working at this place
		Map result = isSomeoneAlreadyWorkingAtThisPlace(realEstateIdentifer, electricalInstallation);
		List resultClosedCases = (List) result.get(ElectricalInstallationState.CLOSED_STATUS_KEY);
		if (! resultClosedCases.isEmpty()) {
			return false; 
		}
		// security checks end
		if (! storeWorkingPlace(electricalInstallation, tilkynningVertakaBean, false)) {
			return false;
		}
		return storeDataStep2(electricalInstallation, rafverktaka, tilkynningVertakaBean, tilkynningLokVerksBean);
	}
	
	private boolean storeDataStep1(			
			Rafverktaka rafverktaka ,
			TilkynningVertakaBean tilkynningVertakaBean, 
			TilkynningLokVerksBean tilkynningLokVerksBean) {
		ElectricalInstallation electricalInstallation = rafverktaka.getElectricalInstallation();
		boolean workingPlaceFixed = getElectricalInstallationState().isWorkingPlaceFixed(electricalInstallation);
		if (! storeWorkingPlace(electricalInstallation, tilkynningVertakaBean, workingPlaceFixed)) {
			return false;
		}
		return storeDataStep2(electricalInstallation, rafverktaka, tilkynningVertakaBean, tilkynningLokVerksBean);
	}
	
	private boolean storeDataStep2(
			ElectricalInstallation electricalInstallation,
			Rafverktaka rafverktaka ,
			TilkynningVertakaBean tilkynningVertakaBean, 
			TilkynningLokVerksBean tilkynningLokVerksBean) {
		storeElectrician(rafverktaka);
		storeManagedBean(rafverktaka, tilkynningVertakaBean);
		storeManagedBean(rafverktaka, tilkynningLokVerksBean);
		if (! storeMeters(rafverktaka, tilkynningVertakaBean, tilkynningLokVerksBean)) {
			return false;
		}
		electricalInstallation.store();
		rafverktaka.initialize(electricalInstallation, this);
		return true;
	}
	
	private boolean storeWorkingPlace(ElectricalInstallation electricalInstallation, TilkynningVertakaBean tilkynningVertakaBean, boolean workingPlaceFixed) {
		// store real estate
		if (! workingPlaceFixed) {
			try {
				storeRealEstate(electricalInstallation, tilkynningVertakaBean);
			}
			catch (CreateException e) {
				e.printStackTrace();
				return false;
			}
			catch (EJBException e) {
				e.printStackTrace();
				return false;
			}
			catch (RemoveException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
	
	private boolean storeMeters(		
			Rafverktaka rafverktaka, 
			TilkynningVertakaBean tilkynningVertakaBean,
			TilkynningLokVerksBean tilkynningLokVerksBean) {
		try {
			storeMetersOfManagedBeans(rafverktaka, tilkynningVertakaBean, tilkynningLokVerksBean);
			return true;
		}
		catch (EJBException e) {
			e.printStackTrace();
			return false;
		}
		catch (CreateException e) {
			e.printStackTrace();
			return false;
		}
		catch (RemoveException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private ElectricalInstallation createElectricalInstallation() throws CreateException {
		return  getElectricalInstallationHome().create();
	}
	
	private void storeElectrician(Rafverktaka rafverktaka) {
		Rafverktaki rafverktaki = rafverktaka.getRafverktaki(); 
		User electrician = rafverktaki.getElectrician();
		ElectricalInstallation electricalInstallation = rafverktaka.getElectricalInstallation();
		electricalInstallation.setElectrician(electrician);
		// electrician company 
		String electricianCompany = rafverktaki.getNafnFyrirtaekis();
		electricalInstallation.setElectricianCompany(electricianCompany);
	}
	
	private void storeRealEstate(ElectricalInstallation electricalInstallation, TilkynningVertakaBean tilkynningVertakaBean) throws CreateException, EJBException, RemoveException {
		RealEstateIdentifier realEstateIdentifer = tilkynningVertakaBean.getRealEstateIdentifier();
		boolean newRealEstateIsNotDummy = ! realEstateIdentifer.isDummy();
		RealEstate realEstate = electricalInstallation.getRealEstate();
		if (realEstate == null) {
			if (newRealEstateIsNotDummy) {
				// "normal" case if the application is new and complete
				setRealEstate(realEstateIdentifer, electricalInstallation, tilkynningVertakaBean);
				return;
			}
			// application is new but not complete
			setDummyRealEstateOrNull(electricalInstallation, tilkynningVertakaBean);
			return;
		}
		// there is a real estate....
		// check the existing real estate
		RealEstateIdentifier oldRealEstateNumber = RealEstateIdentifier.getInstance(realEstate);
		boolean oldRealEstateIsNotDummy = ! oldRealEstateNumber.isDummy();
		if (oldRealEstateIsNotDummy) {
			if (newRealEstateIsNotDummy) {
				// "normal" case: application replaces real estate with complete real estate
				setRealEstate(realEstateIdentifer, electricalInstallation, tilkynningVertakaBean);
				return;
			}
			// application replaces the existing real estate with incomplete real estate
			setDummyRealEstateOrNull(electricalInstallation, tilkynningVertakaBean);
			return;
		}
		// there is a dummy real estate 
		if (newRealEstateIsNotDummy) {
			// delete the dummy one
			deleteDummyRealEstate(electricalInstallation);
			setRealEstate(realEstateIdentifer, electricalInstallation, tilkynningVertakaBean);
			return;
		}
		// new real estate is dummy and the old one was one
		// check if the old one could be deleted
		Street street = findOrCreateStreet(tilkynningVertakaBean);
		if (street == null) {
			deleteDummyRealEstate(electricalInstallation);
			return;
		}
		// street was found, reuse the existing dummy real estate
		setStreetToRealEstate(realEstate, street, tilkynningVertakaBean);
		realEstate.store();		
	}
	
	private void deleteDummyRealEstate(ElectricalInstallation electricalInstallation) throws EJBException, RemoveException {
		RealEstate realEstate = electricalInstallation.getRealEstate();
		// security check - ONLY delete real dummies
		if (StringHandler.isEmpty(realEstate.getRealEstateNumber())) {
			electricalInstallation.setRealEstate(null);
			realEstate.remove();
		}
	}
		
	private void setDummyRealEstateOrNull(ElectricalInstallation electricalInstallation, TilkynningVertakaBean tilkynningVertakaBean) throws CreateException {
		// special case: if there is no street do not create a dummy real estate
		Street street = findOrCreateStreet(tilkynningVertakaBean);
		if (street == null) {
			electricalInstallation.setRealEstate(null);
			return;
		}
		// create a dummy one and add the street 
		RealEstate dummyRealEstate = getRealEstateHome().create();
		dummyRealEstate.setStreetNumber(tilkynningVertakaBean.getGotunumer());
		dummyRealEstate.setStreet(street);
		dummyRealEstate.store();
		electricalInstallation.setRealEstate(dummyRealEstate);
	}
	
	private void setStreetToRealEstate(RealEstate realEstate, Fasteign fasteign) throws CreateException {
		Street street = findOrCreateStreet(fasteign);
		if (street != null) {
			realEstate.setStreetNumber(fasteign.getGotuNumer());
			realEstate.setStreet(street);
		}
	}
	
	private void setStreetToRealEstate(RealEstate realEstate, Street street, TilkynningVertakaBean tilkynningVertakaBean) throws CreateException {
		if (street != null) {
			realEstate.setStreetNumber(tilkynningVertakaBean.getGotunumer());
			realEstate.setStreet(street);
		}
	}
		
	private void setRealEstate(RealEstateIdentifier realEstateIdentifer, ElectricalInstallation electricalInstallation, TilkynningVertakaBean tilkynningVertakaBean) throws CreateException {
		RealEstate realEstate = findRealEstate(realEstateIdentifer);
		// not found? create one!
		if (realEstate == null) {
			Fasteign fasteign = getFasteign(realEstateIdentifer, tilkynningVertakaBean);
			if (fasteign != null) {
				realEstate = createRealEstate(fasteign);
			}
			if (realEstate == null) {
				// could not be created (realEstateNumber wrong!), give up and set dummy one
				setDummyRealEstateOrNull(electricalInstallation, tilkynningVertakaBean);
				return;
			}
			setStreetToRealEstate(realEstate, fasteign);
			realEstate.store();
		}
		// found? just set in electrical installation
		electricalInstallation.setRealEstate(realEstate);
	}
			
	private Street findOrCreateStreet(TilkynningVertakaBean tilkynningVertakaBean) throws CreateException {
		String streetName = tilkynningVertakaBean.getGata();
		String postalCode = tilkynningVertakaBean.getPostnumer();
		if (StringHandler.isEmpty(postalCode) || StringHandler.isEmpty(streetName)) {
			return null;
		}
		Gata gata = lookupGata(streetName, postalCode);
		return findOrCreateStreet(gata);
	}
	
	
	private Street findOrCreateStreet(Fasteign fasteign) throws CreateException {
		Gata gata = fasteign.getGata();
		return findOrCreateStreet(gata);
	}
//		String postalCode = fasteign.getGata();

		
	private Street findOrCreateStreet(Gata gata) throws CreateException {

		if (gata == null) {
			return null;
		}
		String name = gata.getNafn();
		String nameDativ = gata.getNafnThagufall();
		if (StringHandler.isEmpty(name) && StringHandler.isEmpty(nameDativ)) {
			return null;
		}
		PostalCode postalCodeObject = null;
		try {
			postalCodeObject = getPostalCodeHome().findByPostalCode(gata.getPostnumerString());
		}
		catch (FinderException e) {
			postalCodeObject = null;
		}
		if (postalCodeObject == null) {
			return null;
		}
		Street street; 
		try {
			street = getStreetHome().findStreetByPostalCodeAndNameOrNameDativ(postalCodeObject, name, nameDativ);
		}
		catch (FinderException e) {
			street = null;
		}
		if (street != null) {
			return street;
		}
		// not found, let us create one
		street = getStreetHome().create();
		street.setName(name);
		street.setNameDativ(nameDativ);
		street.setPostalCode(postalCodeObject);
		street.store();
		return street;
	}
		
	private RealEstate findRealEstate(RealEstateIdentifier realEstateIdentifier) {
		if (realEstateIdentifier.isDummy()) {
			return null;
		}
		// try to find an existing (that is in our database existing) real estate
		RealEstate realEstate = null;
		try {
			realEstate = getRealEstateHome().findRealEstateByRealEstateIdentifier(
					realEstateIdentifier.getLandNumber(),
					realEstateIdentifier.getRealEstateNumber(),
					realEstateIdentifier.getRealEstateUnit(),
					realEstateIdentifier.getRealEstateCode());
		}
		catch (FinderException e) {
			realEstate = null;
		}
		return realEstate;
	}
	
	private Fasteign getFasteign(RealEstateIdentifier realEstateIdentifier, TilkynningVertakaBean tilkynningVertakaBean)  {
		if (realEstateIdentifier.isDummy()) {
			// should not happen, this method is called when realEstateNumber is not empty
			return null;
		}
		Fasteign fasteign = lookupFasteign(realEstateIdentifier, tilkynningVertakaBean);
		if (fasteign == null) {
			// should not happen, something wrong with GUI?
			return null;
		}
		return fasteign;
	}
		
	private RealEstate createRealEstate(Fasteign fasteign) throws CreateException{
		RealEstate realEstate = getRealEstateHome().create();
		realEstate.setLandRegisterMapNumber(fasteign.getLandnumer());
		realEstate.setRealEstateNumber(fasteign.getFastaNumer());
		realEstate.setRealEstateUnit(fasteign.getMatseiningNumer());
		realEstate.setRealEstateCode(fasteign.getMerking());
		realEstate.setUse(fasteign.getNotkun());
		realEstate.setComment(fasteign.getSkyring());
		realEstate.setName(fasteign.getNafn());
		return realEstate;
	}


		
	
	private Gata lookupGata(String streetName, String postalCode) {
		if (postalCode == null) {
			return null;
		}
		List gotuListi = BaseBean.getInitialData().getGotuListiByPostnumer(postalCode);
		Iterator iterator = gotuListi.iterator();
		while(iterator.hasNext()) {
			Gata gata = (Gata) iterator.next();
			String name = gata.getNafn();
			if (name != null && name.equals(streetName)) {
				return gata;
			}
		}
		return null;
	}
	
	private Fasteign lookupFasteign(RealEstateIdentifier realEstateIdentifier, TilkynningVertakaBean tilkynningVertakaBean) {
		return tilkynningVertakaBean.lookupFasteign(realEstateIdentifier);
	}
		
		
	
	
	private void storeManagedBean(Rafverktaka rafverktaka, TilkynningVertakaBean tilkynningVertakaBean) {
		ElectricalInstallation electricalInstallation = rafverktaka.getElectricalInstallation();
		// energy company
		String energyCompany = tilkynningVertakaBean.getOrkuveitufyrirtaeki();
		Integer energyCompanyInteger = null;
		try {
			energyCompanyInteger = Integer.valueOf(energyCompany);
		}
		catch(NumberFormatException e) {
			energyCompanyInteger = null;
		}		
		electricalInstallation.setEnergyCompanyID(energyCompanyInteger);
		// external project id 
		String externalProjectID = tilkynningVertakaBean.getExternalProjectID();
		if (StringHandler.isEmpty(externalProjectID)) {
			externalProjectID = generateExternalProjectID();
		}
		electricalInstallation.setExternalProjectID(externalProjectID);
		electricalInstallation.setPersonInCharge(tilkynningVertakaBean.getPersonInCharge());
		// energy consumer
		electricalInstallation.setEnergyConsumerName(tilkynningVertakaBean.getNafnOrkukaupanda());
		electricalInstallation.setEnergyConsumerPersonalID(tilkynningVertakaBean.getKennitalaOrkukaupanda());
		electricalInstallation.setEnergyConsumerWorkPhone(tilkynningVertakaBean.getVinnusimiOrkukaupanda());
		electricalInstallation.setEnergyConsumerHomePhone(tilkynningVertakaBean.getHeimasimiOrkukaupanda());
		electricalInstallation.setEnergyConsumerEmail(tilkynningVertakaBean.getEnergyConsumerEmail());
		
		electricalInstallation.setType(tilkynningVertakaBean.getNotkunarflokkur());
		electricalInstallation.setCurrentLineModification(tilkynningVertakaBean.getHeimtaug());
		electricalInstallation.setCurrentLineConnectionModification(tilkynningVertakaBean.getHeimtaugTengist());
		electricalInstallation.setHomeLineA(tilkynningVertakaBean.getStofn1());
		electricalInstallation.setHomeLineB(tilkynningVertakaBean.getStofn2());
		electricalInstallation.setHomeLineC(tilkynningVertakaBean.getStofn3());
		electricalInstallation.setSwitchPanelModification(tilkynningVertakaBean.getAdaltafla());
		electricalInstallation.setElectronicalProtectiveMeasures(tilkynningVertakaBean.getVarnarradstoefun());
		electricalInstallation.setApplication(tilkynningVertakaBean.getBeidniUm());
		electricalInstallation.setPower(tilkynningVertakaBean.getUppsett());
		electricalInstallation.setSwitchPanelNumber(tilkynningVertakaBean.getNumerToeflu());
		electricalInstallation.setVoltageSystem(tilkynningVertakaBean.getSpennukerfi());
		electricalInstallation.setVoltageSystemOther(tilkynningVertakaBean.getAnnad());
		electricalInstallation.setApplicationRemarks(tilkynningVertakaBean.getSkyringar());
	}
	
	private void storeManagedBean(Rafverktaka rafverktaka, TilkynningLokVerksBean tilkynningLokVerksBean) {
		ElectricalInstallation electricalInstallation = rafverktaka.getElectricalInstallation();
		electricalInstallation.setAnnouncement(tilkynningLokVerksBean.getTilkynnt());
		electricalInstallation.setAnnouncementOther(tilkynningLokVerksBean.getTilkynntAnnad());
		electricalInstallation.setTypeInReport(tilkynningLokVerksBean.getNotkunarflokkur());
		electricalInstallation.setAnnouncementRemarks(tilkynningLokVerksBean.getSkyring());
		electricalInstallation.setVoltageSystemInReport(tilkynningLokVerksBean.getSpennukerfi());
		electricalInstallation.setVoltageSystemOtherInReport(tilkynningLokVerksBean.getAnnad());
		electricalInstallation.setElectronicalProtectiveMeasuresInReport(tilkynningLokVerksBean.getVarnarradstoefun());
		electricalInstallation.setGrounding(tilkynningLokVerksBean.getJardskaut());
		electricalInstallation.setGroundingOther(tilkynningLokVerksBean.getJardskautAnnad());
		electricalInstallation.setRemarks(tilkynningLokVerksBean.getSkyringar());
		// third step		
		electricalInstallation.setSwitchPanelResistence(tilkynningLokVerksBean.getHringrasarvidam());
		electricalInstallation.setSwitchPanelAmpere(tilkynningLokVerksBean.getSkammhlaupsstraumur());
		electricalInstallation.setInsulationResistence(tilkynningLokVerksBean.getEinangrunNeysluveitu());
		electricalInstallation.setGroundingResistence(tilkynningLokVerksBean.getHringrasarvidnamJardskaut());
		electricalInstallation.setShortCircuitAmpere(tilkynningLokVerksBean.getSkammhlaupsstraumurNeysluveitu());
		electricalInstallation.setResistence(tilkynningLokVerksBean.getHringrasarvidnamNeysluveitu());
		electricalInstallation.setVoltagePhaseN(tilkynningLokVerksBean.getMaeldSpennaFasiN());
		electricalInstallation.setVoltagePhasePhase(tilkynningLokVerksBean.getMaeldSpennaFasiFasi());
		
		// note: fuse attached attribute might be null (that means "unknown")
		String fuseAttachedString = tilkynningLokVerksBean.getLekastraumsrofi();
		Boolean fuseAttached = null;
		if (InitialData.LEKASTRAUMSROFI_I_LAGI_KEY.equals(fuseAttachedString)) {
			fuseAttached = Boolean.TRUE;
		}
		else if (InitialData.LEKASTRAUMSROFI_EKKI_TIL_STADAR_KEY.equals(fuseAttachedString)) {
			fuseAttached = Boolean.FALSE;
		}
		electricalInstallation.setFuseAttached(fuseAttached);
		
		electricalInstallation.setFuseVoltage(tilkynningLokVerksBean.getSpennuhaekkunUtleysingVolt());
		electricalInstallation.setFuseTime(tilkynningLokVerksBean.getLekastraumsrofaUtleysingMillisecond());
		electricalInstallation.setMeasurementRemarks(tilkynningLokVerksBean.getSkyringarMaelingar());
	}
	
	private void storeMetersOfManagedBeans(		
		Rafverktaka rafverktaka, 
		TilkynningVertakaBean tilkynningVertakaBean,
		TilkynningLokVerksBean tilkynningLokVerksBean) throws CreateException, EJBException, RemoveException {
		ElectricalInstallation electricalInstallation = rafverktaka.getElectricalInstallation();
		Collection metersInDatabase = null;
		// handling all meters (mapping meter to maelir)
		MeterHome meterHomeTemp = getMeterHome();
		try {
			metersInDatabase = meterHomeTemp.findMetersByElectricalInstallation(electricalInstallation);
		}
		catch (FinderException ex) {
			// nothing found that is fine (there might be no meters)
			// nothing to do 
		}
		// special case stadur maelir
		Maelir stadurMaelir = tilkynningVertakaBean.getStadurMaelir();
		Meter stadurMeter = findMeterByContextAndIndex(metersInDatabase, InitialData.STADUR, 0, electricalInstallation);
		storeMaelir(stadurMaelir, stadurMeter);
		// prevent deleting!
		metersInDatabase.remove(stadurMeter);
		// specail case meter in report (second form)
		Maelir maelirInReport = tilkynningLokVerksBean.getMaelir();
		Meter meterInReport = findMeterByContextAndIndex(metersInDatabase, InitialData.METER_IN_REPORT, 0, electricalInstallation);
		storeMaelir(maelirInReport, meterInReport);
		// prevent deleting!
		metersInDatabase.remove(meterInReport);
		// meters in list
		Map maelirMap = tilkynningVertakaBean.getList();
		Iterator iterator = maelirMap.keySet().iterator();
		while (iterator.hasNext()) {
			String context = (String) iterator.next();
			Collection coll = (Collection) maelirMap.get(context);
			Iterator maelirIterator = coll.iterator();
			while (maelirIterator.hasNext()) {
				Maelir maelir = (Maelir) maelirIterator.next();
				if (maelir.isValid()) {
					Meter meter = findMeterByContextAndIndex(metersInDatabase, context, maelir.getPriorityWithinContext(), electricalInstallation);
					storeMaelir(maelir, meter);
					// do not keep the meter in the collection
					metersInDatabase.remove(meter);
				}
			}
		}
		// delete the meters in the database that are left
		Iterator toDelete = metersInDatabase.iterator();
		while (toDelete.hasNext()) {
			Meter meterToDelete = (Meter) toDelete.next();
			meterToDelete.remove();
		}
		
		
	}
	
	// lazy searching, collection is usually very small 
	private Meter findMeterByContextAndIndex(Collection coll, String context, int index, ElectricalInstallation electricalInstallation) throws CreateException {
		if (coll != null) {
			Iterator iterator = coll.iterator();
			while (iterator.hasNext()) {
				Meter meter = (Meter) iterator.next();
				if ((context.equals(meter.getContext())) && (index == meter.getPriorityWithinContext())) {
					// found!
					return meter;
				}
			}
		}
		// not found, create new one
		Meter newMeter =  getMeterHome().create();
		newMeter.setElectricalInstallation(electricalInstallation);
		return newMeter;
		
	}
	
	/**
	 * Returns number calculated from current time:
	 * 
	 * year, month, day and  seconds of the day
	 * 
	 *  e.g. 31.05.2007 16:12:45 returns 07053158365
	 * 
	 * @return
	 */
	private String generateExternalProjectID() {
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyMMdd");
		String dateString = dateformat.format(date);
		StringBuffer buffer = new StringBuffer(dateString);
		int secondsOfDay = calendar.get(Calendar.HOUR_OF_DAY) * 3600;
		secondsOfDay += calendar.get(Calendar.MINUTE) *60;
		secondsOfDay += calendar.get(Calendar.SECOND);
		buffer.append(secondsOfDay);
		return buffer.toString();
	}
	
	public void initializeManagedBeans(
			Rafverktaka rafverktaka ,
			TilkynningVertakaBean tilkynningVertakaBean, 
			TilkynningLokVerksBean tilkynningLokVerksBean) {
		initializeManagedBean(rafverktaka, tilkynningVertakaBean);
		initializeManagedBean(rafverktaka, tilkynningVertakaBean, tilkynningLokVerksBean);
		initializeManagedBeansByMeters(rafverktaka, tilkynningVertakaBean, tilkynningLokVerksBean);
	}
	
	public KeyValuePair getPDFApplication(ElectricalInstallation electricalInstallation) throws IOException {
		String downloadURL = getElectricalInstallationRendererBusiness().getPDFApplication(electricalInstallation);
		return new KeyValuePair(downloadURL, null);
	}
	
	
	public KeyValuePair getPDFApplicationAndSendEmails(ElectricalInstallation electricalInstallation) throws IOException {
		String downloadURL = getElectricalInstallationRendererBusiness().getPDFApplication(electricalInstallation);
		String result = getUserMessagesBusiness().sendDataToEnergyCompany(electricalInstallation, downloadURL);
		return new KeyValuePair(downloadURL, result);
	}
	
	private void initializeManagedBean(Rafverktaka rafverktaka, TilkynningVertakaBean tilkynningVertakaBean) {
		// move all values from the entity to the managed bean
		ElectricalInstallation electricalInstallation = rafverktaka.getElectricalInstallation();
		if (electricalInstallation == null) {
			//nothing to do
			return;
		}
		tilkynningVertakaBean.initialize();
		// has to be done at the beginning
		tilkynningVertakaBean.setRafverktaka(rafverktaka);
		// energy company
		Integer energyCompanyInteger = electricalInstallation.getEnergyCompanyID();
		String energyCompany = (energyCompanyInteger == null) ? null : energyCompanyInteger.toString();
		// real estate
		RealEstate realEstate = electricalInstallation.getRealEstate();
		if (realEstate != null) {
			// set street number
			tilkynningVertakaBean.setGotunumer(realEstate.getStreetNumber());
			RealEstateIdentifier realEstateIdentifier = RealEstateIdentifier.getInstance(realEstate);
			if (! realEstateIdentifier.isDummy()) {
				Fasteign fasteign = new Fasteign(realEstate);
				tilkynningVertakaBean.initFastanumer(realEstateIdentifier.getIdentifierAsString());
				tilkynningVertakaBean.setVeitustadurDisplay(fasteign.getDescription());
				tilkynningVertakaBean.initializeWorkingPlaceErrorMessage(fasteign);
			}
			Street street = realEstate.getStreet();
			if (street != null) { 
				// set street name
				tilkynningVertakaBean.setGata(street.getName());
				// set postal code
				PostalCode postalCode = street.getPostalCode();
				if (postalCode != null) {
					tilkynningVertakaBean.setPostnumer(postalCode.getPostalCode());
				}
			}
		}
		tilkynningVertakaBean.setOrkuveitufyrirtaeki(energyCompany);
		tilkynningVertakaBean.setExternalProjectID(electricalInstallation.getExternalProjectID());
		tilkynningVertakaBean.setPersonInCharge(electricalInstallation.getPersonInCharge());
		tilkynningVertakaBean.setNotkunarflokkur(electricalInstallation.getType());
		tilkynningVertakaBean.setHeimtaug(electricalInstallation.getCurrentLineModification());
		tilkynningVertakaBean.setHeimtaugTengist(electricalInstallation.getCurrentLineConnectionModification());
		tilkynningVertakaBean.setStofn1(electricalInstallation.getHomeLineA());
		tilkynningVertakaBean.setStofn2(electricalInstallation.getHomeLineB());
		tilkynningVertakaBean.setStofn3(electricalInstallation.getHomeLineC());
		tilkynningVertakaBean.setAdaltafla(electricalInstallation.getSwitchPanelModification());
		tilkynningVertakaBean.setVarnarradstoefun(electricalInstallation.getElectronicalProtectiveMeasures());
		tilkynningVertakaBean.setBeidniUm(electricalInstallation.getApplication());
		tilkynningVertakaBean.setUppsett(electricalInstallation.getPower());
		tilkynningVertakaBean.setNumerToeflu(electricalInstallation.getSwitchPanelNumber());
		tilkynningVertakaBean.setSpennukerfi(electricalInstallation.getVoltageSystem());
		tilkynningVertakaBean.setAnnad(electricalInstallation.getVoltageSystemOther());
		tilkynningVertakaBean.setSkyringar(electricalInstallation.getApplicationRemarks());
	}
	
	private void initializeManagedBean(Rafverktaka rafverktaka, TilkynningVertakaBean tilkynningVertakaBean, TilkynningLokVerksBean tilkynningLokVerksBean) {
		ElectricalInstallation electricalInstallation = rafverktaka.getElectricalInstallation();
		tilkynningLokVerksBean.initialize();
		boolean doOnlyCopy = (! getElectricalInstallationState().isApplicationReportStored(electricalInstallation));
		tilkynningLokVerksBean.setRafverktaka(rafverktaka);
		
		String type = (doOnlyCopy) ? tilkynningVertakaBean.getNotkunarflokkur() : electricalInstallation.getTypeInReport();
		tilkynningLokVerksBean.setNotkunarflokkur(type);
		
		String voltageSystem = (doOnlyCopy) ? tilkynningVertakaBean.getSpennukerfi() : electricalInstallation.getVoltageSystemInReport();
		tilkynningLokVerksBean.setSpennukerfi(voltageSystem);
		
		String voltageSystemOther = (doOnlyCopy) ? tilkynningVertakaBean.getAnnad() : electricalInstallation.getVoltageSystemOtherInReport();
		tilkynningLokVerksBean.setAnnad(voltageSystemOther);
		
		List epm = (doOnlyCopy) ? tilkynningVertakaBean.getVarnarradstoefun() : electricalInstallation.getElectronicalProtectiveMeasuresInReport();
		tilkynningLokVerksBean.setVarnarradstoefun(epm);

		if (doOnlyCopy) {
			// electricalInstallation might be null!
			return;
		}
		tilkynningLokVerksBean.setTilkynnt(electricalInstallation.getAnnouncement());
		tilkynningLokVerksBean.setTilkynntAnnad(electricalInstallation.getAnnouncementOther());
		
		tilkynningLokVerksBean.setSkyring(electricalInstallation.getAnnouncementRemarks());
		
		tilkynningLokVerksBean.setJardskaut(electricalInstallation.getGrounding());
		tilkynningLokVerksBean.setJardskautAnnad(electricalInstallation.getGroundingOther());
		tilkynningLokVerksBean.setSkyringar(electricalInstallation.getRemarks());
		// third step
		tilkynningLokVerksBean.setHringrasarvidam(electricalInstallation.getSwitchPanelResistence());
		tilkynningLokVerksBean.setSkammhlaupsstraumur(electricalInstallation.getSwitchPanelAmpere());
		tilkynningLokVerksBean.setEinangrunNeysluveitu(electricalInstallation.getInsulationResistence());
		tilkynningLokVerksBean.setHringrasarvidnamJardskaut(electricalInstallation.getGroundingResistence());
		tilkynningLokVerksBean.setSkammhlaupsstraumurNeysluveitu(electricalInstallation.getShortCircuitAmpere());
		tilkynningLokVerksBean.setHringrasarvidnamNeysluveitu(electricalInstallation.getResistence());
		tilkynningLokVerksBean.setMaeldSpennaFasiN(electricalInstallation.getVoltagePhaseN());
		tilkynningLokVerksBean.setMaeldSpennaFasiFasi(electricalInstallation.getVoltagePhasePhase());
		// fuse attached might be null (that means "unknown")
		Boolean fuseAttached = electricalInstallation.isFuseAttached();
		if (fuseAttached != null) {
			if (fuseAttached.booleanValue()) {
				tilkynningLokVerksBean.setLekastraumsrofi(InitialData.LEKASTRAUMSROFI_I_LAGI_KEY);
			}
			else {
				tilkynningLokVerksBean.setLekastraumsrofi(InitialData.LEKASTRAUMSROFI_EKKI_TIL_STADAR_KEY);
			}
		}
		tilkynningLokVerksBean.setSpennuhaekkunUtleysingVolt(electricalInstallation.getFuseVoltage());
		tilkynningLokVerksBean.setLekastraumsrofaUtleysingMillisecond(electricalInstallation.getFuseTime());
		tilkynningLokVerksBean.setSkyringarMaelingar(electricalInstallation.getMeasurementRemarks());
	}
	
	
	
	private void initializeManagedBeansByMeters(
		Rafverktaka rafverktaka, 
		TilkynningVertakaBean tilkynningVertakaBean,
		TilkynningLokVerksBean tilkynningLokVerksBean) {
		ElectricalInstallation electricalInstallation = rafverktaka.getElectricalInstallation();
		if (electricalInstallation == null) {
			//nothing to do
			return;
		}
		MaelirList maelirList = getMaelirList(electricalInstallation);
		// handling all meters (mapping meter to maelir)
		tilkynningVertakaBean.initList(maelirList.getMaelirListMap());
		tilkynningVertakaBean.setStadurMaelir(maelirList.getStadurMaelir());
		tilkynningLokVerksBean.setMaelir(maelirList.getMaelir());
	}
		
	public MaelirList getMaelirList(ElectricalInstallation electricalInstallation) {
		MaelirList maelirList = new MaelirList();
		MeterHome meterHomeTemp = getMeterHome();
		Collection coll = null;
		try {
			coll = meterHomeTemp.findMetersByElectricalInstallation(electricalInstallation);
		}
		catch (FinderException ex) {
			// nothing found that is fine (there might be no meters)
			// nothing to do 
			return maelirList;
		}
		// first step putting all meters into the map
		Iterator iterator = coll.iterator();
		Map maelirMap = maelirList.getMaelirListMap();
		while (iterator.hasNext()) {
			Meter meter = (Meter) iterator.next();
			Maelir maelir = initializeMaelir(meter);
			String context = meter.getContext();
			// special case stadur maelir
			if (InitialData.STADUR.equals(context)) {
				maelirList.setStadurMaelir(maelir);
			}
			else if (InitialData.METER_IN_REPORT.equals(context)) {
				maelirList.setMaelir(maelir);
			}
			else {
				List list = (List) maelirMap.get(context);
				if (list != null) { 
					list.add(maelir);
					maelir.setMyList(list);
				}
			}
		}
		// second step: sorting all meter corresponding to the index
		Iterator meterLists = maelirMap.values().iterator();
		// comparator: index ascending
		Comparator comparator = new Comparator() {
			public int compare(Object o1, Object o2) {
				Maelir meter1 = (Maelir) o1;
				Maelir meter2 = (Maelir) o2;
				return meter1.getPriorityWithinContext() - meter2.getPriorityWithinContext();
 			}
		};
		// sorting and setting of the right index
		while (meterLists.hasNext()) {
			List list = (List) meterLists.next();
			Collections.sort(list, comparator);
			// the very first one is the dummy maelir, put it at the end of the list, set the priority right 
			int size = list.size();
			if (list.size() > 1) {
				// if only one element nothing to do!
				Maelir lastMaelir = (Maelir) list.get(size - 1);
				int highestIndex = lastMaelir.getPriorityWithinContext();
				highestIndex++;
				Maelir dummyMaelir = (Maelir) list.get(0);
				dummyMaelir.setPriorityWithinContext(highestIndex);
				// move the dummy from the beginning to the end
				list.remove(0);
				list.add(dummyMaelir);
			}
		}
		return maelirList;
	}
	
	private Maelir initializeMaelir(Meter meter) {
		Maelir maelir = new Maelir(meter.getContext(), meter.getPriorityWithinContext());
		maelir.setContext(meter.getContext());
		maelir.setPriorityWithinContext(meter.getPriorityWithinContext());
		maelir.setNumer(meter.getNumber());
		maelir.setStadur(meter.getPlace());
		maelir.setAmpere(meter.getAmpere());
		maelir.setFasa(meter.getPhase());
		maelir.setHjalpataeki(meter.getDevice());
		maelir.setTaxti(meter.getRate());
		return maelir;
	}
	
	private void storeMaelir(Maelir maelir, Meter meter) {
		if (! maelir.isValid()) {
			return;
		}
		meter.setContext(maelir.getContext());
		meter.setPriorityWithinContext(maelir.getPriorityWithinContext());
		meter.setNumber(maelir.getNumer());
		meter.setPlace(maelir.getStadur());
		meter.setAmpere(maelir.getAmpere());
		meter.setPhase(maelir.getFasa());
		meter.setDevice(maelir.getHjalpataeki());
		meter.setRate(maelir.getTaxti());
		meter.store();
	}
	
	public ElectricalInstallation getElectricalInstallationByPrimaryKey(Object primaryKey) throws FinderException {
		return getElectricalInstallationHome().findByPrimaryKey(primaryKey);
	}

		
	public Collection getElectricalInstallationByElectrician(User electrician) throws FinderException {
		return getElectricalInstallationHome().findElectricalInstallationByElectrician(electrician);
	}
	
	public Collection getElectricalInstallationByRealEstateNumber(String realEstateNumber) throws FinderException {
		return getElectricalInstallationHome().findElectricalInstallationByRealEstateNumber(realEstateNumber);
	}
	
	public Collection getOtherClosedElectricalInstallationByRealEstate(RealEstate realEstate, User currentUser) throws FinderException {
		return getElectricalInstallationHome().findOtherClosedElectricalInstallationByRealEstate(realEstate, currentUser);
	}
	
	public Collection getOtherClosedElectricalInstallationByRealEstateIdentifer(RealEstateIdentifier realEstateIdentifier, User currentUser) throws FinderException {
		return getElectricalInstallationHome().findOtherClosedElectricalInstallationByRealEstateIdentifer(realEstateIdentifier, currentUser);
	}
	
	public Collection getOtherOpenElectricalInstallationByRealEstate(RealEstate realEstate, User user) throws FinderException {
		return getElectricalInstallationHome().findOtherOpenElectricalInstallationByRealEstate(realEstate, user);
	}
	
	public Collection getOtherOpenElectricalInstallationByRealEstateIdentifier(RealEstateIdentifier realEstateIdentifier, User user) throws FinderException {
		return getElectricalInstallationHome().findOtherOpenElectricalInstallationByRealEstateIdentifier(realEstateIdentifier, user);
	}
	
	public Collection getElectricalInstallationByEnergyCompanyUser(User energyCompanyUser) {
		Group group = energyCompanyUser.getPrimaryGroup();
		try {
			return getElectricalInstallationHome().findElectricalInstallationByEnergyCompany(group);
		}
		catch (FinderException ex) {
			return new ArrayList(0);
		}
	}
	
	
	private ElectricalInstallationHome getElectricalInstallationHome() {
		if (electricalInstallationHome == null) {
			electricalInstallationHome = (ElectricalInstallationHome) retrieveHome(ElectricalInstallation.class);
		}
		return electricalInstallationHome;
	}
	
	private MeterHome getMeterHome() {
		if (meterHome == null) {
			meterHome = (MeterHome) retrieveHome(Meter.class);
		}
		return meterHome;
	}

	
	private RealEstateHome getRealEstateHome() {
		if (realEstateHome == null) {
			realEstateHome = (RealEstateHome) retrieveHome(RealEstate.class);
		}
		return realEstateHome;
	}
	
	private StreetHome getStreetHome() {
		if (streetHome == null) {
			streetHome = (StreetHome)  retrieveHome(Street.class);
		}
		return streetHome;
	}
	
	private PostalCodeHome getPostalCodeHome() {
		if (postalCodeHome == null) {
			postalCodeHome = (PostalCodeHome) retrieveHome(PostalCode.class);
		}
		return postalCodeHome;
	}
	
	private ElectricalInstallationRendererBusiness getElectricalInstallationRendererBusiness() {
		if (electricalInstallationRendererBusiness == null) {
			electricalInstallationRendererBusiness = (ElectricalInstallationRendererBusiness) getServiceBean(ElectricalInstallationRendererBusiness.class);
		}
		return electricalInstallationRendererBusiness;
	}

	
	private UserMessagesBusiness  getUserMessagesBusiness() {
		if (userMessagesBusiness == null) {
			userMessagesBusiness = (UserMessagesBusiness) getServiceBean(UserMessagesBusiness.class);
		}
		return userMessagesBusiness;
	}

	
	public ElectricalInstallationCaseBusiness getElectricalInstallationCaseBusiness() {
		if (electricalInstallationCaseBusiness == null) { 
			electricalInstallationCaseBusiness = (ElectricalInstallationCaseBusiness) getServiceBean(ElectricalInstallationCaseBusiness.class);
		}
		return electricalInstallationCaseBusiness;
	}
	
	public ElectricalInstallationState getElectricalInstallationState() {
		if (electricalInstallationState == null) {
			electricalInstallationState = new ElectricalInstallationState(getIWApplicationContext());
		}
		return electricalInstallationState;
	}
	
	public ElectricalInstallationCache getElectricalInstallationCache() {
		if (electricalInstallationCache == null) {
			electricalInstallationCache = ElectricalInstallationCache.getInstance();
		}
		return electricalInstallationCache;
	}
	
	private IBOService getServiceBean(Class serviceClass ) {
		IBOService service = null;
		try {
			service = IBOLookup.getServiceInstance(getIWApplicationContext(), serviceClass);
		}
		catch (RemoteException rme) {
			throw new RuntimeException(rme.getMessage());
		}
		return service;
	}
	
	
	private IDOHome retrieveHome(Class entityClass ) {
		IDOHome home = null;
		try {
			home = IDOLookup.getHome(entityClass);
		}
		catch (RemoteException rme) {
			throw new RuntimeException(rme.getMessage());
		}
		return home;
	}
	
	
}
