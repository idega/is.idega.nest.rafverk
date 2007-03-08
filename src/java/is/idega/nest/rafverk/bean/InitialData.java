package is.idega.nest.rafverk.bean;

import is.idega.nest.rafverk.domain.Fasteign;
import is.idega.nest.rafverk.domain.Heimilisfang;
import is.idega.nest.rafverk.domain.Orkufyrirtaeki;
import is.idega.nest.rafverk.domain.Orkukaupandi;
import is.idega.nest.rafverk.domain.Rafverktaka;
import is.idega.nest.rafverk.domain.Rafverktaki;
import is.idega.nest.rafverk.domain.Veitustadur;
import is.postur.Gata;
import is.postur.Gotuskra;
import is.postur.Postnumer;
import is.postur.Postnumeraskra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import com.idega.idegaweb.IWResourceBundle;
import com.idega.presentation.IWContext;

public class InitialData extends BaseBean {
	
	public static final String BUNDLE_IDENTIFIER = "is.idega.nest.rafverk";
	
	private IWResourceBundle resourceBundle = null;
	
	// first step
	
	private static final String[] NOTKUNARFLOKKUR = {
		"Íbúðarhúsnæði", "IBUDARHUSNAEDI", 
		"Sumarhús", "SUMARHUS",
		"Landb./Garðyrkja", "GARDYRKJA",
		"Iðnaður", "IDNADUR", 
		"Fiskiðnaður", "FISKIDNADUR",
		"Verslun/Þjónusta", "PJONUSTA", 
		"Opinber stofnun", "OPINBER_STOFNUN", 
		"Bráðabirgðanotkun", "BRADABIRGDANOTKUN"
		};
	
	private static final String[] HEIMTAUG = {
		"Ný", "NY",
		"Óbreytt", "OBREYTT",
		"Breytt", "BREYTT",
		"Tekin", "TEKIN"
		};
	
	private static final String[] ADALTAFLA = HEIMTAUG;
	
	private static final String[] HEIMTAUG_TENGIST = {
		"Aðaltöflu", "ADALTOEFLU",
		"Stofntengibox", "STOFNTENGIBOX",
		"Mælakassa", "MAELAKASSA",
		"Stofnvarkassa", "STOFNVARKASSA"
		};

	private static final String[] VARNARRADSTOEFUN = {
		"Núllun", "NULLUN",
		"um sérskaut", "UM_SERSKAUT",
		"Lekastraumsrofvörn", "LEKASTRAUMSROFVOERN",
		"Önnur", "OENNUR"
		};
	
	
	
	
	// second step
	
	private static final String[] BEIDNI = {
		"Spennusetningu", "SPENNUSETNINGU",
		"Mælitæki", "MAELITAEKI",
		"Tengingu rafhita", "TENGINGU_RAFHITA",
		"Niðurtöku", "NIDURTÖKU",
		"Taxtabreytingu", "TAXTABREYTINGU",
		"Tengingu án mælis", "TENINGU_AN_MAELIS"
		};
	
	private static final String[] SPENNUKERFI = {
		"3-230V", "3-230V",
		"2N-460/230V", "2N-460/230V",
		"3N-400/230V", "3N-400/230V",
		"IN-230V", "IN-230V",
		"Annað", "ANNAD"
		};

	private static final String[] MAELI = {
		"1-fasa", "1-FASA",
		"3-fasa", "3-FASA"
		};
	
	private static final String[] FLUTT = {
		"á","A",
		"af", "AF"
		};
	
	private static final String[] TEGUND = {
		"Aðalheimtaug", "ADALHEIMTAUG",
		"Götuljósakerfi", "GOETULJOSAKERFI",
		"Bráðabirgðaheimtaug", "BRADABIRGDAHEIMTAUG"
		};
	
	private static final String[] STRENGUR = {
		"Jarðstrengur", "JARDSTRENGUR",
		"Loftstrengur/ - lina", "LOFTSTRENGUR_LINA"
		};
	
