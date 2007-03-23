package is.idega.nest.rafverk.business;


import java.util.Collection;
import is.idega.nest.rafverk.domain.ElectricalInstallationHome;
import is.idega.nest.rafverk.bean.TilkynningLokVerksBean;
import is.idega.nest.rafverk.bean.TilkynningVertakaBean;
import javax.ejb.FinderException;
import com.idega.business.IBOService;
import is.idega.nest.rafverk.domain.Rafverktaka;
import com.idega.user.data.User;
import is.idega.nest.rafverk.domain.MeterHome;
import java.rmi.RemoteException;

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
}