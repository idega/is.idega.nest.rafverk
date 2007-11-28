/*
 * $Id: ElectricalInstallationMessageBusinessBean.java,v 1.4 2007/11/28 17:54:19 thomas Exp $
 * Created on Jun 18, 2007
 *
 * Copyright (C) 2007 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package is.idega.nest.rafverk.business;

import is.idega.idegaweb.egov.message.business.CommuneMessageBusiness;
import is.idega.nest.rafverk.bean.BaseBean;
import is.idega.nest.rafverk.domain.ElectricalInstallation;

import java.io.File;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Iterator;

import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.mail.MessagingException;

import com.idega.block.process.message.business.MessageBusiness;
import com.idega.block.process.message.business.MessageValue;
import com.idega.business.IBOLookup;
import com.idega.business.IBORuntimeException;
import com.idega.business.IBOService;
import com.idega.business.IBOServiceBean;
import com.idega.core.contact.data.Email;
import com.idega.user.business.GroupBusiness;
import com.idega.user.business.NoEmailFoundException;
import com.idega.user.business.UserBusiness;
import com.idega.user.data.Group;
import com.idega.user.data.User;
import com.idega.util.SendMail;
import com.idega.util.StringHandler;


/**
 * 
 *  Last modified: $Date: 2007/11/28 17:54:19 $ by $Author: thomas $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.4 $
 */
public class ElectricalInstallationMessageBusinessBean extends IBOServiceBean implements ElectricalInstallationMessageBusiness {
	
	private static String APPLICATION_PROPERTY_SYSTEM_SMTP_MAILSERVER = "messagebox_smtp_mailserver";
	
	private GroupBusiness groupBusiness;
	
	private UserBusiness userBusiness;
	
	private CommuneMessageBusiness communeMessageBusiness;
	
	private MessageBusiness messageBusiness;
	
	private ElectricalInstallationBusiness electricalInstallationBusiness;
	
	public String createStatusChangedUserMessage(ElectricalInstallation electricalInstallation, User sender, User receiver, String text) {
		String statusDescription;
		try {
			statusDescription = getElectricalInstallationBusiness().getElectricalInstallationState().getStatusDescription(electricalInstallation);
		}
		catch (RemoteException e1) {
			e1.printStackTrace();
			throw new IBORuntimeException();
		}
		// composing email
		String subject = 
			BaseBean.getResourceBundle().getLocalizedAndFormattedString("rafverk_status_change", "Status has changed to {0}.", new String[] {statusDescription});
		return createUserMessage(electricalInstallation, sender, receiver, subject, text);
	}
		
	public String createUserMessage(ElectricalInstallation electricalInstallationParentCase, User sender, User receiver, String subject, String text) {

		MessageValue messageValue = new MessageValue();
		messageValue.setSender(sender);
		messageValue.setReceiver(receiver);
		messageValue.setParentCase(electricalInstallationParentCase);
		messageValue.setSubject(subject);
		messageValue.setBody(text);
		// first create message (for message box)
		try {
			// do not use CommmuneMessageBusiness (the method there sets a sender that is defined as application property)
			getMessageBusiness().createMessage(messageValue);
		}
		catch (CreateException e) {
			// rare problem, does not need to be localized
			return "Error: User message for message box could not be created";
		}
		catch (RemoteException e) {
			e.printStackTrace();
			throw new IBORuntimeException();
		}
		// second send email if desired
		if (userPrefersMessageByEmail(receiver)) {
			return sendEmail(sender, receiver, subject, text, getSMTPMailserver());
		}
		return null;
	}
	
	public String sendDataToEnergyCompany(ElectricalInstallation electricalInstallation, String subject, String text, String uriToResource) {
		Group energyCompany = electricalInstallation.getEnergyCompany();
		Collection energyCompanyRepresentatives = null;
		try {
			energyCompanyRepresentatives = getGroupBusiness().getUsers(energyCompany);
		}
		catch (RemoteException e) {
			throw new RuntimeException(e.getMessage());
		}
		catch (FinderException e) {
			e.printStackTrace();
			energyCompanyRepresentatives = null;
		}
		if (energyCompanyRepresentatives == null) {
			return null;
		}
		String realPathtoResource = getIWMainApplication().getRealPath(uriToResource);
		File resource = new File(realPathtoResource);
		String smtpMailServer = getSMTPMailserver();
		Iterator iterator = energyCompanyRepresentatives.iterator();
		String result = null;
		while (iterator.hasNext() && result == null) {
			User energyCompanyRepresentative = (User) iterator.next();
			if (userPrefersMessageByEmail(energyCompanyRepresentative)) {
				User electrician = electricalInstallation.getElectrician();
				result = sendEmail(electrician, energyCompanyRepresentative, subject, text, resource, smtpMailServer);
			}
		}
		return result;
	}
	
