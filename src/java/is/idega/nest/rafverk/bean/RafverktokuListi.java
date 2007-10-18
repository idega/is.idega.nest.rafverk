package is.idega.nest.rafverk.bean;

import is.idega.nest.rafverk.business.ElectricalInstallationBusiness;
import is.idega.nest.rafverk.business.ElectricalInstallationState;
import is.idega.nest.rafverk.domain.ElectricalInstallation;
import is.idega.nest.rafverk.domain.Rafverktaka;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;

import javax.ejb.FinderException;
import javax.faces.component.UIComponent;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.FacesEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.apache.myfaces.custom.datascroller.HtmlDataScroller;

import com.idega.presentation.IWContext;
import com.idega.user.data.User;
import com.idega.util.StringHandler;

public class RafverktokuListi extends BaseBean  {
	
	private static final String DATASCROLLER_ID = "myDatascroller"; 
	
	boolean hasChanged = true;
	
	// searching start...
	String selectedStatus;
	
	String oldSelectedStatus = "";
	
	String searchForExternalProjectID;
	
	String oldSearchForExternalProjectID = "";
	
	String searchForEnergyConsumer;
	
	String oldSearchForEnergyConsumer = "";
	
	String searchForRealEstate;
	
	String oldSearchForRealEstate = "";
	
	List filteredList;
	// ...searching end
	
	String numberOfRowsPerPage = "10";
	
	Map rafverktokuListi = null;
	
	Rafverktaka currentRafverktaka = null;
	
	User electrician = null;
	
	private ElectricalInstallationBusiness electricalInstallationBusiness = null;

	public RafverktokuListi() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		IWContext iwc = IWContext.getIWContext(facesContext);
		User user = iwc.getCurrentUser();
		initialize(user);
	}
	
	public RafverktokuListi(User electrician) {
		initialize(electrician);
	}
	
	private void initialize(User electrician) {
		this.electrician = electrician;
		initializeList();
	}
		
	private void initializeList() {
		rafverktokuListi = new TreeMap(Collections.reverseOrder());
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
				Rafverktaka verktaka = new Rafverktaka(electricalInstallation, getElectricalInstallationBusiness());
				String id = verktaka.getId();
				rafverktokuListi.put(id, verktaka);	
			}
		}
		filteredList = getAllRafverktokur();
	}
	
	public void addRafvertaka(Rafverktaka rafverktaka) {
		rafverktokuListi.put(rafverktaka.getId(), rafverktaka);
		filteredList = getAllRafverktokur();
		resetList();
	}

