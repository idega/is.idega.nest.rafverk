package is.idega.nest.rafverk.business;


import javax.ejb.CreateException;
import com.idega.business.IBOHome;
import java.rmi.RemoteException;

public interface ElectricalInstallationMessageBusinessHome extends IBOHome {

	public ElectricalInstallationMessageBusiness create() throws CreateException, RemoteException;
}