package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javax.xml.catalog.Catalog;

import model.catalog.ComponentCatalog;
import model.configuration.Configuration;
import model.configurator.Configurator;
import model.customer.Customer;
import model.dao.PersistenceFacade;
import servlet.ApplicationServer;
import servlet.ConfigurationServlet;
import servlet.HomeServlet;
import servlet.MyServlet;
import servlet.RegisterServlet;

public class inizializationHome {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		System.out.println("prova");
		// Questo va fatto all'avvio per inizializzare gli id da usare per le configurazioni
		PersistenceFacade pf = PersistenceFacade.getIstance();
		Configuration.setLastUsedId(pf.getLastUsedId());
		ComponentCatalog CatHome = new ComponentCatalog();
		Configuration confMr = new Configuration(); 
		confMr.setName("MrBlockchain_edition");
		confMr.addComponent(CatHome.getComponentByModel("Deepcool_CASTLE_360EX"));
		confMr.addComponent(CatHome.getComponentByModel("i9-9980HK"));
		confMr.addComponent(CatHome.getComponentByModel("seagate_barracuda"));
		confMr.addComponent(CatHome.getComponentByModel("seagate_barracuda"));
		confMr.addComponent(CatHome.getComponentByModel("Gigabyte_GeForce_GTX_660_Ti"));
		confMr.addComponent(CatHome.getComponentByModel("Gigabyte_GeForce_GTX_660_Ti"));
		confMr.addComponent(CatHome.getComponentByModel("Cougar_MX330"));
		confMr.addComponent(CatHome.getComponentByModel("Corsair_RM_750"));
		confMr.addComponent(CatHome.getComponentByModel("Corsair_RM_750"));
		confMr.addComponent(CatHome.getComponentByModel("corsair_vengeance_lpx"));
		confMr.addComponent(CatHome.getComponentByModel("corsair_vengeance_lpx"));
		confMr.addComponent(CatHome.getComponentByModel("asus_prime_z390-1"));
		System.out.println(confMr.getAddedComponents());
		System.out.println(confMr.getTotalPrice());
		pf.addUser("Stefano","Butera","stefano.butera01@universitadipavia.it","root",true);
		
		pf.addConfiguration(confMr, new Customer());
		System.out.println(confMr.checkConf());
		
		
		
		
		
		
		
		
	
	}
	
	

}
