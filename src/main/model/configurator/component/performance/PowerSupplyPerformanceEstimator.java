package main.model.configurator.component.performance;

import java.util.Map;

import main.model.configurator.component.Attribute;

/**
 * 
 * @author Irene S
 *
 */
public class PowerSupplyPerformanceEstimator implements InterfacePerformanceEstimator {
	private static final double MAX_POINT_POWER = 100;
	
	@Override
	public double computePerformance(Map<String, Attribute> componentAttributes) {
		/*
		 * Considera il seguente parametro:
		 * power
		 * 
		 */
		Attribute att = componentAttributes.get("power");
		if(att == null) {
			return -1;
		}
		String powerString = att.getValue();
		int power;
		try {
			power = Integer.parseInt(powerString);
		} catch (NumberFormatException e) {
			return -1;
		}
		
		return powerPerformance(power);
	}
	
	private double powerPerformance(int value) {
		if(value>750) {
			return MAX_POINT_POWER;
		} else if(value>650) {
			return 3*MAX_POINT_POWER/4;
		} else if(value>550) {
			return MAX_POINT_POWER/2;
		} else if(value>450) {
			return MAX_POINT_POWER/4;
		} else {
			return 0;
		}
	}

}
