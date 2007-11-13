/*
 * $Id: SimpleElectricalInstallationWrapper.java,v 1.1 2007/11/13 16:25:19 thomas Exp $
 * Created on Nov 7, 2007
 *
 * Copyright (C) 2007 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package is.idega.nest.rafverk.data;

import java.util.List;

import com.idega.core.location.data.RealEstate;
import com.idega.user.data.Group;
import com.idega.user.data.User;
import com.idega.util.StringHandler;

import is.idega.nest.rafverk.bean.InitialData;
import is.idega.nest.rafverk.bean.TilkynningLokVerksBean;
import is.idega.nest.rafverk.bean.TilkynningVertakaBean;
import is.idega.nest.rafverk.business.ElectricalInstallationBusiness;
import is.idega.nest.rafverk.domain.SimpleElectricalInstallation;


/**
 * 
 *  Last modified: $Date: 2007/11/13 16:25:19 $ by $Author: thomas $
 * 
 * @author <a href="mailto:thomas@idega.com">thomas</a>
 * @version $Revision: 1.1 $
 * 
 * Wrapper to have access to the data of the managed beans by API of ElectricalInstallation 
 * (without the API of Case) 
 * 
 * 
 */
public class SimpleElectricalInstallationWrapper implements SimpleElectricalInstallation {
	
	TilkynningVertakaBean tilkynningVertakaBean;
	
	TilkynningLokVerksBean tilkynningLokVerksBean;
	
	public SimpleElectricalInstallationWrapper(TilkynningVertakaBean tilkynningVertakaBean, TilkynningLokVerksBean tilkynningLokVerksBean) {
		this.tilkynningVertakaBean = tilkynningVertakaBean;
		this.tilkynningLokVerksBean = tilkynningLokVerksBean;
	}
	