	private static final String[] TENGISTADUR = {
		"Rofi i dreifistöð", "ROFI_I_DREIFISTOED",
		"Múffa", "MUFFA",
		"Loftlina", "LOFTLINA",
		"Götuljósastólpi", "GOETULJOSASTOLPI",
		"Vör i stólpa", "VOER_I_STOLPA",
		};
	
	private static final String[] TENGING = {
		"Veita uppfyllir tæknilega tengiskilmála rafveitna og tengd", "VEITA",
		"Ekki tengt", "EKKI",
		"Athugasemdir sjá bakhlið", "BAKHLID"};
	
	// third step
	
	private static final String TAKA = "taka";
	private static final String FYRIR = "fyrir";
	private static final String SETJA = "setja";
	private static final String FLUTT_A = "fluttA";
	private static final String FLUTT_AF = "fluttAf";
	private static final String HJALPATAEKI = "hjalpataeki";
	private static final String STRAUMSPENNA = "straumspenna";
	
	public static final String[] MAELIR_TYPES = {TAKA,FYRIR, SETJA, FLUTT_A, FLUTT_AF, HJALPATAEKI, STRAUMSPENNA};
	
	// tilkynning lok verks
	
	private static final String[] TILKYNNT = {
		"Nýlögn", "NYLOEGN",
		"Breyting", "BREYING",
		"Viðbót", "VIDBOT",
		"Annað", "ANNAD"
		};
	
	private static final String[] HUSNAEDIS = {
		"Íbúð", "IBUD",
		"Sumarhús", "SUMARHUS",
		"Þjónusta/Iðnaður", "PJONUSTA"
		};
	
	private static final String[] JARDSKAUT = {
		"Vatnspípukerfi", "VATNSPIPUKERFI",
		"Sökkulskaut", "SOEKKULSKAUT"
		};
	
	private static final String[] LEKASTRAUMSROFI = {
		"í lagi", "I_LAGI",
		"ekki til staðar", "EKKI_TIL_STADAR"
		};
	
	public List getRafveituListi(){
		ArrayList listi = new ArrayList();
		
		Orkufyrirtaeki or = getOrkuveitan();
		listi.add(or);
		
		Orkufyrirtaeki rarik = getRarik();
		listi.add(rarik);
		
		return listi;	
	}

	public Orkufyrirtaeki getRarik() {
		Orkufyrirtaeki rarik = new Orkufyrirtaeki();
		rarik.setId("RARIK");
		rarik.setName("Rarik");
		return rarik;
	}

	public Orkufyrirtaeki getOrkuveitan() {
		Orkufyrirtaeki or = new Orkufyrirtaeki();
		or.setId("OR");
		or.setName("Orkuveita Reykjavikur");
		return or;
	}
	
	public List getRafveituListiSelects(){
		
		ArrayList listi = new ArrayList();
		List rafveitur = getRafveituListi();
		for (Iterator iter = rafveitur.iterator(); iter.hasNext();) {
			Orkufyrirtaeki fyrirtaeki = (Orkufyrirtaeki) iter.next();
			SelectItem item = new SelectItem();
			item.setLabel(fyrirtaeki.getName());
			item.setValue(fyrirtaeki.getId());
			listi.add(item);
		}
		
		return listi;
	}
	
	public List getNotkunarflokkurListi() {
		return Arrays.asList(NOTKUNARFLOKKUR);
	}
	
	public List getHeimtaugListi() {
		return Arrays.asList(HEIMTAUG);
	}
	
	public List getHeimtaugTengistListi() {
		return Arrays.asList(HEIMTAUG_TENGIST);
	}
	
	public List getAdaltaflaListi() {
		return Arrays.asList(ADALTAFLA);
	}
	
	public List getVarnarradstoefunListi() {
		return Arrays.asList(VARNARRADSTOEFUN);
	}
	
	// second page
	
	public List getBeidniListi() {
		return Arrays.asList(BEIDNI);
	}
	
	public List getSpennukerfiListi() {
		return Arrays.asList(SPENNUKERFI);
	}
	
