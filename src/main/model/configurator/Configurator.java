package main.model.configurator;


import java.util.ArrayList;
import java.util.List;

import main.model.configurator.component.Component;
import main.model.configurator.configuration.Configuration;
import main.model.configurator.constraint.AbstractConstraint;
import main.model.people.costumer.Customer;
import main.services.persistence.PersistenceFacade;

/**
 * 
 * @author Capici Alessandro , Ripari Irene
 * 
 * 
 * */
public class Configurator {

	private ComponentCatalog catalog;
	private Customer customer;
	private Configuration configuration;

	public Configurator(ComponentCatalog catalog) {
		super();
		this.catalog = catalog;
	}
	
	public Configurator(ComponentCatalog catalog,Configuration configuration) {
		this.catalog = catalog;
		this.configuration=configuration;
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
	 * @param comp - Component that you would like to add.
	 * @return 
	 * @see {@link Configuration}
	 * 
	 * */
	public boolean addComponent(Component comp) {
		return configuration.addComponent(comp);
	}
	
	public boolean removeComponent(Component comp) {
		return configuration.removeComponent(comp);
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
	
	// TODO togliere, non serve piu!
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
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	/**
	 * 
	 * 
	 * @return the last constrinatErrorList that the configurations getted trying to 
	 * add an incompatible component.
	 * If returns null means that the last add of a component worked correctly.
	 */
	public List<String> getListStringOfConstraintErrors(){
		List<String> list = new ArrayList<String>();
		
		List<AbstractConstraint> costraintErrorsList = configuration.getConstraintErrors();
		
		for(AbstractConstraint constraint : costraintErrorsList){
			list.add(constraint.getConstraintName());
		}
		
		if(list.isEmpty())
			return null;
		
		return list;
	}
	
	public boolean saveConfiguration(){
		PersistenceFacade facade = PersistenceFacade.getIstance();
		return 	facade.addConfiguration(configuration, customer);
	}
	
	

}
