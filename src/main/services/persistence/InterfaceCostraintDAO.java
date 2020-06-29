package main.services.persistence;

import java.util.List;

import main.model.configurator.constraint.AbstractConstraint;

public interface InterfaceCostraintDAO {
/**
 * interfaccia per definire un piccolo set di metodi che verranno implementati dal DAO per aggiungere o rimuovere dei 
 * Constraint nel Db 	
 * @return
 */
	public List<AbstractConstraint> getAllConstraints(); 
	public boolean addNewConstraint(String name, String type);
	public boolean removeConstraint(String name);

}
