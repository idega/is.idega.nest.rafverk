package is.idega.nest.rafverk.domain;

import is.postur.Gata;
import is.postur.Postnumer;

import com.idega.util.StringHandler;

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
	
	public String getDisplay(){
		String name = StringHandler.replaceIfEmpty(getGata().getNafn(), "");
		String tempHusnumer = StringHandler.replaceIfEmpty(getHusnumer(), "");
		String tempHushluti = StringHandler.replaceIfEmpty(getHushluti(), "");
		Gata tempGata = getGata();
		String tempPostnumer = null;
		if (tempGata != null) {
			Postnumer tempPostnumer2 = tempGata.getPostnumer();
			if (tempPostnumer2 != null) {
				tempPostnumer = tempPostnumer2.getName();
			}
		}
		tempPostnumer = StringHandler.replaceIfEmpty(tempPostnumer, "");
		return name + " " + tempHusnumer +" "+ tempHushluti + " " + tempPostnumer;
	}
	
	
}
