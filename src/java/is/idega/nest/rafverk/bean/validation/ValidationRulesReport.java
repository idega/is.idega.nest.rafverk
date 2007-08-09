/*
 * $Id: ValidationRulesReport.java,v 1.1 2007/08/09 16:35:35 thomas Exp $
 * Created on Aug 7, 2007
 *
 * Copyright (C) 2007 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package is.idega.nest.rafverk.bean.validation;

import is.idega.nest.rafverk.bean.InitialData;
import is.idega.nest.rafverk.bean.constants.FieldID;
import is.idega.nest.rafverk.bean.validation.validator.RadioButtonPlusInputFieldValidator;
import is.idega.nest.rafverk.bean.validation.validator.RealEstateValidator;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.idega.fop.validator.PropertyValidator;
import com.idega.fop.validator.impl.EmptyValidator;
import com.idega.fop.validator.impl.IgnoreValidator;
import com.idega.fop.validator.impl.IntegerValidator;
import com.idega.fop.validator.impl.NotEmptyValidator;


/**
 * 
 *  Last modified: $Date: 2007/08/09 16:35:35 $ by $Author: thomas $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.1 $
 */
public class ValidationRulesReport implements ValidationRules {
	
	Map validators = null;
	Map userMessages = null;
	
	PropertyValidator realEstateValidator = null;
	PropertyValidator notEmptyValidator = null; 
	PropertyValidator ignoreValidator = null;
	PropertyValidator emptyGreaterThanZeroValidator = null;
	PropertyValidator announcementGroupValidator = null;
	PropertyValidator voltageSystemGroupValidator = null;
	PropertyValidator fuseValidator = null;
	
	public ValidationRulesReport() {
		initializeValidators();
		initialize();
	}
	
	private void initializeValidators() {
		// at least one child must not be empty
		realEstateValidator = new RealEstateValidator();
		// must not be empty
		notEmptyValidator = new NotEmptyValidator();
		// ignore validator
		ignoreValidator = new IgnoreValidator();
		// might be empty else must be greater than zero
		emptyGreaterThanZeroValidator = new EmptyValidator(IntegerValidator.getGreaterThanOrEqualValidator(0, false));
		// special one only for announcement group
		announcementGroupValidator = new RadioButtonPlusInputFieldValidator(FieldID.ANNOUNCEMENT, FieldID.ANNOUNCEMENT_OTHER);
		// special one only for voltage system group
		voltageSystemGroupValidator = new RadioButtonPlusInputFieldValidator(FieldID.VOLTAGE_SYSTEM_IN_REPORT, FieldID.VOLTAGE_SYSTEM_OTHER_IN_REPORT);
		// special one only for fuse 
		fuseValidator = new RadioButtonPlusInputFieldValidator(FieldID.FUSE_ATTACHED, InitialData.LEKASTRAUMSROFI_I_LAGI_KEY, FieldID.FUSE_VOLTAGE, FieldID.FUSE_TIME);

	}
	
	
	private void initialize() {
		validators = new HashMap();
		userMessages = new HashMap();
		put(FieldID.WORKING_PLACE, realEstateValidator);
		
		put(FieldID.NAME, notEmptyValidator);
		put(FieldID.ENERGY_CONSUMER_PERSONAL_ID, notEmptyValidator);
		put(FieldID.ENERGY_CONSUMER_HOME_PHONE, notEmptyValidator);
		put(FieldID.ENERGY_CONSUMER_WORK_PHONE, notEmptyValidator);
		
		put(FieldID.ANNOUNCEMENT_GROUP,announcementGroupValidator);
 
		put(FieldID.TYPE_IN_REPORT, notEmptyValidator, "type not fine" );
		
		put(FieldID.VOLTAGE_SYSTEM_GROUP_IN_REPORT, voltageSystemGroupValidator);
		
		put(FieldID.ELECTRONIC_PROTECTIVE_MEASURES_IN_REPORT, notEmptyValidator);
		
		put(FieldID.FUSE, fuseValidator);
	}
		
		
	
	private void put (String key, PropertyValidator propertyValidator) {
		put(key, propertyValidator, null);
	}
	
	private void put(String key, PropertyValidator propertyValidator, String userMessage) {
		validators.put(key, propertyValidator);
		userMessages.put(key, userMessage);
	}
	
	public Set getKeySet() {
		return validators.keySet();
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
	
	public  String getUserMessage(String key) {
		String userMessage = (String) userMessages.get(key);
		return (userMessage == null) ? "" : userMessage;
	}
	
	
}
