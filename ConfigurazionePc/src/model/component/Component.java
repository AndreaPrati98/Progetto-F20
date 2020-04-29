package model.component;

import java.util.List;
import java.util.Map;

import model.component.constraint.Constraint;

//Capici Alessandro
public class Component {

	private String typeComponent;
	private List<Constraint> constraintList;//non è meglio una mappa??
	private Map<String, String> attributesMap;

	public Component(String typeComponent, List<Constraint> constraintList, Map<String, String> attributesMap) {
		this.setTypeComponent(typeComponent);
		this.constraintList = constraintList;
		this.attributesMap=attributesMap;
	}

	
	
	
	public List<Constraint> getConstraints() {
		// TODO Auto-generated method stub
		return this.constraintList;
	}




	public Map<String, String> getAttributesMap() {
		return attributesMap;
	}




	public boolean setAttributesMap(String key, String attributes) {
		attributesMap.replace(key, attributes);
		return true;
	}




	public String getTypeComponent() {
		return typeComponent;
	}




	public void setTypeComponent(String typeComponent) {
		this.typeComponent = typeComponent;
	}

}
