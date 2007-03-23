/*
 * $Id: MeterBMPBean.java,v 1.1 2007/03/23 16:13:54 thomas Exp $
 * Created on Mar 13, 2007
 *
 * Copyright (C) 2007 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package is.idega.nest.rafverk.domain;

import java.util.Collection;
import javax.ejb.FinderException;
import com.idega.data.GenericEntity;
import com.idega.data.IDOQuery;


/**
 * 
 *  Last modified: $Date: 2007/03/23 16:13:54 $ by $Author: thomas $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.1 $
 */
public class MeterBMPBean extends GenericEntity implements Meter {
	
	// short input fields for names (equal to size of display field in User)
	private static final int SHORT_INPUT_FIELDS = 180;
	
	// length of fields that are identifiers (equal to size of primary keys)
	private static final int IDENTIFIER = 22;
	
	// long input fields 
	private static final int LONG_INPUT_FIELD = 255;
	
	private static final String COLUMN_PHASE = "PHASE";
	private static final String COLUMN_NUMBER = "NUMBER";
	private static final String COLUMN_AMPERE = "AMPERE";
	private static final String COLUMN_RATE = "RATE";
	private static final String COLUMN_DEVICE = "DEVICE";
	private static final String COLUMN_PLACE = "PLACE";
	
	// pointer to parent electrical installation
	private static final String COLUMN_ELECTRIAL_INSTALLATION_ID = "ELECTRICAL_INSTALLATION_ID";
	// context in what the meter is used 
	private static final String COLUMN_CONTEXT = "CONTEXT";
	// priority within the context
	private static final String COLUMN_PRIORITY_WITHHIN_CONTEXT = "PRIORITY_WITHIN_CONTEXT";
	

	/* (non-Javadoc)
	 * @see com.idega.data.GenericEntity#getEntityName()
	 */
	public String getEntityName() {
		return "nest_meter";
	}

	/* (non-Javadoc)
	 * @see com.idega.data.GenericEntity#initializeAttributes()
	 */
	public void initializeAttributes() {
		addAttribute(getIDColumnName());
		addAttribute(COLUMN_PHASE, "phase", String.class, IDENTIFIER);
		addAttribute(COLUMN_NUMBER, "number", String.class, SHORT_INPUT_FIELDS);
		addAttribute(COLUMN_AMPERE, "ampere", String.class, SHORT_INPUT_FIELDS);
		addAttribute(COLUMN_RATE, "rate", String.class, SHORT_INPUT_FIELDS);
		addAttribute(COLUMN_DEVICE, "device", String.class, SHORT_INPUT_FIELDS);
		addAttribute(COLUMN_PLACE, "place", String.class, LONG_INPUT_FIELD);
		
		addAttribute(COLUMN_CONTEXT, "context", String.class, IDENTIFIER);
		addAttribute(COLUMN_PRIORITY_WITHHIN_CONTEXT, "priority within the context", Integer.class);
		
		// pointers to other entities
		addManyToOneRelationship(COLUMN_ELECTRIAL_INSTALLATION_ID, ElectricalInstallation.class);
	}
	
	public void setPhase(String phase) {
		setColumn(COLUMN_PHASE, phase);
	}
	
	public String getPhase() {
		return (String) getColumnValue(COLUMN_PHASE);
	}
	
	public void setNumber(String number) {
		setColumn(COLUMN_NUMBER, number);
	}
	
	public String getNumber() {
		return (String) getColumnValue(COLUMN_NUMBER);
	}
	
	public void setAmpere(String ampere) {
		setColumn(COLUMN_AMPERE, ampere);
	}
	
	public String getAmpere() {
		return (String) getColumnValue(COLUMN_AMPERE);
	}
	
	public void setRate(String rate) {
		setColumn(COLUMN_RATE, rate);
	}
	
	public String getRate() {
		return (String) getColumnValue(COLUMN_RATE);
	}

	public void setDevice(String device) {
		setColumn(COLUMN_DEVICE, device);
	}
	
	public String getDevice() {
		return (String) getColumnValue(COLUMN_NUMBER);
	}
	
	public void setPlace(String place) {
		setColumn(COLUMN_PLACE, place);
	}
	
	public String getPlace() {
		return (String) getColumnValue(COLUMN_PLACE);
	}
	
	public void setContext(String context) {
		setColumn(COLUMN_CONTEXT, context);
	}
	
	public String getContext() {
		return (String) getColumnValue(COLUMN_CONTEXT);
	}
	
	public void setPriorityWithinContext(int priority) {
		setColumn(COLUMN_NUMBER, priority);
	}
	
	public int getPriorityWithinContext() {
		return getIntColumnValue(COLUMN_NUMBER, 0);
	}
	
	// pointer to other entities
	public void setElectricalInstallation(ElectricalInstallation electricalInstallation) {
		setColumn(COLUMN_ELECTRIAL_INSTALLATION_ID, electricalInstallation.getPrimaryKey());
	}
	
	public ElectricalInstallation getElectricalInstallation() {
		return (ElectricalInstallation) getColumnValue(COLUMN_ELECTRIAL_INSTALLATION_ID);
	}
	
	public Collection ejbFindMetersByElectricalInstallation(ElectricalInstallation electricalInstallation) throws FinderException {
	    IDOQuery query = idoQueryGetSelect();
	    query.appendWhere();
	    query.appendEqualsQuoted(COLUMN_ELECTRIAL_INSTALLATION_ID, electricalInstallation.getPrimaryKey().toString());
	    return idoFindPKsByQuery(query);

	}
}
