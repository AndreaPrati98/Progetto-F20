package model.component.performanceAlgorithms;

import java.util.Map;

import model.component.Attribute;

/**
 * 
 * @author Irene S
 *
 */
public class MassStoragePerformanceEstimator implements InterfacePerformanceEstimator {
	private static final double MAX_POINT_SIZE = 50;
	private static final double MAX_POINT_TYPE = 50;
	
	@Override
	public double computePerformance(Map<String, Attribute> componentAttributes) {
		/*
		 * Considera i seguenti parametri:
		 * size
		 * type
		 * 
		 */
		String sizeString = componentAttributes.get("size").getValue();
		String type = componentAttributes.get("type").getValue();
		if(type == null) {
			return -1;
		}
		
		int size;
		try {
			size = Integer.parseInt(sizeString);
		} catch (NumberFormatException e) {
			//e.printStackTrace();
			return -1;
		}
		
		double index = 0;
		if(type.equalsIgnoreCase("ssd")) {
			index += MAX_POINT_TYPE;
		} else if(type.equalsIgnoreCase("hdd")) {
			index += MAX_POINT_TYPE/2;
		} else {
			index += 0;
		}
		
		if(size>4000) {
			index += MAX_POINT_SIZE;
		} else if(size > 2000) {
			index += 3*MAX_POINT_SIZE/4;
		} else if(size > 1000) {
			index += MAX_POINT_SIZE/2;
		} else if(size > 500) {
			index += MAX_POINT_SIZE/4;
		} else {
			index += 0;
		}
		
		return index;
	}

}
