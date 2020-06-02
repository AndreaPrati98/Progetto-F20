package model.component.performanceAlgorithms;

import java.util.Map;

import model.component.Attribute;

public class CoolerPerformanceEstimator implements InterfacePerformanceEstimator {

	private static final double MAX_POINT_TDP = 100;	
	
	@Override
	public double computePerformance(Map<String, Attribute> componentAttributes) {

		double index = 0;
		
		// Controllo giusto per sicurezza
		if(componentAttributes == null) {
			throw new NullPointerException("Invalid instance of componentAttributes");
		}
		
		Attribute att = componentAttributes.get("tdp");
		
		if(att == null) {
			throw new NullPointerException("Invalid instance of Attribute");			
		}
		
		int tdp = -1; 
		
		try {
			tdp = Integer.parseInt(att.getValue());
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return -1;
			
		}
		
		if(tdp > 1100) {
			index = CoolerPerformanceEstimator.MAX_POINT_TDP;
		} else if(tdp > 800) {
			index = CoolerPerformanceEstimator.MAX_POINT_TDP * 2/3;			
		} else if(tdp > 500) {
			index = CoolerPerformanceEstimator.MAX_POINT_TDP/2;
		} else if (tdp > 150) {
			index = CoolerPerformanceEstimator.MAX_POINT_TDP/3;			
		} else if(tdp > 100) {
			index = CoolerPerformanceEstimator.MAX_POINT_TDP/4;			
		} else {
			return index;
		}
		
		return index;
	}

}
