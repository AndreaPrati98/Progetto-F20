package main.model.configurator.constraint;

import java.util.List;

import main.model.configurator.component.Component;

public interface InterfaceConstraintChecker {


	/**
	 * 
	 * @param newComponent
	 * @param oldComponents
	 * 
	 * @return List of Constraints that are not respected
	 * 
	 * @see Constraint
	 * @see Component 
	 */
	List<AbstractConstraint> check(Component newComponent, List<Component> oldComponents);
	
	/**
	 * Check if a list of components contains all the needed components
	 * @param components in the configuration
	 * @return true if all needed components are present, false if not all needed components are present
	 */
	boolean checkIfComplete(List<Component> components);
}
