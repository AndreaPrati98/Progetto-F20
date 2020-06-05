package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javax.xml.catalog.Catalog;

import model.catalog.ComponentCatalog;
import model.configuration.Configuration;
import model.dao.PersistenceFacade;
import servlet.ApplicationServer;
import servlet.ConfigurationServlet;
import servlet.HomeServlet;
import servlet.MyServlet;
import servlet.RegisterServlet;

public class tester2 {

	public tester2() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<MyServlet> servlet = new ArrayList<MyServlet>();
		servlet.add(new HomeServlet("home","/"));
		servlet.add(new RegisterServlet("register", "/sign-in"));
		servlet.add(new ConfigurationServlet("configuration", "/configuration/*"));
		new ApplicationServer(8082, servlet).start();
		
		// Questo va fatto all'avvio per inizializzare gli id da usare per le configurazioni
		PersistenceFacade pf = PersistenceFacade.getIstance();
		Configuration.setLastUsedId(pf.getLastUsedId());
		ComponentCatalog provaCat = new ComponentCatalog();
		Configuration confMR = new Configuration(); 
		Component comp1 = provaCat.getComponentByModel(")

	}

}
