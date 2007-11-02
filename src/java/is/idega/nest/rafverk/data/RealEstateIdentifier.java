/*
 * $Id: RealEstateIdentifier.java,v 1.4 2007/11/02 16:37:39 thomas Exp $
 * Created on Sep 13, 2007
 *
 * Copyright (C) 2007 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package is.idega.nest.rafverk.data;

import is.fmr.landskra.Fasteign;

import java.util.StringTokenizer;

import com.idega.core.location.data.RealEstate;
import com.idega.util.StringHandler;


/**
 * 
 *  Last modified: $Date: 2007/11/02 16:37:39 $ by $Author: thomas $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.4 $
 */
public class RealEstateIdentifier {
	
	private final static String AT_TOKEN = "@";
	
	private final static String NULL_STRING = "NULL";
	
	public final static String LAND_REGISTER_MAP_COLUMN = "LAND_REGISTER";
	
	public final static String REAL_ESTATE_NUMBER_COLUMN = "REAL_ESTATE_NUMBER";
	
	public final static String REAL_ESTATE_UNIT_COLUMN = "REAL_ESTATE_UNIT";
	
	public final static String REAL_ESTATE_CODE_COLUMN = "REAL_ESTATE_CODE";
	
	public static RealEstateIdentifier getInstance(String realEstateIdentifierAsString) {
		RealEstateIdentifier realEstateIdentifier = new RealEstateIdentifier();
		if (StringHandler.isEmpty(realEstateIdentifierAsString)) {
			return realEstateIdentifier;
		}
		StringTokenizer tokenizer = new StringTokenizer(realEstateIdentifierAsString, RealEstateIdentifier.AT_TOKEN);
		if (tokenizer.hasMoreTokens()) {
			
			realEstateIdentifier.setLandNumber(decodeValue(tokenizer.nextToken()));
		}
		if (tokenizer.hasMoreTokens()) {
			realEstateIdentifier.setRealEstateNumber(decodeValue(tokenizer.nextToken()));
		}
		if (tokenizer.hasMoreTokens()) {
			realEstateIdentifier.setRealEstateUnit(decodeValue(tokenizer.nextToken()));
		}
		if (tokenizer.hasMoreTokens()) {
			realEstateIdentifier.setRealEstateCode(decodeValue(tokenizer.nextToken()));
		}
		return realEstateIdentifier;
 	}
	
	private static String decodeValue(String value) {
		if (StringHandler.isEmpty(value) || RealEstateIdentifier.NULL_STRING.equals(value)) {
			return null;
		}
		return value;
	}
	
	public static RealEstateIdentifier getInstance(RealEstate realEstate) {
		RealEstateIdentifier realEstateIdentifier = new RealEstateIdentifier();
		if (realEstate == null) {
			return realEstateIdentifier;
		}
		realEstateIdentifier.setLandNumber(realEstate.getLandRegisterMapNumber());
		realEstateIdentifier.setRealEstateNumber(realEstate.getRealEstateNumber());
		realEstateIdentifier.setRealEstateUnit(realEstate.getRealEstateUnit());
		realEstateIdentifier.setRealEstateCode(realEstate.getRealEstateCode());
		return realEstateIdentifier;
	}
	
	public static RealEstateIdentifier getInstance(Fasteign fasteign) {
		RealEstateIdentifier realEstateIdentifier = new RealEstateIdentifier();
		if (fasteign == null) {
			return realEstateIdentifier;
		}
		realEstateIdentifier.setLandNumber(fasteign.getLandnumer());
		realEstateIdentifier.setRealEstateNumber(fasteign.getFastaNumer());
		realEstateIdentifier.setRealEstateUnit(fasteign.getMatseiningNumer());
		realEstateIdentifier.setRealEstateCode(fasteign.getMerking());
		return realEstateIdentifier;
	}
		

	public static String getIdentifierAsString(RealEstate realEstate) {
		String landNumer = (realEstate == null) ? null : realEstate.getLandRegisterMapNumber();
		String fastaNumer = (realEstate == null) ? null : realEstate.getRealEstateNumber();
		String matseiningNumer = (realEstate == null) ? null : realEstate.getRealEstateUnit();
		String merking = (realEstate == null) ? null : realEstate.getRealEstateUnit();

		StringBuffer buffer = new StringBuffer(StringHandler.replaceIfEmpty(landNumer,RealEstateIdentifier.NULL_STRING));
		buffer.append(RealEstateIdentifier.AT_TOKEN);
		buffer.append(StringHandler.replaceIfEmpty(fastaNumer, RealEstateIdentifier.NULL_STRING));
		buffer.append(RealEstateIdentifier.AT_TOKEN);
		buffer.append(StringHandler.replaceIfEmpty(matseiningNumer, RealEstateIdentifier.NULL_STRING));
		buffer.append(RealEstateIdentifier.AT_TOKEN);
		buffer.append(StringHandler.replaceIfEmpty(merking, RealEstateIdentifier.NULL_STRING));
		return buffer.toString();
	}
	
