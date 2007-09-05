package is.idega.nest.rafverk.business;


import com.idega.util.datastructures.list.KeyValuePair;
import is.idega.nest.rafverk.bean.TilkynningVertakaBean;
import com.idega.user.data.User;
import is.idega.nest.rafverk.domain.ElectricalInstallation;
import java.rmi.RemoteException;
import is.idega.nest.rafverk.cache.ElectricalInstallationCache;
import java.io.IOException;
import java.util.Collection;
import is.idega.nest.rafverk.bean.TilkynningLokVerksBean;
import javax.ejb.FinderException;
import com.idega.business.IBOService;
import is.idega.nest.rafverk.domain.Rafverktaka;
import is.idega.nest.rafverk.data.MaelirList;

public interface ElectricalInstallationBusiness extends IBOService {

	/**
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationBusinessBean#addChangeForUser
	 */
	public void addChangeForUser(User user) throws RemoteException;

	/**
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationBusinessBean#changesForUser
	 */
	public boolean changesForUser(User user) throws RemoteException;

	/**
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationBusinessBean#getChildElectricalInstallationOrNull
	 */
	public ElectricalInstallation getChildElectricalInstallationOrNull(ElectricalInstallation electricalInstallation)
			throws RemoteException;

	/**
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationBusinessBean#sendApplication
	 */
	public boolean sendApplication(Rafverktaka rafverktaka) throws RemoteException;

	/**
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationBusinessBean#sendApplicationReport
	 */
	public boolean sendApplicationReport(Rafverktaka rafverktaka) throws RemoteException;

	/**
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationBusinessBean#storeApplication
	 */
	public boolean storeApplication(Rafverktaka rafverktaka, TilkynningVertakaBean tilkynningVertakaBean,
			TilkynningLokVerksBean tilkynningLokVerksBean) throws RemoteException;

	/**
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationBusinessBean#storeApplicationReport
	 */
	public boolean storeApplicationReport(Rafverktaka rafverktaka, TilkynningVertakaBean tilkynningVertakaBean,
			TilkynningLokVerksBean tilkynningLokVerksBean) throws RemoteException;

	/**
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationBusinessBean#initializeManagedBeans
	 */
	public void initializeManagedBeans(Rafverktaka rafverktaka, TilkynningVertakaBean tilkynningVertakaBean,
			TilkynningLokVerksBean tilkynningLokVerksBean) throws RemoteException;

	/**
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationBusinessBean#getPDFApplication
	 */
	public KeyValuePair getPDFApplication(ElectricalInstallation electricalInstallation) throws IOException,
			RemoteException;

	/**
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationBusinessBean#getPDFApplicationAndSendEmails
	 */
	public KeyValuePair getPDFApplicationAndSendEmails(ElectricalInstallation electricalInstallation)
			throws IOException, RemoteException;

	/**
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationBusinessBean#getMaelirList
	 */
	public MaelirList getMaelirList(ElectricalInstallation electricalInstallation) throws RemoteException;

	/**
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationBusinessBean#getElectricalInstallationByPrimaryKey
	 */
	public ElectricalInstallation getElectricalInstallationByPrimaryKey(Object primaryKey) throws FinderException,
			RemoteException;

	/**
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationBusinessBean#getElectricalInstallationByElectrician
	 */
	public Collection getElectricalInstallationByElectrician(User electrician) throws FinderException, RemoteException;

	/**
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationBusinessBean#getElectricalInstallationByRealEstateNumber
	 */
	public Collection getElectricalInstallationByRealEstateNumber(String realEstateNumber) throws FinderException,
			RemoteException;

	/**
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationBusinessBean#getOtherElectricalInstallationByRealEstateNumber
	 */
	public Collection getOtherElectricalInstallationByRealEstateNumber(String realEstateNumber, User user)
			throws FinderException, RemoteException;

	/**
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationBusinessBean#getElectricalInstallationByEnergyCompanyUser
	 */
	public Collection getElectricalInstallationByEnergyCompanyUser(User energyCompanyUser) throws RemoteException;

	/**
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationBusinessBean#getElectricalInstallationCaseBusiness
	 */
	public ElectricalInstallationCaseBusiness getElectricalInstallationCaseBusiness() throws RemoteException;

	/**
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationBusinessBean#getElectricalInstallationState
	 */
	public ElectricalInstallationState getElectricalInstallationState() throws RemoteException;

	/**
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationBusinessBean#getElectricalInstallationCache
	 */
	public ElectricalInstallationCache getElectricalInstallationCache() throws RemoteException;
}