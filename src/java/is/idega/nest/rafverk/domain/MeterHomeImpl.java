package is.idega.nest.rafverk.domain;


import java.util.Collection;
import javax.ejb.CreateException;
import javax.ejb.FinderException;
import com.idega.data.IDOEntity;
import com.idega.data.IDOFactory;

public class MeterHomeImpl extends IDOFactory implements MeterHome {

	public Class getEntityInterfaceClass() {
		return Meter.class;
	}

	public Meter create() throws CreateException {
		return (Meter) super.createIDO();
	}

	public Meter findByPrimaryKey(Object pk) throws FinderException {
		return (Meter) super.findByPrimaryKeyIDO(pk);
	}

	public Collection findMetersByElectricalInstallation(ElectricalInstallation electricalInstallation) throws FinderException {
		IDOEntity entity = this.idoCheckOutPooledEntity();
		Collection ids = ((MeterBMPBean) entity).ejbFindMetersByElectricalInstallation(electricalInstallation);
		this.idoCheckInPooledEntity(entity);
		return this.getEntityCollectionForPrimaryKeys(ids);
	}
}