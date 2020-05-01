package model.customer;

import java.lang.module.Configuration;
import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author Andrea
 *
 */
public class Customer {

	//public static int MAX_NUMBER_OF_CONFIGURATIONS = 5;
	
	//private String name;
	private List<Configuration> configurationList;

	public Customer() {
		configurationList = new ArrayList<Configuration>();
	}
	
	public List<Configuration> getConfigurationList() {
		return configurationList;
	}
	
	/*
	 * I metodi di add e di remove configuration non li ho fatti perchè mi sembra abbia più senso che 
	 * la responsabilità di farlo l'abbia system e non il cliente direttamente dalla propria classe, ne parliamo alla riunione
	 */
	
}
