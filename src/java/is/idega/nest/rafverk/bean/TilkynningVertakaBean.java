/*
 * $Id: TilkynningVertakaBean.java,v 1.8 2007/03/08 22:54:32 thomas Exp $
 * Created on Feb 13, 2007
 *
 * Copyright (C) 2007 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package is.idega.nest.rafverk.bean;

import is.idega.nest.rafverk.data.Maelir;
import is.idega.nest.rafverk.domain.Fasteign;
import is.idega.nest.rafverk.domain.FasteignaEigandi;
import is.idega.nest.rafverk.domain.Heimilisfang;
import is.idega.nest.rafverk.domain.Orkukaupandi;
import is.idega.nest.rafverk.domain.Rafverktaka;
import is.idega.nest.rafverk.domain.Rafverktaki;
import is.idega.nest.rafverk.fmr.FMRLookupBean;
import is.postur.Gata;
import is.postur.Postnumer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;


/**
 * 
 *  Last modified: $Date: 2007/03/08 22:54:32 $ by $Author: thomas $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.8 $
 */
public class TilkynningVertakaBean {
	
	private Rafverktaki rafverktaka = null;
	
	// first step 
	
    private String orkuveitufyrirtaeki = null;
    
    private String postnumer = null;
    
    private String gata = null;
    
    private String gotunumer = null;
    
    private String haed = null;
    
    private String nafnOrkukaupanda = null;
    
    private String kennitalaOrkukaupanda = null;
    
    private String heimasimiOrkukaupanda = null;
    
    private String vinnusimiOrkukaupanda = null;
    
    private String fastanumer;
    private List fasteignaListi;
    
    // second step 
    
    private String notkunarflokkur = null;
    
    private String heimtaug = null;
    
    private String heimtaugTengist = null;
    
    private String stofn1;
    
    private String stofn2;
    
    private String stofn3;
    
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
	
	private void initialize() {
	    orkuveitufyrirtaeki = null;
	    postnumer = null;
	    gata = null;
	    gotunumer = null;
	    haed = null;
	    nafnOrkukaupanda = null;
	    kennitalaOrkukaupanda = null;
	    heimasimiOrkukaupanda = null;
	    vinnusimiOrkukaupanda = null;
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
		for (int i = 0; i < InitialData.MAELIR_TYPES.length; i++) {
			List list = new ArrayList();
			// initialize all lists with an invalid instance of maelir
			Maelir.addInvalidInstance(list);
			maelirListMap.put(InitialData.MAELIR_TYPES[i], list);
		}
	}
	
	private void storeRafvertaka() {
		Orkukaupandi orkukaupandi = new Orkukaupandi();
		orkukaupandi.setNafn(getNafnOrkukaupanda());
		orkukaupandi.setKennitala(getKennitalaOrkukaupanda());
		orkukaupandi.setHeimasimi(getHeimasimiOrkukaupanda());
		orkukaupandi.setVinnusimi(getVinnusimiOrkukaupanda());
		
		Heimilisfang heimilisfang = new Heimilisfang();
		Gata gataObject = new Gata();
		gataObject.setNafn(getGata());
		Postnumer postnumerObject = new Postnumer();
		postnumerObject.setNumer(getPostnumer());
		gataObject.setPostnumer(postnumerObject);
		heimilisfang.setGata(gataObject);
		heimilisfang.setHusnumer(getGotunumer());
		heimilisfang.setHushluti(getHaed());
		
		orkukaupandi.setHeimilisfang(heimilisfang);
		
		// second step
		
		Rafverktaka rafverktaka = new Rafverktaka();
		rafverktaka.setOrkukaupandi(orkukaupandi);
		

		
	}

	public Map getList() {
		return maelirListMap;
	}
	
	public String store() {
		return "store";
	}
	
	public String send() {
		return "send";
	}
	
	public String goToTilkynningLokVerks() {
		initializeTilkynningLokVerks();
		return "nextwizard";
	}
	
	public String goToTilkynningVertaka() {
		initialize();
		return "tilkynningvertaka";
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
		tilkynningLokVersBean.setHaed(getHaed());
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




	
	public String getHaed() {
		return haed;
	}




	
	public void setHaed(String haed) {
		this.haed = haed;
	}




	
	public String getHeimasimiOrkukaupanda() {
		return heimasimiOrkukaupanda;
	}




	
	public void setHeimasimiOrkukaupanda(String heimasimiOrkukaupanda) {
		this.heimasimiOrkukaupanda = heimasimiOrkukaupanda;
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
		return kennitalaOrkukaupanda;
	}




	
	public void setKennitalaOrkukaupanda(String kennitalaOrkukaupanda) {
		if(kennitalaOrkukaupanda!=null&!kennitalaOrkukaupanda.equals("")){
			this.kennitalaOrkukaupanda = kennitalaOrkukaupanda;
		}
	}




	
	public String getNafnOrkukaupanda() {
		return nafnOrkukaupanda;
	}




	
	public void setNafnOrkukaupanda(String nafnOrkukaupanda) {
		if(nafnOrkukaupanda!=null&!nafnOrkukaupanda.equals("")){
			this.nafnOrkukaupanda = nafnOrkukaupanda;
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
		return vinnusimiOrkukaupanda;
	}

	
	public void setVinnusimiOrkukaupanda(String vinnusimiOrkukaupanda) {
		this.vinnusimiOrkukaupanda = vinnusimiOrkukaupanda;
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
		//TODO: Implement lookup
		String sveitarfelagsNumer = "0000";
		
		String addr = getGata()+" "+getGotunumer();

		FMRLookupBean lookup = getFMRLookup();
		fasteignaListi = lookup.getFasteignir(sveitarfelagsNumer, addr);
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
			
			Fasteign fasteign = getFMRLookup().getFasteignByFastanumer(fastanumer);
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
				item.setValue(gata.getGotuId());
				selects.add(item);
			}
		}
		return selects;
	}

}
