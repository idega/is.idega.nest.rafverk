/*
 * $Id: TilkynningVertakaBean.java,v 1.15 2007/04/18 17:54:46 thomas Exp $
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
import is.idega.nest.rafverk.data.Maelir;
import is.idega.nest.rafverk.data.MaelirList;
import is.idega.nest.rafverk.domain.Fasteign;
import is.idega.nest.rafverk.domain.FasteignaEigandi;
import is.idega.nest.rafverk.domain.Rafverktaka;
import is.idega.nest.rafverk.fmr.FMRLookupBean;
import is.postur.Gata;
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
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import com.idega.business.IBOLookup;
import com.idega.business.IBOService;
import com.idega.fop.business.DataToXMLToPDFBusiness;
import com.idega.idegaweb.IWApplicationContext;
import com.idega.presentation.IWContext;
import com.idega.user.business.GroupBusiness;
import com.idega.user.data.Group;
import com.idega.util.StringHandler;


/**
 * 
 *  Last modified: $Date: 2007/04/18 17:54:46 $ by $Author: thomas $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.15 $
 */
public class TilkynningVertakaBean {
	
	private Rafverktaka rafverktaka = null;
	
	private ElectricalInstallationBusiness electricalInstallationBusiness = null;
	
	private GroupBusiness groupBusiness = null;
	
	private ElectricalInstallationRendererBusiness electricalInstallationRendererBusiness = null;
	
	// special lock variables
	
	private boolean nafnOrkukaupandaIsLocked = false;
	
	private boolean kennitalaOrkukaupandaIsLocked = false;
	
	// first step 
	
	// id of group
	
	private List cachedListOfEnergyCompanies = null;
	
    private String orkuveitufyrirtaeki = null;
    
    private String postnumer = null;
    
    private String gata = null;
    
    private String gotunumer = null;
    
    private String fastanumer = null;
    
    private String veitustadurDisplay = null;
    
    private List fasteignaListi = null;
    
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
    private String downloadTilkynningVertaka = null;
    private String downloadTilkynningLokVerks = null;
    
	public TilkynningVertakaBean() {
    	initialize();
    }
	
