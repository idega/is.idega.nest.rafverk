package is.idega.nest.rafverk.business;


import java.io.IOException;
import is.idega.nest.rafverk.bean.validation.ElectricalInstallationValidator;
import com.idega.business.IBOService;
import com.idega.user.business.UserBusiness;
import is.idega.nest.rafverk.domain.Rafverktaka;
import is.idega.nest.rafverk.data.MaelirList;
import org.xml.sax.SAXException;
import is.idega.nest.rafverk.domain.ElectricalInstallation;
import com.idega.fop.data.PropertyTree;
import java.rmi.RemoteException;
import com.idega.fop.data.Property;

public interface ElectricalInstallationRendererBusiness extends IBOService {

	/**
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationRendererBusinessBean#getPDFApplication
	 */
	public String getPDFApplication(Rafverktaka rafverktaka) throws IOException, RemoteException;

	/**
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationRendererBusinessBean#getPDFReport
	 */
	public String getPDFReport(Rafverktaka rafverktaka) throws IOException, RemoteException;

	/**
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationRendererBusinessBean#validateApplication
	 */
	public ElectricalInstallationValidator validateApplication(Rafverktaka rafverktaka) throws SAXException, RemoteException;

	/**
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationRendererBusinessBean#getHeadApplication
	 */
	public Property getHeadApplication(ElectricalInstallation electricalInstallation) throws RemoteException;

	/**
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationRendererBusinessBean#getEnergyCompany
	 */
	public Property getEnergyCompany(ElectricalInstallation electricalInstallation) throws RemoteException;

	/**
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationRendererBusinessBean#getElectricianData
	 */
	public Property getElectricianData(ElectricalInstallation electricalInstallation) throws RemoteException;

	/**
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationRendererBusinessBean#getWorkingplaceData
	 */
	public Property getWorkingplaceData(ElectricalInstallation electricalInstallation) throws RemoteException;

	/**
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationRendererBusinessBean#getEnergyConsumer
	 */
	public Property getEnergyConsumer(ElectricalInstallation electricalInstallation) throws RemoteException;

	/**
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationRendererBusinessBean#getElectronicalProtectiveMeasures
	 */
	public Property getElectronicalProtectiveMeasures(ElectricalInstallation electricalInstallation) throws RemoteException;

	/**
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationRendererBusinessBean#getBodyApplication
	 */
	public Property getBodyApplication(ElectricalInstallation electricalInstallation) throws RemoteException;

	/**
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationRendererBusinessBean#getApplication
	 */
	public Property getApplication(ElectricalInstallation electricalInstallation) throws RemoteException;

	/**
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationRendererBusinessBean#getVoltageSystem
	 */
	public Property getVoltageSystem(ElectricalInstallation electricalInstallation) throws RemoteException;

	/**
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationRendererBusinessBean#addAllMaelir
	 */
	public void addAllMaelir(PropertyTree propertyTree, MaelirList maelirList) throws RemoteException;

	/**
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationRendererBusinessBean#getHeadReport
	 */
	public Property getHeadReport(ElectricalInstallation electricalInstallation) throws RemoteException;

	/**
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationRendererBusinessBean#getAnnouncement
	 */
	public Property getAnnouncement(ElectricalInstallation electricalInstallation) throws RemoteException;

	/**
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationRendererBusinessBean#getBodyReport
	 */
	public Property getBodyReport(ElectricalInstallation electricalInstallation) throws RemoteException;

	/**
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationRendererBusinessBean#getVoltageSystemInReport
	 */
	public Property getVoltageSystemInReport(ElectricalInstallation electricalInstallation) throws RemoteException;

	/**
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationRendererBusinessBean#getElectronicalProtectiveMeasuresInReport
	 */
	public Property getElectronicalProtectiveMeasuresInReport(ElectricalInstallation electricalInstallation) throws RemoteException;

	/**
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationRendererBusinessBean#getGrounding
	 */
	public Property getGrounding(ElectricalInstallation electricalInstallation) throws RemoteException;

	/**
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationRendererBusinessBean#getMeter
	 */
	public Property getMeter(MaelirList maelirList) throws RemoteException;

	/**
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationRendererBusinessBean#getBottomReport
	 */
	public Property getBottomReport(ElectricalInstallation electricalInstallation) throws RemoteException;

	/**
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationRendererBusinessBean#getSwitchPanel
	 */
	public Property getSwitchPanel(ElectricalInstallation electricalInstallation) throws RemoteException;

	/**
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationRendererBusinessBean#getInsulation
	 */
	public Property getInsulation(ElectricalInstallation electricalInstallation) throws RemoteException;

	/**
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationRendererBusinessBean#getShortCircuit
	 */
	public Property getShortCircuit(ElectricalInstallation electricalInstallation) throws RemoteException;

	/**
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationRendererBusinessBean#getVoltage
	 */
	public Property getVoltage(ElectricalInstallation electricalInstallation) throws RemoteException;

	/**
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationRendererBusinessBean#isFuseAttached
	 */
	public Property isFuseAttached(ElectricalInstallation electricalInstallation) throws RemoteException;

	/**
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationRendererBusinessBean#getUserBusiness
	 */
	public UserBusiness getUserBusiness() throws RemoteException;

	/**
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationRendererBusinessBean#getElectricalInstallationBusiness
	 */
	public ElectricalInstallationBusiness getElectricalInstallationBusiness() throws RemoteException;
}