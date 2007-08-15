/*
 * $Id: NestServiceBean.java,v 1.6 2007/08/15 17:16:05 thomas Exp $
 * Created on Jun 7, 2007
 *
 * Copyright (C) 2007 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package is.idega.nest.rafverk.dwr;

import is.idega.nest.rafverk.bean.BaseBean;
import is.idega.nest.rafverk.bean.ChangeElectricianBean;
import is.idega.nest.rafverk.bean.RealEstateBean;
import is.idega.nest.rafverk.bean.TilkynningVertakaBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.idega.business.IBOServiceBean;
import com.idega.util.StringHandler;


/**
 * 
 *  Last modified: $Date: 2007/08/15 17:16:05 $ by $Author: thomas $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.6 $
 */
public class NestServiceBean extends IBOServiceBean implements NestService{
	
	public Map getStreetsByPostalCodeForChangeElectricianBean(String postalCode) {
		ChangeElectricianBean changeElectricianBean = BaseBean.getChangeElectricianBean();
		return getStreetsByPostalCode(postalCode, changeElectricianBean);
	}
	
	public Map getStreetsByPostalCode(String postalCode) {
		TilkynningVertakaBean tilkynningVertakaBean = BaseBean.getTilkynningVertakaBean();
		return getStreetsByPostalCode(postalCode, tilkynningVertakaBean);
	}
		
	private Map getStreetsByPostalCode(String postalCode, RealEstateBean realEstateBean) {
		// Postalcode must be set so that methof getStreets returns the right options list
		// If the value is not set the selected item of the street drop down can not be validated.
		// JSF  accepts only a value that matches a value in the option list.
		// When submitting JSF checks if the submitted value is contained in the option list. 
		realEstateBean.setPostnumer(postalCode);
		return realEstateBean.getStreets();
	}

	public Map getRealEstatesByPostalCodeStreetStreetNumberForChangeElectricianBean(String postalCode, String street, String streetNumber) {
		ChangeElectricianBean changeElectricianBean = BaseBean.getChangeElectricianBean();
		return getRealEstatesByPostalCodeStreetStreetNumber(postalCode, street, streetNumber, changeElectricianBean);
	}
	
	public Map getRealEstatesByPostalCodeStreetStreetNumber(String postalCode, String street, String streetNumber) {
		TilkynningVertakaBean tilkynningVertakaBean = BaseBean.getTilkynningVertakaBean();
		return getRealEstatesByPostalCodeStreetStreetNumber(postalCode, street, streetNumber, tilkynningVertakaBean);
	}
		
	private Map getRealEstatesByPostalCodeStreetStreetNumber(String postalCode, String street, String streetNumber, RealEstateBean realEstateBean) {
		realEstateBean.setRealEstateListByPostalCodeStreetStreetNumber(postalCode, street, streetNumber);
		return realEstateBean.getRealEstates();
	}
	
	public List getElectricInstallationList(String realEstateNumber) {
		ChangeElectricianBean changeElectricianBean = BaseBean.getChangeElectricianBean();
		changeElectricianBean.setRealEstateNumber(realEstateNumber);
		
		String realEstateDisplay = changeElectricianBean.getVeitustadurDisplay();
		realEstateDisplay = StringHandler.replaceIfEmpty(realEstateDisplay, StringHandler.EMPTY_STRING);
		
		List list = new ArrayList(2);
		list.add(realEstateDisplay);
		list.add(changeElectricianBean.getElectricalInstallationList());
		return list;
	}
	
	
	public List getEnergyConsumerFields(String realEstateNumber) {
		TilkynningVertakaBean tilkynningVertakaBean = BaseBean.getTilkynningVertakaBean();
		tilkynningVertakaBean.setRealEstateNumber(realEstateNumber);
		String realEstateDisplay = tilkynningVertakaBean.getVeitustadurDisplay();
		String energyConsumerName = tilkynningVertakaBean.getNafnOrkukaupanda();
		String energyConsumerPersonalID = tilkynningVertakaBean.getKennitalaOrkukaupanda();

		// converting null values to empty strings has to be done because IE does not like null values 
		realEstateDisplay = StringHandler.replaceIfEmpty(realEstateDisplay, StringHandler.EMPTY_STRING);
		energyConsumerName = StringHandler.replaceIfEmpty(energyConsumerName, StringHandler.EMPTY_STRING);
		energyConsumerPersonalID = StringHandler.replaceIfEmpty(energyConsumerPersonalID, StringHandler.EMPTY_STRING);
		
		List list = new ArrayList(3);
		list.add(realEstateDisplay);
		list.add(energyConsumerName);
		list.add(energyConsumerPersonalID);
		return list;
	}
	

//	public void updateMeter(String pageURI, String componentID) {
//		ViewManager viewManager = ViewManager.getInstance(getIWMainApplication());
//		ViewNode viewNode = viewManager.calculateViewNodeForUrl(pageURI);
//		String resourceURI = viewNode.getResourceURI();
//		
//
//		FacesContext context = FacesContext.getCurrentInstance();
//		Application application = context.getApplication();
//		ViewHandler viewHandler = application.getViewHandler();
//
//		ViewHandler parent = ((IWViewHandlerImpl) viewHandler).getParentViewHandler();
//		IWJspViewHandler jspViewHandler = new IWJspViewHandler(parent);
//		
//		UIViewRoot viewRoot = jspViewHandler.restoreView(context, resourceURI);
//		String renderkit = parent.calculateRenderKitId(context);
//		UIViewRoot root = context.getViewRoot();
//		UIComponent component = viewRoot.findComponent(componentID);
//		component.getChildren();
//		BuilderLogic builderLogic = BuilderLogic.getInstance();
//		String pageKey = builderLogic.getPageKeyByURI(pageURI);
//		CachedBuilderPage page = builderLogic.getCachedBuilderPage(pageKey);
//	}


}
