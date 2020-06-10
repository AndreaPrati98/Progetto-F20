package main.model.configurator.configuration.autofill;

import java.util.List;

import main.model.configurator.component.Component;

public interface InterfaceAutofiller {
	
	List<Component> completeConfiguration(List<Component> alreadyInside);
	
	// L'ho aggiunta cosi mi pusha il package
	// Schimpa
}
