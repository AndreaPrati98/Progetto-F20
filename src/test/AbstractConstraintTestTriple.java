package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import main.model.configurator.component.Attribute;
import main.model.configurator.component.Component;
import main.model.configurator.constraint.AbstractConstraint;
import main.model.configurator.constraint.DimensionConstraint;
import main.model.configurator.constraint.EqualsConstraint;
import main.model.configurator.constraint.MaxConstraint;

/**
 * This class is used to test the implementation of the method checkList in {@link AbstractConstraint}.
 * In particular we want to test the method with 3 component.
 * @author Andrea
 *
 */
public class AbstractConstraintTestTriple {

	/**
	 * This is the oracle for cases that has to assert true with EqualsConstraint's 
	 * checkList method
	 * 
	 * @see EqualsConstraint
	 */
	private static ArrayList<Object[]> initializeAttributesForEqualsTrue() {
		
		ArrayList<Object[]> buff = new ArrayList<Object[]>();
		//NOTA BENE: la prima tupla e' quella che viene inserita come nuova!
		buff.add(new Object[] {"cpuSocket", "1200", "CpuSocket", true, true, null});
		buff.add(new Object[] {"cpuSocket", "1200", "CpuSocket", true, true, null});		
		buff.add(new Object[] {"cpuSocket", "1200", "CpuSocket", true, true, null});
		
		buff.add(new Object[] {"cpuSocket", "0", "CpuSocket", true, true, null});
		buff.add(new Object[] {"cpuSocket", "0", "CpuSocket", true, true, null});
		buff.add(new Object[] {"cpuSocket", "0", "CpuSocket", true, true, null});
		
		buff.add(new Object[] {"cpuSocket", "1000", "CpuSocket", true, true, null});
		buff.add(new Object[] {"cpuSocket", "1000", "CpuSocket", true, true, null});
		buff.add(new Object[] {"cpuSocket", "1000", "CpuSocket", true, true, null});
		
		return buff;		
	} 
	
	/**
	 * This is the oracle for cases that has to assert false with EqualsConstraint's 
	 * checkList method
	 * 
	 * @see EqualsConstraint
	 */
	private static ArrayList<Object[]> initializeAttributesForEqualsFalse() {
		
		ArrayList<Object[]> buff = new ArrayList<Object[]>();
		//NOTA BENE: la prima tupla e' quella che viene inserita come nuova!		
		buff.add(new Object[] {"cpuSocket", "1200", "CpuSocket", true, true, null});
		buff.add(new Object[] {"cpuSocket", "1200", "CpuSocket", true, true, null});
		buff.add(new Object[] {"cpuSocket", "1201", "CpuSocket", true, true, null});
		
		buff.add(new Object[] {"cpuSocket", "5", "CpuSocket", true, true, null});
		buff.add(new Object[] {"cpuSocket", "0", "CpuSocket", true, true, null});
		buff.add(new Object[] {"cpuSocket", "5", "CpuSocket", true, true, null});

		buff.add(new Object[] {"cpuSocket", "800", "CpuSocket", true, true, null});
		buff.add(new Object[] {"cpuSocket", "500", "CpuSocket", true, true, null});
		buff.add(new Object[] {"cpuSocket", "800", "CpuSocket", true, true, null});

		return buff;		
	} 
	
	/** 
	 * This is the oracle for cases that has to assert true with DimensionConstraint's 
	 * checkList method
	 * 
	 * @see DimensionConstraint
	 */
	private static ArrayList<Object[]> initializeAttributesForDimensionTrue() {
		
		ArrayList<Object[]> buff = new ArrayList<Object[]>();
		//NOTA BENE: la prima tupla e' quella che viene inserita come nuova!
		buff.add(new Object[] {"ramSize", "16", "RamSize", true, true, "external"});
		buff.add(new Object[] {"ramSize", "8", "RamSize", true, true, "internal"});
		buff.add(new Object[] {"ramSize", "8", "RamSize", true, true, "internal"});

		/*
		 * Questo test ci ricorda che gli elementi agginti prima devono rispettare gia i vincoli
		 * perche non verranno ricontrollati 
		 */
		buff.add(new Object[] {"ramSize", "32", "RamSize", true, true, "external"});
		buff.add(new Object[] {"ramSize", "16", "RamSize", true, true, "external"});
		buff.add(new Object[] {"ramSize", "17", "RamSize", true, true, "internal"});

		buff.add(new Object[] {"ramSize", "8", "RamSize", true, true, "internal"});
		buff.add(new Object[] {"ramSize", "16", "RamSize", true, true, "external"});		
		buff.add(new Object[] {"ramSize", "8", "RamSize", true, true, "internal"});
		
		/*
		 * Se vengono aggiunti dei constraint che hanno un nome diverso da quello che viene controllato
		 * non vengono considerati e viene restituito true
		 */
		buff.add(new Object[] {"ciccioPasticcio", "16", "ciao", true, true, "internal"});
		buff.add(new Object[] {"lollipop", "8", "ciao", true, true, "external"});
		buff.add(new Object[] {"IDK", "8", "ciao", true, true, "external"});
				
		return buff;		

	}
	
