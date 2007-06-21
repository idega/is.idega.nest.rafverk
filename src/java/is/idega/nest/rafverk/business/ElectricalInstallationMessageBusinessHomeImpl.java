package is.idega.nest.rafverk.business;


import javax.ejb.CreateException;
import com.idega.business.IBOHomeImpl;

public class ElectricalInstallationMessageBusinessHomeImpl extends IBOHomeImpl implements
		ElectricalInstallationMessageBusinessHome {

	public Class getBeanInterfaceClass() {
		return ElectricalInstallationMessageBusiness.class;
	}

	public ElectricalInstallationMessageBusiness create() throws CreateException {
		return (ElectricalInstallationMessageBusiness) super.createIBO();
	}
}