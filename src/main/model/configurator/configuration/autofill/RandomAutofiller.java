package main.model.configurator.configuration.autofill;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import main.model.configurator.component.Component;

public class RandomAutofiller extends AbstractAutoFiller {
	private List<String> componentTypes;
	
	public RandomAutofiller() {
		componentTypes = new ArrayList<>();
		// Nella lista aggiungo nell'ordine i tipi di componenti a partire da quelli che hanno
		// più vincoli da rispettare, che quindi voglio che siano aggiunti per primi
		componentTypes.add("mobo");
		componentTypes.add("cpu");
		componentTypes.add("ram");
		componentTypes.add("case");
		componentTypes.add("gpu");
		componentTypes.add("power");
		componentTypes.add("massStorage");
		componentTypes.add("cooler");
	}
	
	@Override
	public List<Component> completeConfiguration(List<Component> alreadyInside) {
		//TODO cosa succede se alreadyInside dovesse essere null? Funziona tutto comunque?
		List<Component> completeConfig = new ArrayList<>(alreadyInside);
		for(String typeComp : componentTypes) {
			// Controllo se tra i componenti già presenti ce n'è uno del tipo specificato
			if(!containsTypeComponent(typeComp, completeConfig)) {
				List<Component> compatibleComp = new ArrayList<>();
				compatibleComp = getCompatibleComponents(typeComp, completeConfig);
				if(compatibleComp.isEmpty()) {
					System.out.println("Autocompletamento fallito");
					return alreadyInside;
				}
				// Genero un numero casuale con cui scegliere quale tra gli elementi compatibili 
				// inserire nella configurazione
				Random rand = new Random();
				int randomElem = rand.nextInt(compatibleComp.size());
				Component compToAdd = compatibleComp.get(randomElem);
				System.out.println("Aggiunto componente "+compToAdd.getTypeComponent()+": "+compToAdd.getModel());
				completeConfig.add(compToAdd);
			}
		}
		System.out.println("Completamento riuscito");
		return completeConfig;
	}

}
