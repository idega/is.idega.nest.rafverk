/*
 * $Id: ElectricalInstallationBMPBean.java,v 1.4 2007/04/18 17:55:58 thomas Exp $
 * Created on Mar 13, 2007
 *
 * Copyright (C) 2007 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package is.idega.nest.rafverk.domain;

import is.idega.nest.rafverk.util.DataConverter;

import java.util.Collection;
import java.util.List;

import javax.ejb.FinderException;

import com.idega.block.process.data.AbstractCaseBMPBean;
import com.idega.core.location.data.RealEstate;
import com.idega.data.IDOQuery;
import com.idega.user.data.Group;
import com.idega.user.data.User;


/**
 * 
 *  Last modified: $Date: 2007/04/18 17:55:58 $ by $Author: thomas $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.4 $
 */
public class ElectricalInstallationBMPBean extends AbstractCaseBMPBean implements ElectricalInstallation{
	
	// short input fields for names (equal to size of display field in User)
	private static final int SHORT_INPUT_FIELDS = 180;
	
	// length of fields that are identifiers (equal to size of primary keys)
	private static final int IDENTIFIER = 22;
	
	// length of identifer lists (12 times 22)
	private static final int IDENTIFIER_LIST = 264;
	
	// length of description field
	private static final int DESCRIPTION = 255;
	
	// long input fields 
	private static final int LONG_INPUT_FIELD = 255;
	
	// first form Tilkynning
	
	private static final String COLUMN_ELECTRICIAN_ID = "ELECTRICIAN_ID";
	
	private static final String COLUMN_ELECTRICAN_COMPANY = "ElECTRICIAN_COMPANY";
	
	// who is in charge for that project
	private static final String COLUMN_IN_CHARGE = "IN_CHARGE";
	
	// orkuveitufyrirtaeki
	private static final String COLUMN_ENERGY_COMPANY_ID = "ENERGY_COMPANY_ID";
	
	// external project number (used only by the electrician)
	private static final String COLUMN_EXT_PROJECT_ID= "EXT_PROJECT_ID";
	
	// orkukaupandi (user/group/company)
	private static final String COLUMN_ENERGY_CONSUMER_PERSONAL_ID = "CONSUMER_PERSONAL_ID";
	private static final String COLUMN_ENERGY_CONSUMER_NAME = "CONSUMER_NAME";
	private static final String COLUMN_CON_HOME_PHONE = "CON_HOME_PHONE";
	private static final String COLUMN_CON_WORK_PHONE = "CON_WORK_PHONE";
	
	// veitustadur
	private static final String COLUMN_REAL_ESTATE_ID = "REAL_ESTATE_ID";
	
	// notkunarflokkur
	private static final String COLUMN_TYPE = "TYPE";	
	
	// heimtaug
	private static final String COLUMN_CURRENT_LINE_MOD = "CURRENT_LINE_MOD";
	
	// heimtaug tengist 
	private static final String COLUMN_CURRENT_LINE_CON_MOD = "CURRENT_LINE_CON_MOD";
	
	// stofn
	private static final String COLUMN_HOME_LINE_A = "HOME_LINE_A";
	private static final String COLUMN_HOME_LINE_B = "HOME_LINE_B";
	private static final String COLUMN_HOME_LINE_C = "HOME_LINE_C";
	
	// adaltafla
	private static final String COLUMN_SWITCH_PANEL_MOD = "SWITCH_PANEL_MOD";
	
	// varnarradstoefun (electronic protective measures (abbr.EPM))
	private static final String COLUMN_EPM = "EPM";
	
	// beidniUm
	private static final String COLUMN_APPLICATION = "APPLICATION";
	
	// uppsett
	private static final String COLUMN_POWER = "POWER";
	
	// numer toeflu
	private static final String COLUMN_SWITCH_PANEL_NUMBER = "SWITCH_PANEL_NUMBER";
	
	// spennukerfi
	private static final String COLUMN_VOLTAGE_SYSTEM = "VOLTAGE_SYSTEM";
	
	// spennukerfi annad
	private static final String COLUMN_VOLTAGE_SYSTEM_OTHER = "VOLTAGE_SYSTEM_OTHER";
	
