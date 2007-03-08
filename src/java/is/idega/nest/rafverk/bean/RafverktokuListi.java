package is.idega.nest.rafverk.bean;

import is.idega.nest.rafverk.domain.Rafverktaka;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.faces.model.SelectItem;

public class RafverktokuListi extends BaseBean implements ActionListener {
	
	String selectedStatus;
	
	Map rafverktokuListi = null;
	
	Rafverktaka currentRafverktaka = null;
	
	public RafverktokuListi() {
		initialize();
	}
	
	private void initialize() {
		rafverktokuListi = new HashMap();
		List verktokur = getInitialData().getAllRafverktokurListi();
		Iterator iterator = verktokur.iterator();
		while (iterator.hasNext()) {
			Rafverktaka verktaka = (Rafverktaka) iterator.next();
			String id = verktaka.getId();
			rafverktokuListi.put(id, verktaka);			
		}
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
		return getInitialData().getAllRafverktokurWithStatusListi(status);
	}

	public List getAllRafverktokur() {
		return getInitialData().getAllRafverktokurListi();
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

	public void processAction(ActionEvent actionEvent) throws AbortProcessingException {
		FacesContext context = FacesContext.getCurrentInstance();
		actionEvent.hashCode();
		Object component = actionEvent.getComponent();
		String id = (String) ((javax.faces.component.html.HtmlCommandLink) component).getValue();
		currentRafverktaka = (Rafverktaka) rafverktokuListi.get(id);
	}

}