	public List getMaeliListi() {
		return Arrays.asList(MAELI);
	}
	
	public List getFluttListi() {
		return Arrays.asList(FLUTT);
	}
	
	// third page
	
	public List getTegundListi() {
		return Arrays.asList(TEGUND);
	}
	
	public List getStrengurListi() {
		return Arrays.asList(STRENGUR);
	}
	
	public List getTengistadurListi() {
		return Arrays.asList(TENGISTADUR);
	}
	
	public List getTengingListi() {
		return Arrays.asList(TENGING);
	}
	
	// tilkynning lok verks
	
	public List getTilkynntListi() {
		return Arrays.asList(TILKYNNT);
	}
	
	public List getHusnaedisListi() {
		return Arrays.asList(HUSNAEDIS);
	}
	
	public List getJardskautListi() {
		return Arrays.asList(JARDSKAUT);
	}
	
	public List getLekastraumsrofiListi() {
		return Arrays.asList(LEKASTRAUMSROFI);
	}
	
	
	public List getPostnumeraListi(){
		/*ArrayList listi = new ArrayList();
		
		
		Postnumer rvk101 = getRvk101();
		listi.add(rvk101);
		
		Postnumer rvk104 = getRvk104();
		listi.add(rvk104);
		
		return listi;*/
		

		Postnumeraskra skra= Postnumeraskra.getCached();
		return skra.getPostnumer();
		
	}
	
	public List getPostnumeraListiSelects(){
		ArrayList selects = new ArrayList();
		
		SelectItem item0 = new SelectItem();
		item0.setLabel("Veldu póstnúmer:");
		item0.setValue("");
		selects.add(item0);
		
		List postnumer = getPostnumeraListi();
		for (Iterator iter = postnumer.iterator(); iter.hasNext();) {
			Postnumer numer = (Postnumer) iter.next();
			SelectItem item = new SelectItem();
			item.setLabel(numer.getFulltNafn());
			item.setValue(numer.getNumer());
			selects.add(item);
		}
		return selects;
	}
	
	public List getGotuListiSelects(){
		ArrayList selects = new ArrayList();
		List gotuListi = getGotuListi();
		for (Iterator iter = gotuListi.iterator(); iter.hasNext();) {
			Gata gata = (Gata) iter.next();
			SelectItem item = new SelectItem();
			item.setLabel(gata.getNafn());
			item.setValue(gata.getGotuId());
			selects.add(item);
		}
		return selects;
	}

	public Postnumer getRvk104() {
		Postnumer rvk104 = new Postnumer();
		rvk104.setSveitarfelag("Reykjavik");
		rvk104.setId("104");
		rvk104.setNumer("104");
		rvk104.setName("104 Reykjavik");
		return rvk104;
	}

	public Postnumer getRvk101() {
		Postnumer rvk101 = new Postnumer();
		rvk101.setSveitarfelag("Reykjavik");
		rvk101.setId("101");
		rvk101.setNumer("101");
		rvk101.setName("101 Reykjavik");
		return rvk101;
	}
	
	public List getGotuListi(){
		/*ArrayList listi = new ArrayList();
		
		Gata adalstr = getGataAdalstraeti();
		listi.add(adalstr);
		
		Gata austurstraeti = getGataAusturstraeti();
		listi.add(austurstraeti);
		
		Gata engjavegur = getGataEngjavegur();
		listi.add(engjavegur);
		
		Gata engjateigur = getGataEngjateigur();
		listi.add(engjateigur);
		
		
		return listi;*/
		
		Gotuskra skra=null;
		skra = Gotuskra.getCached();
		return skra.getGotur();
	}
	
	public List getGotuListiByPostnumer(String postnumer){
		return Gotuskra.getCached().getGoturByPostnumer(postnumer);
	}