	// skyringar
	private static final String COLUMN_APPLICATION_REMARKS = "APPLICATION_REMARKS";
	
	// first form end Tilkynning +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	// start form Tilkynning lok verks +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	// tilkynnt
	private static final String COLUMN_ANNOUNCEMENT = "ANNOUNCEMENT";
	
	// tilkynnt annad
	private static final String COLUMN_ANNOUNCEMENT_OTHER = "ANNOUNCEMENT_OTHER";
	
	// notkunarflokkur
	private static final String COLUMN_REPORT_TYPE = "REPORT_TYPE";
	
	// skyring
	private static final String COLUMN_ANNOUNCEMENT_REMARKS = "ANNOUNCEMENT_REMARKS";
	
	// spennukerfi
	private static final String COLUMN_REPORT_VOLTAGE_SYSTEM = "REPORT_VOLTAGE_SYSTEM";
	
	// annad
	private static final String COLUMN_REPORT_VOLTAGE_SYSTEM_OTHER = "REPORT_VOLTAGE_SYSTEM_OTHER";
																	
	// varnarradstoefun (electronic protective measures (abbr.EPM))
	private static final String COLUMN_REPORT_EPM ="REPORT_EPM";
	
	// jardskaut
	private static final String COLUMN_GROUNDING = "GROUNDING";
	
	// jardskautannad
	private static final String COLUMN_GROUNDING_OTHER = "GROUNDING_OTHER";
	
	// skyringar
	private static final String COLUMN_REMARKS = "REMARKS";
	
	// hringrasarvidam
	private static final String COLUMN_SWITCH_PANEL_RESISTENCE = "SWITCH_PANEL_RESISTENCE";
	
	// skammhlaupsstraumur
	private static final String COLUMN_SWITCH_PANEL_AMPERE = "SWITCH_PANEL_AMPERE";
	
	// einangrunNeysluveitu
	private static final String COLUMN_INSULATION_RESISTENCE = "INSULATION_RESISTENCE";
	
	// hringrasarvidnamJardskaut
	private static final String COLUMN_GROUNDING_RESISTENCE = "GROUNDING_RESISTENCE";
	
	// skammhlaupsstraumurNeysluveitu
	private static final String COLUMN_SHORT_CIRCUIT_AMPERE = "SHORT_CIRCUIT_AMPERE";
	
	// hringrasarvidnamNeysluveitu
	private static final String COLUMN_RESISTENCE = "RESISTENCE";
	
	// maeldSpennaFasiN
	private static final String COLUMN_VOLTAGE_PHASE_N = "VOLTAGE_PHASE_N";
	
	// maeldSpennaFasiFasi
	private static final String COLUMN_VOLTAGE_PHASE_PHASE = "VOLTAGE_PHASE_PHASE";
	
	// lekastraumsrofi (ground fault circuit interrupter)
	private static final String COLUMN_FUSE_ATTACHED = "FUSE_ATTACHED";
	
	// spennuhaekkunUtleysingVolt
	private static final String COLUMN_FUSE_VOLTAGE = "FUSE_VOLTAGE";
	
	// lekastraumsrofaUtleysingMillisecond
	private static final String COLUMN_FUSE_TIME = "FUSE_TIME";
	
	// skyringarMaelingar
	private static final String COLUMN_MEASUREMENT_REMARKS = "MEASUREMENT_REMARKS";
	
	// stadur maelir 
	private static final String COLUMN_REPORT_METER_ID = "REPORT_METER_ID";
	
	// end form Tilkynning lok verks

	/* (non-Javadoc)
	 * @see com.idega.block.process.data.AbstractCaseBMPBean#getCaseCodeDescription()
	 */
	public String getCaseCodeDescription() {
		return "Electrical installation";
	}

	/* (non-Javadoc)
	 * @see com.idega.block.process.data.AbstractCaseBMPBean#getCaseCodeKey()
	 */
	public String getCaseCodeKey() {
		// TODO Auto-generated method stub
		return "ELINST";
	}

