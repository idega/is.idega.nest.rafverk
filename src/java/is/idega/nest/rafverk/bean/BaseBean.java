package is.idega.nest.rafverk.bean;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;

public class BaseBean implements Serializable{

	public static InitialData getInitialData() {
		FacesContext context = FacesContext.getCurrentInstance();
		ValueBinding binding = context.getApplication().createValueBinding("#{RafverktakaInitialdata}");
		Object bean = binding.getValue(context);
		return (InitialData) bean;
	}

	public RafverktokuListi getRafverktokuListi(){
		FacesContext context = FacesContext.getCurrentInstance();
		ValueBinding binding = context.getApplication().createValueBinding("#{RafverktokuListi}");
		Object bean = binding.getValue(context);
		return (RafverktokuListi) bean;
	}
}