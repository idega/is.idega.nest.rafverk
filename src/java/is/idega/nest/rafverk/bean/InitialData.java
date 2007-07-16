package is.idega.nest.rafverk.bean;

import is.idega.nest.rafverk.business.ElectricalInstallationState;
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
import javax.faces.model.SelectItem;

public class InitialData extends BaseBean {
	
	// first step
	
	// navigation
	public static final String TILKYNNING_VERTAKA_URI = "/pages/rafverktaki/rafverk/tilkynningvertaka/";
	
	public static final String TILKYNNING_LOK_VERKS_URI = "/pages/rafverktaki/rafverk/tilkynninglokverks/";
	
	public static final String YFIRLIT_URI = "/pages/rafverktaki/rafverk/yfirlit/";
	
	public static final String NONE_URI = "none_uri";
	
	// special key for none street
	public static final String NONE_STREET = "none_street";
	
	public static final String NONE_REAL_ESTATE_SELECTION = "none_real_estate_selection";
	
	// special key (also used in validators)
	public static final String ANNAD = "ANNAD";
	
	public static final String RAFVEITUR = "Rafveitur";
	
	public static final String[] NOTKUNARFLOKKUR = {
		"Íbúðarhúsnæði", "IBUDARHUSNAEDI", 
		"Sumarhús", "SUMARHUS",
		"Landb./Garðyrkja", "GARDYRKJA",
		"Iðnaður", "IDNADUR", 
		"Fiskiðnaður", "FISKIDNADUR",
		"Verslun/Þjónusta", "THJONUSTA", 
		"Opinber stofnun", "OPINBER_STOFNUN", 
		"Bráðabirgðanotkun", "BRADABIRGDANOTKUN"
		};
	
	public static final List NOTKUNARFLOKKUR_LIST = Arrays.asList(NOTKUNARFLOKKUR);
	
	public static final String[] HEIMTAUG = {
		"Ný", "NY",
		"Óbreytt", "OBREYTT",
		"Breytt", "BREYTT",
		"Tekin", "TEKIN"
		};
	
	public static final List HEIMTAUG_LIST = Arrays.asList(HEIMTAUG);
	
	public static final String[] ADALTAFLA = HEIMTAUG;
	
	public static final List ADALTAFLA_LIST = Arrays.asList(ADALTAFLA);
	
	public static final String[] HEIMTAUG_TENGIST = {
		"Aðaltöflu", "ADALTOEFLU",
		"Stofntengibox", "STOFNTENGIBOX",
		"Mælakassa", "MAELAKASSA",
		"Stofnvarkassa", "STOFNVARKASSA"
		};
	
	public static final List HEIMTAUG_TENGIST_LIST = Arrays.asList(HEIMTAUG_TENGIST);

	public static final String[] VARNARRADSTOEFUN = {
		"Núllun", "NULLUN",
		"um sérskaut", "UM_SERSKAUT",
		"Lekastraumsrofvörn", "LEKASTRAUMSROFVOERN",
		"Önnur", "OENNUR"
		};
	
	public static final List VARNARRADSTOEFUN_LIST = Arrays.asList(VARNARRADSTOEFUN);
	
	
	// second step
	
	public static final String[] BEIDNI = {
		"Spennusetningu", "SPENNUSETNINGU",
		"Mælitæki", "MAELITAEKI",
		"Tengingu rafhita", "TENGINGU_RAFHITA",
		"Niðurtöku", "NIDURTÖKU",
		"Taxtabreytingu", "TAXTABREYTINGU",
		"Tengingu án mælis", "TENINGU_AN_MAELIS"
		};

	public static final List BEIDNI_LIST = Arrays.asList(BEIDNI);
	
	public static final String[] SPENNUKERFI = {
		"3-230V", "3-230V",
		"2N-460/230V", "2N-460/230V",
		"3N-400/230V", "3N-400/230V",
		"IN-230V", "IN-230V",
		"Annað", ANNAD
		};
	
	public static final List SPENNUKERFI_LIST = Arrays.asList(SPENNUKERFI);

	public static final String[] MAELI = {
		"1-fasa", "1-FASA",
		"3-fasa", "3-FASA"
		};
	
	public static final List MAELI_LIST = Arrays.asList(MAELI);
	
	public static final String[] FLUTT = {
		"á","A",
		"af", "AF"
		};
	
	public static final List FLUTT_LIST = Arrays.asList(FLUTT);
	
	public static final String[] TEGUND = {
		"Aðalheimtaug", "ADALHEIMTAUG",
		"Götuljósakerfi", "GOETULJOSAKERFI",
		"Bráðabirgðaheimtaug", "BRADABIRGDAHEIMTAUG"
		};
	
	public static final List TEGUND_LIST = Arrays.asList(TEGUND);
	
	public static final String[] STRENGUR = {
		"Jarðstrengur", "JARDSTRENGUR",
		"Loftstrengur/ - lina", "LOFTSTRENGUR_LINA"
		};
	
	public static final List STRENGUR_LIST = Arrays.asList(STRENGUR);
	
