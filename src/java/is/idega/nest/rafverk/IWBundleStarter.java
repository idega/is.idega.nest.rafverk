/*
 * $Id: IWBundleStarter.java,v 1.2 2007/08/17 17:07:22 thomas Exp $
 * Created on Sep 24, 2005
 *
 * Copyright (C) 2005 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package is.idega.nest.rafverk;

import is.idega.nest.rafverk.bean.constants.CaseConstants;
import is.idega.nest.rafverk.business.ElectricalInstallationCaseBusiness;

import com.idega.block.process.business.CaseCodeManager;
import com.idega.idegaweb.IWBundle;
import com.idega.idegaweb.IWBundleStartable;


/**
 * Last modified: $Date: 2007/08/17 17:07:22 $ by $Author: thomas $
 * 
 * @author <a href="mailto:laddi@idega.com">laddi</a>
 * @version $Revision: 1.2 $
 */
public class IWBundleStarter implements IWBundleStartable {

	/* (non-Javadoc)
	 * @see com.idega.idegaweb.IWBundleStartable#start(com.idega.idegaweb.IWBundle)
	 */
	public void start(IWBundle starterBundle) {
		CaseCodeManager caseCodeManager = CaseCodeManager.getInstance();
		caseCodeManager.addCaseBusinessForCode(CaseConstants.CASE_CODE_KEY_ELINST, ElectricalInstallationCaseBusiness.class);
		caseCodeManager.addCaseBusinessForCode(CaseConstants.CASE_CODE_KEY_ELCHN, ElectricalInstallationCaseBusiness.class);
		
	}

	/* (non-Javadoc)
	 * @see com.idega.idegaweb.IWBundleStartable#stop(com.idega.idegaweb.IWBundle)
	 */
	public void stop(IWBundle starterBundle) {
	}
}
