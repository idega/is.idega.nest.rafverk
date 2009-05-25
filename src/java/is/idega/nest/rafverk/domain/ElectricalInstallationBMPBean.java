/*
 * $Id: ElectricalInstallationBMPBean.java,v 1.17 2009/05/25 13:43:17 valdas Exp $
 * Created on Mar 13, 2007
 *
 * Copyright (C) 2007 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package is.idega.nest.rafverk.domain;

import is.idega.nest.rafverk.bean.constants.CaseConstants;
import is.idega.nest.rafverk.business.ElectricalInstallationBusiness;
import is.idega.nest.rafverk.business.ElectricalInstallationState;
import is.idega.nest.rafverk.data.MaelirList;
import is.idega.nest.rafverk.data.RealEstateIdentifier;
import is.idega.nest.rafverk.util.DataConverter;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

import javax.ejb.FinderException;

import com.idega.block.process.data.AbstractCaseBMPBean;
import com.idega.block.process.data.Case;
import com.idega.business.IBORuntimeException;
import com.idega.core.location.data.RealEstate;
import com.idega.data.IDOAddRelationshipException;
import com.idega.data.IDOQuery;
import com.idega.data.IDORemoveRelationshipException;
import com.idega.user.data.Group;
import com.idega.user.data.User;
import com.idega.util.StringHandler;


/**
 * 
 *  Last modified: $Date: 2009/05/25 13:43:17 $ by $Author: valdas $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.17 $
 */
public class ElectricalInstallationBMPBean extends AbstractCaseBMPBean implements ElectricalInstallation,SimpleElectricalInstallation,Case {
	
	/* Following fields are in application as well as in report -
	At the beginning the fields are set to be the same that is 
	after filling in the first form (application) 
	the fields of the second form (report) are prefilled 
	by the values in the first form 
	TYPE 					REPORT_TYPE
	VOLTAGE_SYSTEM			REPORT_VOLTAGE_SYSTEM	
	VOLTAGE_SYSTEM_OTHER	REPORT_VOLTAGE_SYSTEM_OTHER
	EPM						REPORT_EPM
	METER_ID 				REPORT_METER_ID
	*/
	
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
	private static final String COLUMN_CON_EMAIL = "CON_EMAIL";
	
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
	
	//Added for import from Access:
	//Urtak
	private static final String COLUMN_INSPECTION_SAMPLE = "INSPECTION_SAMPLE";
	//Skodunarstofa
	private static final String COLUMN_INSPECTION_AGENCY_ID = "INSPECTION_AGENCY_ID";
	//DagsSkodunar
	private static final String COLUMN_DATE_OF_INSPECTION = "DATE_OF_INSPECTION";
	//DOCUMENT_ID
	private static final String COLUMN_FORM_DOCUMENT_ID = "FORM_DOCUMENT_ID";
	// end form Tilkynning lok verks
	
	
	/* (non-Javadoc)
	 * @see com.idega.block.process.data.AbstractCaseBMPBean#getCaseCodeDescription()
	 */
	@Override
	public String getCaseCodeDescription() {
		return "Electrical installation";
	}

	/* (non-Javadoc)
	 * @see com.idega.block.process.data.AbstractCaseBMPBean#getCaseCodeKey()
	 */
	@Override
	public String getCaseCodeKey() {
		return CaseConstants.CASE_CODE_KEY_ELINST;
	}

	/* (non-Javadoc)
	 * @see com.idega.data.GenericEntity#getEntityName()
	 */
	@Override
	public String getEntityName() {
		// TODO Auto-generated method stub
		return "nest_el_install";
	}
	
	/* (non-Javadoc)
	 * @see com.idega.data.GenericEntity#initializeAttributes()
	 */
	@Override
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
		addAttribute(COLUMN_CON_EMAIL, "comsumer email", String.class, LONG_INPUT_FIELD);
		
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
		
		//Added for import from Access:
		addAttribute(COLUMN_INSPECTION_SAMPLE, "inspection sample", Boolean.class);
		addAttribute(COLUMN_INSPECTION_AGENCY_ID, "inspection agency id", Integer.class);
		addAttribute(COLUMN_DATE_OF_INSPECTION, "inspection date", Timestamp.class);
		addAttribute(COLUMN_FORM_DOCUMENT_ID, "form document id", Integer.class);
		
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
	
	public void setEnergyConsumerEmail(String consumerEmail) {
		setColumn(COLUMN_CON_EMAIL, consumerEmail);
	}
	
