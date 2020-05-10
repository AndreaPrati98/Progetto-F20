package model.component;

import java.util.List;
import java.util.Map;

import model.component.constraint.Constraint;

/**
 * 
 * @author Capici Alessandro
 * @co_author Cic
 * @co_author Ste
 *
 */
public class Component {

	private String typeComponent;
	private List<Constraint> constraintList;//non � meglio una mappa??
	private Map<String, String> attributesMap;

	public Component(String typeComponent, List<Constraint> constraintList, Map<String, String> attributesMap) {
		this.setTypeComponent(typeComponent);
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
	public String getTypeComponent() {
		return typeComponent;
	}



	/**
	 * 
	 * @param typeComponent type:String
	 */
	public void setTypeComponent(String typeComponent) {
		this.typeComponent = typeComponent;
	}




	@Override
	public String toString() {
		return "Component [typeComponent=" + typeComponent + ", constraintList=" + constraintList + ", attributesMap="
				+ attributesMap + "]";
	}
	
	/**
	 * 
	 * @return price  dd 
	 * 
	 * 
	 */
	
	public double getPrice() {
		
		return Double.parseDouble(attributesMap.get("price"));
	}
	
	

}
