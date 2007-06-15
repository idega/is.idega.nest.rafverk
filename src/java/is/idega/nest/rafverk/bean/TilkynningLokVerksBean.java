/*
 * $Id: TilkynningLokVerksBean.java,v 1.14 2007/06/15 16:20:34 thomas Exp $
 * Created on Feb 13, 2007
 *
 * Copyright (C) 2007 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package is.idega.nest.rafverk.bean;

import is.idega.nest.rafverk.data.Maelir;
import is.idega.nest.rafverk.data.MaelirList;
import is.idega.nest.rafverk.domain.Rafverktaka;
import is.postur.Gata;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;


/**
 * 
 *  Last modified: $Date: 2007/06/15 16:20:34 $ by $Author: thomas $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.14 $
 */
public class TilkynningLokVerksBean {
	
	private Rafverktaka rafverktaka = null;
	
	// first step 
	
    private String postnumer = null;
    
    private String gata = null;
    
    private String gotunumer = null;
    
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
	
	public void initialize() {
	    postnumer = null;
	    gata = null;
	    gotunumer = null;
	    // second step 
	    tilkynnt = null;
	    tilkynntAnnad = null;
	    notkunarflokkur = null;
	    skyring = null;
	    spennukerfi = null;
	    annad = null;
	    varnarradstoefun = null;
	    jardskaut = null;
	    jardskautAnnad = null;
	    maelir = null;
	    skyringar = null;
	    // third step
	    hringrasarvidam = null;
	    skammhlaupsstraumur = null;
	    einangrunNeysluveitu = null;
	    hringrasarvidnamJardskaut = null;
	    skammhlaupsstraumurNeysluveitu = null;
	    hringrasarvidnamNeysluveitu = null;
	    maeldSpennaFasiN = null;
	    maeldSpennaFasiFasi = null;
	    lekastraumsrofi = null;
	    spennuhaekkunUtleysingVolt = null;
	    lekastraumsrofaUtleysingMillisecond = null;
	    skyringarMaelingar = null;
		// initialize maelir
		maelir = MaelirList.getEmptyMaelirContextMeterInReport();
		
	}
	
//	public void populate(Rafverktaka rafverktaka) {
//		Orkukaupandi orkukaupandi = rafverktaka.getOrkukaupandi();
//		setNafnOrkukaupanda(orkukaupandi.getNafn());
//		setKennitalaOrkukaupanda(orkukaupandi.getKennitala());
//		setHeimasimiOrkukaupanda(orkukaupandi.getHeimasimi());
//		setVinnusimiOrkukaupanda(orkukaupandi.getVinnusimi());
//		
//		Heimilisfang heimilisfang = orkukaupandi.getHeimilisfang();
//		Gata gataObject = heimilisfang.getGata();
//		setGata(gataObject.getNafn());
//		
//		Postnumer postnumerObject = gataObject.getPostnumer();
//		setPostnumer(postnumerObject.getNumer());
//		
//		setGotunumer(heimilisfang.getHusnumer());
//		
//		// second step
//		
//		Maelir tempMaelir = new Maelir(InitialData.METER_IN_REPORT,0);
//		Maelir tempStadurMaelir = rafverktaka.getStadurMaelir();
//		if (tempStadurMaelir != null) {
//			String tempStadur = tempStadurMaelir.getStadur();
//			tempMaelir.setStadur(tempStadur);
//			setMaelir(tempStadurMaelir);
//		}
//		
//		setNotkunarflokkur(rafverktaka.getNotkunarflokkur());
//		setSpennukerfi(rafverktaka.getSpennukerfi());
//		setAnnad(rafverktaka.getAnnad());
//		setVarnarradstoefun(rafverktaka.getVarnarradstoefun());
//		
//	}
	
	
	/**
	 * Called by JSF page (report)
	 * 
	 * @return
	 */
	public String store() {
		TilkynningVertakaBean tilkynningVertakaBean = BaseBean.getTilkynningVertakaBean();
		return tilkynningVertakaBean.storeApplicationReport();
	}
	
	/**
	 * Called by JSF page (report)
	 * 
	 * @return
	 */
	public String send() {
		TilkynningVertakaBean tilkynningVertakaBean = BaseBean.getTilkynningVertakaBean();
		return tilkynningVertakaBean.sendApplicationReport();
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

	
	public String getHeimasimiOrkukaupanda() {
		return getRafverktaka().getOrkukaupandi().getHeimasimi();
	}

	
	public void setHeimasimiOrkukaupanda(String heimasimiOrkukaupanda) {
		getRafverktaka().getOrkukaupandi().setHeimasimi(heimasimiOrkukaupanda);
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
		return getRafverktaka().getOrkukaupandi().getKennitala();
	}

	
	public void setKennitalaOrkukaupanda(String kennitalaOrkukaupanda) {
		getRafverktaka().getOrkukaupandi().setKennitala(kennitalaOrkukaupanda);
	}

	
	public Maelir getMaelir() {
		return maelir;
	}

	
	public void setMaelir(Maelir maelir) {
		this.maelir = maelir;
	}

	
	public String getNafnOrkukaupanda() {
		return getRafverktaka().getOrkukaupandi().getNafn();
	}

	
	public void setNafnOrkukaupanda(String nafnOrkukaupanda) {
		getRafverktaka().getOrkukaupandi().setNafn(nafnOrkukaupanda);
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
		return getRafverktaka().getOrkukaupandi().getVinnusimi();
	}

	
	public void setVinnusimiOrkukaupanda(String vinnusimiOrkukaupanda) {
		getRafverktaka().getOrkukaupandi().setVinnusimi(vinnusimiOrkukaupanda);
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

	
	public Rafverktaka getRafverktaka() {
		return rafverktaka;
	}

	
	public void setRafverktaka(Rafverktaka rafverktaka) {
		this.rafverktaka = rafverktaka;
	}
	
	
	
}