package is.idega.nest.rafverk.business;


import javax.ejb.CreateException;
import com.idega.business.IBOHomeImpl;

public class ElectricalInstallationRendererBusinessHomeImpl extends IBOHomeImpl implements ElectricalInstallationRendererBusinessHome {

	public Class getBeanInterfaceClass() {
		return ElectricalInstallationRendererBusiness.class;
	}

	public ElectricalInstallationRendererBusiness create() throws CreateException {
		return (ElectricalInstallationRendererBusiness) super.createIBO();
	}
}