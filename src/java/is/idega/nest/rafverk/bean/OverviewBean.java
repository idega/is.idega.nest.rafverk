/*
 * $Id: OverviewBean.java,v 1.1 2007/11/16 16:30:51 thomas Exp $
 * Created on Nov 14, 2007
 *
 * Copyright (C) 2007 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package is.idega.nest.rafverk.bean;

import is.idega.nest.rafverk.business.ElectricalInstallationBusiness;
import is.idega.nest.rafverk.domain.ElectricalInstallation;
import is.idega.nest.rafverk.domain.Rafverktaka;

import java.rmi.RemoteException;

import javax.ejb.FinderException;

import com.idega.business.IBORuntimeException;


/**
 * 
 *  Last modified: $Date: 2007/11/16 16:30:51 $ by $Author: thomas $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.1 $
 */
public class OverviewBean {
	
	private String sel_case_nr = null;
	
	private ElectricalInstallationBusiness electricalInstallationBusiness;
	
	public void setSel_case_nr(String sel_case_nr) {
		// coming to the first page of wizard from overview (that is block UserCases) 
		// (look at faces-config.xml, sel_case_nr is defined as initial parameter, this bean has scope "request")
		if (sel_case_nr != null) {
			this.sel_case_nr = sel_case_nr;
		}
//		else {
//		// NOT USED HERE AT THE MOMENT 	
//		// coming from first page of wizard going to the second page (selectionCaseNumber is a hidden input)
//			Map requestMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
//			this.sel_case_nr = (String) requestMap.get("form1:selectionCaseNumber");
//		}
		// coming from second page of wizard going to the third page (selection is not set at all, not necessary)
		
		if (this.sel_case_nr != null) {
			initialize();
		}
	}
	
	
	// initialize the bean that is actually doing the work
	private void initialize() {
		try {
			ElectricalInstallation electricalInstallation = getElectricalInstallationBusiness().getElectricalInstallationByPrimaryKey(sel_case_nr);
			Rafverktaka rafverktaka = Rafverktaka.getInstanceFromElectricalInstallation(electricalInstallation, getElectricalInstallationBusiness());
			TilkynningVertakaBean tilkynningVertakaBean = BaseBean.getTilkynningVertakaBean();
			TilkynningLokVerksBean tilkynningLokVerksBean = BaseBean.getTilkynningLokVerksBean();
			getElectricalInstallationBusiness().initializeManagedBeans(rafverktaka, tilkynningVertakaBean, tilkynningLokVerksBean);
			tilkynningVertakaBean.createApplicationPDF();
			tilkynningVertakaBean.createApplicationReportPDF();
		}
		catch (RemoteException e) {
			// TODO Auto-generated catch block
			throw new IBORuntimeException(e);
		}
		catch (FinderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Dummy method to force JSF to initialize this bean
	 * This method is called at the beginning of the jsf page
	 * and is doing nothing 
	 * 
	 * @return
	 */
	public String getDummy() {
		return "";
	}
	
	

	public ElectricalInstallationBusiness getElectricalInstallationBusiness() {
		electricalInstallationBusiness = (ElectricalInstallationBusiness) 
			BaseBean.initializeServiceBean(electricalInstallationBusiness, ElectricalInstallationBusiness.class);
		return electricalInstallationBusiness;
	}
	
	
	
}
