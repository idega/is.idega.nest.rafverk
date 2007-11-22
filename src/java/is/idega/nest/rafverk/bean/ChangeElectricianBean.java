/*
 * $Id: ChangeElectricianBean.java,v 1.9 2007/11/22 16:23:44 thomas Exp $
 * Created on Aug 13, 2007
 *
 * Copyright (C) 2007 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package is.idega.nest.rafverk.bean;

import is.fmr.landskra.Fasteign;
import is.idega.nest.rafverk.business.ElectricalInstallationBusiness;
import is.idega.nest.rafverk.business.ElectricalInstallationCaseBusiness;
import is.idega.nest.rafverk.business.UserMessagesBusiness;
import is.idega.nest.rafverk.data.RealEstateIdentifier;
import is.idega.nest.rafverk.domain.ElectricalInstallation;
import is.idega.nest.rafverk.domain.Orkukaupandi;
import is.idega.nest.rafverk.domain.Rafverktaka;
import is.idega.nest.rafverk.domain.Rafverktaki;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.ejb.FinderException;

import com.idega.block.process.data.Case;
import com.idega.user.data.User;
import com.idega.util.StringHandler;
import com.idega.util.datastructures.list.KeyValuePair;


/**
 * 
 *  Last modified: $Date: 2007/11/22 16:23:44 $ by $Author: thomas $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.9 $
 */
public class ChangeElectricianBean extends RealEstateBean {
	
	private Map electricalInstallationList;
	
	private Map electricalInstallationMap; 
	
	private String electricalInstallationIDSelection;
	
	private ElectricalInstallationBusiness electricalInstallationBusiness;
	
	private ElectricalInstallationCaseBusiness electricalInstallationCaseBusiness;
	
	private UserMessagesBusiness userMessagesBusiness;
	
	private String messageStoring = null;
	
	public ChangeElectricianBean() {
    	initialize();
    }
	
	public void initialize() {
		super.initializeForm();
		messageStoring = StringHandler.EMPTY_STRING; 
		electricalInstallationList = new TreeMap(Collections.reverseOrder());
		electricalInstallationMap = new HashMap();
		initializeElectricalInstallationList(null);
	}
	
	// might be overwritten by subclasses
	void changedRealEstate(Fasteign fasteign) {
		RealEstateIdentifier realEstateIdentifier = RealEstateIdentifier.getInstance(fasteign);
		initializeElectricalInstallationList(realEstateIdentifier);
	}
	
	protected void initializeElectricalInstallationList(RealEstateIdentifier realEstateIdentifier) {
		electricalInstallationList.clear(); 
		electricalInstallationMap.clear();
		if (realEstateIdentifier == null) {
			return;
		}
		User currentUser = BaseBean.getCurrentUser();
		Collection verktokur = null;
		try {
			verktokur = getElectricalInstallationBusiness().getOtherOpenElectricalInstallationByRealEstateIdentifier(realEstateIdentifier, currentUser);
		}
		catch (RemoteException e) {
			throw new RuntimeException(e.getMessage());
		}
		catch (FinderException e) {
			verktokur = null;
		}
		if (verktokur == null || verktokur.isEmpty()) {
			electricalInstallationList.put(InitialData.NONE_ELECTRIC_INSTALLATION_SELECTION, "ekkert fannst");
		}
		else {
			// show only one electrical installation, if the electrician is the same
			// note: one electrician can have more than one application per working place
			// show here the oldest one!
			Set users = new HashSet();
			electricalInstallationList.put(InitialData.NONE_ELECTRIC_INSTALLATION_SELECTION, "Veldu rafverktöku");
			Comparator comparator = new Comparator() {

				public int compare(Object o1, Object o2) {
					ElectricalInstallation e1 = (ElectricalInstallation) o1;
					ElectricalInstallation e2 = (ElectricalInstallation) o2;
					Timestamp t1 = e1.getCreated();
					Timestamp t2 = e2.getCreated();
					return t1.compareTo(t2);
				}
			};
			List verktokurList = new ArrayList(verktokur);
			Collections.sort(verktokurList, comparator);
			Iterator iterator = verktokur.iterator();
			while (iterator.hasNext()) {
				ElectricalInstallation electricalInstallation = (ElectricalInstallation) iterator.next();
				User user = electricalInstallation.getElectrician();
				if (! users.contains(user)) {
					users.add(user);
					Rafverktaka verktaka = new Rafverktaka(electricalInstallation, getElectricalInstallationBusiness());
					String id = verktaka.getId();
					String label = getElectricalInstallationLabel(verktaka);
					electricalInstallationList.put(id, label);	
					electricalInstallationMap.put(id, verktaka);
				}
			}
		}
	}
	
