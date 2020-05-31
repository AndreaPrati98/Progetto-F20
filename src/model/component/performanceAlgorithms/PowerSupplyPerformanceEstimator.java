package model.component.performanceAlgorithms;

import java.util.Map;

import model.component.Attribute;

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
		 * DEVO CONSIDERARE ALTRO??
		 */
		String powerString = componentAttributes.get("power").getValue();
		int power;
		try {
			power = Integer.parseInt(powerString);
		} catch (NumberFormatException e) {
			// La potenza non è disponibile tra gli attributi
			return -1;
		}
		
		double index = 0.0;
		if(power>750) {
			index += MAX_POINT_POWER;
		} else if(power>650) {
			index += 3*MAX_POINT_POWER/4;
		} else if(power>550) {
			index += MAX_POINT_POWER/2;
		} else if(power>450) {
			index += MAX_POINT_POWER/4;
		} else {
			index += 0;
		}
		
		return index;
	}

}
