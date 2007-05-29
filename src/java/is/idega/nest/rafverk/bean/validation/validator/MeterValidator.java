/*
 * $Id: MeterValidator.java,v 1.2 2007/05/29 11:27:09 thomas Exp $
 * Created on May 16, 2007
 *
 * Copyright (C) 2007 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package is.idega.nest.rafverk.bean.validation.validator;

import is.idega.nest.rafverk.bean.constants.FieldID;
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
 *  Last modified: $Date: 2007/05/29 11:27:09 $ by $Author: thomas $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.2 $
 */
public class MeterValidator implements PropertyValidator, PropertyVisitor {

	public static MeterValidator getMeterValidatorNumberDevicePhaseRateAmpere(boolean number, boolean device, boolean phase, boolean ampere, boolean rate) {
		return new MeterValidator(number, device, phase, ampere, rate);
	}
	
	private boolean number = true;
	private boolean device = true;
	private boolean phase = true;
	private boolean ampere = true;
	private boolean rate = true;
	
	public MeterValidator(boolean number, boolean device, boolean phase, boolean ampere, boolean rate) {
		this.number = number;
		this.device = device;
		this.phase = phase;
		this.ampere = ampere;
		this.rate = rate;
	}
	
	/* (non-Javadoc)
	 * @see com.idega.fop.validator.PropertyValidator#isValid(com.idega.fop.data.Property)
	 */
	public boolean isValid(Property value) {
		Boolean isValid = (Boolean) value.accept(this);
		return isValid.booleanValue();
	}

	public Object visit(PropertyTree propertyTree) {
		List values  = propertyTree.getValue();
		Iterator iterator = values.iterator();
		while (iterator.hasNext()) {
			Property property = (Property) iterator.next();
			if (! isValid(property)) {
				return Boolean.FALSE;
			}
		}
		return Boolean.TRUE;
	}

	public Object visit(PropertyImpl propertyImpl) {
		String key = propertyImpl.getKey();
		String value = propertyImpl.getValue();
		if (number && FieldID.NUMBER.equals(key)) {
			if (StringHandler.isEmpty(value)) {
				return Boolean.FALSE;
			}
		}
		else if (device && FieldID.DEVICE.equals(value)) {
			if (StringHandler.isEmpty(value)) {
				return Boolean.FALSE;
			}
		}
		else if (phase && FieldID.PHASE.equals(value)) {
			if (StringHandler.isEmpty(value)) {
				return Boolean.FALSE;
			}
		}
		else if (ampere && FieldID.AMPERE.equals(value)) {
			if (StringHandler.isEmpty(value)) {
				return Boolean.FALSE;
			}
		}
		else if (rate && FieldID.RATE.equals(value)) {
			if (StringHandler.isEmpty(value)) {
				return Boolean.FALSE;
			}
		}
		return Boolean.TRUE;
	}

	public Object visit(PropertyWithUnit propertyWithUnit) {
		return visit((PropertyImpl) propertyWithUnit);
	}

	public Object visit(PropertyWithValueDescription propertyWithValueDescription) {
		return visit((PropertyImpl) propertyWithValueDescription);
	}

	public Object visit(ThreeValuePropertyWithUnit threeValuePropertyWithUnit) {
		return visit((PropertyImpl) threeValuePropertyWithUnit);
	}
}
