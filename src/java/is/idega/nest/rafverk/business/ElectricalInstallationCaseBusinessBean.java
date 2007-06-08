/*
 * $Id: ElectricalInstallationCaseBusinessBean.java,v 1.1 2007/06/08 17:06:16 thomas Exp $
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


/**
 * 
 *  Last modified: $Date: 2007/06/08 17:06:16 $ by $Author: thomas $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.1 $
 */
public class ElectricalInstallationCaseBusinessBean extends CaseBusinessBean implements ElectricalInstallationCaseBusiness{
	
	/**
	 * Can be overrided in subclasses
	 */
	protected String getBundleIdentifier() {
		return CaseConstants.BUNDLE_IDENTIFIER;
	}
}
