/*
 * $Id: EnergyCompanyCasesList.java,v 1.2 2008/05/26 16:45:28 valdas Exp $
 * Created on May 29, 2007
 *
 * Copyright (C) 2007 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package is.idega.nest.rafverk.block;

import is.idega.nest.rafverk.business.ElectricalInstallationBusiness;
import is.idega.nest.rafverk.business.ElectricalInstallationRendererBusiness;

import java.rmi.RemoteException;
import java.util.Collection;

import com.idega.business.IBOLookup;
import com.idega.business.IBOService;
import com.idega.user.data.User;


/**
 * 
 *  Last modified: $Date: 2008/05/26 16:45:28 $ by $Author: valdas $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.2 $
 */
public class EnergyCompanyCasesList extends MyCases {
	
	private ElectricalInstallationBusiness electricalInstallationBusiness = null;
	private ElectricalInstallationRendererBusiness electricalInstallationRendererBusiness = null;

	protected Collection getCases(User user) {
		Collection cases;
		try {
			cases = getElectricalInstallationBusiness().getElectricalInstallationByEnergyCompanyUser(user);
		}
		catch (RemoteException e) {
			throw new RuntimeException(e.getMessage());
		}
		return cases;
	}
	
	public ElectricalInstallationBusiness getElectricalInstallationBusiness() {
		if (electricalInstallationBusiness == null) {
			electricalInstallationBusiness = (ElectricalInstallationBusiness) getServiceBean(ElectricalInstallationBusiness.class);
		}
		return electricalInstallationBusiness;
	}
	
	public ElectricalInstallationRendererBusiness getElectricalInstallationRendererBusiness() {
		if (electricalInstallationRendererBusiness == null) {
			electricalInstallationRendererBusiness =  (ElectricalInstallationRendererBusiness) getServiceBean(ElectricalInstallationRendererBusiness.class);
		}
		return electricalInstallationRendererBusiness;
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
	
}
