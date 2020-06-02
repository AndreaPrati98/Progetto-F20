package model.component.performanceAlgorithms;

import java.util.Map;

import model.component.Attribute;

public class CasePerformanceEstimator implements InterfacePerformanceEstimator {

	private static final double MAX_POINT_FORM_FACTOR = 100;
	
	@Override
	public double computePerformance(Map<String, Attribute> componentAttributes) {
		
		double index = 0;
		
		if(componentAttributes == null) {
			throw new NullPointerException("Invalid instance of componentAttributes");
		}
		
		/*
		 * I case hanno 2 standard attribute al momento, solo uno di questi interessa le performance, volendo
		 */
		
		Attribute att = componentAttributes.get("formFactor");
		
		if(att == null) {
			throw new NullPointerException("Invalid instance of Attribute");			
		}
		
		String formFactor = att.getValue();
		
		if(formFactor == "atx") {
			index = CasePerformanceEstimator.MAX_POINT_FORM_FACTOR;			
		} else if (formFactor == "micro-atx") {
			index = CasePerformanceEstimator.MAX_POINT_FORM_FACTOR * 2/3;			
		} else if (formFactor == "mini-atx") {
			index = CasePerformanceEstimator.MAX_POINT_FORM_FACTOR /3;
		} else if(formFactor == null) {
			index = -1;
		}
		
		return index;
	}

}