	public static String getIdentifierAsString(Fasteign fasteign) {
		String landNumer = (fasteign == null) ? null : fasteign.getLandnumer();
		String fastaNumer = (fasteign == null) ? null :fasteign.getFastaNumer();
		String matseiningNumer = (fasteign == null) ? null : fasteign.getMatseiningNumer();
		String merking = (fasteign == null) ? null : fasteign.getMerking();

		StringBuffer buffer = new StringBuffer(StringHandler.replaceIfEmpty(landNumer,RealEstateIdentifier.NULL_STRING));
		buffer.append(RealEstateIdentifier.AT_TOKEN);
		buffer.append(StringHandler.replaceIfEmpty(fastaNumer, RealEstateIdentifier.NULL_STRING));
		buffer.append(RealEstateIdentifier.AT_TOKEN);
		buffer.append(StringHandler.replaceIfEmpty(matseiningNumer, RealEstateIdentifier.NULL_STRING));
		buffer.append(RealEstateIdentifier.AT_TOKEN);
		buffer.append(StringHandler.replaceIfEmpty(merking, RealEstateIdentifier.NULL_STRING));
		return buffer.toString();
	}
	
	public static boolean hasIdentifier(Fasteign fasteign, String identifierAsString) {
		String identiferFasteign = RealEstateIdentifier.getIdentifierAsString(fasteign);
		return identiferFasteign.equals(identifierAsString);
	}

	
	private String landNumber;
	
	private String realEstateNumber;
	
	private String realEstateUnit;
	
	private String realEstateCode;

	private RealEstateIdentifier() {
	}
	
	
	/**
	 * @return Returns the landNumber.
	 */
	public String getLandNumber() {
		return landNumber;
	}

	
	/**
	 * @param landNumber The landNumber to set.
	 */
	public void setLandNumber(String landNumber) {
		this.landNumber = landNumber;
	}

	
	/**
	 * @return Returns the realEstateNumber.
	 */
	public String getRealEstateNumber() {
		return realEstateNumber;
	}

	
	/**
	 * @param realEstateNumber The realEstateNumber to set.
	 */
	public void setRealEstateNumber(String realEstateNumber) {
		this.realEstateNumber = realEstateNumber;
	}

	
	/**
	 * @return Returns the realEstateUnit.
	 */
	public String getRealEstateUnit() {
		return realEstateUnit;
	}

	
	/**
	 * @param realEstateUnit The realEstateUnit to set.
	 */
	public void setRealEstateUnit(String realEstateUnit) {
		this.realEstateUnit = realEstateUnit;
	}

	
	/**
	 * @return Returns the realEstateCode.
	 */
	public String getRealEstateCode() {
		return realEstateCode;
	}

	
	/**
	 * @param realEstateCode The realEstateCode to set.
	 */
	public void setRealEstateCode(String realEstateCode) {
		this.realEstateCode = realEstateCode;
	}
	
	public String getIdentifierAsString() {
		StringBuffer buffer = new StringBuffer(StringHandler.replaceIfEmpty(landNumber,RealEstateIdentifier.NULL_STRING));
		buffer.append(RealEstateIdentifier.AT_TOKEN);
		buffer.append(StringHandler.replaceIfEmpty(realEstateNumber, RealEstateIdentifier.NULL_STRING));
		buffer.append(RealEstateIdentifier.AT_TOKEN);
		buffer.append(StringHandler.replaceIfEmpty(realEstateUnit, RealEstateIdentifier.NULL_STRING));
		buffer.append(RealEstateIdentifier.AT_TOKEN);
		buffer.append(StringHandler.replaceIfEmpty(realEstateCode, RealEstateIdentifier.NULL_STRING));
		return buffer.toString();
	}
	
	public boolean isFasteign(Fasteign fasteign) {
		return (isEqualTo(fasteign.getLandnumer(), getLandNumber()) &&
				isEqualTo(fasteign.getFastaNumer(), getRealEstateNumber()) &&
				isEqualTo(fasteign.getMatseiningNumer(), getRealEstateUnit()) &&
				isEqualTo(fasteign.getMerking(), getRealEstateCode()));
	}
	
	private boolean isEqualTo(String first, String second) {
		if (StringHandler.isNotEmpty(first) && StringHandler.isNotEmpty(second)) {
			return first.equals(second);
		}
		return (StringHandler.isEmpty(first) && StringHandler.isEmpty(second));
		
	}
	
	public boolean isDummy() {
		return (landNumber == null &&
			realEstateNumber == null &&
			realEstateUnit == null &&
			realEstateCode == null);
	}
}
