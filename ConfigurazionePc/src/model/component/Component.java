package model.component;

import java.util.List;
import java.util.Map;

import model.component.constraint.Constraint;

/**
 * 
 * @author Capici Alessandro
 *
 */
public class Component {

	private String name;
	private List<Constraint> constraintList;//non è meglio una mappa??
	private Map<String, String> attributesMap;

	public Component(String name, List<Constraint> constraintList, Map<String, String> attributesMap) {
		this.setNameComponent(name);
		this.constraintList = constraintList;
		this.attributesMap=attributesMap;
	}

	
	
	/**
	 * 
	 * @return constraint's list
	 */
	public List<Constraint> getConstraints() {
		// TODO Auto-generated method stub
		return this.constraintList;
	}



	/**
	 * 
	 * @return attributes map
	 */
	public Map<String, String> getAttributesMap() {
		return attributesMap;
	}



	/**
	 * 
	 * @param key type:String
	 * @param attributes type:String
	 * @return true if attributesMap 's attributes will be replace
	 */
	public boolean setAttributesMap(String key, String attributes) {
		attributesMap.replace(key, attributes);
		return true;
	}



	/**
	 * 
	 * @return type component
	 */
	public String getNameComponent() {
		return name;
	}



	/**
	 * 
	 * @param typeComponent type:String
	 */
	public void setNameComponent(String name) {
		this.name = name;
	}




	@Override
	public String toString() {
		return "Component [typeComponent=" + name + ", constraintList=" + constraintList + ", attributesMap="
				+ attributesMap + "]";
	}
	
	

}
