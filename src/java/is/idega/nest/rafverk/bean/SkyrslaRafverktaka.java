package is.idega.nest.rafverk.bean;

import is.idega.nest.rafverk.domain.Rafverktaka;

import java.util.Iterator;
import java.util.List;

import javax.faces.event.ValueChangeEvent;

public class SkyrslaRafverktaka extends BaseBean{

	Rafverktaka rafverktaka;

	public Rafverktaka getRafverktaka() {
		return rafverktaka;
	}

	public void setRafverktaka(Rafverktaka rafverktaka) {
		this.rafverktaka = rafverktaka;
	}
	
	public boolean getRafverktakaSelected(){
		return (rafverktaka!=null);
	}
	
	public void onChangeRafverktaka(ValueChangeEvent event){
		Object oNewValue = event.getNewValue();
		//HtmlSelectOneMenu menu = (HtmlSelectOneMenu) event.getComponent();
		//Object o = menu.getValue();
		
		String rafverktakaId = (String)oNewValue;
		List rafverktokur = getInitialData().getAllRafverktokurListi();
		for (Iterator iter = rafverktokur.iterator(); iter.hasNext();) {
			Rafverktaka verktaka = (Rafverktaka) iter.next();
			if(rafverktakaId.equals(verktaka.getId())){
				setRafverktaka(verktaka);
				break;
			}
		}
		
	}
	
}
