/*
 * $Id: ValidationRules.java,v 1.1 2007/05/16 15:54:53 thomas Exp $
 * Created on Apr 25, 2007
 *
 * Copyright (C) 2007 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package is.idega.nest.rafverk.bean.validation;

import is.idega.nest.rafverk.bean.InitialData;
import is.idega.nest.rafverk.bean.constants.FieldID;
import is.idega.nest.rafverk.bean.validation.validator.MeterValidator;
import is.idega.nest.rafverk.bean.validation.validator.RadioButtonPlusInputFieldValidator;
import java.util.HashMap;
import java.util.Map;
import com.idega.fop.validator.PropertyValidator;
import com.idega.fop.validator.impl.EmptyValidator;
import com.idega.fop.validator.impl.IgnoreValidator;
import com.idega.fop.validator.impl.IntegerValidator;
import com.idega.fop.validator.impl.NotEmptyValidator;


/**
 * 
 *  Last modified: $Date: 2007/05/16 15:54:53 $ by $Author: thomas $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.1 $
 */
public class ValidationRules {
	
	Map validators = null;
	
	PropertyValidator notEmptyValidator = null; 
	PropertyValidator ignoreValidator = null;
	PropertyValidator emptyGreaterThanZeroValidator = null;
	PropertyValidator voltageSystemGroupValidator = null;
	PropertyValidator onlyNumberValidator = null;
	
	public ValidationRules() {
		initializeValidators();
		initialize();
	}
	
	private void initializeValidators() {
		// must not be empty
		notEmptyValidator = new NotEmptyValidator();
		// ignore validator
		ignoreValidator = new IgnoreValidator();
		// might be empty else must be greater than zero
		emptyGreaterThanZeroValidator = new EmptyValidator(IntegerValidator.getGreaterThanOrEqualValidator(0, false));
		// special one only for voltage system other
		voltageSystemGroupValidator = new RadioButtonPlusInputFieldValidator(FieldID.VOLTAGE_SYSTEM, FieldID.VOLTAGE_SYSTEM_OTHER);
		// meter 
		onlyNumberValidator = MeterValidator.getMeterValidatorNumberDevicePhaseAmpere(true, false, false, false, false);
		
		
	}
	
	
	private void initialize() {
		validators = new HashMap();
		validators.put(FieldID.TYPE, notEmptyValidator );
		validators.put(FieldID.CURRENT_LINE_MODIFICATION, notEmptyValidator);
		validators.put(FieldID.CURRENT_LINE_CONNECTION_MODIFICATION, notEmptyValidator);
		validators.put(FieldID.HOME_LINE, notEmptyValidator);
		validators.put(FieldID.SWITCH_PANEL_MODIFICATION, notEmptyValidator);
		validators.put(FieldID.ELECTRONICAL_PROTECTIVE_MEASURES, notEmptyValidator);
		validators.put(FieldID.APPLICATION, notEmptyValidator);
		validators.put(FieldID.POWER, emptyGreaterThanZeroValidator);
		validators.put(FieldID.PLACE_METER,notEmptyValidator);
		validators.put(FieldID.SWITCH_PANEL_NUMBER,notEmptyValidator);
		validators.put(FieldID.VOLTAGE_SYSTEM_GROUP, voltageSystemGroupValidator);
		// meter 

		
		
		
	}
	
	/**
	 * Returns a validator or a default one. 
	 * 
	 * @param key
	 * @return
	 */
	public PropertyValidator getValidator(String key) {
		PropertyValidator propertyValidator = (PropertyValidator) validators.get(key);
		return (propertyValidator == null) ? ignoreValidator : propertyValidator;
	}
	

}
