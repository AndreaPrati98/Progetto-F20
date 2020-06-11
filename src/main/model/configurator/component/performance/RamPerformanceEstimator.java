package main.model.configurator.component.performance;

import java.util.Map;

import main.model.configurator.component.Attribute;

public class RamPerformanceEstimator implements InterfacePerformanceEstimator {
	private static final double MAX_POINT_SIZE = 33;
	private static final double MAX_POINT_TYPE = 34;
	private static final double MAX_POINT_FREQUENCY = 33;
	
	@Override
	public double computePerformance(Map<String, Attribute> componentAttributes) {
		Attribute sizeAtt = componentAttributes.get("ramSize");
		Attribute typeAtt = componentAttributes.get("ramType");
		Attribute freqAtt = componentAttributes.get("ramMaxFrequency");
		if(sizeAtt == null || typeAtt == null || freqAtt == null) {
			return -1;
		}
		
		String type = typeAtt.getValue();
		int size;
		int freq;
		
		try {
			size = Integer.parseInt(sizeAtt.getValue());
			freq = Integer.parseInt(freqAtt.getValue());
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return -1;
		}
		
		return sizePerformance(size) + typePerformance(type) + frequencyPerformance(freq);
	}
	
	private double sizePerformance(int value) {
		if(value > 16) {
			return MAX_POINT_SIZE;
		} else if(value > 8) {
			return 3*MAX_POINT_SIZE/4;
		} else if(value > 4) {
			return MAX_POINT_SIZE/2;
		} else if(value > 2) {
			return MAX_POINT_SIZE/4;
		} else {
			return 0;
		}
	}
	
	private double typePerformance(String value) {
		if(value.equalsIgnoreCase("DDR4")) {
			return MAX_POINT_TYPE;		
		} else if(value.equalsIgnoreCase("DDR3")) {
			return MAX_POINT_TYPE/2;
		} else {
			return 0;
		}
	}
	
	//Qui i valori sono un po' a caso
	private double frequencyPerformance(int value) {
		if(value>=3600) {
			return MAX_POINT_FREQUENCY;
		} else if(value >= 3000) {
			return 3*MAX_POINT_FREQUENCY/4;
		} else if(value >= 2400) {
			return MAX_POINT_FREQUENCY/2;
		} else if(value >= 1600) {
			return MAX_POINT_FREQUENCY/4;
		} else {
			return 0;
		}
	}

}
