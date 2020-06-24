package main.model.configurator.component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.model.configurator.component.performance.InterfacePerformanceEstimator;
import main.model.configurator.component.performance.PerformanceEstimatorFactory;

/**
 * 
 * @author Capici Alessandro
 * @author Cic
 * @author Andrea
 *
 */
public class Component {

	private String model;
	private String typeComponent;
	private double price;
	private Map<String, Attribute> attributesMap;
	private double performanceIndex;

	public Component(String model, String typeOfComponent) {
		this.model = model;
		this.typeComponent = typeOfComponent;
		performanceIndex = -1;
		attributesMap = new HashMap<String, Attribute>();
	
	}
	
	public Component(String model, String typeOfComponent, Map<String, Attribute> attributesMap) {
		this(model, typeOfComponent);
		this.attributesMap = attributesMap;
	}
	
	public Component(String model, String typeOfComponent, double price, Map<String, Attribute> attributesMap) {
		this(model, typeOfComponent, attributesMap);
		this.price = price;
	}
	
	public Component(String typeComponent, /*List<Constraint> constraintList,*/ Map<String, Attribute> attributesMap) {
		this.setTypeComponent(typeComponent);
		this.attributesMap=attributesMap;
		performanceIndex = -1; // starebbe a significare "indice non calcolato" oppure "indice non calcolabile"
	}

	/**
	 * 
	 * @return attributes map
	 */
	public Map<String, Attribute> getAttributesMap() {
		return attributesMap;
	}



	/**
	 * 
	 * @param key type:String
	 * @param attributes type:String
	 * @return true if attributesMap 's attributes will be replace
	 */
	public boolean replaceAttribute(String key, Attribute attributes) {
		attributesMap.replace(key, attributes);
		return true;
	}
	
	/**
	 * 
	 * @param attribute
	 */
	public void setAttribute(Attribute attribute) {
		attributesMap.put(attribute.getName(), attribute);
	}
	
	/**
	 * 
	 * @param name of the attribute
	 * @return the attribute with the given name
	 */
	public Attribute getAttributeByName(String name) {
		return attributesMap.get(name);
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
		return "\nComponent:\n"
				+ "\t"+"[ model= "+ model +"\n\t"+"typeComponent= " + typeComponent + "\n\t"+"attributesMap= \n" + attributesMap + "\n\t"+"performanceIndex= "
				+ performanceIndex + "]\n";
	}

	/**
	 * 
	 * @return price  dd 
	 * 
	 * @desc fornisce indicazioni relative al prezzo amazon
	 */
	
	public double getPrice() {
		
		return price;
	}
	
	/**
	 * 
	 * @return a performance index between 0 and 100 
	 * or -1 if it is not possible to compute the performance index
	 */
	public Double getPerformanceIndex() {
		if(performanceIndex == -1) {
			PerformanceEstimatorFactory fact = new PerformanceEstimatorFactory();
			InterfacePerformanceEstimator perfEstim = fact.getPerformanceEstimator(typeComponent);
			if(perfEstim==null) {
				performanceIndex = -1;
			} else {
				performanceIndex = perfEstim.computePerformance(attributesMap);
			}
		}
		return performanceIndex;
		
	}

	public String getModel() {
		return model;
	}
	
	public List<Attribute> getAttributesByConstraint(String constraintName) {
		List<Attribute> att = new ArrayList<>();
		for(Attribute a : attributesMap.values()) {
			if(a.getConstraintName().equals(constraintName)) {
				att.add(a);
			}
		}
		
		return att;
	}
	

}
