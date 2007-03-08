/*
 * $Id: TilkynningVertakaBean.java,v 1.6 2007/03/08 15:21:38 thomas Exp $
 * Created on Feb 13, 2007
 *
 * Copyright (C) 2007 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package is.idega.nest.rafverk.bean;

import is.idega.nest.rafverk.data.Maelir;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 
 *  Last modified: $Date: 2007/03/08 15:21:38 $ by $Author: thomas $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.6 $
 */
public class TilkynningVertakaBean {
	
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
	
	

	public Map getList() {
		return maelirListMap;
	}
	
	// navigation methods
	
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
		tilkynningLokVersBean.setPostnumer(getPostnumer());
		tilkynningLokVersBean.setGata(getGata());
		tilkynningLokVersBean.setGotunumer(getGotunumer());
		tilkynningLokVersBean.setHaed(getHaed());
		// line break
		tilkynningLokVersBean.setNafnOrkukaupanda(getNafnOrkukaupanda());
		tilkynningLokVersBean.setKennitalaOrkukaupanda(getKennitalaOrkukaupanda());
		tilkynningLokVersBean.setHeimasimiOrkukaupanda(getHeimasimiOrkukaupanda());
		tilkynningLokVersBean.setVinnusimiOrkukaupanda(getVinnusimiOrkukaupanda());
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
		this.kennitalaOrkukaupanda = kennitalaOrkukaupanda;
	}




	
	public String getNafnOrkukaupanda() {
		return nafnOrkukaupanda;
	}




	
	public void setNafnOrkukaupanda(String nafnOrkukaupanda) {
		this.nafnOrkukaupanda = nafnOrkukaupanda;
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

}
