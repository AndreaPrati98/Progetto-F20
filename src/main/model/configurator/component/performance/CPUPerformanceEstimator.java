package main.model.configurator.component.performance;

import java.util.Map;

import main.model.configurator.component.Attribute;

/**
 * 
 * @author Andrea
 * @author Irene S.
 *
 */
public class CPUPerformanceEstimator implements InterfacePerformanceEstimator {

	/* Abbiamo diviso cosi' in base a quanti attributi consideriamo
	 * in questo caso 100/5
	 */
	private static final double MAX_POINT_RAM_TYPE = 20;
	private static final double MAX_POINT_CLOCK = 20;
	private static final double MAX_POINT_CORE = 20;
	private static final double MAX_POINT_THREAD = 20;
	private static final double MAX_POINT_CACHE = 20;
	
	/*
	 * Considera i seguenti parametri: 
	 * clock
	 * numero di core
	 * numero di thread
	 * tipo ram supportata
	 * cache
	 */
	
	/**
	 *
	 */
	@Override
	public double computePerformance(Map<String, Attribute> componentAttributes) {
		
		String ramType;
		double clock;
		int numberOfCore; 
		int numberOfThread; 		
		int cacheSize;
		
		try {
			ramType = componentAttributes.get("ramType").getValue();
			clock = Double.parseDouble(componentAttributes.get("cpuFrequency").getValue());
			numberOfCore = Integer.parseInt(componentAttributes.get("n_core").getValue()); 
			numberOfThread = Integer.parseInt(componentAttributes.get("n_thread").getValue()); 
			cacheSize = Integer.parseInt(componentAttributes.get("cacheSize").getValue());
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
			/*
			 * Ritorno valore negativo perche' non ho abbastanza info per calcolare un
			 * indice di performance
			 */
			return -1;
		} catch (NullPointerException e1) {
			//Potrebbe lanciarla se qualche attributo non e' presente nel momento
			//in cui cerca di accedere al suo valore
			e1.printStackTrace();
			return -1;
		}
		
		if (ramType == null) {
			return -1;
		}
		
		return ramTypePerformance(ramType)+clockPerformance(clock)+nCorePerformance(numberOfCore)+
				nThreadPerformance(numberOfThread)+cacheSizePerformance(cacheSize);
		
	}
	
	private double ramTypePerformance(String value) {
		if(value.equalsIgnoreCase("DDR4")) {
			return CPUPerformanceEstimator.MAX_POINT_RAM_TYPE;		
		} else if(value.equalsIgnoreCase("DDR3")) {
			return CPUPerformanceEstimator.MAX_POINT_RAM_TYPE/2;
		} else {
			return 0;
		}
	}
	
	private double clockPerformance(double value) {
		if(value >= 4.0) {
			return CPUPerformanceEstimator.MAX_POINT_CLOCK;
		} else if (value > 3.0) {
			return CPUPerformanceEstimator.MAX_POINT_CLOCK/2;
		} else if (value > 2.0) {
			return CPUPerformanceEstimator.MAX_POINT_CLOCK/3;
		} else if(value <= 1) {
			return 0;	
		} else {
			return CPUPerformanceEstimator.MAX_POINT_CLOCK/4;
		} 
	}
	
	private double nCorePerformance(int value) {
		if(value > 6) {
			return CPUPerformanceEstimator.MAX_POINT_CORE;
		} else if (value >= 4) {
			return 3*CPUPerformanceEstimator.MAX_POINT_CORE/4;
		} else if(value >= 2) {
			return CPUPerformanceEstimator.MAX_POINT_CORE/2;
		} else {
			return CPUPerformanceEstimator.MAX_POINT_CORE/5;
		}
	}
	
	private double nThreadPerformance(int value) {
		if(value > 12) {
			return CPUPerformanceEstimator.MAX_POINT_THREAD;
		} else if (value >= 8) {
			return 3*CPUPerformanceEstimator.MAX_POINT_THREAD/4;
		} else if(value >= 4) {
			return CPUPerformanceEstimator.MAX_POINT_THREAD/2;
		} else {
			return 0;
		}
	}
	
	private double cacheSizePerformance(int value) {
		if(value >= 18) {
			return CPUPerformanceEstimator.MAX_POINT_CACHE;
		} else if (value >= 12) {
			return 3*CPUPerformanceEstimator.MAX_POINT_CACHE/4;
		} else if(value > 6) {
			return CPUPerformanceEstimator.MAX_POINT_CACHE/2;
		} else {
			return 0;
			
		}
	}

}

















