package is.idega.nest.rafverk.domain;


import com.idega.core.location.data.RealEstate;
import com.idega.user.data.Group;
import is.idega.nest.rafverk.data.RealEstateIdentifier;
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

	public Collection findElectricalInstallationByEnergyCompany(Group energyCompany) throws FinderException {
		IDOEntity entity = this.idoCheckOutPooledEntity();
		Collection ids = ((ElectricalInstallationBMPBean) entity).ejbFindElectricalInstallationByEnergyCompany(energyCompany);
		this.idoCheckInPooledEntity(entity);
		return this.getEntityCollectionForPrimaryKeys(ids);
	}

	public Collection findElectricalInstallationByRealEstateNumber(String realEstateNumber) throws FinderException {
		IDOEntity entity = this.idoCheckOutPooledEntity();
		Collection ids = ((ElectricalInstallationBMPBean) entity).ejbFindElectricalInstallationByRealEstateNumber(realEstateNumber);
		this.idoCheckInPooledEntity(entity);
		return this.getEntityCollectionForPrimaryKeys(ids);
	}

	public Collection findOtherOpenElectricalInstallationByRealEstateIdentifier(
			RealEstateIdentifier realEstateIdentifier, User currentUser) throws FinderException {
		IDOEntity entity = this.idoCheckOutPooledEntity();
		Collection ids = ((ElectricalInstallationBMPBean) entity).ejbFindOtherOpenElectricalInstallationByRealEstateIdentifier(
				realEstateIdentifier, currentUser);
		this.idoCheckInPooledEntity(entity);
		return this.getEntityCollectionForPrimaryKeys(ids);
	}

	public Collection findOtherClosedElectricalInstallationByRealEstateIdentifer(
			RealEstateIdentifier realEstateIdentifer, ElectricalInstallation currentElectricalInstallation)
			throws FinderException {
		IDOEntity entity = this.idoCheckOutPooledEntity();
		Collection ids = ((ElectricalInstallationBMPBean) entity).ejbFindOtherClosedElectricalInstallationByRealEstateIdentifer(
				realEstateIdentifer, currentElectricalInstallation);
		this.idoCheckInPooledEntity(entity);
		return this.getEntityCollectionForPrimaryKeys(ids);
	}

	public Collection findOtherClosedElectricalInstallationByRealEstate(RealEstate realEstate,
			ElectricalInstallation currentElectricalInstallation) throws FinderException {
		IDOEntity entity = this.idoCheckOutPooledEntity();
		Collection ids = ((ElectricalInstallationBMPBean) entity).ejbFindOtherClosedElectricalInstallationByRealEstate(
				realEstate, currentElectricalInstallation);
		this.idoCheckInPooledEntity(entity);
		return this.getEntityCollectionForPrimaryKeys(ids);
	}
}