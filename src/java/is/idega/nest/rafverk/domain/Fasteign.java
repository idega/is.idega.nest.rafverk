package is.idega.nest.rafverk.domain;

import com.idega.core.location.data.RealEstate;
import com.idega.core.location.data.Street;
import com.idega.util.StringHandler;
import fasteignaskra.landskra_wse.FasteignaskraFasteign;
import fasteignaskra.landskra_wse.FasteignaskraFasteignEigandi;
import fasteignaskra.landskra_wse.Fasteignaskra_Element;
import is.postur.Gata;
import is.postur.Gotuskra;

/**
 * RealEstate
 * 
 * @author Tryggvi
 *
 */
public class Fasteign extends BaseBean{
	
	String fasteignId;
	String nafn;
	String fastaNumer;
	String merking;// 100101 Bygging Hæð Nr. innan hæðar
	String notkun; //Íbúð, Skrifstofa
	String skyring;
	String landnumer;
	
	Gata gata;
	String gotuNumer;
	FasteignaEigandi eigandi;
	
	public Fasteign(){}
	
	public Fasteign(RealEstate realEstate) {
		setFastaNumer(realEstate.getRealEstateNumber());
		setMerking(realEstate.getRealEstateCode());
		setNafn(realEstate.getName());
		setNotkun(realEstate.getUse());
		setSkyring(realEstate.getComment());
		setLandnumer(realEstate.getLandRegisterMapNumber());
		setGotuNumer(realEstate.getStreetNumber());
		Street street = realEstate.getStreet();
		if (street != null) {
			Gata gataTemp = new Gata(street);
			setGata(gataTemp);
		}
	}
	
	public Fasteign(Fasteignaskra_Element dFasteign,String postnumer) {
		
		FasteignaskraFasteign eFasteign = dFasteign.getFasteign();

		setFastaNumer(eFasteign.getFastanr().toString());
		setMerking(eFasteign.getMerking());
		setNotkun(eFasteign.getNotkun());
		if(postnumer!=null){
			setGata(Gotuskra.getCached().getGataByNafnAndPostnumer(eFasteign.getGotuheiti(), postnumer));
		}
		setGotuNumer(eFasteign.getHusnumer());
		setSkyring(eFasteign.getSkyring());
		
		setLandnumer(eFasteign.getLandnr().toString());
		
		FasteignaskraFasteignEigandi[] eigendur = eFasteign.getEigandi();
		if(eigendur!=null&&eigendur.length>=1){
			FasteignaskraFasteignEigandi firstowner = eigendur[0];
			setEigandi(new FasteignaEigandi(firstowner.getKennitala(),firstowner.getNafn()));
		}
				
	}
	public String getFastaNumer() {
		return fastaNumer;
	}
	public void setFastaNumer(String fastaNumer) {
		this.fastaNumer = fastaNumer;
	}
	public String getFasteignId() {
		return fasteignId;
	}
	public void setFasteignId(String fasteignId) {
		this.fasteignId = fasteignId;
	}
	public Gata getGata() {
		return gata;
	}
	public void setGata(Gata gata) {
		this.gata = gata;
	}
	public String getMerking() {
		return merking;
	}
	public void setMerking(String merking) {
		this.merking = merking;
	}
	public String getNafn() {
		if(nafn!=null){
			return nafn;
		}
		else{
			Gata gata = getGata();
			if(gata!=null){
				String gotuheiti = gata.getNafn();
				gotuheiti=gotuheiti+" "+getGotuNumer();
				return gotuheiti;
			}
		}
		return null;
	}
	public void setNafn(String nafn) {
		this.nafn = nafn;
	}
	public String getNotkun() {
		return notkun;
	}
	public void setNotkun(String notkun) {
		this.notkun = notkun;
	}

	public FasteignaEigandi getEigandi() {
		return eigandi;
	}
	public void setEigandi(FasteignaEigandi eigandi) {
		this.eigandi = eigandi;
	}
	
	public String getMerkingHumanReadable(){
		String merking = getMerking();
		String notkun = getNotkun();
		String skyring = getSkyring();
		if (merking == null || notkun == null || skyring == null) {
			return StringHandler.EMPTY_STRING;
		}
		String sBygging = merking.substring(0,2);
		Integer iBygging = new Integer(sBygging);
		String sHaed = merking.substring(2,4);
		Integer iHaed = new Integer(sHaed);
		String sNr = merking.substring(4,6);
		Integer iNr = new Integer(sNr);
		

		
		if(notkun.endsWith("hæð")){

			notkun=notkun+" "+iHaed+", "+skyring+" "+iNr;
		}
		else{
			
			String haedHuman = null;
			if(iHaed.intValue()==0){
				haedHuman = "jarðhæð";
			}
			else{
				haedHuman = iHaed+". hæð";
			}
			
			notkun=notkun+", "+haedHuman+", nr. "+iNr;
		}
		/*String haedHuman = null;
		if(iHaed.intValue()==0){
			haedHuman = "jarðhæð";
		}
		else{
			haedHuman = iHaed+". hæð";
		}*/
		
		String str="";
		if(iBygging.intValue()==1){
			str = notkun;
		}
		else{
			str = notkun+", bygging "+iBygging;
		}
		
		return str;
		
	}
	
	public String getDescription(){
		StringBuffer buffer = new StringBuffer();
		// nafn
		add(buffer, getNafn());
		buffer.append(" - ");
		// merking human readable
		add(buffer, getMerkingHumanReadable());
		// eigandi
		FasteignaEigandi eigandi = getEigandi();
		if(StringHandler.isNotEmpty(eigandi)){
			buffer.append(" (");
			buffer.append(eigandi.getNafn());
			buffer.append(")");
		}
		// fastanumer
		buffer.append(" [Fastanúmer ");
		add(buffer, getFastaNumer());
		buffer.append(" ");
		// merking
		add(buffer, getMerking());
		buffer.append("]");
		// landnumer
		buffer.append(" [Landnúmer ");
		add(buffer,getLandnumer());
		buffer.append("] ");
		return buffer.toString();
	}
	
	private void add(StringBuffer buffer, String value) {
		buffer.append(StringHandler.getStringOrDash(value));
	}
	
	public String getGotuNumer() {
		return gotuNumer;
	}
	public void setGotuNumer(String gotuNumer) {
		this.gotuNumer = gotuNumer;
	}

	public String getSkyring() {
		return skyring;
	}

	public void setSkyring(String skyring) {
		this.skyring = skyring;
	}

	
	public String getLandnumer() {
		return landnumer;
	}

	
	public void setLandnumer(String landnumer) {
		this.landnumer = landnumer;
	}
	

}
