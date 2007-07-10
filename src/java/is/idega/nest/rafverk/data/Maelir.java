/*
 * $Id: Maelir.java,v 1.6 2007/07/10 11:58:36 thomas Exp $
 * Created on Feb 12, 2007
 *
 * Copyright (C) 2007 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package is.idega.nest.rafverk.data;

import is.idega.nest.rafverk.bean.constants.JSFPageID;

import java.io.IOException;
import java.util.List;

import javax.faces.context.FacesContext;


/**
 * 
 *  Last modified: $Date: 2007/07/10 11:58:36 $ by $Author: thomas $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.6 $
 */
public class Maelir {
	
	
	
	public static Maelir addInvalidInstance(String context, int priorityWithinList, List myList) {
		Maelir maelir = new Maelir(context, priorityWithinList);
		myList.add(maelir);
		maelir.setMyList(myList);
		maelir.setValid(false);
		maelir.setContext(context);
		return maelir;
	}
	
	List myList = null;
	
	private boolean valid = true;
	
	private String context = null;
	
	private int priorityWithinContext = 0;
	
	private String fasa = null;
	
	private String numer = null;
	
	private String ampere = null;
	
	private String taxti = null;
	
	private String hjalpataeki = null;
	
	private String stadur = null;
	
	public Maelir(String context, int priorityWithinContext) {
		this.context = context;
		this.priorityWithinContext = priorityWithinContext;
	}
	
	/**
	 * Set this instance to valid and 
	 * add an invalid instance at the end
	 * of myList, goes to the desired anchor
	 *
	 */
	public void add() {
		valid = true;
		int newPriority = priorityWithinContext + 1;
		Maelir.addInvalidInstance(getContext(), newPriority, myList);
		goBackToAnchor();
	}
	
	public void delete() {
		myList.remove(this);
		goBackToAnchor();
	}

	
	public String getAmpere() {
		return ampere;
	}

	
	public void setAmpere(String ampere) {
		this.ampere = ampere;
	}

	
	public String getNumer() {
		return numer;
	}

	
	public void setNumer(String numer) {
		this.numer = numer;
	}

	
	public String getTaxti() {
		return taxti;
	}

	
	public void setTaxti(String taxti) {
		this.taxti = taxti;
	}


	
	public String getFasa() {
		return fasa;
	}


	
	public void setFasa(String fasa) {
		this.fasa = fasa;
	}

	
	public boolean isValid() {
		return valid;
	}

	
	public void setValid(boolean valid) {
		this.valid = valid;
	}

	
	public String getHjalpataeki() {
		return hjalpataeki;
	}

	
	public void setHjalpataeki(String hjalparTaeki) {
		this.hjalpataeki = hjalparTaeki;
	}

	
	public String getStadur() {
		return stadur;
	}

	
	public void setStadur(String stadur) {
		this.stadur = stadur;
	}

	
	public String getContext() {
		return context;
	}

	
	public void setContext(String context) {
		this.context = context;
	}

	
	public int getPriorityWithinContext() {
		return priorityWithinContext;
	}

	
	public void setPriorityWithinContext(int priorityWithinContext) {
		this.priorityWithinContext = priorityWithinContext;
	}

	
	public List getMyList() {
		return myList;
	}

	
	public void setMyList(List myList) {
		this.myList = myList;
	}
	
	private void goBackToAnchor() {
        FacesContext context = FacesContext.getCurrentInstance();
        String anchorNameValue = (String) context.getExternalContext().getRequestParameterMap().get(JSFPageID.PARAMETER_ANCHOR_NAME);
        StringBuffer buffer = new StringBuffer(JSFPageID.PAGE_URI_APPLICATION_FORM_STEP_3);
        buffer.append("#").append(anchorNameValue);
		try {
			// (e.g., "/pages/rafverktaki/rafverk/tilkynningvertakaskref3/#takaAdd")
			context.getExternalContext().redirect(buffer.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
