/*
 * $Id: NestServiceBean.java,v 1.1 2007/06/08 17:05:12 thomas Exp $
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

import java.util.HashMap;
import java.util.Map;

import com.idega.business.IBOServiceBean;
import com.idega.util.StringHandler;


/**
 * 
 *  Last modified: $Date: 2007/06/08 17:05:12 $ by $Author: thomas $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.1 $
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
}
