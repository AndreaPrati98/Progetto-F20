package model.component;

import java.util.List;
import java.util.Map;

import model.component.constraint.Constraint;
import model.component.performanceAlgorithms.InterfacePerformanceEstimator;
import model.component.performanceAlgorithms.PerformanceEstimatorFactory;

/**
 * 
 * @author Capici Alessandro
 * @co_author Cic
 * @co_author Ste
 *
 */
public class Component {

	private String typeComponent;
	//private List<Constraint> constraintList;//non � meglio una mappa??
	private Map<String, Attribute> attributesMap;
	private double performanceIndex;

	public Component(String typeComponent, List<Constraint> constraintList, Map<String, Attribute> attributesMap) {
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

	public void setAttribute(Attribute attribute) {
		attributesMap.put(attribute.getName(), attribute);
	}
	
	public Attribute getAttributeByName(String name) {
		return attributesMap.get(name);
	}
	
	public String getAttributeValueByName(String name) {
		return attributesMap.get(name).getValue();
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
		return "Component [typeComponent=" + typeComponent + ", attributesMap=" + attributesMap + ", performanceIndex="
				+ performanceIndex + "]";
	}

	/**
	 * 
	 * @return price  dd 
	 * 
	 * @desc fornisce indicazioni relative al prezzo amazon
	 */
	
	public double getPrice() {
		
		return Double.parseDouble(attributesMap.get("price").getValue());
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
				//performanceIndex = perfEstim.computePerformance(attributesMap);
				/*
				 * DECOMMENTARE QUANDO AVREMO AGGIUSTATO I PERFORMANCE ESTIMATOR CON GLI ATTRIBUTE
				 */
			}
		}
		return performanceIndex;
		
	}
	

}
