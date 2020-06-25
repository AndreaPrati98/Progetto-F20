package main.model.configurator.component.performance;

import java.util.Map;

import main.model.configurator.component.Attribute;
/**
 * 
 * @author Irene Schimperna
 * @author Andrea Prati
 *
 */
public class GPUPerformanceEstimator implements InterfacePerformanceEstimator {

	private static final double MAX_POINT_FREQ = 25;
	private static final double MAX_POINT_CORE = 25;
	private static final double MAX_POINT_MEM_FREQ = 25;
	private static final double MAX_POINT_FRAMEBUFFER = 25;
	
	/*
	 * Considero i seguenti parametri:
	 * coreCount
	 * graphicCardMemoryFrequency
	 * graphicCardFrequency
	 * graphicCardFrameBuffer
	 */
	
	@Override
	public double computePerformance(Map<String, Attribute> componentAttributes) {
		Attribute att1 = componentAttributes.get("coreCount");
		Attribute att2 = componentAttributes.get("graphicCardFrequency");
		Attribute att3 = componentAttributes.get("graphicCardMemoryFrequency");
		Attribute att4 = componentAttributes.get("graphicCardFrameBuffer");
		
		if(att1 == null || att2 == null || att3 == null || att4 == null) {
			return -1;			
		}		
		
		int coreCount = -1;
		double freq = -1;
		double memFreq = -1;
		int frameBuffer = -1;
		
		try {
			coreCount = Integer.parseInt(att1.getValue());
			freq = Double.parseDouble(att2.getValue());
			memFreq = Double.parseDouble(att3.getValue());
			frameBuffer = Integer.parseInt(att4.getValue());
		
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return -1;
		}
		
		return corePerformance(coreCount) + frequencyPerformance(freq)
			+ memoryFreqPerformance(memFreq) + frameBufferPerformance(frameBuffer);
		
	}
	
	private double corePerformance(int value) {
		if(value > 1) {
			return GPUPerformanceEstimator.MAX_POINT_CORE;
		} else {
			return 0;
		}
	}
	
	private double frequencyPerformance(double value) {
		if(value > 1.5) {
			return GPUPerformanceEstimator.MAX_POINT_FREQ;
		} else if (value > 1) {
			return GPUPerformanceEstimator.MAX_POINT_FREQ/2;
		} else if (value > 0.5) {
			return GPUPerformanceEstimator.MAX_POINT_FREQ/4;
		} else {
			return 0;
		}
	}
	
	private double memoryFreqPerformance(double value) {
		if(value >= 6) {
			return GPUPerformanceEstimator.MAX_POINT_MEM_FREQ;
		} else if(value >= 4) {
			return 3*GPUPerformanceEstimator.MAX_POINT_MEM_FREQ/4;
		} else if (value >= 2) {
			return GPUPerformanceEstimator.MAX_POINT_MEM_FREQ/2;
		} else if (value >= 1) {
			return GPUPerformanceEstimator.MAX_POINT_MEM_FREQ/4;
		} else {
			return 0;
		}
	}
	
	private double frameBufferPerformance(int value) {
		if(value >= 4) {
			return GPUPerformanceEstimator.MAX_POINT_FRAMEBUFFER;
		} else if (value >= 2) {
			return GPUPerformanceEstimator.MAX_POINT_FRAMEBUFFER/2;
		} else {
			return 0;
		}
	}
}
