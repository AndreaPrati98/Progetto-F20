package model.customer;


import java.util.ArrayList;
import java.util.List;

import model.configuration.Configuration;
import model.configurator.Configurator;
/**
 * 
 * @author Andrea  e stepa
 *
 */
public class Customer {

	//public static int MAX_NUMBER_OF_CONFIGURATIONS = 5;
	
	private String email;
	private String nome;
	private String cognome;
	
	
	private boolean isAdmin;
	//private String name;
	private List<Configuration> configurationList;
	@SuppressWarnings("unused")
	private Configurator facadeController;
	
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
	
	/*
	 * I metodi di add e di remove configuration non li ho fatti perch� mi sembra abbia pi� senso che 
	 * la responsabilit� di farlo l'abbia system e non il cliente direttamente dalla propria classe, ne parliamo alla riunione
	 */
	
	/*
	 * non c'era il costruttore che avesse questi campi e mi serviva
	 */
	public Customer(String nome , String cognome , String email , boolean isAdmin) {
		this.cognome=cognome;
		this.nome=nome;
		this.email=email;
		this.isAdmin=isAdmin;
	}
}
