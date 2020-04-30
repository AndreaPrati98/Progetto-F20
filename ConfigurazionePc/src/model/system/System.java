package model.system;

import java.util.ArrayList;
import java.util.List;

import model.catalog.ComponentCatalog;
import model.component.Component;
import model.configuration.Configuration;
import model.customer.Customer;

// Irene Ripari, Alessandro Capici
public class System {

	private ComponentCatalog catalog;
	private Customer customer;
	private Configuration configuration;

	public System(ComponentCatalog catalog, Customer customer) {
		this.catalog = catalog;
		this.customer = customer;

	}

	public ComponentCatalog getCatalog() {
		return catalog;
	}

	public void createConfiguration(List<String> neededComponents) {
		configuration = new Configuration(neededComponents);

	}

	public void addComponent(Component comp) {
		configuration.addComponent(comp);
	}
	
	public void checkConf() {
		configuration.checkConf();
	}

	public Configuration getConfiguration() {
		return configuration;
	}

	public ComponentCatalog getCatalog(String compnentType) {
		return catalog;
	}

}