	public Gata getGataEngjateigur() {
		Gata engjateigur = new Gata();
		//engjateigur.setGotuId("104eingjavegur");
		engjateigur.setNafn("Engjavegur");
		engjateigur.setNafnThagufall("Engjavegi");
		engjateigur.setPostnumer(getRvk104());
		return engjateigur;
	}


	public Gata getGataEngjavegur() {
		Gata engjavegur = new Gata();
		//engjavegur.setGotuId("104engjateigur");
		engjavegur.setNafn("Engjateigur");
		engjavegur.setNafnThagufall("Engjategi");
		engjavegur.setPostnumer(getRvk104());
		return engjavegur;
	}


	public Gata getGataAusturstraeti() {
		Gata austurstraeti = new Gata();
		//austurstraeti.setGotuId("101adalstr");
		austurstraeti.setNafn("Austurstraeti");
		austurstraeti.setNafnThagufall("Austurstraeti");
		austurstraeti.setPostnumer(getRvk101());
		return austurstraeti;
	}


	public Gata getGataAdalstraeti() {
		Gata adalstr = new Gata();
		//adalstr.setGotuId("101adalstr");
		adalstr.setNafn("Adalstraeti");
		adalstr.setNafnThagufall("Adalstraeti");
		adalstr.setPostnumer(getRvk101());
		return adalstr;
	}
	
	public Fasteign getFasteignAusturstraeti1() {
		Fasteign austurstraeti = new Fasteign();
		austurstraeti.setFastaNumer("1234567");
		austurstraeti.setNafn("Austurstraeti 11");
		austurstraeti.setGata(getGataAusturstraeti());
		return austurstraeti;
	}


	public Fasteign getFasteignAdalstraeti13() {
		Fasteign adalstr = new Fasteign();
		adalstr.setFastaNumer("7654321");
		adalstr.setNafn("Adalstraeti 13");
		adalstr.setGata(getGataAusturstraeti());
		return adalstr;
	}
	

	public Fasteign getFasteignEngjavegur6() {
		Fasteign engjavegur = new Fasteign();
		engjavegur.setFastaNumer("1234123");
		engjavegur.setNafn("Engjavegur 6");
		engjavegur.setGata(getGataEngjavegur());
		return engjavegur;
	}
	
	
	public Rafverktaki getRafverktakiJon() {
		Rafverktaki jon = new Rafverktaki();
		jon.setId("1");
		jon.setNafn("Jon Jonsson");
		jon.setHeimasimi("5553333");
		jon.setNafnFyrirtaekis("Rafverktaka Jons");
		jon.setKennitala("1010100009");
		jon.setVinnusimi("5552222");
		jon.setStada(Rafverktaki.STADA_VIRKUR);
		return jon;
	}

	public Rafverktaki getRafverktakiGudmundur() {
		Rafverktaki gummi = new Rafverktaki();
		gummi.setId("2");
		gummi.setNafn("Gudmundur Gudmundsson");
		gummi.setHeimasimi("4445580");
		gummi.setNafnFyrirtaekis("Rafvirkjun Gudmunds");
		gummi.setKennitala("1212120109");
		gummi.setVinnusimi("4443322");
		gummi.setStada(Rafverktaki.STADA_VIRKUR);
		gummi.setHeimilisfang(getHeimilisfangAdalstraeti1());
		return gummi;
	}
	
	public Rafverktaki getRafverktakiEirikur() {
		Rafverktaki eirikur = new Rafverktaki();
		eirikur.setId("3");
		eirikur.setNafn("Eiriks Eiriks");
		eirikur.setHeimasimi("4445580");
		eirikur.setNafnFyrirtaekis("Rafvirkjun Eiriks");
		eirikur.setKennitala("141414142039");
		eirikur.setVinnusimi("4443322");
		eirikur.setStada(Rafverktaki.STADA_VIRKUR);
		eirikur.setHeimilisfang(getHeimilisfangAdalstraeti1());
		return eirikur;
	}
	
