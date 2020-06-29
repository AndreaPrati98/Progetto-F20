package main.model.configurator;

import java.util.ArrayList;
import java.util.List;

import main.model.configurator.component.Component;
import main.model.configurator.configuration.Configuration;
import main.model.configurator.constraint.AbstractConstraint;
import main.model.customer.Customer;
import main.services.persistence.PersistenceFacade;

/**
 * 
 * @author Capici Alessandro , Ripari Irene
 * 
 * 
 */
public class Configurator {

	private Configuration configuration;

	public Configurator() {

	}

	public Configurator(Configuration configuration) {
		this.configuration = configuration;
	}

	public void newConfiguration() {
		configuration = new Configuration();
	}

	/**
	 * @param comp - Component that you would like to add.
	 * @return
	 * @see {@link Configuration}
	 * 
	 */
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
	 */
	public boolean checkConf() {
		return configuration.checkConf();
	}

	/**
	 * @return configuration - returns the current configuration
	 * @see Configuration {@link Configuration}
	 */
	public Configuration getConfiguration() {
		return configuration;
	}

	/**
	 * @return addedComponents - returns the current component's list of your
	 *         configuration
	 * @see Configuration {@link Configuration}
	 */
	public List<Component> getAddedComponents() {
		return configuration.getAddedComponents();
	}

	@Override
	public String toString() {
		return "System [configuration=" + configuration + "]";
	}

	/**
	 * 
	 * 
	 * @return the last constrinatErrorList that the configurations getted trying to
	 *         add an incompatible component. If returns null means that the last
	 *         add of a component worked correctly.
	 */
	public List<String> getListStringOfConstraintErrors() {
		List<String> list = new ArrayList<String>();

		List<AbstractConstraint> costraintErrorsList = configuration.getConstraintErrors();

		for (AbstractConstraint constraint : costraintErrorsList) {
			list.add(constraint.getConstraintName());
		}

		if (list.isEmpty())
			return null;

		return list;
	}

	public boolean saveConfiguration(Customer customer) {
		String email = customer.getEmail();
		int confId = configuration.getId();
		PersistenceFacade facade = PersistenceFacade.getIstance();
		if (email.equals(facade.getOwnerMailByConfigurationId(confId))) {
			return facade.updateConfiguration(configuration, customer);
		} else {
			configuration.refreshId();
			return facade.addConfiguration(configuration, customer);
		}

	}

	/**
	 * Retrieve from the database a previously created configuration setting it
	 * inside the configurator
	 * 
	 * @param confId
	 */
	public void retrieveConfigurationById(int confId) {
		PersistenceFacade facade = PersistenceFacade.getIstance();
		configuration = facade.getConfiguration(confId);
	}

	/**
	 * 
	 * @return the performance index of the current configuration
	 */
	public double getPerformaceIndex() {
		return configuration.getPerformance();
	}

	public double getPrice() {
		return configuration.getTotalPrice();
	}

	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

	public boolean removeConfiguration(int confId) {
		PersistenceFacade facade = PersistenceFacade.getIstance();
		return facade.removeConfiguration(confId);
	}
	
	public boolean autofillByPrice(double price){
		return configuration.autofillByPrice(price); 
	}	

	public boolean autofillRandom(){
		return configuration.autofillRandom(); 
	}

	public int getConfigurationId() {
		return configuration.getId();
	}
	
	//TODO: Aggiungere uml
	public void setConfigurationName(String name) {
		configuration.setName(name);
	}
	
	//TODO: Aggiungere uml
	public String getConfigurationName() {
		return configuration.getName();
	}
		
}
