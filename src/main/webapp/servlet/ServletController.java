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
 *         Dovendo essere usata da tutti ed avendone bisogno di un unica
 *         istanza, devo averla singleton
 */
public class ServletController {

	//public static ServletController controller;
	Configurator configurator;
	Customer customer;

	public ServletController() {
		ComponentCatalog catalog = new ComponentCatalog();
		List<String> neededComponents = new ArrayList<String>();
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

	public boolean addToConfiguration(String model) {
		ComponentCatalog catalog = configurator.getCatalog();
		Component component = catalog.getComponentByModel(model);
		if (component == null)
			return false;
		return configurator.addComponent(component);
	}

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

	public void printConf() {
		for (int i = 0; i < 5; i++) System.out.println(); //Serve a fare spazio biacno
		System.out.println(configurator.getConfiguration());

	}
	
	public boolean login(String email, String password){
		PersistenceFacade facade = PersistenceFacade.getIstance();
		//Dovrei avere una funzione di login sulla facade		
		if(facade.login(email, password)) {			
			//customer = facade.getUser(email);
			return true;
		}
		
		return false;
	}
	

}
