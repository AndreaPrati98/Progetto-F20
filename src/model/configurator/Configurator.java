package model.configurator;


import java.util.List;

import model.catalog.ComponentCatalog;
import model.component.Component;
import model.configuration.Configuration;

/**
 * 
 * @author Capici Alessandro , Ripari Irene
 * 
 * 
 * */
public class Configurator {

	private ComponentCatalog catalog;
	private Configuration configuration;

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
	 * 
	 * @param compnentType
	 * @return
	 */
	public ComponentCatalog getCatalog(String compnentType) {
		return catalog;
	}
	
	
	/**
	 * 
	 * @param comp {@link Component}
	 * @return true and false
	 */
	public boolean addComponent(Component comp) {
		return configuration.addComponent(comp);
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


}
