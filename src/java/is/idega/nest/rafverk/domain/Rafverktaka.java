package is.idega.nest.rafverk.domain;

import is.idega.nest.rafverk.bean.InitialData;
import is.idega.nest.rafverk.business.ElectricalInstallationBusiness;
import is.idega.nest.rafverk.business.ElectricalInstallationState;
import is.idega.nest.rafverk.data.Maelir;

import java.rmi.RemoteException;
import java.util.List;

import com.idega.business.IBORuntimeException;
import com.idega.core.location.data.RealEstate;
import com.idega.user.data.Group;
import com.idega.user.data.User;

public class Rafverktaka {
	

	

	
	public static Rafverktaka getInstanceWithCurrentUserAsRafverktaki() {
		return new Rafverktaka();
	}
	
	public static Rafverktaka getInstanceFromElectricalInstallation(ElectricalInstallation electricalInstallation, ElectricalInstallationBusiness electricalInstallationBusiness) {
		return new Rafverktaka(electricalInstallation, electricalInstallationBusiness);
	}
	
	ElectricalInstallation electricalInstallation;
	
	ElectricalInstallation childElectricalInstallation;
	
	Rafverktaki newOwner;
	
	Rafverktaki rafverktaki;
	Fasteign fasteign;
	
	String id;
	String externalProjectID;
	Orkufyrirtaeki orkufyrirtaeki;

	Veitustadur veitustadur;
	
	Orkukaupandi orkukaupandi;
	
	String stada;
	
	String stadaDisplay;
	
    private String notkunarflokkur;
    
    private String spennukerfi;
    
    private String annad;
    
    private List varnarradstoefun;
    
	private Maelir stadurMaelir = new Maelir(InitialData.STADUR, 0);
    

	public Rafverktaka() {
		initialize();
	}

	public Rafverktaka(ElectricalInstallation electricalInstallation, ElectricalInstallationBusiness electricalInstallationBusiness){
		initialize(electricalInstallation, electricalInstallationBusiness);
	}
	
	private void initialize() {
		rafverktaki = Rafverktaki.getInstanceWithCurrentUserAsRafverktaki();
	}
 	
	public void initialize(ElectricalInstallation electricalInstallation, ElectricalInstallationBusiness electricalInstallationBusiness) {

		// set rafverktaki
		setElectricalInstallation(electricalInstallation);
		User user = electricalInstallation.getElectrician();
		String electricianCompany = electricalInstallation.getElectricianCompany();
		rafverktaki = Rafverktaki.getInstanceWithUserAsRafverktaki(user,electricianCompany);
		
		// is there a child installation? (was the electrical installation taken over by another electrician)
		try {
			childElectricalInstallation = electricalInstallationBusiness.getChildElectricalInstallationOrNull(electricalInstallation);
			if (childElectricalInstallation != null) {
				String electricianCompanyNewOwner = childElectricalInstallation.getElectricianCompany();
				User electrician = childElectricalInstallation.getElectrician();
				newOwner = Rafverktaki.getInstanceWithUserAsRafverktaki(electrician,electricianCompanyNewOwner);
			}
		}
		catch (RemoteException e1) {
			throw new RuntimeException(e1);
		}
		
		externalProjectID = electricalInstallation.getExternalProjectID();
		id = electricalInstallation.getPrimaryKey().toString();
		stada = electricalInstallation.getStatus();
		ElectricalInstallationState electricalInstallationState;
		try {
			electricalInstallationState = electricalInstallationBusiness.getElectricalInstallationState();
		}
		catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new IBORuntimeException();
		}
		stadaDisplay = electricalInstallationState.getStatusDescription(electricalInstallation);
		Group energyCompany = electricalInstallation.getEnergyCompany();
		setOrkufyrirtaeki(new Orkufyrirtaeki(energyCompany));
		setOrkukaupandi(new Orkukaupandi(electricalInstallation));
		RealEstate realEstate = electricalInstallation.getRealEstate();
		if (realEstate != null) {
			fasteign = new Fasteign(realEstate);
		}
		
	}


	public Orkufyrirtaeki getOrkufyrirtaeki() {
		return orkufyrirtaeki;
	}
	public void setOrkufyrirtaeki(Orkufyrirtaeki orkufyrirtaeki) {
		this.orkufyrirtaeki = orkufyrirtaeki;
	}
	public Orkukaupandi getOrkukaupandi() {
		if (orkukaupandi == null) {
			orkukaupandi = new Orkukaupandi();
		}
		return orkukaupandi;
	}
	public void setOrkukaupandi(Orkukaupandi orkukaupandi) {
		this.orkukaupandi = orkukaupandi;
	}
	
	/**
	 * if there is no electrical installation set use current user else
	 * use user from electrical installation
	 * 
	 * @return
	 */
	public Rafverktaki getRafverktaki() {
		return rafverktaki;
	}

	public void setRafverktaki(Rafverktaki rafverktaki) {
		this.rafverktaki = rafverktaki;
	}
	public Veitustadur getVeitustadur() {
		return veitustadur;
	}
	public void setVeitustadur(Veitustadur veitustadur) {
		this.veitustadur = veitustadur;
	}
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getExternalProjectID() {
		return externalProjectID;
	}
	
	public void setExternalProjectID(String externalProjectID) {
		this.externalProjectID = externalProjectID;
	}

	public String getStadaDisplay() {
		return stadaDisplay;
	}
	
	public String getStada() {
		return stada;
	}


	public void setStada(String stada) {
		this.stada = stada;
	}


	
	public String getAnnad() {
		return annad;
	}


	
	public void setAnnad(String annad) {
		this.annad = annad;
	}


	
	public String getNotkunarflokkur() {
		return notkunarflokkur;
	}


	
	public void setNotkunarflokkur(String notkunarflokkur) {
		this.notkunarflokkur = notkunarflokkur;
	}


	
	public String getSpennukerfi() {
		return spennukerfi;
	}


	
	public void setSpennukerfi(String spennukerfi) {
		this.spennukerfi = spennukerfi;
	}


	
	public List getVarnarradstoefun() {
		return varnarradstoefun;
	}


	
	public void setVarnarradstoefun(List varnarradstoefun) {
		this.varnarradstoefun = varnarradstoefun;
	}


	
	public Maelir getStadurMaelir() {
		return stadurMaelir;
	}


	
	public void setStadurMaelir(Maelir stadurMaelir) {
		this.stadurMaelir = stadurMaelir;
	}

	
	public ElectricalInstallation getElectricalInstallation() {
		return electricalInstallation;
	}

	
	public void setElectricalInstallation(ElectricalInstallation electricalInstallation) {
		this.electricalInstallation = electricalInstallation;
	}
	
	public Rafverktaki getNewOwner() {
		return newOwner;
	}
	
	public String getVeitustadurDisplay() {
		if (fasteign != null) {
			return fasteign.getDescription();
		}
		return null;
	}
		
}
