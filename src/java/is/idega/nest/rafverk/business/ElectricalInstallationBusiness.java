package is.idega.nest.rafverk.business;


import is.idega.nest.rafverk.domain.ElectricalInstallationHome;
import is.idega.nest.rafverk.bean.TilkynningVertakaBean;
import com.idega.user.data.User;
import is.idega.nest.rafverk.domain.ElectricalInstallation;
import java.rmi.RemoteException;
import com.idega.core.location.data.PostalCodeHome;
import java.util.Collection;
import is.idega.nest.rafverk.bean.TilkynningLokVerksBean;
import javax.ejb.FinderException;
import com.idega.business.IBOService;
import com.idega.user.business.UserBusiness;
import is.idega.nest.rafverk.domain.Rafverktaka;
import is.idega.nest.rafverk.data.MaelirList;
import com.idega.core.location.data.RealEstateHome;
import is.idega.nest.rafverk.domain.MeterHome;
import com.idega.core.location.data.StreetHome;

public interface ElectricalInstallationBusiness extends IBOService {

	/**
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationBusinessBean#storeManagedBeans
	 */
	public boolean storeManagedBeans(Rafverktaka rafverktaka, TilkynningVertakaBean tilkynningVertakaBean, TilkynningLokVerksBean tilkynningLokVerksBean) throws RemoteException;

	/**
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationBusinessBean#initializeManagedBeans
	 */
	public void initializeManagedBeans(Rafverktaka rafverktaka, TilkynningVertakaBean tilkynningVertakaBean, TilkynningLokVerksBean tilkynningLokVerksBean) throws RemoteException;

	/**
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationBusinessBean#getMaelirList
	 */
	public MaelirList getMaelirList(ElectricalInstallation electricalInstallation) throws RemoteException;

	/**
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationBusinessBean#getElectricalInstallationByElectrician
	 */
	public Collection getElectricalInstallationByElectrician(User electrician) throws FinderException, RemoteException;

	/**
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationBusinessBean#getElectricalInstallationHome
	 */
	public ElectricalInstallationHome getElectricalInstallationHome() throws RemoteException;

	/**
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationBusinessBean#getMeterHome
	 */
	public MeterHome getMeterHome() throws RemoteException;

	/**
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationBusinessBean#getRealEstateHome
	 */
	public RealEstateHome getRealEstateHome() throws RemoteException;

	/**
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationBusinessBean#getStreetHome
	 */
	public StreetHome getStreetHome() throws RemoteException;

	/**
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationBusinessBean#getPostalCodeHome
	 */
	public PostalCodeHome getPostalCodeHome() throws RemoteException;

	/**
	 * @see is.idega.nest.rafverk.business.ElectricalInstallationBusinessBean#getUserBusiness
	 */
	public UserBusiness getUserBusiness() throws RemoteException;
}