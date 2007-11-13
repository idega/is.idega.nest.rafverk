/*
 * $Id: ElectricalInstallationValidationBusinessBean.java,v 1.2 2007/11/13 16:25:19 thomas Exp $
 * Created on Aug 8, 2007
 *
 * Copyright (C) 2007 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package is.idega.nest.rafverk.business;

import is.idega.nest.rafverk.bean.validation.ElectricalInstallationValidator;
import is.idega.nest.rafverk.bean.validation.ValidationRules;
import is.idega.nest.rafverk.bean.validation.ValidationRulesApplication;
import is.idega.nest.rafverk.bean.validation.ValidationRulesReport;
import is.idega.nest.rafverk.domain.SimpleElectricalInstallation;

import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.idega.business.IBOLookup;
import com.idega.business.IBOService;
import com.idega.business.IBOServiceBean;
import com.idega.fop.data.Property;


/**
 * 
 *  Last modified: $Date: 2007/11/13 16:25:19 $ by $Author: thomas $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.2 $
 */
public class ElectricalInstallationValidationBusinessBean extends IBOServiceBean implements ElectricalInstallationValidationBusiness{
	
	private ElectricalInstallationRendererBusiness electricalInstallationRendererBusiness = null;
	
	private ValidationRules validationRulesApplication = null; 
	
	private ValidationRules validationRulesReport = null;
	
	public boolean isApplicationValid(Map validationResults) {
		Set keySet = getValidationRulesApplication().getKeySet();
		Set resultKeySet = validationResults.keySet();
		return ! containsKeyOf(resultKeySet, keySet);
	}
	
	public boolean isReportValid(Map validationResults) {
		Set keySet = getValidationRulesReport().getKeySet();
		Set resultKeySet = validationResults.keySet();
		return ! containsKeyOf(resultKeySet, keySet);
	}
	
	private boolean containsKeyOf(Set firstKeySet, Set secondKeySet) {
		Iterator iterator = secondKeySet.iterator();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			if (firstKeySet.contains(key)) {
				return true;
			}
		}
		return false;
	}
	
	
	public Map validateApplication(SimpleElectricalInstallation electricalInstallation) throws RemoteException {
		Property property = getElectricalInstallationRendererBusiness().getApplicationProperty(electricalInstallation);
		ElectricalInstallationValidator handler = new ElectricalInstallationValidator(getValidationRulesApplication());
		property.accept(handler);
		return handler.getResults();
	}
	
	public Map validateReport(SimpleElectricalInstallation electricalInstallation) throws RemoteException {
		Property property = getElectricalInstallationRendererBusiness().getReportProperty(electricalInstallation);
		ElectricalInstallationValidator handler = new ElectricalInstallationValidator(getValidationRulesReport());
		property.accept(handler);
		return handler.getResults();
	}
	
	private ElectricalInstallationRendererBusiness getElectricalInstallationRendererBusiness() {
		if (electricalInstallationRendererBusiness == null) {
			electricalInstallationRendererBusiness = (ElectricalInstallationRendererBusiness) getServiceBean(ElectricalInstallationRendererBusiness.class);
		}
		return electricalInstallationRendererBusiness;
	}
	
	private ValidationRules getValidationRulesApplication() {
		if (validationRulesApplication == null) {
			validationRulesApplication = new ValidationRulesApplication();
		}
		return validationRulesApplication;
	}
	
	private ValidationRules getValidationRulesReport() {
		if (validationRulesReport == null) {
			validationRulesReport = new ValidationRulesReport();
		}
		return validationRulesReport;
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