	/* (non-Javadoc)
	 * @see com.idega.data.GenericEntity#getEntityName()
	 */
	public String getEntityName() {
		// TODO Auto-generated method stub
		return "nest_el_install";
	}
	
	/* (non-Javadoc)
	 * @see com.idega.data.GenericEntity#initializeAttributes()
	 */
	public void initializeAttributes() {
		addGeneralCaseRelation();
		
		// first form
		addAttribute(COLUMN_ELECTRICAN_COMPANY, "electrician company", String.class, LONG_INPUT_FIELD);
		
		addAttribute(COLUMN_EXT_PROJECT_ID, "external project identifier", String.class, SHORT_INPUT_FIELDS);
		addAttribute(COLUMN_IN_CHARGE, "in charge", String.class, SHORT_INPUT_FIELDS);
		addAttribute(COLUMN_ENERGY_CONSUMER_PERSONAL_ID, "energy consumer personal id", String.class, IDENTIFIER);
		addAttribute(COLUMN_ENERGY_CONSUMER_NAME, "energy consumer name", String.class, LONG_INPUT_FIELD);
		addAttribute(COLUMN_CON_HOME_PHONE, "consumer home phone", String.class, LONG_INPUT_FIELD);
		addAttribute(COLUMN_CON_WORK_PHONE, "consumer work phone", String.class, LONG_INPUT_FIELD);
		
		addAttribute(COLUMN_TYPE, "type", String.class, IDENTIFIER);
		addAttribute(COLUMN_REPORT_TYPE, "report type", String.class, IDENTIFIER);
		addAttribute(COLUMN_CURRENT_LINE_MOD, "current line modification", String.class, IDENTIFIER);
		addAttribute(COLUMN_CURRENT_LINE_CON_MOD, "current line connection modification", String.class, IDENTIFIER);
		addAttribute(COLUMN_HOME_LINE_A, "home line A", String.class, SHORT_INPUT_FIELDS);
		addAttribute(COLUMN_HOME_LINE_B, "home line B", String.class, SHORT_INPUT_FIELDS);
		addAttribute(COLUMN_HOME_LINE_C, "home line C", String.class, SHORT_INPUT_FIELDS);
		
		addAttribute(COLUMN_SWITCH_PANEL_MOD, "switch panel modification", String.class, IDENTIFIER);
		addAttribute(COLUMN_EPM, "electronic protective measures", String.class, IDENTIFIER_LIST);
		addAttribute(COLUMN_REPORT_EPM, "report electronic protective measures", String.class,IDENTIFIER_LIST);
		
		addAttribute(COLUMN_APPLICATION, "application", String.class, IDENTIFIER_LIST);
		addAttribute(COLUMN_POWER, "power", String.class, SHORT_INPUT_FIELDS);
		addAttribute(COLUMN_SWITCH_PANEL_NUMBER, "number switch panel", String.class, SHORT_INPUT_FIELDS);
		addAttribute(COLUMN_VOLTAGE_SYSTEM, "voltage system", String.class, IDENTIFIER);
		addAttribute(COLUMN_REPORT_VOLTAGE_SYSTEM, "report voltage system", String.class, IDENTIFIER);
		addAttribute(COLUMN_VOLTAGE_SYSTEM_OTHER, "voltage system other", String.class, SHORT_INPUT_FIELDS);
		addAttribute(COLUMN_REPORT_VOLTAGE_SYSTEM_OTHER, "report voltage system other", String.class, SHORT_INPUT_FIELDS);
		
		addAttribute(COLUMN_APPLICATION_REMARKS, "application remarks", String.class, DESCRIPTION);
		
		// second form
		
		addAttribute(COLUMN_ANNOUNCEMENT, "announcement", String.class, IDENTIFIER);
		addAttribute(COLUMN_ANNOUNCEMENT_OTHER, "announcement other", String.class, SHORT_INPUT_FIELDS);
		addAttribute(COLUMN_ANNOUNCEMENT_REMARKS, "announcement remarks", String.class, DESCRIPTION);
		addAttribute(COLUMN_GROUNDING, "grounding", String.class, IDENTIFIER_LIST);
		addAttribute(COLUMN_GROUNDING_OTHER, "grounding other", String.class, SHORT_INPUT_FIELDS);
		addAttribute(COLUMN_REMARKS, "remarks", String.class, DESCRIPTION);
		
		addAttribute(COLUMN_SWITCH_PANEL_RESISTENCE, "switch panel resistence", String.class, SHORT_INPUT_FIELDS);
		addAttribute(COLUMN_SWITCH_PANEL_AMPERE, "switch panel ampere", String.class, SHORT_INPUT_FIELDS);
		addAttribute(COLUMN_INSULATION_RESISTENCE, "insulation resistence", String.class, SHORT_INPUT_FIELDS);
		addAttribute(COLUMN_GROUNDING_RESISTENCE, "grounding resistence", String.class, SHORT_INPUT_FIELDS);
		
		addAttribute(COLUMN_SHORT_CIRCUIT_AMPERE, "short circuit ampere", String.class, SHORT_INPUT_FIELDS);
		addAttribute(COLUMN_RESISTENCE, "resistence", String.class, SHORT_INPUT_FIELDS);
		addAttribute(COLUMN_VOLTAGE_PHASE_N, "voltage phase n", String.class, SHORT_INPUT_FIELDS);
		addAttribute(COLUMN_VOLTAGE_PHASE_PHASE, "phase phase", String.class, SHORT_INPUT_FIELDS);
		
		addAttribute(COLUMN_FUSE_ATTACHED, "fuse attached", Boolean.class);
		addAttribute(COLUMN_FUSE_VOLTAGE, "fuse voltage", String.class, SHORT_INPUT_FIELDS);
		addAttribute(COLUMN_FUSE_TIME, "fuse time", String.class, SHORT_INPUT_FIELDS);
	
		addAttribute(COLUMN_MEASUREMENT_REMARKS, "measurement remarks", String.class, DESCRIPTION);
		
		// pointers to other entities
		addManyToOneRelationship(COLUMN_ENERGY_COMPANY_ID, Group.class);
		addManyToOneRelationship(COLUMN_ELECTRICIAN_ID, User.class);
		addManyToOneRelationship(COLUMN_REAL_ESTATE_ID, RealEstate.class);

	}
	
