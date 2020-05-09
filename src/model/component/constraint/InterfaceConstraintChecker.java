package model.component.constraint;

import java.util.List;
import model.component.Component;

public interface InterfaceConstraintChecker {


	/**
	 * Gets {@link Constraint} not respected from already added components list, and check if they are compatible 
	 * with new component's constraint.
	 * 
	 * @param newComponent
	 * @param oldComponents
	 * 
	 * @return list of constraints not followed
	 * 
	 * @see Constraint
	 * @see Component 
	 */
	List<Constraint> check(Component newComponent, List<Component> oldComponents);
}
