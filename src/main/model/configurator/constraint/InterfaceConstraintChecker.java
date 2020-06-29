package main.model.configurator.constraint;

import java.util.List;

import main.model.configurator.component.Component;
import main.model.configurator.configuration.Configuration;

/**
 * Used to check the compatibility between components ({@link Component}) or if a configuration ({@link Configuration})
 * is complete
 * 
 */
public interface InterfaceConstraintChecker {


	/**
	 * 
	 * @param newComponent
	 * @param oldComponents
	 * 
	 * @return List of Constraints that are not respected
	 * 
	 * @see {@link ConstraintChecker}
	 * @see {@link Component} 
	 */
	List<AbstractConstraint> check(Component newComponent, List<Component> oldComponents);
	
	/**
	 * Check if a list of components contains all the needed components
	 * @param components in the configuration
	 * @return true if all needed components are present, false if not all needed components are present
	 */
	boolean checkIfComplete(List<Component> components);
}
