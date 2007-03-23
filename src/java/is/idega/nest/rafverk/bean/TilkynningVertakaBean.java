/*
 * $Id: TilkynningVertakaBean.java,v 1.12 2007/03/23 16:13:15 thomas Exp $
 * Created on Feb 13, 2007
 *
 * Copyright (C) 2007 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package is.idega.nest.rafverk.bean;

import is.idega.nest.rafverk.business.ElectricalInstallationBusiness;
import is.idega.nest.rafverk.data.Maelir;
import is.idega.nest.rafverk.domain.ElectricalInstallation;
import is.idega.nest.rafverk.domain.ElectricalInstallationHome;
import is.idega.nest.rafverk.domain.Fasteign;
import is.idega.nest.rafverk.domain.FasteignaEigandi;
import is.idega.nest.rafverk.domain.Heimilisfang;
import is.idega.nest.rafverk.domain.Meter;
import is.idega.nest.rafverk.domain.MeterHome;
import is.idega.nest.rafverk.domain.Orkufyrirtaeki;
import is.idega.nest.rafverk.domain.Orkukaupandi;
import is.idega.nest.rafverk.domain.Rafverktaka;
import is.idega.nest.rafverk.domain.Rafverktaki;
import is.idega.nest.rafverk.fmr.FMRLookupBean;
import is.postur.Gata;
import is.postur.Postnumer;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import com.idega.business.IBOLookup;
import com.idega.data.IDOLookup;
import com.idega.idegaweb.IWApplicationContext;
import com.idega.presentation.IWContext;


/**
 * 
 *  Last modified: $Date: 2007/03/23 16:13:15 $ by $Author: thomas $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.12 $
 */
public class TilkynningVertakaBean {
	
	private Rafverktaka rafverktaka = null;
	
	private ElectricalInstallationBusiness electricalInstallationBusiness = null;
	
	// first step 
	
    private String orkuveitufyrirtaeki = null;
    
    private String postnumer = null;
    
    private String gata = null;
    
    private String gotunumer = null;
    
    private String fastanumer = null;
    
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
		stadurMaelir = new Maelir();
		// initialize list of maelir
		maelirListMap = new HashMap();
		for (int i = 0; i < InitialData.MAELIR_CONTEXT.length; i++) {
			List list = new ArrayList();
			// initialize all lists with an invalid instance of maelir
			Maelir.addInvalidInstance(list);
			maelirListMap.put(InitialData.MAELIR_CONTEXT[i], list);
		}
	}
	
	private void storeRafvertaka() {
//		Orkukaupandi orkukaupandi = new Orkukaupandi();
//		orkukaupandi.setNafn(getNafnOrkukaupanda());
//		orkukaupandi.setKennitala(getKennitalaOrkukaupanda());
//		orkukaupandi.setHeimasimi(getHeimasimiOrkukaupanda());
//		orkukaupandi.setVinnusimi(getVinnusimiOrkukaupanda());
//		
//		Heimilisfang heimilisfang = new Heimilisfang();
//		Gata gataObject = new Gata();
//		gataObject.setNafn(getGata());
//		Postnumer postnumerObject = new Postnumer();
//		postnumerObject.setNumer(getPostnumer());
//		gataObject.setPostnumer(postnumerObject);
//		heimilisfang.setGata(gataObject);
//		heimilisfang.setHusnumer(getGotunumer());
//		heimilisfang.setHushluti(getHaed());
//		
//		orkukaupandi.setHeimilisfang(heimilisfang);
//		
//		// second step
//		
//		Rafverktaka newRafverktaka = new Rafverktaka();
//		newRafverktaka.setOrkukaupandi(orkukaupandi);
//		
//		Orkufyrirtaeki orkufyrirtaeki = new Orkufyrirtaeki();
//		orkufyrirtaeki.setName(getOrkuveitufyrirtaeki());
//		newRafverktaka.setOrkufyrirtaeki(orkufyrirtaeki);
//		
//		newRafverktaka.setNotkunarflokkur(getNotkunarflokkur());
//		newRafverktaka.setSpennukerfi(getSpennukerfi());
//		newRafverktaka.setAnnad(getAnnad());
//		newRafverktaka.setVarnarradstoefun(getVarnarradstoefun());
//		
//		String tempStadur = getStadurMaelir().getStadur();
//		Maelir tempMaelir = new Maelir();
//		tempMaelir.setStadur(tempStadur);
//		newRafverktaka.setStadurMaelir(tempMaelir);
		

		
		// store as case
		boolean successfullyStored = false;
		try {
			successfullyStored = getElectricalInstallationBusiness().storeManagedBeans(getRafverktaka(), this, BaseBean.getTilkynningLokVerksBean());
		}
		catch (RemoteException e) {
			throw new RuntimeException(e.getMessage());
		}
		BaseBean.getRafverktokuListi().addRafvertaka(getRafverktaka());
		
		

		

		
	}

	public Map getList() {
		return maelirListMap;
	}
	
	public String store() {
		storeRafvertaka();
		return "store";
	}
	
	public String send() {
		storeRafvertaka();
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
		return getRafverktaka().getOrkukaupandi().getKennitala();
	}




	
	public void setKennitalaOrkukaupanda(String kennitalaOrkukaupanda) {
		if(kennitalaOrkukaupanda!=null&!kennitalaOrkukaupanda.equals("")){
			getRafverktaka().getOrkukaupandi().setKennitala(kennitalaOrkukaupanda);
		}
	}




	
	public String getNafnOrkukaupanda() {
		return getRafverktaka().getOrkukaupandi().getNafn();
	}




	
	public void setNafnOrkukaupanda(String nafnOrkukaupanda) {
		if(nafnOrkukaupanda!=null&!nafnOrkukaupanda.equals("")){
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

	public void setFastanumer(String fastanumer) {
		if(fastanumer!=null&&!fastanumer.equals("")){
			String postnumer = getPostnumer();
			Fasteign fasteign = getFMRLookup().getFasteignByFastanumerAndPostnumer(fastanumer,postnumer);
			FasteignaEigandi eigandi = fasteign.getEigandi();
			if(eigandi!=null){
				setNafnOrkukaupanda(eigandi.getNafn());
				setKennitalaOrkukaupanda(eigandi.getKennitala());
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
			List gotuListi = InitialData.getInitialData().getGotuListiByPostnumer(postnumer);
			for (Iterator iter = gotuListi.iterator(); iter.hasNext();) {
				Gata gata = (Gata) iter.next();
				SelectItem item = new SelectItem();
				item.setLabel(gata.getNafn());
				item.setValue(gata.getNafn());
				selects.add(item);
			}
		}
		return selects;
	}

	public ElectricalInstallationBusiness getElectricalInstallationBusiness() {
		if (electricalInstallationBusiness == null) {
			try {
				FacesContext context = FacesContext.getCurrentInstance();
				IWContext iwContext = IWContext.getIWContext(context);
				IWApplicationContext iwac = iwContext.getApplicationContext();
				electricalInstallationBusiness = (ElectricalInstallationBusiness) 
					IBOLookup.getServiceInstance(iwac, ElectricalInstallationBusiness.class);
			}
			catch (RemoteException rme) {
				throw new RuntimeException(rme.getMessage());
			}
		}
		return electricalInstallationBusiness;
	}

	
	public Rafverktaka getRafverktaka() {
		return rafverktaka;
	}

	
	public void setRafverktaka(Rafverktaka rafverktaka) {
		this.rafverktaka = rafverktaka;
	}
	
	
	
}
