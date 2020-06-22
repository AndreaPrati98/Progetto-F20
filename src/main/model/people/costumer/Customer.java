package main.model.people.costumer;


import java.util.ArrayList;
import java.util.List;

import main.model.configurator.Configurator;
import main.model.configurator.configuration.Configuration;
/**
 * 
 * @author Andrea  e stepa
 *
 */
public class Customer {

	//public static int MAX_NUMBER_OF_CONFIGURATIONS = 5;
	private String name;
	private String surname;
	private String email;	
	private boolean isAdmin;

	private List<Configuration> configurationList;

	
	public Customer(String name,String surname,String email,boolean isAdmin) {
		this.email=email;
		this.name=name;
		this.setSurname(surname);
		this.isAdmin=isAdmin;
		configurationList = new ArrayList<Configuration>();
	}
	
	public List<Configuration> getConfigurationList() {
		return configurationList;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setConfigurationList(List<Configuration> configurationList) {
		this.configurationList = configurationList;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public boolean isAdmin() {
		return isAdmin;
	}
	

	
}
