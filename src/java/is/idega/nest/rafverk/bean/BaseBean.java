package is.idega.nest.rafverk.bean;

import is.idega.nest.rafverk.bean.constants.CaseConstants;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.directwebremoting.WebContextFactory;

import com.idega.business.IBOLookup;
import com.idega.business.IBOService;
import com.idega.idegaweb.IWApplicationContext;
import com.idega.idegaweb.IWBundle;
import com.idega.idegaweb.IWMainApplication;
import com.idega.idegaweb.IWResourceBundle;
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

	public static UIComponent findComponentInRoot(String id) {
	    FacesContext facesContext = FacesContext.getCurrentInstance();
	    if (facesContext != null) {
	      UIComponent root = facesContext.getViewRoot();
	      return findComponent(root, id);
	    }
	    return null;
	}

	public static UIComponent findComponent(UIComponent component, String id) {
		List components = new ArrayList();
		int i = 0;
		do {
			if (component != null) {
				components.addAll(component.getChildren());
				components.addAll(component.getFacets().values());
				if (id.equals(component.getId())) {
					return component;
				}
			}
			if (components.size() <= i) {
				return null;
			}
			component = (UIComponent) components.get(i++);
		}
		while (true);
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


	public static IWResourceBundle getResourceBundle() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		IWContext iwc = IWContext.getIWContext(facesContext);
		IWMainApplication mainApplication = iwc.getApplicationContext().getIWMainApplication();
		IWBundle bundle = mainApplication.getBundle(CaseConstants.BUNDLE_IDENTIFIER);
		return bundle.getResourceBundle(iwc);
	}
}