package main;

import java.util.ArrayList;
import java.util.List;

import main.model.configurator.configuration.Configuration;
import main.services.persistence.PersistenceFacade;
import main.webapp.servlet.AdministratorServlet;
import main.webapp.servlet.ApplicationServer;
import main.webapp.servlet.ConfigurationServlet;
import main.webapp.servlet.HomeServlet;
import main.webapp.servlet.LoginServlet;
import main.webapp.servlet.LogoutServlet;
import main.webapp.servlet.MyServlet;
import main.webapp.servlet.ProfileServlet;
import main.webapp.servlet.RegisterServlet;

public class TesterServlet {

	public static void main(String[] args) {
		
		List<MyServlet> servlet = new ArrayList<MyServlet>();
		servlet.add(new HomeServlet("home","/"));
		servlet.add(new RegisterServlet("register", "/sign-in"));
		servlet.add(new ConfigurationServlet("configuration", "/configuration/*"));
		servlet.add(new LoginServlet("login", "/login"));
		servlet.add(new LogoutServlet("logout", "/logout"));
		servlet.add(new ProfileServlet("profile", "/profile/*"));
		servlet.add(new AdministratorServlet("Admin", "/administrator/*"));
		new ApplicationServer(8080, servlet).start();
		
		// Questo va fatto all'avvio per inizializzare gli id da usare per le configurazioni
		PersistenceFacade pf = PersistenceFacade.getIstance();
		Configuration.setLastUsedId(pf.getLastUsedId());
		
//		ComponentCatalog cata = new ComponentCatalog();
//		System.out.println(cata.toString());
 		

		//		for (Component c : catalog.getComponentList()) {
//			System.out.println(c.getTypeComponent()+"  "+c.getAttributesMap().get("modello").getValue());
//		}
//	System.out.println(catalog.getComponentList().size());
		
		
		
		
	}

}
