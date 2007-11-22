/*
 * $Id: UserMessagesBusinessBean.java,v 1.1 2007/11/22 16:23:44 thomas Exp $
 * Created on Nov 19, 2007
 *
 * Copyright (C) 2007 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package is.idega.nest.rafverk.business;

import is.fmr.landskra.Fasteign;
import is.idega.nest.rafverk.bean.BaseBean;
import is.idega.nest.rafverk.domain.ElectricalInstallation;

import java.rmi.RemoteException;
import java.text.MessageFormat;

import com.idega.business.IBOLookup;
import com.idega.business.IBORuntimeException;
import com.idega.business.IBOService;
import com.idega.business.IBOServiceBean;
import com.idega.core.location.data.RealEstate;
import com.idega.idegaweb.IWResourceBundle;
import com.idega.user.data.User;
import com.idega.util.StringHandler;


/**
 * 
 *  Last modified: $Date: 2007/11/22 16:23:44 $ by $Author: thomas $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.1 $
 */
public class UserMessagesBusinessBean extends IBOServiceBean implements UserMessagesBusiness{
	
	private ElectricalInstallationMessageBusiness electricalInstallationMessageBusiness;

	/**
	 * used on "rafverktakaskiptiskref3" jsf page
	 */
	public String getMessageAfterAcceptingRequestForChangeOfElectrician(ElectricalInstallation electricalInstallation) {
		IWResourceBundle resourceBundle = BaseBean.getResourceBundle();
		String[] arguments = getArgumentsFrom(electricalInstallation);
		String message = "Request for change has been accepted. <br>" +
		"Electrician {0} has taken over the job. <br>" + 
		"Working place: {2} <br>" +
		"Energy consumer: {3} ";
		return resourceBundle.getLocalizedAndFormattedString("rafverk_message_after_accepting_request_for_change", message, arguments);
	}	
	
	/**
	 * used on "rafverktakaskiptiskref3" jsf page
	 */
	public String getMessageAfterSendingRequestForChangeOfElectrician(ElectricalInstallation electricalInstallation) {
		IWResourceBundle resourceBundle = BaseBean.getResourceBundle();
		String[] arguments = getArgumentsFrom(electricalInstallation);
		String message = "Request for change has been sent to {0} <br>" +
		"Working place: {2} <br>" +
		"Energy consumer: {3} ";
		return resourceBundle.getLocalizedAndFormattedString("rafverk_message_after_sending_request_for_change", message, arguments);
	}	
	
	/**
	 * used on "sent" jsf page
	 */
	public String getMessageAfterSendingInApplication(ElectricalInstallation electricalInstallation) {
		IWResourceBundle resourceBundle = BaseBean.getResourceBundle();
		String[] arguments = getArgumentsFrom(electricalInstallation);
		String message = "Application with external project id {1} has been sent in. <br>" +
		"Working place: {2} <br>" +
		"Energy consumer: {3} ";
		return resourceBundle.getLocalizedAndFormattedString("rafverk_message_after_sending_in_application", message, arguments);
	}	
	
	/**
	 * used on "sent" jsf page
	 */
	public String getMessageAfterSendingInApplicationReport(ElectricalInstallation electricalInstallation) {
		IWResourceBundle resourceBundle = BaseBean.getResourceBundle();
		String[] arguments = getArgumentsFrom(electricalInstallation);
		String message = "Report with external project id {1} has been sent in. <br>" +
		"Working place: {2} <br>" +
		"Energy consumer: {3} ";
		return resourceBundle.getLocalizedAndFormattedString("rafverk_message_after_sending_in_report", message, arguments);
	}
	
	/**
	 * used on "geymt" jsf page
	 */
	public String getMessageAfterCheckingOutWorkingPlace(ElectricalInstallation electricalInstallation) {
		IWResourceBundle resourceBundle = BaseBean.getResourceBundle();
		String[] arguments = getArgumentsFrom(electricalInstallation);
		String message = "Working place {2} has been checked out. <br>" +
				"External project number: {1} <br>" +
				"Energy consumer: {3} ";
		return resourceBundle.getLocalizedAndFormattedString("rafverk_message_after_checking_out_working_place", message, arguments);
	}
	
	/**
	 * used on "geymt" jsf page
	 */
	public String getMessageAfterStoringApplication(ElectricalInstallation electricalInstallation) {
		IWResourceBundle resourceBundle = BaseBean.getResourceBundle();
		String[] arguments = getArgumentsFrom(electricalInstallation);
		String message = "Application with external project id {1} has been stored. <br>" +
				"Working place: {2} <br>" +
				"Energy consumer: {3} ";
		return resourceBundle.getLocalizedAndFormattedString("rafverk_message_after_storing_application", message, arguments);
	}
	
	/**
	 * used on "geymt" jsf page
	 */
	public String getMessageAfterStoringApplicationReport(ElectricalInstallation electricalInstallation) {
		IWResourceBundle resourceBundle = BaseBean.getResourceBundle();
		String[] arguments = getArgumentsFrom(electricalInstallation);
		String message = "Report with external project id {1} has been stored. <br>" +
				"Working place: {2} <br>" +
				"Energy consumer: {3} ";
		return resourceBundle.getLocalizedAndFormattedString("rafverk_message_after_storing_report", message, arguments);
	}
	
	
	
