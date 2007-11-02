/*
 * $Id: ValidationRulesApplication.java,v 1.3 2007/11/02 16:37:39 thomas Exp $
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
import is.idega.nest.rafverk.bean.validation.validator.MeterValidator;
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
 *  Last modified: $Date: 2007/11/02 16:37:39 $ by $Author: thomas $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.3 $
 */
public class ValidationRulesApplication implements ValidationRules {
	
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
	
	public ValidationRulesApplication() {
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
		put(FieldID.ENERGY_COMPANY, notEmptyValidator, "Þú verður að setja gildi í reitinn");
		put(FieldID.WORKING_PLACE, realEstateValidator, "Þú verður að setja gildi í reitinn");
		
		put(FieldID.NAME, notEmptyValidator, "Þú verður að setja gildi í reitinn");
		put(FieldID.ENERGY_CONSUMER_PERSONAL_ID, notEmptyValidator, "Þú verður að setja gildi í reitinn");
		put(FieldID.ENERGY_CONSUMER_HOME_PHONE, notEmptyValidator, "Þú verður að setja gildi í reitinn");
		put(FieldID.ENERGY_CONSUMER_WORK_PHONE, notEmptyValidator, "Þú verður að setja gildi í reitinn");
 
		put(FieldID.TYPE, notEmptyValidator, "Þú verður að setja gildi í reitinn" );
		put(FieldID.CURRENT_LINE_MODIFICATION, notEmptyValidator, "Þú verður að setja gildi í reitinn");
		put(FieldID.CURRENT_LINE_CONNECTION_MODIFICATION, notEmptyValidator, "Þú verður að setja gildi í reitinn");
		put(FieldID.HOME_LINE, notEmptyValidator, "Þú verður að setja gildi í reitinn");
		put(FieldID.SWITCH_PANEL_MODIFICATION, notEmptyValidator, "Þú verður að setja gildi í reitinn");
		put(FieldID.ELECTRONIC_PROTECTIVE_MEASURES, notEmptyValidator, "Þú verður að setja gildi í reitinn");
		put(FieldID.APPLICATION, notEmptyValidator, "Þú verður að setja gildi í reitinn");
		put(FieldID.POWER, emptyGreaterThanZeroValidator, "Talan verður að vera stærri en núll");
		put(FieldID.PLACE_METER,notEmptyValidator, "Þú verður að setja gildi í reitinn");
		put(FieldID.SWITCH_PANEL_NUMBER,notEmptyValidator, "Þú verður að setja gildi í reitinn");
		put(FieldID.VOLTAGE_SYSTEM_GROUP, voltageSystemGroupValidator, "Þú verður að setja gildi í reitinn");
		// meters
		put(InitialData.TAKA, onlyNumberValidator, "Þú verður að setja gildi í reitinn");
		put(InitialData.FYRIR, onlyNumberValidator, "Þú verður að setja gildi í reitinn");
		put(InitialData.SETJA, phaseAmpereRateValidator, "Þú verður að setja gildi í reitinn");
		put(InitialData.FLUTT_A, onlyNumberValidator, "Þú verður að setja gildi í reitinn");
		put(InitialData.FLUTT_AF, onlyNumberValidator, "Þú verður að setja gildi í reitinn");
		put(InitialData.HJALPATAEKI, deviceValidator, "Þú verður að setja gildi í reitinn");
		put(InitialData.STRAUMSPENNA, ampereRateValidator, "Þú verður að setja gildi í reitinn");	
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