	public void setElectricianCompany(String electricianCompany) {
		setColumn(COLUMN_ELECTRICAN_COMPANY, electricianCompany);
	}
	
	public String getElectricianCompany() {
		return (String) getColumnValue(COLUMN_ELECTRICAN_COMPANY);
	}

	public void setExternalProjectID(String externalProjectID) {
		setColumn(COLUMN_EXT_PROJECT_ID, externalProjectID);
	}
	
	public String getExternalProjectID() {
		return (String) getColumnValue(COLUMN_EXT_PROJECT_ID);
	}

	public void setPersonInCharge(String personInCharge) {
		setColumn(COLUMN_IN_CHARGE, personInCharge);
	}
	
	public String getPersonInCharge() {
		return (String) getColumnValue(COLUMN_IN_CHARGE);
	}
	
	public void setEnergyConsumerHomePhone(String consumerHomePhone) {
		setColumn(COLUMN_CON_HOME_PHONE, consumerHomePhone);
	}
	
	public String getEnergyConsumerHomePhone() {
		return (String) getColumnValue(COLUMN_CON_HOME_PHONE);
	}
	
	public void setEnergyConsumerWorkPhone(String consumerWorkPhone) {
		setColumn(COLUMN_CON_WORK_PHONE, consumerWorkPhone);
	}
	
	public String getEnergyConsumerWorkPhone() {
		return (String) getColumnValue(COLUMN_CON_WORK_PHONE);
	}
	
	public void setType(String type) {
		setColumn(COLUMN_TYPE, type);
	}
	
	public String getType() {
		return (String) getColumnValue(COLUMN_TYPE);
	}
	
	public void setTypeInReport(String typeInReport) {
		setColumn(COLUMN_REPORT_TYPE, typeInReport);
	}
	
	public String getTypeInReport() {
		return (String) getColumnValue(COLUMN_REPORT_TYPE);
	}
	
