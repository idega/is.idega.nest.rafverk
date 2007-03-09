package is.idega.nest.rafverk.bean;

import is.idega.nest.rafverk.domain.Rafverktaka;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

public class RafverktokuListi extends BaseBean  {
	
	String selectedStatus;
	
	SortedMap rafverktokuListi = null;
	
	Rafverktaka currentRafverktaka = null;
	
	String currentId = null;
	
	int k = 1;
	
	public RafverktokuListi() {
		initialize();
	}
	
	private void initialize() {
		rafverktokuListi = new TreeMap();
		List verktokur = getInitialData().getAllRafverktokurListi();
		Iterator iterator = verktokur.iterator();
		while (iterator.hasNext()) {
			k++;
			Rafverktaka verktaka = (Rafverktaka) iterator.next();
			String id = verktaka.getId();
			rafverktokuListi.put(id, verktaka);	
			currentId = id;
		}
	}
	
	public String getNewId() {
		return "id"+k++;
	}
	
	public void addRafvertaka(Rafverktaka rafvertaka) {
		rafverktokuListi.put(rafvertaka.getId(), rafvertaka);
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
		FacesContext context = FacesContext.getCurrentInstance();
		actionEvent.hashCode();
		Object component = actionEvent.getComponent();
		String id = (String) ((javax.faces.component.html.HtmlCommandLink) component).getValue();
		currentRafverktaka = (Rafverktaka) rafverktokuListi.get(id);
		TilkynningLokVerksBean tilkynningLokVerksBean = BaseBean.getTilkynningLokVerksBean();
		tilkynningLokVerksBean.initialize();
		tilkynningLokVerksBean.populate(currentRafverktaka);
	}

}
