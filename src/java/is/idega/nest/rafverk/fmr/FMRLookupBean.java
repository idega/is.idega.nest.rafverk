package is.idega.nest.rafverk.fmr;

import is.idega.nest.rafverk.bean.InitialData;
import is.idega.nest.rafverk.domain.Fasteign;
import is.idega.nest.rafverk.domain.FasteignaEigandi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FMRLookupBean {

	public static List hardList;

	
	
	public List getFasteignir(String sveitarfelagsNumer,String addr){
		//TODO: Implement real webservice call
		if(hardList==null){
			hardList = getHardcodedList();
		}
		return hardList;
	}
	
	
	
	private List getHardcodedList() {

		ArrayList list = new ArrayList();
		String sveitarfelagsNumer=null;
		
		if(sveitarfelagsNumer==null){
			sveitarfelagsNumer="0000";
		}	
		
		InitialData initData = InitialData.getInitialData();
		
		Fasteign fasteign0 = new Fasteign();
		fasteign0.setNafn("Engjavegur 6");
		fasteign0.setFastaNumer("2019523");
		fasteign0.setMerking("010001");
		fasteign0.setNotkun("Íbúð");
		fasteign0.setEigandi(new FasteignaEigandi("1010102009","Jón Jónsson"));
		fasteign0.setGata(initData.getGataEngjavegur());
		fasteign0.setGotuNumer("6");
		list.add(fasteign0);
		
		Fasteign fasteign1 = new Fasteign();
		fasteign1.setNafn("Engjavegur 6");
		fasteign1.setFastaNumer("2019524");
		fasteign1.setMerking("010101");
		fasteign1.setNotkun("Íbúð");
		fasteign1.setEigandi(new FasteignaEigandi("1011783159","Tryggvi Lárusson"));
		fasteign1.setGata(initData.getGataEngjavegur());
		fasteign1.setGotuNumer("6");
		list.add(fasteign1);
		
		Fasteign fasteign2 = new Fasteign();
		fasteign2.setNafn("Engjavegur 6");
		fasteign2.setFastaNumer("2019526");
		fasteign2.setMerking("010201");
		fasteign2.setNotkun("Íbúð");
		fasteign2.setEigandi(new FasteignaEigandi("1010103009","Sigurður Guðmundsson"));
		fasteign2.setGata(initData.getGataEngjavegur());
		fasteign2.setGotuNumer("6");
		list.add(fasteign2);
		
		return list;
	}



	public Fasteign getFasteignByFastanumer(String fastanumer){
		List fasteignir = getFasteignir(null,null);
		for (Iterator iter = fasteignir.iterator(); iter.hasNext();) {
			Fasteign fasteign = (Fasteign) iter.next();
			if(fasteign.getFastaNumer().equals(fastanumer)){
				return fasteign;
			}
		}
		throw new RuntimeException("Engin fasteign fannst");
	}
	
	
}