	public String getEnergyConsumerEmail() {
		return (String) getColumnValue(COLUMN_CON_EMAIL);
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
	
	public void setFuseAttached(Boolean fuseAttached) {
		setColumn(COLUMN_FUSE_ATTACHED, fuseAttached);
	}
	
	public Boolean isFuseAttached() {
		return (Boolean) getColumnValue(COLUMN_FUSE_ATTACHED);
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
	
	public Collection ejbFindElectricalInstallationByEnergyCompany(Group energyCompany) throws FinderException {
		IDOQuery query = idoQuery("select n.* from nest_el_install n, proc_case c");
		query.appendWhere();
	    query.appendEqualsQuoted("n.energy_company_id", energyCompany.getPrimaryKey().toString());
	    query.appendAnd();
	    query.append("n.nest_el_install_id").appendEqualSign().append("c.proc_case_id");
		query.appendAnd();
		query.append("c.case_status");
		List openCases = ElectricalInstallationState.getNotVisbibleForEnergyCompany();
		query.appendNotInForStringCollectionWithSingleQuotes(openCases);
	    return idoFindPKsByQuery(query);
	}
	
	public Collection ejbFindElectricalInstallationByRealEstateNumber(String realEstateNumber) throws FinderException {
		IDOQuery query = idoQuery("select n.* from nest_el_install n, ic_real_estate ic");
		query.appendWhere("ic.real_estate_number");
		query.appendEqualSign();
		query.appendQuoted(realEstateNumber);
		query.appendAnd();
		query.append("ic.ic_real_estate_id = n.real_estate_id");
	    return idoFindPKsByQuery(query);
	}
	
	public Collection ejbFindNotFreeElectricalinstallationByRealEstate(RealEstate realEstate, User currentUser) throws FinderException {
		IDOQuery query = getFirstPartOfQueryForRealEstate();
		checkRealEstate(realEstate, query);
		query.appendAnd();
		onlyCurrentUser(currentUser, query);
		query.appendAnd();
		checkStatusFirstPart(query);
		checkStatusSecondPartNotFreeCases(query);
		return idoFindPKsByQuery(query);
	}
	
	
	
	public Collection ejbFindOtherOpenElectricalInstallationByRealEstateIdentifier(RealEstateIdentifier realEstateIdentifier, User currentUser) throws FinderException {
		IDOQuery query = getFirstPartOfQueryForRealEstateIdentifier();
		checkRealEstateByIdentifier(realEstateIdentifier, query);
		query.appendAnd();
		ignoreCurrentUser(currentUser, query);
		query.appendAnd();
		checkStatusFirstPart(query);
		checkStatusSecondPartOpenCases(query);
		return idoFindPKsByQuery(query);
	}
	
	public Collection ejbFindOtherOpenElectricalInstallationByRealEstate(RealEstate realEstate, User currentUser) throws FinderException {
		IDOQuery query = getFirstPartOfQueryForRealEstate();
		checkRealEstate(realEstate, query);
		query.appendAnd();
		ignoreCurrentUser(currentUser, query);
		query.appendAnd();
		checkStatusFirstPart(query);
		checkStatusSecondPartOpenCases(query);
		return idoFindPKsByQuery(query);
	}
	
	public Collection ejbFindOtherClosedElectricalInstallationByRealEstateIdentifer(RealEstateIdentifier realEstateIdentifer, User currentUser) throws FinderException {
		IDOQuery query = getFirstPartOfQueryForRealEstateIdentifier();
		checkRealEstateByIdentifier(realEstateIdentifer, query);
		query.appendAnd();
		ignoreCurrentUser(currentUser, query);
		query.appendAnd();
		checkStatusFirstPart(query);
		checkStatusSecondPartClosedCases(query);
		return idoFindPKsByQuery(query);
	}
	
	public Collection ejbFindOtherClosedElectricalInstallationByRealEstate(RealEstate realEstate, User currentUser) throws FinderException {
		IDOQuery query = getFirstPartOfQueryForRealEstate();
		query.appendWhere();
		checkRealEstate(realEstate, query);
		query.appendAnd();
		ignoreCurrentUser(currentUser, query);
		query.appendAnd();
		checkStatusFirstPart(query);
		checkStatusSecondPartClosedCases(query);
		return idoFindPKsByQuery(query);
	}
		
	private IDOQuery getFirstPartOfQueryForRealEstateIdentifier() {
		IDOQuery query = idoQuery("select n.* from nest_el_install n, ic_real_estate ic, proc_case c");
		query.appendWhere();
		return query;
	}
	
	private IDOQuery getFirstPartOfQueryForRealEstate() {
		// first part of query without real_estate table
		IDOQuery query = idoQuery("select n.* from nest_el_install n, proc_case c");
		query.appendWhere();
		return query;
	}
	
	private void checkRealEstateByIdentifier(RealEstateIdentifier realEstateIdentifier, IDOQuery query) { 
		// land register number
		appendNullOrEqual(query, RealEstateIdentifier.LAND_REGISTER_MAP_COLUMN, realEstateIdentifier.getLandNumber()).appendAnd();
		// real estate number
		appendNullOrEqual(query, RealEstateIdentifier.REAL_ESTATE_NUMBER_COLUMN, realEstateIdentifier.getRealEstateNumber()).appendAnd();
		// real estate unit
		appendNullOrEqual(query, RealEstateIdentifier.REAL_ESTATE_UNIT_COLUMN, realEstateIdentifier.getRealEstateUnit()).appendAnd();
		// real estate code
		appendNullOrEqual(query, RealEstateIdentifier.REAL_ESTATE_CODE_COLUMN, realEstateIdentifier.getRealEstateCode()).appendAnd();
		query.append("ic.ic_real_estate_id = n.real_estate_id");
	}
	
	private void checkRealEstate(RealEstate realEstate, IDOQuery query) {
		query.appendEquals("n.real_estate_id",realEstate);
	}
	
	private void ignoreCurrentUser(User user, IDOQuery query) {
		query.append("n.electrician_id").appendNOTEqual().append(user);
	}
	
	private void onlyCurrentUser(User user, IDOQuery query) {
		query.append("n.electrician_id").appendEqualSign().append(user);
	}
	
	
	private void checkStatusFirstPart(IDOQuery query) {
		query.append("n.nest_el_install_id").appendEqualSign().append("c.proc_case_id");
		query.appendAnd();
		query.append("c.case_status");

	}
	
	private void checkStatusSecondPartClosedCases(IDOQuery query) { 
		List closedCases = ElectricalInstallationState.getFreeOrOpenStatuses();
		query.appendNotInForStringCollectionWithSingleQuotes(closedCases);
	}
	
	private void checkStatusSecondPartOpenCases(IDOQuery query) {
		List openCases = ElectricalInstallationState.getOpenStatuses();
		query.appendInForStringCollectionWithSingleQuotes(openCases);
	}
	
	private void checkStatusSecondPartNotFreeCases(IDOQuery query) {
		List openCases = ElectricalInstallationState.getFreeStatuses();
		query.appendNotInForStringCollectionWithSingleQuotes(openCases);
	}

	
	private IDOQuery appendNullOrEqual(IDOQuery query, String columnName, String value) { 
		String column = StringHandler.concat("ic.", columnName);
		if (value == null) {
			query.append(column).appendIsNull();
		}
		else {
			query.appendEqualsQuoted(column, value);
		}
		return query;
	}
	
	public MaelirList getMaelirList(ElectricalInstallationBusiness electricalInstallationBusiness) {
		try {
			return electricalInstallationBusiness.getMaelirList(this);
		}
		catch (RemoteException e) {
			throw new IBORuntimeException(e);
		}
	}
	
	@Override
	public Object ejbFindByExternalId(String externalId)throws FinderException{
		return super.ejbFindByExternalId(externalId);
	}

	public boolean isInspectionSample() {
		return getBooleanColumnValue(COLUMN_INSPECTION_SAMPLE);
	}

	public void setInspectionSample(boolean inspectionSample) {
		setColumn(COLUMN_INSPECTION_SAMPLE, inspectionSample);
	}

	public int getInspectionAgencyId() {
		return getIntColumnValue(COLUMN_INSPECTION_AGENCY_ID);
	}

	public void setInspectionAgencyId(int inspectionAgencyId) {
		setColumn(COLUMN_INSPECTION_AGENCY_ID, inspectionAgencyId);
	}

	public Timestamp getDateOfInspection() {
		return getTimestampColumnValue(COLUMN_DATE_OF_INSPECTION);
	}

	public void setDateOfInspection(Timestamp dateOfInspection) {
		setColumn(COLUMN_DATE_OF_INSPECTION,dateOfInspection);
	}

	public int getFormDocumentId() {
		return getIntColumnValue(COLUMN_FORM_DOCUMENT_ID);
	}

	public void setFormDocumentId(int formDocumentId) {
		setColumn(COLUMN_FORM_DOCUMENT_ID, formDocumentId);
	}

	public void addSubscriber(User subscriber)
			throws IDOAddRelationshipException {
		throw new UnsupportedOperationException("This method is not implemented!");
	}

	public Collection<User> getSubscribers() {
		throw new UnsupportedOperationException("This method is not implemented!");
	}

	public void removeSubscriber(User subscriber)
			throws IDORemoveRelationshipException {
		throw new UnsupportedOperationException("This method is not implemented!");
	}
	
}
