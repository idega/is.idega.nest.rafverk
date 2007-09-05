package is.idega.nest.rafverk.domain;

import is.idega.nest.rafverk.bean.BaseBean;
import is.postur.Gata;
import is.postur.Postnumer;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.context.FacesContext;

import com.idega.business.IBOLookup;
import com.idega.business.IBOLookupException;
import com.idega.business.IBORuntimeException;
import com.idega.core.contact.data.Phone;
import com.idega.core.location.data.Address;
import com.idega.core.location.data.PostalCode;
import com.idega.idegaweb.IWApplicationContext;
import com.idega.presentation.IWContext;
import com.idega.user.business.NoPhoneFoundException;
import com.idega.user.business.UserBusiness;
import com.idega.user.data.User;

public class Rafverktaki extends BaseBean{
	
	public static Rafverktaki getInstanceWithCurrentUserAsRafverktaki() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		IWContext iwc = IWContext.getIWContext(facesContext);
		return getInstanceWithCurrentUserAsRafverktaki(iwc);

	}
	
	public static Rafverktaki getInstanceWithCurrentUserAsRafverktaki(IWContext iwc) {
		User user = iwc.getCurrentUser();
		return getInstanceWithUserAsRafverktaki(user, EMPTY_STRING, iwc);
	}
	
	public static Rafverktaki getInstanceWithUserAsRafverktaki(User user) {
		return getInstanceWithUserAsRafverktaki(user, EMPTY_STRING);
	}
	
	public static Rafverktaki getInstanceWithUserAsRafverktaki(User user, String electricianCompany) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		IWContext iwc = IWContext.getIWContext(facesContext);
		IWApplicationContext iwac = iwc.getApplicationContext(); 
		return getInstanceWithUserAsRafverktaki(user, electricianCompany, iwac);
	}
	
	public static Rafverktaki getInstanceWithUserAsRafverktaki(User user, String electricicanCompany, IWApplicationContext iwac) {
		return new Rafverktaki(user, electricicanCompany, iwac);
	}

	public static final String EMPTY_STRING = "";
	
	public static String STADA_VIRKUR="VIRKUR";
	public static String STADA_OVIRKUR="OVIRKUR";
	
	String id;
	User electrician;
	String nafn;
	String nafnFyrirtaekis;
	String kennitala;
	Heimilisfang heimilisfang;
	String heimasimi;
	String vinnusimi;
	
	String tegund;
	String stada=STADA_VIRKUR;
	
	public Rafverktaki() {
		// should not be used still there because of prototype
	}
	
	private Rafverktaki(User user, String electricianCompany, IWApplicationContext iwac) {
		initialize(user, electricianCompany, iwac);
	}
	
	private void initialize(User user, String electricianCompany, IWApplicationContext iwac) {
		electrician = user;
		nafn = user.getDisplayName();
		kennitala = user.getPersonalID();
		UserBusiness userBusiness = getUserBusiness(iwac);
		initializeHeimilisfang(user, userBusiness);
		initializeSimi(user, userBusiness);
		
		setNafnFyrirtaekis(electricianCompany);
		
	}

	private void initializeHeimilisfang(User user, UserBusiness userBusiness) {
		try {
			// co address is actually work address
			Address address = userBusiness.getUsersCoAddress(user);
			if (address == null) {
				return;
			}
			String streetName = address.getStreetNameOriginal();
			String streetNumber = address.getStreetNumber();
			Gata gata = new Gata();
			gata.setNafn(streetName);
			PostalCode postalCode = address.getPostalCode();
			if (postalCode != null) {
				String zipCode = postalCode.getPostalCode();
				String zipName = postalCode.getName();
				Postnumer postnumer = new Postnumer();
				postnumer.setNumer(zipCode);
				postnumer.setName(zipName);
				gata.setPostnumer(postnumer);
			}
			heimilisfang = new Heimilisfang();
			heimilisfang.setHusnumer(streetNumber);
			heimilisfang.setGata(gata);
		}
		catch (RemoteException e) {
			throw new IBORuntimeException(e);
		}
	}
	
	private void initializeSimi(User user, UserBusiness userBusiness) {
		try {
			Phone phone = userBusiness.getUsersHomePhone(user);
			heimasimi = (phone == null) ? EMPTY_STRING : phone.getNumber();
		}
		catch (RemoteException e) {
			throw new IBORuntimeException(e);
		}
		catch (NoPhoneFoundException e) {
			heimasimi = EMPTY_STRING;
		}
		try {
			Phone phone = userBusiness.getUsersWorkPhone(user);
			vinnusimi = (phone == null) ? EMPTY_STRING : phone.getNumber();
		}
		catch (RemoteException e) {
			throw new IBORuntimeException(e);
		}
		catch (NoPhoneFoundException e) {
			vinnusimi = EMPTY_STRING;
		}
	}
	
	public String getHeimasimi() {
		return heimasimi;
	}
	public void setHeimasimi(String heimasimi) {
		this.heimasimi = heimasimi;
	}
	public Heimilisfang getHeimilisfang() {
		return heimilisfang;
	}
	public void setHeimilisfang(Heimilisfang heimilisfang) {
		this.heimilisfang = heimilisfang;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getKennitala() {
		return kennitala;
	}
	public void setKennitala(String kennitala) {
		this.kennitala = kennitala;
	}
	public String getNafnFyrirtaekis() {
		return nafnFyrirtaekis;
	}
	public void setNafnFyrirtaekis(String nafnFyrirtaekis) {
		this.nafnFyrirtaekis = nafnFyrirtaekis;
	}
	public String getStada() {
		return stada;
	}
	public void setStada(String stada) {
		this.stada = stada;
	}
	public String getTegund() {
		return tegund;
	}
	public void setTegund(String tegund) {
		this.tegund = tegund;
	}
	public String getVinnusimi() {
		return vinnusimi;
	}
	public void setVinnusimi(String vinnusimi) {
		this.vinnusimi = vinnusimi;
	}
	public String getNafn() {
		return nafn;
	}
	public void setNafn(String nafn) {
		this.nafn = nafn;
	}
	
	public List getRafverktokurWithStatus(String status){
		List verktokur = getRafverktokur();
		List list = new ArrayList();
		for (Iterator iter = verktokur.iterator(); iter.hasNext();) {
			Rafverktaka verktaka = (Rafverktaka) iter.next();
			if(verktaka.getStada().equals(status)){
				list.add(verktaka);
			}
		}
		return list;
	}
	
	public List getRafverktokur(){
		List all = BaseBean.getInitialData().getAllRafverktokurListi();
		List myRafverktokur = new ArrayList();
		for (Iterator iter = all.iterator(); iter.hasNext();) {
			Rafverktaka verktaka = (Rafverktaka) iter.next();
			if(verktaka.getRafverktaki().getId().equals(this.getId())){
				myRafverktokur.add(verktaka);
			}
		}
		return myRafverktokur;
	}
	
	
	private UserBusiness getUserBusiness(IWApplicationContext iwac) {	
		try {
			return (UserBusiness) IBOLookup.getServiceInstance(iwac,UserBusiness.class);
		}
		catch (IBOLookupException e) {
			throw new IBORuntimeException(e);
		}
	}

	
	public User getElectrician() {
		return electrician;
	}

	
	public void setElectrician(User electrician) {
		this.electrician = electrician;
	}
	
}
