package main.services.persistence;

import java.util.List;

import main.model.configurator.constraint.AbstractConstraint;

/**
 * Interface used to define methods related to constraints, that will later get implemented in RdbConstraintDAO
 * @see RdbConstraintDAO
 */

public interface InterfaceCostraintDAO {
	public List<AbstractConstraint> getAllConstraints(); 
	public boolean addNewConstraint(String name, String type);
	public boolean removeConstraint(String name);

}
