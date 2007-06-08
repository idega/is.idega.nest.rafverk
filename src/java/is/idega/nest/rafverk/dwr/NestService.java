package is.idega.nest.rafverk.dwr;


import java.util.Map;
import com.idega.business.IBOService;
import java.rmi.RemoteException;

public interface NestService extends IBOService {

	/**
	 * @see is.idega.nest.rafverk.dwr.NestServiceBean#getName
	 */
	public String getName() throws RemoteException;

	/**
	 * @see is.idega.nest.rafverk.dwr.NestServiceBean#getStreetsByPostalCode
	 */
	public Map getStreetsByPostalCode(String postalCode) throws RemoteException;

	/**
	 * @see is.idega.nest.rafverk.dwr.NestServiceBean#getRealEstatesByPostalCodeStreetStreetNumber
	 */
	public Map getRealEstatesByPostalCodeStreetStreetNumber(String postalCode, String street, String streetNumber)
			throws RemoteException;
}