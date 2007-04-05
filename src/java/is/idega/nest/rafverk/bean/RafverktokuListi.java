package is.idega.nest.rafverk.bean;

import is.idega.nest.rafverk.business.ElectricalInstallationBusiness;
import is.idega.nest.rafverk.domain.ElectricalInstallation;
import is.idega.nest.rafverk.domain.Rafverktaka;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.ejb.FinderException;
import javax.faces.component.UIComponent;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import com.idega.business.IBOLookup;
import com.idega.idegaweb.IWApplicationContext;
import com.idega.presentation.IWContext;
import com.idega.user.data.User;

public class RafverktokuListi extends BaseBean  {
	
	String selectedStatus;
	
	Map rafverktokuListi = null;
	
	Rafverktaka currentRafverktaka = null;

	
	private ElectricalInstallationBusiness electricalInstallationBusiness = null;

	public RafverktokuListi() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		IWContext context = IWContext.getIWContext(facesContext);
		User user = context.getCurrentUser();
		initialize(user);
	}
	
	public RafverktokuListi(User electrician) {
		initialize(electrician);
	}
	
	private void initialize(User electrician) {
		rafverktokuListi = new TreeMap();
		Collection verktokur = null;
		try {
			verktokur = getElectricalInstallationBusiness().getElectricalInstallationByElectrician(electrician);
		}
		catch (RemoteException e) {
			throw new RuntimeException(e.getMessage());
		}
		catch (FinderException e) {
			verktokur = null;
		}
		if (verktokur != null) {
			Iterator iterator = verktokur.iterator();
			while (iterator.hasNext()) {
				ElectricalInstallation electricalInstallation = (ElectricalInstallation) iterator.next();
				Rafverktaka verktaka = new Rafverktaka(electricalInstallation);
				String id = verktaka.getId();
				rafverktokuListi.put(id, verktaka);	
			}
		}
	}
	
	public void addRafvertaka(Rafverktaka rafverktaka) {
		rafverktokuListi.put(rafverktaka.getId(), rafverktaka);
	}

	public List getRafverktokuListiSelects(){
		ArrayList selects = new ArrayList();
		for (Iterator iter = rafverktokuListi.values().iterator(); iter.hasNext();) {
			Rafverktaka verktaka = (Rafverktaka) iter.next();
			SelectItem item = new SelectItem();
			item.setLabel(verktaka.getVeitustadur().getVeitustadur());
			item.setValue(verktaka.getId());
			selects.add(item);
		}
		return selects;
	}

	public List getRafverktokur() {
		String status = getSelectedStatus();
		if(status==null||status.equals("")){
			return getAllRafverktokur();
		}
		else{
			return getRafverktokurWithStatus(status);
		}
	}

	public List getRafverktokurWithStatus(String status) {
		return getAllRafverktokurWithStatusListi(status);
	}
	
	public List getAllRafverktokurWithStatusListi(String status){
		List verktokur = getAllRafverktokur();
		List list = new ArrayList();
		for (Iterator iter = verktokur.iterator(); iter.hasNext();) {
			Rafverktaka verktaka = (Rafverktaka) iter.next();
			if(verktaka.getStada().equals(status)){
				list.add(verktaka);
			}
		}
		return list;
	}

	public List getAllRafverktokur() {
		return new ArrayList(rafverktokuListi.values());
		//return getInitialData().getAllRafverktokurListi();
	}

	public String getSelectedStatus() {
		return selectedStatus;
	}

	public void setSelectedStatus(String selectedStatus) {
		this.selectedStatus = selectedStatus;
	}

	public List getPossibleStatusesSelects() {
		ArrayList selects = new ArrayList();
		List verktokur = Rafverktaka.getPossibleStatuses();
		SelectItem item0 = new SelectItem();
		item0.setLabel("Allar");
		item0.setValue("");
		selects.add(item0);
		
		for (Iterator iter = verktokur.iterator(); iter.hasNext();) {
			String statusString = (String) iter.next();
			SelectItem item = new SelectItem();
			item.setLabel(statusString);
			item.setValue(statusString);
			selects.add(item);
		}
		return selects;
	}

	public void populateForms(ActionEvent actionEvent) throws AbortProcessingException {
	    Rafverktaka rafverktaka = null;

	    UIComponent tmpComponent = actionEvent.getComponent();

	    while (null != tmpComponent && !(tmpComponent instanceof UIData)) {
	      tmpComponent = tmpComponent.getParent();
	    }

	    if (tmpComponent instanceof UIData) {
	    	Object tmpRowData = ((UIData) tmpComponent).getRowData();
	    	if (tmpRowData instanceof Rafverktaka) {
	    		rafverktaka = (Rafverktaka) tmpRowData;
	    		TilkynningVertakaBean tilkynningVertakaBean = BaseBean.getTilkynningVertakaBean();
	    		TilkynningLokVerksBean tilkynningLokVerksBean = BaseBean.getTilkynningLokVerksBean();
	    		try {
	    			getElectricalInstallationBusiness().initializeManagedBeans(rafverktaka, tilkynningVertakaBean, tilkynningLokVerksBean);
	    		}
	    		catch (RemoteException e) {
	    			throw new RuntimeException(e.getMessage());
	    		}
	    	}
	    }
	}
	
	public ElectricalInstallationBusiness getElectricalInstallationBusiness() {
		if (electricalInstallationBusiness == null) {
			try {
				FacesContext context = FacesContext.getCurrentInstance();
				IWContext iwContext = IWContext.getIWContext(context);
				IWApplicationContext iwac = iwContext.getApplicationContext();
				electricalInstallationBusiness = (ElectricalInstallationBusiness) 
					IBOLookup.getServiceInstance(iwac, ElectricalInstallationBusiness.class);
			}
			catch (RemoteException rme) {
				throw new RuntimeException(rme.getMessage());
			}
		}
		return electricalInstallationBusiness;
	}
	

}
