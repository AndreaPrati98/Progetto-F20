package model.component.performanceAlgorithms;

import java.util.Map;

/**
 * 
 * @author Andrea
 * @author Irene S.
 *
 */
public interface InterfacePerformanceEstimator {

	public double computePerformance(Map<String, String> componentAttributes);
	
}
