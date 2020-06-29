package main.model.configurator.configuration.autofill;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.model.configurator.ComponentCatalog;
import main.model.configurator.component.Component;

/**
 * 
 * @author Andrea Prati
 *
 */
public class PriceAutoFiller extends AbstractAutoFiller {
	private List<String> componentTypes;
	private Map<String, Double> percentageMap;
	private double priceScope;
	
	public PriceAutoFiller(double priceScope) {
		componentTypes = new ArrayList<>();
		// Nella lista aggiungo nell'ordine i tipi di componenti a partire da quelli che hanno
		// più vincoli da rispettare, che quindi voglio che siano aggiunti per primi
		// Avremmo potuto scaricare l'elenco dei tipi di componenti dal database, ma non sarebbero
		// stati nell'ordine voluto, quindi abbiamo scelto di inserirli a mano
		if(priceScope <= 500) {
			// Se voglio una configurazione a basso prezzo aggiungo solo le componenti essenziali
			// (cioè quelle che nel database sono needed)
			componentTypes.add("mobo");
			componentTypes.add("cpu");
			componentTypes.add("ram");
			componentTypes.add("case");
			componentTypes.add("power");
			componentTypes.add("massStorage");
		} else {
			// Se voglio una configurazione con un prezzo sopra una certa soglia allora 
			// nella confgurazione verranno aggiunte tutte le componenti
			componentTypes.add("mobo");
			componentTypes.add("cpu");
			componentTypes.add("ram");
			componentTypes.add("case");
			componentTypes.add("gpu");
			componentTypes.add("power");
			componentTypes.add("massStorage");
			componentTypes.add("cooler");
		}
		
		this.priceScope = priceScope; 
		this.percentageMap = calculatePercentageMap();
		
	}
	
	/**
	 * Completes the configuration trying to get as close as possible to the price scope
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
			// Controllo se tra i componenti già presenti ce n'è uno del tipo specificato
			if(!containsTypeComponent(typeComp, completeConfig)) {
				List<Component> compatibleComp = new ArrayList<>();
				compatibleComp = getCompatibleComponents(typeComp, completeConfig);
				if(compatibleComp.isEmpty()) {
					// Se non ci sono componenti compatibili allora l'autocompletamento è fallito
					return alreadyInside;
				}
				
				Component compToAdd = componentByScope(compatibleComp, typeComp);
				completeConfig.add(compToAdd);

			}
		}
		return completeConfig;
	}

	/**
	 * Get the component of the given type which price is the nearest to the price scope for that type.
	 * The price scope of a given type is obtained as a percentage of the total scope price.
	 * 
	 * @param compatibleComp
	 * @param typeOfComponent
	 * @return
	 */
	private Component componentByScope(List<Component> compatibleComp, String typeOfComponent) {

		Component bestComponent = null;
		double diff = -1;
		double temp;
		//controllo che la lista delle componenti compatibili non sia nulla
		if (compatibleComp == null) {
			return null;
		}
		
		//calcolo a quale prezzo devo puntare con questa componente
		double priceScopeByType = percentageMap.get(typeOfComponent)*priceScope;
		
		for (Component component : compatibleComp) {
			//se diff è negativa allora è il primo giro
			if (diff < 0) {
				diff = component.getPrice() - priceScopeByType;
				diff = Math.abs(diff);
				bestComponent = component;
			} else {
				temp = component.getPrice() - priceScopeByType;
				temp = Math.abs(temp);
				//se ho minimizzato ancora di più la differenza di prezzo mi salvo la componente
				if(temp < diff) {
					diff = temp;
					bestComponent = component;
				}
			}	
		}
		return bestComponent;
	}
	
	/**
	 * Create a map that contains the couple (typeOfComponent, Percentage of price)
	 * @return percentageMap
	 */
	private Map<String, Double> calculatePercentageMap() {
		ComponentCatalog catalog = ComponentCatalog.getInstance();
		//Creo la mappa che deve contenere la coppia tipo di componente-percentuale del prezzo
		Map<String, Double> percMap = new HashMap<String, Double>();
		
		double totalSum = 0;
		for (String typeOfC : componentTypes) {
			List<Component> compByType = catalog.getComponentListByType(typeOfC);
			double sum = 0;
			for (Component component : compByType) {
				sum += component.getPrice();
			}
			// Temporaneamente nella mappa viene messo il prezzo medio per ogni componente
			Double avg = sum / compByType.size();
			totalSum += avg;
			percMap.put(typeOfC, avg);
		}
		
		for (String typeOfC : componentTypes) {
			Double percentage = percMap.get(typeOfC) / totalSum;
			// rimpiazzo quello che era il prezzo medio con la percentuale
			percMap.put(typeOfC, percentage);
		}

		return percMap;
	}
	
}
