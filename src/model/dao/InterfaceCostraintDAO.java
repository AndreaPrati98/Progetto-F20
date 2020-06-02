package model.dao;

import java.util.List;

import model.component.constraint.AbstractConstraint;

public interface InterfaceCostraintDAO {
	
	//public AbstractConstraint getConstraint(String name, String typeOfComponent);
	public List<AbstractConstraint> getAllConstraints(); 
	public boolean addNewConstraint(String name, String type);
	public boolean removeConstraint(String name);

}
