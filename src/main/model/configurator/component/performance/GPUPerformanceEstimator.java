package main.model.configurator.component.performance;

import java.util.Map;

import main.model.configurator.component.Attribute;

public class GPUPerformanceEstimator implements InterfacePerformanceEstimator {

	private static final double MAX_POINT_MEMORY = 50;
	private static final double MAX_POINT_CORE = 50;
//	private static final double MAX_POINT_THREAD = 20;
//	private static final double MAX_POINT_CACHE = 20;
	
	//TODO ancora da finire
	@Override
	public double computePerformance(Map<String, Attribute> componentAttributes) {
		Attribute att1 = componentAttributes.get("coreCount");
		Attribute att2 = componentAttributes.get("graphicCardFrequency");
		
		if(att1 == null || att2 == null) {
			return -1;			
		}		
		
		int coreCount = -1;
		double memory = -1;
		
		try {
			coreCount = Integer.parseInt(att1.getValue());
			memory = Double.parseDouble(att2.getValue());
		
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return -1;
		}
		
		return corePerformance(coreCount) + memoryPerformance(memory);
		
	}
	
	private double corePerformance(int value) {
		if(value > 1) {
			return GPUPerformanceEstimator.MAX_POINT_CORE;
		} else {
			return 0;
		}
	}
	
	private double memoryPerformance(double value) {
		if(value > 8) {
			return GPUPerformanceEstimator.MAX_POINT_MEMORY;
		} else if (value > 4) {
			return GPUPerformanceEstimator.MAX_POINT_MEMORY/2;
		} else {
			return 0;
		}
	}
}
