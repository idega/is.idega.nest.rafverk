package is.idega.nest.rafverk.business;


import com.idega.business.IBOService;
import com.idega.user.data.User;
import is.idega.nest.rafverk.domain.ElectricalInstallation;
import java.rmi.RemoteException;

public interface UserMessagesBusiness extends IBOService {

	/**
	 * @see is.idega.nest.rafverk.business.UserMessagesBusinessBean#getMessageAfterAcceptingRequestForChangeOfElectrician
	 */
	public String getMessageAfterAcceptingRequestForChangeOfElectrician(ElectricalInstallation electricalInstallation)
			throws RemoteException;

	/**
	 * @see is.idega.nest.rafverk.business.UserMessagesBusinessBean#getMessageAfterRemovingRequestForChangeOfElectrician
	 */
	public String getMessageAfterRemovingRequestForChangeOfElectrician(ElectricalInstallation electricalInstallation)
			throws RemoteException;

	/**
	 * @see is.idega.nest.rafverk.business.UserMessagesBusinessBean#getMessageAfterSendingRequestForChangeOfElectrician
	 */
	public String getMessageAfterSendingRequestForChangeOfElectrician(ElectricalInstallation electricalInstallation)
			throws RemoteException;

	/**
	 * @see is.idega.nest.rafverk.business.UserMessagesBusinessBean#getMessageAfterSendingInApplication
	 */
	public String getMessageAfterSendingInApplication(ElectricalInstallation electricalInstallation)
			throws RemoteException;

	/**
	 * @see is.idega.nest.rafverk.business.UserMessagesBusinessBean#getMessageAfterSendingInApplicationReport
	 */
	public String getMessageAfterSendingInApplicationReport(ElectricalInstallation electricalInstallation)
			throws RemoteException;

	/**
	 * @see is.idega.nest.rafverk.business.UserMessagesBusinessBean#getMessageAfterCheckingOutWorkingPlace
	 */
	public String getMessageAfterCheckingOutWorkingPlace(ElectricalInstallation electricalInstallation)
			throws RemoteException;

	/**
	 * @see is.idega.nest.rafverk.business.UserMessagesBusinessBean#getMessageAfterStoringApplication
	 */
	public String getMessageAfterStoringApplication(ElectricalInstallation electricalInstallation)
			throws RemoteException;

	/**
	 * @see is.idega.nest.rafverk.business.UserMessagesBusinessBean#getMessageAfterStoringApplicationReport
	 */
	public String getMessageAfterStoringApplicationReport(ElectricalInstallation electricalInstallation)
			throws RemoteException;

	/**
	 * @see is.idega.nest.rafverk.business.UserMessagesBusinessBean#sendUserMessageAfterChangingElectrician
	 */
	public String sendUserMessageAfterChangingElectrician(ElectricalInstallation oldElectricalInstallation,
			ElectricalInstallation newElectricalInstallation) throws RemoteException;

	/**
	 * @see is.idega.nest.rafverk.business.UserMessagesBusinessBean#sendUserMessageRequestingChangeOfElectrician
	 */
	public String sendUserMessageRequestingChangeOfElectrician(ElectricalInstallation electricalInstallation,
			User sender) throws RemoteException;

	/**
	 * @see is.idega.nest.rafverk.business.UserMessagesBusinessBean#sendDataToEnergyCompany
	 */
	public String sendDataToEnergyCompany(ElectricalInstallation electricalInstallation, String uriToResource)
			throws RemoteException;
}