	private String getUserMail(User user) {
		try {
			Email email = getUserBusiness().getUsersMainEmail(user);
			return (email == null) ? null : email.getEmailAddress();
			
		}
		catch (RemoteException e) {
			throw new RuntimeException(e.getMessage());
		}
		catch (NoEmailFoundException e) {
			// do nothing, is fine
		}
		return null;
	}
	
	private boolean userPrefersMessageByEmail(User user) {
		try {
			return getCommuneMessageBusiness().getIfUserPreferesMessageByEmail(user);
		}
		catch (RemoteException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	
	public String sendEmail(User fromUser, User toUser, String subject, String text, String smtpMailServer) {
		return sendEmail(fromUser, toUser, subject, text, null, smtpMailServer);
	}
	
		
	public String sendEmail(User fromUser, User toUser, String subject, String text, File resource, String smtpMailServer) {
		String from = getUserMail(fromUser);
		String to = getUserMail(toUser);
		boolean fromAvailable = StringHandler.isNotEmpty(from);
		boolean toAvailable = StringHandler.isNotEmpty(to);
		if (fromAvailable && toAvailable) {
			try {
				SendMail.send(from, to, null, null, smtpMailServer, subject, text, resource);
				return null;
			}
			catch (MessagingException e) {
				return BaseBean.getResourceBundle().
					getLocalizedAndFormattedString("rafverk_error_sending_mail", "Problems appeared sending mail from address {0} to address {1}.", (new String[] {from, to })); 
			}
		}
		else {
			StringBuffer buffer = new StringBuffer();
			if (! fromAvailable) {
				String fromUserName = fromUser.getName();
				buffer.append(
						BaseBean.getResourceBundle()
						.getLocalizedAndFormattedString("rafverk_error_mail_address_not_found", "Mail address of {0} not found.", (new String[] {fromUserName})));
			}
			if ((! fromAvailable) && (! toAvailable)) {
				buffer.append(" ");
			}
			if (! toAvailable) {
				String toUserName = toUser.getName();
				buffer.append(
						BaseBean.getResourceBundle()
						.getLocalizedAndFormattedString("rafverk_error_mail_address_not_found", "Mail address of {0} not found.", (new String[] {toUserName})));
			}
			return buffer.toString();
		}
	}
	
	
	private String getSMTPMailserver() {
		return getIWMainApplication().getSettings().getProperty(APPLICATION_PROPERTY_SYSTEM_SMTP_MAILSERVER);
	}
	
	
	private GroupBusiness getGroupBusiness() {
		if (groupBusiness == null) {
			groupBusiness = (GroupBusiness) getServiceBean(GroupBusiness.class);
		}
		return groupBusiness;
	}
	
	private UserBusiness getUserBusiness() {
		if (userBusiness == null) {
			userBusiness = (UserBusiness) getServiceBean(UserBusiness.class);
		}
		return userBusiness;
	}
	
	private CommuneMessageBusiness getCommuneMessageBusiness() {
		if (communeMessageBusiness == null) {
			communeMessageBusiness = (CommuneMessageBusiness) getServiceBean(CommuneMessageBusiness.class);
		}
		return communeMessageBusiness;
	}
	
	private MessageBusiness getMessageBusiness() {
		if (messageBusiness == null) {
			messageBusiness = (MessageBusiness) getServiceBean(MessageBusiness.class);
		}
		return messageBusiness;
	}
	
	private ElectricalInstallationBusiness getElectricalInstallationBusiness() {
		if (electricalInstallationBusiness == null) {
			electricalInstallationBusiness = (ElectricalInstallationBusiness) getServiceBean(ElectricalInstallationBusiness.class);
		}
		return electricalInstallationBusiness;
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
