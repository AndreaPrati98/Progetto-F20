package controller;

import model.catalog.ComponentCatalog;
import model.configurator.Configurator;
import view.TesterFrame;

public class Tester {

	public static void main(String[] args) {

		ComponentCatalog catalog = new ComponentCatalog();
		Configurator model = new Configurator(catalog);
		TesterFrame view = new TesterFrame("Prova");
		Controller controller = new Controller(view, model);

	}

}
