/*
 * $Id: ElectricalInstallationState.java,v 1.5 2007/09/05 16:33:16 thomas Exp $
 * Created on Jun 5, 2007
 *
 * Copyright (C) 2007 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package is.idega.nest.rafverk.business;

import is.idega.nest.rafverk.domain.ElectricalInstallation;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.faces.context.FacesContext;

import com.idega.block.process.business.CaseBusiness;
import com.idega.block.process.business.CaseCodeManager;
import com.idega.block.process.data.Case;
import com.idega.block.process.data.CaseHome;
import com.idega.block.process.data.CaseStatus;
import com.idega.business.IBORuntimeException;
import com.idega.data.IDOLookup;
import com.idega.data.IDOLookupException;
import com.idega.idegaweb.IWApplicationContext;
import com.idega.presentation.IWContext;
import com.idega.util.StringHandler;


/**
 * Handles state of ElectricalInstallation
 * 
 *  Last modified: $Date: 2007/09/05 16:33:16 $ by $Author: thomas $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.5 $
 */
public class ElectricalInstallationState {
	
	// for electrical installation case
	public static final String THJONUSTUBEIDNI_GEYMD = "THJONUSTUBEIDNI_GEYMD";
	public static final String SKYRSLA_GEYMD = "SKYRSLA_GEYMD";
	public static final String MOTTEKIN = "MOTTEKIN";
	public static final String MOTTEKIN_SKYRSLA_GEYMD = "MSKYRSLA_GEYMD";
	public static final String TILKYNNT_LOK = "TILKYNNT_LOK";
	public static final String I_SKODUN = "ISKODUN";
	public static final String SKODUN_LOKID = "SKODUN_LOID";
	public static final String LOKID = "LOKID";
	
	// for changing the electrician
	public static final String SKIPT_UM_RAFVERKTAKA = "SKIPT_UM_RAFVERKTAKA";
	
	// for electrical installation change case
	public static final String BEIDNI_UM_SKIPTI = "BEIDNI_UM_SKIPTI";
	
	// status that are called "open"
	public static final String[] OPEN_STATUS = {
		THJONUSTUBEIDNI_GEYMD, 
		SKYRSLA_GEYMD,
		MOTTEKIN,
		MOTTEKIN_SKYRSLA_GEYMD
	};
	
	public static final List OPEN_STATUS_LIST;   
	
	public static final String[] STADA = {
		"Þjónustubeiðni geymd", THJONUSTUBEIDNI_GEYMD,
		"Skýrsla geymd", SKYRSLA_GEYMD,
		"Móttekin", MOTTEKIN,
		"Móttekin og Skýrlsa geymd", MOTTEKIN_SKYRSLA_GEYMD,
		"Tilkynnt lok", TILKYNNT_LOK,
		"í skoðun", I_SKODUN,
		"Skoðun lokið", SKODUN_LOKID,
		"Lokið", LOKID,
		"Skipt um rafverktaka", SKIPT_UM_RAFVERKTAKA
	};
	
	public static final List STADA_LIST = Arrays.asList(STADA);

	public static List getPossibleStatuses(){
		return STADA_LIST;
	}
	
	public static List getOpenStatuses() {
		return OPEN_STATUS_LIST;
		
	}
	
	static 
	{
		OPEN_STATUS_LIST = new ArrayList(OPEN_STATUS.length);
		for (int i = 0; i < OPEN_STATUS.length; i++) {
			OPEN_STATUS_LIST.add(OPEN_STATUS[i].substring(0,4));
		}
	}
	
	private IWApplicationContext iwac = null;
	
	// store icelandic locale (this app is only for icelandic)
	private Locale currentLocale = null;
	
	// storing open case status string
	private String openCaseStatus = null;
	
