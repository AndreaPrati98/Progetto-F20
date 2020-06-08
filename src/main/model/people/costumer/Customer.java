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
	private String nome;
	private String cognome;
	
	
	private boolean isAdmin;
	//private String name;
	private List<Configuration> configurationList;
	@SuppressWarnings("unused")
	private Configurator facadeController;
	
	public Customer(String name,String surname,String email,boolean isAdmin) {
		this.email=email;
		this.name=name;
		this.setSurname(surname);
		this.isAdmin=isAdmin;
		configurationList = new ArrayList<Configuration>();
	}
	
	public Customer(Configurator system) {
		configurationList = new ArrayList<Configuration>();
		facadeController = system;
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
	
	
	
	/*
	 * I metodi di add e di remove configuration non li ho fatti perch� mi sembra abbia pi� senso che 
	 * la responsabilit� di farlo l'abbia system e non il cliente direttamente dalla propria classe, ne parliamo alla riunione
	 */
	
	
}