	/** 
	 * This is the oracle for cases that has to assert false with DimensionConstraint's 
	 * checkList method
	 * 
	 * @see DimensionConstraint
	 */
	private static ArrayList<Object[]> initializeAttributesForDimensionFalse() {
		ArrayList<Object[]> buff = new ArrayList<Object[]>();
		//NOTA BENE: la prima tupla e' quella che viene inserita come nuova!
		buff.add(new Object[] {"ramSize", "4", "RamSize", true, true, "external"});		
		buff.add(new Object[] {"ramSize", "8", "RamSize", true, true, "external"});
		buff.add(new Object[] {"ramSize", "16", "RamSize", true, true, "internal"});
		
		buff.add(new Object[] {"ramSize", "16", "RamSize", true, true, "internal"});
		buff.add(new Object[] {"ramSize", "4", "RamSize", true, true, "external"});		
		buff.add(new Object[] {"ramSize", "16", "RamSize", true, true, "external"});
		
		return buff;
	}
	
	/**
	 * This is the oracle for cases that has to assert true with MaxConstraint's 
	 * checkList method
	 * 
	 * @see MaxConstraint
	 */
	private static ArrayList<Object[]> initializeAttributesForMaxTrue(){
		ArrayList<Object[]> buff = new ArrayList<Object[]>();
		//NOTA BENE: la prima tupla e quella che viene inserita come nuova!
		buff.add(new Object[] {"power", "750", "Power", true, true, "external"});
		buff.add(new Object[] {"power", "65", "Power", true, true, "internal"});
		buff.add(new Object[] {"power", "600", "Power", true, true, "internal"});
		
		buff.add(new Object[] {"power", "10", "Power", true, true, "internal"});
		buff.add(new Object[] {"power", "75", "Power", true, true, "external"});
		buff.add(new Object[] {"power", "65", "Power", true, true, "internal"});
		
		buff.add(new Object[] {"power", "10", "Power", true, true, "internal"});
		buff.add(new Object[] {"power", "65", "Power", true, true, "internal"});
		buff.add(new Object[] {"power", "75", "Power", true, true, "external"});
			
		return buff;
	}
	
	/**
	 * This is the oracle for cases that has to assert false with MaxConstraint's 
	 * checkList method
	 * 
	 * @see MaxConstraint
	 */
	private static ArrayList<Object[]> initializeAttributesForMaxFalse(){
		ArrayList<Object[]> buff = new ArrayList<Object[]>();
		//NOTA BENE: la prima tupla e' quella che viene inserita come nuova!
		buff.add(new Object[] {"power", "750", "Power", true, true, "internal"});
		buff.add(new Object[] {"power", "65", "Power", true, true, "internal"});
		buff.add(new Object[] {"power", "65", "Power", true, true, "external"});
		
		buff.add(new Object[] {"power", "65", "Power", true, true, "internal"});
		buff.add(new Object[] {"power", "650", "Power", true, true, "external"});		
		buff.add(new Object[] {"power", "700", "Power", true, true, "internal"});		
		
		buff.add(new Object[] {"power", "10", "Power", true, true, "internal"});
		buff.add(new Object[] {"power", "65", "Power", true, true, "internal"});
		buff.add(new Object[] {"power", "74", "Power", true, true, "external"});

		return buff;				
	}
		
