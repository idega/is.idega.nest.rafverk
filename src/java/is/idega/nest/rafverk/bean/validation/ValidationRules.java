/*
 * $Id: ValidationRules.java,v 1.5 2007/07/20 16:32:08 thomas Exp $
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
import is.idega.nest.rafverk.bean.validation.validator.RealEstateValidator;

import java.util.HashMap;
import java.util.Map;
import com.idega.fop.validator.PropertyValidator;
import com.idega.fop.validator.impl.EmptyValidator;
import com.idega.fop.validator.impl.IgnoreValidator;
import com.idega.fop.validator.impl.IntegerValidator;
import com.idega.fop.validator.impl.NotEmptyValidator;


/**
 * 
 *  Last modified: $Date: 2007/07/20 16:32:08 $ by $Author: thomas $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.5 $
 */
public class ValidationRules {
	
	Map validators = null;
	Map userMessages = null;
	
	PropertyValidator realEstateValidator = null;
	PropertyValidator notEmptyValidator = null; 
	PropertyValidator ignoreValidator = null;
	PropertyValidator emptyGreaterThanZeroValidator = null;
	PropertyValidator voltageSystemGroupValidator = null;
	PropertyValidator onlyNumberValidator = null;
	PropertyValidator phaseAmpereRateValidator = null;
	PropertyValidator deviceValidator = null;
	PropertyValidator ampereRateValidator = null;
	
	public ValidationRules() {
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
		// special one only for voltage system other
		voltageSystemGroupValidator = new RadioButtonPlusInputFieldValidator(FieldID.VOLTAGE_SYSTEM, FieldID.VOLTAGE_SYSTEM_OTHER);
		// meter 
		onlyNumberValidator = MeterValidator.getMeterValidatorNumberDevicePhaseRateAmpere(true, false, false, false, false);
		phaseAmpereRateValidator = MeterValidator.getMeterValidatorNumberDevicePhaseRateAmpere(false, false, true, true, true);
		deviceValidator = MeterValidator.getMeterValidatorNumberDevicePhaseRateAmpere(false, true, false, false, false);
		ampereRateValidator = MeterValidator.getMeterValidatorNumberDevicePhaseRateAmpere(false, false, false, true, true);
	}
	
	
	private void initialize() {
		validators = new HashMap();
		userMessages = new HashMap();
		put(FieldID.WORKING_PLACE, realEstateValidator);
		
		put(FieldID.NAME, notEmptyValidator);
		put(FieldID.ENERGY_CONSUMER_PERSONAL_ID, notEmptyValidator);
		put(FieldID.ENERGY_CONSUMER_HOME_PHONE, notEmptyValidator);
		put(FieldID.ENERGY_CONSUMER_WORK_PHONE, notEmptyValidator);
 
		put(FieldID.TYPE, notEmptyValidator, "type not fine" );
		put(FieldID.CURRENT_LINE_MODIFICATION, notEmptyValidator, "is empty");
		put(FieldID.CURRENT_LINE_CONNECTION_MODIFICATION, notEmptyValidator);
		put(FieldID.HOME_LINE, notEmptyValidator);
		put(FieldID.SWITCH_PANEL_MODIFICATION, notEmptyValidator);
		put(FieldID.ELECTRONICAL_PROTECTIVE_MEASURES, notEmptyValidator);
		put(FieldID.APPLICATION, notEmptyValidator);
		put(FieldID.POWER, emptyGreaterThanZeroValidator);
		put(FieldID.PLACE_METER,notEmptyValidator);
		put(FieldID.SWITCH_PANEL_NUMBER,notEmptyValidator);
		put(FieldID.VOLTAGE_SYSTEM_GROUP, voltageSystemGroupValidator);
		// meters
		put(InitialData.TAKA, onlyNumberValidator);
		put(InitialData.FYRIR, onlyNumberValidator);
		put(InitialData.SETJA, phaseAmpereRateValidator);
		put(InitialData.FLUTT_A, onlyNumberValidator);
		put(InitialData.FLUTT_AF, onlyNumberValidator);
		put(InitialData.HJALPATAEKI, deviceValidator);
		put(InitialData.STRAUMSPENNA, ampereRateValidator);	
	}
	
	private void put (String key, PropertyValidator propertyValidator) {
		put(key, propertyValidator, null);
	}
	
	private void put(String key, PropertyValidator propertyValidator, String userMessage) {
		validators.put(key, propertyValidator);
		userMessages.put(key, userMessage);
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