	public Orkukaupandi getOrkukaupandiSigurdur(){
		Orkukaupandi sigurdur = new Orkukaupandi();
		sigurdur.setNafn("Sigurdur Sigurddsson");
		sigurdur.setVinnusimi("5606060");
		sigurdur.setKennitala("1313133009");
		sigurdur.setHeimilisfang(getHeimilisfangAdalstraeti2());
		return sigurdur;
	}

	public Orkukaupandi getOrkukaupandiGunnar(){
		Orkukaupandi gunnar = new Orkukaupandi();
		gunnar.setNafn("Gunnar Gunnarsson");
		gunnar.setVinnusimi("5230933");
		gunnar.setKennitala("0909091239");
		gunnar.setHeimilisfang(getHeimilisfangAdalstraeti1());
		return gunnar;
	}


	public Heimilisfang getHeimilisfangAdalstraeti1() {
		Heimilisfang adalstraeti = new Heimilisfang();
		adalstraeti.setGata(getGataAdalstraeti());
		adalstraeti.setHusnumer("1");
		return adalstraeti;
	}
	
	public Heimilisfang getHeimilisfangAdalstraeti2() {
		Heimilisfang adalstraeti = new Heimilisfang();
		adalstraeti.setGata(getGataAdalstraeti());
		adalstraeti.setHusnumer("2");
		return adalstraeti;
	}


	public Veitustadur getVeitustadurAusturstraeti1() {
		Veitustadur stadur = new Veitustadur();
		stadur.setFasteign(getFasteignAusturstraeti1());
		stadur.setGotuNumer("1");
		stadur.setHushluti("3 haed");
		stadur.setId("1");
		return stadur;
	}

	public Veitustadur getVeitustadurEngjavegur6() {
		Veitustadur stadur = new Veitustadur();
		stadur.setFasteign(getFasteignEngjavegur6());
		stadur.setGotuNumer("6");
		stadur.setId("6");
		return stadur;
	}

	public List getAllRafverktakaListi() {
		List listi = new ArrayList();
		listi.add(getRafverktakiGudmundur());
		listi.add(getRafverktakiJon());
		listi.add(getRafverktakiEirikur());
		return listi;
	}
	
	protected Rafverktaki getRafverktakiById(String id){
		List l = getAllRafverktakaListi();
		for (Iterator iter = l.iterator(); iter.hasNext();) {
			Rafverktaki verktaki = (Rafverktaki) iter.next();
			if(verktaki.getId().equals(id)){
				return verktaki;
			}
		}
		return null;
	}
	
	public List getAllRafverktokurListi(){
		
		InitialData initialData = getInitialData();
		
		ArrayList list = new ArrayList();
		
		Rafverktaka verktaka1 = new Rafverktaka();
		verktaka1.setId("id1");
		verktaka1.setOrkufyrirtaeki(initialData.getOrkuveitan());
		verktaka1.setRafverktaki(initialData.getRafverktakiGudmundur());
		verktaka1.setOrkukaupandi(initialData.getOrkukaupandiSigurdur());
		verktaka1.setVeitustadur(initialData.getVeitustadurAusturstraeti1());
		verktaka1.setStada(Rafverktaka.STADA_TILKYNNT_LOK);		
		list.add(verktaka1);
		
		Rafverktaka verktaka2 = new Rafverktaka();
		verktaka2.setId("id2");
		verktaka2.setOrkufyrirtaeki(initialData.getRarik());
		verktaka2.setRafverktaki(initialData.getRafverktakiJon());
		verktaka2.setOrkukaupandi(initialData.getOrkukaupandiGunnar());
		verktaka2.setVeitustadur(initialData.getVeitustadurEngjavegur6());
		verktaka2.setStada(Rafverktaka.STADA_MOTTEKIN);		
		list.add(verktaka2);
		
		Rafverktaka verktaka3 = new Rafverktaka();
		verktaka3.setId("id3");
		verktaka3.setOrkufyrirtaeki(initialData.getOrkuveitan());
		verktaka3.setRafverktaki(initialData.getRafverktakiGudmundur());
		verktaka3.setOrkukaupandi(initialData.getOrkukaupandiGunnar());
		verktaka3.setVeitustadur(initialData.getVeitustadurAusturstraeti1());
		verktaka3.setStada(Rafverktaka.STADA_LOKID);
		list.add(verktaka3);
		
		Rafverktaka verktaka4 = new Rafverktaka();
		verktaka4.setId("id4");
		verktaka4.setOrkufyrirtaeki(initialData.getOrkuveitan());
		verktaka4.setRafverktaki(initialData.getRafverktakiJon());
		verktaka4.setOrkukaupandi(initialData.getOrkukaupandiSigurdur());
		verktaka4.setVeitustadur(initialData.getVeitustadurEngjavegur6());
		verktaka4.setStada(Rafverktaka.STADA_I_SKODUN);
		list.add(verktaka4);
		
		return list;
	}

