package is.idega.nest.rafverk.dwr;


import javax.ejb.CreateException;
import com.idega.business.IBOHome;
import java.rmi.RemoteException;

public interface NestServiceHome extends IBOHome {

	public NestService create() throws CreateException, RemoteException;
}