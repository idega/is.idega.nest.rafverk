/*
 * $Id: URLDisplayer.java,v 1.1 2007/06/08 17:08:20 thomas Exp $
 * Created on May 30, 2007
 *
 * Copyright (C) 2007 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package is.idega.nest.rafverk.block;

import is.idega.nest.rafverk.business.ElectricalInstallationBusiness;
import is.idega.nest.rafverk.business.ElectricalInstallationRendererBusiness;
import is.idega.nest.rafverk.domain.ElectricalInstallation;
import is.idega.nest.rafverk.domain.Rafverktaka;

import java.io.IOException;
import java.rmi.RemoteException;

import javax.ejb.FinderException;

import com.idega.business.IBOLookup;
import com.idega.business.IBOService;
import com.idega.presentation.IWContext;
import com.idega.presentation.ui.Window;


/**
 * 
 *  Last modified: $Date: 2007/06/08 17:08:20 $ by $Author: thomas $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.1 $
 */
public class URLDisplayer extends Window {
	
	public URLDisplayer() {
	    setWidth(500 );
	    setHeight(800 );
		setResizable(true);
	}


	public void main(IWContext iwc) {
		String casePK = null;
		this.setAllMargins(0);
		// get title
		if (iwc.isParameterSet(CasesList.PARAMETER_CASE_PK)) {
			casePK = iwc.getParameter(CasesList.PARAMETER_CASE_PK);
		}
		// set title
		//setTitle(title);
		// get info text
		try {
			ElectricalInstallation electricalInstallation = getElectricalInstallationBusiness(iwc).getElectricalInstallationByPrimaryKey(casePK);
			Rafverktaka rafverktaka = Rafverktaka.getInstanceFromElectricalInstallation(electricalInstallation);
			String url = getElectricalInstallationRendererBusiness(iwc).getPDFApplication(rafverktaka);
			iwc.sendRedirect(url);
		}
		catch (FinderException e) {
			e.printStackTrace();
		}
		catch (RemoteException e) {
			e.printStackTrace();
		}
		catch (IOException e) {

			e.printStackTrace();
		}
	}
	
	private ElectricalInstallationBusiness getElectricalInstallationBusiness(IWContext iwc) {
		return (ElectricalInstallationBusiness) getServiceBean(ElectricalInstallationBusiness.class, iwc);
	}
	
	private ElectricalInstallationRendererBusiness getElectricalInstallationRendererBusiness(IWContext iwc) {
		return (ElectricalInstallationRendererBusiness) getServiceBean(ElectricalInstallationRendererBusiness.class,iwc);
	}
	
	private IBOService getServiceBean(Class serviceClass, IWContext iwc ) {
		IBOService service = null;
		try {
			service = IBOLookup.getServiceInstance(iwc.getApplicationContext(), serviceClass);
		}
		catch (RemoteException rme) {
			throw new RuntimeException(rme.getMessage());
		}
		return service;
	}	

}

	
		
		