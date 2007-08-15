package is.idega.nest.rafverk.dwr;


import java.util.Map;
import com.idega.business.IBOService;
import java.util.List;
import java.rmi.RemoteException;

public interface NestService extends IBOService {

	/**
	 * @see is.idega.nest.rafverk.dwr.NestServiceBean#getStreetsByPostalCodeForChangeElectricianBean
	 */
	public Map getStreetsByPostalCodeForChangeElectricianBean(String postalCode) throws RemoteException;

	/**
	 * @see is.idega.nest.rafverk.dwr.NestServiceBean#getStreetsByPostalCode
	 */
	public Map getStreetsByPostalCode(String postalCode) throws RemoteException;

	/**
	 * @see is.idega.nest.rafverk.dwr.NestServiceBean#getRealEstatesByPostalCodeStreetStreetNumberForChangeElectricianBean
	 */
	public Map getRealEstatesByPostalCodeStreetStreetNumberForChangeElectricianBean(String postalCode, String street,
			String streetNumber) throws RemoteException;

	/**
	 * @see is.idega.nest.rafverk.dwr.NestServiceBean#getRealEstatesByPostalCodeStreetStreetNumber
	 */
	public Map getRealEstatesByPostalCodeStreetStreetNumber(String postalCode, String street, String streetNumber)
			throws RemoteException;

	/**
	 * @see is.idega.nest.rafverk.dwr.NestServiceBean#getElectricInstallationList
	 */
	public List getElectricInstallationList(String realEstateNumber) throws RemoteException;

	/**
	 * @see is.idega.nest.rafverk.dwr.NestServiceBean#getEnergyConsumerFields
	 */
	public List getEnergyConsumerFields(String realEstateNumber) throws RemoteException;
}