	public String sendUserMessageAfterChangingElectrician(ElectricalInstallation oldElectricalInstallation, ElectricalInstallation newElectricalInstallation) {
		IWResourceBundle resourceBundle = BaseBean.getResourceBundle();
		String result1 = sendAcceptMessageToNewOwner(oldElectricalInstallation, newElectricalInstallation, resourceBundle);
		String result2 = sendHandshakingMessageToOldOwner(oldElectricalInstallation, newElectricalInstallation, resourceBundle);
		if (result1 == null && result2 == null) {
			// normal case
			return null;
		}
		// problems appeared
		if (result1 == null) {
			return result2;
		}
		if (result2 == null) {
			return result1;
		}
		return result1 + result2;
	}
		
	private String sendAcceptMessageToNewOwner(ElectricalInstallation oldElectricalInstallation, ElectricalInstallation newElectricalInstallation, IWResourceBundle resourceBundle ) {
		// send accept message to new owner
		String[] oldArguments = getArgumentsFrom(oldElectricalInstallation);	
		String subject = "Request for taking over task was accepted";
		String body = "Electrician {0} has accepted your request for taking over the following job: \r"+
		"Working place: {2} \r" +
		"Energy consumer: {3}  \r";
		String localizedSubject = resourceBundle.getLocalizedString("rafverk_request_for_taking_over_task_was_accepted_subject", subject);
		String localizedBody = resourceBundle.getLocalizedString("rafverk_request_for_taking_over_task_was_accepted_body", body);
		String formatedLocalizedBody = MessageFormat.format(localizedBody, oldArguments);
		User sender = oldElectricalInstallation.getElectrician();
		User receiver = newElectricalInstallation.getElectrician();
		try {
			return getElectricalInstallationMessageBusiness().createUserMessage(newElectricalInstallation, sender, receiver, localizedSubject, formatedLocalizedBody);
		}
		catch (RemoteException e) {
			// TODO Auto-generated catch block
			throw new IBORuntimeException(e);
		}
	}
		
	private String sendHandshakingMessageToOldOwner(ElectricalInstallation oldElectricalInstallation, ElectricalInstallation newElectricalInstallation, IWResourceBundle resourceBundle ) {
		// // send handshaking to yourself
		String[] arguments = getArgumentsFrom(newElectricalInstallation);
		
		// get verknumer (was not taken over)
		String verknumer = oldElectricalInstallation.getExternalProjectID();
		verknumer = StringHandler.replaceIfEmpty(verknumer, "");
		arguments[1] = verknumer;
		
		String subject = "Request for taking over task accepted";
		String body = "Electrician {0} has taken over the following job: \r"+
		"Project number: {1} \r" +
		"Working place: {2} \r" +
		"Energy consumer: {3}  \r";
		String localizedSubject = resourceBundle.getLocalizedString("rafverk_request_for_taking_over_task_accepted_subject", subject);
		String localizedBody = resourceBundle.getLocalizedString("rafverk_request_for_taking_over_task_accepted_body", body);
		String formatedLocalizedBody = MessageFormat.format(localizedBody, arguments);
		User sender = oldElectricalInstallation.getElectrician();
		User receiver = oldElectricalInstallation.getElectrician();
		try {
			return getElectricalInstallationMessageBusiness().createUserMessage(newElectricalInstallation, sender, receiver, localizedSubject, formatedLocalizedBody);
		}
		catch (RemoteException e) {
			// TODO Auto-generated catch block
			throw new IBORuntimeException(e);
		}
	}
	
	/**
	 * Returns an array that contains
	 * #0 electricians name
	 * #1 external project number (verknumer) 
	 * #2 working place description
	 * #3 energy consumers name 
	 */
	private String[] getArgumentsFrom(ElectricalInstallation electricalInstallation) {
		// #0
		User electrician = electricalInstallation.getElectrician();
			
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
		
		String[] arg = { electrician.getName(), electricalInstallation.getExternalProjectID(), workingPlaceDisplay, energyConsumerName };
		return arg;
	}
	
	public  String sendUserMessageRequestingChangeOfElectrician(ElectricalInstallation electricalInstallation, User sender) {
		// #0
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
		
		User receiver = electricalInstallation.getElectrician();
		try {
			return getElectricalInstallationMessageBusiness().createUserMessage(electricalInstallation, sender, receiver, localizedSubject, formatedLocalizedBody);
		}
		catch (RemoteException e) {
			throw new IBORuntimeException(e);
		}
	}
		
	public String sendDataToEnergyCompany(ElectricalInstallation electricalInstallation, String uriToResource) {
		User electrician = electricalInstallation.getElectrician();
		String name = (electrician == null) ? null : electrician.getName();
		String subject = "Þjónustubeiðni";
		subject = (name == null) ? subject : subject + ": " + name;
		String text = null;
		RealEstate realEstate = electricalInstallation.getRealEstate();
		if (realEstate != null) {
			Fasteign fasteign = new Fasteign(realEstate);
			text = fasteign.getDescription();
		}
		else {
			text = StringHandler.EMPTY_STRING;
		}
		try {
			return getElectricalInstallationMessageBusiness().sendDataToEnergyCompany(electricalInstallation, subject, text, uriToResource);
		}
		catch (RemoteException e) {
			throw new IBORuntimeException();
		}
	}
		
	

	private ElectricalInstallationMessageBusiness  getElectricalInstallationMessageBusiness() {
		if (electricalInstallationMessageBusiness == null) {
			electricalInstallationMessageBusiness = (ElectricalInstallationMessageBusiness) getServiceBean(ElectricalInstallationMessageBusiness.class);
		}
		return electricalInstallationMessageBusiness;
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
