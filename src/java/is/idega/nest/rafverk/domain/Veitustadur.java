package is.idega.nest.rafverk.domain;

import is.fmr.landskra.Fasteign;
import is.postur.Gata;

public class Veitustadur extends BaseBean{

	String id;
	//Gata gata;
	Fasteign fasteign;
	String gotuNumer;
	String hushluti="";
	
	public Gata getGata() {
		return getFasteign().getGata();
	}
	/*public void setGata(Gata gata) {
		this.gata = gata;
	}*/
	
	public String getGotuNumer() {
		return gotuNumer;
	}
	public void setGotuNumer(String gotuNumer) {
		this.gotuNumer = gotuNumer;
	}
	public String getHushluti() {
		return hushluti;
	}
	public void setHushluti(String hushluti) {
		this.hushluti = hushluti;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getVeitustadur() {
		return getGata().getNafn()+" "+getGotuNumer()+" "+getHushluti();
	}
	public Fasteign getFasteign() {
		return fasteign;
	}
	public void setFasteign(Fasteign fasteign) {
		this.fasteign = fasteign;
	}
}
