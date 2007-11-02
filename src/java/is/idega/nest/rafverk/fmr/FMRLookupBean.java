package is.idega.nest.rafverk.fmr;

import is.fmr.landskra.Fasteign;
import is.fmr.landskra.FasteignaskraClient;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.idega.util.StringHandler;

public class FMRLookupBean {
	
    private FasteignaskraClient fasteignaskraClient = null;
	
	public FMRLookupBean() {

	}
	
	public List getFasteignir(String streetName, String gotunumer, String postnumer){
		// streetNumber is a valid integer or null
		Integer searchStreetNumber = null;
		if (StringHandler.isEmpty(streetName)) {
			// use the field gotunumner as general search string
			streetName = gotunumer;
			searchStreetNumber = new Integer(-1);
		}
		else {
			searchStreetNumber = FasteignaskraClient.parseStreetNumber(gotunumer);
		}
		List list = new ArrayList();
		FasteignaskraClient client = getFasteignaskraClient();
		try {
			List result = client.getFasteignirByHeitiAndPostnumer(streetName, searchStreetNumber, postnumer);
			if(result != null){
				Iterator iterator = result.iterator();
				while (iterator.hasNext()) {
					Fasteign fasteign = (Fasteign) iterator.next();
					if (checkStreetNumber(fasteign, searchStreetNumber)) {
//						List fasteignList = createFasteignFrom(eFasteign, postnumer);
						list.add(fasteign);
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return list;
	}
	
	
	private boolean checkStreetNumber(Fasteign fasteign, Integer searchStreetNumber) {
		// no search street number given, accept all response
		if (searchStreetNumber.intValue() == -1) {
			return true;
		}
		String gotuNumer = fasteign.getGotuNumer();
		// filter out wrong streetnumber (if 2 was looked up 2, 21, 22 is also returned)
		Integer responseStreetNumber = FasteignaskraClient.parseResponseStreetNumber(gotuNumer);
		return responseStreetNumber.equals(searchStreetNumber);
	}
	
	private FasteignaskraClient getFasteignaskraClient() {
		if (fasteignaskraClient == null) {
			fasteignaskraClient = new FasteignaskraClient();
		}
		return fasteignaskraClient;
	}
	
}
