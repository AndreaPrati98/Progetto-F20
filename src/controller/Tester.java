package controller;

import java.util.ArrayList;
import java.util.List;

import model.dao.PersistenceFacade;
import servlet.ApplicationServer;
import servlet.ConfigurationServlet;
import servlet.HomeServlet;
import servlet.MyServlet;
import servlet.RegisterServlet;

public class Tester {

	public static void main(String[] args) {
		List<MyServlet> servlet = new ArrayList<MyServlet>();
		servlet.add(new HomeServlet("home","/"));
		servlet.add(new RegisterServlet("register", "/sign-in"));
		servlet.add(new ConfigurationServlet("configuration", "/configuration"));
		new ApplicationServer(8080, servlet).start();
		

		
//		ComponentCatalog catalog = new ComponentCatalog();
//		
//		for (Component c : catalog.getComponentList()) {
//			System.out.println(c.getTypeComponent()+"  "+c.getAttributesMap().get("modello").getValue());
//		}
//	System.out.println(catalog.getComponentList().size());
		
		
		
		
	}

}
