/*
 * $Id: ElectricalInstallationRendererBusinessBean.java,v 1.11 2008/01/03 11:05:14 laddi Exp $
 * Created on Apr 11, 2007
 *
 * Copyright (C) 2007 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package is.idega.nest.rafverk.business;

import is.idega.nest.rafverk.bean.InitialData;
import is.idega.nest.rafverk.bean.constants.FieldID;
import is.idega.nest.rafverk.data.Maelir;
import is.idega.nest.rafverk.data.MaelirList;
import is.idega.nest.rafverk.domain.ElectricalInstallation;
import is.idega.nest.rafverk.domain.SimpleElectricalInstallation;
import is.idega.nest.rafverk.util.DataConverter;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import com.idega.business.IBOLookup;
import com.idega.business.IBOService;
import com.idega.business.IBOServiceBean;
import com.idega.core.contact.data.Phone;
import com.idega.core.location.data.Address;
import com.idega.core.location.data.PostalCode;
import com.idega.core.location.data.RealEstate;
import com.idega.core.location.data.Street;
import com.idega.fop.data.Property;
import com.idega.fop.data.PropertyImpl;
import com.idega.fop.data.PropertyTree;
import com.idega.fop.tools.PropertyWriter;
import com.idega.presentation.IWContext;
import com.idega.user.business.NoPhoneFoundException;
import com.idega.user.business.UserBusiness;
import com.idega.user.data.Group;
import com.idega.user.data.User;

/**
 * 
 * Last modified: $Date: 2008/01/03 11:05:14 $ by $Author: laddi $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.11 $
 */
public class ElectricalInstallationRendererBusinessBean extends IBOServiceBean implements ElectricalInstallationRendererBusiness {

	private static final String OHM = "\u2126";
	private static final String MEGA_OHM = "M\u2126";
	private static final String VOLT = "V";
	private static final String AMPERE = "A";
	private static final String MILLI_SECONDS = "ms";

	private UserBusiness userBusiness = null;

	private ElectricalInstallationBusiness electricalInstallationBusiness = null;

	public String getPDFApplication(ElectricalInstallation electricalInstallation) throws IOException {
		Property prop = getApplicationProperty(electricalInstallation);
		return getPDFOutput(prop);
	}

	public String getXMLApplication(ElectricalInstallation electricalInstallation) throws IOException {
		Property prop = getApplicationProperty(electricalInstallation);
		return getXMLOutput(prop);
	}

	public String getPDFReport(ElectricalInstallation electricalInstallation) throws IOException {
		Property prop = getReportProperty(electricalInstallation);
		return getPDFOutput(prop);
	}

	public String getXMLReport(ElectricalInstallation electricalInstallation) throws IOException {
		Property prop = getReportProperty(electricalInstallation);
		return getXMLOutput(prop);
	}

	public Property getApplicationProperty(SimpleElectricalInstallation electricalInstallation) {
		PropertyTree prop = new PropertyTree("thjonustubeidni", "Þjónustubeiðni");
		prop.add(getHeadApplication(electricalInstallation)).add(getBodyApplication(electricalInstallation));
		return prop;
	}

	public Property getReportProperty(SimpleElectricalInstallation electricalInstallation) {
		PropertyTree prop = new PropertyTree("skyrsla", "Skýrsla um neysluveitu");
		prop.add(getHeadReport(electricalInstallation)).add(getBodyReport(electricalInstallation)).add(getBottomReport(electricalInstallation));
		return prop;
	}

	private String getPDFOutput(Property property) throws IOException {
		return getOutput(property, PropertyWriter.PDF_RENDERER);
	}

	private String getXMLOutput(Property property) throws IOException {
		return getOutput(property, PropertyWriter.XML_DATA_RENDERER);
	}

