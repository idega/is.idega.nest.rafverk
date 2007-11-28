/*
 * $Id: TilkynningVertakaBean.java,v 1.38 2007/11/28 17:58:02 thomas Exp $
 * Created on Feb 13, 2007
 *
 * Copyright (C) 2007 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package is.idega.nest.rafverk.bean;

import is.fmr.landskra.Fasteign;
import is.idega.nest.rafverk.business.ElectricalInstallationBusiness;
import is.idega.nest.rafverk.business.ElectricalInstallationRendererBusiness;
import is.idega.nest.rafverk.business.ElectricalInstallationState;
import is.idega.nest.rafverk.business.ElectricalInstallationValidationBusiness;
import is.idega.nest.rafverk.business.UserMessagesBusiness;
import is.idega.nest.rafverk.data.Maelir;
import is.idega.nest.rafverk.data.MaelirList;
import is.idega.nest.rafverk.data.RealEstateIdentifier;
import is.idega.nest.rafverk.data.SimpleElectricalInstallationWrapper;
import is.idega.nest.rafverk.domain.ElectricalInstallation;
import is.idega.nest.rafverk.domain.Rafverktaka;
import is.idega.nest.rafverk.domain.Rafverktaki;
import is.idega.nest.rafverk.domain.SimpleElectricalInstallation;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.FinderException;
import javax.faces.component.UIComponent;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import com.idega.business.IBORuntimeException;
import com.idega.user.business.GroupBusiness;
import com.idega.user.data.Group;
import com.idega.user.data.User;
import com.idega.util.StringHandler;
import com.idega.util.datastructures.list.KeyValuePair;


/**
 * 
 *  Last modified: $Date: 2007/11/28 17:58:02 $ by $Author: thomas $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.38 $
 */
public class TilkynningVertakaBean extends RealEstateBean {
	
	private Rafverktaka rafverktaka;
	
	private ElectricalInstallationBusiness electricalInstallationBusiness;
	
	private GroupBusiness groupBusiness;
	
	private ElectricalInstallationRendererBusiness electricalInstallationRendererBusiness;
	
	private ElectricalInstallationValidationBusiness electricalInstallationValidationBusiness;
	
	private UserMessagesBusiness userMessagesBusiness;
	
	// special lock variables
	
	private boolean nafnOrkukaupandaIsLocked = false;
	
	private boolean kennitalaOrkukaupandaIsLocked = false;
	
	// first step 
	
	private List cachedListOfEnergyCompanies = null;
	
    private String orkuveitufyrirtaeki = null;
    
    private String externalProjectID = null;
    
    private String personInCharge = null;
    
    // second step 
    
    private String notkunarflokkur = null;
    
    private String heimtaug = null;
    
    private String heimtaugTengist = null;
    
    private String stofn1 = null;
    
    private String stofn2 = null;
    
    private String stofn3 = null;
    
    private String adaltafla = null;
    
    private List varnarradstoefun = null;
    
    private List beidniUm = null;
    
    private String uppsett = null;
    
    private Maelir stadurMaelir = null;
    
    private String numerToeflu = null;
    
    private String spennukerfi = null;
    
    private String annad = null;

    // third step
    
    private Map maelirListMap = null;
    
    private String skyringar = null;
    
    // sent step
    
    private boolean isSuccessfullyStored = false;
    private boolean isDownloadTilkynningVertaka = false;
    private boolean isDownloadTilkynningLokVerks = false;
    private String messagePDF = null;
    private String messageStoring= null;
    private String downloadTilkynningVertakaPDF = null;
    private String downloadTilkynningVertakaXML = null;
    private String downloadTilkynningLokVerksPDF = null;
    private String downloadTilkynningLokVerksXML = null;
    
    // validation and user messages
    private Map validationResults = null;
    
    private String currentWorkingPlaceErrorMessage = null;
    
    private boolean showChangeElectricianOption = false;
    
    private boolean showCheckingOutWorkingPlaceOption = false;
    
    private boolean workingPlaceChangeable = false;
    
    // component map
    Map componentMap;
    
    
    
	public TilkynningVertakaBean() {
    	initialize();
    }
	
	public void initialize() {
		initializeForm();
		initializeSending();
		initializeValidation();
	}
	
	public void initializeAfterSendingPage() {
		// do not initialize everything because sent page needs some piece of data
		initializeForm();
		initializeValidation();
	}
	
	void initializeForm() {
		super.initializeForm();
	    orkuveitufyrirtaeki = null;
	    externalProjectID = null;
	    personInCharge = null;
	    // second step 
	    notkunarflokkur = null;
	    heimtaug = null;
	    heimtaugTengist = null;
	    stofn1 = null;
	    stofn2 = null;
	    stofn3 = null;
	    adaltafla = null;
	    varnarradstoefun = null;
	    beidniUm = null;
	    uppsett = null;
	    stadurMaelir = null;
	    numerToeflu = null;
	    spennukerfi = null;
	    annad = null;
	    // third step
	    maelirListMap = null;
	    skyringar = null;
		// initialize maelir
		stadurMaelir = MaelirList.getEmptyMaelirContextStadurMaelir();
		// initialize list of maelir
		maelirListMap = MaelirList.getEmptyMaelirMap(); 
	}
	
