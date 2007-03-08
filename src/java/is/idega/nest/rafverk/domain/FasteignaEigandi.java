package is.idega.nest.rafverk.domain;

public class FasteignaEigandi {

	String kennitala;
	String nafn;
	
	public FasteignaEigandi(){}
	
	public FasteignaEigandi(String kt,String nafn){
		setNafn(nafn);
		setKennitala(kt);
	}
	
	public String getKennitala() {
		return kennitala;
	}
	public void setKennitala(String kennitala) {
		this.kennitala = kennitala;
	}
	public String getNafn() {
		return nafn;
	}
	public void setNafn(String nafn) {
		this.nafn = nafn;
	}
	
}
