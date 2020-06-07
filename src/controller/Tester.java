package controller;

import java.util.ArrayList;
import java.util.List;

import model.catalog.ComponentCatalog;
import model.configuration.Configuration;
import model.dao.PersistenceFacade;
import servlet.ApplicationServer;
import servlet.ConfigurationServlet;
import servlet.HomeServlet;
import servlet.LoginServlet;
import servlet.LogoutServlet;
import servlet.MyServlet;
import servlet.RegisterServlet;

public class Tester {

	public static void main(String[] args) {
		
		List<MyServlet> servlet = new ArrayList<MyServlet>();
		servlet.add(new HomeServlet("home","/"));
		servlet.add(new RegisterServlet("register", "/sign-in"));
		servlet.add(new ConfigurationServlet("configuration", "/configuration/*"));
		servlet.add(new LoginServlet("login", "/login"));
		servlet.add(new LogoutServlet("logout", "/logout"));
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