	public static final String[] TENGISTADUR = {
		"Rofi i dreifistöð", "ROFI_I_DREIFISTOED",
		"Múffa", "MUFFA",
		"Loftlina", "LOFTLINA",
		"Götuljósastólpi", "GOETULJOSASTOLPI",
		"Vör i stólpa", "VOER_I_STOLPA",
		};
	
	public static final List TENGISTADUR_LIST = Arrays.asList(TENGISTADUR);
	
	public static final String[] TENGING = {
		"Veita uppfyllir tæknilega tengiskilmála rafveitna og tengd", "VEITA",
		"Ekki tengt", "EKKI",
		"Athugasemdir sjá bakhlið", "BAKHLID"};
	
	public static final List TENGING_LIST = Arrays.asList(TENGING);
	// third step
	
	// !!!! NEVER change the strings of the following maelir types variables since they are referenced in JSF pages !!!!!
	
	// special case (a special input field)
	public static final String STADUR = "place";
	
	// special case (two special input fields (number and place) in the second form)
	public static final String METER_IN_REPORT = "meterInReport";
	
	public static final String TAKA = "taka";
	public static final String FYRIR = "fyrir";
	public static final String SETJA = "setja";
	public static final String FLUTT_A = "fluttA";
	public static final String FLUTT_AF = "fluttAf";
	public static final String HJALPATAEKI = "hjalpataeki";
	public static final String STRAUMSPENNA = "straumspenna";
	
	public static final String[] MAELIR_CONTEXT = {TAKA ,FYRIR, SETJA, FLUTT_A, FLUTT_AF, HJALPATAEKI, STRAUMSPENNA};
	
	// tilkynning lok verks
	
	public static final String[] TILKYNNT = {
		"Nýlögn", "NYLOEGN",
		"Breyting", "BREYING",
		"Viðbót", "VIDBOT",
		"Annað", ANNAD
		};
	
	public static final List TILKYNNT_LIST = Arrays.asList(TILKYNNT);
	
	public static final String[] HUSNAEDIS = {
		"Íbúð", "IBUD",
		"Sumarhús", "SUMARHUS",
		"Þjónusta/Iðnaður", "THJONUSTA"
		};
	
	public static final List HUSNAEDIS_LIST = Arrays.asList(HUSNAEDIS);
	
	public static final String[] JARDSKAUT = {
		"Vatnspípukerfi", "VATNSPIPUKERFI",
		"Sökkulskaut", "SOEKKULSKAUT"
		};
	
	public static final List JARDSKAUT_LIST = Arrays.asList(JARDSKAUT);
	
	public static final String LEKASTRAUMSROFI_I_LAGI_KEY = "I_LAGI";
	public static final String LEKASTRAUMSROFI_EKKI_TIL_STADAR_KEY = "EKKI_TIL_STADAR";
	
	public static final String[] LEKASTRAUMSROFI = {
		"í lagi", LEKASTRAUMSROFI_I_LAGI_KEY,
		"ekki til staðar", LEKASTRAUMSROFI_EKKI_TIL_STADAR_KEY
		};
	
	public static final List LEKASTRAUMSROFI_LIST = Arrays.asList(LEKASTRAUMSROFI);

	// rafverktoekur page
	
	public static final String[] OPTIONS_PER_ELECTRICAL_INSTALLATION = {
		"Fara á...", NONE_URI,
		"Þjónustubeiðni", TILKYNNING_VERTAKA_URI,
		"Skýrsla", TILKYNNING_LOK_VERKS_URI,
		"Yfirlit", YFIRLIT_URI
	};
	
	public static final List OPTIONS_PER_ELECTRICAL_INSTALLATION_LIST = Arrays.asList(OPTIONS_PER_ELECTRICAL_INSTALLATION);
	

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
	
	public List getNotkunarflokkurListi() {
		return NOTKUNARFLOKKUR_LIST;
	}
	
	public List getHeimtaugListi() {
		return HEIMTAUG_LIST;
	}
	
	public List getHeimtaugTengistListi() {
		return HEIMTAUG_TENGIST_LIST;
	}
	
	public List getAdaltaflaListi() {
		return ADALTAFLA_LIST;
	}
	
	public List getVarnarradstoefunListi() {
		return VARNARRADSTOEFUN_LIST;
	}
	
	// second page
	
	public List getBeidniListi() {
		return BEIDNI_LIST;
	}
	
	public List getSpennukerfiListi() {
		return SPENNUKERFI_LIST;
	}
	
	public List getMaeliListi() {
		return MAELI_LIST;
	}
	
	public List getFluttListi() {
		return FLUTT_LIST;
	}
	
	// third page
	
	public List getTegundListi() {
		return TEGUND_LIST;
	}
	
	public List getStrengurListi() {
		return STRENGUR_LIST;
	}
	
	public List getTengistadurListi() {
		return TENGISTADUR_LIST;
	}
	
	public List getTengingListi() {
		return TENGING_LIST;
	}
	
	// tilkynning lok verks
	
	public List getTilkynntListi() {
		return TILKYNNT_LIST;
	}
	
	public List getHusnaedisListi() {
		return HUSNAEDIS_LIST;
	}
	
