/*
 * $Id: ElectricalInstallationValidator.java,v 1.1 2007/04/20 18:12:25 thomas Exp $
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
 *  Last modified: $Date: 2007/04/20 18:12:25 $ by $Author: thomas $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.1 $
 */
public class ElectricalInstallationValidator implements PropertyVisitor {
	

	public void visit(PropertyTree propertyTree) throws SAXException {
		Iterator iterator = propertyTree.getValue().iterator();
		while (iterator.hasNext()) {
			Property property = (Property) iterator.next();
			property.accept(this);
		}
		
	}

	public void visit(PropertyImpl propertyImpl) throws SAXException {
		String key = propertyImpl.getKey();
		System.out.println(key);
		
	}

	public void visit(PropertyWithUnit propertyWithUnit) throws SAXException {
		// TODO Auto-generated method stub
		
	}

	public void visit(PropertyWithValueDescription propertyWithValueDescription) throws SAXException {
		// TODO Auto-generated method stub
		
	}

	public void visit(ThreeValuePropertyWithUnit threeValuePropertyWithUnit) throws SAXException {
		// TODO Auto-generated method stub
		
	}
	
	
}
