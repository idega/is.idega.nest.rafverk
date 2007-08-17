package is.idega.nest.rafverk.business;


import com.idega.block.process.business.CaseBusiness;
import com.idega.block.process.data.CaseCode;
import com.idega.business.IBOService;
import java.rmi.RemoteException;

public interface ElectricalInstallationCaseBusiness extends IBOService, CaseBusiness {

	/**
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationCaseBusinessBean#getCaseCodeForElectricalInstallationChange
	 */
	public CaseCode getCaseCodeForElectricalInstallationChange() throws RemoteException;
}