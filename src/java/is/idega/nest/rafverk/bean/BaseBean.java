package is.idega.nest.rafverk.bean;

import java.io.Serializable;

import javax.faces.context.FacesContext;

public class BaseBean implements Serializable{

	public static InitialData getInitialData() {
		return (InitialData) getBean("RafverktakaInitialdata");
	}

	public static RafverktokuListi getRafverktokuListi(){
		return (RafverktokuListi) getBean("RafverktokuListi");
	}
	
	public static TilkynningLokVerksBean getTilkynningLokVerksBean() {
		return (TilkynningLokVerksBean) getBean("TilkynningLokVerksBean");
	}
	
	public static TilkynningVertakaBean getTilkynningVertakaBean() {
		return (TilkynningVertakaBean) getBean("TilkynningVertakaBean");
	}
	
	public static ChangeElectricianBean getChangeElectricianBean() {
		return (ChangeElectricianBean) getBean("ChangeElectricianBean");
	}
	
	public static Object getBean(String bean) {
		FacesContext context = FacesContext.getCurrentInstance();
		return context.getApplication().getVariableResolver().resolveVariable(context, bean);
		//ValueBinding binding = context.getApplication().createValueBinding(bean);
		//return binding.getValue(context);
	}
}