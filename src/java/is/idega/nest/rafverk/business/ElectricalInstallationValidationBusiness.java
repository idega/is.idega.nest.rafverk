package is.idega.nest.rafverk.business;


import is.idega.nest.rafverk.domain.SimpleElectricalInstallation;
import java.util.Map;
import com.idega.business.IBOService;
import java.rmi.RemoteException;

public interface ElectricalInstallationValidationBusiness extends IBOService {

	/**
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationValidationBusinessBean#isApplicationValid
	 */
	public boolean isApplicationValid(Map validationResults) throws RemoteException;

	/**
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationValidationBusinessBean#isReportValid
	 */
	public boolean isReportValid(Map validationResults) throws RemoteException;

	/**
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationValidationBusinessBean#validateApplication
	 */
	public Map validateApplication(SimpleElectricalInstallation electricalInstallation) throws RemoteException,
			RemoteException;

	/**
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationValidationBusinessBean#validateReport
	 */
	public Map validateReport(SimpleElectricalInstallation electricalInstallation) throws RemoteException,
			RemoteException;
}