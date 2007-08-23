package is.idega.nest.rafverk.business;


import is.idega.nest.rafverk.domain.ElectricalInstallation;

import java.rmi.RemoteException;

import javax.ejb.FinderException;

import com.idega.block.process.business.CaseBusiness;
import com.idega.block.process.data.Case;
import com.idega.block.process.data.CaseCode;

public interface ElectricalInstallationCaseBusiness extends CaseBusiness {

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
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationCaseBusinessBean#sendRequestForChangingElectrician
	 */
	public String sendRequestForChangingElectrician(ElectricalInstallation electricalInstallation)
			throws RemoteException;
}