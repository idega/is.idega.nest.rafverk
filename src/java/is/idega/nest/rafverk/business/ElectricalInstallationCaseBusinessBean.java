/*
 * $Id: ElectricalInstallationCaseBusinessBean.java,v 1.2 2007/08/17 17:07:22 thomas Exp $
 * Created on Jun 6, 2007
 *
 * Copyright (C) 2007 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package is.idega.nest.rafverk.business;

import is.idega.nest.rafverk.bean.constants.CaseConstants;

import com.idega.block.process.business.CaseBusinessBean;
import com.idega.block.process.data.CaseCode;


/**
 * 
 *  Last modified: $Date: 2007/08/17 17:07:22 $ by $Author: thomas $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.2 $
 */
public class ElectricalInstallationCaseBusinessBean extends CaseBusinessBean implements ElectricalInstallationCaseBusiness{
	
	/**
	 * Can be overrided in subclasses
	 */
	protected String getBundleIdentifier() {
		return CaseConstants.BUNDLE_IDENTIFIER;
	}
	
	public CaseCode getCaseCodeForElectricalInstallationChange() {
		return getCaseCodeAndInstallIfNotExists(CaseConstants.CASE_CODE_KEY_ELCHN);
	}
}
