package is.idega.nest.rafverk.fmr;

import fasteignaskra.landskra_wse.FasteignaskraFasteign;
import fasteignaskra.landskra_wse.FasteignaskraFasteignEigandi;
import fasteignaskra.landskra_wse.FasteignaskraFasteignMatseining;
import is.fmr.landskra.FasteignaskraClient;
import is.idega.nest.rafverk.domain.Fasteign;
import is.idega.nest.rafverk.domain.FasteignaEigandi;
import is.postur.Gata;
import is.postur.Gotuskra;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FMRLookupBean {

	private Pattern streetNumberRangePattern;
	
	public FMRLookupBean() {
		// pattern like "12-144"
		streetNumberRangePattern = Pattern.compile("\\d+-\\d+");
	}
	
	public List getFasteignir(String addr,String postnumer){
		List list = new ArrayList();
		FasteignaskraClient client = getFasteignaskraClient();
		try {
			List fasteignaskra = client.getFasteignirByHeitiAndPostnumer(addr, postnumer);
			if(fasteignaskra!=null){
				Iterator iterator = fasteignaskra.iterator();
				while (iterator.hasNext()) {
					FasteignaskraFasteign eFasteign = (FasteignaskraFasteign) iterator.next();
					List fasteignList = createFasteignFrom(eFasteign, postnumer);
					list.addAll(fasteignList);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return list;
	}
	
	protected FasteignaskraClient getFasteignaskraClient() {
		return new FasteignaskraClient();
	}
	
	public List createFasteignFrom(FasteignaskraFasteign fasteignaskraFasteign,String postnumer) {
		// figure out street, replace
		// MyStreet 1-5 with 1R
		// to
		// MyStreet with 1-5
		String streetName = fasteignaskraFasteign.getGotuheiti();
		String gotuNumer = fasteignaskraFasteign.getHusnumer();
		Matcher matcher = streetNumberRangePattern.matcher(streetName);
		if (matcher.find()) {
			gotuNumer = matcher.group();
			streetName = matcher.replaceAll("");
		}
		streetName.trim();


		Gata gata = null;
		if(postnumer!=null){
			gata = Gotuskra.getCached().getGataByNafnAndPostnumer(streetName, postnumer);
		}
		// figure out owner
		FasteignaEigandi fasteignaEigandi = null;
		FasteignaskraFasteignEigandi[] eigendur = fasteignaskraFasteign.getEigandi();
		if(eigendur!=null && eigendur.length>=1){
			FasteignaskraFasteignEigandi firstowner = eigendur[0];
			fasteignaEigandi = new FasteignaEigandi(firstowner.getKennitala(),firstowner.getNafn());
		}
		FasteignaskraFasteignMatseining[] fasteignaskraFasteignMatseinings = fasteignaskraFasteign.getMatseining();
		
		// return list
		List list;
		if (fasteignaskraFasteignMatseinings == null || fasteignaskraFasteignMatseinings.length == 0) {
			String landnumer = fasteignaskraFasteign.getLandnr().toString();
			Fasteign fasteign = new Fasteign(landnumer, fasteignaEigandi, gata, gotuNumer);
			list = new ArrayList(1);
			list.add(fasteign);
		}
		else {
			list = new ArrayList(fasteignaskraFasteignMatseinings.length);
			for (int i = 0; i < fasteignaskraFasteignMatseinings.length; i++) {
				FasteignaskraFasteignMatseining fasteignaskraFasteignMatseining = fasteignaskraFasteignMatseinings[i];
				Fasteign fasteign = new Fasteign(fasteignaskraFasteignMatseining, fasteignaEigandi, gata, gotuNumer);
				list.add(fasteign);
			}
		}
		return list;
	}
}
