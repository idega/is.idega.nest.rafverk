package is.idega.nest.rafverk.domain;


import com.idega.user.data.Group;
import com.idega.location.data.Location;
import com.idega.user.data.User;
import java.util.List;
import com.idega.data.IDOEntity;

public interface ElectricalInstallation extends IDOEntity {

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#getCaseCodeDescription
	 */
	public String getCaseCodeDescription();

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#getCaseCodeKey
	 */
	public String getCaseCodeKey();

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#setExternalProjectID
	 */
	public void setExternalProjectID(String externalProjectID);

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#getExternalProjectID
	 */
	public String getExternalProjectID();

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#setPersonInCharge
	 */
	public void setPersonInCharge(String personInCharge);

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#getPersonInCharge
	 */
	public String getPersonInCharge();

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#setEnergyConsumerHomePhone
	 */
	public void setEnergyConsumerHomePhone(String consumerHomePhone);

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#getEneryConsumerHomePhone
	 */
	public String getEneryConsumerHomePhone();

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#setEnergyConsumerWorkPhone
	 */
	public void setEnergyConsumerWorkPhone(String consumerWorkPhone);

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#getEnergyConsumerWorkPhone
	 */
	public String getEnergyConsumerWorkPhone();

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#setType
	 */
	public void setType(String type);

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#getType
	 */
	public String getType();

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#setTypeInReport
	 */
	public void setTypeInReport(String typeInReport);

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#getTypeInReport
	 */
	public String getTypeInReport();

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#setCurrentLineModification
	 */
	public void setCurrentLineModification(String currentLineModification);

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#getCurrentLineModification
	 */
	public String getCurrentLineModification();

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#setCurrentLineConnectionModification
	 */
	public void setCurrentLineConnectionModification(String currentLineConnectionModification);

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#getCurrentLineConnectionModification
	 */
	public String getCurrentLineConnectionModification();

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#setHomeLineA
	 */
	public void setHomeLineA(String homelLineA);

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#getHomeLineA
	 */
	public String getHomeLineA();

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#setHomeLineB
	 */
	public void setHomeLineB(String homelLineB);

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#getHomeLineB
	 */
	public String getHomeLineB();

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#setHomeLineC
	 */
	public void setHomeLineC(String homelLineC);

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#getHomeLineC
	 */
	public String getHomeLineC();

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#setSwitchPanelModification
	 */
	public void setSwitchPanelModification(String switchPanelModification);

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#getSwitchPanelModification
	 */
	public String getSwitchPanelModification();

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#setElectronicalProtectiveMeasures
	 */
	public void setElectronicalProtectiveMeasures(List epm);

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#getElectronicalProtectiveMeasures
	 */
	public List getElectronicalProtectiveMeasures();

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#setElectronicalProtectiveMeasuresInReport
	 */
	public void setElectronicalProtectiveMeasuresInReport(List epm);

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#getElectronicalProtectiveMeasuresInReport
	 */
	public List getElectronicalProtectiveMeasuresInReport();

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#setApplication
	 */
	public void setApplication(List application);

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#getApplication
	 */
	public List getApplication();

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#setPower
	 */
	public void setPower(String power);

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#getPower
	 */
	public String getPower();

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#setSwitchPanelNumber
	 */
	public void setSwitchPanelNumber(String switchPanelNumber);

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#getSwitchPanelNumber
	 */
	public String getSwitchPanelNumber();

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#setVoltageSystem
	 */
	public void setVoltageSystem(String voltageSystem);

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#getVoltageSystem
	 */
	public String getVoltageSystem();

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#setVoltageSystemInReport
	 */
	public void setVoltageSystemInReport(String voltageSystem);

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#getVoltageSystemInReport
	 */
	public String getVoltageSystemInReport();

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#setVoltageSystemOther
	 */
	public void setVoltageSystemOther(String voltageSystemOther);

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#getVoltageSystemOther
	 */
	public String getVoltageSystemOther();

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#setVoltageSystemOtherInReport
	 */
	public void setVoltageSystemOtherInReport(String voltageSystemOtherInReport);

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#getVoltageSystemOtherInReport
	 */
	public String getVoltageSystemOtherInReport();

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#setApplicationRemarks
	 */
	public void setApplicationRemarks(String applicationRemarks);

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#getApplicationRemarks
	 */
	public String getApplicationRemarks();

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#setAnnouncement
	 */
	public void setAnnouncement(String announcement);

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#getAnnouncement
	 */
	public String getAnnouncement();

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#setAnnouncementOther
	 */
	public void setAnnouncementOther(String announcementOther);

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#getAnnouncementOther
	 */
	public String getAnnouncementOther();

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#setAnnouncementRemarks
	 */
	public void setAnnouncementRemarks(String announcementRemarks);

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#getAnnouncementRemarks
	 */
	public String getAnnouncementRemarks();

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#setGrounding
	 */
	public void setGrounding(List grounding);

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#getGrounding
	 */
	public List getGrounding();

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#setGroundingOther
	 */
	public void setGroundingOther(String grounding);

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#getGroundingOther
	 */
	public String getGroundingOther();

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#setRemarks
	 */
	public void setRemarks(String remarks);

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#getRemarks
	 */
	public String getRemarks();

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#setSwitchPanelResistence
	 */
	public void setSwitchPanelResistence(String switchPanelResistence);

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#getSwitchPanelResistence
	 */
	public String getSwitchPanelResistence();

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#setSwitchPanelAmpere
	 */
	public void setSwitchPanelAmpere(String switchPanelAmpere);

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#getSwitchPanelAmpere
	 */
	public String getSwitchPanelAmpere();

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#setInsulationResistence
	 */
	public void setInsulationResistence(String insulationResistence);

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#getInsulationResistence
	 */
	public String getInsulationResistence();

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#setGroundingResistence
	 */
	public void setGroundingResistence(String groundingResistence);

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#getGroundingResistence
	 */
	public String getGroundingResistence();

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#setShortCircuitAmpere
	 */
	public void setShortCircuitAmpere(String shortCircuitAmpere);

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#getShortCircuitAmpere
	 */
	public String getShortCircuitAmpere();

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#setResistence
	 */
	public void setResistence(String resistence);

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#getResistence
	 */
	public String getResistence();

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#setVoltagePhaseN
	 */
	public void setVoltagePhaseN(String voltagePhaseN);

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#getVoltagePhaseN
	 */
	public String getVoltagePhaseN();

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#setVoltagePhasePhase
	 */
	public void setVoltagePhasePhase(String voltagePhasePhase);

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#getVoltagePhasePhase
	 */
	public String getVoltagePhasePhase();

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#setFuseAttached
	 */
	public void setFuseAttached(boolean fuseAttached);

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#isFuseAttached
	 */
	public boolean isFuseAttached();

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#setFuseVoltage
	 */
	public void setFuseVoltage(String fuseVoltage);

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#getFuseVoltage
	 */
	public String getFuseVoltage();

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#setFuseTime
	 */
	public void setFuseTime(String fuseTime);

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#getFuseTime
	 */
	public String getFuseTime();

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#setMeasurementRemarks
	 */
	public void setMeasurementRemarks(String measurementRemarks);

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#getMeasurementRemarks
	 */
	public String getMeasurementRemarks();

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#setEnergyCompany
	 */
	public void setEnergyCompany(Group energyCompany);

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#getEnergyCompany
	 */
	public Group getEnergyCompany();

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#setEnergyConsumerPersonalID
	 */
	public void setEnergyConsumerPersonalID(String energyConsumerPersonalID);

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#getEnergyConsumerPersonalID
	 */
	public String getEnergyConsumerPersonalID();

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#setEnergyConsumerName
	 */
	public void setEnergyConsumerName(String energyConsumerName);

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#getEnergyConsumerName
	 */
	public String getEnergyConsumerName();

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#setElectrician
	 */
	public void setElectrician(User electrician);

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#getElectrician
	 */
	public User getElectrician();

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#setLocation
	 */
	public void setLocation(Location location);

	/**
	 * @see is.idega.nest.rafverk.domain.ElectricalInstallationBMPBean#getLocation
	 */
	public Location getLocation();
}