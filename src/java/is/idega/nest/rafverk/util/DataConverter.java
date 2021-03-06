/*
 * $Id: DataConverter.java,v 1.3 2007/11/29 13:01:01 thomas Exp $
 * Created on Mar 15, 2007
 *
 * Copyright (C) 2007 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package is.idega.nest.rafverk.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import com.idega.util.StringHandler;


/**
 * 
 *  Last modified: $Date: 2007/11/29 13:01:01 $ by $Author: thomas $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.3 $
 */
public class DataConverter {
	
	private static final String TOKEN = ":";
	
	/**
	 * encode a list of identifiers "hello" "world" into "hello:world"
	 * 
	 * null or empty list is converted to null
	 * 
	 * @param identifiers as list
	 * @return encodedIdentifiers as list
	 */
	public static String encodeIdentifers(List identifiers) {
		if (identifiers == null || identifiers.isEmpty()) {
			return null;
		}
		StringBuffer buffer = new StringBuffer();
		Iterator iterator = identifiers.iterator();
		// list is not empty
		boolean go = true;
		while (go) {
			String aString = (String) iterator.next();
			buffer.append(aString);
			go = iterator.hasNext();
			if (go) {
				buffer.append(TOKEN);
			}
		}
		return buffer.toString();
	}
	
	
	/**
	 * decodes a string of encoded identifiers back to a list 
	 * 
	 * @param encodedIdentfiers 
	 * @return list of identifiers
	 */
	public static List decodeIdentifiers(String encodedIdentfiers) {
		if (StringHandler.isEmpty(encodedIdentfiers)) {
			return new ArrayList(0);
		}
		List result = new ArrayList();
		StringTokenizer tokenizer = new StringTokenizer(encodedIdentfiers, ":");
		while (tokenizer.hasMoreTokens()) {
			result.add(tokenizer.nextToken());
		}
		return result;
	}
	
	
	/**
	 * Looks up  values of keys of the static arrays in InitialData ("value1, key1, value2, key2 .....") 
	 * if value is not found returns key.
	 */
	public static String lookup(String[] myArray, String myKey) {
		for (int i = 1; i < myArray.length; i = i + 2) {
			String string = myArray[i];
			if (string.equals(myKey)) {
				return myArray[--i];
			}
		}
		return null;
	}
	
	
}
