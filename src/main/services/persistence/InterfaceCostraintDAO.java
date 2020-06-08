package main.services.persistence;

import java.util.List;

import main.model.configurator.constraint.AbstractConstraint;

public interface InterfaceCostraintDAO {
	
	public List<AbstractConstraint> getAllConstraints(); 
	public boolean addNewConstraint(String name, String type);
	public boolean removeConstraint(String name);

}