//	public List getRafverktokuListiSelects(){
//		ArrayList selects = new ArrayList();
//		for (Iterator iter = rafverktokuListi.values().iterator(); iter.hasNext();) {
//			Rafverktaka verktaka = (Rafverktaka) iter.next();
//			SelectItem item = new SelectItem();
//			item.setLabel(verktaka.getVeitustadur().getVeitustadur());
//			item.setValue(verktaka.getId());
//			selects.add(item);
//		}
//		return selects;
//	}
	
	public Rafverktaka getRafverktaka(String id) {
		return (Rafverktaka) rafverktokuListi.get(id);
	}

	public void resetList(ValueChangeEvent event) {
		resetList();
	}
	
	private void resetList() {
		HtmlDataScroller scroller = (HtmlDataScroller) 
			BaseBean.findComponentInRoot(DATASCROLLER_ID);
		if (scroller != null) {
			UIData uiData = scroller.getUIData();
			if (uiData != null) {
				uiData.setFirst(0);
			}
		}
	}
	
	public List getRafverktokur() {
		return filteredList;
	}
	
	public String doFilter() {
		if (
				oldSelectedStatus.equals(getSelectedStatus()) &&
				oldSearchForExternalProjectID.equals(getSearchForExternalProjectID()) &&
				oldSearchForEnergyConsumer.equals(getSearchForEnergyConsumer()) &&
				oldSearchForRealEstate.equals(getSearchForRealEstate())) {
			return null;
		}
		Pattern externalProjectIDPattern = getPattern(getSearchForExternalProjectID());
		Pattern energyConsumerPattern = getPattern(getSearchForEnergyConsumer());
		Pattern realEstatePattern = getPattern(getSearchForRealEstate());
		List verktokur = getAllRafverktokur();
		// shortcut for standard case
		if (StringHandler.isEmpty(getSelectedStatus()) 
				&& externalProjectIDPattern == null 
				&& energyConsumerPattern == null
				&& realEstatePattern == null) {
			filteredList = verktokur;
		}
		else {
			List list = new ArrayList();
			for (Iterator iter = verktokur.iterator(); iter.hasNext();) {
				Rafverktaka verktaka = (Rafverktaka) iter.next();
				if(checkElectricalInstallation(verktaka, externalProjectIDPattern, energyConsumerPattern, realEstatePattern)) {
					list.add(verktaka);
				}
			}
			filteredList = list;
		}
		resetList();
		oldSelectedStatus = getSelectedStatus() == null ? "" : getSelectedStatus();
		oldSearchForExternalProjectID = getSearchForExternalProjectID() == null ? "" : getSearchForExternalProjectID();
		oldSearchForEnergyConsumer = getSearchForEnergyConsumer() == null ? "" : getSearchForEnergyConsumer();
		oldSearchForRealEstate = getSearchForRealEstate() == null ? "" : getSearchForRealEstate();
		return null;
	}
	
	private boolean checkElectricalInstallation(Rafverktaka verktaka, Pattern externalProjectIDPattern, Pattern energyConsumerPattern, Pattern realEstatePattern) {
		if (StringHandler.isNotEmpty(getSelectedStatus()) && (! getSelectedStatus().startsWith(verktaka.getStada()))) {
			return false;
		}
		return 
			checkAttribute(externalProjectIDPattern, verktaka.getExternalProjectID()) &&
			checkAttribute(energyConsumerPattern, verktaka.getOrkukaupandi().getNafn()) &&
			checkAttribute(realEstatePattern, verktaka.getVeitustadurDisplay());
	}
	
	private boolean checkAttribute(Pattern search, String project) {
		if (search == null) {
			// nothing to do
			return true;
		}
		// search is not empty! check if project is null
		if (StringHandler.isEmpty(project)) {
			return false;
		}
		return search.matcher(project).matches();
	}
	
	private Pattern getPattern(String search) {
		if (StringHandler.isEmpty(search)) {
			return null;
		}
		String regex = StringHandler.convertWildcardExpressionToRegularExpression(search);
		return Pattern.compile(regex);
	}

	public List getAllRafverktokur() {
		try {
			if (getElectricalInstallationBusiness().changesForUser(electrician)) {
				initializeList();
			}
		}
		catch (RemoteException e) {
			throw new RuntimeException(e.getMessage());
		}
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
		List verktokur = ElectricalInstallationState.getPossibleStatuses();
		SelectItem item0 = new SelectItem();
		item0.setLabel("Allar");
		item0.setValue("");
		selects.add(item0);
		for (Iterator iter = verktokur.iterator(); iter.hasNext();) {
			String label = (String) iter.next();
			String value = (String) iter.next();
			SelectItem item = new SelectItem();
			item.setLabel(label);
			item.setValue(value);
			selects.add(item);
		}
		return selects;
	}

	public ElectricalInstallationBusiness getElectricalInstallationBusiness() {
		electricalInstallationBusiness = (ElectricalInstallationBusiness) 
			BaseBean.initializeServiceBean(electricalInstallationBusiness, ElectricalInstallationBusiness.class);
		return electricalInstallationBusiness;
	}
	
	
	/**
	 * Called by JSF page (opening existing electrical installation)
	 * 
	 * @param actionEvent
	 * @throws AbortProcessingException
	 */
	public void populateFormsForOverview(ActionEvent actionEvent) throws AbortProcessingException {
		initializeManagedBeansAndPDFs(actionEvent, true);	    
	}
	

	public void populateFormsForForms(ValueChangeEvent valueChangeEvent) throws AbortProcessingException {
	    String target = (String) valueChangeEvent.getNewValue();
	    if (InitialData.NONE_URI.equals(target)) {
	    	// do nothing
	    	return;
	    }
	    boolean createPDFs = InitialData.YFIRLIT_URI.equals(target);
	    initializeManagedBeansAndPDFs(valueChangeEvent, createPDFs);
	    try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(target);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void initializeManagedBeansAndPDFs(FacesEvent event, boolean createPDFs) {
	    UIComponent tmpComponent = event.getComponent();

	    while (null != tmpComponent && !(tmpComponent instanceof UIData)) {
	      tmpComponent = tmpComponent.getParent();
	    }

	    if (tmpComponent instanceof UIData) {
	    	Object tmpRowData = ((UIData) tmpComponent).getRowData();
	    	if (tmpRowData instanceof Rafverktaka) {
	    		Rafverktaka rafverktaka = (Rafverktaka) tmpRowData;
	    		TilkynningVertakaBean tilkynningVertakaBean = BaseBean.getTilkynningVertakaBean();
	    		TilkynningLokVerksBean tilkynningLokVerksBean = BaseBean.getTilkynningLokVerksBean();
	    		try {
	    			getElectricalInstallationBusiness().initializeManagedBeans(rafverktaka, tilkynningVertakaBean, tilkynningLokVerksBean);
	    			if (createPDFs) { 
	    				tilkynningVertakaBean.createApplicationPDF();
	    				tilkynningVertakaBean.createApplicationReportPDF();
	    			}
	    		}
	    		catch (RemoteException e) {
	    			throw new RuntimeException(e.getMessage());
	    		}
	    	}
	    }

	}
	
	public String getSearchForExternalProjectID() {
		return searchForExternalProjectID;
	}

	
	public void setSearchForExternalProjectID(String searchForExternalProjectID) {
		this.searchForExternalProjectID = searchForExternalProjectID;
	}

	
	public String getSearchForEnergyConsumer() {
		return searchForEnergyConsumer;
	}

	
	public void setSearchForEnergyConsumer(String searchForEnergyConsumer) {
		this.searchForEnergyConsumer = searchForEnergyConsumer;
	}

	
	public String getSearchForRealEstate() {
		return searchForRealEstate;
	}

	
	public void setSearchForRealEstate(String searchForRealEstate) {
		this.searchForRealEstate = searchForRealEstate;
	}
	
	public void setNumberOfRowsPerPage(String number) {
		numberOfRowsPerPage = number;
	}
	
	public String getNumberOfRowsPerPage() {
		return numberOfRowsPerPage;
	}
	
	public int getNumberOfRowsPerPageAsInt() {
		return Integer.valueOf(getNumberOfRowsPerPage()).intValue();
	}

}
