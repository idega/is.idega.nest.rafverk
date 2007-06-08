package is.idega.nest.rafverk.dwr;


import javax.ejb.CreateException;
import com.idega.business.IBOHomeImpl;

public class NestServiceHomeImpl extends IBOHomeImpl implements NestServiceHome {

	public Class getBeanInterfaceClass() {
		return NestService.class;
	}

	public NestService create() throws CreateException {
		return (NestService) super.createIBO();
	}
}