package is.idega.nest.rafverk.bean;

import is.idega.nest.rafverk.domain.Rafverktaki;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.component.UIParameter;
import javax.faces.component.html.HtmlCommandLink;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

public class RafverktakaListi extends BaseBean{

	List rafverktakaListi;
	static String filterStringInitial="Gefdu nafn";
	String searchFilterString=filterStringInitial;
	boolean displaySearchResults;
	//Rafverktaki selectedRafverktaki;
	
	public List getRafverktakaListi(){
		if(rafverktakaListi==null){
			setRafverktakaListi(getAllRafverktakaListi());
		}
		return rafverktakaListi;
	}
	
	public void setRafverktakaListi(List newlist){
		this.rafverktakaListi=newlist;
	}

	public List getAllRafverktakaListi() {
		return getInitialData().getAllRafverktakaListi();
	}
	

	public String filter() {
		String searchFilterString = getSearchFilterString();
		
		

		if(searchFilterString == null || searchFilterString.equals("")){
			resetList();
		}
		else if(!searchFilterString.equals(filterStringInitial)){
			displaySearchResults=true;
			String searchFilterStringLowerCase = searchFilterString.toLowerCase();
			List allVerktakar = getAllRafverktakaListi();
			List newList = new ArrayList();
			for (Iterator iter = allVerktakar.iterator(); iter.hasNext();) {
				Rafverktaki verktaki = (Rafverktaki) iter.next();
				if(verktaki.getNafn().toLowerCase().indexOf(searchFilterStringLowerCase) > -1){
					newList.add(verktaki);
				}
			}
			setRafverktakaListi(newList);
			
		}
		
		return null;
		//TODO Auto-generated method stub
	}

	private void resetList() {
		searchFilterString=filterStringInitial;
		setRafverktakaListi(getAllRafverktakaListi());
		setSelectedRafverktaki(null);
	}

	public boolean isDisplaySearchResults() {
		return displaySearchResults;
	}

	public void setDisplaySearchResults(boolean displaySearchResults) {
		this.displaySearchResults = displaySearchResults;
	}

	public String getSearchFilterString() {
		return searchFilterString;
	}

	public void setSearchFilterString(String searchFilterString) {
		this.searchFilterString = searchFilterString;
	}

	public YfirlitRafverktaka getYfirlitRafverktaka(){
		return YfirlitRafverktaka.getInstance();
	}
	
	public Rafverktaki getSelectedRafverktaki() {
		return getYfirlitRafverktaka().getSelectedRafverktaki();
	}

	public void setSelectedRafverktaki(Rafverktaki verktaki) {
		//System.out.println("Selected rafverktaki with id="+verktaki.getId());
		getYfirlitRafverktaka().setSelectedRafverktaki(verktaki);
	}
	
	public boolean isRafverktakiSelected(){
		return getSelectedRafverktaki()!=null;
	}

	public String selectRafverktaki() {
		//return null;
		return "selectRafverktaki";
	}

	public void selectRafverktaki(ActionEvent event) {
		//TODO Auto-generated method stub
		
		HtmlCommandLink link = (HtmlCommandLink) event.getSource();
		List children = link.getChildren();
		for (Iterator iter = children.iterator(); iter.hasNext();) {
			UIComponent element = (UIComponent) iter.next();
			if(element instanceof UIParameter){
				UIParameter param = (UIParameter)element;
				if(param.getName().equals("rafverktakiId")){
					String id = (String) param.getValue();
					Rafverktaki verktaki = getInitialData().getRafverktakiById(id);
					if(verktaki!=null){
						setSelectedRafverktaki(verktaki);

						FacesContext context = FacesContext.getCurrentInstance();
						try {
							context.getExternalContext().redirect("/pages/yfirlitRafverktaka.jsf");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}
		
		
		
		//return "selectRafverktaki";
	}
	
}
