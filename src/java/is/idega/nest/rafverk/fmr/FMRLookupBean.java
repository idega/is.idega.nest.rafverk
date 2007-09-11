package is.idega.nest.rafverk.fmr;

import fasteignaskra.landskra_wse.Fasteignaskra_Element;
import is.fmr.landskra.FasteignaskraClient;
import is.idega.nest.rafverk.domain.Fasteign;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FMRLookupBean {

	public static List hardList;

	
	
	public List getFasteignir(String addr,String postnumer){
		//TODO: Implement real webservice call
		/*if(hardList==null){
			hardList = getHardcodedList();
		}
		return hardList;*/
		
		List list = new ArrayList();
		
		FasteignaskraClient client = getFasteignaskraClient();
		try {
			List fasteignaskra = client.getFasteignirByHeitiAndPostnumer(addr, postnumer);
			if(fasteignaskra!=null){
				Iterator iterator = fasteignaskra.iterator();
				while (iterator.hasNext()) {
					Fasteignaskra_Element eFasteign = (Fasteignaskra_Element) iterator.next();
					List fasteignList = Fasteign.createFasteignFrom(eFasteign, postnumer);
					list.addAll(fasteignList);
				}
//				for (int i = 0; i < fasteignir.length; i++) {
//					Fasteignaskra_Element eFasteign = fasteignir[i];
//					Fasteign fasteign = new Fasteign(eFasteign,postnumer);
//					list.add(fasteign);
//				}
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



//	private List getHardcodedList() {
//
//		ArrayList list = new ArrayList();
//		String sveitarfelagsNumer=null;
//		
//		if(sveitarfelagsNumer==null){
//			sveitarfelagsNumer="0000";
//		}	
//		
//		InitialData initData = InitialData.getInitialData();
//		
//		Fasteign fasteign0 = new Fasteign();
//		fasteign0.setNafn("Engjavegur 6");
//		fasteign0.setFastaNumer("2019523");
//		fasteign0.setMerking("010001");
//		fasteign0.setNotkun("Íbúð");
//		fasteign0.setEigandi(new FasteignaEigandi("1010102009","Jón Jónsson"));
//		fasteign0.setGata(initData.getGataEngjavegur());
//		fasteign0.setGotuNumer("6");
//		list.add(fasteign0);
//		
//		Fasteign fasteign1 = new Fasteign();
//		fasteign1.setNafn("Engjavegur 6");
//		fasteign1.setFastaNumer("2019524");
//		fasteign1.setMerking("010101");
//		fasteign1.setNotkun("Íbúð");
//		fasteign1.setEigandi(new FasteignaEigandi("1011783159","Tryggvi Lárusson"));
//		fasteign1.setGata(initData.getGataEngjavegur());
//		fasteign1.setGotuNumer("6");
//		list.add(fasteign1);
//		
//		Fasteign fasteign2 = new Fasteign();
//		fasteign2.setNafn("Engjavegur 6");
//		fasteign2.setFastaNumer("2019526");
//		fasteign2.setMerking("010201");
//		fasteign2.setNotkun("Íbúð");
//		fasteign2.setEigandi(new FasteignaEigandi("1010103009","Sigurður Guðmundsson"));
//		fasteign2.setGata(initData.getGataEngjavegur());
//		fasteign2.setGotuNumer("6");
//		list.add(fasteign2);
//		
//		return list;
//	}



//	public Fasteign getFasteignByFastanumerAndPostnumer(String fastanumer,String postnumer){
//		
//
//		FasteignaskraClient client = getFasteignaskraClient();
//		Fasteignaskra_Element eFasteign;
//		try {
//			eFasteign = client.getFasteignByFastaNr(fastanumer);
//			Fasteign fasteign = new Fasteign(eFasteign,postnumer);
//			return fasteign;
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		throw new RuntimeException("Engin fasteign fannst");
//	}
	
	
}