	public void initialize() {
	    orkuveitufyrirtaeki = null;
	    postnumer = null;
	    gata = null;
	    gotunumer = null;
	    fastanumer = null;
	    fasteignaListi = null;
	    veitustadurDisplay = null;
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
	
	public boolean storeRafvertaka() {
		// store as case
		boolean successfullyStored = false;
		try {
			successfullyStored = getElectricalInstallationBusiness().storeManagedBeans(getRafverktaka(), this, BaseBean.getTilkynningLokVerksBean());
		}
		catch (RemoteException e) {
			throw new RuntimeException(e.getMessage());
		}
		BaseBean.getRafverktokuListi().addRafvertaka(getRafverktaka());
		return successfullyStored;
	}
	
	public void initList(Map maelirListMap) {
		this.maelirListMap = maelirListMap;
	}

	public Map getList() {
		return maelirListMap;
	}
	
	public String store() {
		isSuccessfullyStored = storeRafvertaka();
		if (isSuccessfullyStored) { 
			messageStoring = "Skýrsla send";
			isDownloadTilkynningVertaka = createTilkynningVertakaPDF(); 
			if (!isDownloadTilkynningVertaka) {
				messagePDF = "PDF could not be created";
			}
		}
		else {
			messageStoring = "Skýrsla ekki send";
		}
		return "send";
	}
	
	public String send() {
		if (storeRafvertaka()) {
			createTilkynningVertakaPDF();
		}
		return "send";
	}
	
	public String goToTilkynningLokVerks() {
		initializeTilkynningLokVerks();
		return "nextwizard";
	}
	
	/**
	 * Creates an empty electrical installation with the current user as electrician
	 * 
	 * @return
	 */
	public String startTilkynningVertaka() {
		createEmptyRafverktaka();
		return "tilkynningvertaka";
	}
	
	/**
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
		TilkynningLokVerksBean tilkynningLokVersBean = BaseBean.getTilkynningLokVerksBean();
		// first step
		tilkynningLokVersBean.setNafnOrkukaupanda(getNafnOrkukaupanda());
		tilkynningLokVersBean.setKennitalaOrkukaupanda(getKennitalaOrkukaupanda());
		tilkynningLokVersBean.setHeimasimiOrkukaupanda(getHeimasimiOrkukaupanda());
		tilkynningLokVersBean.setVinnusimiOrkukaupanda(getVinnusimiOrkukaupanda());
		
		tilkynningLokVersBean.setPostnumer(getPostnumer());
		tilkynningLokVersBean.setGata(getGata());
		tilkynningLokVersBean.setGotunumer(getGotunumer());
		// second step
		tilkynningLokVersBean.setNotkunarflokkur(getNotkunarflokkur());
		// spennu fields...
		tilkynningLokVersBean.setSpennukerfi(getSpennukerfi());
		tilkynningLokVersBean.setAnnad(getAnnad());
		// ...spennu fields
		tilkynningLokVersBean.setVarnarradstoefun(getVarnarradstoefun());
		
	}
	

	// generated getter and setter methods
	
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




	
	public String getGata() {
		return gata;
	}




	
	public void setGata(String gata) {
		this.gata = gata;
	}




	
	public String getGotunumer() {
		return gotunumer;
	}




	
	public void setGotunumer(String gotunumer) {
		this.gotunumer = gotunumer;
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




	
	public String getPostnumer() {
		return postnumer;
	}




	
	public void setPostnumer(String postnumer) {
		this.postnumer = postnumer;
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
	
	
	public String flettaUppIFasteignaskra() {
		
		fetchFasteignaListi();
		
		return "result";
	}
	
	
	private void fetchFasteignaListi() {
		
		String addr = getGata()+" "+getGotunumer();

		FMRLookupBean lookup = getFMRLookup();
		fasteignaListi = lookup.getFasteignir(addr,getPostnumer());
		setAvailablefasteign(true);
	}

	public List getFasteignaListi(){
		return fasteignaListi;
	}
	

	public String getFastanumer() {
		return fastanumer;
	}

	// called when populating the form with a previous stored form
	public void initFastanumer(String fastanumer) {
		this.fastanumer = fastanumer;
	}
	
	
	public void setFastanumer(String fastanumer) {
		if(StringHandler.isNotEmpty(fastanumer)) {
			// do not do anything if there is no change
			if (fastanumer.equals(this.fastanumer)) {
				return;
			}
			String postnumer = getPostnumer();
			Fasteign fasteign = lookupFasteign(fastanumer);
			FasteignaEigandi eigandi = fasteign.getEigandi();
			if(eigandi!=null){
				setNafnOrkukaupandaAndLock(eigandi.getNafn());
				setKennitalaOrkukaupandaAndLock(eigandi.getKennitala());
				String description = fasteign.getDescription();
				setVeitustadurDisplay(description);
			}
			
		}
		this.fastanumer = fastanumer;
	}

	public List getFasteignaListiSelects() {
		ArrayList selects = new ArrayList();
		List listi = getFasteignaListi();
		SelectItem noneItem = new SelectItem();
		noneItem.setLabel("Vinsamlegast veldu rétta fasteign:");
		noneItem.setValue("");
		selects.add(noneItem);
		for (Iterator iter = listi.iterator(); iter.hasNext();) {
			Fasteign fasteign = (Fasteign) iter.next();
			SelectItem item = new SelectItem();
			item.setLabel(fasteign.getDescription());
			item.setValue(fasteign.getFastaNumer());
			selects.add(item);
		}
		return selects;
	}


	public void setFasteignaListi(List fasteignaListi) {
		this.fasteignaListi = fasteignaListi;
	}

	public boolean isAvailablefasteign() {
		return fasteignaListi!=null;
		//return availablefasteign;
	}

	public void setAvailablefasteign(boolean availablefasteign) {
		//this.availablefasteign = availablefasteign;
	}
	
	
	public FMRLookupBean getFMRLookup(){
		return new FMRLookupBean();
	}
	
	public List getGotuListiSelects(){

		ArrayList selects = new ArrayList();
		if(postnumer==null||postnumer.equals("")){
			SelectItem item = new SelectItem();
			item.setLabel("Veldu póstnúmer fyrst");
			item.setValue("");
			selects.add(item);
		}
		else{
			SelectItem item0 = new SelectItem();
			item0.setLabel("- Engin gata til staðar");
			item0.setValue("none");
			selects.add(item0);
			List gotuListi = BaseBean.getInitialData().getGotuListiByPostnumer(postnumer);
			for (Iterator iter = gotuListi.iterator(); iter.hasNext();) {
				Gata gataTemp = (Gata) iter.next();
				SelectItem item = new SelectItem();
				item.setLabel(gataTemp.getNafn());
				item.setValue(gataTemp.getNafn());
				selects.add(item);
			}
		}
		return selects;
	}
	
	// lookup fasteign
	
	public Fasteign lookupFasteign(String realEstateNumber) {
		List fasteignaListiTemp = getFasteignaListi();
		Iterator iterator = fasteignaListiTemp.iterator();
		while(iterator.hasNext()) {
			Fasteign fasteign = (Fasteign) iterator.next();
			String fastaNumer = fasteign.getFastaNumer();
			if (fastaNumer != null && fastaNumer.equals(realEstateNumber)) {
				return fasteign;
			}
		}
		return null;
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
		if (electricalInstallationRendererBusiness == null) {
			electricalInstallationRendererBusiness =  (ElectricalInstallationRendererBusiness) getSeviceBean(ElectricalInstallationRendererBusiness.class);
		}
		return electricalInstallationRendererBusiness;
	}
	
	public ElectricalInstallationBusiness getElectricalInstallationBusiness() {
		if (electricalInstallationBusiness == null) {
			electricalInstallationBusiness = (ElectricalInstallationBusiness) getSeviceBean(ElectricalInstallationBusiness.class);
		}
		return electricalInstallationBusiness;
	}

	public GroupBusiness getGroupBusiness() {
		if (groupBusiness == null) {
			groupBusiness = (GroupBusiness) getSeviceBean(GroupBusiness.class);
		}
		return groupBusiness;
	}
	
	private IBOService getSeviceBean(Class serviceBeanClass) {
		IBOService myServiceBean = null;
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			IWContext iwContext = IWContext.getIWContext(context);
			IWApplicationContext iwac = iwContext.getApplicationContext();
			myServiceBean = IBOLookup.getServiceInstance(iwac, serviceBeanClass);
		}
		catch (RemoteException rme) {
			throw new RuntimeException(rme.getMessage());
		}
		return myServiceBean;
	}

	
	public Rafverktaka getRafverktaka() {
		return rafverktaka;
	}

	
	public void setRafverktaka(Rafverktaka rafverktaka) {
		this.rafverktaka = rafverktaka;
	}

	
	public String getVeitustadurDisplay() {
		return veitustadurDisplay;
	}

	
	public void setVeitustadurDisplay(String veitustadur) {
		this.veitustadurDisplay = veitustadur;
	}
	
	// storing and creating 
	
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

	
	public String getDownloadTilkynningVertakaURL() {
		return downloadTilkynningVertaka;
	}
	
	public String getDownloadTilkynningLokVerksURL() {
		return downloadTilkynningLokVerks;
	}
	
	private boolean createTilkynningVertakaPDF()  {
		try {
			downloadTilkynningVertaka = getElectricalInstallationRendererBusiness().getPDFApplication(getRafverktaka());
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
			downloadTilkynningLokVerks = getElectricalInstallationRendererBusiness().getPDFReport(getRafverktaka());
			return true;
		}
		catch (RemoteException e) {
			throw new RuntimeException(e.getMessage());
		}
		catch (IOException e) {
			return false;
		}
	}
	
	
}
