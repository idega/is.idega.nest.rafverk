package is.idega.nest.rafverk.importer;


import javax.ejb.CreateException;
import com.idega.business.IBOHome;
import java.rmi.RemoteException;

public interface ElectricalInstallationsImportFileHandlerHome extends IBOHome {
	public ElectricalInstallationsImportFileHandler create()
			throws CreateException, RemoteException;
}