package is.idega.nest.rafverk.importer;


import com.idega.user.data.Group;
import com.idega.business.IBOService;
import java.util.List;
import com.idega.block.importer.business.ImportFileHandler;
import java.rmi.RemoteException;
import com.idega.block.importer.data.ImportFile;

public interface ElectricalInstallationsImportFileHandler extends IBOService,
		ImportFileHandler {
	/**
	 * @see is.idega.nest.rafverk.importer.ElectricalInstallationsImportFileHandlerBean#getFailedRecords
	 */
	public List getFailedRecords() throws RemoteException, RemoteException;

	/**
	 * @see is.idega.nest.rafverk.importer.ElectricalInstallationsImportFileHandlerBean#handleRecords
	 */
	public boolean handleRecords() throws RemoteException, RemoteException;

	/**
	 * @see is.idega.nest.rafverk.importer.ElectricalInstallationsImportFileHandlerBean#setImportFile
	 */
	public void setImportFile(ImportFile file) throws RemoteException,
			RemoteException;

	/**
	 * @see is.idega.nest.rafverk.importer.ElectricalInstallationsImportFileHandlerBean#setRootGroup
	 */
	public void setRootGroup(Group rootGroup) throws RemoteException,
			RemoteException;
}