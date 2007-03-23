package is.idega.nest.rafverk.domain;


import java.util.Collection;
import javax.ejb.CreateException;
import javax.ejb.FinderException;
import com.idega.user.data.User;
import com.idega.data.IDOEntity;
import com.idega.data.IDOFactory;

public class ElectricalInstallationHomeImpl extends IDOFactory implements ElectricalInstallationHome {

	public Class getEntityInterfaceClass() {
		return ElectricalInstallation.class;
	}

	public ElectricalInstallation create() throws CreateException {
		return (ElectricalInstallation) super.createIDO();
	}

	public ElectricalInstallation findByPrimaryKey(Object pk) throws FinderException {
		return (ElectricalInstallation) super.findByPrimaryKeyIDO(pk);
	}

	public Collection findElectricalInstallationByElectrician(User electrician) throws FinderException {
		IDOEntity entity = this.idoCheckOutPooledEntity();
		Collection ids = ((ElectricalInstallationBMPBean) entity).ejbFindElectricalInstallationByElectrician(electrician);
		this.idoCheckInPooledEntity(entity);
		return this.getEntityCollectionForPrimaryKeys(ids);
	}
}