package is.idega.nest.rafverk.business;


import java.io.IOException;
import java.util.Map;
import com.idega.business.IBOService;
import is.idega.nest.rafverk.domain.ElectricalInstallation;
import java.rmi.RemoteException;

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
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationRendererBusinessBean#validateApplication
	 */
	public Map validateApplication(ElectricalInstallation electricalInstallation) throws RemoteException;
}