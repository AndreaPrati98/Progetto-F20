package main.model.configurator.component.performance;

import java.util.Map;

import main.model.configurator.component.Attribute;

public class CasePerformanceEstimator implements InterfacePerformanceEstimator {

	private static final double MAX_POINT_FORM_FACTOR = 100;
	
	@Override
	public double computePerformance(Map<String, Attribute> componentAttributes) {
		Attribute att = componentAttributes.get("formFactor");
		if(att == null) {
			return -1;			
		}
		String formFactor = att.getValue();
		
		return formFactorPerformance(formFactor);
	}
	
	private double formFactorPerformance(String value) {
		if(value.equals("atx")) {
			return CasePerformanceEstimator.MAX_POINT_FORM_FACTOR;			
		} else if (value.equals("micro-atx")) {
			return CasePerformanceEstimator.MAX_POINT_FORM_FACTOR * 2/3;			
		} else if (value.equals("mini-atx")) {
			return CasePerformanceEstimator.MAX_POINT_FORM_FACTOR /3;
		} else {
			return -1;
		}
	}

}