	public void setCurrentLineModification(String currentLineModification) {
		setColumn(COLUMN_CURRENT_LINE_MOD, currentLineModification);
	}
	
	public String getCurrentLineModification() {
		return (String) getColumnValue(COLUMN_CURRENT_LINE_MOD);
	}
	
	public void setCurrentLineConnectionModification(String currentLineConnectionModification) {
		setColumn(COLUMN_CURRENT_LINE_CON_MOD, currentLineConnectionModification);
	}
	
	public String getCurrentLineConnectionModification() {
		return (String) getColumnValue(COLUMN_CURRENT_LINE_CON_MOD);
	}
	
	public void setHomeLineA(String homelLineA) {
		setColumn(COLUMN_HOME_LINE_A, homelLineA);
	}
	
	public String getHomeLineA() {
		return (String) getColumnValue(COLUMN_HOME_LINE_A);
	}
	
	public void setHomeLineB(String homelLineB) {
		setColumn(COLUMN_HOME_LINE_B, homelLineB);
	}
	
	public String getHomeLineB() {
		return (String) getColumnValue(COLUMN_HOME_LINE_B);
	}
	
	public void setHomeLineC(String homelLineC) {
		setColumn(COLUMN_HOME_LINE_C, homelLineC);
	}
	
	public String getHomeLineC() {
		return (String) getColumnValue(COLUMN_HOME_LINE_C);
	}

	public void setSwitchPanelModification(String switchPanelModification) {
		setColumn(COLUMN_SWITCH_PANEL_MOD, switchPanelModification);
	}
	
	public String getSwitchPanelModification() {
		return (String) getColumnValue(COLUMN_SWITCH_PANEL_MOD);
	}
 
	public void setElectronicalProtectiveMeasures(List epm) {
		setColumn(COLUMN_EPM, DataConverter.encodeIdentifers(epm));
	}
	
	public List getElectronicalProtectiveMeasures() {
		return DataConverter.decodeIdentifiers((String) getColumnValue(COLUMN_EPM));
	}

	public void setElectronicalProtectiveMeasuresInReport(List epm) {
		setColumn(COLUMN_REPORT_EPM, DataConverter.encodeIdentifers(epm));
	}
	
	public List getElectronicalProtectiveMeasuresInReport() {
		return DataConverter.decodeIdentifiers((String) getColumnValue(COLUMN_REPORT_EPM));
	}
	
	public void setApplication(List application) {
		setColumn(COLUMN_APPLICATION, DataConverter.encodeIdentifers(application));
	}
	
	public List getApplication() {
		return DataConverter.decodeIdentifiers((String) getColumnValue(COLUMN_APPLICATION));
	}
	
	public void setPower(String power) {
		setColumn(COLUMN_POWER, power);
	}
	
	public String getPower() {
		return (String) getColumnValue(COLUMN_POWER);
	}
	
	public void setSwitchPanelNumber(String switchPanelNumber) {
		setColumn(COLUMN_SWITCH_PANEL_NUMBER, switchPanelNumber);
	}
	
	public String getSwitchPanelNumber() {
		return (String) getColumnValue(COLUMN_SWITCH_PANEL_NUMBER);
	}
	
	public void setVoltageSystem(String voltageSystem) {
		setColumn(COLUMN_VOLTAGE_SYSTEM, voltageSystem);
	}
	
	public String getVoltageSystem() {
		return (String) getColumnValue(COLUMN_VOLTAGE_SYSTEM);
	}
	
	public void setVoltageSystemInReport(String voltageSystem) {
		setColumn(COLUMN_REPORT_VOLTAGE_SYSTEM, voltageSystem);
	}
	
	public String getVoltageSystemInReport() {
		return (String) getColumnValue(COLUMN_REPORT_VOLTAGE_SYSTEM);
	}
	
	public void setVoltageSystemOther(String voltageSystemOther) {
		setColumn(COLUMN_VOLTAGE_SYSTEM_OTHER, voltageSystemOther);
	}
	
	public String getVoltageSystemOther() {
		return (String) getColumnValue(COLUMN_VOLTAGE_SYSTEM_OTHER);
	}
	