	private String getOutput(Property property, String renderer) throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();
		IWContext iwContext = IWContext.getIWContext(context);
		PropertyWriter writerToFile = new PropertyWriter(property, iwContext);
		writerToFile.setRenderer(renderer);
		return writerToFile.createContainer();
	}

	// head application +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	private Property getHeadApplication(SimpleElectricalInstallation electricalInstallation) {
		PropertyTree prop = new PropertyTree("head", "Upplýsingar um neysluveitu");
		prop.add(getEnergyCompany(electricalInstallation));
		addHeadData(prop, electricalInstallation);
		String type = electricalInstallation.getType();
		prop.addWithValueDescription(FieldID.TYPE, "Notkunarflokkur", type, DataConverter.lookup(InitialData.NOTKUNARFLOKKUR, type));
		String currentLineModification = electricalInstallation.getCurrentLineModification();
		prop.addWithValueDescription(FieldID.CURRENT_LINE_MODIFICATION, "Heimtaug", currentLineModification, DataConverter.lookup(InitialData.HEIMTAUG, currentLineModification));
		String currentLineConnectionModification = electricalInstallation.getCurrentLineConnectionModification();
		prop.addWithValueDescription(FieldID.CURRENT_LINE_CONNECTION_MODIFICATION, "Heimtaug tengist í", currentLineConnectionModification, DataConverter.lookup(InitialData.HEIMTAUG_TENGIST, currentLineConnectionModification));
		prop.add(FieldID.HOME_LINE, "Stofn/kvisl", electricalInstallation.getHomeLineA(), "x", electricalInstallation.getHomeLineB(), "+", electricalInstallation.getHomeLineC(), ("mm\u00b2"));
		String switchPanelModification = electricalInstallation.getSwitchPanelModification();
		prop.addWithValueDescription(FieldID.SWITCH_PANEL_MODIFICATION, "Aðaltafla/Mælatafla", switchPanelModification, DataConverter.lookup(InitialData.ADALTAFLA, switchPanelModification)).add(getElectronicProtectiveMeasures(electricalInstallation));
		return prop;
	}

	private void addHeadData(PropertyTree propertyTree, SimpleElectricalInstallation electricalInstallation) {
		propertyTree.add(getElectricianData(electricalInstallation)).add(getExternalData(electricalInstallation)).add(getWorkingplaceData(electricalInstallation)).add(getEnergyConsumer(electricalInstallation));
	}

	private Property getEnergyCompany(SimpleElectricalInstallation electricalInstallation) {
		Group energyCompany = electricalInstallation.getEnergyCompany();
		String energyCompanyString = null;
		if (energyCompany != null) {
			energyCompanyString = energyCompany.getName();
		}
		return new PropertyImpl(FieldID.ENERGY_COMPANY, "Orkuveitu", energyCompanyString);
	}

	private Property getExternalData(SimpleElectricalInstallation electricalInstallation) {
		PropertyTree propertyTree = new PropertyTree("externalData", "Ytri gögn");
		String externalProjectID = electricalInstallation.getExternalProjectID();
		String personInCharge = electricalInstallation.getPersonInCharge();
		propertyTree.add("externalProjectID", "Verknúmer", externalProjectID);
		propertyTree.add("personInCharge", "Ábyrgðarmaður", personInCharge);
		return propertyTree;
	}

	private Property getElectricianData(SimpleElectricalInstallation electricalInstallation) {
		PropertyTree propertyTree = new PropertyTree("electrician", "Rafverktaki");
		// user name
		User electrician = electricalInstallation.getElectrician();
		if (electrician == null) {
			return propertyTree;
		}
		String electricianName = null, electricianPersonalID = null;
		if (electrician != null) {
			electricianName = electrician.getName();
			electricianPersonalID = electrician.getPersonalID();
		}
		propertyTree.add("electricianName", "Löggiltur rafverktaki", electricianName).add("personalIDElectrician", "Kennitala", electricianPersonalID).add("electricianCompany", "Rafverktakafyrirtæki", electricalInstallation.getElectricianCompany());
		// user address
		PropertyTree addressProp = new PropertyTree("address", "Heimilisfang");
		UserBusiness userBusinessTemp = getUserBusiness();
		Address address = null;
		try {
			address = userBusinessTemp.getUsersCoAddress(electrician);
		}
		catch (RemoteException rme) {
			throw new RuntimeException(rme.getMessage());
		}
		String streetName = null, streetNumber = null, postalCodeName = null, postalCodeNumber = null;
		if (address != null) {
			streetName = address.getStreetAddress();
			streetNumber = address.getStreetNumber();
			PostalCode postalCode = address.getPostalCode();
			if (postalCode != null) {
				postalCodeName = postalCode.getName();
				postalCodeNumber = postalCode.getPostalCode();
			}
		}
		addressProp.add("street", "Gata", streetName).add("streetNumber", "Númer", streetNumber).add("postalCodeName", "Sveitarfélag", postalCodeName).add("postalCodeNumber", "Póstnúmer", postalCodeNumber).add(addressProp);
		// phones
		Phone homePhone = null;
		String homePhoneString = null;
		try {
			homePhone = userBusinessTemp.getUsersHomePhone(electrician);
			homePhoneString = (homePhone == null) ? null : homePhone.getNumber();

		}
		catch (RemoteException rme) {
			throw new RuntimeException(rme.getMessage());
		}
		catch (NoPhoneFoundException e) {
			homePhoneString = null;
		}
		propertyTree.add("homePhone", "Heimasimi", homePhoneString);
		Phone workPhone = null;
		String workPhoneString = null;
		try {
			workPhone = userBusinessTemp.getUsersWorkPhone(electrician);
			workPhoneString = (workPhone == null) ? null : workPhone.getNumber();

		}
		catch (RemoteException rme) {
			throw new RuntimeException(rme.getMessage());
		}
		catch (NoPhoneFoundException e) {
			workPhoneString = null;
		}
		propertyTree.add("workPhone", "Vinnusimi", workPhoneString);
		return propertyTree;
	}

	private Property getWorkingplaceData(SimpleElectricalInstallation electricalInstallation) {
		PropertyTree propertyTree = new PropertyTree(FieldID.WORKING_PLACE, "Veitustaður");
		// user name
		String name = null, use = null, comment = null, streetName = null, streetNumber = null, postalCodeNumber = null, postalCodeName = null, landRegisterMapNumber = null;
		RealEstate realEstate = electricalInstallation.getRealEstate();
		if (realEstate != null) {
			name = realEstate.getName();
			use = realEstate.getUse();
			comment = realEstate.getComment();
			landRegisterMapNumber = realEstate.getLandRegisterMapNumber();
			Street street = realEstate.getStreet();
			streetNumber = realEstate.getStreetNumber();
			if (street != null) {
				streetName = street.getName();
				PostalCode postalCode = street.getPostalCode();
				if (postalCode != null) {
					postalCodeNumber = postalCode.getPostalCode();
					postalCodeName = postalCode.getName();
				}
			}
		}
		propertyTree.add("name", "Veitustaður", name).add("use", "Not", use).add("comment", "Skýringar", comment).add("streetName", "Gata", streetName).add("streetNumber", "Götunúmer", streetNumber).add("postalCodeNumber", "Póstnúmer", postalCodeNumber).add("postalCodeName", "Sveitarfélag", postalCodeName).add("landRegisterMapNumber", "Landnúmer", landRegisterMapNumber);
		return propertyTree;
	}

	private Property getEnergyConsumer(SimpleElectricalInstallation electricalInstallation) {
		PropertyTree propertyTree = new PropertyTree("energyConsumer", "Orkukaupandi");
		propertyTree.add(FieldID.NAME, "Orkukaupandi", electricalInstallation.getEnergyConsumerName()).add(FieldID.ENERGY_CONSUMER_PERSONAL_ID, "Kennitala", electricalInstallation.getEnergyConsumerPersonalID()).add(FieldID.ENERGY_CONSUMER_HOME_PHONE, "Heimasími", electricalInstallation.getEnergyConsumerHomePhone()).add(FieldID.ENERGY_CONSUMER_WORK_PHONE, "Vinnusími", electricalInstallation.getEnergyConsumerWorkPhone()).add(FieldID.ENERGY_CONSUMER_EMAIL, "Netfang", electricalInstallation.getEnergyConsumerEmail());
		return propertyTree;
	}

	private Property getElectronicProtectiveMeasures(SimpleElectricalInstallation electricalInstallation) {
		PropertyTree propertyTree = new PropertyTree(FieldID.ELECTRONIC_PROTECTIVE_MEASURES, "Varnarráðstöfun");
		List list = electricalInstallation.getElectronicalProtectiveMeasures();
		if (list != null) {
			Iterator iterator = list.iterator();
			while (iterator.hasNext()) {
				String value = (String) iterator.next();
				propertyTree.addWithValueDescription("item", "", value, DataConverter.lookup(InitialData.VARNARRADSTOEFUN, value));
			}
		}
		return propertyTree;
	}

	// body application ++++++++++++++++++++++++++++++++++++++++++++++++++++

	private Property getBodyApplication(SimpleElectricalInstallation electricalInstallation) {
		MaelirList maelirList = electricalInstallation.getMaelirList(getElectricalInstallationBusiness());
		PropertyTree prop = new PropertyTree("body", "Þjónustubeiðni");
		prop.add(getApplication(electricalInstallation));
		prop.add(FieldID.PLACE_METER, "Staður mælis", maelirList.getStadurMaelir().getStadur());
		prop.add(FieldID.SWITCH_PANEL_NUMBER, "Númer töflu", electricalInstallation.getSwitchPanelNumber());
		prop.add(getVoltageSystem(electricalInstallation));
		addAllMaelir(prop, maelirList);
		prop.add("applicationRemarks", "Skýringar", electricalInstallation.getApplicationRemarks());
		return prop;
	}

	private Property getApplication(SimpleElectricalInstallation electricalInstallation) {
		PropertyTree propertyTree = new PropertyTree("applicationGroup", "Beiðni um");
		PropertyTree items = new PropertyTree(FieldID.APPLICATION, "Beiðni");
		List list = electricalInstallation.getApplication();
		if (list != null) {
			Iterator iterator = list.iterator();
			while (iterator.hasNext()) {
				String value = (String) iterator.next();
				items.addWithValueDescription("item", "", value, DataConverter.lookup(InitialData.BEIDNI, value));
			}
		}
		propertyTree.add(items);
		propertyTree.addWithUnit(FieldID.POWER, "Uppsett afl", electricalInstallation.getPower(), "kW");
		return propertyTree;
	}

	private Property getVoltageSystem(SimpleElectricalInstallation electricalInstallation) {
		PropertyTree propertyTree = new PropertyTree("voltageSystemGroup", "Spennukerfi");
		String voltageSystem = electricalInstallation.getVoltageSystem();
		propertyTree.addWithValueDescription(FieldID.VOLTAGE_SYSTEM, "Spennukerfi", voltageSystem, DataConverter.lookup(InitialData.SPENNUKERFI, voltageSystem)).add(FieldID.VOLTAGE_SYSTEM_OTHER, "Annað", electricalInstallation.getVoltageSystemOther());
		return propertyTree;
	}

	private void addAllMaelir(PropertyTree propertyTree, MaelirList maelirList) {
		propertyTree.add(getMaelirForContextShowNumber(InitialData.TAKA, "Taka mæli", maelirList)).add(getMaelirForContextShowNumber(InitialData.FYRIR, "Fyrir er", maelirList)).add(getMaelirForContextShowPhaseAmpere(InitialData.SETJA, "Setja mæli", maelirList)).add(getMaelirForContextShowNumber(InitialData.FLUTT_A, "Flutt á", maelirList)).add(getMaelirForContextShowNumber(InitialData.FLUTT_AF, "Flutt af", maelirList)).add(getMaelirForContextShowDevice(InitialData.HJALPATAEKI, "Setja hjálpatæki", maelirList)).add(getMaelirForContextShowAmpere(InitialData.STRAUMSPENNA, "Setja straumspennamæli", maelirList));
	}

	private Property getMaelirForContextShowNumber(String context, String description, MaelirList maelirList) {
		return getMaelirForContextShowNumberDevicePhaseAmpereRate(context, description, "", true, false, false, false, false, maelirList);
	}

	private Property getMaelirForContextShowDevice(String context, String description, MaelirList maelirList) {
		return getMaelirForContextShowNumberDevicePhaseAmpereRate(context, description, "", false, true, false, false, false, maelirList);
	}

	private Property getMaelirForContextShowAmpere(String context, String description, MaelirList maelirList) {
		return getMaelirForContextShowNumberDevicePhaseAmpereRate(context, description, "", false, false, false, true, true, maelirList);
	}

	private Property getMaelirForContextShowPhaseAmpere(String context, String description, MaelirList maelirList) {
		return getMaelirForContextShowNumberDevicePhaseAmpereRate(context, description, "Mælir", false, false, true, true, true, maelirList);
	}

	private Property getMaelirForContextShowNumberDevicePhaseAmpereRate(String context, String description, String itemName, boolean showNumber, boolean showDevice, boolean showPhase, boolean showAmpere, boolean showRate, MaelirList maelirList) {
		PropertyTree propertyTree = new PropertyTree(context, description);
		Map map = maelirList.getMaelirListMap();
		List list = (List) map.get(context);
		if (list == null) {
			return propertyTree;
		}
		Iterator iterator = list.iterator();
		while (iterator.hasNext()) {
			Maelir maelir = (Maelir) iterator.next();
			if (maelir.isValid()) {
				PropertyTree maelirData = new PropertyTree("item", itemName);
				if (showNumber) {
					maelirData.add(FieldID.NUMBER, "Mæli númer", maelir.getNumer());
				}
				if (showDevice) {
					maelirData.add(FieldID.DEVICE, "Hjálpatæki", maelir.getHjalpataeki());
				}
				if (showPhase) {
					String fasa = maelir.getFasa();
					maelirData.addWithValueDescription(FieldID.PHASE, "Fasa", fasa, DataConverter.lookup(InitialData.MAELI, fasa));
				}
				if (showAmpere) {
					maelirData.addWithUnit(FieldID.AMPERE, "Stærð", maelir.getAmpere(), AMPERE);
				}
				if (showRate) {
					maelirData.add(FieldID.RATE, "Taxti", maelir.getTaxti());
				}
				propertyTree.add(maelirData);
			}
		}
		return propertyTree;
	}

	// report rendering -------------------------------------------------------------------------------------------------------------------------------------------
	// head report ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	private Property getHeadReport(SimpleElectricalInstallation electricalInstallation) {
		PropertyTree prop = new PropertyTree("head", "");
		addHeadData(prop, electricalInstallation);
		prop.add(getAnnouncement(electricalInstallation));
		String typeInReport = electricalInstallation.getTypeInReport();
		prop.addWithValueDescription(FieldID.TYPE_IN_REPORT, "Notkun húsnæðis", typeInReport, DataConverter.lookup(InitialData.NOTKUNARFLOKKUR, typeInReport));
		String announcementRemarks = electricalInstallation.getAnnouncementRemarks();
		prop.add("announcementRemarks", "Nánari skýring á því sem tilkynnt er", announcementRemarks);
		return prop;
	}

	private Property getAnnouncement(SimpleElectricalInstallation electricalInstallation) {
		PropertyTree propertyTree = new PropertyTree(FieldID.ANNOUNCEMENT_GROUP, "Tilkynnt er");
		String announcement = electricalInstallation.getAnnouncement();
		propertyTree.addWithValueDescription(FieldID.ANNOUNCEMENT, "Tilkynnt", announcement, DataConverter.lookup(InitialData.TILKYNNT, announcement));
		propertyTree.add(FieldID.ANNOUNCEMENT_OTHER, "Annað", electricalInstallation.getAnnouncementOther());
		return propertyTree;
	}

	// body report ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	private Property getBodyReport(SimpleElectricalInstallation electricalInstallation) {
		PropertyTree propertyTree = new PropertyTree("bodyReport", "Upplýsingar um neysluveitu");
		propertyTree.add(getVoltageSystemInReport(electricalInstallation)).add(getElectronicProtectiveMeasuresInReport(electricalInstallation)).add(getGrounding(electricalInstallation));
		MaelirList maelirList = electricalInstallation.getMaelirList(getElectricalInstallationBusiness());
		propertyTree.add(getMeter(maelirList)).add("remarks", "Skýringar", electricalInstallation.getRemarks());
		return propertyTree;
	}

	private Property getVoltageSystemInReport(SimpleElectricalInstallation electricalInstallation) {
		PropertyTree propertyTree = new PropertyTree(FieldID.VOLTAGE_SYSTEM_GROUP_IN_REPORT, "Veitukerfi");
		String voltageSystemInReport = electricalInstallation.getVoltageSystemInReport();
		propertyTree.addWithValueDescription(FieldID.VOLTAGE_SYSTEM_IN_REPORT, "Veitukerfi", voltageSystemInReport, DataConverter.lookup(InitialData.SPENNUKERFI, voltageSystemInReport));
		propertyTree.add(FieldID.VOLTAGE_SYSTEM_OTHER_IN_REPORT, "Annað", electricalInstallation.getVoltageSystemOtherInReport());
		return propertyTree;
	}

	private Property getElectronicProtectiveMeasuresInReport(SimpleElectricalInstallation electricalInstallation) {
		PropertyTree propertyTree = new PropertyTree(FieldID.ELECTRONIC_PROTECTIVE_MEASURES_IN_REPORT, "Varnarráðstöfun");
		List list = electricalInstallation.getElectronicalProtectiveMeasuresInReport();
		if (list != null) {
			Iterator iterator = list.iterator();
			while (iterator.hasNext()) {
				String value = (String) iterator.next();
				propertyTree.addWithValueDescription("item", "", value, DataConverter.lookup(InitialData.VARNARRADSTOEFUN, value));
			}
		}
		return propertyTree;
	}

	private Property getGrounding(SimpleElectricalInstallation electricalInstallation) {
		PropertyTree propTree = new PropertyTree("groundingGroup", "Jarðskaut/spennujöfnun");
		PropertyTree propertyTree = new PropertyTree("grounding", "Jarðskaut/spennujöfnun");
		List list = electricalInstallation.getGrounding();
		if (list != null) {
			Iterator iterator = list.iterator();
			while (iterator.hasNext()) {
				String value = (String) iterator.next();
				propertyTree.addWithValueDescription("item", "", value, DataConverter.lookup(InitialData.JARDSKAUT, value));
			}
		}
		propTree.add(propertyTree);
		propTree.add("groundingOther", "Annað", electricalInstallation.getGroundingOther());
		return propertyTree;
	}

	private Property getMeter(MaelirList maelirList) {
		PropertyTree propertyTree = new PropertyTree("meterGroup", "Mælir");
		Maelir maelir = maelirList.getStadurMaelir();
		propertyTree.add("meterNumber", "Númer mælis rafveitu", maelir.getNumer()).add("meterPlace", "Staður mælis", maelir.getStadur());
		return propertyTree;
	}

	// bottom report ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	private Property getBottomReport(SimpleElectricalInstallation electricalInstallation) {
		PropertyTree propertyTree = new PropertyTree("bottomReport", "Mælingar og prófanir");
		propertyTree.add(getSwitchPanel(electricalInstallation)).add(getInsulation(electricalInstallation)).add(getShortCircuit(electricalInstallation)).add(getVoltage(electricalInstallation)).add(isFuseAttached(electricalInstallation)).add("measurementRemarks", "Skýringar", electricalInstallation.getMeasurementRemarks());
		return propertyTree;
	}

	private Property getSwitchPanel(SimpleElectricalInstallation electricalInstallation) {
		PropertyTree propertyTree = new PropertyTree("switchPanel", "");
		propertyTree.addWithUnit("switchPanelResistence", "Hringrásarviðnám aðal-/greinitöflu", electricalInstallation.getSwitchPanelResistence(), OHM).addWithUnit("switchPanelAmpere", "Skammhlaupsstraumur aðal-/greinitöflu", electricalInstallation.getSwitchPanelAmpere(), AMPERE);
		return propertyTree;
	}

	private Property getInsulation(SimpleElectricalInstallation electricalInstallation) {
		PropertyTree propertyTree = new PropertyTree("insulation", "");
		propertyTree.addWithUnit("insulationResistence", "Einangrun neysluveitu", electricalInstallation.getInsulationResistence(), MEGA_OHM).addWithUnit("groundingResistence", "Hringrásarviðnámsmæling jarðskauts/sp.jöfnunar", electricalInstallation.getGroundingResistence(), AMPERE);
		return propertyTree;
	}

	private Property getShortCircuit(SimpleElectricalInstallation electricalInstallation) {
		PropertyTree propertyTree = new PropertyTree("shortCircuit", "");
		propertyTree.addWithUnit("ShortCircuitAmpere", "Skammhlaupsstraumur neysluveitu", electricalInstallation.getShortCircuitAmpere(), AMPERE).addWithUnit("resistence", "Hringrásarviðnámsmæling neysluveitu", electricalInstallation.getResistence(), OHM);
		return propertyTree;
	}

	private Property getVoltage(SimpleElectricalInstallation electricalInstallation) {
		PropertyTree propertyTree = new PropertyTree("voltage", "");
		propertyTree.addWithUnit("voltagePhaseN", "Mæld spenna Fasi-N", electricalInstallation.getVoltagePhaseN(), VOLT).addWithUnit("voltagePhasePhase", "Mæld spenna Fasi-Fasi", electricalInstallation.getVoltagePhasePhase(), VOLT);
		return propertyTree;
	}

	private Property isFuseAttached(SimpleElectricalInstallation electricalInstallation) {
		PropertyTree propertyTree = new PropertyTree(FieldID.FUSE, "");
		Boolean fuseAttached = electricalInstallation.isFuseAttached();
		String key = null;
		String description = null;
		if (fuseAttached != null) {
			key = fuseAttached.booleanValue() ? InitialData.LEKASTRAUMSROFI_I_LAGI_KEY : InitialData.LEKASTRAUMSROFI_EKKI_TIL_STADAR_KEY;
			description = DataConverter.lookup(InitialData.LEKASTRAUMSROFI, key);
		}
		propertyTree.addWithValueDescription("fuseAttached", "Lekastraumsrofi", key, description).addWithUnit("fuseVoltage", "", electricalInstallation.getFuseVoltage(), VOLT).addWithUnit("fuseTime", "", electricalInstallation.getFuseTime(), MILLI_SECONDS);
		return propertyTree;
	}

	private UserBusiness getUserBusiness() {
		if (userBusiness == null) {
			userBusiness = (UserBusiness) getServiceBean(UserBusiness.class);
		}
		return userBusiness;
	}

	private ElectricalInstallationBusiness getElectricalInstallationBusiness() {
		if (electricalInstallationBusiness == null) {
			electricalInstallationBusiness = (ElectricalInstallationBusiness) getServiceBean(ElectricalInstallationBusiness.class);
		}
		return electricalInstallationBusiness;
	}

	private IBOService getServiceBean(Class serviceClass) {
		IBOService service = null;
		try {
			service = IBOLookup.getServiceInstance(getIWApplicationContext(), serviceClass);
		}
		catch (RemoteException rme) {
			throw new RuntimeException(rme.getMessage());
		}
		return service;
	}

}
