/*
 * $Id: ChangeElectricianBean.java,v 1.1 2007/08/15 17:12:51 thomas Exp $
 * Created on Aug 13, 2007
 *
 * Copyright (C) 2007 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package is.idega.nest.rafverk.bean;

import is.idega.nest.rafverk.business.ElectricalInstallationBusiness;
import is.idega.nest.rafverk.domain.ElectricalInstallation;
import is.idega.nest.rafverk.domain.Fasteign;
import is.idega.nest.rafverk.domain.Orkukaupandi;
import is.idega.nest.rafverk.domain.Rafverktaka;
import is.idega.nest.rafverk.domain.Rafverktaki;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.ejb.FinderException;
import javax.faces.context.FacesContext;

import com.idega.business.IBOLookup;
import com.idega.business.IBOService;
import com.idega.idegaweb.IWApplicationContext;
import com.idega.presentation.IWContext;
import com.idega.util.StringHandler;


/**
 * 
 *  Last modified: $Date: 2007/08/15 17:12:51 $ by $Author: thomas $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.1 $
 */
public class ChangeElectricianBean extends RealEstateBean {
	
	private Map electricalInstallationList = null;
	
	private String electricalInstallationIDSelection = null;
	
	private ElectricalInstallationBusiness electricalInstallationBusiness = null;
	
	public ChangeElectricianBean() {
    	initialize();
    }
	
	public void initialize() {
		super.initializeForm();
	}
	
	// might be overwritten by subclasses
	void changedRealEstate(Fasteign fasteign) {
		String realEstateNumber = fasteign.getFastaNumer();
		initializeElectricalInstallationList(realEstateNumber);
	}
	
	private void initializeElectricalInstallationList(String realEstateNumber) {
		electricalInstallationList = new TreeMap(Collections.reverseOrder());
		Collection verktokur = null;
		try {
			verktokur = getElectricalInstallationBusiness().getElectricalInstallationByRealEstateNumber(realEstateNumber);
		}
		catch (RemoteException e) {
			throw new RuntimeException(e.getMessage());
		}
		catch (FinderException e) {
			verktokur = null;
		}
		if (verktokur != null) {
			Iterator iterator = verktokur.iterator();
			while (iterator.hasNext()) {
				ElectricalInstallation electricalInstallation = (ElectricalInstallation) iterator.next();
				Rafverktaka verktaka = new Rafverktaka(electricalInstallation, getElectricalInstallationBusiness());
				String id = verktaka.getId();
				String label = getElectricalInstallationLabel(verktaka);
				electricalInstallationList.put(id, label);	
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
		
		buffer.append(", Verknúmer: ");
		add(buffer, rafverktaka.getExternalProjectID());
		
		buffer.append(", Orkukaupandi: ");
		Orkukaupandi orkukaupandi = rafverktaka.getOrkukaupandi();
		if (orkukaupandi != null) {
			add(buffer, orkukaupandi.getNafn());
		}
		
		buffer.append(", Stada: ");
		add(buffer, rafverktaka.getStadaDisplay());
		
		return buffer.toString();
	}
	
	private void add(StringBuffer buffer, String value) {
		buffer.append(StringHandler.getStringOrDash(value));
	}
	
	public ElectricalInstallationBusiness getElectricalInstallationBusiness() {
		if (electricalInstallationBusiness == null) {
			electricalInstallationBusiness = (ElectricalInstallationBusiness) getSeviceBean(ElectricalInstallationBusiness.class);
		}
		return electricalInstallationBusiness;
	}
	
	private IBOService getSeviceBean(Class serviceBeanClass) {
		IBOService myServiceBean = null;
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			IWContext iwContext = IWContext.getIWContext(context);
			IWApplicationContext iwac = iwContext.getApplicationContext();
			myServiceBean = IBOLookup.getServiceInstance(iwac, serviceBeanClass);
		}
		catch (RemoteException rme) {
			throw new RuntimeException(rme.getMessage());
		}
		return myServiceBean;
	}

	public Map getElectricalInstallationList() {
		return electricalInstallationList;
	}
	
	public List getElectricalInstallationListiSelects() {
		return getListiSelects(electricalInstallationList);
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
