package main.model.configurator.component.performance;

import java.util.Map;

import main.model.configurator.component.Attribute;

public class MoboPerformanceEstimator implements InterfacePerformanceEstimator {
	private static final double MAX_POINT_RAM_TYPE = 17;
	private static final double MAX_POINT_RAM_SIZE = 17;
	private static final double MAX_POINT_RAM_FREQ = 17;
	private static final double MAX_POINT_STORAGE_PORT = 16;
	private static final double MAX_POINT_HDMI_PORT = 17;
	private static final double MAX_POINT_PCI_PORT = 16;
	
	/*
	 * Considero i seguenti parametri:
	 * ramType
	 * ramSize
	 * ramFrequency
	 * storagePortVersion
	 * hdmiPortsCount
	 * pciSlotCount
	 */
	
	
	@Override
	public double computePerformance(Map<String, Attribute> componentAttributes) {
		Attribute sizeAtt = componentAttributes.get("ramSize");
		Attribute typeAtt = componentAttributes.get("ramType");
		Attribute freqAtt = componentAttributes.get("ramMaxFrequency");
		Attribute storageAtt = componentAttributes.get("storagePortVersion");
		Attribute hdmiAtt = componentAttributes.get("hdmiPortsCount");
		Attribute pciAtt = componentAttributes.get("pciSlotCount");
		if(sizeAtt == null || typeAtt == null || freqAtt == null || storageAtt == null || hdmiAtt == null || pciAtt == null) {
			return -1;
		}
		
		String ramType = typeAtt.getValue();
		int ramSize;
		int ramFreq;
		String storagePortVersion = storageAtt.getValue();
		int hdmiPort;
		int pciPort;
		
		try {
			ramSize = Integer.parseInt(sizeAtt.getValue());
			ramFreq = Integer.parseInt(freqAtt.getValue());
			hdmiPort = Integer.parseInt(hdmiAtt.getValue());
			pciPort = Integer.parseInt(pciAtt.getValue());
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return -1;
		}
		
		return ramSizePerformance(ramSize)+ramTypePerformance(ramType)+ramFrequencyPerformance(ramFreq)+
				storagePortPerformance(storagePortVersion)+hdmiPortPerformance(hdmiPort)+pciPortPerformance(pciPort);
	}
	
	private double ramSizePerformance(int value) {
		if(value > 16) {
			return MAX_POINT_RAM_SIZE;
		} else if(value > 8) {
			return 3*MAX_POINT_RAM_SIZE/4;
		} else if(value > 4) {
			return MAX_POINT_RAM_SIZE/2;
		} else if(value > 2) {
			return MAX_POINT_RAM_SIZE/4;
		} else {
			return 0;
		}
	}
	
	private double ramTypePerformance(String value) {
		if(value.equalsIgnoreCase("DDR4")) {
			return MAX_POINT_RAM_TYPE;		
		} else if(value.equalsIgnoreCase("DDR3")) {
			return MAX_POINT_RAM_TYPE/2;
		} else {
			return 0;
		}
	}
	
	//Qui i valori sono un po' a caso
	private double ramFrequencyPerformance(int value) {
		if(value>=3600) {
			return MAX_POINT_RAM_FREQ;
		} else if(value >= 3000) {
			return 3*MAX_POINT_RAM_FREQ/4;
		} else if(value >= 2400) {
			return MAX_POINT_RAM_FREQ/2;
		} else if(value >= 1600) {
			return MAX_POINT_RAM_FREQ/4;
		} else {
			return 0;
		}
	}
	
	private double storagePortPerformance(String value) {
		if(value.equals("sata3")) {
			return MAX_POINT_STORAGE_PORT;
		} else {
			return 0;
		}
	}
	
	private double hdmiPortPerformance(int value) {
		if(value > 1) {
			return MAX_POINT_HDMI_PORT;
		} else if (value == 1) {
			return MAX_POINT_HDMI_PORT/2;
		} else {
			return 0;
		}
	}
	
	private double pciPortPerformance(int value) {
		if(value > 1) {
			return MAX_POINT_PCI_PORT;
		} else if (value == 1) {
			return MAX_POINT_PCI_PORT/2;
		} else {
			return 0;
		}
	}

}
