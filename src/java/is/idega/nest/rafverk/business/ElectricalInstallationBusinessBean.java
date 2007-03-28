/*
 * $Id: ElectricalInstallationBusinessBean.java,v 1.2 2007/03/28 17:21:15 thomas Exp $
 * Created on Mar 16, 2007
 *
 * Copyright (C) 2007 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package is.idega.nest.rafverk.business;

import is.idega.nest.rafverk.bean.BaseBean;
import is.idega.nest.rafverk.bean.InitialData;
import is.idega.nest.rafverk.bean.TilkynningLokVerksBean;
import is.idega.nest.rafverk.bean.TilkynningVertakaBean;
import is.idega.nest.rafverk.data.Maelir;
import is.idega.nest.rafverk.domain.ElectricalInstallation;
import is.idega.nest.rafverk.domain.ElectricalInstallationHome;
import is.idega.nest.rafverk.domain.Fasteign;
import is.idega.nest.rafverk.domain.Meter;
import is.idega.nest.rafverk.domain.MeterHome;
import is.idega.nest.rafverk.domain.Rafverktaka;
import is.postur.Gata;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.RemoveException;
import com.idega.business.IBOServiceBean;
import com.idega.core.location.data.PostalCode;
import com.idega.core.location.data.PostalCodeHome;
import com.idega.core.location.data.RealEstate;
import com.idega.core.location.data.RealEstateHome;
import com.idega.core.location.data.Street;
import com.idega.core.location.data.StreetHome;
import com.idega.data.IDOLookup;
import com.idega.user.data.User;
import com.idega.util.StringHandler;


/**
 * 
 *  Last modified: $Date: 2007/03/28 17:21:15 $ by $Author: thomas $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.2 $
 */
public class ElectricalInstallationBusinessBean extends IBOServiceBean implements ElectricalInstallationBusiness {
	
	private ElectricalInstallationHome electricalInstallationHome = null;
	
	private MeterHome meterHome = null;
	
	private RealEstateHome realEstateHome = null;
	
	private StreetHome streetHome = null;
	
	private PostalCodeHome postalCodeHome = null;
	
	public boolean storeManagedBeans(			
			Rafverktaka rafverktaka ,
			TilkynningVertakaBean tilkynningVertakaBean, 
			TilkynningLokVerksBean tilkynningLokVerksBean) {
		ElectricalInstallation electricalInstallation = rafverktaka.getElectricalInstallation();
		if (electricalInstallation == null) {
			try {
				electricalInstallation = createElectricalInstallation();
				rafverktaka.setElectricalInstallation(electricalInstallation);
			}
			catch (CreateException e) {	
				e.printStackTrace();
				return false;
			}
		}
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
		storeElectrician(rafverktaka);
		storeManagedBean(rafverktaka, tilkynningVertakaBean);
		storeManagedBean(rafverktaka, tilkynningLokVerksBean);
		electricalInstallation.store();
		rafverktaka.initialize(electricalInstallation);
		return true;
	}
	
	private ElectricalInstallation createElectricalInstallation() throws CreateException {
		return  getElectricalInstallationHome().create();
	}
	
	private void storeElectrician(Rafverktaka rafverktaka) {
		User electrician = rafverktaka.getRafverktaki().getElectrician();
		ElectricalInstallation electricalInstallation = rafverktaka.getElectricalInstallation();
		electricalInstallation.setElectrician(electrician);
	}
	
