package is.idega.nest.rafverk.business;


import javax.ejb.CreateException;
import com.idega.business.IBOHome;
import java.rmi.RemoteException;

public interface UserMessagesBusinessHome extends IBOHome {

	public UserMessagesBusiness create() throws CreateException, RemoteException;
}