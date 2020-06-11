package main.model.configurator.component.performance;

import java.util.Map;

import main.model.configurator.component.Attribute;

public class CoolerPerformanceEstimator implements InterfacePerformanceEstimator {

	private static final double MAX_POINT_TDP = 100;	
	
	@Override
	public double computePerformance(Map<String, Attribute> componentAttributes) {
		Attribute att = componentAttributes.get("tdp");
		
		if(att == null) {
			return -1;			
		}
		
		int tdp = -1; 
		try {
			tdp = Integer.parseInt(att.getValue());
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return -1;
			
		}
		
		return tdpPerformance(tdp);
	}
	
	private double tdpPerformance(int value) {
		if(value > 1100) {
			return CoolerPerformanceEstimator.MAX_POINT_TDP;
		} else if(value > 800) {
			return CoolerPerformanceEstimator.MAX_POINT_TDP * 2/3;			
		} else if(value > 500) {
			return CoolerPerformanceEstimator.MAX_POINT_TDP/2;
		} else if (value > 150) {
			return CoolerPerformanceEstimator.MAX_POINT_TDP/3;			
		} else if(value > 100) {
			return CoolerPerformanceEstimator.MAX_POINT_TDP/4;			
		} else {
			return 0;
		}
	}

}
