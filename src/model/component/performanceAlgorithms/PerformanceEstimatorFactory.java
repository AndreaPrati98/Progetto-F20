package model.component.performanceAlgorithms;

public class PerformanceEstimatorFactory {

	public InterfacePerformanceEstimator getPerformanceEstimator(String typeOfComponent) {
		
		if(typeOfComponent.equalsIgnoreCase("CPU")) {
			return new CPUPerformanceEstimator();
			
		} else if (typeOfComponent.equalsIgnoreCase("ram")) {
			return new RamPerformanceEstimator();
			
		}  else if (typeOfComponent.equalsIgnoreCase("mobo")) {
			return new MoboPerformanceEstimator();
			
		}  else if (typeOfComponent.equalsIgnoreCase("gpu")) {
			return new GPUPerformanceEstimator();
			
		}  else if (typeOfComponent.equalsIgnoreCase("cooler")) {
			return new CoolerPerformanceEstimator();
			
		}  else if (typeOfComponent.equalsIgnoreCase("power")) {
			return new PowerSupplyPerformanceEstimator();
			
		}  else if (typeOfComponent.equalsIgnoreCase("case")) {
			return new CasePerformanceEstimator();
			
		}  else if (typeOfComponent.equalsIgnoreCase("massStorage")) {
			return new MassStoravePerformanceEstimator();
			
		}  else {
			return null;
		
		} 
	}
	
}
