package is.idega.nest.rafverk.domain;

import is.idega.nest.rafverk.bean.InitialData;
import is.idega.nest.rafverk.data.Maelir;
import java.util.ArrayList;
import java.util.List;
import com.idega.core.location.data.RealEstate;
import com.idega.user.data.Group;
import com.idega.user.data.User;

public class Rafverktaka {
	
	
	public static final String STADA_MOTTEKIN="MOTTEKIN";
	public static final String STADA_TILKYNNT_LOK="TILKYNNT_LOK";
	public static final String STADA_I_SKODUN="I_SKODUN";
	public static final String STADA_SKODUN_LOKID="SKODUN_LOKID";
	public static final String STADA_LOKID="LOKID";
	private static List statusList;
	
	public static Rafverktaka getInstanceWithCurrentUserAsRafverktaki() {
		return new Rafverktaka();
	}
	
	public static List getPossibleStatuses(){
		
		if(statusList==null){
			statusList=new ArrayList();
			statusList.add(STADA_MOTTEKIN);
			statusList.add(STADA_TILKYNNT_LOK);
			statusList.add(STADA_I_SKODUN);
			statusList.add(STADA_SKODUN_LOKID);
			statusList.add(STADA_LOKID);
		}
		return statusList;
	}
	
	ElectricalInstallation electricalInstallation = null;
	Rafverktaki rafverktaki = null;
	Fasteign fasteign = null;
	
	String id;
	Orkufyrirtaeki orkufyrirtaeki;

	Veitustadur veitustadur;
	Orkukaupandi orkukaupandi;
	String stada=STADA_MOTTEKIN;
	
    private String notkunarflokkur = null;
    
    private String spennukerfi = null;
    
    private String annad = null;
    
    private List varnarradstoefun = null;
    
	private Maelir stadurMaelir = new Maelir(InitialData.STADUR, 0);
    

	public Rafverktaka() {
	}

	public Rafverktaka(ElectricalInstallation electricalInstallation){
		initialize(electricalInstallation);
	}
	
	public void initialize(ElectricalInstallation electricalInstallation) {
		setElectricalInstallation(electricalInstallation);
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
		if (rafverktaki == null) {
			if (electricalInstallation != null) {
				User user = electricalInstallation.getElectrician();
				String electricianCompany = electricalInstallation.getElectricianCompany();
					if (user != null) {
						rafverktaki = Rafverktaki.getInstanceWithUserAsRafverktaki(user,electricianCompany);
						return rafverktaki;
					}
				}
				rafverktaki = Rafverktaki.getInstanceWithCurrentUserAsRafverktaki();
		}
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
		id = electricalInstallation.getPrimaryKey().toString();
	}
	
	public String getVeitustadurDisplay() {
		if (fasteign != null) {
			return fasteign.getDescription();
		}
		return null;
	}
	
}
