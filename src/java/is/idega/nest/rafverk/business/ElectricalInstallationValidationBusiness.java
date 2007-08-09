package is.idega.nest.rafverk.business;


import java.util.Map;
import com.idega.business.IBOService;
import is.idega.nest.rafverk.domain.ElectricalInstallation;
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
	public Map validateApplication(ElectricalInstallation electricalInstallation) throws RemoteException;

	/**
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationValidationBusinessBean#validateReport
	 */
	public Map validateReport(ElectricalInstallation electricalInstallation) throws RemoteException;
}