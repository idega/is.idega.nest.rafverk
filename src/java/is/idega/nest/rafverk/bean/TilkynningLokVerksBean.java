/*
 * $Id: TilkynningLokVerksBean.java,v 1.4 2007/03/08 14:27:55 thomas Exp $
 * Created on Feb 13, 2007
 *
 * Copyright (C) 2007 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package is.idega.nest.rafverk.bean;

import is.idega.nest.rafverk.data.Maelir;
import java.util.List;


/**
 * 
 *  Last modified: $Date: 2007/03/08 14:27:55 $ by $Author: thomas $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.4 $
 */
public class TilkynningLokVerksBean {
	
	// first step 
	
    private String postnumer = null;
    
    private String gata = null;
    
    private String gotunumer = null;
    
    private String haed = null;
    
    private String nafnOrkukaupanda = null;
    
    private String kennitalaOrkukaupanda = null;
    
    private String heimasimiOrkukaupanda = null;
    
    private String vinnusimiOrkukaupanda = null;
    
    // second step 
    
    private String tilkynnt = null;
    
    private String tilkynntAnnad = null;
    
    private String notkunarflokkur = null;
    
    private String skyring = null;
    
    private String spennukerfi = null;
    
    private String annad = null;
    
    private List varnarradstoefun = null;
    
    private List jardskaut = null;
    
    private String jardskautAnnad = null;
    
    private Maelir maelir = null;
    
    private String skyringar = null;
    
    // third step
    
    private String hringrasarvidam = null;
    
    private String skammhlaupsstraumur = null;
    
    private String einangrunNeysluveitu = null;
    
    private String hringrasarvidnamJardskaut = null;
    
    private String skammhlaupsstraumurNeysluveitu = null;
    
    private String hringrasarvidnamNeysluveitu = null;
    
    private String maeldSpennaFasiN = null;
    
    private String maeldSpennaFasiFasi = null;
    
    private String lekastraumsrofi = null;
    
    private String spennuhaekkunUtleysingVolt = null;
    
    private String lekastraumsrofaUtleysingMillisecond = null;
    
    private String skyringarMaelingar = null;

    
	public TilkynningLokVerksBean() {
    	initialize();
    }
	
	private void initialize() {
		// initialize maelir
		maelir = new Maelir();
		
	}
	
	public String store() {
		return "store";
	}
	
	public String send() {
		return "send";
	}
	

	// generated getter and setter methods
	
