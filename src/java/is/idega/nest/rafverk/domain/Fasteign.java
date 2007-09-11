package is.idega.nest.rafverk.domain;

import fasteignaskra.landskra_wse.FasteignaskraFasteign;
import fasteignaskra.landskra_wse.FasteignaskraFasteignEigandi;
import fasteignaskra.landskra_wse.FasteignaskraFasteignMatseining;
import fasteignaskra.landskra_wse.Fasteignaskra_Element;
import is.postur.Gata;
import is.postur.Gotuskra;

import java.util.ArrayList;
import java.util.List;

import com.idega.core.location.data.RealEstate;
import com.idega.core.location.data.Street;
import com.idega.util.StringHandler;

/**
 * RealEstate
 * 
 * @author Tryggvi
 *
 */
public class Fasteign extends BaseBean{
	
	public static List createFasteignFrom(Fasteignaskra_Element dFasteign,String postnumer) {
		FasteignaskraFasteign fasteignaskraFasteign = dFasteign.getFasteign();
		
		// figure out street
		Gata gata = null;
		if(postnumer!=null){
			gata = Gotuskra.getCached().getGataByNafnAndPostnumer(fasteignaskraFasteign.getGotuheiti(), postnumer);
		}
		// figure out owner
		FasteignaEigandi fasteignaEigandi = null;
		FasteignaskraFasteignEigandi[] eigendur = fasteignaskraFasteign.getEigandi();
		if(eigendur!=null && eigendur.length>=1){
			FasteignaskraFasteignEigandi firstowner = eigendur[0];
			fasteignaEigandi = new FasteignaEigandi(firstowner.getKennitala(),firstowner.getNafn());
		}
		// figure out street number
		String gotuNumer = fasteignaskraFasteign.getHusnumer();
		FasteignaskraFasteignMatseining[] fasteignaskraFasteignMatseinings = fasteignaskraFasteign.getMatseining();
		
		// return list
		List list = new ArrayList(fasteignaskraFasteignMatseinings.length);
		for (int i = 0; i < fasteignaskraFasteignMatseinings.length; i++) {
			FasteignaskraFasteignMatseining fasteignaskraFasteignMatseining = fasteignaskraFasteignMatseinings[i];
			Fasteign fasteign = new Fasteign(fasteignaskraFasteignMatseining, fasteignaEigandi, gata, gotuNumer);
			list.add(fasteign);
		}
		return list;
	}
	
	String fasteignId;
	String nafn;
	String fastaNumer;
	String matseiningNumer;
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
		setMatseiningNumer(realEstate.getRealEstateUnit());
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
	
	public Fasteign(Fasteignaskra_Element dFasteign, String postnumer) {
		
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
	
	public Fasteign(FasteignaskraFasteignMatseining fasteignaskraFasteignMatseining, FasteignaEigandi fasteignaEigandi, Gata gata, String gotuNumer) {
		initialize(fasteignaskraFasteignMatseining, fasteignaEigandi, gata, gotuNumer);
	}
	
	private void initialize(FasteignaskraFasteignMatseining fasteignaskraFasteignMatseining, FasteignaEigandi fasteignaEigandi, Gata gata, String gotuNumer) {
		setLandnumer(fasteignaskraFasteignMatseining.getLandnr().toString());
		setFastaNumer(fasteignaskraFasteignMatseining.getFastanr().toString());
		setMatseiningNumer(fasteignaskraFasteignMatseining.getMatseiningarnr().toString());
		setMerking(fasteignaskraFasteignMatseining.getMerking());
		setNotkun(fasteignaskraFasteignMatseining.getNotkun());
		setSkyring(fasteignaskraFasteignMatseining.getSkyring());
		setEigandi(fasteignaEigandi);
		setGata(gata);
		setGotuNumer(gotuNumer);
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
		String sHaed = merking.substring(2,4);
		String sNr = merking.substring(4,6);

		StringBuffer buffer = new StringBuffer(notkun);
		if(notkun.endsWith("hæð")){
			buffer.append(" ");
			buffer.append(sHaed)
				.append(", ")
				.append(skyring)
				.append(" ")
				.append(sNr);
			notkun= buffer.toString();
		}
		else{
			buffer.append(", ");
			if("0".equals(sHaed)){
				buffer.append("jarðhæð");
			}
			else{
				buffer.append(sHaed).append(". hæð");
			}
			buffer.append(", nr. ").append(sNr);
		}
		/*String haedHuman = null;
		if(iHaed.intValue()==0){
			haedHuman = "jarðhæð";
		}
		else{
			haedHuman = iHaed+". hæð";
		}*/
		if(! "1".equals(sBygging)){
			buffer.append(", bygging ").append(sBygging);
		}
		
		return buffer.toString();
		
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
		buffer.append(" [Fastanr ");
		add(buffer, getFastaNumer());
		buffer.append("]");
		// matseining
		buffer.append(" Matseiningnr ");
		buffer.append(getMatseiningNumer());
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

	
	/**
	 * @return Returns the matseining.
	 */
	public String getMatseiningNumer() {
		return matseiningNumer;
	}

	
	/**
	 * @param matseining The matseining to set.
	 */
	public void setMatseiningNumer(String matseiningNumer) {
		this.matseiningNumer = matseiningNumer;
	}
	

}
