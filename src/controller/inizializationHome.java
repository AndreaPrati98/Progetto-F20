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
		System.out.println("Deepcool_CASTLE_360EX aggiunto con successo ");
		System.out.println(confMr.getAddedComponents().toString());
		
		
	
	}
	
	

}
