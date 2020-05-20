package model.component.performanceAlgorithms;

import java.util.Map;

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
	public double computePerformance(Map<String, String> componentAttributes);
	
}
