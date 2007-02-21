package is.idega.nest.rafverk.domain;

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
	String merking;
	String notkun;
	Gata gata;
	
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

}
