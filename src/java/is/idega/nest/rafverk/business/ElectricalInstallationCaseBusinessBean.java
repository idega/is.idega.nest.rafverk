/*
 * $Id: ElectricalInstallationCaseBusinessBean.java,v 1.4 2007/09/05 16:33:16 thomas Exp $
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
import java.util.logging.Level;

import javax.ejb.CreateException;
import javax.ejb.FinderException;

import com.idega.block.process.business.CaseBusinessBean;
import com.idega.block.process.data.Case;
import com.idega.block.process.data.CaseCode;
import com.idega.user.data.User;


/**
 * 
 *  Last modified: $Date: 2007/09/05 16:33:16 $ by $Author: thomas $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.4 $
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
		Collection coll = parentCase.getChildren();
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
		User sender = BaseBean.getCurrentUser();
		String subject = "Beiðni um breytingu";
		String text = "Beiðni um breytingu";
		String result = null;
		try {
			CaseCode caseCode = getCaseCodeForElectricalInstallationChange();
			Case newCase = createSubCase(electricalInstallation, caseCode);
			getElectricalInstallationBusiness().getElectricalInstallationState().sendRequestForChange(newCase);
			newCase.setCreator(sender);
			newCase.setParentCase(electricalInstallation);
			newCase.store();
			// note: if a create exception is thrown a user message is not created
			result = getElectricalInstallationMessageBusiness().createUserMessage(electricalInstallation, sender, subject, text);
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
