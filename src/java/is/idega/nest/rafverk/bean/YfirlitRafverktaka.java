package is.idega.nest.rafverk.bean;


import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;

import is.idega.nest.rafverk.domain.Rafverktaki;

public class YfirlitRafverktaka extends RafverktokuListi{
	

	Rafverktaki selectedRafverktaki;
	public YfirlitRafverktaka() {
		FacesContext context = FacesContext.getCurrentInstance();
		String verktakiId = (String) context.getExternalContext().getRequestParameterMap().get("rafverktakiId");
		Rafverktaki verktaki = getInitialData().getRafverktakiById(verktakiId);
		setSelectedRafverktaki(verktaki);
	}
	
	public static YfirlitRafverktaka getInstance(){
		FacesContext context = FacesContext.getCurrentInstance();
		ValueBinding binding = context.getApplication().createValueBinding("#{YfirlitRafverktaka}");
		Object bean = binding.getValue(context);
		return (YfirlitRafverktaka) bean;
	}
	
	public List getRafverktokurWithStatus(String status) {
		return getSelectedRafverktaki().getRafverktokurWithStatus(status);
	}

	public List getAllRafverktokur() {
		return getSelectedRafverktaki().getRafverktokur();
	}

	
	public Rafverktaki getSelectedRafverktaki() {
		return selectedRafverktaki;
	}

	public void setSelectedRafverktaki(Rafverktaki selectedRafverktaki) {
		this.selectedRafverktaki = selectedRafverktaki;
	}

	public String updateSelectedRafverktaki() {
		return null;
	}
}
