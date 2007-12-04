/*
 * $Id: RealEstateBean.java,v 1.10 2007/12/04 15:30:22 thomas Exp $
 * Created on Aug 13, 2007
 *
 * Copyright (C) 2007 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package is.idega.nest.rafverk.bean;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import is.fmr.landskra.Fasteign;
import is.fmr.landskra.FasteignaskraClient;
import is.idega.nest.rafverk.data.RealEstateIdentifier;
import is.idega.nest.rafverk.fmr.FMRLookupBean;
import is.postur.Gata;

import javax.ejb.FinderException;
import javax.faces.model.SelectItem;

import com.idega.core.location.data.RealEstate;
import com.idega.core.location.data.RealEstateHome;
import com.idega.data.IDOHome;
import com.idega.data.IDOLookup;
import com.idega.util.StringHandler;


/**
 * 
 *  Last modified: $Date: 2007/12/04 15:30:22 $ by $Author: thomas $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.10 $
 */
public class RealEstateBean {
	
    private String postnumer;
    
    private String gata;
    
    private String gotunumer;
    
    private String freeText;
    
    private String streetNumber;
    
    private String fastanumer;
    
    private String veitustadurDisplay;
    
    private List fasteignaListi;
    
    private RealEstateHome realEstateHome;
    

    
    void initializeForm() {
	    postnumer = null;
	    gata = null;
	    gotunumer = null;
	    streetNumber = null;
	    freeText = null;
	    fastanumer = null;
	    fasteignaListi = null;
	    veitustadurDisplay = null;
    }
    
    
	private void fetchFasteignaListi() {

		FMRLookupBean lookup = getFMRLookup();
		fasteignaListi = lookup.getFasteignir(getGata(), getGotunumer(), getPostnumer());
	}
	
	// called by NestService
	public void setRealEstateListByPostalCodeStreetStreetNumber(String postalCode, String street, String streetNumber, String freeText) {
		if (StringHandler.isEmpty(postalCode)) {
			fasteignaListi = null;
			return;
		}
		String streetNumberTemp = null;
		if (InitialData.NONE_STREET.equals(street)) {
			// no street selected, free text search
			street = null;
			streetNumberTemp = freeText;
		}
		else if (InitialData.NONE_STREET_NUMBER.equals(streetNumber)) {
			fasteignaListi = null;
			return;
		}
		else if (InitialData.ALL_STREET_NUMBERS.equals(streetNumber) || StringHandler.isEmpty(streetNumber)) {
			// change * to null
			// street selected, no street number specified
			streetNumberTemp = null;
		}
		else {
			streetNumberTemp = streetNumber;
		}
		FMRLookupBean lookup = getFMRLookup();
		fasteignaListi = lookup.getFasteignir(street, streetNumberTemp, postalCode);
	}

	// called by NestService 
	public void setRealEstateNumber(String realEstateNumber) {
		setFastanumer(realEstateNumber);
	}
	
	
	public void setFastanumer(String fastanumer) {
		if (InitialData.NONE_REAL_ESTATE_SELECTION.equals(fastanumer)) {
			// do nothing, leave everything unchanged
			return;
		}
		if (StringHandler.isEmpty(fastanumer)) {
			// set and good bye
			this.fastanumer = fastanumer;
			return;
		}
		// do not do anything if there is no change
		if (fastanumer.equals(this.fastanumer)) {
			return;
		}
		// quite important: set fastanumer before calling changedRealEstate
		this.fastanumer = fastanumer;
		Fasteign fasteign = lookupFasteign(fastanumer);
		if (fasteign != null) {
			String description = fasteign.getDescription();
			setVeitustadurDisplay(description);
		}
		changedRealEstate(fasteign);
	}
	
	// might be overwritten by subclasses
	void changedRealEstate(Fasteign fasteign) {
		// do nothing
	}
	
	public String flettaUppIFasteignaskra() {
		
		fetchFasteignaListi();
		
		return "result";
	}

