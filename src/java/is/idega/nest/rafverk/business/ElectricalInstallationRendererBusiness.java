package is.idega.nest.rafverk.business;


import is.idega.nest.rafverk.domain.SimpleElectricalInstallation;
import java.io.IOException;
import com.idega.business.IBOService;
import is.idega.nest.rafverk.domain.ElectricalInstallation;
import java.rmi.RemoteException;
import com.idega.fop.data.Property;

public interface ElectricalInstallationRendererBusiness extends IBOService {

	/**
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationRendererBusinessBean#getPDFApplication
	 */
	public String getPDFApplication(ElectricalInstallation electricalInstallation) throws IOException, RemoteException;

	/**
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationRendererBusinessBean#getXMLApplication
	 */
	public String getXMLApplication(ElectricalInstallation electricalInstallation) throws IOException, RemoteException;

	/**
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationRendererBusinessBean#getPDFReport
	 */
	public String getPDFReport(ElectricalInstallation electricalInstallation) throws IOException, RemoteException;

	/**
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationRendererBusinessBean#getXMLReport
	 */
	public String getXMLReport(ElectricalInstallation electricalInstallation) throws IOException, RemoteException;

	/**
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationRendererBusinessBean#getApplicationProperty
	 */
	public Property getApplicationProperty(SimpleElectricalInstallation electricalInstallation) throws RemoteException;

	/**
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationRendererBusinessBean#getReportProperty
	 */
	public Property getReportProperty(SimpleElectricalInstallation electricalInstallation) throws RemoteException;
}