/*
 * $Id: ElectricalInstallationValidator.java,v 1.3 2007/05/29 11:27:08 thomas Exp $
 * Created on Apr 20, 2007
 *
 * Copyright (C) 2007 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package is.idega.nest.rafverk.bean.validation;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import com.idega.fop.data.Property;
import com.idega.fop.data.PropertyImpl;
import com.idega.fop.data.PropertyTree;
import com.idega.fop.data.PropertyWithUnit;
import com.idega.fop.data.PropertyWithValueDescription;
import com.idega.fop.data.ThreeValuePropertyWithUnit;
import com.idega.fop.validator.PropertyValidator;
import com.idega.fop.visitor.PropertyVisitor;


/**
 * WARNING: This validator is not thread safe.
 * 
 * Validator checks all fields of an ElectricalInstallation using the specified ValidationRules.
 * Results are collected in a map with corresponding user messages.
 * An empty map means that there are not any errors.
 * 
 * Do not reuse it but rather create a new instance if needed.
 * 
 * One instance should be used per ElectricalInstallation.
 * 
 *  Last modified: $Date: 2007/05/29 11:27:08 $ by $Author: thomas $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.3 $
 */
public class ElectricalInstallationValidator implements PropertyVisitor {
	
	private ValidationRules validationRules = null;
	
	private Map results = null;
	
	public ElectricalInstallationValidator(ValidationRules validationRules) {
		this.validationRules = validationRules;
		results = new HashMap();
	}
	
	private void validate(Property property) {
		String key = property.getKey();
		PropertyValidator validator =  validationRules.getValidator(key);
		if (! validator.isValid(property)) {
			String userMessage = validationRules.getUserMessage(key);
			results.put(key, userMessage);
		}
	}
	
	public Map getResults() {
		return results;
	}

	public Object visit(PropertyTree propertyTree)  {
		validate(propertyTree);
		Iterator iterator = propertyTree.getValue().iterator();
		while (iterator.hasNext()) {
			Property property = (Property) iterator.next();
			property.accept(this);
		}
		return null;
	}

	public Object visit(PropertyImpl propertyImpl) {
		validate(propertyImpl);
		return null;
	}

	public Object visit(PropertyWithUnit propertyWithUnit)  {
		validate(propertyWithUnit);
		return null;
		
	}

	public Object visit(PropertyWithValueDescription propertyWithValueDescription)  {
		validate(propertyWithValueDescription);
		return null;
	}

	public Object visit(ThreeValuePropertyWithUnit threeValuePropertyWithUnit)  {
		validate(threeValuePropertyWithUnit);
		return null;
	}
	
	
}