	/**
	 * Support method for the creation of a List of Attributes, strating by a List of Objects
	 * @param attBuff - List of Objects to convert into List of Attributes
	 * @return List of attributes that has are usable 3 by 3
	 */
	private static ArrayList<Attribute> createAttributesTriple(ArrayList<Object[]> attBuff) {
		
		ArrayList<Attribute> attributesList = new ArrayList<Attribute>(); 
	
		if(attBuff.size() % 3 == 0) {
			//ATTENZIONE, LA VARIABILE 'i' VIENE INCREMENTATA DI 3 AD OGNI CICLO
			for(int i = 0; i < attBuff.size(); i += 3) {
				
				int j = i+1;
				int k = i+2;

				String name1 = (String)attBuff.get(i)[0];
				String value1 = (String)attBuff.get(i)[1]; 
				String constraintName1 = (String)attBuff.get(i)[2];
				boolean isBinding1 = (boolean)attBuff.get(i)[3];
				boolean isPresentable1 = (boolean)attBuff.get(i)[4];
				String constraintCategory1 = (String)attBuff.get(i)[5];				
				Attribute newAtt = new Attribute(name1, value1, constraintName1, isBinding1, isPresentable1, constraintCategory1);
				
				String name2 = (String)attBuff.get(j)[0];
				String value2 = (String)attBuff.get(j)[1]; 
				String constraintName2 = (String)attBuff.get(j)[2];
				boolean isBinding2 = (boolean)attBuff.get(j)[3];
				boolean isPresentable2 = (boolean)attBuff.get(j)[4];
				String constraintCategory2 = (String)attBuff.get(j)[5];				
				Attribute oldAtt = new Attribute(name2, value2, constraintName2, isBinding2, isPresentable2, constraintCategory2);

				String name3 = (String)attBuff.get(k)[0];
				String value3 = (String)attBuff.get(k)[1]; 
				String constraintName3 = (String)attBuff.get(k)[2];
				boolean isBinding3 = (boolean)attBuff.get(k)[3];
				boolean isPresentable3 = (boolean)attBuff.get(k)[4];
				String constraintCategory3 = (String)attBuff.get(k)[5];				
				Attribute oldAtt2 = new Attribute(name3, value3, constraintName3, isBinding3, isPresentable3, constraintCategory3);
								
				attributesList.add(oldAtt);
				attributesList.add(oldAtt2);
				attributesList.add(newAtt);
			}
		} else {
			System.out.println("La lista di oggetti deve contenere un numero di oggetti multiplo di 3");
		}
		return attributesList;
	}

	@Test 
	public void trueTestCheckListOnEquals() {
		ArrayList<Object[]> attBuff = AbstractConstraintTestTriple.initializeAttributesForEqualsTrue();
		ArrayList<Attribute> attributeList = AbstractConstraintTestTriple.createAttributesTriple(attBuff);

		EqualsConstraint constraint  = new EqualsConstraint("CpuSocket");
		
		for (int i = 0; i < attributeList.size(); i += 3) {
			
			HashMap<String, Attribute> attributesMap1 = new HashMap<String, Attribute>();
			HashMap<String, Attribute> attributesMap2 = new HashMap<String, Attribute>();
			HashMap<String, Attribute> attributesMap3 = new HashMap<String, Attribute>();
			
			Attribute att1 = attributeList.get(i);
			Attribute att2 = attributeList.get(i+1);
			Attribute att3 = attributeList.get(i+2);
			
			attributesMap1.put(att1.getName(), att1);
			attributesMap2.put(att2.getName(), att2);
			attributesMap3.put(att3.getName(), att3);
			
			Component alreadyCheckedComp1 = new Component("old1", "cpu", 16, attributesMap1);
			Component alreadyCheckedComp2 = new Component("old2", "cpu", 16, attributesMap2);
			
			ArrayList<Component> oldCheckedComponents = new ArrayList<Component>();
			oldCheckedComponents.add(alreadyCheckedComp1);
			oldCheckedComponents.add(alreadyCheckedComp2);
			
			Component componentToCheck = new Component("new", "mobo", 14, attributesMap3);
			
			assertTrue("Non viene restituito true al giro: "+ i/3, constraint.checkList(oldCheckedComponents, componentToCheck));
		}
	}

