package is.idega.nest.rafverk.domain;


import com.idega.core.location.data.RealEstate;
import com.idega.user.data.Group;
import is.idega.nest.rafverk.data.RealEstateIdentifier;
import java.util.Collection;
import javax.ejb.CreateException;
import com.idega.data.IDOHome;
import javax.ejb.FinderException;
import com.idega.user.data.User;

public interface ElectricalInstallationHome extends IDOHome {

	public ElectricalInstallation create() throws CreateException;

	public ElectricalInstallation findByPrimaryKey(Object pk) throws FinderException;

	public Collection findElectricalInstallationByElectrician(User electrician) throws FinderException;

	public Collection findElectricalInstallationByEnergyCompany(Group energyCompany) throws FinderException;

	public Collection findElectricalInstallationByRealEstateNumber(String realEstateNumber) throws FinderException;

	public Collection findOtherOpenElectricalInstallationByRealEstateIdentifier(
			RealEstateIdentifier realEstateIdentifier, User currentUser) throws FinderException;

	public Collection findOtherClosedElectricalInstallationByRealEstateIdentifer(
			RealEstateIdentifier realEstateIdentifer, ElectricalInstallation currentElectricalInstallation)
			throws FinderException;

	public Collection findOtherClosedElectricalInstallationByRealEstate(RealEstate realEstate,
			ElectricalInstallation currentElectricalInstallation) throws FinderException;
}