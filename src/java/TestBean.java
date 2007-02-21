import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;


public class TestBean {

	String flippTexti;
	HtmlInputText flippTextiInput;
	Map keys;
	List manys;
	
	public TestBean(){
		keys = new HashMap();
		keys.put("test1", "ok");
		keys.put("testerflip", "rappson");
		
		manys = new ArrayList();
		manys.add("stak1");
		manys.add("stak2");
		manys.add("stak3");
		
	}
	
	
	public HtmlInputText getFlippTextiInput() {
		return flippTextiInput;
	}

	public Map getKeys() {
		return keys;
	}

	public void setKeys(Map keys) {
		this.keys = keys;
	}

	public List getManys() {
		return manys;
	}

	public void setManys(List manys) {
		this.manys = manys;
	}

	public void setFlippTextiInput(HtmlInputText flippTextiInput) {
		this.flippTextiInput = flippTextiInput;
	}

	public String getFlippTexti() {
		return flippTexti;
	}

	public void setFlippTexti(String flippTexti) {
		this.flippTexti = flippTexti;
	}

	public String doFlip() {
		//flippTexti=flippTexti+" is doflipped";
		System.out.println("doFlip: flippTexti: "+flippTexti);
		//setFlippTexti("gott stoff");
		//getFlippTextiInput().setValue("Jafnvel thetta");
		return null;
	}

	public void validateinput(FacesContext context, UIComponent component,
			Object value) {
		
		 //final String RESOURCE_BASE_NAME = ??;
		 //final String ERROR_KEY = ??;
		 //java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle(RESOURCE_BASE_NAME, context.getViewRoot().getLocale());
	
		 String error = "villa";
		 throw new javax.faces.validator.ValidatorException(new javax.faces.application.FacesMessage(error));
		 
		//TODO Auto-generated method stub
	}

	public void doFlip2(ActionEvent event) {
		//TODO Auto-generated method stub
		System.out.println("doFlip2: flippTexti: "+flippTexti);
		//setFlippTexti("gott stoff");
		getFlippTextiInput().setValue("Jafnvel thetta");
	}

	public String doForm() {
		System.out.println("doForm: flippTexti: "+flippTexti);
		
		return null;
		//TODO Auto-generated method stub
	}
	
	
	
}
