package is.idega.nest.rafverk.importer;


import javax.ejb.CreateException;
import com.idega.business.IBOHomeImpl;

public class ElectricalInstallationsImportFileHandlerHomeImpl extends
		IBOHomeImpl implements ElectricalInstallationsImportFileHandlerHome {
	public Class getBeanInterfaceClass() {
		return ElectricalInstallationsImportFileHandler.class;
	}

	public ElectricalInstallationsImportFileHandler create()
			throws CreateException {
		return (ElectricalInstallationsImportFileHandler) super.createIBO();
	}
}