package is.idega.nest.rafverk.importer;

import is.idega.nest.rafverk.bean.InitialData;
import is.idega.nest.rafverk.business.ElectricalInstallationState;
import is.idega.nest.rafverk.domain.ElectricalInstallation;
import is.idega.nest.rafverk.domain.ElectricalInstallationHome;
import is.postur.Gata;
import is.postur.Gotuskra;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.idega.block.importer.business.ImportFileHandler;
import com.idega.block.importer.data.ImportFile;
import com.idega.business.IBOLookupException;
import com.idega.business.IBOServiceBean;
import com.idega.core.location.data.PostalCode;
import com.idega.core.location.data.PostalCodeHome;
import com.idega.core.location.data.RealEstate;
import com.idega.core.location.data.RealEstateHome;
import com.idega.core.location.data.Street;
import com.idega.core.location.data.StreetHome;
import com.idega.user.business.GroupBusiness;
import com.idega.user.business.UserBusiness;
import com.idega.user.data.Gender;
import com.idega.user.data.Group;
import com.idega.user.data.GroupHome;
import com.idega.user.data.User;
import com.idega.user.data.UserHome;
import com.idega.util.IWTimestamp;

public class ElectricalInstallationsImportFileHandlerBean extends
		IBOServiceBean implements ElectricalInstallationsImportFileHandler,ImportFileHandler{

	//private ImportFile importFile;
	//private List failedRecords=new ArrayList();
	private ArrayList failedRecordList = new ArrayList();
	private ArrayList valueList;
	private ImportFile file;
	private Gotuskra gotuskra;
	private Map electricCompaniesMap;
	/*
	//Rafverktokur:
	public final static int COLUMN_ID = 0;//ID
	public final static int COLUMN_LOCATION = 1;//Veitustadur
	public final static int COLUMN_SSN=2;//Kennitala
	public final static int COLUMN_DATE_RECEIVED=3;//Dags mott
	public final static int COLUMN_EST_CLOSED=4;//Aaetlud verklok
	public final static int COLUMN_INSTALLATION_NUMBER=5;//Veitunumer
	public final static int COLUMN_COMMENTS=6;//Athugasemdir
	public final static int COLUMN_STATUS=7;//Stada
	public final static int COLUMN_ELECTRIC_COMPANY=8;//Rafveita
	public final static int COLUMN_OWNER=9;//Eigandi
	public final static int COLUMN_OWNER_SSN=10;//Kennitala eiganda
	public final static int COLUMN_ANNOUNCEMENT_TYPE=11;//Tegund tilkynningar
	public final static int COLUMN_POSTAL_CODE=12;//Postnumer
	public final static int COLUMN_REGISTRATIONDAY=13;//Skraningardagur ls
	public final static int COLUMN_USAGE_TYPE=14;//Neysluv_Notkunfl
	public final static int COLUMN_DOCUMENT_ID=15;//DOCUMENT_ID
	*/
	//Tilkynningar:
	public final static int COLUMN_ID = 0;//ID
	public final static int COLUMN_SSN = 1;//Kennitala
	public final static int COLUMN_NAME = 2;//Nafn verks
	public final static int COLUMN_INSTALLATION_NUMBER = 3;//Veitunumer
	public final static int COLUMN_ELECTRICAL_USER = 4;//Notandi
	public final static int COLUMN_ANNOUNCEMENT=5;//Upplysingar
	public final static int COLUMN_TOTALLYANNOUNCED=6;//TilkynntAlls
	public final static int COLUMN_RISKCATEGORY=7;//Ahaettufl
	public final static int COLUMN_ELECTRIC_COMPANY=8;//Rafveita
	public final static int COLUMN_DATE_REGISTERED=9;//DagsSkraning
	public final static int COLUMN_INSPECTION_SAMPLE=10;//Urtak
	public final static int COLUMN_BILL=11;//Reikningur
	public final static int COLUMN_INSPECTION_AGENCY=12;//Skodunarstofa
	public final static int COLUMN_DATE_OF_DELIVERY1=13;//DagsAfhendingar
	public final static int COLUMN_DATE_OF_INSPECTION=14;//DagsSkodunar
	public final static int COLUMN_DATE_OF_DELIVERY2=15;//DagSkiladags
	public final static int COLUMN_COMMENTS=16;//Athugasemd
	public final static int COLUMN_POSTAL_CODE=17;//Postnumer
	public final static int COLUMN_DATE_RECEIVED=18;//Dags mottoku
	public final static int COLUMN_STATUS=19;//Stada
	public final static int COLUMN_DATE_RECEIVED_EI=20;//Dags mottoku rafverktokur
	public final static int COLUMN_INSPECTION_OVER=21;//Skodun lokid
	public final static int COLUMN_OWNER_SSN=22;//Eigandi_Kt
	public final static int COLUMN_ONWER_PHONE=23;//Eigandi_Simi
	public final static int COLUMN_ANNOUNCED_TYPE=24;//Tilk_Notkun
	public final static int COLUMN_ANNOUNCED_OTHER=25;//Tilk_Notkun_Annad
	public final static int COLUMN_ANNOUNCED_DESCRIPTION=26;//Tilk_Nanar
	public final static int COLUMN_ELECTRIC_SYSTEM=27;//Veita_Kerfi
	public final static int COLUMN_ELECTRIC_SYSTEM_OTHER=28;//Veita_Kerfi_Annad
	public final static int COLUMN_GROUNDING=29;//Veita_Spenna
	public final static int COLUMN_GROUNDING_OTHER=30;//Veita_Spenna_Annad
	public final static int COLUMN_PROTECTION=31;//Veita_Varnar
	public final static int COLUMN_PROTECTION_OTHER=32;//Veita_Varnar_Annad
	public final static int COLUMN_MEASUREMENTS_VOLTAGE_PHASE_N=33;//Mael_SpFN
	public final static int COLUMN_MEASUREMENTS_VOLTAGE_PHASE_PHASE=34;//Mael_SpFF
	public final static int COLUMN_MEASUREMENTS_CIRCULAR_RESISTANCE=35;//Mael_Hrv_Rafv
	public final static int COLUMN_MEASUREMENTS_SHORT_CIRCUIT_CURRENT=36;//Mael_Skamm_Rafv
	public final static int COLUMN_MEASUREMENTS_GROUNDING_RESISTANCE=37;//Mael_Vidnam_Skauts
	public final static int COLUMN_MEASUREMENTS_GROUNDING_SC=38;//Mael_Skamm_Skauts
	public final static int COLUMN_MEASUREMENTS_CRESISTANCE_SWITCH_PANEL=39;//Mael_Hrv_GreinTafla
	public final static int COLUMN_MEASUREMENTS_SHORT_CIRCUIT_CURRENT_SWITCH_PANEL=40;//Mael_Skamm_Grein
	public final static int COLUMN_MEASUREMENTS_CRESISTANCE_ELECTRICAL_LINE=41;//Mael_Hrv_NeysluVeita
	public final static int COLUMN_MEASUREMENTS_SHORT_CIRCUIT_CURRENT_ELECTRICAL_LINE=42;//Mael_Skamm_Veita
	public final static int COLUMN_MEASUREMENTS_INSULATION=43;//Mael_Einangrun
	public final static int COLUMN_MEASUREMENTS_VOLTAGE_H=44;//Mael_Spennuh
	public final static int COLUMN_MEASUREMENTS_FUSE_TIME=45;//Mael_Utleysitimi
	public final static int COLUMN_MEASUREMENTS_FUSE_COMMENT=46;//Mael_Utleysing
	public final static int COLUMN_MEASUREMENTS_REMARKS=47;//Mael_AdrarSkyr
	public final static int COLUMN_FORM_DOCUMENT_ID=48;//DOCUMENT_ID
	public final static int COLUMN_STATUS2=49;//STATUS
	//See record with id=29890
	
	public List getFailedRecords() throws RemoteException {
		return failedRecordList;
	}

	public boolean handleRecords() throws RemoteException {
		int count = 0;
		String item;
		while (!(item = (String) this.file.getNextRecord()).equals("")) {
			count++;
			try {
				if(count%500==0){
					System.out.println("ElectricalInstallationsImportFileHandlerBean: Processing record nr.: "+count+" from file "+this.file.getFile().getName());
				}
				if (!processRecord(item)) {
					this.failedRecordList.add(item);
				}
			} catch (Exception e) {
				e.printStackTrace();
				//return false;
			}
		}
		
		return true;
	}

	public void setImportFile(ImportFile file) throws RemoteException {
		this.file=file;
	}

	public void setRootGroup(Group rootGroup) throws RemoteException {
		// TODO: Do wee need to implement this?	
	}
	
	private boolean processRecord(String record) throws RemoteException,
	CreateException {
		this.valueList = this.file.getValuesFromRecordString(record);

		boolean success = storeElectricalInstallationRecord();

		this.valueList = null;

		return success;
	}
	
	protected boolean storeElectricalInstallationRecord() throws RemoteException,
	CreateException {
		try{
			// variables
			String id = getProperty(COLUMN_ID);//ID
			String ssn = getProperty(COLUMN_SSN);//Kennitala

			if(ssn==null){
				throw new RuntimeException("Kennitala is null (Kennitala rafverktaka ekki sett í færslu)");
			}
				
			if(ssn.endsWith(",00")){
				ssn=ssn.substring(0,ssn.length()-3);
			}
			
			if(ssn.length()!=10){
				throw new RuntimeException("Kennitala is of invalid length, should be 10 digits (Kennitala rafverktaka ekki rétt)");
			}

			String name = getProperty(COLUMN_NAME);//Nafn verks
			String installationNumber = getProperty(COLUMN_INSTALLATION_NUMBER);//Veitunumer
			String userName = getProperty(COLUMN_ELECTRICAL_USER);//Notandi
			String announcement = getProperty(COLUMN_ANNOUNCEMENT);//Upplysingar
			String totallyAnnounced = getProperty(COLUMN_TOTALLYANNOUNCED);//TilkynntAlls
			String riskCategory = getProperty(COLUMN_RISKCATEGORY);//Ahaettufl
			String electricCompanyId = getProperty(COLUMN_ELECTRIC_COMPANY);//Rafveita
			String dateRegistered = getProperty(COLUMN_DATE_REGISTERED);//DagsSkraning
			String inspectionSample = getProperty(COLUMN_INSPECTION_SAMPLE);//Urtak
			String bill = getProperty(COLUMN_BILL);//Reikningur
			String inspectionAgencyId = getProperty(COLUMN_INSPECTION_AGENCY);//Skodunarstofa
			String dateOfDelivery1 = getProperty(COLUMN_DATE_OF_DELIVERY1);//DagsAfhendingar
			String dateOfInspection = getProperty(COLUMN_DATE_OF_INSPECTION);//DagsSkodunar
			String dateOfDelivery2 = getProperty(COLUMN_DATE_OF_DELIVERY2);//DagSkiladags
			String comments = getProperty(COLUMN_COMMENTS);//Athugasemd
			String postalCode = getProperty(COLUMN_POSTAL_CODE);//Postnumer
			String dateReceived = getProperty(COLUMN_DATE_RECEIVED);//Dags mottoku
			String status = getProperty(COLUMN_STATUS);//Stada
			String dateReceivedEI = getProperty(COLUMN_DATE_RECEIVED_EI);//Dags mottoku rafverktokur
			String inspectionOver = getProperty(COLUMN_INSPECTION_OVER);//Skodun lokid
			String ownerSSN = getProperty(COLUMN_OWNER_SSN);//Eigandi_Kt
			String ownerPhone = getProperty(COLUMN_ONWER_PHONE);//Eigandi_Simi
			String announcedType = getProperty(COLUMN_ANNOUNCED_TYPE);//Tilk_Notkun
			String announcedTypeOther = getProperty(COLUMN_ANNOUNCED_OTHER);//Tilk_Notkun_Annad
			String announcedDescription = getProperty(COLUMN_ANNOUNCED_DESCRIPTION);//Tilk_Nanar
			String electricSystem = getProperty(COLUMN_ELECTRIC_SYSTEM);//Veita_Kerfi
			String electricSystemOther = getProperty(COLUMN_ELECTRIC_SYSTEM_OTHER);//Veita_Kerfi_Annad
			String grounding = getProperty(COLUMN_GROUNDING);//Veita_Spenna
			String groundingOther = getProperty(COLUMN_GROUNDING_OTHER);//Veita_Spenna_Annadófa
			String protection = getProperty(COLUMN_PROTECTION);//Veita_Varnar
			String protectionOther = getProperty(COLUMN_PROTECTION_OTHER);//Veita_Varnar_Annad
			String measurementsVoltagePhaseN = getProperty(COLUMN_MEASUREMENTS_VOLTAGE_PHASE_N);//Mael_SpFN
			String measurementsVoltagePhasePhase = getProperty(COLUMN_MEASUREMENTS_VOLTAGE_PHASE_PHASE);//Mael_SpFF
			String measurementsCircularResistance = getProperty(COLUMN_MEASUREMENTS_CIRCULAR_RESISTANCE);//Mael_Hrv_Rafv
			String measurementsShortCircuitCurrent = getProperty(COLUMN_MEASUREMENTS_SHORT_CIRCUIT_CURRENT);//Mael_Skamm_Rafv
			String measurementsGroundingResistance = getProperty(COLUMN_MEASUREMENTS_GROUNDING_RESISTANCE);//Mael_Vidnam_Skauts
			String measurementsGroundingCS = getProperty(COLUMN_MEASUREMENTS_GROUNDING_SC);//Mael_Skamm_Skauts
			String measurementsCResistanceSwitchPanel = getProperty(COLUMN_MEASUREMENTS_CRESISTANCE_SWITCH_PANEL);//Mael_Hrv_GreinTafla
			String measurementsShortCircuitCurrentSwitchPanel = getProperty(COLUMN_MEASUREMENTS_SHORT_CIRCUIT_CURRENT_SWITCH_PANEL);//Mael_Skamm_Grein
			String measurementsCResistanceElectricalLine = getProperty(COLUMN_MEASUREMENTS_CRESISTANCE_ELECTRICAL_LINE);//Mael_Hrv_NeysluVeita
			String measurementsShortCircuitCurrentElectricalLine = getProperty(COLUMN_MEASUREMENTS_SHORT_CIRCUIT_CURRENT_ELECTRICAL_LINE);//Mael_Skamm_Veita
			String measurementsInsulation = getProperty(COLUMN_MEASUREMENTS_INSULATION);//Mael_Einangrun
			String measurementsVoltage = getProperty(COLUMN_MEASUREMENTS_VOLTAGE_H);//Mael_Spennuh
			String measurementsFuseTime = getProperty(COLUMN_MEASUREMENTS_FUSE_TIME);//Mael_Utleysitimi
			String measurementsFuseComments = getProperty(COLUMN_MEASUREMENTS_FUSE_COMMENT);//Mael_Utleysing
			String measurementsRemarks = getProperty(COLUMN_MEASUREMENTS_REMARKS);//Mael_AdrarSkyr
			String formDocumentId = getProperty(COLUMN_FORM_DOCUMENT_ID);//DOCUMENT_ID
			String status2 = getProperty(COLUMN_STATUS2);//STATUS
			
			
			if(postalCode==null){
				//System.out.println("PostalCode is null for record with id:"+id);
			}
			
			//UserBusiness business = getUserBusiness();
			//UserHome userHome = getUserHome();
	
			User electrician = getElectrician(ssn);
			
			//ElectricalInstallationHome elHome = getElectricalInstallationHome();
			ElectricalInstallation installation = getElecticalInstallation(id);
			installation.setElectrician(electrician);
			installation.setOwner(electrician);
			
			installation.setExternalId(id);
			
			RealEstate realEstate = installation.getRealEstate();
			if(realEstate==null){
				RealEstateHome realEstateHome = getRealEstateHome();
				realEstate = realEstateHome.create();
				installation.setRealEstate(realEstate);
			}
			realEstate.setName(name);
		
			Street street = realEstate.getStreet();
			if(street==null){
				if(postalCode!=null){
					street = getStreet(name,postalCode);
					realEstate.setStreet(street);
				}
			}
			//street.store();
			realEstate.store();
			
			installation.setEnergyConsumerName(userName);
			installation.setEnergyConsumerPersonalID(ownerSSN);
			installation.setEnergyConsumerHomePhone(ownerPhone);
			
			Group energyCompany = getElectricCompany(electricCompanyId);
			installation.setEnergyCompany(energyCompany);
			
			String announcedTypeParsed = getAnnouncedTypeParsed(announcedType,announcedTypeOther);
			installation.setType(announcedTypeParsed);
			installation.setTypeInReport(announcedTypeParsed);
			
			installation.setAnnouncementRemarks(announcedDescription);
			
			Timestamp dateRegisteredParsed = getDateRegisteredParsed(dateRegistered);
			installation.setCreated(dateRegisteredParsed);
			
			try{
				String announcementParsed = getAnnouncementParsed(announcement);
				installation.setAnnouncement(announcementParsed);
			}
			catch(RuntimeException e){
				installation.setAnnouncement(InitialData.TILKYNNT_ANNAD);
				installation.setAnnouncementOther(announcement);
			}
			
			String electricSystemParsed = getElectricSystemParsed(electricSystem);
			installation.setVoltageSystem(electricSystemParsed);
			
			installation.setVoltageSystemOther(electricSystemOther);
			
			List protectionParsed = getProtectionParsed(protection,protectionOther);
			installation.setElectronicalProtectiveMeasuresInReport(protectionParsed);
			installation.setElectronicalProtectiveMeasures(protectionParsed);
			

			List groundingParsed = parseGrounding(grounding);
			installation.setGrounding(groundingParsed);

			installation.setGroundingOther(groundingOther);	
			
			installation.setVoltagePhaseN(measurementsVoltagePhaseN);
			installation.setVoltagePhasePhase(measurementsVoltagePhasePhase);
			
			installation.setShortCircuitAmpere(measurementsShortCircuitCurrentElectricalLine);
			installation.setResistence(measurementsCResistanceElectricalLine);
			
			installation.setSwitchPanelResistence(measurementsCResistanceSwitchPanel);
			installation.setSwitchPanelAmpere(measurementsShortCircuitCurrentSwitchPanel);
			
			installation.setInsulationResistence(measurementsInsulation);
			installation.setGroundingResistence(measurementsGroundingResistance);
			
			Boolean fuseTested = getMeasurementsFuseCommentsParsed(measurementsFuseComments);
			installation.setFuseAttached(fuseTested);
			installation.setFuseTime(measurementsFuseTime);
			//TODO: setFuseVoltage() should probably be setFuseCurrent() - not set because not found in import
			
			boolean inspectionSampleParsed = getInspectionSampleParsed(inspectionSample);
			installation.setInspectionSample(inspectionSampleParsed);
			int inspectionAgencyIdParsed = getInspectionAgencyIdParsed(inspectionAgencyId);
			if(inspectionAgencyIdParsed>0){
				installation.setInspectionAgencyId(inspectionAgencyIdParsed);
			}
			Timestamp dateOfInspectionParsed = getDateOfInspectionParsed(dateOfInspection);
			installation.setDateOfInspection(dateOfInspectionParsed);
			int formDocumentIdParsed = getFormDocumentIdParsed(formDocumentId);
			if(formDocumentIdParsed>0){
				installation.setFormDocumentId(formDocumentIdParsed);
			}
			
			installation.setMeasurementRemarks(measurementsRemarks);
			
			ElectricalInstallationState stateManager = new ElectricalInstallationState(getIWApplicationContext());
			stateManager.setStatus(installation, ElectricalInstallationState.ELDRI_TILKYNNING);
			
			installation.store();
			
			
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
	}


	private int getFormDocumentIdParsed(String formDocumentId) {
		if(formDocumentId!=null){
			try{
				return Integer.parseInt(formDocumentId);
			}
			catch(NumberFormatException nfe){}
		}
		return 0;
	}

	private Timestamp getDateOfInspectionParsed(String dateOfInspection) {
		return parseDateString(dateOfInspection);
	}

	private int getInspectionAgencyIdParsed(String inspectionAgencyId) {
		if(inspectionAgencyId!=null){
			try{
				return Integer.parseInt(inspectionAgencyId);
			}
			catch(NumberFormatException nfe){}
		}
		return 0;
	}

	private boolean getInspectionSampleParsed(String inspectionSample) {
		if(inspectionSample!=null){
			if(inspectionSample.equals("0")){
				return false;
			}
			else if(inspectionSample.equals("1")){
				return true;
			}
		}
		return false;
	}

	private Boolean getMeasurementsFuseCommentsParsed(
			String measurementsFuseComments) {
		if(measurementsFuseComments!=null){
			if(measurementsFuseComments.equals("Útleysing í lagi")){
				return Boolean.TRUE;
			}
		}
		return null;
	}

	private List parseGrounding(String grounding) {
		List returnList=new ArrayList();
		if(grounding!=null){
			String oldSeparator = ";";
			
			StringTokenizer tokenizer = new StringTokenizer(grounding,oldSeparator);
			while(tokenizer.hasMoreElements()){
				String element = tokenizer.nextToken();
				element = element.trim();
				if(element.equalsIgnoreCase("Vatnspípukerfi")){
					returnList.add(InitialData.JARDSKAUT_VATNSPIPUKERFI);
				}
				else if(element.equalsIgnoreCase("Sökkulsskaut")){
					returnList.add(InitialData.JARDSKAUT_SOEKKULSKAUT);
				}
				else if(element.equalsIgnoreCase("Sérskaut")){
					returnList.add(InitialData.JARDSKAUT_SERSKAUT);
				}
				else if(element.startsWith("Annað")){
					//We can expect this to happen, the there should be a value in the groundingOther attribute
					//returnList.add(InitialData.JARDSKAUT_SERSKAUT);
				}
				else{
					throw new RuntimeException("Unexpected value: "+element);
				}
				
			}
		}
		return returnList;

	}
	
	private Timestamp getDateRegisteredParsed(String dateRegistered) {
		return parseDateString(dateRegistered);
	}
	
	private Timestamp parseDateString(String dateRegistered) {
		
		if(dateRegistered!=null&&!dateRegistered.equals("")){
			
			String sDay = dateRegistered.substring(0,dateRegistered.indexOf("."));
			dateRegistered= dateRegistered.substring(dateRegistered.indexOf(".")+1,dateRegistered.length());
			
			String sMonth = dateRegistered.substring(0,dateRegistered.indexOf("."));
			dateRegistered= dateRegistered.substring(dateRegistered.indexOf(".")+1,dateRegistered.length());
			
			String sYear = dateRegistered.substring(0,dateRegistered.indexOf(" "));
			dateRegistered= dateRegistered.substring(dateRegistered.indexOf(".")+1,dateRegistered.length());
			
			IWTimestamp timestamp = new IWTimestamp(Integer.parseInt(sDay),Integer.parseInt(sMonth),Integer.parseInt(sYear));
			return timestamp.getTimestamp();
		}
		return null;
	}

	private String getAnnouncedTypeParsed(String announcedType, String announcedTypeOther) {
		if(announcedType!=null){
			if(announcedType.indexOf("Bráðabirgðanotkun")!=-1){
				return InitialData.NOTKUNARFLOKKUR_BRADABIRGDANOTKUN;
			}
			else if(announcedType.indexOf("Fiskiðnaður")!=-1){
				return InitialData.NOTKUNARFLOKKUR_FISKIDNADUR;
			}
			else if(announcedType.indexOf("Landb./Garðyrkja")!=-1){
				return InitialData.NOTKUNARFLOKKUR_GARDYRKJA;
			}
			else if(announcedType.indexOf("Íbúð")!=-1){
				return InitialData.NOTKUNARFLOKKUR_IBUDARHUSNAEDI;
			}
			else if(announcedType.indexOf("Iðnaður")!=-1){
				return InitialData.NOTKUNARFLOKKUR_IDNADUR;
			}
			else if(announcedType.indexOf("Opinber stofnun")!=-1){
				return InitialData.NOTKUNARFLOKKUR_OPINBER_STOFNUN;
			}
			else if(announcedType.indexOf("Sumarhús")!=-1){
				return InitialData.NOTKUNARFLOKKUR_SUMARHUS;
			}
			else if(announcedType.indexOf("Þjónusta")!=-1){
				return InitialData.NOTKUNARFLOKKUR_THJONUSTA;
			}
			else if(announcedType.indexOf("Annað")!=-1){
				return announcedTypeOther;
			}
			else{
				throw new RuntimeException("Unexpected value: "+announcedType);
			}
		}
		return null;
	}

	private List getProtectionParsed(String protection, String protectionOther) {
		List returnList=new ArrayList();
		if(protection!=null){
			String oldSeparator = ";";
			
			StringTokenizer tokenizer = new StringTokenizer(protection,oldSeparator);
			while(tokenizer.hasMoreElements()){
				String element = tokenizer.nextToken();
				element = element.trim();

				if(element.equalsIgnoreCase("Núllun")){
					returnList.add(InitialData.VARNARRADSTOEFUN_NULLUN);
				}
				else if(element.equalsIgnoreCase("Lekastraumsvörn")){
					returnList.add(InitialData.VARNARRADSTOEFUN_LEKASTRAUMSROFVOERN);
				}
				else if(element.equalsIgnoreCase("Um sérskaut")){
					returnList.add(InitialData.VARNARRADSTOEFUN_UM_SERSKAUT);
				}
				else if(element.equalsIgnoreCase("Önnur")){
					returnList.add(InitialData.VARNARRADSTOEFUN_OENNUR);
				}
				else if(element.startsWith("Önnur")){
					//returnList.add(InitialData.VARNARRADSTOEFUN_OENNUR);
					returnList.add(protectionOther);
				}
				else if(element.startsWith("Hlífðar")){
					returnList.add("Hlífðareinangrun");
				}
				else if(element.equalsIgnoreCase("Varnarjarðt. um sérskaut")){
					returnList.add("Varnarjarðt. um sérskaut");
				}
				else{
					throw new RuntimeException("Unexpected value for protection: "+element);
				}
				
			}
		}
		return returnList;
	}

	private String getElectricSystemParsed(String electricSystem) {
		if(electricSystem!=null){
			if(electricSystem.indexOf("1N~230V")!=-1){
				//TODO: change IN to 1N
				return InitialData.SPENNUKERFI_IN_230V;
			}
			else if(electricSystem.indexOf("3~230V")!=-1){
				return InitialData.SPENNUKERFI_3_230V;
			}
			else if(electricSystem.indexOf("2N~460/230V")!=-1){
				return InitialData.SPENNUKERFI_2N_460_230V;
			}
			else if(electricSystem.indexOf("3N~400/230V")!=-1){
				return InitialData.SPENNUKERFI_3N_400_230V;
			}
			else{
				throw new RuntimeException("Unexpected value: "+electricSystem);
			}
		}
		return null;
	}

	private String getAnnouncementParsed(String informationText) {
		if(informationText!=null){
			if(informationText.indexOf("Nýlögn")!=-1){
				return InitialData.TILKYNNT_NYLOEGN;
			}
			else if(informationText.indexOf("Breyting")!=-1){
				return InitialData.TILKYNNT_BREYING;
			}
			else if(informationText.indexOf("Viðbót")!=-1){
				return InitialData.TILKYNNT_VIDBOT;
			}
			else{
				//TODO: Maybe set other values in the announcedOther attribute
				throw new RuntimeException("Unexpected value: "+informationText);
			}
		}
		return null;
	}

	private Group getElectricCompany(String electricCompanyId) {
		if(electricCompanyId!=null){
			if(!electricCompanyId.equals("0")){
				Map electricCompanies = getOldElectricCompaniesMap();
				String electricCompanyName = (String) electricCompanies.get(electricCompanyId);
				if(electricCompanyName==null){
					System.out.println("electricCompanyName==null for electricCompanyId:"+electricCompanyId);
				}
				Group group = getElectriCompanyGroupFromMap(electricCompanyId,electricCompanyName);
				return group;
			}
		}
		return null;
	}


	private Group getElectriCompanyGroupFromMap(String electricCompanyId,
			String electricCompanyName) {
		Group headGroup = getElectricCompaniesHeadGroup();
		List childGroups = headGroup.getChildGroups();
		for (Iterator iterator = childGroups.iterator(); iterator.hasNext();) {
			Group group = (Group) iterator.next();
			if(group!=null){
				String groupName = group.getName();
				if(groupName!=null){
					if(groupName.equals(electricCompanyName)){
						return group;
					}
				}
			}

		}
		//No group found:
		GroupHome gHome = getGroupHome();
		Group company;
		try {
			company = gHome.create();
			company.setName(electricCompanyName);
			company.store();
			headGroup.addGroup(company);
			return company;
		} catch (CreateException e) {
			throw new RuntimeException("Error creating energycompany group for: "+electricCompanyName);
		}
	}

	private Group getElectricCompaniesHeadGroup() {
		GroupHome gHome = getGroupHome();
		Collection groups;
		try {
			groups = gHome.findGroupsByName(InitialData.RAFVEITUR);
			for (Iterator iterator = groups.iterator(); iterator.hasNext();) {
				Group name = (Group) iterator.next();
				//Just return the first one:
				return name;
			}
		} catch (FinderException e1) {
		}

		//If no groups found:
		Group group;
		try {
			
			GroupBusiness gBusiness = getGroupBusiness();
			group = gBusiness.createGroup(InitialData.RAFVEITUR);
			//group = gHome.create();
			//group.setName(InitialData.RAFVEITUR);
			//group.store();
			return group;
		} catch (CreateException e) {
			throw new RuntimeException(e);
		} catch (RemoteException e) {
			throw new RuntimeException(e);
		}
	}

	private GroupBusiness getGroupBusiness() {
		try {
			return (GroupBusiness) getServiceInstance(GroupBusiness.class);
		} catch (IBOLookupException e) {
			throw new RuntimeException(e);
		}
	}

	private GroupHome getGroupHome() {
		try {
			return (GroupHome) getIDOHome(Group.class);
		} catch (RemoteException e) {
			throw new RuntimeException(e);
		}
	}

	private Map getOldElectricCompaniesMap() {
		if(electricCompaniesMap==null){
			//Hardcoded id,Name Map copied over from Rafveitur table in Access
			electricCompaniesMap=new HashMap();
			electricCompaniesMap.put("4", "Orkuveita Reykjavíkur");//OR
			electricCompaniesMap.put("5", "Hitaveita Suðurnesja hf.");//Hafnarfirdi
			electricCompaniesMap.put("6", "Hitaveita Suðurnesja hf.");//HS
			electricCompaniesMap.put("7", "Orkuveita Reykjavíkur");//OR Akranesi
			electricCompaniesMap.put("8", "Orkubú Vestfjarða hf.");//
			electricCompaniesMap.put("9", "Rafveita Sauðárkróks");//
			electricCompaniesMap.put("10", "Norðurorka hf.");//
			electricCompaniesMap.put("11", "Rafveita Reyðarfjarðar");
			electricCompaniesMap.put("12", "Hitaveita Suðurnesja hf.");//HSÁrb
			electricCompaniesMap.put("13", "Hitaveita Suðurnesja hf.");//BV
			electricCompaniesMap.put("14", "RARIK hf.");//Rarik suðurlandi
			electricCompaniesMap.put("15", "RARIK hf.");//Rarik vesturlandi
			electricCompaniesMap.put("16", "RARIK hf.");//Rarik norðurlandi vestra
			electricCompaniesMap.put("17", "RARIK hf.");//Rarik norðurlandi eystra
			electricCompaniesMap.put("18", "RARIK hf.");//Rarik austurlandi
			electricCompaniesMap.put("19", "Einkarafstöðvar");//Einkarafstöðvar
			electricCompaniesMap.put("20", "Orkuveita Húsavíkur ehf.");//
			electricCompaniesMap.put("21", "Landsvirkjun");//
			electricCompaniesMap.put("26", "Annað");//
		}
		return electricCompaniesMap;
	}

	private ElectricalInstallation getElecticalInstallation(String id) {
		ElectricalInstallationHome elHome = getElectricalInstallationHome();
		
		try {
			ElectricalInstallation elInstallation = elHome.findByExternalId(id);
			return elInstallation;
			
		} catch (FinderException e) {
			ElectricalInstallation elInstallation;
			try {
				elInstallation = elHome.create();
				elInstallation.setExternalId(id);
				//elInstallation.store();
				return elInstallation;
			} catch (CreateException e1) {
				throw new RuntimeException(e1);
			}
		}
		
	}

	private User getElectrician(String ssn) {
		UserHome userHome;
		try {
			userHome = getUserHome();
			return userHome.findByPersonalID(ssn);
		} catch (FinderException e) {
			String fullName = ssn;
			String personalID=ssn;
			Gender gender = null;
			IWTimestamp dateOfBirth = null;
			try {
				return getUserBusiness().createUserByPersonalIDIfDoesNotExist(fullName, personalID, gender, dateOfBirth);
			} catch (IBOLookupException e1) {
				throw new RuntimeException(e1);
			} catch (RemoteException e1) {
				throw new RuntimeException(e1);
			} catch (CreateException e1) {
				throw new RuntimeException(e1);
			}
		} catch (RemoteException e) {
			throw new RuntimeException(e);
		}
	}

	private Street getStreet(String address, String sPostalCode) {
		
		Gotuskra skra = Gotuskra.getCached();
		StreetHome streetHome = getStreetHome();
		PostalCodeHome postalCodeHome = getPostalCodeHome();

		PostalCode postalCode = null;
		try {
			postalCode = postalCodeHome.findByPostalCode(sPostalCode);
		} catch (FinderException e) {
			try {
				postalCode = postalCodeHome.create();
				postalCode.setPostalCode(sPostalCode);
				postalCode.store();
			} catch (CreateException e1) {
				e1.printStackTrace();
			}
		}
		String streetName=null;
		//Attempt to break up the name of the streetname:
		int indexOfSpace = address.indexOf(" ");
		if(indexOfSpace!=-1){
			streetName = address.substring(0,indexOfSpace);
		}
		else{
			streetName = address;
		}
		
		Gata gata = skra.getGataByNafnOrNafnThagufallAndPostnumer(streetName, sPostalCode);
		if(gata==null){
			Street street = null;
			try {
				street = streetHome.create();
			} catch (CreateException e1) {
				e1.printStackTrace();
			}
			street.setName(address);
			street.setPostalCode(postalCode);
			street.store();
			return street;
		}
		else{
			String name = gata.getNafn();
			String nameDativ = gata.getNafnThagufall();
			Street street = null;

			try {
				street = streetHome.findStreetByPostalCodeAndNameOrNameDativ(postalCode, name, nameDativ);
			} catch (FinderException e) {
				try {
					street = streetHome.create();
				} catch (CreateException e1) {
					e1.printStackTrace();
				}
				street.setName(gata.getNafn());
				street.setNameDativ(gata.getNafnThagufall());
				street.setPostalCode(postalCode);
				street.store();
			}
			return street;
		}
		
	}

	private PostalCodeHome getPostalCodeHome() {
		try {
			return (PostalCodeHome) getIDOHome(PostalCode.class);
		} catch (RemoteException e) {
			throw new RuntimeException(e);
		}
	}

	private StreetHome getStreetHome() {
		try {
			return (StreetHome) getIDOHome(Street.class);
		} catch (RemoteException e) {
			throw new RuntimeException(e);
		}
	}

	private Gotuskra getGotuskra() {
		if(gotuskra==null){
			try {
				gotuskra=new Gotuskra();
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			}
		}
		return gotuskra;
	}

	private RealEstateHome getRealEstateHome() throws RemoteException {
		return (RealEstateHome) getIDOHome(RealEstate.class);
	}

	private UserHome getUserHome() throws RemoteException{
		return (UserHome) getIDOHome(User.class);
	}
	
	private UserBusiness getUserBusiness() throws IBOLookupException {
		return (UserBusiness) getServiceInstance(UserBusiness.class);
	}

	private ElectricalInstallationHome getElectricalInstallationHome() {
		try {
			return (ElectricalInstallationHome) getIDOHome(ElectricalInstallation.class);
		} catch (RemoteException e) {
			throw new RuntimeException(e);
		}
	}

	private String getProperty(int columnIndex) {
		String value = null;

		if (this.valueList != null) {

			try {
				value = (String) this.valueList.get(columnIndex);
			} catch (RuntimeException e) {
				return null;
			}
			if (this.file.getEmptyValueString().equals(value)) {
				return null;
			}
			else {
				if(value.startsWith("\"")&&value.endsWith("\"")){
					value=value.substring(1,value.length()-1);
				}
				return value;
			}
		}
		else {
			return null;
		}
	}

}
