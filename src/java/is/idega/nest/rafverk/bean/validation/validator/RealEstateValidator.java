/*
 * $Id: RealEstateValidator.java,v 1.1 2007/07/20 16:32:08 thomas Exp $
 * Created on Jul 20, 2007
 *
 * Copyright (C) 2007 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package is.idega.nest.rafverk.bean.validation.validator;

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
 * This class is very similar to the class NotEmptyValidator but
 * if a property is a tree only one child needs to have a not empty value
 * in contrast to NotEmptyValidator where every child must have a not empty value.
 * 
 *  Last modified: $Date: 2007/07/20 16:32:08 $ by $Author: thomas $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.1 $
 */
public class RealEstateValidator implements PropertyVisitor, PropertyValidator {

	/* (non-Javadoc)
	 * @see com.idega.fop.visitor.PropertyVisitor#visit(com.idega.fop.data.PropertyTree)
	 */
	public Object visit(PropertyTree propertyTree) {
		List values  = propertyTree.getValue();
		Iterator iterator = values.iterator();
		// return with true if at least one child has a value!
		// !! Note: almost opposite behaviour than NotEmptyValidator !!
		while (iterator.hasNext()) {
			Property property = (Property) iterator.next();
			if (isValid(property)) {
				return Boolean.TRUE;
			}
		}
		return Boolean.FALSE;
	}

	/* (non-Javadoc)
	 * @see com.idega.fop.visitor.PropertyVisitor#visit(com.idega.fop.data.PropertyImpl)
	 */
	public Object visit(PropertyImpl propertyImpl) {
		String value = propertyImpl.getValue();
		return StringHandler.isNotEmpty(value)? Boolean.TRUE : Boolean.FALSE;
	}

	/* (non-Javadoc)
	 * @see com.idega.fop.visitor.PropertyVisitor#visit(com.idega.fop.data.PropertyWithUnit)
	 */
	public Object visit(PropertyWithUnit propertyWithUnit) {
		return visit((PropertyImpl) propertyWithUnit);
	}

	/* (non-Javadoc)
	 * @see com.idega.fop.visitor.PropertyVisitor#visit(com.idega.fop.data.PropertyWithValueDescription)
	 */
	public Object visit(PropertyWithValueDescription propertyWithValueDescription) {
		return visit((PropertyImpl) propertyWithValueDescription);
	}

	/* (non-Javadoc)
	 * @see com.idega.fop.visitor.PropertyVisitor#visit(com.idega.fop.data.ThreeValuePropertyWithUnit)
	 */
	public Object visit(ThreeValuePropertyWithUnit threeValuePropertyWithUnit) {
		String value = threeValuePropertyWithUnit.getValue();
		if (StringHandler.isNotEmpty(value)) {
			String value2 = threeValuePropertyWithUnit.getValue2();
			if (StringHandler.isNotEmpty(value2)) {
				String value3 = threeValuePropertyWithUnit.getValue3();
				if (StringHandler.isNotEmpty(value3)) {
					return Boolean.TRUE;
				}
			}
		}
		return Boolean.FALSE;
	}

	/* (non-Javadoc)
	 * @see com.idega.fop.validator.PropertyValidator#isValid(com.idega.fop.data.Property)
	 */
	public boolean isValid(Property value) {
		Boolean isValid = (Boolean) value.accept(this);
		return isValid.booleanValue();
	}
}