	private void initializeSending() {
	    isSuccessfullyStored = false;
	    isDownloadTilkynningVertaka = false;
	    isDownloadTilkynningLokVerks = false;
	    messagePDF = null;
	    messageStoring= null;
	    downloadTilkynningVertakaPDF = null;
	    downloadTilkynningVertakaXML = null;
	    downloadTilkynningLokVerksPDF = null;
	    downloadTilkynningLokVerksXML = null;
	}
	
	


	private void initializeValidation() {
		validationResults = null;;
		currentWorkingPlaceErrorMessage = null;
		showChangeElectricianOption = false;
		showCheckingOutWorkingPlaceOption = false;
		workingPlaceChangeable = true;
	}
	
	public void createApplicationPDF() {
		createApplicationPDFSendEmails(false);
	}
 	
	public String sendApplication() {
		isSuccessfullyStored = storeApplicationData();
		if (isSuccessfullyStored) {
			// note: validation should not be done if wroking place is wrong
			if (noOneIsAlreadyWorkingAtThisPlace() && validateTilkynningVertaka()) {
				if (sendApplicationData()) {
					try {
						messageStoring = getUserMessagesBusiness().getMessageAfterSendingInApplication(getRafverktaka().getElectricalInstallation());
					}
					catch (RemoteException e) {
						throw new IBORuntimeException();
					}
					createApplicationPDFSendEmails(true);
				}
				else {
					messageStoring = "Þjónustubeiðni ekki send";
				}
				// good bye to tilkynningvertaka bean
				initializeAfterSendingPage();
				return "send";
			}
			// successfully stored but validation problems, stay on same page
			return null;
		}
		// good bye to tilkynningvertaka bean
		initializeAfterSendingPage();
		messageStoring = "Þjónustubeiðni ekki geymd";
		return "send";
	}
	
