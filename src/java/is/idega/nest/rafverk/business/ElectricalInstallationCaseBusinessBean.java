/*
 * $Id: ElectricalInstallationCaseBusinessBean.java,v 1.6 2007/11/16 16:30:50 thomas Exp $
 * Created on Jun 6, 2007
 *
 * Copyright (C) 2007 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package is.idega.nest.rafverk.business;

import is.fmr.landskra.Fasteign;
import is.idega.nest.rafverk.bean.BaseBean;
import is.idega.nest.rafverk.bean.constants.CaseConstants;
import is.idega.nest.rafverk.domain.ElectricalInstallation;

import java.rmi.RemoteException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

import javax.ejb.CreateException;
import javax.ejb.FinderException;

import com.idega.block.process.business.CaseBusinessBean;
import com.idega.block.process.data.Case;
import com.idega.block.process.data.CaseCode;
import com.idega.core.location.data.RealEstate;
import com.idega.idegaweb.IWResourceBundle;
import com.idega.user.data.User;
import com.idega.util.StringHandler;


/**
 * 
 *  Last modified: $Date: 2007/11/16 16:30:50 $ by $Author: thomas $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.6 $
 */
public class ElectricalInstallationCaseBusinessBean extends CaseBusinessBean implements ElectricalInstallationCaseBusiness{
	
	private ElectricalInstallationBusiness electricalInstallationBusiness = null;
	
	private ElectricalInstallationMessageBusiness electricalInstallationMessageBusiness = null;
	
	/**
	 * Can be overrided in subclasses
	 */
	protected String getBundleIdentifier() {
		return CaseConstants.BUNDLE_IDENTIFIER;
	}
	
	public CaseCode getCaseCodeForElectricalInstallationChange() {
		return getCaseCodeAndInstallIfNotExists(CaseConstants.CASE_CODE_KEY_ELCHN);
	}
	
	public ElectricalInstallation getParentCaseAsElectricalInstallation(Case childCase) throws FinderException {
		Object primaryKey = childCase.getParentCase().getPrimaryKey();
		try {
			return getElectricalInstallationBusiness().getElectricalInstallationByPrimaryKey(primaryKey);
		}
		catch (RemoteException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public List getChildrenOfCaseAsElectricalInstallation(Case parentCase)  {
		if (parentCase == null) {
			return null;
		}
		Collection coll = parentCase.getChildren();
		if (coll == null) {
			return null;
		}
		List result = new ArrayList(coll.size());
		Iterator iterator = coll.iterator();
		while (iterator.hasNext()) {
			Case item = (Case) iterator.next();
			Object primaryKey = item.getPrimaryKey();
			try {
				ElectricalInstallation electricalInstallation = getElectricalInstallationBusiness().getElectricalInstallationByPrimaryKey(primaryKey);
				result.add(electricalInstallation);
			}
			catch (RemoteException e) {
				throw new RuntimeException(e.getMessage());
			}
			catch (FinderException e) {
				getLogger().log(Level.SEVERE, e.getMessage());
			}
		}
		return result;
	}
	
	public String sendRequestForChangingElectrician(ElectricalInstallation electricalInstallation) {
		// #0
		User sender = BaseBean.getCurrentUser();
		IWResourceBundle resourceBundle = BaseBean.getResourceBundle();
			
		// #1
		String externalProjectID = electricalInstallation.getExternalProjectID();
		externalProjectID =  StringHandler.replaceIfEmpty(externalProjectID, "");
		
		// #2
		RealEstate realEstate = electricalInstallation.getRealEstate();
		String workingPlaceDisplay = null;
		if (realEstate != null) {
			Fasteign fasteign = new Fasteign(realEstate);
			workingPlaceDisplay = fasteign.getDescription();
			
		}
		workingPlaceDisplay = StringHandler.replaceIfEmpty(workingPlaceDisplay, "");
		
		// #3
		String energyConsumerName = electricalInstallation.getEnergyConsumerName();
		energyConsumerName = StringHandler.replaceIfEmpty(energyConsumerName, "");
		
		String[] arg = { sender.getName(), electricalInstallation.getExternalProjectID(), workingPlaceDisplay, energyConsumerName };
		String subject = "Request for taking over task";
		String body = "Electrician {0} would like to take over the following job: \r"+
		"Number: {1} \r" +
		"Working place: {2} \r" +
		"Energy consumer: {3}  \r" +
		"Please confirm the request as soon as possible";
		
		String localizedSubject = resourceBundle.getLocalizedString("rafverk_request_for_taking_over_task_subject", subject);
		String localizedBody = resourceBundle.getLocalizedString("rafverk_request_for_taking_over_task_body", body);
		
		String formatedLocalizedBody = MessageFormat.format(localizedBody, arg);

		String result = null;
		try {
			CaseCode caseCode = getCaseCodeForElectricalInstallationChange();
			Case newCase = createSubCase(electricalInstallation, caseCode);
			getElectricalInstallationBusiness().getElectricalInstallationState().sendRequestForChange(newCase);
			newCase.setCreator(sender);
			newCase.setParentCase(electricalInstallation);
			newCase.store();
			// note: if a create exception is thrown a user message is not created
			User receiver = electricalInstallation.getElectrician();
			result = getElectricalInstallationMessageBusiness().createUserMessage(electricalInstallation, sender, receiver, localizedSubject, formatedLocalizedBody);
			result = (result == null) ? "Successfully sent" : "Successfully sent request but problems occurred sending email";
		}
		catch (RemoteException e) {
			throw new RuntimeException(e.getMessage());
		}
		catch (CreateException e) {
			result = "An error occurred: Request was not sent.";
		}
		return result;
	}
		
	private ElectricalInstallationBusiness getElectricalInstallationBusiness() {
		electricalInstallationBusiness = (ElectricalInstallationBusiness) 
			BaseBean.initializeServiceBean(electricalInstallationBusiness, ElectricalInstallationBusiness.class);
		return electricalInstallationBusiness;
	}
	
	private ElectricalInstallationMessageBusiness getElectricalInstallationMessageBusiness() {
		electricalInstallationMessageBusiness = (ElectricalInstallationMessageBusiness)  
			BaseBean.initializeServiceBean(electricalInstallationMessageBusiness, ElectricalInstallationMessageBusinessBean.class);
		return electricalInstallationMessageBusiness;
	}
	
}
