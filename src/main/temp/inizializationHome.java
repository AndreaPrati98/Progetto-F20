package main.temp;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javax.xml.catalog.Catalog;

import main.model.configurator.ComponentCatalog;
import main.model.configurator.Configurator;
import main.model.configurator.configuration.Configuration;
import main.model.people.costumer.Customer;
import main.services.persistence.PersistenceFacade;
import main.webapp.servlet.ApplicationServer;
import main.webapp.servlet.ConfigurationServlet;
import main.webapp.servlet.HomeServlet;
import main.webapp.servlet.MyServlet;
import main.webapp.servlet.RegisterServlet;

public class inizializationHome {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		System.out.println("prova");
	/*	// Questo va fatto all'avvio per inizializzare gli id da usare per le configurazioni
		PersistenceFacade pf = PersistenceFacade.getIstance();
		Configuration.setLastUsedId(pf.getLastUsedId());
		ComponentCatalog CatHome = new ComponentCatalog();
		
		Configuration confMr = new Configuration(); 
		Configuration confSt = new Configuration();
		Configuration confPr = new Configuration();
		
		confMr.setName("MrBlockchain_edition");
		confMr.addComponent(CatHome.getComponentByModel("Asus_ROG_RYUJIN"));
		confMr.addComponent(CatHome.getComponentByModel("i9-9980HK"));
		confMr.addComponent(CatHome.getComponentByModel("seagate_barracuda"));
		//confMr.addComponent(CatHome.getComponentByModel("seagate_barracuda"));
		confMr.addComponent(CatHome.getComponentByModel("msi_geforce_rtx_2080_super"));
		//confMr.addComponent(CatHome.getComponentByModel("msi_geforce_rtx_2080_super"));
		confMr.addComponent(CatHome.getComponentByModel("Cougar_MX330"));
		confMr.addComponent(CatHome.getComponentByModel("Corsair_RM_750"));
		confMr.addComponent(CatHome.getComponentByModel("corsair_vengeance_lpx"));
		//confMr.addComponent(CatHome.getComponentByModel("corsair_vengeance_lpx"));
		confMr.addComponent(CatHome.getComponentByModel("asus_prime_z390-1"));
		System.out.println(confMr.getAddedComponents());
		System.out.println(confMr.getTotalPrice());
		System.out.println(confMr.checkConf());
		
		
		confSt.setName("studio_edition");
		confSt.addComponent(CatHome.getComponentByModel("Noctua_NH-U12S"));
		confSt.addComponent(CatHome.getComponentByModel("i3-9100F"));
		confSt.addComponent(CatHome.getComponentByModel("samsung_860_evo"));
		confSt.addComponent(CatHome.getComponentByModel("DIYPC_MA01"));
		confSt.addComponent(CatHome.getComponentByModel("HEC_200_W_Mini_ITX"));
		confSt.addComponent(CatHome.getComponentByModel("hyperx_fury"));
		//confSt.addComponent(CatHome.getComponentByModel("hyperx_fury"));
		confSt.addComponent(CatHome.getComponentByModel("asus_prime_a32m-k"));
		
		//System.out.println(confSt.getAddedComponents());
		System.out.println(confSt.getTotalPrice());
		System.out.println(confSt.checkConf());
		
		
		confPr.setName("Professional");
		confPr.addComponent(CatHome.getComponentByModel("DIYPC_MA10"));
		confPr.addComponent(CatHome.getComponentByModel("i7-9850HL"));
		confPr.addComponent(CatHome.getComponentByModel("westerdigital_wd40"));
		//confPr.addComponent(CatHome.getComponentByModel("westerdigital_wd40"));
		confPr.addComponent(CatHome.getComponentByModel("Gigabyte_GeForce_GTX_660_Ti"));
		confPr.addComponent(CatHome.getComponentByModel("DIYPC_MA01"));
		confPr.addComponent(CatHome.getComponentByModel("HEC_200_W_Mini_ITX"));
		confPr.addComponent(CatHome.getComponentByModel("corsair_vengeance_lpx"));
		//confPr.addComponent(CatHome.getComponentByModel("corsair_vengeance_lpx"));
		confPr.addComponent(CatHome.getComponentByModel("msi_z97_gaming_3"));
		
		System.out.println(confPr.getTotalPrice());
		System.out.println(confPr.checkConf());
		
		
		
		Customer stefanoCostumer =  new Customer("Stefano","Butera","stefano.butera01@universitadipavia.it",true);
		pf.addConfiguration(confMr, stefanoCostumer);
		pf.addConfiguration(confSt, stefanoCostumer);
		pf.addConfiguration(confPr, stefanoCostumer);
		
		
		
		
		
		
		 * mi da degli errori di SQL che non capisco ho fatto il check non funziona il caricamento delle configurazioni 
		 * 
		 */
	
	}
	
	

}