	public ElectricalInstallationState(IWApplicationContext iwac) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		currentLocale = IWContext.getIWContext(facesContext).getCurrentLocale();
		this.iwac = iwac;
		openCaseStatus = getCaseHome().getCaseStatusOpen();
	}
	
	public void changeElectrician(ElectricalInstallation electricalInstallation) {
		setStatus(electricalInstallation,SKIPT_UM_RAFVERKTAKA);
	}
	
	public void storeApplication(ElectricalInstallation electricalInstallation) {
		String status = electricalInstallation.getStatus();
		if (SKYRSLA_GEYMD.startsWith(status)) {
			return;
		}
		setStatus(electricalInstallation, THJONUSTUBEIDNI_GEYMD);
	}
	
	public void storeApplicationReport(ElectricalInstallation electricalInstallation) {
		String status = electricalInstallation.getStatus();
		if (MOTTEKIN_SKYRSLA_GEYMD.startsWith(status)) {
			return;
		}
		if (MOTTEKIN.startsWith(status)) {
			setStatus(electricalInstallation, MOTTEKIN_SKYRSLA_GEYMD);
			return;
		}
		setStatus(electricalInstallation, SKYRSLA_GEYMD);
	}
	
	public void sendRequestForChange(Case myCase) {
		setStatus(myCase, BEIDNI_UM_SKIPTI);
	}
	
	public void sendApplication(ElectricalInstallation electricalInstallation) {
		setStatus(electricalInstallation,MOTTEKIN);
	}
	
	public void sendApplicationReport(ElectricalInstallation electricalInstallation) {
		setStatus(electricalInstallation,TILKYNNT_LOK);
	}
	
	public boolean isApplicationHasNewOwner(ElectricalInstallation electricalInstallation) {
		if (electricalInstallation == null) {
			return false;
		}
		String status = electricalInstallation.getStatus();
		return SKIPT_UM_RAFVERKTAKA.startsWith(status);
	}
	
	public boolean isApplicationReportStored(ElectricalInstallation electricalInstallation) {
		if (electricalInstallation == null) {
			return false;
		}
		String status = electricalInstallation.getStatus();
		return !(THJONUSTUBEIDNI_GEYMD.startsWith(status) || MOTTEKIN.startsWith(status));
	}
	
	public boolean isApplicationStorable(ElectricalInstallation electricalInstallation) {
		if (electricalInstallation == null) {
			return true;
		}
		String status = electricalInstallation.getStatus();
		return (StringHandler.isEmpty(status) || 
				openCaseStatus.startsWith(status) ||
				THJONUSTUBEIDNI_GEYMD.startsWith(status) || 
				SKYRSLA_GEYMD.startsWith(status));
	}
	
	public boolean isApplicationSendable(ElectricalInstallation electricalInstallation) {
		return isApplicationStorable(electricalInstallation);  
	}
	
	public boolean isApplicationReportStorable(ElectricalInstallation electricalInstallation) {
		if (electricalInstallation == null) {
			return true;
		}
		String status = electricalInstallation.getStatus();
		return isApplicationStorable(electricalInstallation) || MOTTEKIN.startsWith(status) || MOTTEKIN_SKYRSLA_GEYMD.startsWith(status);
	}
	
	public boolean isApplicationReportSendable(ElectricalInstallation electricalInstallation) {
		return isApplicationReportStorable(electricalInstallation);
	}
	
	private void setStatus(Case electricalInstallationCase, String status) {
		try {
			// get the right case business by the case code (case code is the corresponding entity of the case code key)
			CaseBusiness caseBusiness  = CaseCodeManager.getInstance().getCaseBusiness(electricalInstallationCase.getCaseCode(), iwac);
			// now get the entity case status by the specified status string (e.g. GEYMD returns corresponding entity with primary key GEYM (first four letters))
			CaseStatus caseStatus = caseBusiness.getCaseStatus(status);
			// store the primary key of the case status (e.g. store GEYM but not GEYMD)
			electricalInstallationCase.setStatus(caseStatus.getStatus());
		}
		catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getStatusDescription(ElectricalInstallation electricalInstallation) {
		try {
			// get the right case business by the case code (case code is the corresponding entity of the case code key)
			CaseBusiness caseBusiness  = CaseCodeManager.getInstance().getCaseBusiness(electricalInstallation.getCaseCode(), iwac);
			// now get the entity case status by the specified status string (e.g. GEYMD returns corresponding entity with primary key GEYM (first four letters))
			CaseStatus caseStatus = electricalInstallation.getCaseStatus();
			String status = caseBusiness.getLocalizedCaseStatusDescription(electricalInstallation, caseStatus, currentLocale);
			return status;
		}
		catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new IBORuntimeException();
		}
	}
	
	private CaseHome getCaseHome() {
		try {
			return (CaseHome) IDOLookup.getHome(Case.class);
		}
		catch (IDOLookupException ile) {
			throw new IBORuntimeException(ile);
		}
	}
	
}
