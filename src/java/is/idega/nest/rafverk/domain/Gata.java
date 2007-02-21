package is.idega.nest.rafverk.domain;

/**
 * Street
 * 
 * @author Tryggvi
 *
 */
public class Gata extends BaseBean{
	
	String gotuId;
	String nafn;
	String nafnThagufall;
	Postnumer postnumer;
	
	public String getGotuId() {
		return gotuId;
	}
	public void setGotuId(String gotuId) {
		this.gotuId = gotuId;
	}
	public String getNafn() {
		return nafn;
	}
	public void setNafn(String nafn) {
		this.nafn = nafn;
	}
	public String getNafnThagufall() {
		return nafnThagufall;
	}
	public void setNafnThagufall(String nafnThagufall) {
		this.nafnThagufall = nafnThagufall;
	}
	public Postnumer getPostnumer() {
		return postnumer;
	}
	public void setPostnumer(Postnumer postnumer) {
		this.postnumer = postnumer;
	}
	
	
}
