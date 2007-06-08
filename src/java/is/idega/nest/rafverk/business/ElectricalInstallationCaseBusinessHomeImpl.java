package is.idega.nest.rafverk.business;


import javax.ejb.CreateException;
import com.idega.business.IBOHomeImpl;

public class ElectricalInstallationCaseBusinessHomeImpl extends IBOHomeImpl implements
		ElectricalInstallationCaseBusinessHome {

	public Class getBeanInterfaceClass() {
		return ElectricalInstallationCaseBusiness.class;
	}

	public ElectricalInstallationCaseBusiness create() throws CreateException {
		return (ElectricalInstallationCaseBusiness) super.createIBO();
	}
}