	private String getElectricalInstallationLabel(Rafverktaka rafverktaka) {
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("Rafverktaki: ");
		Rafverktaki rafverktaki = rafverktaka.getRafverktaki();
		if (rafverktaki != null) {
			add(buffer, rafverktaki.getNafn());
		}
		
//		buffer.append(", Verknúmer: ");
//		add(buffer, rafverktaka.getExternalProjectID());
		
		buffer.append(", Orkukaupandi: ");
		Orkukaupandi orkukaupandi = rafverktaka.getOrkukaupandi();
		if (orkukaupandi != null) {
			add(buffer, orkukaupandi.getNafn());
		}
		
//		buffer.append(", Stada: ");
//		add(buffer, rafverktaka.getStadaDisplay());
		
		return buffer.toString();
	}
	
	private void add(StringBuffer buffer, String value) {
		buffer.append(StringHandler.getStringOrDash(value));
	}
	
	public Rafverktaka getCurrentElectricalInstallationSelection() {
		String id = getElectricalInstallationIDSelection();
		if (id == null ||InitialData.NONE_ELECTRIC_INSTALLATION_SELECTION.equals(id)) {
			return null;
		}
		return (electricalInstallationMap == null) ? null : (Rafverktaka) electricalInstallationMap.get(id);
	}

	public Map getElectricalInstallationList() {
		return electricalInstallationList;
	}
	
	public List getElectricalInstallationListiSelects() {
		return getListiSelects(getElectricalInstallationList());
	}
	
	// getter for second step...
	
	public String getNafnOrkukaupanda() {
		Orkukaupandi orkukaupandi = getCurrentElectricalInstallationSelection().getOrkukaupandi();
		if (orkukaupandi != null) {
			return orkukaupandi.getNafn();
		}
		return StringHandler.EMPTY_STRING;
	}
	
	public String getKennitalaOrkukaupanda() {
		Orkukaupandi orkukaupandi = getCurrentElectricalInstallationSelection().getOrkukaupandi();
		if (orkukaupandi != null) {
			return orkukaupandi.getKennitala();
		}
		return StringHandler.EMPTY_STRING;
	}
	
	public String getVeitustadurDisplay() {
		Rafverktaka currentElectricalInstallation = getCurrentElectricalInstallationSelection();
		if (currentElectricalInstallation == null) {
			return super.getVeitustadurDisplay();
		}
		String display = currentElectricalInstallation.getVeitustadurDisplay();
		return StringHandler.replaceIfEmpty(display, StringHandler.EMPTY_STRING);
	}
	
	public String getStadaDisplay() {
		return getCurrentElectricalInstallationSelection().getStadaDisplay();
	}
	
	// ...getter for second step
	
	// action method second step 
	public String changeElectrician() {
		// get the current selection of electric installation
		Rafverktaka currentElectricalInstallation = getCurrentElectricalInstallationSelection();
		ElectricalInstallation electricalInstallation = currentElectricalInstallation.getElectricalInstallation();
		ElectricalInstallationCaseBusiness caseBusiness = getElectricalInstallationCaseBusiness();
		try {
			KeyValuePair pair = caseBusiness.sendRequestForChangingElectrician(electricalInstallation);
			Case newCase = (Case) pair.getKey();
			if (newCase == null) {
				// case could not be created!!
				messageStoring = "Sorry. Request could not be sent. Problems appeared.";
				return "next";
			}
			messageStoring = getUserMessagesBusiness().getMessageAfterSendingRequestForChangeOfElectrician(electricalInstallation);
			String result = (String) pair.getValue();
			if (result != null) {
				// minor problems (case could be created, some problems with user messages
				messageStoring = result + messageStoring;
			}
			return "next";
		}
		catch (RemoteException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	// message for third step
	public String getMessageStoring() {
		return messageStoring;
	}
	
	public ElectricalInstallationBusiness getElectricalInstallationBusiness() {
		electricalInstallationBusiness = (ElectricalInstallationBusiness) 
			BaseBean.initializeServiceBean(electricalInstallationBusiness,ElectricalInstallationBusiness.class);
		return electricalInstallationBusiness;
	}
	
	public ElectricalInstallationCaseBusiness getElectricalInstallationCaseBusiness() {
		electricalInstallationCaseBusiness = (ElectricalInstallationCaseBusiness) 
			BaseBean.initializeServiceBean(electricalInstallationCaseBusiness, ElectricalInstallationCaseBusiness.class);
		return electricalInstallationCaseBusiness;
	}
	
	public UserMessagesBusiness getUserMessagesBusiness() {
		userMessagesBusiness = (UserMessagesBusiness) 
			BaseBean.initializeServiceBean(userMessagesBusiness, UserMessagesBusiness.class);
		return userMessagesBusiness;
	}
	
	/**
	 * @return Returns the electricalInstallationIDSelection.
	 */
	public String getElectricalInstallationIDSelection() {
		return electricalInstallationIDSelection;
	}

	
	/**
	 * @param electricalInstallationIDSelection The electricalInstallationIDSelection to set.
	 */
	public void setElectricalInstallationIDSelection(String electricalInstallationIDSelection) {
		this.electricalInstallationIDSelection = electricalInstallationIDSelection;
	}

}
