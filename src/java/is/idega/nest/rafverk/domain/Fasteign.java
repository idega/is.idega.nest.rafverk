package is.idega.nest.rafverk.domain;

import is.postur.Gata;

/**
 * RealEstate
 * 
 * @author Tryggvi
 *
 */
public class Fasteign extends BaseBean{
	
	String fasteignId;
	String nafn;
	String fastaNumer;
	String merking;// 100101 Bygging Hæð Nr. innan hæðar
	String notkun; //Íbúð, Skrifstofa
	
	Gata gata;
	String gotuNumer;
	FasteignaEigandi eigandi;
	
	public String getFastaNumer() {
		return fastaNumer;
	}
	public void setFastaNumer(String fastaNumer) {
		this.fastaNumer = fastaNumer;
	}
	public String getFasteignId() {
		return fasteignId;
	}
	public void setFasteignId(String fasteignId) {
		this.fasteignId = fasteignId;
	}
	public Gata getGata() {
		return gata;
	}
	public void setGata(Gata gata) {
		this.gata = gata;
	}
	public String getMerking() {
		return merking;
	}
	public void setMerking(String merking) {
		this.merking = merking;
	}
	public String getNafn() {
		return nafn;
	}
	public void setNafn(String nafn) {
		this.nafn = nafn;
	}
	public String getNotkun() {
		return notkun;
	}
	public void setNotkun(String notkun) {
		this.notkun = notkun;
	}

	public FasteignaEigandi getEigandi() {
		return eigandi;
	}
	public void setEigandi(FasteignaEigandi eigandi) {
		this.eigandi = eigandi;
	}
	
	public String getMerkingHumanReadable(){
		String merking = getMerking();
		
		String sBygging = merking.substring(0,2);
		Integer iBygging = new Integer(sBygging);
		String sHaed = merking.substring(2,4);
		Integer iHaed = new Integer(sHaed);
		String sNr = merking.substring(4,6);
		Integer iNr = new Integer(sNr);
		
		String notkun = getNotkun();
		
		String haedHuman = null;
		if(iHaed.intValue()==0){
			haedHuman = "jarðhæð";
		}
		else{
			haedHuman = iHaed+". hæð";
		}
		
		String str="";
		if(iBygging.intValue()==1){
			str = haedHuman+" "+notkun+" "+iNr;
		}
		else{
			str = "Bygging "+iBygging+" "+haedHuman+" "+notkun+" "+iNr;
		}
		
		return str;
		
	}
	
	public String getDescription(){
		
		String desc = getNafn()+" - "+getMerkingHumanReadable();
		FasteignaEigandi eigandi = getEigandi();
		if(eigandi!=null){
			desc+=" ("+eigandi.getNafn()+")";
		}
		desc += " [Fastanúmer "+getFastaNumer()+" "+getMerking()+"]";
		return desc;
	}
	
	public String getGotuNumer() {
		return gotuNumer;
	}
	public void setGotuNumer(String gotuNumer) {
		this.gotuNumer = gotuNumer;
	}
}
