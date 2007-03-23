package is.idega.nest.rafverk.domain;


import java.util.Collection;
import javax.ejb.CreateException;
import com.idega.data.IDOHome;
import javax.ejb.FinderException;

public interface MeterHome extends IDOHome {

	public Meter create() throws CreateException;

	public Meter findByPrimaryKey(Object pk) throws FinderException;

	public Collection findMetersByElectricalInstallation(ElectricalInstallation electricalInstallation) throws FinderException;
}