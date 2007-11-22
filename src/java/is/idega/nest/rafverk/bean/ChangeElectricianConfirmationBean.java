/*
 * $Id: ChangeElectricianConfirmationBean.java,v 1.6 2007/11/22 16:23:44 thomas Exp $
 * Created on Aug 20, 2007
 *
 * Copyright (C) 2007 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package is.idega.nest.rafverk.bean;

import is.idega.nest.rafverk.business.ElectricalInstallationBusiness;
import is.idega.nest.rafverk.business.ElectricalInstallationCaseBusiness;
import is.idega.nest.rafverk.business.ElectricalInstallationState;
import is.idega.nest.rafverk.business.UserMessagesBusiness;
import is.idega.nest.rafverk.domain.ElectricalInstallation;
import is.idega.nest.rafverk.domain.Rafverktaka;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import javax.ejb.FinderException;
import javax.faces.context.FacesContext;

import com.idega.block.process.data.Case;
import com.idega.business.IBORuntimeException;
import com.idega.user.data.User;
import com.idega.util.datastructures.list.KeyValuePair;


/**
 * 
 *  Last modified: $Date: 2007/11/22 16:23:44 $ by $Author: thomas $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.6 $
 */
public class ChangeElectricianConfirmationBean {
	
	private String sel_case_nr = null;
	
	private String messageStoring = null;
	
	private Rafverktaka rafverktaka = null;
	
	private Case changeElectricanCase = null;
	
	private ElectricalInstallation oldElectricalInstallation = null;
	
	private User requestSender = null;
	
	private ElectricalInstallationBusiness electricalInstallationBusiness;
	
	private ElectricalInstallationCaseBusiness electricalInstallationCaseBusiness;
	
	private UserMessagesBusiness userMessagesBusiness;
	
	public void setSel_case_nr(String sel_case_nr) {
		// coming to the first page of wizard from overview (that is block UserCases) 
		// (look at faces-config.xml, sel_case_nr is defined as initial parameter, this bean has scope "request")
		if (sel_case_nr != null) {
			this.sel_case_nr = sel_case_nr;
		}
		else {
		// coming from first page of wizard going to the second page (selectionCaseNumber is a hidden input)
			Map requestMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
			this.sel_case_nr = (String) requestMap.get("form1:selectionCaseNumber");
		}
		// coming from second page of wizard going to the third page (selection is not set at all, not necessary)
		
		if (this.sel_case_nr != null) {
			initialize();
		}
	}
	
	public void setSelectionCaseNumber() {
		// dummy method otherwise jsf is complaining, hidden input is used during initialization
	}
	
	
	public String getSel_case_nr() {
		return sel_case_nr;
	}
	
	private void initialize() {
		ElectricalInstallationCaseBusiness electricalInstallationCaseBusinessLocal = getElectricalInstallationCaseBusiness();
		try {
			changeElectricanCase = electricalInstallationCaseBusinessLocal.getCase(sel_case_nr);
			oldElectricalInstallation = 
				(ElectricalInstallation) electricalInstallationCaseBusinessLocal.getParentCaseAsElectricalInstallation(changeElectricanCase);
			// security check - do not go further if the status is wrong
			String status = oldElectricalInstallation.getStatus();
			List openStatusList = ElectricalInstallationState.getOpenStatuses();
			if (! openStatusList.contains(status)) {
				// this is an attempt to hack the system
				System.err.print("[ChangeElectricianConfirmation] ERROR: Attempt to copy an electrical installation with wrong status");
				return;
			}
			requestSender = changeElectricanCase.getCreator();
			String id = oldElectricalInstallation.getPrimaryKey().toString();
			
			// use the one that is stored in managed bean
			rafverktaka = BaseBean.getRafverktokuListi().getRafverktaka(id);
			oldElectricalInstallation = rafverktaka.getElectricalInstallation();
		}
		catch (RemoteException e) {
			throw new RuntimeException(e.getMessage());
		}
		catch (FinderException e) {
			e.printStackTrace();
		}
	}
	
	public String getRequestSender() {
		return requestSender.getName();
	}
	
	public String getNafnOrkukaupanda() {
		return rafverktaka.getOrkukaupandi().getNafn();
	}

	public String getKennitalaOrkukaupanda() {
		return rafverktaka.getOrkukaupandi().getKennitala();
	}

	public String getVeitustadurDisplay() {
		return rafverktaka.getVeitustadurDisplay();
	}
	
	public String getStadaDisplay() {
		return rafverktaka.getStadaDisplay();
	}
	
	public String getMessageStoring() {
		return messageStoring;
	}
	
	public String confirmChangeOfElectrician() {
		ElectricalInstallationBusiness electricalInstallationBusinessLocal = getElectricalInstallationBusiness();
		try {
			RafverktokuListi rafverktokuListi = BaseBean.getRafverktokuListi();
			KeyValuePair newElectricalInstallationResult = electricalInstallationBusinessLocal.changeElectrician(oldElectricalInstallation, requestSender, rafverktokuListi);
			ElectricalInstallation newElectricalInstallation = (ElectricalInstallation) newElectricalInstallationResult.getKey();
			if (newElectricalInstallation == null) {
				// new electrical installation could not be created
				messageStoring = "Error occurred";
				return "next";
			}
			messageStoring = getUserMessagesBusiness().getMessageAfterAcceptingRequestForChangeOfElectrician(newElectricalInstallation);
			String result = (String) newElectricalInstallationResult.getValue();
			if (result != null) {
				// should not happen, result indicates a small but not serious problem
				messageStoring = result + messageStoring;
			}
			// flag change-electrician case as deleted
			String deleted = getElectricalInstallationCaseBusiness().getCaseStatusDeletedString();
			changeElectricanCase.setStatus(deleted);
			changeElectricanCase.store();
			
			// mark changes for the user that sent the request
			electricalInstallationBusinessLocal.addChangeForUser(requestSender);

			// reset everything here
			sel_case_nr = null;
			rafverktaka = null;
			changeElectricanCase = null;
			oldElectricalInstallation = null;
			requestSender = null;
			electricalInstallationBusiness = null;
			electricalInstallationCaseBusiness = null;
			return "next";
		}
		catch (RemoteException e) {
			throw new IBORuntimeException(e.getMessage());
		}
	}
	
	private ElectricalInstallationBusiness getElectricalInstallationBusiness() {
		electricalInstallationBusiness = (ElectricalInstallationBusiness) 
			BaseBean.initializeServiceBean(electricalInstallationBusiness, ElectricalInstallationBusiness.class);
		return electricalInstallationBusiness;
	}
	
	public ElectricalInstallationCaseBusiness getElectricalInstallationCaseBusiness() {
		electricalInstallationCaseBusiness = (ElectricalInstallationCaseBusiness) 
			BaseBean.initializeServiceBean(electricalInstallationCaseBusiness, ElectricalInstallationCaseBusiness.class);
		return electricalInstallationCaseBusiness;
	}

	public UserMessagesBusiness getUserMessagesBusiness() {
		userMessagesBusiness = 
			(UserMessagesBusiness) BaseBean.initializeServiceBean(userMessagesBusiness, UserMessagesBusiness.class);
		return userMessagesBusiness;
	}

}
