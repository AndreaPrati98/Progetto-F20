package main.model.configurator.constraint;

import java.util.List;

import main.model.configurator.component.Component;

public interface InterfaceConstraintChecker {


	/**
	 * Estrae i {@link Constraint} non rispettati a partire dalla lista dei componenti gia aggiunti
	 * e controlla che siano compatibili con i Constrint del nuovo componente
	 * 
	 * @param newComponent
	 * @param oldComponents
	 * 
	 * @return La lista di Constraint non rispettati
	 * 
	 * @see Constraint
	 * @see Component 
	 */
	List<AbstractConstraint> check(Component newComponent, List<Component> oldComponents);
}