	public Map getRealEstates() {
		List realEstateList = getFasteignaListi();
		
		int size =  (realEstateList == null) ? 1 : realEstateList.size();
		
		Map realEstates = new LinkedHashMap(size);

		if (realEstateList == null || realEstateList.isEmpty()) {
			realEstates.put(InitialData.NONE_REAL_ESTATE_SELECTION, "Fletta í Landskrá fasteigna fyrst");
			return realEstates;
		}
		realEstates.put(InitialData.NONE_REAL_ESTATE_SELECTION, "Vinsamlegast veldu rétta fasteign:");

		// sorting by street numbers else keep order
		SortedMap streetMap = new TreeMap();
		Iterator iterator = realEstateList.iterator();
		while (iterator.hasNext()) {
			Fasteign fasteign = (Fasteign) iterator.next();
			String streetnumber = fasteign.getGotuNumer();
			Integer streetNr = FasteignaskraClient.parseNumberForSorting(streetnumber);
			List list;
			if (! streetMap.containsKey(streetNr)) {
				list = new ArrayList();
				streetMap.put(streetNr, list);
				
			}
			else {
				list = (List) streetMap.get(streetNr);
			}
			list.add(fasteign);
		}
		

		
		Iterator firstIterator = streetMap.values().iterator();
		while (firstIterator.hasNext()) {
			List list = (List) firstIterator.next();
			Iterator secondIterator = list.iterator();
			while (secondIterator.hasNext()) {
				Fasteign fasteign = (Fasteign) secondIterator.next();
				String value = RealEstateIdentifier.getIdentifierAsString(fasteign);
				String label = fasteign.getDescription();
				realEstates.put(value, label);
			}
		}
		return realEstates;
	}
	

	
	
	public List getFasteignaListiSelects() {
		Map realEstates = getRealEstates();
		return getListiSelects(realEstates);
	}

	public void setFasteignaListi(List fasteignaListi) {
		this.fasteignaListi = fasteignaListi;
	}

	public boolean isAvailablefasteign() {
		return fasteignaListi!=null;
		//return availablefasteign;
	}
	
	public FMRLookupBean getFMRLookup(){
		return new FMRLookupBean();
	}
	
	public List getGotuListiSelects(){
		Map streets = getStreets();
		return getListiSelects(streets);
	}
	
	public Map getStreets() {
		// keep the order
		Map streets = new LinkedHashMap();
		if(postnumer==null||postnumer.equals("")){
			streets.put("", "Veldu póstnúmer fyrst");
		}
		else{
			streets.put(InitialData.NONE_STREET,"- Engin gata til staðar");
			List streetList = BaseBean.getInitialData().getGotuListiByPostnumer(postnumer);
			Iterator iterator = streetList.iterator(); 
			while (iterator.hasNext()) {
				Gata tempGata = (Gata) iterator.next();
				String name = tempGata.getNafn();
				// value, label
				streets.put(name, name);
			}
		}
		return streets;
	}
	
	public Map getStreetsByDWR() {
		// keep the order
		Map streets = new LinkedHashMap();
		if(postnumer==null||postnumer.equals("")){
			streets.put("", "Veldu póstnúmer fyrst");
		}
		else{
			streets.put(InitialData.NONE_STREET,"- Engin gata til staðar");
			List streetList = BaseBean.getInitialDataByDWR().getGotuListiByPostnumer(postnumer);
			Iterator iterator = streetList.iterator(); 
			while (iterator.hasNext()) {
				Gata tempGata = (Gata) iterator.next();
				String name = tempGata.getNafn();
				// value, label
				streets.put(name, name);
			}
		}
		return streets;
	}
	
	
	// lookup fasteign
	
