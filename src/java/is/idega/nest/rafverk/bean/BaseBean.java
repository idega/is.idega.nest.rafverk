package is.idega.nest.rafverk.bean;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;

public class BaseBean implements Serializable{

	public static InitialData getInitialData() {
		return (InitialData) getBean("#{RafverktakaInitialdata}");
	}

	public static RafverktokuListi getRafverktokuListi(){
		return (RafverktokuListi) getBean("#{RafverktokuListi}");
	}
	
	public static Object getBean(String bean) {
		FacesContext context = FacesContext.getCurrentInstance();
		ValueBinding binding = context.getApplication().createValueBinding(bean);
		return binding.getValue(context);
	}
}