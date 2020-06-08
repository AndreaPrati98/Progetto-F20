package main.model.configurator.component.performance;

import java.util.Map;

import main.model.configurator.component.Attribute;

/**
 * 
 * @author Andrea
 * @author Irene S.
 *
 */
public interface InterfacePerformanceEstimator {

	/**
	 * Compute an index method based on the attributes inside the map
	 * 
	 * @param componentAttributes
	 * @return index of performance
	 */
	public double computePerformance(Map<String, Attribute> componentAttributes);
	
}
