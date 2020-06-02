package model.component.performanceAlgorithms;

import java.util.Map;

import model.component.Attribute;

public class GPUPerformanceEstimator implements InterfacePerformanceEstimator {

	private static final double MAX_POINT_MEMORY = 50;
	private static final double MAX_POINT_CORE = 50;
//	private static final double MAX_POINT_THREAD = 20;
//	private static final double MAX_POINT_CACHE = 20;
	
	//TODO ancora da finire
	@Override
	public double computePerformance(Map<String, Attribute> componentAttributes) {
		double index = 0;
		
		// Controllo giusto per sicurezza
		if(componentAttributes == null) {
			throw new NullPointerException("Invalid instance of componentAttributes");
		}

		Attribute att1 = componentAttributes.get("coreCount");
		Attribute att2 = componentAttributes.get("graphicCardFrequency");
		
		if(att1 == null || att2 == null) {
			throw new NullPointerException("Invalid instance of Attribute");			
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
		
		if(coreCount > 1) {
			index += GPUPerformanceEstimator.MAX_POINT_CORE;
		} else {
			index += 0;
		}
		
		if(memory > 8) {
			index += GPUPerformanceEstimator.MAX_POINT_MEMORY;
		} else if (memory > 4) {
			index += GPUPerformanceEstimator.MAX_POINT_MEMORY/2;
		} else {
			index += 0;
		}
		
		return index;
	}
	
}
