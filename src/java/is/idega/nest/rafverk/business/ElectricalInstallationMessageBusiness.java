package is.idega.nest.rafverk.business;


import com.idega.business.IBOService;
import java.io.File;
import com.idega.user.data.User;
import is.idega.nest.rafverk.domain.ElectricalInstallation;
import java.rmi.RemoteException;

public interface ElectricalInstallationMessageBusiness extends IBOService {

	/**
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationMessageBusinessBean#createStatusChangedUserMessage
	 */
	public String createStatusChangedUserMessage(ElectricalInstallation electricalInstallation, User sender, String text)
			throws RemoteException;

	/**
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationMessageBusinessBean#sendDataToEnergyCompany
	 */
	public String sendDataToEnergyCompany(ElectricalInstallation electricalInstallation, String subject, String text,
			String uriToResource) throws RemoteException;

	/**
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationMessageBusinessBean#sendEmail
	 */
	public String sendEmail(User fromUser, User toUser, String subject, String text, String smtpMailServer)
			throws RemoteException;

	/**
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationMessageBusinessBean#sendEmail
	 */
	public String sendEmail(User fromUser, User toUser, String subject, String text, File resource,
			String smtpMailServer) throws RemoteException;
}