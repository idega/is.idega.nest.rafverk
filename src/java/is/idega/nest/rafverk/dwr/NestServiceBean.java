/*
 * $Id: NestServiceBean.java,v 1.2 2007/06/15 16:21:33 thomas Exp $
 * Created on Jun 7, 2007
 *
 * Copyright (C) 2007 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package is.idega.nest.rafverk.dwr;

import is.idega.nest.rafverk.bean.BaseBean;
import is.idega.nest.rafverk.bean.TilkynningVertakaBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.idega.business.IBOServiceBean;


/**
 * 
 *  Last modified: $Date: 2007/06/15 16:21:33 $ by $Author: thomas $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.2 $
 */
public class NestServiceBean extends IBOServiceBean implements NestService{
	
	public String getName() {
		return "Hello Word";
	}
	
	public Map getStreetsByPostalCode(String postalCode) {
		TilkynningVertakaBean tilkynningVertakaBean = BaseBean.getTilkynningVertakaBean();
		// Postalcode must be set so that methof getStreets returns the right options list
		// If the value is not set the selected item of the street drop down can not be validated.
		// JSF  accepts only a value that matches a value in the option list.
		// When submitting JSF checks if the submitted value is contained in the option list. 
		tilkynningVertakaBean.setPostnumer(postalCode);
		return tilkynningVertakaBean.getStreets();
	}
	
	public Map getRealEstatesByPostalCodeStreetStreetNumber(String postalCode, String street, String streetNumber) {
		TilkynningVertakaBean tilkynningVertakaBean = BaseBean.getTilkynningVertakaBean();
		tilkynningVertakaBean.setRealEstateListByPostalCodeStreetStreetNumber(postalCode, street, streetNumber);
		return tilkynningVertakaBean.getRealEstates();
	}
	
	public List getEnergyConsumerFields(String realEstateNumber) {
		TilkynningVertakaBean tilkynningVertakaBean = BaseBean.getTilkynningVertakaBean();
		tilkynningVertakaBean.setRealEstateNumber(realEstateNumber);
		String realEstateDisplay = tilkynningVertakaBean.getVeitustadurDisplay();
		String energyConsumerName = tilkynningVertakaBean.getNafnOrkukaupanda();
		String energyConsumerPersonalID = tilkynningVertakaBean.getKennitalaOrkukaupanda();

		List list = new ArrayList(3);
		list.add(realEstateDisplay);
		list.add(energyConsumerName);
		list.add(energyConsumerPersonalID);
		return list;
	}
}