	public MaelirList getMaelirList(ElectricalInstallationBusiness electricalInstallationBusiness) {
		MaelirList maelirList = new MaelirList();
		maelirList.setMaelirListMap(tilkynningVertakaBean.getList());
		maelirList.setMaelir(tilkynningLokVerksBean.getMaelir());
		maelirList.setStadurMaelir(tilkynningVertakaBean.getStadurMaelir());
		return maelirList;
	}
	
	
	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#getElectrician()
	 */
	public User getElectrician() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#getElectricianCompany()
	 */
	public String getElectricianCompany() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#getCaseCodeDescription()
	 */
	public String getCaseCodeDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#getCaseCodeKey()
	 */
	public String getCaseCodeKey() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#getAnnouncement()
	 */
	public String getAnnouncement() {
		// TODO Auto-generated method stub
		return tilkynningLokVerksBean.getTilkynnt();
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#getAnnouncementOther()
	 */
	public String getAnnouncementOther() {
		return tilkynningLokVerksBean.getTilkynntAnnad();
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#getAnnouncementRemarks()
	 */
	public String getAnnouncementRemarks() {
		return tilkynningLokVerksBean.getSkyring();
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#getApplication()
	 */
	public List getApplication() {
		return tilkynningVertakaBean.getBeidniUm();
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#getApplicationRemarks()
	 */
	public String getApplicationRemarks() {
		// TODO Auto-generated method stub
		return tilkynningVertakaBean.getSkyringar();
	}



	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#getCurrentLineConnectionModification()
	 */
	public String getCurrentLineConnectionModification() {
		// TODO Auto-generated method stub
		return tilkynningVertakaBean.getHeimtaugTengist();
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#getCurrentLineModification()
	 */
	public String getCurrentLineModification() {
		// TODO Auto-generated method stub
		return tilkynningVertakaBean.getHeimtaug();
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#getElectronicalProtectiveMeasures()
	 */
	public List getElectronicalProtectiveMeasures() {
		// TODO Auto-generated method stub
		return tilkynningVertakaBean.getVarnarradstoefun();
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#getElectronicalProtectiveMeasuresInReport()
	 */
	public List getElectronicalProtectiveMeasuresInReport() {
		// TODO Auto-generated method stub
		return tilkynningLokVerksBean.getVarnarradstoefun();
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#getEnergyCompany()
	 */
	public Group getEnergyCompany() {
		// TODO Auto-generated method stub
		return tilkynningVertakaBean.getEnergyCompany();
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#getEnergyCompanyID()
	 */
	public Integer getEnergyCompanyID() {
		// TODO Auto-generated method stub
		String primaryKey = tilkynningVertakaBean.getOrkuveitufyrirtaeki();
		if (StringHandler.isEmpty(primaryKey)) {
			return null;
		}
		try {
			return Integer.valueOf(primaryKey);
		}
		catch (NumberFormatException ex) {
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#getEnergyConsumerHomePhone()
	 */
	public String getEnergyConsumerHomePhone() {
		// TODO Auto-generated method stub
		return tilkynningVertakaBean.getHeimasimiOrkukaupanda();
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#getEnergyConsumerName()
	 */
	public String getEnergyConsumerName() {
		// TODO Auto-generated method stub
		return tilkynningVertakaBean.getNafnOrkukaupanda();
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#getEnergyConsumerPersonalID()
	 */
	public String getEnergyConsumerPersonalID() {
		// TODO Auto-generated method stub
		return tilkynningVertakaBean.getKennitalaOrkukaupanda();
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#getEnergyConsumerWorkPhone()
	 */
	public String getEnergyConsumerWorkPhone() {
		// TODO Auto-generated method stub
		return tilkynningVertakaBean.getVinnusimiOrkukaupanda();
	}
	
	public String getEnergyConsumerEmail() {
		return tilkynningVertakaBean.getEnergyConsumerEmail();
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#getExternalProjectID()
	 */
	public String getExternalProjectID() {
		// TODO Auto-generated method stub
		return tilkynningVertakaBean.getExternalProjectID();
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#getFuseTime()
	 */
	public String getFuseTime() {
		// TODO Auto-generated method stub
		return tilkynningLokVerksBean.getLekastraumsrofaUtleysingMillisecond();
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#getFuseVoltage()
	 */
	public String getFuseVoltage() {
		// TODO Auto-generated method stub
		return tilkynningLokVerksBean.getSpennuhaekkunUtleysingVolt();
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#getGrounding()
	 */
	public List getGrounding() {
		// TODO Auto-generated method stub
		return tilkynningLokVerksBean.getJardskaut();
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#getGroundingOther()
	 */
	public String getGroundingOther() {
		// TODO Auto-generated method stub
		return tilkynningLokVerksBean.getJardskautAnnad();
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#getGroundingResistence()
	 */
	public String getGroundingResistence() {
		// TODO Auto-generated method stub
		return tilkynningLokVerksBean.getHringrasarvidnamJardskaut();
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#getHomeLineA()
	 */
	public String getHomeLineA() {
		// TODO Auto-generated method stub
		return tilkynningVertakaBean.getStofn1();
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#getHomeLineB()
	 */
	public String getHomeLineB() {
		// TODO Auto-generated method stub
		return tilkynningVertakaBean.getStofn2();
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#getHomeLineC()
	 */
	public String getHomeLineC() {
		// TODO Auto-generated method stub
		return tilkynningVertakaBean.getStofn3();
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#getInsulationResistence()
	 */
	public String getInsulationResistence() {
		// TODO Auto-generated method stub
		return tilkynningLokVerksBean.getEinangrunNeysluveitu();
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#getMeasurementRemarks()
	 */
	public String getMeasurementRemarks() {
		// TODO Auto-generated method stub
		return tilkynningLokVerksBean.getSkyringarMaelingar();
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#getPersonInCharge()
	 */
	public String getPersonInCharge() {
		// TODO Auto-generated method stub
		return tilkynningVertakaBean.getPersonInCharge();
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#getPower()
	 */
	public String getPower() {
		// TODO Auto-generated method stub
		return tilkynningVertakaBean.getUppsett();
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#getRealEstate()
	 */
	public RealEstate getRealEstate() {
		return tilkynningVertakaBean.getRealEstate();
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#getRemarks()
	 */
	public String getRemarks() {
		// TODO Auto-generated method stub
		return tilkynningLokVerksBean.getSkyringar();
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#getResistence()
	 */
	public String getResistence() {
		return tilkynningLokVerksBean.getHringrasarvidnamNeysluveitu();
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#getShortCircuitAmpere()
	 */
	public String getShortCircuitAmpere() {
		return tilkynningLokVerksBean.getSkammhlaupsstraumurNeysluveitu();
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#getSwitchPanelAmpere()
	 */
	public String getSwitchPanelAmpere() {
		return tilkynningLokVerksBean.getSkammhlaupsstraumur();
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#getSwitchPanelModification()
	 */
	public String getSwitchPanelModification() {
		return tilkynningVertakaBean.getAdaltafla();
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#getSwitchPanelNumber()
	 */
	public String getSwitchPanelNumber() {
		return tilkynningVertakaBean.getNumerToeflu();
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#getSwitchPanelResistence()
	 */
	public String getSwitchPanelResistence() {
		return tilkynningLokVerksBean.getHringrasarvidam();
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#getType()
	 */
	public String getType() {
		return tilkynningVertakaBean.getNotkunarflokkur();
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#getTypeInReport()
	 */
	public String getTypeInReport() {
		return tilkynningLokVerksBean.getNotkunarflokkur();
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#getVoltagePhaseN()
	 */
	public String getVoltagePhaseN() {
		return tilkynningLokVerksBean.getMaeldSpennaFasiN();
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#getVoltagePhasePhase()
	 */
	public String getVoltagePhasePhase() {
		return tilkynningLokVerksBean.getMaeldSpennaFasiFasi();
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#getVoltageSystem()
	 */
	public String getVoltageSystem() {
		// TODO Auto-generated method stub
		return tilkynningVertakaBean.getSpennukerfi();
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#getVoltageSystemInReport()
	 */
	public String getVoltageSystemInReport() {
		// TODO Auto-generated method stub
		return tilkynningLokVerksBean.getSpennukerfi();
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#getVoltageSystemOther()
	 */
	public String getVoltageSystemOther() {
		// TODO Auto-generated method stub
		return tilkynningVertakaBean.getAnnad();
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#getVoltageSystemOtherInReport()
	 */
	public String getVoltageSystemOtherInReport() {
		// TODO Auto-generated method stub
		return tilkynningLokVerksBean.getAnnad();
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#isFuseAttached()
	 */
	public Boolean isFuseAttached() {
		String fuseAttachedString = tilkynningLokVerksBean.getLekastraumsrofi();
		Boolean fuseAttached = null;
		if (InitialData.LEKASTRAUMSROFI_I_LAGI_KEY.equals(fuseAttachedString)) {
			fuseAttached = Boolean.TRUE;
		}
		else if (InitialData.LEKASTRAUMSROFI_EKKI_TIL_STADAR_KEY.equals(fuseAttachedString)) {
			fuseAttached = Boolean.FALSE;
		}
		return fuseAttached;
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#setAnnouncement(java.lang.String)
	 */
	public void setAnnouncement(String announcement) {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#setAnnouncementOther(java.lang.String)
	 */
	public void setAnnouncementOther(String announcementOther) {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#setAnnouncementRemarks(java.lang.String)
	 */
	public void setAnnouncementRemarks(String announcementRemarks) {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#setApplication(java.util.List)
	 */
	public void setApplication(List application) {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#setApplicationRemarks(java.lang.String)
	 */
	public void setApplicationRemarks(String applicationRemarks) {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#setCurrentLineConnectionModification(java.lang.String)
	 */
	public void setCurrentLineConnectionModification(String currentLineConnectionModification) {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#setCurrentLineModification(java.lang.String)
	 */
	public void setCurrentLineModification(String currentLineModification) {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#setElectrician(com.idega.user.data.User)
	 */
	public void setElectrician(User electrician) {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#setElectricianCompany(java.lang.String)
	 */
	public void setElectricianCompany(String electricianCompany) {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#setElectronicalProtectiveMeasures(java.util.List)
	 */
	public void setElectronicalProtectiveMeasures(List epm) {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#setElectronicalProtectiveMeasuresInReport(java.util.List)
	 */
	public void setElectronicalProtectiveMeasuresInReport(List epm) {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#setEnergyCompany(com.idega.user.data.Group)
	 */
	public void setEnergyCompany(Group energyCompany) {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#setEnergyCompanyID(java.lang.Integer)
	 */
	public void setEnergyCompanyID(Integer energyCompanyID) {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#setEnergyConsumerHomePhone(java.lang.String)
	 */
	public void setEnergyConsumerHomePhone(String consumerHomePhone) {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#setEnergyConsumerName(java.lang.String)
	 */
	public void setEnergyConsumerName(String energyConsumerName) {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#setEnergyConsumerPersonalID(java.lang.String)
	 */
	public void setEnergyConsumerPersonalID(String energyConsumerPersonalID) {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#setEnergyConsumerWorkPhone(java.lang.String)
	 */
	public void setEnergyConsumerWorkPhone(String consumerWorkPhone) {
		// TODO Auto-generated method stub
	}

	public void setEnergyConsumerEmail(String consumerEmail) {
		// nothing
	}
	
	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#setExternalProjectID(java.lang.String)
	 */
	public void setExternalProjectID(String externalProjectID) {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#setFuseAttached(java.lang.Boolean)
	 */
	public void setFuseAttached(Boolean fuseAttached) {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#setFuseTime(java.lang.String)
	 */
	public void setFuseTime(String fuseTime) {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#setFuseVoltage(java.lang.String)
	 */
	public void setFuseVoltage(String fuseVoltage) {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#setGrounding(java.util.List)
	 */
	public void setGrounding(List grounding) {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#setGroundingOther(java.lang.String)
	 */
	public void setGroundingOther(String grounding) {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#setGroundingResistence(java.lang.String)
	 */
	public void setGroundingResistence(String groundingResistence) {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#setHomeLineA(java.lang.String)
	 */
	public void setHomeLineA(String homelLineA) {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#setHomeLineB(java.lang.String)
	 */
	public void setHomeLineB(String homelLineB) {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#setHomeLineC(java.lang.String)
	 */
	public void setHomeLineC(String homelLineC) {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#setInsulationResistence(java.lang.String)
	 */
	public void setInsulationResistence(String insulationResistence) {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#setMeasurementRemarks(java.lang.String)
	 */
	public void setMeasurementRemarks(String measurementRemarks) {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#setPersonInCharge(java.lang.String)
	 */
	public void setPersonInCharge(String personInCharge) {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#setPower(java.lang.String)
	 */
	public void setPower(String power) {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#setRealEstate(com.idega.core.location.data.RealEstate)
	 */
	public void setRealEstate(RealEstate realEstate) {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#setRemarks(java.lang.String)
	 */
	public void setRemarks(String remarks) {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#setResistence(java.lang.String)
	 */
	public void setResistence(String resistence) {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#setShortCircuitAmpere(java.lang.String)
	 */
	public void setShortCircuitAmpere(String shortCircuitAmpere) {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#setSwitchPanelAmpere(java.lang.String)
	 */
	public void setSwitchPanelAmpere(String switchPanelAmpere) {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#setSwitchPanelModification(java.lang.String)
	 */
	public void setSwitchPanelModification(String switchPanelModification) {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#setSwitchPanelNumber(java.lang.String)
	 */
	public void setSwitchPanelNumber(String switchPanelNumber) {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#setSwitchPanelResistence(java.lang.String)
	 */
	public void setSwitchPanelResistence(String switchPanelResistence) {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#setType(java.lang.String)
	 */
	public void setType(String type) {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#setTypeInReport(java.lang.String)
	 */
	public void setTypeInReport(String typeInReport) {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#setVoltagePhaseN(java.lang.String)
	 */
	public void setVoltagePhaseN(String voltagePhaseN) {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#setVoltagePhasePhase(java.lang.String)
	 */
	public void setVoltagePhasePhase(String voltagePhasePhase) {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#setVoltageSystem(java.lang.String)
	 */
	public void setVoltageSystem(String voltageSystem) {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#setVoltageSystemInReport(java.lang.String)
	 */
	public void setVoltageSystemInReport(String voltageSystem) {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#setVoltageSystemOther(java.lang.String)
	 */
	public void setVoltageSystemOther(String voltageSystemOther) {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see is.idega.nest.rafverk.domain.SimpleElectricalInstallation#setVoltageSystemOtherInReport(java.lang.String)
	 */
	public void setVoltageSystemOtherInReport(String voltageSystemOtherInReport) {
		// TODO Auto-generated method stub
	}
}
