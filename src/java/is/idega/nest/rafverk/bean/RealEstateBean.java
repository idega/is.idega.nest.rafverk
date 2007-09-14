/*
 * $Id: RealEstateBean.java,v 1.4 2007/09/14 10:40:43 thomas Exp $
 * Created on Aug 13, 2007
 *
 * Copyright (C) 2007 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package is.idega.nest.rafverk.bean;

import is.idega.nest.rafverk.data.RealEstateIdentifier;
import is.idega.nest.rafverk.domain.Fasteign;
import is.idega.nest.rafverk.fmr.FMRLookupBean;
import is.postur.Gata;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.faces.model.SelectItem;

import com.idega.util.StringHandler;


/**
 * 
 *  Last modified: $Date: 2007/09/14 10:40:43 $ by $Author: thomas $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.4 $
 */
public class RealEstateBean {
	
    private String postnumer = null;
    
    private String gata = null;
    
    private String gotunumer = null;
    
    private String fastanumer = null;
    
    private String veitustadurDisplay = null;
    
    private List fasteignaListi = null;
    
    void initializeForm() {
	    postnumer = null;
	    gata = null;
	    gotunumer = null;
	    fastanumer = null;
	    fasteignaListi = null;
	    veitustadurDisplay = null;
    }
    
    
	private void fetchFasteignaListi() {
		
		String addr = getGata()+" "+getGotunumer();

		FMRLookupBean lookup = getFMRLookup();
		fasteignaListi = lookup.getFasteignir(addr,getPostnumer());
		setAvailablefasteign(true);
	}
	
	// called by NestService
	public void setRealEstateListByPostalCodeStreetStreetNumber(String postalCode, String street, String streetNumber) {
		if (StringHandler.isEmpty(postalCode)) {
			fasteignaListi = null;
		}
		StringBuffer buffer = new StringBuffer();
		if (StringHandler.isNotEmpty(street) && ! InitialData.NONE_STREET.equals(street)) {
			buffer.append(street).append(" ");
		}
		if (StringHandler.isNotEmpty(streetNumber)) {
			buffer.append(streetNumber);
		}
		FMRLookupBean lookup = getFMRLookup();
		fasteignaListi = lookup.getFasteignir(buffer.toString(),postalCode);
		setAvailablefasteign(true);
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
		if(StringHandler.isNotEmpty(fastanumer)) {
			// do not do anything if there is no change
			if (fastanumer.equals(this.fastanumer)) {
				return;
			}
			Fasteign fasteign = lookupFasteign(fastanumer);
			String description = fasteign.getDescription();
			setVeitustadurDisplay(description);
			changedRealEstate(fasteign);
		}
		this.fastanumer = fastanumer;
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
		realEstates.put(InitialData.NONE_REAL_ESTATE_SELECTION, "Vinsamlegast veldu rétta fasteign:");
		if (realEstateList == null) {
			return realEstates;
		}
		
		// sorting by street numbers else keep order
		SortedMap streetMap = new TreeMap();
		Iterator iterator = realEstateList.iterator();
		while (iterator.hasNext()) {
			Fasteign fasteign = (Fasteign) iterator.next();
			String streetnumber = fasteign.getGotuNumer();
			Integer streetNr;
			try {
				streetNr = new Integer(Integer.parseInt(streetnumber));
			}
			catch (NumberFormatException ex) {
				streetNr = new Integer(0);
			}
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

	public void setAvailablefasteign(boolean availablefasteign) {
		//this.availablefasteign = availablefasteign;
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
		ArrayList selects = new ArrayList(map.size());
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
	

	

 }
