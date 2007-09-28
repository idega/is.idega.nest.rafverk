package is.idega.nest.rafverk.bean;

import java.io.Serializable;
import java.rmi.RemoteException;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import uk.ltd.getahead.dwr.WebContextFactory;

import com.idega.business.IBOLookup;
import com.idega.business.IBOService;
import com.idega.idegaweb.IWApplicationContext;
import com.idega.presentation.IWContext;
import com.idega.user.data.User;

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
	
	public static User getCurrentUser() {
		FacesContext context = FacesContext.getCurrentInstance();
		IWContext iwContext = IWContext.getIWContext(context);
		return iwContext.getCurrentUser();
	}
	
	public static Object getBean(String bean) {
		FacesContext context = FacesContext.getCurrentInstance();
		return context.getApplication().getVariableResolver().resolveVariable(context, bean);
		//ValueBinding binding = context.getApplication().createValueBinding(bean);
		//return binding.getValue(context);
	}
	
	public static InitialData getInitialDataByDWR() {
		return (InitialData) getBeanByDWR("RafverktakaInitialdata");
	}	

	public static RafverktokuListi getRafverktokuListiByDWR(){
		return (RafverktokuListi) getBeanByDWR("RafverktokuListi");
	}
	
	public static TilkynningLokVerksBean getTilkynningLokVerksBeanByDWR() {
		return (TilkynningLokVerksBean) getBeanByDWR("TilkynningLokVerksBean");
	}
	
	public static TilkynningVertakaBean getTilkynningVertakaBeanByDWR() {
		return (TilkynningVertakaBean) getBeanByDWR("TilkynningVertakaBean");
	}
	
	public static ChangeElectricianBean getChangeElectricianBeanByDWR() {
		return (ChangeElectricianBean) getBeanByDWR("ChangeElectricianBean");
	}

	public static Object getBeanByDWR(String bean) {
		HttpSession session = WebContextFactory.get().getSession();
		return session.getAttribute(bean);
	}
	
	public static Object initializeServiceBean(Object myServiceBeanVariable, Class serviceBeanClass) {
		if (myServiceBeanVariable != null) {
			// nothing to do
			return myServiceBeanVariable;
		}
		return getSeviceBean(serviceBeanClass);
	}
	
	
	private static IBOService getSeviceBean(Class serviceBeanClass) {
		IBOService myServiceBean = null;
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			IWContext iwContext = IWContext.getIWContext(context);
			IWApplicationContext iwac = iwContext.getApplicationContext();
			myServiceBean = IBOLookup.getServiceInstance(iwac, serviceBeanClass);
		}
		catch (RemoteException rme) {
			throw new RuntimeException(rme.getMessage());
		}
		return myServiceBean;
	}
}