	public Fasteign lookupFasteign(RealEstateIdentifier realEstateIdentifer) {
		List fasteignaListiTemp = getFasteignaListi();
		Iterator iterator = fasteignaListiTemp.iterator();
		while(iterator.hasNext()) {
			Fasteign fasteign = (Fasteign) iterator.next();
			if (realEstateIdentifer.isFasteign(fasteign)) {
				return fasteign;
			}
		}
		return null;
	}
	
	public Fasteign lookupFasteign(String realEstateIdentiferAsString) {
		List fasteignaListiTemp = getFasteignaListi();
		Iterator iterator = fasteignaListiTemp.iterator();
		while(iterator.hasNext()) {
			Fasteign fasteign = (Fasteign) iterator.next();
			if (RealEstateIdentifier.hasIdentifier(fasteign, realEstateIdentiferAsString)) {
				return fasteign;
			}
		}
		return null;
	}
	
	public RealEstateIdentifier getRealEstateIdentifier() {
		return RealEstateIdentifier.getInstance(getFastanumer());
	}
	
	public List getFasteignaListi(){
		return fasteignaListi;
	}

	public String getFastanumer() {
		return fastanumer;
	}
	
	public RealEstate getRealEstate() {
		String fastanumer = getFastanumer();
		if (StringHandler.isNotEmpty(fastanumer)) {
			RealEstateIdentifier realEstateIdentifier = RealEstateIdentifier.getInstance(fastanumer);
			if (realEstateIdentifier.isDummy()) {
				return null;
			}
			try {
				return getRealEstateHome().findRealEstateByRealEstateIdentifier(
						realEstateIdentifier.getLandNumber(), 
						realEstateIdentifier.getRealEstateNumber(), 
						realEstateIdentifier.getRealEstateUnit(), 
						realEstateIdentifier.getRealEstateCode());
			}
			catch (FinderException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		return null;
 	}

	// called when populating the form with a previous stored form
	public void initFastanumer(String fastanumer) {
		this.fastanumer = fastanumer;
	}
	
	public String getGata() {
		return gata;
	}
	
	public void setGata(String gata) {
		this.gata = gata;
	}
	
	public String getGotunumer() {
		return gotunumer;
	}
	
	public void setGotunumer(String gotunumer) {
		this.gotunumer = gotunumer;
	}
	
	public String getPostnumer() {
		return postnumer;
	}

	public void setPostnumer(String postnumer) {
		this.postnumer = postnumer;
	}
	
	public String getVeitustadurDisplay() {
		return veitustadurDisplay;
	}
	
	public void setVeitustadurDisplay(String veitustadur) {
		this.veitustadurDisplay = veitustadur;
	}
	
	List getListiSelects(Map map) {
		List selects = new ArrayList(map.size());
		Iterator iterator = map.keySet().iterator();
		while (iterator.hasNext()) {
			String value = (String) iterator.next();
			String label = (String) map.get(value);
			SelectItem item = new SelectItem();
			item.setLabel(label);
			item.setValue(value);
			selects.add(item);
		}
		return selects;
	}
	
	
	public String getShowStreetNumberSelects() {
		return ! (InitialData.NONE_STREET.equals(getGata())) ? "display:block" : "display:none";
	}
	
	public String getShowFreetextGotunumer() {
		return (InitialData.NONE_STREET.equals(getGata())) ? "display:block" : "display:none";
	}

	

	
	public String getStreetNumber() {
		return streetNumber;
	}


	
	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}


	
	public String getFreeText() {
		return freeText;
	}


	
	public void setFreeText(String freeText) {
		this.freeText = freeText;
	}
	
	private RealEstateHome getRealEstateHome() {
		if (realEstateHome == null) {
			realEstateHome = (RealEstateHome) retrieveHome(RealEstate.class);
		}
		return realEstateHome;
	}
	
	private IDOHome retrieveHome(Class entityClass ) {
		IDOHome home = null;
		try {
			home = IDOLookup.getHome(entityClass);
		}
		catch (RemoteException rme) {
			throw new RuntimeException(rme.getMessage());
		}
		return home;
	}
	

 }