	private void storeRealEstate(ElectricalInstallation electricalInstallation, TilkynningVertakaBean tilkynningVertakaBean) throws CreateException, EJBException, RemoveException {
		String realEstateNumber = tilkynningVertakaBean.getFastanumer();
		boolean newRealEstateIsNotDummy = StringHandler.isNotEmpty(realEstateNumber);
		RealEstate realEstate = electricalInstallation.getRealEstate();
		if (realEstate == null) {
			if (newRealEstateIsNotDummy) {
				// "normal" case if the application is new and complete
				setRealEstate(realEstateNumber, electricalInstallation, tilkynningVertakaBean);
				return;
			}
			// application is new but not complete
			setDummyRealEstateOrNull(electricalInstallation, tilkynningVertakaBean);
			return;
		}
		// there is a real estate....
		// check the existing real estate
		String oldRealEstateNumber = realEstate.getRealEstateNumber();
		boolean oldRealEstateIsNotDummy = StringHandler.isNotEmpty(oldRealEstateNumber);
		if (oldRealEstateIsNotDummy) {
			if (newRealEstateIsNotDummy) {
				// "normal" case: application replaces real estate with complete real estate
				setRealEstate(realEstateNumber, electricalInstallation, tilkynningVertakaBean);
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
			setRealEstate(realEstateNumber, electricalInstallation, tilkynningVertakaBean);
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
		setStreetToRealEstate(realEstate, tilkynningVertakaBean);
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
	
	private void setStreetToRealEstate(RealEstate realEstate, TilkynningVertakaBean tilkynningVertakaBean) throws CreateException {
		Street street = findOrCreateStreet(tilkynningVertakaBean);
		if (street != null) {
			realEstate.setStreetNumber(tilkynningVertakaBean.getGotunumer());
			realEstate.setStreet(street);
		}
	}
		
	private void setRealEstate(String realEstateNumber, ElectricalInstallation electricalInstallation, TilkynningVertakaBean tilkynningVertakaBean) throws CreateException {
		RealEstate realEstate = findRealEstate(realEstateNumber);
		// not found? create one!
		if (realEstate == null) {
			realEstate = createRealEstate(realEstateNumber, tilkynningVertakaBean);
			if (realEstate == null) {
				// could not be created (realEstateNumber wrong!), give up and set dummy one
				setDummyRealEstateOrNull(electricalInstallation, tilkynningVertakaBean);
				return;
			}
			setStreetToRealEstate(realEstate, tilkynningVertakaBean);
			realEstate.store();
		}
		// found? just set in electrical installation
		electricalInstallation.setRealEstate(realEstate);
	}
			
		
	private Street findOrCreateStreet(TilkynningVertakaBean tilkynningVertakaBean) throws CreateException {
		String postalCode = tilkynningVertakaBean.getPostnumer();
		String streetName = tilkynningVertakaBean.getGata();
		if (StringHandler.isEmpty(postalCode) || StringHandler.isEmpty(streetName)) {
			return null;
		}
		Gata gata = lookupGata(streetName, postalCode);
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
			postalCodeObject = getPostalCodeHome().findByPostalCode(postalCode);
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
		
	private RealEstate findRealEstate(String realEstateNumber) {
		if (StringHandler.isEmpty(realEstateNumber)) {
			return null;
		}
		// try to find an existing (that is in our database existing) real estate
		RealEstate realEstate = null;
		try {
			realEstate = getRealEstateHome().findRealEstateByNumber(realEstateNumber);
		}
		catch (FinderException e) {
			realEstate = null;
		}
		return realEstate;
	}
	
	private RealEstate createRealEstate(String realEstateNumber, TilkynningVertakaBean tilkynningVertakaBean) throws CreateException {
		if (StringHandler.isEmpty(realEstateNumber)) {
			// should not happen, this method is called when realEstateNumber is not empty
			return null;
		}
		Fasteign fasteign = lookupFasteign(realEstateNumber, tilkynningVertakaBean);
		if (fasteign == null) {
			// should not happen, something wrong with GUI?
			return null;
		}
		RealEstate realEstate = getRealEstateHome().create();
		realEstate.setRealEstateNumber(realEstateNumber);
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
	
	private Fasteign lookupFasteign(String realEstateNumber, TilkynningVertakaBean tilkynningVertakaBean) {
		return tilkynningVertakaBean.lookupFasteign(realEstateNumber);
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
		// energy consumer
		electricalInstallation.setEnergyConsumerName(tilkynningVertakaBean.getNafnOrkukaupanda());
		electricalInstallation.setEnergyConsumerPersonalID(tilkynningVertakaBean.getKennitalaOrkukaupanda());
		electricalInstallation.setEnergyConsumerWorkPhone(tilkynningVertakaBean.getVinnusimiOrkukaupanda());
		electricalInstallation.setEnergyConsumerHomePhone(tilkynningVertakaBean.getHeimasimiOrkukaupanda());
		
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
		electricalInstallation.setVoltageSystemInReport(tilkynningVertakaBean.getAnnad());
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
		
		electricalInstallation.setFuseAttached(InitialData.LEKASTRAUMSROFI_I_LAGI_KEY.equals(tilkynningLokVerksBean.getLekastraumsrofi()));
		
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
		Meter stadurMeter = findMeterByContextAndIndex(metersInDatabase, InitialData.STADUR, 0);
		storeMaelir(stadurMaelir, stadurMeter);
		// specail case meter in report (second form)
		Maelir maelirInReport = tilkynningLokVerksBean.getMaelir();
		Meter meterInReport = findMeterByContextAndIndex(metersInDatabase, InitialData.METER_IN_REPORT, 0);
		storeMaelir(maelirInReport, meterInReport);
		// meters in list
		Map maelirMap = tilkynningVertakaBean.getList();
		Iterator iterator = maelirMap.values().iterator();
		while (iterator.hasNext()) {
			Collection coll = (Collection) iterator.next();
			Iterator maelirIterator = coll.iterator();
			while (maelirIterator.hasNext()) {
				Maelir maelir = (Maelir) maelirIterator.next();
				Meter meter = findMeterByContextAndIndex(metersInDatabase, maelir.getContext(), maelir.getPriorityWithinContext());
				storeMaelir(maelir, meter);
				// do not keep the meter in the collection
				metersInDatabase.remove(meter);
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
	private Meter findMeterByContextAndIndex(Collection coll, String context, int index) throws CreateException {
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
		return getMeterHome().create();
	}
	
	public void initializeManagedBeans(
			Rafverktaka rafverktaka ,
			TilkynningVertakaBean tilkynningVertakaBean, 
			TilkynningLokVerksBean tilkynningLokVerksBean) {
		initializeManagedBean(rafverktaka, tilkynningVertakaBean);
		initializeManagedBean(rafverktaka, tilkynningLokVerksBean);
		//initializeManagedBeansByMeters(electricalInstallation, tilkynningVertakaBean, tilkynningLokVerksBean);
	}
	
	
	private void initializeManagedBean(Rafverktaka rafverktaka, TilkynningVertakaBean tilkynningVertakaBean) {
		// move all values from the entity to the managed bean
		ElectricalInstallation electricalInstallation = rafverktaka.getElectricalInstallation();
		tilkynningVertakaBean.initialize();
		// energy company
		Integer energyCompanyInteger = electricalInstallation.getEnergyCompanyID();
		String energyCompany = (energyCompanyInteger == null) ? null : energyCompanyInteger.toString();
		// real estate
		RealEstate realEstate = electricalInstallation.getRealEstate();
		if (realEstate != null) {
			// set street number
			tilkynningVertakaBean.setGotunumer(realEstate.getStreetNumber());
			String number = realEstate.getRealEstateNumber();
			if (number != null) {
				Fasteign fasteign = new Fasteign(realEstate);
				tilkynningVertakaBean.setVeitustadurDisplay(fasteign.getDescription());
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
		tilkynningVertakaBean.setRafverktaka(rafverktaka);
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
	}
	
	private void initializeManagedBean(Rafverktaka rafverktaka, TilkynningLokVerksBean tilkynningLokVerksBean) {
		ElectricalInstallation electricalInstallation = rafverktaka.getElectricalInstallation();
		tilkynningLokVerksBean.initialize();
		tilkynningLokVerksBean.setRafverktaka(rafverktaka);
		tilkynningLokVerksBean.setTilkynnt(electricalInstallation.getAnnouncement());
		tilkynningLokVerksBean.setTilkynntAnnad(electricalInstallation.getAnnouncementOther());
		tilkynningLokVerksBean.setNotkunarflokkur(electricalInstallation.getTypeInReport());
		tilkynningLokVerksBean.setSkyring(electricalInstallation.getAnnouncementRemarks());
		tilkynningLokVerksBean.setSpennukerfi(electricalInstallation.getVoltageSystemInReport());
		tilkynningLokVerksBean.setAnnad(electricalInstallation.getVoltageSystemOtherInReport());
		tilkynningLokVerksBean.setVarnarradstoefun(electricalInstallation.getElectronicalProtectiveMeasuresInReport());
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
		if (electricalInstallation.isFuseAttached()) {
			tilkynningLokVerksBean.setLekastraumsrofi(InitialData.LEKASTRAUMSROFI_I_LAGI_KEY);
		}
		else {
			tilkynningLokVerksBean.setLekastraumsrofi(InitialData.LEKASTRAUMSROFI_EKKI_TIL_STADAR_KEY);
		}
		tilkynningLokVerksBean.setSpennuhaekkunUtleysingVolt(electricalInstallation.getFuseVoltage());
		tilkynningLokVerksBean.setLekastraumsrofaUtleysingMillisecond(electricalInstallation.getFuseTime());
		tilkynningLokVerksBean.setSkyringarMaelingar(electricalInstallation.getMeasurementRemarks());
	}
	
	private void initializeManagedBeansByMeters(
		ElectricalInstallation electricalInstallation, 
		TilkynningVertakaBean tilkynningVertakaBean,
		TilkynningLokVerksBean tilkynningLokVerksBean) {
		// handling all meters (mapping meter to maelir)
		MeterHome meterHomeTemp = getMeterHome();
		Collection coll = null;
		try {
			coll = meterHomeTemp.findMetersByElectricalInstallation(electricalInstallation);
		}
		catch (FinderException ex) {
			// nothing found that is fine (there might be no meters)
			// nothing to do 
			return;
		}
		// first step putting all meters into the map
		Iterator iterator = coll.iterator();
		Map maelirMap = tilkynningVertakaBean.getList();
		while (iterator.hasNext()) {
			Meter meter = (Meter) iterator.next();
			Maelir maelir = new Maelir();
			initializeMaelir(maelir, meter);
			String context = meter.getContext();
			// special case stadur maelir
			if (InitialData.STADUR.equals(context)) {
				tilkynningVertakaBean.setStadurMaelir(maelir);
			}
			else if (InitialData.METER_IN_REPORT.equals(context)) {
				tilkynningLokVerksBean.setMaelir(maelir);
			}
			else {
				List list = (List) maelirMap.get(context);
				list.add(maelir);
			}
		}
		// second step: sorting all meter corresponding to the index
		Iterator meterLists = maelirMap.values().iterator();
		// comparator: index ascending
		Comparator comparator = new Comparator() {
			public int compare(Object o1, Object o2) {
				Meter meter1 = (Meter) o1;
				Meter meter2 = (Meter) o2;
				return meter1.getPriorityWithinContext() - meter2.getPriorityWithinContext();
 			}
		};
		// sorting	
		while (meterLists.hasNext()) {
			List list = (List) meterLists.next();
			Collections.sort(list, comparator);
		}
	}
	
	private void initializeMaelir(Maelir maelir, Meter meter) {
		maelir.setContext(meter.getContext());
		maelir.setPriorityWithinContext(meter.getPriorityWithinContext());
		maelir.setNumer(meter.getNumber());
		maelir.setStadur(meter.getPlace());
		maelir.setAmpere(meter.getAmpere());
		maelir.setFasa(meter.getPhase());
		maelir.setHjalpataeki(meter.getDevice());
		maelir.setTaxti(meter.getRate());
	}
	
	private void storeMaelir(Maelir maelir, Meter meter) {
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
	

		
	public Collection getElectricalInstallationByElectrician(User electrician) throws FinderException {
		return getElectricalInstallationHome().findElectricalInstallationByElectrician(electrician);
	}
	
	
	public ElectricalInstallationHome getElectricalInstallationHome() {
		return (ElectricalInstallationHome) retrieveHome(electricalInstallationHome, ElectricalInstallation.class);
	}
	
	public MeterHome getMeterHome() {
		return (MeterHome) retrieveHome(meterHome, Meter.class);
	}

	
	public RealEstateHome getRealEstateHome() {
		return (RealEstateHome)  retrieveHome(realEstateHome, RealEstate.class);
	}
	
	public StreetHome getStreetHome() {
		return (StreetHome)  retrieveHome(streetHome, Street.class);
	}
	
	public PostalCodeHome getPostalCodeHome() {
		return (PostalCodeHome) retrieveHome(postalCodeHome, PostalCode.class);
	}
	
	private Object retrieveHome(Object home, Class entityClass ) {
		if (home == null) {
			try {
				home = IDOLookup.getHome(entityClass);
			}
			catch (RemoteException rme) {
				throw new RuntimeException(rme.getMessage());
			}
		}
		return home;
	}
	
	
}
