package is.idega.nest.rafverk.business;


import javax.ejb.CreateException;
import com.idega.business.IBOHomeImpl;

public class ElectricalInstallationValidationBusinessHomeImpl extends IBOHomeImpl implements
		ElectricalInstallationValidationBusinessHome {

	public Class getBeanInterfaceClass() {
		return ElectricalInstallationValidationBusiness.class;
	}

	public ElectricalInstallationValidationBusiness create() throws CreateException {
		return (ElectricalInstallationValidationBusiness) super.createIBO();
	}
}