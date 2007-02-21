package is.idega.nest.rafverk.domain;

public class Postnumer extends BaseBean{
	
	String id;
	String numer;
	String name;
	String sveitarfelag;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumer() {
		return numer;
	}
	public void setNumer(String numer) {
		this.numer = numer;
	}
	public String getSveitarfelag() {
		return sveitarfelag;
	}
	public void setSveitarfelag(String sveitarfelag) {
		this.sveitarfelag = sveitarfelag;
	}
	
	
}
