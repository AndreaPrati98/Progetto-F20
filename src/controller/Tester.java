package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.catalog.ComponentCatalog;
import model.configuration.Configuration;
import model.configurator.Configurator;
import model.db.SqliteDB;
import model.db.User;
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
		
		/** TEST FUNZIONAMENTO CONNESSIONE DATABASE, ANDATO A BUON FINE
		 * SqliteDB db = new SqliteDB();
		   User u = new User("Frenkli", "Buzhiqi", "frenkli98@gmail.com", "12345678");
		   db.getUsersFromDB();
		   db.closeConnection();
		 */
		
		ComponentCatalog catalog = new ComponentCatalog();
		Configuration configuration=new Configuration(neededComponents,singleComponents);
		Configurator model = new Configurator(catalog,configuration);
		TesterFrame view = new TesterFrame("Prova");
		Controller controller = new Controller(view, model);

	}

}
