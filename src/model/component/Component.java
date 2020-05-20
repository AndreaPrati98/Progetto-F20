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
	private List<Constraint> constraintList;//non � meglio una mappa??
	private Map<String, String> attributesMap;
	private double performanceIndex;

	public Component(String typeComponent, List<Constraint> constraintList, Map<String, String> attributesMap) {
		this.setTypeComponent(typeComponent);
		this.constraintList = constraintList;
		this.attributesMap=attributesMap;
		performanceIndex = -1; // starebbe a significare "indice non calcolato" oppure "indice non calcolabile"
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
	 * @desc fornisce indicazioni relative al prezzo amazon
	 */
	
	public double getPrice() {
		
		return Double.parseDouble(attributesMap.get("price"));
	}
	
	public Double getPerformanceIndex() {
		if(performanceIndex == -1) {
			PerformanceEstimatorFactory fact = new PerformanceEstimatorFactory();
			InterfacePerformanceEstimator perfEstim = fact.getPerformanceEstimator(typeComponent);
			performanceIndex = perfEstim.computePerformance(attributesMap);
			
		}
		return performanceIndex;
		
	}
	

}
