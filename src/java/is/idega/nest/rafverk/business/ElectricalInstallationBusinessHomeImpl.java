package is.idega.nest.rafverk.business;


import javax.ejb.CreateException;
import com.idega.business.IBOHomeImpl;

public class ElectricalInstallationBusinessHomeImpl extends IBOHomeImpl implements ElectricalInstallationBusinessHome {

	public Class getBeanInterfaceClass() {
		return ElectricalInstallationBusiness.class;
	}

	public ElectricalInstallationBusiness create() throws CreateException {
		return (ElectricalInstallationBusiness) super.createIBO();
	}
}