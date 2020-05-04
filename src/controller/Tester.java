package controller;

import java.util.ArrayList;
import java.util.List;

import model.catalog.ComponentCatalog;
import model.configuration.Configuration;
import model.configurator.Configurator;
import view.TesterFrame;

public class Tester {

	public static void main(String[] args) {
		List<String> neededComponents=new ArrayList<String>();
		neededComponents.add("cpu");
		neededComponents.add("ram");
		neededComponents.add("mobo");
		neededComponents.add("gpu");
		
		ComponentCatalog catalog = new ComponentCatalog();
		Configuration configuration=new Configuration(neededComponents);
		Configurator model = new Configurator(catalog,configuration);
		TesterFrame view = new TesterFrame("Prova");
		Controller controller = new Controller(view, model);

	}

}
