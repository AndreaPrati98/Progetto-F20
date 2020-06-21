package main.model.configurator.configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.model.configurator.component.Component;
import main.model.configurator.configuration.autofill.PriceAutoFiller;
import main.model.configurator.configuration.autofill.RandomAutofiller;
import main.model.configurator.constraint.AbstractConstraint;
import main.model.configurator.constraint.ConstraintChecker;
import main.model.configurator.constraint.InterfaceConstraintChecker;
import main.model.configurator.constraint.MaxConstraint;

/**
 * @author Capici Alessandro ,Frenkli Buzhiqi
 * @co_author Cic
 */

public class Configuration {
	private static int lastUsedId;
	private int id;

	private String name;
	private List<Component> addedComponents;
	private List<AbstractConstraint> constraintErrors;

	/**
	 * Create an empty new configuration, with a never used id
	 */
	public Configuration() { 
		this.constraintErrors = new ArrayList<AbstractConstraint>();
		addedComponents = new ArrayList<Component>();
		lastUsedId++;
		this.id=lastUsedId;
		this.name = "configuration_"+this.id;
	}
	
	
	/**USATO
	 * Create an empty configuration with a given id.
	 * This constructor is used when the configuration is downloaded from the database
	 * @param id
	 */
	public Configuration(int id) {
		this.constraintErrors = new ArrayList<AbstractConstraint>();
		this.addedComponents = new ArrayList<Component>();
		this.id=id;
	}
	
	/**USATO
	 * Create a configuration with a given id and some components.
	 * This constructor is used when the configuration is downloaded from the database.
	 * @param id
	 * @param addedComponents
	 */
	public Configuration(int id, List<Component> addedComponents) {
		this(id);
		this.addedComponents=addedComponents;
	}
	
	//USATO
	public Configuration(int id, String name) {
		this(id);
		this.name = name;
	}
	
	public Configuration(int id, List<Component> addedComponents, String name) {
		this(id, name);
		this.addedComponents=addedComponents;
	}

	/**
	 * aggiunta del componente scelto nella lista dei componenti solo se compatibile
	 * 
	 * @param c type:{@link Component} Component that you would like to add.
	 * @return true if the component have been added, false if the component haven't
	 *         been added or it was already added before
	 * @see Component
	 * 
	 */
	public boolean addComponent(Component c) {
		//Di volta in volta tengo traccia solo degli errori avuti all'ultimo tentativo di inserimento
		constraintErrors.clear();
		
		InterfaceConstraintChecker checker = new ConstraintChecker();
		constraintErrors = checker.check(c, addedComponents);
		if(constraintErrors.isEmpty()) {
			addedComponents.add(c);
			return true;
		} else {
			return false;
		}
	}

	public boolean removeComponent(Component c) {
		if (addedComponents.removeAll(Collections.singleton(c))) {
			return true;
		} else {
			return false;
		}
	}


	/**
	 * Check if the configuration is complete
	 * @return true added components contains essential components
	 */
	public boolean checkConf() {
		InterfaceConstraintChecker cc = new ConstraintChecker();
		return cc.checkIfComplete(addedComponents);		
	}
	/**
	 * Used during downloading from db because they're ok
	 * @param c
	 * @return
	 */
	public boolean addComponentWithoutChecking(Component c) {
		this.addedComponents.add(c);
		return true;
	}

	/**
	 * 
	 * @return addedComponents
	 */
	public List<Component> getAddedComponents() {
		return addedComponents;
	}

	/**
	 * 
	 * @return the abstract constraint error list
	 */

	public List<AbstractConstraint> getConstraintErrors() {
		return constraintErrors;
	}

	@Override
	public String toString() {
		return "Configuration [addedComponents=" + addedComponents + "]";
	}
	
	/**
	 * Returns the total price of the configuration.
	 * Prices in the database are taken from Amazon.
	 * @return totalPrice
	 */
	public double getTotalPrice() {
		double totalPrice=0;
		for(Component c : this.getAddedComponents()) {
			totalPrice += c.getPrice();
		}
		return totalPrice;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static void setLastUsedId(int id) {
		lastUsedId = id;
	}

	public int getId() {
		return id;
	}
	
	/**
	 * 
	 * @return a performance index between 0 and 100 
	 * or -1 if it is not possible to compute the index
	 */
	public double getPerformance() {
		double performanceIndex = 0;
		if(addedComponents.isEmpty()) {
			return -1;
		}
		double numElem = 0;
		for(Component c : addedComponents) {
			double cIndex =c.getPerformanceIndex();
			if(cIndex<0) {
				return -1;
			}
			performanceIndex+=cIndex;
			numElem++; 
		}
		
		return performanceIndex/numElem;
	}
	
	/**
	 * Completes the configuration in a random way. 
	 * @return true if the autofill have been completed correctly
	 */
	public boolean autofillRandom() {
		RandomAutofiller autofiller = new RandomAutofiller();
		List<Component> newComponents = autofiller.completeConfiguration(addedComponents);
		if(newComponents.equals(addedComponents)) {
			//L'autocompletamento e' fallito
			return false;
		}
		addedComponents = newComponents;
		return true;
	}
	
	/**
	 * Completes the configuration trying to have a total price as close as possible to the target price
	 * @param targetPrice
	 * @return true if the autofill have been completed correctly
	 */
	public boolean autofillByPrice(double targetPrice) {
		PriceAutoFiller autofiller = new PriceAutoFiller(targetPrice);
		List<Component> newComponents = autofiller.completeConfiguration(addedComponents);
		if(newComponents.equals(addedComponents)) {
			//L'autocompletamento e' fallito
			return false;
		}
		addedComponents = newComponents;
		return true;
	}


	public void refreshId() {
		lastUsedId++;
		this.id = lastUsedId;
	}
	
	

}
