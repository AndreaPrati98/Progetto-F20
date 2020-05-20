package model.component.performanceAlgorithms;

import java.util.Map;

/**
 * 
 * @author Andrea
 * @author Irene S.
 *
 */
public class CPUPerformanceEstimator implements InterfacePerformanceEstimator {

	/* Abbiamo diviso così in base a quanti attributi consideriamo
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
	 * 
	 * se qualcuno avesse altre idee sono ben accette
	 * 
	 */
	
	/**
	 *
	 */
	@Override
	public double computePerformance(Map<String, String> componentAttributes) throws NullPointerException {
		
		
		// Controllo giusto per sicurezza
		if(componentAttributes == null) {
			throw new NullPointerException("Invalid instance of componentAttributes");
		}
		
		String ramType = componentAttributes.get("ramType");
		double clock = Double.parseDouble(componentAttributes.get("cpuFrequency"));
		int numberOfCore; // non ce l'abbiamo ancora nel JSON
		int numberOfThread; // non ce l'abbiamo ancora nel JSON		
		int cacheSize;
		
		try {
			clock = Double.parseDouble(componentAttributes.get("cpuFrequency"));
			numberOfCore = Integer.parseInt(componentAttributes.get("numberOfCore")); 
			numberOfThread = Integer.parseInt(componentAttributes.get("numberOfThread")); 
			cacheSize = Integer.parseInt(componentAttributes.get("cacheSize"));
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
			/*
			 * Ritorno valore negativo perchè non ho abbastanza info per calcolare un
			 * indice di performance
			 */
			return -1;
			
		}
		
		boolean isRamNull = ramType == null;
		if (isRamNull) {
			return -1;
		}
		
		double index = 0;
		ramType = ramType.toUpperCase();
		if(ramType.equals("DDR4")) {
			index += CPUPerformanceEstimator.MAX_POINT_RAM_TYPE;
			
		} else if(ramType.equals("DDR3")) {
			index += CPUPerformanceEstimator.MAX_POINT_RAM_TYPE/2;
			
		} else {
			index += 0;
		}
		
		if(clock >= 4.0) {
			index += CPUPerformanceEstimator.MAX_POINT_CLOCK;
			
		} else if (clock > 3.0) {
			index += CPUPerformanceEstimator.MAX_POINT_CLOCK/2;
			
		} else if (clock > 2.0) {
			index += CPUPerformanceEstimator.MAX_POINT_CLOCK/3;
		
		} else if(clock <= 1) {
			index += 0;
		
		} else {
			index += CPUPerformanceEstimator.MAX_POINT_CLOCK/4;
		} 
		
		if(numberOfCore > 6) {
			index += CPUPerformanceEstimator.MAX_POINT_CORE;
		
		} else if (numberOfCore >= 4) {
			index += 3*CPUPerformanceEstimator.MAX_POINT_CORE/4;
		
		} else if(numberOfCore >= 2) {
			index += CPUPerformanceEstimator.MAX_POINT_CORE/2;
		
		} else {
			index += CPUPerformanceEstimator.MAX_POINT_CORE/5;
			
		}
		
		if(numberOfThread != 0) {
			index += CPUPerformanceEstimator.MAX_POINT_THREAD;
		
		}
		
		if(cacheSize >= 18) {
			index += CPUPerformanceEstimator.MAX_POINT_CACHE;
			
		} else if (cacheSize >= 12) {
			index += 3*CPUPerformanceEstimator.MAX_POINT_CACHE/4;
		
		} else if(cacheSize < 12 && cacheSize > 6) {
			index += CPUPerformanceEstimator.MAX_POINT_CACHE/2;
			
		} else {
			index += 0;
			
		}
		
		double schimpa = index;
		return schimpa;
	}

}

















