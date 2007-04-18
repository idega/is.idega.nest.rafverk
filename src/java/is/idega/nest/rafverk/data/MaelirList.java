/*
 * $Id: MaelirList.java,v 1.1 2007/04/18 17:55:58 thomas Exp $
 * Created on Apr 12, 2007
 *
 * Copyright (C) 2007 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package is.idega.nest.rafverk.data;

import is.idega.nest.rafverk.bean.InitialData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 
 *  Last modified: $Date: 2007/04/18 17:55:58 $ by $Author: thomas $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.1 $
 */
public class MaelirList {
	
	public static Maelir getEmptyMaelirContextMeterInReport() {
		return new Maelir(InitialData.METER_IN_REPORT,0);
	}
	
	public static Maelir getEmptyMaelirContextStadurMaelir() {
		return new Maelir(InitialData.STADUR,0);
	}
	
	public static Map getEmptyMaelirMap() {
		Map maelirMap = new HashMap();
		for (int i = 0; i < InitialData.MAELIR_CONTEXT.length; i++) {
			List list = new ArrayList();
			// initialize all lists with an invalid instance of maelir
			Maelir.addInvalidInstance(InitialData.MAELIR_CONTEXT[i],0,list);
			maelirMap.put(InitialData.MAELIR_CONTEXT[i], list);
		}
		return maelirMap;
	}
	
	private Map maelirListMap;
	
	private Maelir stadurMaelir;
	
	private Maelir maelir;
	
	public MaelirList() {
		initialize();
	}
	
	private void initialize() {
		maelirListMap = MaelirList.getEmptyMaelirMap();
		stadurMaelir = MaelirList.getEmptyMaelirContextStadurMaelir();
		maelir = MaelirList.getEmptyMaelirContextMeterInReport();
	}

	
	/**
	 * @return the maelirListMap
	 */
	public Map getMaelirListMap() {
		return maelirListMap;
	}

	
	/**
	 * @param maelirListMap the maelirListMap to set
	 */
	public void setMaelirListMap(Map maelirListMap) {
		this.maelirListMap = maelirListMap;
	}

	
	/**
	 * @return the maelir
	 */
	public Maelir getMaelir() {
		return maelir;
	}

	
	/**
	 * @param maelir the maelir to set
	 */
	public void setMaelir(Maelir maelir) {
		this.maelir = maelir;
	}

	
	/**
	 * @return the stadurMaelir
	 */
	public Maelir getStadurMaelir() {
		return stadurMaelir;
	}

	
	/**
	 * @param stadurMaelir the stadurMaelir to set
	 */
	public void setStadurMaelir(Maelir stadurMaelir) {
		this.stadurMaelir = stadurMaelir;
	}

	

}
