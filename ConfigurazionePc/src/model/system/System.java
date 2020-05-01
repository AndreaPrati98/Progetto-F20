package model.system;


import java.util.List;

import model.catalog.ComponentCatalog;
import model.component.Component;
import model.configuration.Configuration;
import model.customer.Customer;

/**
 * 
 * @author Capici Alessandro , Ripari Irene
 * 
 * 
 * */
public class System {

	private ComponentCatalog catalog;
	private Customer customer;
	private Configuration configuration;

	public System(ComponentCatalog catalog, Customer customer) {
		this.catalog = catalog;
		this.customer = customer;

	}
	/**
	 * @return catalog - returns the catalog 
	 * @see Configuration {@link Configuration}
	 * */
	public ComponentCatalog getCatalog() {
		return catalog;
	}
	
	/**
	 *@overloading 
	 *@param componentType - The filter of your search
	 * */
	public ComponentCatalog getCatalog(String compnentType) {
		return catalog;
	}
	
	/**
	 * @param neededComponent - List of the essential components that you must have in your configuration
	 * */
	public void createConfiguration(List<String> neededComponents) {
		configuration = new Configuration(neededComponents);

	}
	/**
	 * @param comp - Component that you would like to add.
	 * @see {@link Configuration}
	 * */
	public void addComponent(Component comp) {
		configuration.addComponent(comp);
	}
	
	/**
	 * @return true - if the configuration is valid
	 * @return false - if the configuration is not valid
	 * @see {@link Configuration} 
	 * */
	public boolean checkConf() {
		return configuration.checkConf();
	}
	/**
	 * @return configuration - returns the current configuration
	 * @see Configuration {@link Configuration}
	 * */
	public Configuration getConfiguration() {
		return configuration;
	}
	
	/**
	 * @return addedComponents - returns the current component's list of your configuration 
	 * @see Configuration {@link Configuration}
	 * */
	public List<Component> getAddedComponents(){
		return configuration.getAddedComponents();
	}
	/**
	 * @return neededComponents - returns the essential components that you must have in your configuration
	 * @see Configuration {@link Configuration}
	 * */
	public List<String> getNeededComponents(){
		return configuration.getNeededComponents();
	}

	@Override
	public String toString() {
		return "System [configuration=" + configuration + "]";
	}
	/**
	 * @return customer - returns the customer
	 * @see {@link Customer}
	 * */
	public Customer getCustomer() {
		return customer;
	}
	
	

}