	private void createApplicationPDFSendEmails(boolean sendEmails) {
		isDownloadTilkynningVertaka = createTilkynningVertakaPDFAndSendEmails(sendEmails);
		if (! isDownloadTilkynningVertaka) {
			String message = "Problems appeared creating PDF";
			messagePDF = (messagePDF == null) ? message : (messagePDF + " " + message);
		}
	}
		
		
	private boolean sendApplicationData() {
		try {
			return getElectricalInstallationBusiness().sendApplication(getRafverktaka());
		}
		catch (RemoteException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public String sendApplicationReport() {
		isSuccessfullyStored = storeApplicationReportData();
		if (isSuccessfullyStored) {
			// note: validation should not be done if wroking place is wrong
			if (noOneIsAlreadyWorkingAtThisPlace() && validateTilkynningLokVerks()) {
				if (sendApplicationReportData()) {
					try {
						messageStoring = getUserMessagesBusiness().getMessageAfterSendingInApplicationReport(getRafverktaka().getElectricalInstallation());
					}
					catch (RemoteException e) {
						throw new IBORuntimeException(e);
					}
					createApplicationReportPDF();
				}
				else {
					messageStoring = "Skýrsla ekki send";
				}
				// good bye to tilkynningvertaka bean
				initializeAfterSendingPage();
				// good bye to tilkynning lok verks bean 
				BaseBean.getTilkynningLokVerksBean().initialize();
				return "send";
			}
			// successfully stored but validation problems, stay on same page
			return null;
		}
		// good bye to tilkynningvertaka bean
		initializeAfterSendingPage();
		// good bye to tilkynning lok verks bean 
		BaseBean.getTilkynningLokVerksBean().initialize();
		messageStoring = "Skýrsla ekki geymd";
		return "send";
		
	}
	
	public void createApplicationReportPDF() {
		isDownloadTilkynningLokVerks = createTilkynningLokVerksPDF();
		if (! isDownloadTilkynningLokVerks()) {
			String message = "Problems appeared creating PDF";
			messagePDF = (messagePDF == null) ? message : (messagePDF + " " + message);
		}
		
	}
	
	private boolean sendApplicationReportData() {
		try {
			RafverktokuListi rafverktokuListi = BaseBean.getRafverktokuListi();
			return getElectricalInstallationBusiness().sendApplicationReport(getRafverktaka(),rafverktokuListi);
		}
		catch (RemoteException e) {
			throw new RuntimeException(e);
		}
	}
	
	public String storeApplication() {
		isSuccessfullyStored = storeApplicationData();
		if (isSuccessfullyStored) { 
			try {
				messageStoring = getUserMessagesBusiness().getMessageAfterStoringApplication(getRafverktaka().getElectricalInstallation());
			}
			catch (RemoteException e) {
				throw new IBORuntimeException(e);
			}
		}
		else {
			messageStoring = "Problem appeared: þjónustubeiðni ekki geymd";
		}
		return "store";
	}
	
	private boolean storeApplicationData() {
		try {
			boolean result = getElectricalInstallationBusiness().storeApplication(getRafverktaka(), this, BaseBean.getTilkynningLokVerksBean());
			if (result) {
				// updating the list
				BaseBean.getRafverktokuListi().addRafvertaka(getRafverktaka());
			}
			return result;
		}
		catch (RemoteException e) {
			throw new IBORuntimeException(e);
		}
	}
	
	public String storeApplicationReport() {
		isSuccessfullyStored = storeApplicationReportData();
		if (isSuccessfullyStored) { 
			try {
				messageStoring = getUserMessagesBusiness().getMessageAfterStoringApplicationReport(getRafverktaka().getElectricalInstallation());
			}
			catch (RemoteException e) {
				throw new IBORuntimeException(e);
			}
		}
		else {
			messageStoring = "Skýrsla ekki geymd";
		}
		return "store";
	}
	
	/*
	 * action method called by JSF
	 */
	public String checkOutWorkingPlace() {
		if (! noOneIsAlreadyWorkingAtThisPlace()) {
			return null;
		}
		isSuccessfullyStored = checkOutWorkingPlaceData();
		if (isSuccessfullyStored) { 
			try {
				messageStoring = getUserMessagesBusiness().getMessageAfterCheckingOutWorkingPlace(getRafverktaka().getElectricalInstallation());
			}
			catch (RemoteException e) {
				throw new IBORuntimeException(e);
			}
		}
		else {
			messageStoring = "Verk ekki tekið";
		}
		return "store";
	}		
		
		
	private boolean checkOutWorkingPlaceData() {
		try {
			boolean result = getElectricalInstallationBusiness().checkOutWorkingPlace(getRafverktaka(), this, BaseBean.getTilkynningLokVerksBean());
			if (result) {
				// updating the list
				BaseBean.getRafverktokuListi().addRafvertaka(getRafverktaka());
			}
			return result;
		}
		catch (RemoteException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	private boolean storeApplicationReportData() {
		try {
			boolean result = getElectricalInstallationBusiness().storeApplicationReport(getRafverktaka(), this, BaseBean.getTilkynningLokVerksBean());
			if (result) {
				// updating the list
				BaseBean.getRafverktokuListi().addRafvertaka(getRafverktaka());
			}
			return result;
		}
		catch (RemoteException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public void initList(Map maelirListMap) {
		this.maelirListMap = maelirListMap;
	}

	public Map getList() {
		return maelirListMap;
	}

	
	/**
	 * Called by JSF page (application form)
	 * 
	 * @return
	 */
	public String store() {
		return storeApplication();
	}
	
	/**
	 *  Called by JSF page (application form)
	 * @return
	 */
	public String send() {
		return sendApplication();
	}
	
	/**
	 * Called by JSF page (application form)
	 * @return
	 */
	public String goToTilkynningLokVerks() {
		initializeTilkynningLokVerks();
		return "nextwizard";
	}
	
	/**
	 * 
	 * Called by JSF page (Create new)
	 * Creates an empty electrical installation with the current user as electrician
	 * 
	 * @return
	 */
	public String startTilkynningVertaka() {
		createEmptyRafverktaka();
		return "tilkynningvertaka";
	}
	
	/**
	 * Called by JSF page (Create new)
	 * Creates an empty electrical installation with the current user as electrician
	 * 
	 * @return
	 */
	public String startTilkynningLokVerks() {
		createEmptyRafverktaka();
		return "tilkynninglokverks";
	}
	
	private void createEmptyRafverktaka() {
		initialize();
		TilkynningLokVerksBean tilkynningLokVersBean = BaseBean.getTilkynningLokVerksBean();
		tilkynningLokVersBean.initialize();
		Rafverktaka rafverktakaTemp = Rafverktaka.getInstanceWithCurrentUserAsRafverktaki();
		setRafverktaka(rafverktakaTemp);
		tilkynningLokVersBean.setRafverktaka(rafverktakaTemp);
	}
	
	private void initializeTilkynningLokVerks() {
		TilkynningLokVerksBean tilkynningLokVerksBean = BaseBean.getTilkynningLokVerksBean();
		try {
			getElectricalInstallationBusiness().initializeManagedBeans(rafverktaka, this, tilkynningLokVerksBean);
		}
		catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new IBORuntimeException();
		}		
	}
	

	// generated getter and setter methods
	/**
	 * @return the externalIdProjectNumber
	 */
	public String getExternalProjectID() {
		return externalProjectID;
	}

	
	/**
	 * @param externalIdProjectNumber the externalIdProjectNumber to set
	 */
	public void setExternalProjectID(String externalProjectID) {
		this.externalProjectID = externalProjectID;
	}

	
	/**
	 * @return the personInCharge
	 */
	public String getPersonInCharge() {
		return personInCharge;
	}

	
	/**
	 * @param personInCharge the personInCharge to set
	 */
	public void setPersonInCharge(String personInCharge) {
		this.personInCharge = personInCharge;
	}
	
	
	
	public String getAdaltafla() {
		return adaltafla;
	}




	
	public void setAdaltafla(String adaltafla) {
		this.adaltafla = adaltafla;
	}

	
	public String getAnnad() {
		return annad;
	}
	
	public void setAnnad(String annad) {
		this.annad = annad;
	}

	
	public List getBeidniUm() {
		return beidniUm;
	}




	
	public void setBeidniUm(List beidniUm) {
		this.beidniUm = beidniUm;
	}


	
	public String getHeimasimiOrkukaupanda() {
		return getRafverktaka().getOrkukaupandi().getHeimasimi();
	}




	
	public void setHeimasimiOrkukaupanda(String heimasimiOrkukaupanda) {
		getRafverktaka().getOrkukaupandi().setHeimasimi(heimasimiOrkukaupanda);
	}




	
	public String getHeimtaug() {
		return heimtaug;
	}




	
	public void setHeimtaug(String heimtaug) {
		this.heimtaug = heimtaug;
	}




	
	public String getHeimtaugTengist() {
		return heimtaugTengist;
	}




	
	public void setHeimtaugTengist(String heimtaugTengist) {
		this.heimtaugTengist = heimtaugTengist;
	}

	// might be overwritten by subclasses
	void changedRealEstate(Fasteign fasteign) {
		if (fasteign != null) {
			setNafnOrkukaupandaAndLock(fasteign.getOwnerName());
			setKennitalaOrkukaupandaAndLock(fasteign.getOwnerKennitala());
		}
		initializeWorkingPlaceErrorMessage(fasteign);
	}
	

	
	public String getKennitalaOrkukaupanda() {
		kennitalaOrkukaupandaIsLocked = false;
		return getRafverktaka().getOrkukaupandi().getKennitala();
	}

	public void setKennitalaOrkukaupandaAndLock(String kennitalaOrkukaupanda) {
		if(StringHandler.isNotEmpty(kennitalaOrkukaupanda)){
			getRafverktaka().getOrkukaupandi().setKennitala(kennitalaOrkukaupanda);
			kennitalaOrkukaupandaIsLocked = true;
		}
	}

	
	public void setKennitalaOrkukaupanda(String kennitalaOrkukaupanda) {
		if(StringHandler.isNotEmpty(kennitalaOrkukaupanda) && ! kennitalaOrkukaupandaIsLocked){
			getRafverktaka().getOrkukaupandi().setKennitala(kennitalaOrkukaupanda);
		}
	}




	
	public String getNafnOrkukaupanda() {
		nafnOrkukaupandaIsLocked = false;
		return getRafverktaka().getOrkukaupandi().getNafn();
	}

	
	public void setNafnOrkukaupandaAndLock(String nafnOrkukaupanda) {
		if(StringHandler.isNotEmpty(nafnOrkukaupanda)){
			getRafverktaka().getOrkukaupandi().setNafn(nafnOrkukaupanda);
			nafnOrkukaupandaIsLocked = true;
		}
	}


	
	public void setNafnOrkukaupanda(String nafnOrkukaupanda) {
		if(StringHandler.isNotEmpty(nafnOrkukaupanda) && ! nafnOrkukaupandaIsLocked){
			getRafverktaka().getOrkukaupandi().setNafn(nafnOrkukaupanda);
		}
	}




	
	public String getNotkunarflokkur() {
		return notkunarflokkur;
	}




	
	public void setNotkunarflokkur(String notkunarflokkur) {
		this.notkunarflokkur = notkunarflokkur;
	}




	
	public String getNumerToeflu() {
		return numerToeflu;
	}




	
	public void setNumerToeflu(String numerToeflu) {
		this.numerToeflu = numerToeflu;
	}




	
	public String getOrkuveitufyrirtaeki() {
		return orkuveitufyrirtaeki;
	}
	
	public Group getEnergyCompany() {
		try {
			String primaryKey = getOrkuveitufyrirtaeki();
			if (StringHandler.isEmpty(primaryKey)) {
				return null;
			}
			return (Group) getGroupBusiness().getGroupHome().findByPrimaryKey(getOrkuveitufyrirtaeki());
		}
		catch (RemoteException e) {
			throw new IBORuntimeException(e);
		}
		catch (FinderException e) {
			e.printStackTrace();
			return null;
		}
	}




	
	public void setOrkuveitufyrirtaeki(String orkuveitufyrirtaeki) {
		this.orkuveitufyrirtaeki = orkuveitufyrirtaeki;
	}

	
	
	
	public String getSpennukerfi() {
		return spennukerfi;
	}




	
	public void setSpennukerfi(String spennukerfi) {
		this.spennukerfi = spennukerfi;
	}




	
	public String getStofn1() {
		return stofn1;
	}




	
	public void setStofn1(String stofn1) {
		this.stofn1 = stofn1;
	}




	
	public String getStofn2() {
		return stofn2;
	}




	
	public void setStofn2(String stofn2) {
		this.stofn2 = stofn2;
	}




	
	public String getStofn3() {
		return stofn3;
	}




	
	public void setStofn3(String stofn3) {
		this.stofn3 = stofn3;
	}




	
	public String getUppsett() {
		return uppsett;
	}




	
	public void setUppsett(String uppsett) {
		this.uppsett = uppsett;
	}




	
	public List getVarnarradstoefun() {
		return varnarradstoefun;
	}




	
	public void setVarnarradstoefun(List varnarradstoefun) {
		this.varnarradstoefun = varnarradstoefun;
	}




	
	public String getVinnusimiOrkukaupanda() {
		return getRafverktaka().getOrkukaupandi().getVinnusimi();
	}

	
	public void setVinnusimiOrkukaupanda(String vinnusimiOrkukaupanda) {
		getRafverktaka().getOrkukaupandi().setVinnusimi(vinnusimiOrkukaupanda);
	}
	
	public String getEnergyConsumerEmail() {
		return getRafverktaka().getOrkukaupandi().getEmail();
	}

	
	public void setEnergyConsumerEmail(String consumerEmail) {
		getRafverktaka().getOrkukaupandi().setEmail(consumerEmail);
	}

	
	public String getSkyringar() {
		return skyringar;
	}

	
	public void setSkyringar(String skyringar) {
		this.skyringar = skyringar;
	}

	
	public Maelir getStadurMaelir() {
		return stadurMaelir;
	}

	
	public void setStadurMaelir(Maelir stadurMaelir) {
		this.stadurMaelir = stadurMaelir;
	}
	
	

	
	// energy companies
	
	public List getRafveituListiSelects(){
		if (cachedListOfEnergyCompanies == null) {
			cachedListOfEnergyCompanies = new ArrayList();
			SelectItem defaultItem = new SelectItem();
			defaultItem.setLabel("Veldu orkuveitufyrirtæki");
			defaultItem.setValue(StringHandler.EMPTY_STRING);
			cachedListOfEnergyCompanies.add(defaultItem);
			List rafveitur = getRafveituListi();
			for (Iterator iter = rafveitur.iterator(); iter.hasNext();) {
				Group fyrirtaeki = (Group) iter.next();
				SelectItem item = new SelectItem();
				item.setLabel(fyrirtaeki.getName());
				item.setValue(fyrirtaeki.getPrimaryKey().toString());
				cachedListOfEnergyCompanies.add(item);
			}
		}
		return cachedListOfEnergyCompanies;
	}
	
	public List getRafveituListi(){
		ArrayList list = new ArrayList();
		GroupBusiness groupBusinessTemp = getGroupBusiness();
		try {
			Collection groups = groupBusinessTemp.getGroupsByGroupName(InitialData.RAFVEITUR);
			Iterator groupIterator = groups.iterator();
			while (groupIterator.hasNext()) {
				Group group = (Group) groupIterator.next();
				List parents = group.getParentGroups();
				if (parents == null ||parents.isEmpty()) {
					Collection children = groupBusinessTemp.getChildGroups(group);
					list.addAll(children);
				}
 			}
		}
		catch (RemoteException e) {
			throw new RuntimeException(e.getMessage());
		}
		// sort according to names
		Comparator comparator = new Comparator() {
			public int compare(Object o1, Object o2) {
				Group group1 = (Group) o1;
				Group group2 = (Group) o2;
				return group1.getName().compareTo(group2.getName());
			}
		};
		Collections.sort(list, comparator);
		return list;
	}

	public ElectricalInstallationRendererBusiness getElectricalInstallationRendererBusiness() {
		electricalInstallationRendererBusiness = (ElectricalInstallationRendererBusiness) 
			BaseBean.initializeServiceBean(electricalInstallationRendererBusiness, ElectricalInstallationRendererBusiness.class);
		return electricalInstallationRendererBusiness;
	}
	
	public ElectricalInstallationBusiness getElectricalInstallationBusiness() {
		electricalInstallationBusiness = (ElectricalInstallationBusiness) 
			BaseBean.initializeServiceBean(electricalInstallationBusiness, ElectricalInstallationBusiness.class);
		return electricalInstallationBusiness;
	}
	
	public ElectricalInstallationValidationBusiness getElectricalInstallationValidationBusiness() {
		electricalInstallationValidationBusiness = (ElectricalInstallationValidationBusiness) 
			BaseBean.initializeServiceBean(electricalInstallationValidationBusiness,ElectricalInstallationValidationBusiness.class);
		return electricalInstallationValidationBusiness;
	}

	public GroupBusiness getGroupBusiness() {
		groupBusiness = 
			(GroupBusiness) BaseBean.initializeServiceBean(groupBusiness,GroupBusiness.class);
		return groupBusiness;
	}
	
	public UserMessagesBusiness getUserMessagesBusiness() {
		userMessagesBusiness = 
			(UserMessagesBusiness) BaseBean.initializeServiceBean(userMessagesBusiness, UserMessagesBusiness.class);
		return userMessagesBusiness;
	}
	
	public Rafverktaka getRafverktaka() {
		return rafverktaka;
	}

	
	public void setRafverktaka(Rafverktaka rafverktaka) {
		this.rafverktaka = rafverktaka;
	}

	
	public Rafverktaki getNewOwnerOfCase() {
		return getRafverktaka().getNewOwner();
	}
	
	/**
	 * Called by JSF page (application form)
	 * @return
	 */	
	public boolean isCurrentWorkingPlaceErrorMessageNotEmpty() {
		return StringHandler.isNotEmpty(currentWorkingPlaceErrorMessage);
	}
	
	/**
	 * Called by JSF page (application form)
	 * @return
	 */	
	public boolean isWorkingPlaceChangeable() {
		return workingPlaceChangeable;
	}
	
	/**
	 * Called by JSF page (application form)
	 * @return
	 */
	public boolean isCheckingOutWorkingPlaceAllowed() {
		return showCheckingOutWorkingPlaceOption;
	}
	
	
	/**
	 * Called by JSF page (application form)
	 * @return
	 */
	public boolean isApplicationStorable() {
		return getElectricalInstallationState().isApplicationStorable(rafverktaka.getElectricalInstallation());
	}
	
	/**
	 * Called by JSF page (application form)
	 * @return
	 */
	public boolean isApplicationSendable() {
		return getElectricalInstallationState().isApplicationSendable(rafverktaka.getElectricalInstallation());
	}
	
	/**
	 * Called by JSF page (application report form)
	 * @return
	 */
	public boolean isApplicationReportStorable() {
		return getElectricalInstallationState().isApplicationReportStorable(rafverktaka.getElectricalInstallation());
	}
	
	/**
	 * Called by JSF page (application report form)
	 * @return
	 */
	public boolean isApplicationReportSendable() {
		return getElectricalInstallationState().isApplicationReportSendable(rafverktaka.getElectricalInstallation());
	}
	
	public boolean isSuccessfullyStored() {
		return isSuccessfullyStored;
	}
	
	public boolean isDownloadTilkynningVertaka() {
		return isDownloadTilkynningVertaka;
	}
	
	public boolean isDownloadTilkynningLokVerks() {
		return isDownloadTilkynningLokVerks;
	}
	
	public String getMessageStoring() {
		return messageStoring;
	}
	
	public String getMessagePDF() {
		return messagePDF;
	}

	
	public String getDownloadTilkynningVertakaPDF() {
		return downloadTilkynningVertakaPDF;
	}
	
	public String getDownloadTilkynningVertakaXML() {
		return downloadTilkynningVertakaXML;
	}
	
	public String getDownloadTilkynningLokVerksPDF() {
		return downloadTilkynningLokVerksPDF;
	}
	
	public String getDownloadTilkynningLokVerksXML() {
		return downloadTilkynningLokVerksXML;
	}
	
	private boolean createTilkynningVertakaPDFAndSendEmails(boolean sendEmails)  {
		try {
			ElectricalInstallation electricalInstallation = rafverktaka.getElectricalInstallation();
			//getElectricalInstallationRendererBusiness().validateApplication(getRafverktaka());
			 KeyValuePair downloadURLMessage = (sendEmails) ? 
				getElectricalInstallationBusiness().getPDFApplicationAndSendEmails(electricalInstallation):
				getElectricalInstallationBusiness().getPDFApplication(electricalInstallation);	
			 downloadTilkynningVertakaPDF = (String) downloadURLMessage.getKey();
			 String result = (String) downloadURLMessage.getValue();
			 if (result != null) {
				 // note: normal case: result is null
				 messagePDF = (messagePDF == null) ? result : messagePDF + " "  + result;
			 }
			 return true;
		}
		catch (RemoteException e) {
			throw new RuntimeException(e.getMessage());
		}
		catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean createTilkynningVertakaXML()  {
		try {
			ElectricalInstallation electricalInstallation = rafverktaka.getElectricalInstallation();
			//getElectricalInstallationRendererBusiness().validateApplication(getRafverktaka());
			downloadTilkynningVertakaXML = getElectricalInstallationRendererBusiness().getXMLApplication(electricalInstallation);
			return true;
		}
		catch (RemoteException e) {
			throw new RuntimeException(e.getMessage());
		}
		catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	private boolean createTilkynningLokVerksPDF()  {
		try {
			ElectricalInstallation electricalInstallation = rafverktaka.getElectricalInstallation();
			downloadTilkynningLokVerksPDF = getElectricalInstallationRendererBusiness().getPDFReport(electricalInstallation);
			return true;
		}
		catch (RemoteException e) {
			throw new RuntimeException(e.getMessage());
		}
		catch (IOException e) {
			return false;
		}
	}
	
	private boolean createTilkynningLokVerksXML()  {
		try {
			ElectricalInstallation electricalInstallation = rafverktaka.getElectricalInstallation();
			downloadTilkynningLokVerksXML = getElectricalInstallationRendererBusiness().getXMLReport(electricalInstallation);
			return true;
		}
		catch (RemoteException e) {
			throw new RuntimeException(e.getMessage());
		}
		catch (IOException e) {
			return false;
		}
	}
	
	public void initializeWorkingPlaceErrorMessage(Fasteign fasteign) {
		RealEstateIdentifier realEstateIdentifier = RealEstateIdentifier.getInstance(fasteign);
		checkWorkingPlace(realEstateIdentifier);
	}	
	
	private boolean noOneIsAlreadyWorkingAtThisPlace() {
		String fastaNumer = getFastanumer();
		RealEstateIdentifier realEstateIdentifier = RealEstateIdentifier.getInstance(fastaNumer);
		checkWorkingPlace(realEstateIdentifier);
		return StringHandler.isEmpty(currentWorkingPlaceErrorMessage);
	}
	
	private void checkWorkingPlace(RealEstateIdentifier realEstateIdentifier) {
		ElectricalInstallation electricalInstallation = rafverktaka.getElectricalInstallation();
		Map result = null;
		try {
			result = getElectricalInstallationBusiness().isSomeoneAlreadyWorkingAtThisPlace(realEstateIdentifier, electricalInstallation);
		}
		catch (RemoteException e) {
			throw new RuntimeException();
		}
		setOptionsAndMessage(result, electricalInstallation);
	}
			
	private void setOptionsAndMessage(Map result, ElectricalInstallation electricalInstallation) {
		// does the state allow to change the working place?
		workingPlaceChangeable = isCheckingOutWorkingPlaceAllowedByState(electricalInstallation);
		if (workingPlaceChangeable) {
			List usersOfAvailableCases = (result == null) ? null : (List)result.get(ElectricalInstallationState.AVAILABLE_STATUS_KEY);
			List usersOfClosedCases = (result == null) ? null : (List) result.get(ElectricalInstallationState.CLOSED_STATUS_KEY);
			currentWorkingPlaceErrorMessage = getSomeoneIsAlreadyWorkingAtThisPlaceErrorMessage(usersOfAvailableCases, usersOfClosedCases);
			setShowChangeElectricianOption(usersOfAvailableCases, usersOfClosedCases);
		}
		else {
			// status does not allow to change the working place
			currentWorkingPlaceErrorMessage = null;
			showChangeElectricianOption = false;
		}
			// must be called at the end, currentWorkingPlaceErrorMessage is checked
		setWorkingPlaceOptions(electricalInstallation, workingPlaceChangeable);
	}
	
	private void setShowChangeElectricianOption(List usersOfAvailableCases, List usersOfClosedCases) {
		// may we ask for taking over working place?
		if (usersOfClosedCases != null && (!usersOfClosedCases.isEmpty())) {
			showChangeElectricianOption = false;
			return;
		}
		// do not show option to change electrician if the electrician himself is blocking the working place
		if (usersOfAvailableCases == null || usersOfAvailableCases.isEmpty()) {
			showChangeElectricianOption = false;
			return;
		}
		User user = BaseBean.getCurrentUser();
		showChangeElectricianOption =  ! usersOfAvailableCases.contains(user);
	}
	
	private void setWorkingPlaceOptions(ElectricalInstallation electricalInstallation, boolean workingPlaceChangeableLocal) {
		// is there a selection that can be check out?
		// #1 has the right status
		// #2 something is selected at all
		// #3 selection is valid
		showCheckingOutWorkingPlaceOption = 
			workingPlaceChangeableLocal &&
			StringHandler.isNotEmpty(getFastanumer()) &&
			(! isWorkingPlaceInvalid());
	}
	
	private boolean isCheckingOutWorkingPlaceAllowedByState(ElectricalInstallation electricalInstallation) {
		return getElectricalInstallationState().isCheckingOutWorkingPlaceAllowed(electricalInstallation);
	}
	
	private boolean isWorkingPlaceInvalid() {
		return StringHandler.isNotEmpty(currentWorkingPlaceErrorMessage);
	}
	
	private String getSomeoneIsAlreadyWorkingAtThisPlaceErrorMessage(List usersOfAvailableCases, List usersOfClosedCases) {
		if (usersOfAvailableCases == null && usersOfClosedCases == null) {
			return "Veitustaður óþekktur";
		}
		User user = null;
		if (usersOfAvailableCases != null) {
			Iterator iterator = usersOfAvailableCases.iterator();
			if (iterator.hasNext()) {
				user = (User) iterator.next();
			}
		}
		else if (usersOfClosedCases != null) {
			Iterator iterator = usersOfClosedCases.iterator();
			if (iterator.hasNext()) {
				user = (User) iterator.next();
			}
		}
		if (user == null) {
			// everything is fine
			return StringHandler.EMPTY_STRING;
		}
		StringBuffer buffer = new StringBuffer("Eftirfarandi rafverktaki er nú þegar að vinna á þessum veiturstað ");
		buffer.append(user.getName());
		return buffer.toString();
	}
	
	
	
	
	
	private boolean validateTilkynningVertaka()  {
		try {
			ElectricalInstallation electricalInstallation = rafverktaka.getElectricalInstallation();
			validationResults =  getElectricalInstallationValidationBusiness().validateApplication(electricalInstallation);
			return validationResults.isEmpty();
		}
		catch (RemoteException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	private boolean validateTilkynningLokVerks()  {
		try {
			ElectricalInstallation electricalInstallation = rafverktaka.getElectricalInstallation();
			validationResults =  getElectricalInstallationValidationBusiness().validateReport(electricalInstallation);
			return validationResults.isEmpty();
		}
		catch (RemoteException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public boolean isApplicationInvalid() {
		try {
			return ! getElectricalInstallationValidationBusiness().isApplicationValid(validationResults);
		}
		catch (RemoteException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public boolean isReportInvalid() {
		try {
			return ! getElectricalInstallationValidationBusiness().isReportValid(validationResults);
		}
		catch (RemoteException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public Map getInvalid() {
		if (validationResults == null) {
			validationResults = new HashMap(0);
		}
		return validationResults;
	}
	
	private ElectricalInstallationState getElectricalInstallationState() {
		try {
			return getElectricalInstallationBusiness().getElectricalInstallationState();
		}
		catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new IBORuntimeException();
		}
	}
	
	public void changeElectrician(ActionEvent event) {
		// initialize change electrician before entering page by values of this page
		initializeChangeElectricianBean();
	}
	
	private void initializeChangeElectricianBean() {
		List list = getFasteignaListi();
		Fasteign fasteign;
		if (list == null) {
			fasteign = rafverktaka.getFasteign();
		}
		else {
			fasteign = lookupFasteign(getFastanumer());
		}
		RealEstateIdentifier realEstateIdentifier = RealEstateIdentifier.getInstance(fasteign);
		ChangeElectricianBean changeElectricianBean = BaseBean.getChangeElectricianBean();
		changeElectricianBean.initializeElectricalInstallationList(realEstateIdentifier);
		changeElectricianBean.setPostnumer(getPostnumer());
		changeElectricianBean.setGata(getGata());
		changeElectricianBean.setGotunumer(getGotunumer());
		changeElectricianBean.setFreeText(getFreeText());
		changeElectricianBean.setStreetNumber(getStreetNumber());
		changeElectricianBean.setFasteignaListi(getFasteignaListi());
		changeElectricianBean.setFastanumer(getFastanumer());
		changeElectricianBean.setVeitustadurDisplay(getVeitustadurDisplay());
		
		// good bye to tilkynningvertaka bean
		initialize();
	}
	
	public String getCurrentWorkingPlaceErrorMessage() {
		return currentWorkingPlaceErrorMessage;
	}
	
	/*
	 * Called by JSF
	 */
	public String getShowChangeElectricianOption() {
		return (showChangeElectricianOption) ? "display:" : "display:none";
	}
	
	/*
	 * Called by DWR
	 */
	public String getShowChangeElectricianOptionForDWR() {
		return (showChangeElectricianOption) ? "" : "none";
	}
	
	public void triggerValidation(ActionEvent actionEvent) {
		// do vaildation if someone tried to send the application
		if (isApplicationInvalid()) {
			SimpleElectricalInstallation simpleElectricalInstallation = 
				new SimpleElectricalInstallationWrapper(this, BaseBean.getTilkynningLokVerksBean());
			try {
				validationResults =  getElectricalInstallationValidationBusiness().validateApplication(simpleElectricalInstallation);
			}
			catch (RemoteException e) {
				throw new IBORuntimeException(e);
			}
		}
	}
	
	public void triggerValidationReport(ActionEvent actionEvent) {
		// do vaildation if someone tried to send the application
		if (isReportInvalid()) {
			SimpleElectricalInstallation simpleElectricalInstallation = 
				new SimpleElectricalInstallationWrapper(this, BaseBean.getTilkynningLokVerksBean());
			try {
				validationResults =  getElectricalInstallationValidationBusiness().validateReport(simpleElectricalInstallation);
			}
			catch (RemoteException e) {
				throw new IBORuntimeException(e);
			}
		}
	}
	
	public UIComponent getComponent(String name) {
		return (UIComponent) componentMap.get(name);
	}
	
	public Map getComponentMap() {
		if (componentMap == null) {
			componentMap = new HashMap();
		}
		return componentMap;
	}
}
