package is.idega.nest.rafverk.domain;

import com.idega.user.data.Group;

public class Orkufyrirtaeki extends BaseBean{
	
	
	String name;
	String id;
	
	public Orkufyrirtaeki() {
	}
	
	public Orkufyrirtaeki(Group energyCompany) {
		initialize(energyCompany);
	}
		
	public void initialize(Group energyCompany) {
		if (energyCompany != null) {
			id = energyCompany.getPrimaryKey().toString();
			name = energyCompany.getName();
		}
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