	@Test 
	public void falseTestCheckListOnEquals() {
		ArrayList<Object[]> attBuff = AbstractConstraintTestTriple.initializeAttributesForEqualsFalse();
		ArrayList<Attribute> attributeList = AbstractConstraintTestTriple.createAttributesTriple(attBuff);

		EqualsConstraint constraint  = new EqualsConstraint("CpuSocket");
		
		for (int i = 0; i < attributeList.size(); i += 3) {
			
			HashMap<String, Attribute> attributesMap1 = new HashMap<String, Attribute>();
			HashMap<String, Attribute> attributesMap2 = new HashMap<String, Attribute>();
			HashMap<String, Attribute> attributesMap3 = new HashMap<String, Attribute>();
			
			Attribute att1 = attributeList.get(i);
			Attribute att2 = attributeList.get(i+1);
			Attribute att3 = attributeList.get(i+2);
			
			attributesMap1.put(att1.getName(), att1);
			attributesMap2.put(att2.getName(), att2);
			attributesMap3.put(att3.getName(), att3);
			
			Component alreadyCheckedComp1 = new Component("old1", "cpu", 16, attributesMap1);
			Component alreadyCheckedComp2 = new Component("old2", "cpu", 16, attributesMap2);
			
			ArrayList<Component> oldCheckedComponents = new ArrayList<Component>();
			oldCheckedComponents.add(alreadyCheckedComp1);
			oldCheckedComponents.add(alreadyCheckedComp2);
			
			Component componentToCheck = new Component("new", "mobo", 14, attributesMap3);
			
			assertFalse("Non viene restituito false al giro: "+ i/3, constraint.checkList(oldCheckedComponents, componentToCheck));
		}
	}
	
	@Test
	public void trueTestCheckListOnDimension() {
		ArrayList<Object[]> attBuff = AbstractConstraintTestTriple.initializeAttributesForDimensionTrue();
		ArrayList<Attribute> attributeList = AbstractConstraintTestTriple.createAttributesTriple(attBuff);

		DimensionConstraint constraint = new DimensionConstraint("RamSize");
		
		for (int i = 0; i < attributeList.size(); i += 3) {
			
			HashMap<String, Attribute> attributesMap1 = new HashMap<String, Attribute>();
			HashMap<String, Attribute> attributesMap2 = new HashMap<String, Attribute>();
			HashMap<String, Attribute> attributesMap3 = new HashMap<String, Attribute>();
			
			Attribute att1 = attributeList.get(i);
			Attribute att2 = attributeList.get(i+1);
			Attribute att3 = attributeList.get(i+2);
			
			attributesMap1.put(att1.getName(), att1);
			attributesMap2.put(att2.getName(), att2);
			attributesMap3.put(att3.getName(), att3);
			
			Component alreadyCheckedComp1 = new Component("old1", "cpu", 16, attributesMap1);
			Component alreadyCheckedComp2 = new Component("old2", "cpu", 16, attributesMap2);
			
			ArrayList<Component> oldCheckedComponents = new ArrayList<Component>();
			oldCheckedComponents.add(alreadyCheckedComp1);
			oldCheckedComponents.add(alreadyCheckedComp2);
			
			Component componentToCheck = new Component("new", "mobo", 14, attributesMap3);
			
			assertTrue("Non viene restituito true al giro: "+ i/3, constraint.checkList(oldCheckedComponents, componentToCheck));
		}
	}

