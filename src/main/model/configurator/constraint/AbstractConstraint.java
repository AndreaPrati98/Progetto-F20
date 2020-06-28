package main.model.configurator.constraint;

import java.util.ArrayList;
import java.util.List;

import main.model.configurator.component.Attribute;
import main.model.configurator.component.Component;

/**
 * 
 * @author Guglielmo Cassini
 *
 */
public abstract class AbstractConstraint {
	
	private String name;
	
	/**
	 * @param name - name of the Constraint
	 */
	public AbstractConstraint(String name) {
		this.name = name;
	}
	
	/**
	 * The method to get the name of the object that implements the
	 * constraint interface.
	 * 
	 * @return the name of the constraint
	 */	
	public String getConstraintName() {
		return this.name;
	}
	/**
	 * Check that the Constraint given as parameters are all compatible
	 * with the contraint that is executing this method.
	 * 
	 * @param oldCheckedComponents - lista ({@link Component}) of component that we are sure that are compatibles
	 * @param componentToCheck - the component to check if is compatible with the list of components
	 * 
	 * @return true - if are all compatibles. <br>
	 * 			false - if there is at least one that's not compatible.
	 */
	abstract public boolean checkList(List<Component> oldCheckedComponents, Component componentToCheck);
	

	/**
	 * The method to extract a list of attributes with the same name of the contraint.
	 * 
	 * 
	 * 
	 * @return the list of attributes that have the same name of the constraint. If there aren't 
	 * attributes with that name returns null. If a list is null it means it's ok because there aren't
	 * attributes that goes in conflict with others.
	 */
	protected List<Attribute> selectAttributeSameName(List<Component> componentsFromWichExtracts){
		List<Attribute> list = new ArrayList<Attribute>();
		List<Attribute> tmpAttribute; 
		
		for(Component c : componentsFromWichExtracts) {	
			tmpAttribute = c.getAttributesByConstraint(this.name);
			if(!tmpAttribute.isEmpty()){
				list.addAll(tmpAttribute);
			}
		}	
		
		if(list.isEmpty())
			return null;
			
		return list;		
	}
	
	/**
	 * Extract, from a component, a List of attributes that have the same name of the constraint that is
	 * calling this method
	 * @param componentFromWhichExtracts - from wich we want to extract the list of attributes<br>
	 * @return null - if component as parameter has no attributes with same name of constraint <br>
	 * ListOfAttribute - the list of attributes that respect the condition
	 * 
	 * @see AbstractConstraint
	 * @see Component
	 * @see List
	 */
	protected List<Attribute> selectAttributeSameName(Component componentFromWhichExtracts){
		List<Attribute> list = new ArrayList<Attribute>();
		List<Attribute> tmpAttribute = componentFromWhichExtracts.getAttributesByConstraint(this.name);
		
		
		if(tmpAttribute != null)
			list.addAll(tmpAttribute);
		
		if(list.isEmpty())
			return null;
		
		return list;		
	}
	
	/**
	 * Filters the attributes list according to the CategoryType given as parameter. 
	 * 
	 * @param componentFromWichExtracts
	 * @return
	 */
	protected List<Attribute> filterAttributesList(List<Attribute> listToFilter, ConstraintCategory category){
		List<Attribute> filteredList = new ArrayList<Attribute>();
		
		for(Attribute attributeToCheck : listToFilter){
			if(attributeToCheck.getConstraintCategory() == category){
				filteredList.add(attributeToCheck);
			}			
		}
		
		return filteredList;		
	}
	
}
