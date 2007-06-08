/*
 * $Id: ElectricalInstallationState.java,v 1.1 2007/06/08 17:06:16 thomas Exp $
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
import java.util.Arrays;
import java.util.List;

import com.idega.block.process.business.CaseBusiness;
import com.idega.block.process.business.CaseCodeManager;
import com.idega.block.process.data.CaseStatus;
import com.idega.business.IBOLookup;
import com.idega.idegaweb.IWApplicationContext;


/**
 * Handles state of ElectricalInstallation
 * 
 *  Last modified: $Date: 2007/06/08 17:06:16 $ by $Author: thomas $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.1 $
 */
public class ElectricalInstallationState {
	
	public static final String GEYMD = "GEYMD";
	public static final String MOTTEKIN = "MOTTEKIN";
	public static final String TILKYNNT_LOK = "TILKYNNT_LOK";
	public static final String I_SKODUN = "ISKODUN";
	public static final String SKODUN_LOKID = "SKODUN_LOID";
	public static final String LOKID = "LOKID";
	
	public static final String[] STADA = {
		"Geymd", GEYMD,
		"Móttekin", MOTTEKIN,
		"Tilkynnt lok", TILKYNNT_LOK,
		"í skoðun", I_SKODUN,
		"Skoðun lokið", SKODUN_LOKID,
		"Lokið", LOKID
	};
	
	public static final List STADA_LIST = Arrays.asList(STADA);

	public static List getPossibleStatuses(){
		return STADA_LIST;
	}
	
	private IWApplicationContext iwac = null;
	
	public ElectricalInstallationState(IWApplicationContext iwac) {
		this.iwac = iwac;
	}
	
	public void initialize(ElectricalInstallation electricalInstallation) {
		setStatus(electricalInstallation, GEYMD);
	}
	
	public void sendApplication(ElectricalInstallation electricalInstallation) {
		setStatus(electricalInstallation,MOTTEKIN);
	}
	
	public void sendApplicationReport(ElectricalInstallation electricalInstallation) {
		setStatus(electricalInstallation,TILKYNNT_LOK);
	}
	
	private void setStatus(ElectricalInstallation electricalInstallation, String status) {
		try {
			// get the right case business by the case code (case code is the corresponding entity of the case code key)
			CaseBusiness caseBusiness  = CaseCodeManager.getInstance().getCaseBusiness(electricalInstallation.getCaseCode(), iwac);
			// now get the entity case status by the specified status string (e.g. GEYMD returns corresponding entity with primary key GEYM (first four letters))
			CaseStatus caseStatus = caseBusiness.getCaseStatus(status);
			// store the primary key of the case status (e.g. store GEYM but not GEYMD)
			electricalInstallation.setStatus(caseStatus.getStatus());
		}
		catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