	public void setVoltageSystemOtherInReport(String voltageSystemOtherInReport) {
		setColumn(COLUMN_REPORT_VOLTAGE_SYSTEM_OTHER, voltageSystemOtherInReport);
	}
	
	public String getVoltageSystemOtherInReport() {
		return (String) getColumnValue(COLUMN_REPORT_VOLTAGE_SYSTEM_OTHER);
	}

	public void setApplicationRemarks(String applicationRemarks) {
		setColumn(COLUMN_APPLICATION_REMARKS, applicationRemarks);
	}
	
	public String getApplicationRemarks() {
		return (String) getColumnValue(COLUMN_APPLICATION_REMARKS);
	}
	
	public void setAnnouncement(String announcement) {
		setColumn(COLUMN_ANNOUNCEMENT, announcement);
	}
	
	public String getAnnouncement() {
		return (String) getColumnValue(COLUMN_ANNOUNCEMENT);
	}	
	
	public void setAnnouncementOther(String announcementOther) {
		setColumn(COLUMN_ANNOUNCEMENT_OTHER, announcementOther);
	}
	
	public String getAnnouncementOther() {
		return (String) getColumnValue(COLUMN_ANNOUNCEMENT_OTHER);
	}	
	
	public void setAnnouncementRemarks(String announcementRemarks) {
		setColumn(COLUMN_ANNOUNCEMENT_REMARKS, announcementRemarks);
	}
	
	public String getAnnouncementRemarks() {
		return (String) getColumnValue(COLUMN_ANNOUNCEMENT_REMARKS);
	}	
	
	public void setGrounding(List grounding) {
		setColumn(COLUMN_GROUNDING, DataConverter.encodeIdentifers(grounding));
	}
	
	public List getGrounding() {
		return DataConverter.decodeIdentifiers((String) getColumnValue(COLUMN_GROUNDING));
	}	
	
	public void setGroundingOther(String grounding) {
		setColumn(COLUMN_GROUNDING_OTHER, grounding);
	}
	
	public String getGroundingOther() {
		return (String) getColumnValue(COLUMN_GROUNDING_OTHER);
	}		
	
	public void setRemarks(String remarks) {
		setColumn(COLUMN_REMARKS, remarks);
	}
	
	public String getRemarks() {
		return (String) getColumnValue(COLUMN_REMARKS);
	}	

	public void setSwitchPanelResistence(String switchPanelResistence) {
		setColumn(COLUMN_SWITCH_PANEL_RESISTENCE, switchPanelResistence);
	}
	
	public String getSwitchPanelResistence() {
		return (String) getColumnValue(COLUMN_SWITCH_PANEL_RESISTENCE);
	}
	
	public void setSwitchPanelAmpere(String switchPanelAmpere) {
		setColumn(COLUMN_SWITCH_PANEL_AMPERE, switchPanelAmpere);
	}
	
	public String getSwitchPanelAmpere() {
		return (String) getColumnValue(COLUMN_SWITCH_PANEL_AMPERE);
	}
	
	public void setInsulationResistence(String insulationResistence) {
		setColumn(COLUMN_INSULATION_RESISTENCE, insulationResistence);
	}
	
	public String getInsulationResistence() {
		return (String) getColumnValue(COLUMN_INSULATION_RESISTENCE);
	}
	
	public void setGroundingResistence(String groundingResistence) {
		setColumn(COLUMN_GROUNDING_RESISTENCE, groundingResistence);
	}
	
	public String getGroundingResistence() {
		return (String) getColumnValue(COLUMN_GROUNDING_RESISTENCE);
	}
	
	public void setShortCircuitAmpere(String shortCircuitAmpere) {
		setColumn(COLUMN_SHORT_CIRCUIT_AMPERE, shortCircuitAmpere);
	}
	
	public String getShortCircuitAmpere() {
		return (String) getColumnValue(COLUMN_SHORT_CIRCUIT_AMPERE);
	}
	
	public void setResistence(String resistence) {
		setColumn(COLUMN_RESISTENCE, resistence);
	}
	
