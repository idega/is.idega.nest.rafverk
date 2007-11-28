/*
 * $Id: ElectricalInstallationCaseBusinessBean.java,v 1.8 2007/11/28 17:55:58 thomas Exp $
 * Created on Jun 6, 2007
 *
 * Copyright (C) 2007 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package is.idega.nest.rafverk.business;

import is.idega.nest.rafverk.bean.BaseBean;
import is.idega.nest.rafverk.bean.constants.CaseConstants;
import is.idega.nest.rafverk.domain.ElectricalInstallation;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.FinderException;

import com.idega.block.process.business.CaseBusinessBean;
import com.idega.block.process.data.Case;
import com.idega.block.process.data.CaseCode;
import com.idega.business.IBORuntimeException;
import com.idega.core.location.data.RealEstate;
import com.idega.core.location.data.Street;
import com.idega.user.data.User;
import com.idega.util.StringHandler;
import com.idega.util.datastructures.list.KeyValuePair;


/**
 * 
 *  Last modified: $Date: 2007/11/28 17:55:58 $ by $Author: thomas $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.8 $
 */
public class ElectricalInstallationCaseBusinessBean extends CaseBusinessBean implements ElectricalInstallationCaseBusiness{
	
	private ElectricalInstallationBusiness electricalInstallationBusiness = null;
	
	private UserMessagesBusiness userMessagesBusiness;
	
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
	
	public KeyValuePair sendRequestForChangingElectrician(ElectricalInstallation electricalInstallation) {
		User sender = BaseBean.getCurrentUser();

		String result = null;
		Case newCase = null;
		try {
			CaseCode caseCode = getCaseCodeForElectricalInstallationChange();
			newCase = createSubCase(electricalInstallation, caseCode);
			// sometimes the owner is not set right in invoked code above
			if (newCase.getOwner() == null) {
				newCase.setOwner(electricalInstallation.getElectrician());
			}
			getElectricalInstallationBusiness().getElectricalInstallationState().sendRequestForChange(newCase);
			newCase.setCreator(sender);
			newCase.setParentCase(electricalInstallation);
			newCase.store();
			// note: if a create exception is thrown a user message is not created
			result = getUserMessagesBusiness().sendUserMessageRequestingChangeOfElectrician(electricalInstallation, sender);
		}
		catch (RemoteException e) {
			throw new RuntimeException(e.getMessage());
		}
		catch (CreateException e) {
			newCase = null;
			// rare case, do not localize
			result = "An error occurred: Request was not sent.";
		}
		return new KeyValuePair(newCase, result);
	}
	
	public String getLocalizedCaseDescription(Case theCase, Locale locale) {
		String result = super.getLocalizedCaseDescription(theCase, locale);
		if (CaseConstants.CASE_CODE_KEY_ELCHN.equals(theCase.getCode())) {
			theCase = theCase.getParentCase();
			if (theCase == null) {
				return result;
			}
		}
		// add street and streetnumber to the result if available
		ElectricalInstallation electricalInstallation = null;
		try {
			electricalInstallation = getElectricalInstallationBusiness().getElectricalInstallationByPrimaryKey(theCase.getPrimaryKey());
		}
		catch (RemoteException e) {
			throw new IBORuntimeException();
		}
		catch (EJBException e) {
			// ignore
		}
		catch (FinderException e) {
			// ignore
		}
		if (electricalInstallation != null) {
			RealEstate realEstate = electricalInstallation.getRealEstate();
			if (realEstate != null) {
				Street street = realEstate.getStreet();
				if (street != null) {
					String streetName = street.getName();
					if (StringHandler.isNotEmpty(streetName)) {
						StringBuffer buffer = new StringBuffer(result);
						buffer.append(": ").append(streetName);
						String streetNumber = realEstate.getStreetNumber();
						if (StringHandler.isNotEmpty(streetNumber)) {
							buffer.append(" ").append(streetNumber);
						}
						return buffer.toString();					
					}
				}
			}
		}
		return result;
	}
		
	private ElectricalInstallationBusiness getElectricalInstallationBusiness() {
		electricalInstallationBusiness = (ElectricalInstallationBusiness) 
			BaseBean.initializeServiceBean(electricalInstallationBusiness, ElectricalInstallationBusiness.class);
		return electricalInstallationBusiness;
	}

	
	private UserMessagesBusiness getUserMessagesBusiness() {
		userMessagesBusiness = (UserMessagesBusiness)  
			BaseBean.initializeServiceBean(userMessagesBusiness, UserMessagesBusiness.class);
		return userMessagesBusiness;
	}
	
}
