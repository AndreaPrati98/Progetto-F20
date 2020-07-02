package main.model.configurator.configuration.autofill;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import main.model.configurator.component.Component;

/**
 * 
 * @author Irene Schimperna
 *
 */
public class RandomAutofiller extends AbstractAutoFiller {
	private List<String> componentTypes;
	
	public RandomAutofiller() {
		componentTypes = new ArrayList<>();
		// Nella lista aggiungo nell'ordine i tipi di componenti a partire da quelli che hanno
		// piu' vincoli da rispettare, che quindi voglio che siano aggiunti per primi.
		// Avremmo potuto scaricare l'elenco dei tipi di componenti dal database, ma non sarebbero
		// stati nell'ordine voluto, quindi abbiamo scelto di inserirli a mano
		componentTypes.add("mobo");
		componentTypes.add("cpu");
		componentTypes.add("ram");
		componentTypes.add("case");
		componentTypes.add("gpu");
		componentTypes.add("power");
		componentTypes.add("massStorage");
		componentTypes.add("cooler");
	}
	
	/**
	 * Complete the configuration in a random way, respecting all the compatibility constraints
	 * 
	 * @param alreadyInside components
	 * @return the list of components of the complete configuration or
	 * the initial list of components if the algorithm fails (for example if in the database there
	 * are no compatible components with the given ones)
	 */
	@Override
	public List<Component> completeConfiguration(List<Component> alreadyInside) {
		// Se viene passata una lista nulla mi comporto come se venisse passata una lista vuota
		if(alreadyInside == null) {
			alreadyInside = new ArrayList<>();
		}
		
		List<Component> completeConfig = new ArrayList<>(alreadyInside);
		for(String typeComp : componentTypes) {
			// Controllo se tra i componenti giu' presenti ce n'e' uno del tipo specificato
			if(!containsTypeComponent(typeComp, completeConfig)) {
				List<Component> compatibleComp = new ArrayList<>();
				compatibleComp = getCompatibleComponents(typeComp, completeConfig);
				if(compatibleComp.isEmpty()) {
					// Se non ci sono componenti compatibili allora l'autocompletamento e' fallito
					return alreadyInside;
				}
				// Genero un numero casuale con cui scegliere quale tra gli elementi compatibili 
				// inserire nella configurazione
				Random rand = new Random();
				int randomElem = rand.nextInt(compatibleComp.size());
				Component compToAdd = compatibleComp.get(randomElem);
				completeConfig.add(compToAdd);
			}
		}
		return completeConfig;
	}

}