	public List getAllRafverktokurWithStatusListi(String status){
		List verktokur = getAllRafverktokurListi();
		List list = new ArrayList();
		for (Iterator iter = verktokur.iterator(); iter.hasNext();) {
			Rafverktaka verktaka = (Rafverktaka) iter.next();
			if(verktaka.getStada().equals(status)){
				list.add(verktaka);
			}
		}
		return list;
	}
	
	public List getNotkunarflokkurListiSelects() {
		return getSelectItemList(getNotkunarflokkurListi());
	}
	
	public List getHeimtaugListiSelects() {
		return getSelectItemList(getHeimtaugListi());
	}
	
	public List getHeimtaugTengistListiSelects() {
		return getSelectItemList(getHeimtaugTengistListi());
	}
	
	public List getAdaltaflaListiSelects() {
		return getSelectItemList(getAdaltaflaListi());
	}
	
	public List getVarnarradstoefunListiSelects() {
		return getSelectItemList(getVarnarradstoefunListi());
	}
	
	// second page
	
	public List getBeidniListiSelects() {
		return getSelectItemList(getBeidniListi());
	}
	
	public List getSpennukerfiListiSelects() {
		return getSelectItemList(getSpennukerfiListi());
	}
	
	public List getMaeliListiSelects() {
		return getSelectItemList(getMaeliListi());
	}
	
	public List getFluttListiSelects() {
		return getSelectItemList(getFluttListi());
	}
	
	// third page
	
	public List getTegundListiSelects() {
		return getSelectItemList(getTegundListi());
	}
	
	public List getStrengurListiSelects() {
		return getSelectItemList(getStrengurListi());
	}
	
	public List getTengistadurListiSelects() {
		return getSelectItemList(getTengistadurListi());
	}
	
	public List getTengingListiSelects() {
		return getSelectItemList(getTengingListi());
	}
	
	
	// tilkynning lok verks
	
	public List getTilkynntListiSelects() {
		return getSelectItemList(getTilkynntListi());
	}
	
	public List getHusnaedisListiSelects() {
		return getSelectItemList(getHusnaedisListi());
	}
	
	
	public List getJardskautListiSelects() {
		return getSelectItemList(getJardskautListi());
	}
	
	public List getLekastraumsrofiListiSelects() {
		return getSelectItemList(getLekastraumsrofiListi());
	}
	                      
	
	private List getSelectItemList(List myList) {
		IWResourceBundle localResourceBundle = getResourceBundle();
		ArrayList selects = new ArrayList();
		for (Iterator iter = myList.iterator(); iter.hasNext();) {
			String label = (String) iter.next();
			String value = (String) iter.next();
			// use value as key
			// label = localResourceBundle.getLocalizedString(value, label);
			SelectItem item = new SelectItem();
			item.setLabel(label);
			item.setValue(value);
			selects.add(item);
		}
		return selects;
	}
	
	private IWResourceBundle getResourceBundle() {
		if (resourceBundle == null) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			IWContext context = IWContext.getIWContext(facesContext);
			resourceBundle = context.getIWMainApplication().getBundle(BUNDLE_IDENTIFIER).getResourceBundle(context);
		}
		return resourceBundle;
	}

}
