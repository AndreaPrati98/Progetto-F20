package controller;

import java.util.*;

import model.catalog.ComponentCatalog;
import model.component.Component;
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
		
		ComponentCatalog catalog = new ComponentCatalog();
		
		for (Component c : catalog.getComponentList()) {
			System.out.println(c.getAttributesMap().get("modello").getValue());
		}
		System.out.println(catalog.getComponentList().size());
		
	}

}