	public String getResistence() {
		return (String) getColumnValue(COLUMN_RESISTENCE);
	}
	
	public void setVoltagePhaseN(String voltagePhaseN) {
		setColumn(COLUMN_VOLTAGE_PHASE_N, voltagePhaseN);
	}
	
	public String getVoltagePhaseN() {
		return (String) getColumnValue(COLUMN_VOLTAGE_PHASE_N);
	}
	
	public void setVoltagePhasePhase(String voltagePhasePhase) {
		setColumn(COLUMN_VOLTAGE_PHASE_PHASE, voltagePhasePhase);
	}
	
	public String getVoltagePhasePhase() {
		return (String) getColumnValue(COLUMN_VOLTAGE_PHASE_PHASE);
	}
	
	public void setFuseAttached(boolean fuseAttached) {
		setColumn(COLUMN_FUSE_ATTACHED, fuseAttached);
	}
	
	public boolean isFuseAttached() {
		return getBooleanColumnValue(COLUMN_FUSE_ATTACHED, false);
	}
	
	public void setFuseVoltage(String fuseVoltage) {
		setColumn(COLUMN_FUSE_VOLTAGE, fuseVoltage);
	}
	
	public String getFuseVoltage() {
		return (String) getColumnValue(COLUMN_FUSE_VOLTAGE);
	}
	
	public void setFuseTime(String fuseTime) {
		setColumn(COLUMN_FUSE_TIME, fuseTime);
	}
	
	public String getFuseTime() {
		return (String) getColumnValue(COLUMN_FUSE_TIME);
	}
	
	public void setMeasurementRemarks(String measurementRemarks) {
		setColumn(COLUMN_MEASUREMENT_REMARKS, measurementRemarks);
	}
	
	public String getMeasurementRemarks() {
		return (String) getColumnValue(COLUMN_MEASUREMENT_REMARKS);
	}
	
	// pointers to other entities
	
	public void setEnergyCompany(Group energyCompany) {
		setColumn(COLUMN_ENERGY_COMPANY_ID, energyCompany);
	}
	
	public Group getEnergyCompany() {
		return (Group) getColumnValue(COLUMN_ENERGY_COMPANY_ID);
	}
	
	public void setEnergyCompanyID(Integer energyCompanyID) {
		setColumn(COLUMN_ENERGY_COMPANY_ID, energyCompanyID);
	}
	
	public Integer getEnergyCompanyID() {
		return getIntegerColumnValue(COLUMN_ENERGY_COMPANY_ID);
	}
	
	public void setEnergyConsumerPersonalID( String energyConsumerPersonalID) {
		setColumn(COLUMN_ENERGY_CONSUMER_PERSONAL_ID, energyConsumerPersonalID);
	}
	
	public String getEnergyConsumerPersonalID() {
		return (String) getColumnValue(COLUMN_ENERGY_CONSUMER_PERSONAL_ID);
	}
	
	public void setEnergyConsumerName( String energyConsumerName) {
		setColumn(COLUMN_ENERGY_CONSUMER_NAME, energyConsumerName);
	}
	
	public String getEnergyConsumerName() {
		return (String) getColumnValue(COLUMN_ENERGY_CONSUMER_NAME);
	}
	
	
	public void setElectrician(User electrician) {
		setColumn(COLUMN_ELECTRICIAN_ID, electrician);
	}
	
	public User getElectrician() {
		return (User) getColumnValue(COLUMN_ELECTRICIAN_ID);
	}
	
	public void setRealEstate(RealEstate realEstate) {
		setColumn(COLUMN_REAL_ESTATE_ID, realEstate);
	}
	
	public RealEstate getRealEstate() {
		return (RealEstate) getColumnValue(COLUMN_REAL_ESTATE_ID);
	}
	
	public Collection ejbFindElectricalInstallationByElectrician(User electrician) throws FinderException {
	    IDOQuery query = idoQueryGetSelect();
	    query.appendWhere();
	    query.appendEqualsQuoted(COLUMN_ELECTRICIAN_ID, electrician.getPrimaryKey().toString());
	    return idoFindPKsByQuery(query);
	}
	
}