	public List getJardskautListi() {
		return JARDSKAUT_LIST;
	}
	
	public List getLekastraumsrofiListi() {
		return LEKASTRAUMSROFI_LIST;
	}
	
	// rafverktoekur page
	
	public List getOptionsPerElectricalInstallationListi() {
		return OPTIONS_PER_ELECTRICAL_INSTALLATION_LIST;
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
		/*Postnumer rvk104 = new Postnumer();
		rvk104.setSveitarfelag("Reykjavik");
		rvk104.setId("104");
		rvk104.setNumer("104");
		rvk104.setName("104 Reykjavik");
		return rvk104;*/
		
		return Postnumeraskra.getCached().getPostnumerByNumer("104");
	}

	public Postnumer getRvk101() {
		/*Postnumer rvk101 = new Postnumer();
		rvk101.setSveitarfelag("Reykjavik");
		rvk101.setId("101");
		rvk101.setNumer("101");
		rvk101.setName("101 Reykjavik");
		return rvk101;*/
		return Postnumeraskra.getCached().getPostnumerByNumer("101");
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
		/*Gata engjateigur = new Gata();
		//engjateigur.setGotuId("104eingjavegur");
		engjateigur.setNafn("Engjavegur");
		engjateigur.setNafnThagufall("Engjavegi");
		engjateigur.setPostnumer(getRvk104());
		return engjateigur;*/
		return Gotuskra.getCached().getGataByNafnAndPostnumer("Engjateigur", "104");
	}


	public Gata getGataEngjavegur() {
		/*Gata engjavegur = new Gata();
		//engjavegur.setGotuId("104engjateigur");
		engjavegur.setNafn("Engjateigur");
		engjavegur.setNafnThagufall("Engjategi");
		engjavegur.setPostnumer(getRvk104());
		return engjavegur;
		*/
		return Gotuskra.getCached().getGataByNafnAndPostnumer("Engjavegur", "104");
	}


	public Gata getGataAusturstraeti() {
		/*Gata austurstraeti = new Gata();
		//austurstraeti.setGotuId("101adalstr");
		austurstraeti.setNafn("Austurstraeti");
		austurstraeti.setNafnThagufall("Austurstraeti");
		austurstraeti.setPostnumer(getRvk101());
		return austurstraeti;*/
		return Gotuskra.getCached().getGataByNafnAndPostnumer("Austurstræti", "101");
	}


	public Gata getGataAdalstraeti() {
		/*Gata adalstr = new Gata();
		//adalstr.setGotuId("101adalstr");
		adalstr.setNafn("Adalstraeti");
		adalstr.setNafnThagufall("Adalstraeti");
		adalstr.setPostnumer(getRvk101());
		return adalstr;*/
		return Gotuskra.getCached().getGataByNafnAndPostnumer("Aðalstræti", "101");
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
		verktaka1.setStada(ElectricalInstallationState.TILKYNNT_LOK);		
		list.add(verktaka1);
		
		Rafverktaka verktaka2 = new Rafverktaka();
		verktaka2.setId("id2");
		verktaka2.setOrkufyrirtaeki(initialData.getRarik());
		verktaka2.setRafverktaki(initialData.getRafverktakiJon());
		verktaka2.setOrkukaupandi(initialData.getOrkukaupandiGunnar());
		verktaka2.setVeitustadur(initialData.getVeitustadurEngjavegur6());
		verktaka2.setStada(ElectricalInstallationState.MOTTEKIN);		
		list.add(verktaka2);
		
		Rafverktaka verktaka3 = new Rafverktaka();
		verktaka3.setId("id3");
		verktaka3.setOrkufyrirtaeki(initialData.getOrkuveitan());
		verktaka3.setRafverktaki(initialData.getRafverktakiGudmundur());
		verktaka3.setOrkukaupandi(initialData.getOrkukaupandiGunnar());
		verktaka3.setVeitustadur(initialData.getVeitustadurAusturstraeti1());
		verktaka3.setStada(ElectricalInstallationState.LOKID);
		list.add(verktaka3);
		
		Rafverktaka verktaka4 = new Rafverktaka();
		verktaka4.setId("id4");
		verktaka4.setOrkufyrirtaeki(initialData.getOrkuveitan());
		verktaka4.setRafverktaki(initialData.getRafverktakiJon());
		verktaka4.setOrkukaupandi(initialData.getOrkukaupandiSigurdur());
		verktaka4.setVeitustadur(initialData.getVeitustadurEngjavegur6());
		verktaka4.setStada(ElectricalInstallationState.I_SKODUN);
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
	
	// rafverktoekur page
	
	public List getOptionsPerElectricalInstallationListiSelects() {
		return getSelectItemList(getOptionsPerElectricalInstallationListi());
	}
	                      
	
	private List getSelectItemList(List myList) {
		ArrayList selects = new ArrayList();
		for (Iterator iter = myList.iterator(); iter.hasNext();) {
			String label = (String) iter.next();
			String value = (String) iter.next();
			SelectItem item = new SelectItem();
			item.setLabel(label);
			item.setValue(value);
			selects.add(item);
		}
		return selects;
	}

	
	
}
