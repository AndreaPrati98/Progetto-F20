package model.component;

import java.util.HashMap;
import java.util.Map;

import model.component.performanceAlgorithms.InterfacePerformanceEstimator;
import model.component.performanceAlgorithms.PerformanceEstimatorFactory;

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
	private String price;
	//private List<Constraint> constraintList;//
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
	
	public Component(String model, String typeOfComponent, String price, Map<String, Attribute> attributesMap) {
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
				+ "\t[typeComponent= " + typeComponent + "\nattributesMap= " + attributesMap + "\nperformanceIndex= "
				+ performanceIndex + "]\n";
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
				performanceIndex = perfEstim.computePerformance(attributesMap);
			}
		}
		return performanceIndex;
		
	}
	

}
