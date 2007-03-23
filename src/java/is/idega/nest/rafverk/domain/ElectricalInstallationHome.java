package is.idega.nest.rafverk.domain;


import java.util.Collection;
import javax.ejb.CreateException;
import com.idega.data.IDOHome;
import javax.ejb.FinderException;
import com.idega.user.data.User;

public interface ElectricalInstallationHome extends IDOHome {

	public ElectricalInstallation create() throws CreateException;

	public ElectricalInstallation findByPrimaryKey(Object pk) throws FinderException;

	public Collection findElectricalInstallationByElectrician(User electrician) throws FinderException;
}