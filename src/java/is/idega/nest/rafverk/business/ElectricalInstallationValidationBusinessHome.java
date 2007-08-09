package is.idega.nest.rafverk.business;


import javax.ejb.CreateException;
import com.idega.business.IBOHome;
import java.rmi.RemoteException;

public interface ElectricalInstallationValidationBusinessHome extends IBOHome {

	public ElectricalInstallationValidationBusiness create() throws CreateException, RemoteException;
}