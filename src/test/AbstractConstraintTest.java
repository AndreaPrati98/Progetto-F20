package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import main.model.configurator.component.Attribute;
import main.model.configurator.component.Component;
import main.model.configurator.constraint.DimensionConstraint;

/**
 * 
 * @author Andrea
 * @author Irene R.
 *
 */

public class AbstractConstraintTest {

	//private static MaxConstraint max;
	//private static DimensionConstraint dim;
	//private static EqualsConstraint equ;
	
	//Con questi valori creo un Component
	//private String model;
	//private String typeOfComponent;
	//private double price;
	//private Map<String, Attribute> attributeMap;
	
	public static List<Component> oldComp;
	public static Component newComp;
	
	public static int contatore = 0;
	
	public AbstractConstraintTest() {
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * name, value, caonstrName, is binding, isPresentable, constraintType
	 */
	
	public void initializeEqualsAttributes() {
		/*
		 * ci serve un esempio di Max, uno di Equals e uno di Dimension.
		 */
		
		//Credo che per il max sia necessario usare più gruppi di questo tipo.
		Object[][] max = new Object[][] {
			{"power", "", "Power", true, true, "internal"},
			{"power", "", "Power", true, true, "external"},
			{"power", "", "Power", true, true, "internal"},
			{"power", "", "Power", true, true, "internal"},
		};
			
	}
	
	private static ArrayList<Object[]> initializeEqualsList() {
		/*
		 * Costruisco così la lista, almeno creo un Component con un 
		 * attributo solo e riesco a provare il funzionamento dei singoli constraint.
		 * Qui posso sfruttare il fatto che non ho internal ed external per
		 * "interlacciare" gli attribti.
		 */
		
		ArrayList<Object[]> buff = new ArrayList<Object[]>();
		
		buff.add(new Object[] {"cpuSocket", "1200", "CpuSocket", true, true});
		buff.add(new Object[] {"cpuSocket", "1200", "CpuSocket", true, true});
		buff.add(new Object[] {"cpuSocket", "1000", "CpuSocket", true, true});
		buff.add(new Object[] {"cpuSocket", "800", "CpuSocket", true, true});
		buff.add(new Object[] {"cpuSocket", "1000", "CpuSocket", true, true});

		return buff;		
	} 
	
	private static ArrayList<Object[]> initializeDimensionList() {
		
		/*
		 * Costruisco così la lista, almeno creo un Component con un 
		 * attributo solo e riesco a provare il funzionamento dei singoli constraint.
		 * Qui dovrò andare di due in due per la creazione dei component (poichè ho internal
		 * ed external)
		 */
		
		ArrayList<Object[]> buff = new ArrayList<Object[]>();
		
		buff.add(new Object[] {"ramSize", "16", "RamSize", true, true, "external"});
		buff.add(new Object[] {"ramSize", "8", "RamSize", true, true, "internal"});
		buff.add(new Object[] {"ramSize", "8", "RamSize", true, true, "external"});
		buff.add(new Object[] {"ramSize", "16", "RamSize", true, true, "internal"});
		buff.add(new Object[] {"ramSize", "8", "RamSize", true, true, "external"});
		buff.add(new Object[] {"ramSize", "16", "RamSize", true, true, "internal"});
		
		return buff;		
	}


	/**
	 * @param attBuff
	 * @return attriuteList - elements goes 2 by 2
	 * 
	 */
	private static ArrayList<Attribute> createTwoAttribute(ArrayList<Object[]> attBuff) {
		
		ArrayList<Attribute> attributesList = new ArrayList<Attribute>(); 
		for(int i = 0; i < attBuff.size() - 1; i++) {
			for(int j = 1; j < attBuff.size(); j++) {
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
				
				//System.out.println(value1);
				//System.out.println(value2);				
				
				attributesList.add(newAtt);
				attributesList.add(oldAtt);
			}
		}
		return attributesList;
	}
	
	@Test
	public void simpleTestOnDimension() {

//		ArrayList<Object[]> attBuff = AbstractConstraintTest.initializeDimensionList();
//		ArrayList<Attribute> attributesList = AbstractConstraintTest.createTwoAttribute(attBuff);
		DimensionConstraint constraint = new DimensionConstraint("RamSize");

		Attribute att1 = new Attribute("ramSize", "13", "RamSize", true, true, "internal");
		Attribute att2 = new Attribute("ramSize", "10", "RamSize", true, true, "external");
		
		HashMap<String, Attribute> attributesMap1 = new HashMap<String, Attribute>();
		HashMap<String, Attribute> attributesMap2 = new HashMap<String, Attribute>();
		
		attributesMap1.put(att1.getName(), att1);
		attributesMap2.put(att2.getName(), att2);
		
		Component oldCheckedComponentsSingle = new Component("old", "cpu", 16, attributesMap1);
		ArrayList<Component> oldCheckedComponents = new ArrayList<Component>();
		oldCheckedComponents.add(oldCheckedComponentsSingle);
		
		Component componentToCheck = new Component("new", "mobo", 14, attributesMap2);
		
		assertTrue(constraint.checkList(oldCheckedComponents, componentToCheck));
		
	}
	
	@Test
	public void testCheckListDimensionConstraint() {
	
		ArrayList<Object[]> attBuff = AbstractConstraintTest.initializeDimensionList();
		ArrayList<Attribute> attributesList = AbstractConstraintTest.createTwoAttribute(attBuff);
		DimensionConstraint constraint = new DimensionConstraint("RamSize");
		
		//ATTENZIONE, INCREMENTO i DI DUE OGNI VOLTA
		for (int i = 0; i < attributesList.size(); i += 2) {
			Map<String, Attribute> map1 = new HashMap<String, Attribute>();
			Map<String, Attribute> map2 = new HashMap<String, Attribute>();
			
			Attribute att1 = attributesList.get(i);
			Attribute att2 = attributesList.get(i+1);
		
			//System.out.println(att1.getValue());
			//System.out.println(att2.getValue());
			
			map1.put(att1.getName(), att1);
			map2.put(att2.getName(), att2);
			
			Component c = new Component("oldOne", map1);
			Component componentToCheck = new Component("newOne", map2);			
			
			ArrayList<Component> oldCheckedComponents = new ArrayList<Component>();
			oldCheckedComponents.add(c);
			
			System.out.println("old " + oldCheckedComponents.get(0).getAttributeByName("ramSize").getValue() +" "+oldCheckedComponents.get(0).getAttributeByName("ramSize").getConstraintCategory());		
			System.out.println("toCheck "+ componentToCheck.getAttributeByName("ramSize").getValue() +" "+ componentToCheck.getAttributeByName("ramSize").getConstraintCategory());
			System.out.println();
			assertTrue(constraint.checkList(oldCheckedComponents, componentToCheck));
			
		}
		
	}
	
	@Test
	public void testCheckListEqualsConstraint() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testCheckListMaxConstraint() {
		fail("Not yet implemented");
	}
	
/*
	@Test
	@Ignore
	public void testSelectAttributeSameNameListOfComponent() {
		fail("Not yet implemented");
	}

	@Test
	@Ignore
	public void testSelectAttributeSameNameComponent() {
		fail("Not yet implemented");
	}

	@Test
	@Ignore
	public void testFilterAttributesList() {
		fail("Not yet implemented");
	}
*/
}
