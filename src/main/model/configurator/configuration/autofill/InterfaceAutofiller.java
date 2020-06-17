package main.model.configurator.configuration.autofill;

import java.util.List;

import main.model.configurator.component.Component;

public interface InterfaceAutofiller {
	
	/**
	 * 
	 * @param alreadyInside components
	 * @return the list of components of the complete configuration or
	 * the initial list of components if the algorithm fails (for example if in the database there
	 * are no compatible components with the given ones)
	 */
	List<Component> completeConfiguration(List<Component> alreadyInside);
	
}
