/*
 * $Id: TilkynningVertakaBean.java,v 1.29 2007/09/05 16:33:16 thomas Exp $
 * Created on Feb 13, 2007
 *
 * Copyright (C) 2007 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package is.idega.nest.rafverk.bean;

import is.idega.nest.rafverk.business.ElectricalInstallationBusiness;
import is.idega.nest.rafverk.business.ElectricalInstallationRendererBusiness;
import is.idega.nest.rafverk.business.ElectricalInstallationState;
import is.idega.nest.rafverk.business.ElectricalInstallationValidationBusiness;
import is.idega.nest.rafverk.data.Maelir;
import is.idega.nest.rafverk.data.MaelirList;
import is.idega.nest.rafverk.domain.ElectricalInstallation;
import is.idega.nest.rafverk.domain.Fasteign;
import is.idega.nest.rafverk.domain.FasteignaEigandi;
import is.idega.nest.rafverk.domain.Rafverktaka;
import is.idega.nest.rafverk.domain.Rafverktaki;

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

import javax.faces.model.SelectItem;

import com.idega.business.IBORuntimeException;
import com.idega.user.business.GroupBusiness;
import com.idega.user.data.Group;
import com.idega.util.StringHandler;
import com.idega.util.datastructures.list.KeyValuePair;


/**
 * 
 *  Last modified: $Date: 2007/09/05 16:33:16 $ by $Author: thomas $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.29 $
 */
public class TilkynningVertakaBean extends RealEstateBean {
	
	private Rafverktaka rafverktaka;
	
	private Rafverktaki newOwnerOfCase;
	
	private ElectricalInstallationBusiness electricalInstallationBusiness = null;
	
	private GroupBusiness groupBusiness = null;
	
	private ElectricalInstallationRendererBusiness electricalInstallationRendererBusiness = null;
	
	private ElectricalInstallationValidationBusiness electricalInstallationValidationBusiness = null;
	
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
    
	public TilkynningVertakaBean() {
    	initialize();
    }
	
	public void initialize() {
		initializeForm();
		initializeSending();
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
		validationResults = null;
	}
	
	public void createApplicationPDF() {
		createApplicationPDFSendEmails(false);
	}
 	
	public String sendApplication() {
		isSuccessfullyStored = storeApplicationData();
		if (isSuccessfullyStored) {
			boolean validationSuccessful = validateTilkynningVertaka();
			if (validationSuccessful) {
				if (sendApplicationData()) {
					messageStoring = "Þjónustubeiðni send";
					createApplicationPDFSendEmails(true);
				}
				else {
					messageStoring = "Þjónustubeiðni ekki send";
				}
				return "send";
			}
			// successfully stored but validation problems, stay on same page
			return null;
		}
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
			boolean validationSuccessful = validateTilkynningLokVerks();
			if (validationSuccessful) {
				if (sendApplicationReportData()) {
					messageStoring = "Skýrsla send";
					createApplicationReportPDF();
				}
				else {
					messageStoring = "Skýrsla ekki send";
				}
				return "send";
			}
			// successfully stored but validation problems, stay on same page
			return null;
		}
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
			return getElectricalInstallationBusiness().sendApplicationReport(getRafverktaka());
		}
		catch (RemoteException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public String storeApplication() {
		isSuccessfullyStored = storeApplicationData();
		if (isSuccessfullyStored) { 
			messageStoring = "þjónustubeiðni geymd";
		}
		else {
			messageStoring = "þjónustubeiðni ekki geymd";
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
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public String storeApplicationReport() {
		isSuccessfullyStored = storeApplicationReportData();
		if (isSuccessfullyStored) { 
			messageStoring = "Skýrsla geymd";
		}
		else {
			messageStoring = "Skýrsla ekki geymd";
		}
		return "store";
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
		FasteignaEigandi eigandi = fasteign.getEigandi();
		if(eigandi!=null){
			setNafnOrkukaupandaAndLock(eigandi.getNafn());
			setKennitalaOrkukaupandaAndLock(eigandi.getKennitala());
		}
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
				Collection children = groupBusinessTemp.getChildGroups(group);
				list.addAll(children);
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
				 messagePDF = (messagePDF == null) ? result : messagePDF + result;
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
}
