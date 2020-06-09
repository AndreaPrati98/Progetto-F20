package main.webapp.servlet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.model.configurator.ComponentCatalog;
import main.model.configurator.Configurator;
import main.model.configurator.component.Component;
import main.model.configurator.configuration.Configuration;
import main.model.people.costumer.Customer;
import main.services.persistence.PersistenceFacade;

/**
 * 
 * @author Ale e Cic
 *
 *         Classe che serve da tramite tra tutti i servlet ed il nostro modello.
 *         Ne viene istanziata una per ogni utente e viene poi conservata nel context
 *         handler.
 */
public class ServletController {

	//public static ServletController controller;
	Configurator configurator;

	public ServletController() {
		ComponentCatalog catalog = new ComponentCatalog();
		List<String> neededComponents = new ArrayList<String>();
		
		//TODO Nella versioe finale questa lista di stringhe deve essere recuperata dal 
		//db dove nella tabella sono presenti
		neededComponents.add("cpu");
		neededComponents.add("mobo");
		neededComponents.add("ram");
		neededComponents.add("case");
		neededComponents.add("power");
		neededComponents.add("massStorage");

		Map<String, Boolean> singleComponents = new HashMap<String, Boolean>();
		singleComponents.put("cpu", false);
		singleComponents.put("mobo", false);
		singleComponents.put("case", false);
		singleComponents.put("cooler", false);

		Configuration configuration = new Configuration(neededComponents, singleComponents);
		configurator = new Configurator(catalog, configuration);
	}
	
//
//	public static ServletController getIstance() {
//		if (controller == null)
//			controller = new ServletController();
//
//		return controller;
//	}

	/**
	 * Add the component with the given model to the configuration. 
	 * 
	 * @param model
	 * @return true if the Component with the specified model as parameter is
	 * added to the configuration
	 */
	public boolean addToConfiguration(String model) {
		ComponentCatalog catalog = configurator.getCatalog();
		Component component = catalog.getComponentByModel(model);
		if (component == null)
			return false;
		return configurator.addComponent(component);
	}

	/**
	 * Remove the component with the given model from the configuration.
	 * 
	 * @param model
	 * @return true if the Component with the specified model as parameter is 
	 * removed to the configuratio
	 */
	public boolean removeFromConfiguration(String model) {
		ComponentCatalog catalog = configurator.getCatalog();
		Component component = catalog.getComponentByModel(model);
		if (component == null)
			return false;
		return configurator.removeComponent(component);
	}

	public Map<String, List<Component>> getConfiguration() {
		return null;
	}

	/**
	 * Debug method for print the configuration
	 */
	public void printConf() {
		for (int i = 0; i < 5; i++) System.out.println(); //Serve a fare spazio biacno
		System.out.println(configurator.getConfiguration());

	}
	
	/**
	 * Try to log with the given email and password, and if correct retrive the Customer
	 * inside for the Configurator object.
	 * 
	 * @param email
	 * @param password
	 * @return true if the login goes right, false otherwise
	 */
	public boolean login(String email, String password){
		PersistenceFacade facade = PersistenceFacade.getIstance();
		//Dovrei avere una funzione di login sulla facade		
		if(facade.login(email, password)) {				
			//Da testare la login che funzioni, è brutto farlo così, magari lo sposto
			//TODO Aggiustare perchè causa eccezione il recupero dello User
			configurator.setCustomer(facade.getUser(email));
			return true;
		}
		
		return false;
	}
	
	/**
	 * Check if the configuration is correct relatively at other things like 
	 * single component.
	 * 
	 * @return true if the configuration is correct 
	 */
	public boolean checkConfiguration(){
		return  configurator.checkConf();	
	}
	
	public List<String> getConstraintErrors(){
		return configurator.getListStringOfConstraintErrors();
	}
	
	public boolean saveConfiguration(){
		return configurator.saveConfiguration();
	}

}
