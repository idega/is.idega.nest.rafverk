/*
 * $Id: RadioButtonPlusInputFieldValidator.java,v 1.3 2007/08/09 16:36:11 thomas Exp $
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
 *  Last modified: $Date: 2007/08/09 16:36:11 $ by $Author: thomas $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.3 $
 */
public class RadioButtonPlusInputFieldValidator implements PropertyValidator, PropertyVisitor {
	
	private String radioButtonKey = null;
	private String radioButtonValue = InitialData.ANNAD;
	private String firstInputFieldKey = null;
	private String secondInputFieldKey = null;
	
	public RadioButtonPlusInputFieldValidator(String radioButtonKey, String inputFieldKey) {
		this.radioButtonKey = radioButtonKey;
		this.firstInputFieldKey = inputFieldKey;
	}
	
	public RadioButtonPlusInputFieldValidator(String radioButtonKey, String radioButtonValue, String firstInputFieldKey, String secondInputFieldKey) {
		this(radioButtonKey, firstInputFieldKey);
		this.secondInputFieldKey = secondInputFieldKey;
		this.radioButtonValue = radioButtonValue;
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
		String firstValue = null;
		String secondValue = null;
		List children = propertyTree.getValue();
		Iterator iterator = children.iterator();
		while (iterator.hasNext()) {
			Property property = (Property) iterator.next();
			String key = property.getKey();
			if (radioButtonKey.equals(key)) {
				value = getValue(property);
			}
			else if (firstInputFieldKey.equals(key)) {
				firstValue = getValue(property);
			}
			else if (secondInputFieldKey != null && secondInputFieldKey.equals(key)) {
				secondValue = getValue(property); 
			}
		}
		// check first value, must be not empty
		if (StringHandler.isEmpty(value)) {
			return Boolean.FALSE;
		}
		// if the radio button is set to the specified value the inputfields must be not empty
		if (radioButtonValue.equals(value)) { 
			if (StringHandler.isNotEmpty(firstValue) &&
					(secondInputFieldKey == null || StringHandler.isNotEmpty(secondValue))) {
				return Boolean.TRUE;
			}
		}
		// if the radio button is to something else input fields must be empty
		else if (StringHandler.isEmpty(firstValue) &&
				(secondInputFieldKey == null || StringHandler.isEmpty(secondValue))) {
			return Boolean.TRUE;
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