	@Test
	public void falseTestCheckListOnDimension() {
		ArrayList<Object[]> attBuff = AbstractConstraintTestTriple.initializeAttributesForDimensionFalse();
		ArrayList<Attribute> attributeList = AbstractConstraintTestTriple.createAttributesTriple(attBuff);
		
		DimensionConstraint constraint = new DimensionConstraint("RamSize");
		
		for (int i = 0; i < attributeList.size(); i += 3) {
			
			HashMap<String, Attribute> attributesMap1 = new HashMap<String, Attribute>();
			HashMap<String, Attribute> attributesMap2 = new HashMap<String, Attribute>();
			HashMap<String, Attribute> attributesMap3 = new HashMap<String, Attribute>();
			
			Attribute att1 = attributeList.get(i);
			Attribute att2 = attributeList.get(i+1);
			Attribute att3 = attributeList.get(i+2);
			
			attributesMap1.put(att1.getName(), att1);
			attributesMap2.put(att2.getName(), att2);
			attributesMap3.put(att3.getName(), att3);
			
			Component alreadyCheckedComp1 = new Component("old1", "cpu", 16, attributesMap1);
			Component alreadyCheckedComp2 = new Component("old2", "cpu", 16, attributesMap2);
			
			ArrayList<Component> oldCheckedComponents = new ArrayList<Component>();
			oldCheckedComponents.add(alreadyCheckedComp1);
			oldCheckedComponents.add(alreadyCheckedComp2);
			
			Component componentToCheck = new Component("new", "mobo", 14, attributesMap3);

			assertFalse("Non viene restituito false al giro: "+ i/3, constraint.checkList(oldCheckedComponents, componentToCheck));
		}
	}

	@Test
	public void trueTestCheckListOnMax() {
		ArrayList<Object[]> attBuff = AbstractConstraintTestTriple.initializeAttributesForMaxTrue();
		ArrayList<Attribute> attributeList = AbstractConstraintTestTriple.createAttributesTriple(attBuff);

		MaxConstraint constraint = new MaxConstraint("Power");
		
		for (int i = 0; i < attributeList.size(); i += 3) {
			
			HashMap<String, Attribute> attributesMap1 = new HashMap<String, Attribute>();
			HashMap<String, Attribute> attributesMap2 = new HashMap<String, Attribute>();
			HashMap<String, Attribute> attributesMap3 = new HashMap<String, Attribute>();
			
			Attribute att1 = attributeList.get(i);
			Attribute att2 = attributeList.get(i+1);
			Attribute att3 = attributeList.get(i+2);
			
			attributesMap1.put(att1.getName(), att1);
			attributesMap2.put(att2.getName(), att2);
			attributesMap3.put(att3.getName(), att3);
			
			Component alreadyCheckedComp1 = new Component("old1", "cpu", 16, attributesMap1);
			Component alreadyCheckedComp2 = new Component("old2", "cpu", 16, attributesMap2);
			
			ArrayList<Component> oldCheckedComponents = new ArrayList<Component>();
			oldCheckedComponents.add(alreadyCheckedComp1);
			oldCheckedComponents.add(alreadyCheckedComp2);
			
			Component componentToCheck = new Component("new", "mobo", 14, attributesMap3);
			
			assertTrue("Non viene restituito true al giro: "+i/3, constraint.checkList(oldCheckedComponents, componentToCheck));
		}

	}
	
	@Test
	public void falseTestCheckListOnMax() {
		ArrayList<Object[]> attBuff = AbstractConstraintTestTriple.initializeAttributesForMaxFalse();
		ArrayList<Attribute> attributeList = AbstractConstraintTestTriple.createAttributesTriple(attBuff);

		MaxConstraint constraint = new MaxConstraint("Power");
		
		for (int i = 0; i < attributeList.size(); i += 3) {
			
			HashMap<String, Attribute> attributesMap1 = new HashMap<String, Attribute>();
			HashMap<String, Attribute> attributesMap2 = new HashMap<String, Attribute>();
			HashMap<String, Attribute> attributesMap3 = new HashMap<String, Attribute>();
			
			Attribute att1 = attributeList.get(i);
			Attribute att2 = attributeList.get(i+1);
			Attribute att3 = attributeList.get(i+2);
			
			attributesMap1.put(att1.getName(), att1);
			attributesMap2.put(att2.getName(), att2);
			attributesMap3.put(att3.getName(), att3);
			
			Component alreadyCheckedComp1 = new Component("old1", "cpu", 16, attributesMap1);
			Component alreadyCheckedComp2 = new Component("old2", "cpu", 16, attributesMap2);
			
			ArrayList<Component> oldCheckedComponents = new ArrayList<Component>();
			oldCheckedComponents.add(alreadyCheckedComp1);
			oldCheckedComponents.add(alreadyCheckedComp2);
			
			Component componentToCheck = new Component("new", "mobo", 14, attributesMap3);
			
			assertFalse("Non viene restituito false al giro: "+ i/3, constraint.checkList(oldCheckedComponents, componentToCheck));
		}

	}
	
	
}
