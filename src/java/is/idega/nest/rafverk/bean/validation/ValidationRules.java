/*
 * $Id: ValidationRules.java,v 1.6 2007/08/09 16:35:35 thomas Exp $
 * Created on Apr 25, 2007
 *
 * Copyright (C) 2007 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package is.idega.nest.rafverk.bean.validation;

import java.util.Set;

import com.idega.fop.validator.PropertyValidator;


/**
 * 
 *  Last modified: $Date: 2007/08/09 16:35:35 $ by $Author: thomas $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.6 $
 */
public interface ValidationRules {
	
	/**
	 * Returns a validator or a default one. 
	 * 
	 * @param key
	 * @return
	 */
	PropertyValidator getValidator(String key);
	
	String getUserMessage(String key);
	
	Set getKeySet();
	

}
