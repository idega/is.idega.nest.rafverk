/*
 * $Id: UserMessagesBusinessBean.java,v 1.2 2007/11/28 17:52:12 thomas Exp $
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
import is.postur.Gata;

import java.rmi.RemoteException;

import com.idega.business.IBOLookup;
import com.idega.business.IBORuntimeException;
import com.idega.business.IBOService;
import com.idega.business.IBOServiceBean;
import com.idega.core.location.data.RealEstate;
import com.idega.idegaweb.IWResourceBundle;
import com.idega.user.data.Group;
import com.idega.user.data.User;
import com.idega.util.StringHandler;


/**
 * 
 *  Last modified: $Date: 2007/11/28 17:52:12 $ by $Author: thomas $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.2 $
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
	public String getMessageAfterRemovingRequestForChangeOfElectrician(ElectricalInstallation electricalInstallation) {
		IWResourceBundle resourceBundle = BaseBean.getResourceBundle();
		String[] arguments = getArgumentsFrom(electricalInstallation);
		String message = "The request needs not to be answered and has been removed.";
		return resourceBundle.getLocalizedAndFormattedString("rafverk_message_after_removing_request_for_change", message, arguments);
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
		"Energy consumer: {3} <br>" +
		"Energy company that received the application: {4}";
		return resourceBundle.getLocalizedAndFormattedString("rafverk_message_after_sending_in_application", message, arguments);
	}	
	
	/**
	 * used on "sent" jsf page
	 */
	public String getMessageAfterSendingInApplicationReport(ElectricalInstallation electricalInstallation) {
		IWResourceBundle resourceBundle = BaseBean.getResourceBundle();
		String[] arguments = getArgumentsFrom(electricalInstallation);
		String message = "Report with external project id {1} has been sent in. <br>" +
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
		String message = "Working place <br>{2}<br> has been checked out. <br>" +
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
		String result1 = sendAcceptUserMessageToNewOwner(oldElectricalInstallation, newElectricalInstallation, resourceBundle);
		String result2 = sendHandshakingUserMessageToOldOwner(oldElectricalInstallation, newElectricalInstallation, resourceBundle);
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
		return result1 + " " + result2;
	}
		
	private String sendAcceptUserMessageToNewOwner(ElectricalInstallation oldElectricalInstallation, ElectricalInstallation newElectricalInstallation, IWResourceBundle resourceBundle ) {
		// send accept message to new owner
		String[] oldArguments = getArgumentsFrom(oldElectricalInstallation);	
		String subject = "Request for taking over task was accepted: {5}";
		String body = "Electrician {0} has accepted your request for taking over the following job: \r"+
		"Working place: {2} \r" +
		"Energy consumer: {3}  \r";
		String localizedSubject = resourceBundle.getLocalizedAndFormattedString("rafverk_request_for_taking_over_task_was_accepted_subject", subject, oldArguments);
		String localizedBody = resourceBundle.getLocalizedAndFormattedString("rafverk_request_for_taking_over_task_was_accepted_body", body, oldArguments);
		User sender = oldElectricalInstallation.getElectrician();
		User receiver = newElectricalInstallation.getElectrician();
		try {
			return getElectricalInstallationMessageBusiness().createUserMessage(newElectricalInstallation, sender, receiver, localizedSubject, localizedBody);
		}
		catch (RemoteException e) {
			// TODO Auto-generated catch block
			throw new IBORuntimeException(e);
		}
	}
		
	private String sendHandshakingUserMessageToOldOwner(ElectricalInstallation oldElectricalInstallation, ElectricalInstallation newElectricalInstallation, IWResourceBundle resourceBundle ) {
		// // send handshaking to yourself
		String[] arguments = getArgumentsFrom(newElectricalInstallation);
		
		// get verknumer (was not taken over)
		String verknumer = oldElectricalInstallation.getExternalProjectID();
		verknumer = StringHandler.replaceIfEmpty(verknumer, "");
		arguments[1] = verknumer;
		
		String subject = "Request for taking over task accepted: {5}";
		String body = "Electrician {0} has taken over the following job: \r"+
		"Project number: {1} \r" +
		"Working place: {2} \r" +
		"Energy consumer: {3}  \r";
		String localizedSubject = resourceBundle.getLocalizedAndFormattedString("rafverk_request_for_taking_over_task_accepted_subject", subject, arguments);
		String localizedBody = resourceBundle.getLocalizedAndFormattedString("rafverk_request_for_taking_over_task_accepted_body", body, arguments);
		User sender = oldElectricalInstallation.getElectrician();
		User receiver = oldElectricalInstallation.getElectrician();
		try {
			return getElectricalInstallationMessageBusiness().createUserMessage(newElectricalInstallation, sender, receiver, localizedSubject, localizedBody);
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
	 * #4 name of energy company
	 * #5 street and streetNumber
	 */
	private String[] getArgumentsFrom(ElectricalInstallation electricalInstallation) {
		// #0
		User electrician = electricalInstallation.getElectrician();
		String electricianName = (electrician == null) ? null : electrician.getName();
 			
		// #1
		String externalProjectID = electricalInstallation.getExternalProjectID();
		
		// #2
		RealEstate realEstate = electricalInstallation.getRealEstate();
		String workingPlaceDisplay = null;
		String streetAndStreetNumber = null;
		if (realEstate != null) {
			Fasteign fasteign = new Fasteign(realEstate);
			workingPlaceDisplay = fasteign.getDescription();	
			
			// #5
			Gata gata = fasteign.getGata();
			if (gata != null) {
				String streetName = gata.getNafn();
				String streetNumber = fasteign.getGotuNumer();
				if (StringHandler.isNotEmpty(streetName)) {
					StringBuffer buffer = new StringBuffer(streetName);
					if (StringHandler.isNotEmpty(streetNumber)) {
						buffer.append(" ").append(streetNumber);
					}
					streetAndStreetNumber = buffer.toString();
				}
			}
		}

		// #3
		String energyConsumerName = electricalInstallation.getEnergyConsumerName();
		
		// #4
		Group energyGroup = electricalInstallation.getEnergyCompany();
		String energyGroupName = (energyGroup == null) ? null : energyGroup.getName();
		
		String[] arg = { electricianName, externalProjectID, workingPlaceDisplay, energyConsumerName, energyGroupName, streetAndStreetNumber };

		// replace null
		for (int i = 0; i < arg.length; i++) {
			arg[i] = StringHandler.replaceIfEmpty(arg[i], "");
		}
		
		return arg;
	}
	
	public String sendUserMessageRequestingChangeOfElectrician(ElectricalInstallation electricalInstallation, User sender) {
		IWResourceBundle resourceBundle = BaseBean.getResourceBundle();
		String result1 = sendUserMessageRequestingChangeOfElectricianToOwner(electricalInstallation, sender, resourceBundle);
		String result2 = sendHandshakingUserMessageToSender(electricalInstallation, sender, resourceBundle);
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
		return result1 + " " + result2;
	}
	
	
	private  String sendUserMessageRequestingChangeOfElectricianToOwner(ElectricalInstallation electricalInstallation, User sender, IWResourceBundle resourceBundle) {
		String[] arguments = getArgumentsFrom(electricalInstallation);

		// replace electrician with sender
		arguments[0] = sender.getName();

		String subject = "Request for taking over task: {5}";
		String body = "Electrician {0} would like to take over the following job: \r"+
		"Number: {1} \r" +
		"Working place: {2} \r" +
		"Energy consumer: {3}  \r" +
		"Please confirm the request as soon as possible";
		
		String localizedSubject = resourceBundle.getLocalizedAndFormattedString("rafverk_request_for_taking_over_task_subject", subject, arguments);
		String localizedBody = resourceBundle.getLocalizedAndFormattedString("rafverk_request_for_taking_over_task_body", body, arguments);
		
		User receiver = electricalInstallation.getElectrician();
		try {
			return getElectricalInstallationMessageBusiness().createUserMessage(electricalInstallation, sender, receiver, localizedSubject, localizedBody);
		}
		catch (RemoteException e) {
			throw new IBORuntimeException(e);
		}
	}
	
	private  String sendHandshakingUserMessageToSender(ElectricalInstallation electricalInstallation, User sender, IWResourceBundle resourceBundle) {
		String[] arguments = getArgumentsFrom(electricalInstallation);


		String subject = "Request for taking over task sent: {5}"; 
		String body = "Request for taking over task was sent to Electrician {0}.\r"+
		"Number: {1} \r" +
		"Working place: {2} \r" +
		"Energy consumer: {3}  \r";
		
		String localizedSubject = resourceBundle.getLocalizedAndFormattedString("rafverk_request_for_taking_over_task_sent_subject", subject, arguments);
		String localizedBody = resourceBundle.getLocalizedAndFormattedString("rafverk_request_for_taking_over_task_sent_body", body, arguments);
		
		try {
			return getElectricalInstallationMessageBusiness().createUserMessage(electricalInstallation, sender, sender, localizedSubject, localizedBody);
		}
		catch (RemoteException e) {
			throw new IBORuntimeException(e);
		}
	}
		
	public String sendDataToEnergyCompany(ElectricalInstallation electricalInstallation, String uriToResource) {
		User electrician = electricalInstallation.getElectrician();
		String name = (electrician == null) ? null : electrician.getName();
		name = StringHandler.replaceIfEmpty(name, "");
		String subject = BaseBean.getResourceBundle()
			.getLocalizedAndFormattedString("rafverk_subject_of_message_to_energy_company", "Application from electrician {0}",
					(new String[] {name}));
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
