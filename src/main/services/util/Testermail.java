package main.services.util;

import java.util.List;

import main.model.configurator.component.Component;
import main.model.configurator.configuration.Configuration;
import main.services.persistence.PersistenceFacade;

public class Testermail {

	public static void main(String[] args) {
		//Mail m =new Mail("alessandrocapici.ac@gmail.com", "Alessandro");
		//HashingPassword hp= new HashingPassword();
		PersistenceFacade pf=PersistenceFacade.getIstance();
		Configuration configuration = pf.getConfiguration(8);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Component comp : configuration.getAddedComponents()) {
			System.out.println(comp.getModel());
		}
	}

}