	public String getAnnad() {
		return annad;
	}

	
	public void setAnnad(String annad) {
		this.annad = annad;
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

	
	public List getJardskaut() {
		return jardskaut;
	}

	
	public void setJardskaut(List jardskaut) {
		this.jardskaut = jardskaut;
	}

	
	public String getJardskautAnnad() {
		return jardskautAnnad;
	}

	
	public void setJardskautAnnad(String jardskautAnnad) {
		this.jardskautAnnad = jardskautAnnad;
	}

	
	public String getKennitalaOrkukaupanda() {
		return kennitalaOrkukaupanda;
	}

	
	public void setKennitalaOrkukaupanda(String kennitalaOrkukaupanda) {
		this.kennitalaOrkukaupanda = kennitalaOrkukaupanda;
	}

	
	public Maelir getMaelir() {
		return maelir;
	}

	
	public void setMaelir(Maelir maelir) {
		this.maelir = maelir;
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

	
	public String getPostnumer() {
		return postnumer;
	}

	
	public void setPostnumer(String postnumer) {
		this.postnumer = postnumer;
	}

	
	public String getSkyring() {
		return skyring;
	}

	
	public void setSkyring(String skyring) {
		this.skyring = skyring;
	}

	
	public String getSkyringar() {
		return skyringar;
	}

	
	public void setSkyringar(String skyringar) {
		this.skyringar = skyringar;
	}

	
	public String getSpennukerfi() {
		return spennukerfi;
	}

	
	public void setSpennukerfi(String spennukerfi) {
		this.spennukerfi = spennukerfi;
	}

	
	public String getTilkynnt() {
		return tilkynnt;
	}

	
	public void setTilkynnt(String tilkynnt) {
		this.tilkynnt = tilkynnt;
	}

	
	public String getTilkynntAnnad() {
		return tilkynntAnnad;
	}

	
	public void setTilkynntAnnad(String tilkynntAnnad) {
		this.tilkynntAnnad = tilkynntAnnad;
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

	
	public String getEinangrunNeysluveitu() {
		return einangrunNeysluveitu;
	}

	
	public void setEinangrunNeysluveitu(String einangrunNeysluveitu) {
		this.einangrunNeysluveitu = einangrunNeysluveitu;
	}

	
	public String getHringrasarvidam() {
		return hringrasarvidam;
	}

	
	public void setHringrasarvidam(String hringrasarvidam) {
		this.hringrasarvidam = hringrasarvidam;
	}

	
	public String getHringrasarvidnamJardskaut() {
		return hringrasarvidnamJardskaut;
	}

	
	public void setHringrasarvidnamJardskaut(String hringrasarvidnamJardskaut) {
		this.hringrasarvidnamJardskaut = hringrasarvidnamJardskaut;
	}

	
	public String getHringrasarvidnamNeysluveitu() {
		return hringrasarvidnamNeysluveitu;
	}

	
	public void setHringrasarvidnamNeysluveitu(String hringrasarvidnamNeysluveitu) {
		this.hringrasarvidnamNeysluveitu = hringrasarvidnamNeysluveitu;
	}

	
	public String getLekastraumsrofaUtleysingMillisecond() {
		return lekastraumsrofaUtleysingMillisecond;
	}

	
	public void setLekastraumsrofaUtleysingMillisecond(String lekastraumsrofaUtleysingMillisecond) {
		this.lekastraumsrofaUtleysingMillisecond = lekastraumsrofaUtleysingMillisecond;
	}

	
	public String getLekastraumsrofi() {
		return lekastraumsrofi;
	}

	
	public void setLekastraumsrofi(String lekastraumsrofi) {
		this.lekastraumsrofi = lekastraumsrofi;
	}

	
	public String getMaeldSpennaFasiFasi() {
		return maeldSpennaFasiFasi;
	}

	
	public void setMaeldSpennaFasiFasi(String maeldSpennaFasiFasi) {
		this.maeldSpennaFasiFasi = maeldSpennaFasiFasi;
	}

	
	public String getMaeldSpennaFasiN() {
		return maeldSpennaFasiN;
	}

	
	public void setMaeldSpennaFasiN(String maeldSpennaFasiN) {
		this.maeldSpennaFasiN = maeldSpennaFasiN;
	}

	
	public String getSkammhlaupsstraumur() {
		return skammhlaupsstraumur;
	}

	
	public void setSkammhlaupsstraumur(String skammhlaupsstraumur) {
		this.skammhlaupsstraumur = skammhlaupsstraumur;
	}

	
	public String getSkammhlaupsstraumurNeysluveitu() {
		return skammhlaupsstraumurNeysluveitu;
	}

	
	public void setSkammhlaupsstraumurNeysluveitu(String skammhlaupsstraumurNeysluveitu) {
		this.skammhlaupsstraumurNeysluveitu = skammhlaupsstraumurNeysluveitu;
	}

	
	public String getSkyringarMaelingar() {
		return skyringarMaelingar;
	}

	
	public void setSkyringarMaelingar(String skyringarMaelingar) {
		this.skyringarMaelingar = skyringarMaelingar;
	}

	
	public String getSpennuhaekkunUtleysingVolt() {
		return spennuhaekkunUtleysingVolt;
	}

	
	public void setSpennuhaekkunUtleysingVolt(String spennuhaekkunUtleysingVolt) {
		this.spennuhaekkunUtleysingVolt = spennuhaekkunUtleysingVolt;
	}


	
	

//	public Map getList() {
//		return maelirListMap;
//	}
//	
//	public String store() {
//		return "store";
//	}
//	
//	public String send() {
//		return "send";
//	}

	// generated getter and setter methods
	
	
	
}