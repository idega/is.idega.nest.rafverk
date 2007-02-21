package is.idega.nest.rafverk.domain;

public class Heimilisfang extends BaseBean{
	
	String id;
	Gata gata;
	String husnumer;
	String hushluti;
	
	public Gata getGata() {
		return gata;
	}
	public void setGata(Gata gata) {
		this.gata = gata;
	}
	public String getHushluti() {
		return hushluti;
	}
	public void setHushluti(String hushluti) {
		this.hushluti = hushluti;
	}
	public String getHusnumer() {
		return husnumer;
	}
	public void setHusnumer(String husnumer) {
		this.husnumer = husnumer;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getHeimilisfang(){
		return getGata().getNafn()+" "+getHusnumer()+" "+getHushluti()+" "+getGata().getPostnumer().getName();
	}
	
}
