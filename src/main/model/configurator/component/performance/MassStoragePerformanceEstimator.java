package main.model.configurator.component.performance;

import java.util.Map;

import main.model.configurator.component.Attribute;

/**
 * 
 * @author Irene S
 *
 */
public class MassStoragePerformanceEstimator implements InterfacePerformanceEstimator {
	private static final double MAX_POINT_SIZE = 50;
	private static final double MAX_POINT_TYPE = 50;
	
	/*
	 * Considera i seguenti parametri:
	 * size
	 * type
	 * 
	 */
	
	@Override
	public double computePerformance(Map<String, Attribute> componentAttributes) {
		Attribute att1 = componentAttributes.get("size");
		Attribute att2 = componentAttributes.get("storageType");
		
		if(att1 == null ||att2 == null) {
			return -1;
		}
		
		String sizeString = att1.getValue();
		String type = att2.getValue();
		
		int size;
		try {
			size = Integer.parseInt(sizeString);
		} catch (NumberFormatException e) {
			//e.printStackTrace();
			return -1;
		}
		
		return typePerformance(type) + sizePerformance(size);
		
	}
	
	private double typePerformance(String value) {
		if(value.equalsIgnoreCase("ssd")) {
			return MAX_POINT_TYPE;
		} else if(value.equalsIgnoreCase("hdd")) {
			return MAX_POINT_TYPE/2;
		} else {
			return 0;
		}
	}
	
	private double sizePerformance(int value) {
		if(value>4000) {
			return MAX_POINT_SIZE;
		} else if(value > 2000) {
			return 3*MAX_POINT_SIZE/4;
		} else if(value > 1000) {
			return MAX_POINT_SIZE/2;
		} else if(value > 500) {
			return MAX_POINT_SIZE/4;
		} else {
			return 0;
		}
	}

}
