package main.model.configurator.configuration.autofill;

import java.util.ArrayList;
import java.util.List;

import main.model.configurator.ComponentCatalog;
import main.model.configurator.component.Component;

public class TesterAutofiller {

	public static void main(String[] args) {
		ComponentCatalog cc = ComponentCatalog.getInstance();
		List<Component> alreadyInside = new ArrayList<>();
		// Aggiungo i componenti che voglio che siano già dentroù
		// Se non aggiungo nessun componente allora mi fa una configurazione random da zero
		alreadyInside.add(cc.getComponentByModel("i5-9400F"));
		alreadyInside.add(cc.getComponentByModel("Cougar_MX330"));
		alreadyInside.add(cc.getComponentByModel("asus_prime_z390-1"));
		
		RandomAutofiller af = new RandomAutofiller();
		List<Component> completeConfig = new ArrayList<>();
		completeConfig = af.completeConfiguration(alreadyInside);
		for(Component c : completeConfig) {
			System.out.println(c.getTypeComponent()+": "+c.getModel());
		}

	}

}
