/*
 * $Id: ElectricalInstallationValidator.java,v 1.2 2007/05/16 15:54:53 thomas Exp $
 * Created on Apr 20, 2007
 *
 * Copyright (C) 2007 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package is.idega.nest.rafverk.bean.validation;

import java.util.Iterator;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import com.idega.fop.data.Property;
import com.idega.fop.data.PropertyImpl;
import com.idega.fop.data.PropertyTree;
import com.idega.fop.data.PropertyWithUnit;
import com.idega.fop.data.PropertyWithValueDescription;
import com.idega.fop.data.ThreeValuePropertyWithUnit;
import com.idega.fop.visitor.PropertyVisitor;


/**
 * 
 *  Last modified: $Date: 2007/05/16 15:54:53 $ by $Author: thomas $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.2 $
 */
public class ElectricalInstallationValidator implements PropertyVisitor {
	

	public Object visit(PropertyTree propertyTree)  {
		Iterator iterator = propertyTree.getValue().iterator();
		while (iterator.hasNext()) {
			Property property = (Property) iterator.next();
			property.accept(this);
		}
		return null;
	}

	public Object visit(PropertyImpl propertyImpl) {
		String key = propertyImpl.getKey();
		System.out.println(key);
		return null;
		
	}

	public Object visit(PropertyWithUnit propertyWithUnit)  {
		// TODO Auto-generated method stub
		return null;
		
	}

	public Object visit(PropertyWithValueDescription propertyWithValueDescription)  {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(ThreeValuePropertyWithUnit threeValuePropertyWithUnit)  {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
