/*
 * $Id: RadioButtonPlusInputFieldValidator.java,v 1.1 2007/05/16 15:54:53 thomas Exp $
 * Created on May 15, 2007
 *
 * Copyright (C) 2007 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package is.idega.nest.rafverk.bean.validation.validator;

import is.idega.nest.rafverk.bean.InitialData;
import java.util.Iterator;
import java.util.List;
import com.idega.fop.data.Property;
import com.idega.fop.data.PropertyImpl;
import com.idega.fop.data.PropertyTree;
import com.idega.fop.data.PropertyWithUnit;
import com.idega.fop.data.PropertyWithValueDescription;
import com.idega.fop.data.ThreeValuePropertyWithUnit;
import com.idega.fop.validator.PropertyValidator;
import com.idega.fop.visitor.PropertyVisitor;
import com.idega.util.StringHandler;


/**
 * 
 *  Last modified: $Date: 2007/05/16 15:54:53 $ by $Author: thomas $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.1 $
 */
public class RadioButtonPlusInputFieldValidator implements PropertyValidator, PropertyVisitor {
	
	private String radioButtonKey = null;
	private String inputFieldKey = null;
	
	public RadioButtonPlusInputFieldValidator(String radioButtonKey, String inputFieldKey) {
		this.radioButtonKey = radioButtonKey;
		this.inputFieldKey = inputFieldKey;
	}

	/* (non-Javadoc)
	 * @see com.idega.fop.validator.PropertyValidator#isValid(com.idega.fop.data.Property)
	 */
	public boolean isValid(Property value) {
		Boolean isValid = (Boolean) value.accept(this);
		return isValid.booleanValue();
	}

	public Object visit(PropertyTree propertyTree) {
		String value = null;
		String otherValue = null;
		List children = propertyTree.getValue();
		Iterator iterator = children.iterator();
		if (iterator.hasNext()) {
			Property property = (Property) iterator.next();
			String key = property.getKey();
			if (radioButtonKey.equals(key)) {
				value = getValue(property);
			}
			if (inputFieldKey.equals(key)) {
				otherValue = getValue(property);
			}
		}
		// check first value, must be not empty
		if (StringHandler.isEmpty(value)) {
			return Boolean.FALSE;
		}
		if (InitialData.ANNAD.equals(value)) {
			// if first value is set to annad, second must be not empty
			if (StringHandler.isNotEmpty(otherValue)) {
				return Boolean.TRUE;
			}
		}
		else {
			// if first value is set to something else second must be empty
			if (StringHandler.isEmpty(otherValue)) {
				return Boolean.TRUE;
			}
		}
		return Boolean.FALSE;
	}
				
	
	private String getValue(Property property) {
		if (property instanceof PropertyImpl) {
			PropertyImpl propertyImpl = (PropertyImpl) property;
			return propertyImpl.getValue();
		}
		return null;
	}

	public Object visit(PropertyImpl propertyImpl) {
		return Boolean.FALSE;
	}

	public Object visit(PropertyWithUnit propertyWithUnit) {
		return Boolean.FALSE;
	}

	public Object visit(PropertyWithValueDescription propertyWithValueDescription) {
		return Boolean.FALSE;
	}

	public Object visit(ThreeValuePropertyWithUnit threeValuePropertyWithUnit) {
		return Boolean.FALSE;
	}
	
}
