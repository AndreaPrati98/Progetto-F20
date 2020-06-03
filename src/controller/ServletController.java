package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.catalog.ComponentCatalog;
import model.component.Component;
import model.configuration.Configuration;
import model.configurator.Configurator;
/**
 * 
 * @author Ale e Cic
 *
 *	Classe che serve da tramite tra tutti i servlet ed il nostro modello.
 *	Dovendo essere usata da tutti ed avendone bisogno di un unica istanza, devo averla singleton
 */
public class ServletController {

	public static ServletController controller;
	Configurator configurator;
	
	private ServletController() {
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
	
	public static ServletController getIstance(){
		if(controller == null)
			controller = new ServletController();
		
		return controller;
	}
	
	
	public boolean addToConfiguration(String model){
		ComponentCatalog catalog = configurator.getCatalog();
		Component component = catalog.getComponentByModel(model);
		if(component == null) 
			return false;
		return configurator.addComponent(component);
	}
	
	public boolean removeFromConfiguration(String model){
		ComponentCatalog catalog = configurator.getCatalog();
		Component component = catalog.getComponentByModel(model);
		if(component == null) 
			return false;
		return configurator.removeComponent(component);
	}
	
	

}
