package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.catalog.ComponentCatalog;
import model.configuration.Configuration;
import model.configurator.Configurator;
import model.dao.RdbOperation;
import view.TesterFrame;

public class Tester {

	public static void main(String[] args) {
		List<String> neededComponents=new ArrayList<String>();
		neededComponents.add("cpu");
		neededComponents.add("ram");
		neededComponents.add("mobo");
		
		Map<String,Boolean> singleComponents = new HashMap<String,Boolean>();
		singleComponents.put("cpu", false);
		singleComponents.put("mobo", false);
		singleComponents.put("case", false);
		
		RdbOperation rdbo=new RdbOperation();

		ComponentCatalog catalog = new ComponentCatalog(rdbo);
		System.out.println(catalog.getComponentList().size());
		System.out.println(catalog);
		
		
//		Configuration configuration=new Configuration(neededComponents,singleComponents);
//		Configurator model = new Configurator(catalog,configuration);
//		TesterFrame view = new TesterFrame("Prova");
//		Controller controller = new Controller(view, model);

	}

}
