package is.idega.nest.rafverk.business;


import com.idega.util.datastructures.list.KeyValuePair;
import com.idega.block.process.business.CaseBusiness;
import com.idega.block.process.data.Case;
import javax.ejb.FinderException;
import com.idega.block.process.data.CaseCode;
import com.idega.business.IBOService;
import java.util.List;
import is.idega.nest.rafverk.domain.ElectricalInstallation;
import java.rmi.RemoteException;

public interface ElectricalInstallationCaseBusiness extends IBOService, CaseBusiness {

	/**
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationCaseBusinessBean#getCaseCodeForElectricalInstallationChange
	 */
	public CaseCode getCaseCodeForElectricalInstallationChange() throws RemoteException;

	/**
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationCaseBusinessBean#getParentCaseAsElectricalInstallation
	 */
	public ElectricalInstallation getParentCaseAsElectricalInstallation(Case childCase) throws FinderException,
			RemoteException;

	/**
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationCaseBusinessBean#getChildrenOfCaseAsElectricalInstallation
	 */
	public List getChildrenOfCaseAsElectricalInstallation(Case parentCase) throws RemoteException;

	/**
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationCaseBusinessBean#sendRequestForChangingElectrician
	 */
	public KeyValuePair sendRequestForChangingElectrician(ElectricalInstallation electricalInstallation)
			throws RemoteException;
}