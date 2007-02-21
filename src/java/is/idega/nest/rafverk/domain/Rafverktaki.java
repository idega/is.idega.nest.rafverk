package is.idega.nest.rafverk.domain;

import is.idega.nest.rafverk.bean.BaseBean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Rafverktaki extends BaseBean{

	
	public static String STADA_VIRKUR="VIRKUR";
	public static String STADA_OVIRKUR="OVIRKUR";
	
	String id;
	String nafn;
	String nafnFyrirtaekis;
	String kennitala;
	Heimilisfang heimilisfang;
	String heimasimi;
	String vinnusimi;
	
	String tegund;
	String stada=STADA_VIRKUR;
	
	
	public String getHeimasimi() {
		return heimasimi;
	}
	public void setHeimasimi(String heimasimi) {
		this.heimasimi = heimasimi;
	}
	public Heimilisfang getHeimilisfang() {
		return heimilisfang;
	}
	public void setHeimilisfang(Heimilisfang heimilisfang) {
		this.heimilisfang = heimilisfang;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getKennitala() {
		return kennitala;
	}
	public void setKennitala(String kennitala) {
		this.kennitala = kennitala;
	}
	public String getNafnFyrirtaekis() {
		return nafnFyrirtaekis;
	}
	public void setNafnFyrirtaekis(String nafnFyrirtaekis) {
		this.nafnFyrirtaekis = nafnFyrirtaekis;
	}
	public String getStada() {
		return stada;
	}
	public void setStada(String stada) {
		this.stada = stada;
	}
	public String getTegund() {
		return tegund;
	}
	public void setTegund(String tegund) {
		this.tegund = tegund;
	}
	public String getVinnusimi() {
		return vinnusimi;
	}
	public void setVinnusimi(String vinnusimi) {
		this.vinnusimi = vinnusimi;
	}
	public String getNafn() {
		return nafn;
	}
	public void setNafn(String nafn) {
		this.nafn = nafn;
	}
	
	public List getRafverktokurWithStatus(String status){
		List verktokur = getRafverktokur();
		List list = new ArrayList();
		for (Iterator iter = verktokur.iterator(); iter.hasNext();) {
			Rafverktaka verktaka = (Rafverktaka) iter.next();
			if(verktaka.getStada().equals(status)){
				list.add(verktaka);
			}
		}
		return list;
	}
	
	public List getRafverktokur(){
		List all = BaseBean.getInitialData().getAllRafverktokurListi();
		List myRafverktokur = new ArrayList();
		for (Iterator iter = all.iterator(); iter.hasNext();) {
			Rafverktaka verktaka = (Rafverktaka) iter.next();
			if(verktaka.getRafverktaki().getId().equals(this.getId())){
				myRafverktokur.add(verktaka);
			}
		}
		return myRafverktokur;
	}
	
	public void update(){
		
	}
	
}
