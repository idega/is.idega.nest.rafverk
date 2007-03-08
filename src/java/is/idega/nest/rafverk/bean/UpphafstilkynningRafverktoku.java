package is.idega.nest.rafverk.bean;

import is.idega.nest.rafverk.domain.Fasteign;
import is.idega.nest.rafverk.domain.Rafverktaka;
import is.idega.nest.rafverk.domain.Rafverktaki;
import is.idega.nest.rafverk.fmr.FMRLookupBean;

import java.util.ArrayList;
import java.util.List;

public class UpphafstilkynningRafverktoku extends BaseBean {

	//List possibleFasteignir;
	Rafverktaka rafverktaka;
	
	public UpphafstilkynningRafverktoku(){
		
		Rafverktaka verktaka = new Rafverktaka();
		verktaka.setRafverktaki(new Rafverktaki());
		setRafverktaka(verktaka);
		
	}

	public Rafverktaka getRafverktaka() {
		return rafverktaka;
	}

	public void setRafverktaka(Rafverktaka verktaka) {
		this.rafverktaka = verktaka;
	}


	
	
}
