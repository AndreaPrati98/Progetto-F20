package test;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import main.model.configurator.component.Component;

/**
 * 
 * @author Andrea
 * @author Irene R.
 *
 */

@RunWith(Parameterized.class)
class AbstractConstraintTest {

	//private static MaxConstraint max;
	//private static DimensionConstraint dim;
	//private static EqualsConstraint equ;
	
	//Con questi valori creo un Attribute
	@Parameter(0)
	public String name1;
	@Parameter(1)
	public String value1;
	@Parameter(2)
	public String constraintName1;
	@Parameter(3)
	public boolean isBinding1;
	@Parameter(4)
	public boolean isPresentable1;
	@Parameter(5)
	public String constraintCategory1;

	@Parameter(6)
	public String name2;
	@Parameter(7)
	public String value2;
	@Parameter(8)
	public String constraintName2;
	@Parameter(9)
	public boolean isBinding2;
	@Parameter(10)
	public boolean isPresentable2;
	@Parameter(11)
	public String constraintCategory2;

	
	//Con questi valori creo un Component
	//private String model;
	//private String typeOfComponent;
	//private double price;
	//private Map<String, Attribute> attributeMap;
	
	public static List<Component> oldComp;
	public static Component newComp;
	
	public static int contatore = 0;
	
	/*
	 * Noi dobbiamo testare il metodo checklist, ci serve
	 * un array diverso per ogni tipo di constraint. 
	 *
	public void daButtare() {
		Component c0 = new Component(model, typeOfComponent, price, attributeMap);	
	}
	 */
	
	@Parameters
	public static Collection<Object[]> data() {
		Object[][] data = new Object[][] {
			{"cpuSocket", "1200", "CpuSocket", true, true, null,
			"cpuSocket", "1200", "CpuSocket", true, true, null},
		};
		//List<Object[]> c = Arrays.asList(data);
		return Arrays.asList(data);
	}
	
	/**
	 * This method is used to initialize some binding Attributes  
	 */
	@BeforeClass
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
		buff.add(new Object[] {"ramSize", "8", "RamSize", true, true, "internal"});
		
		return buff;		
	}
	
	@Test
	void testCheckListMaxConstraint() {
		fail("Not yet implemented");
	}
	
	@Test
	void testCheckListDimensionConstraint() {
		fail("Not yet implemented");
	}
	
	@Test
	void testCheckListEqualsConstraint() {
		fail("Not yet implemented");
	}

	@Test
	@Ignore
	void testSelectAttributeSameNameListOfComponent() {
		fail("Not yet implemented");
	}

	@Test
	@Ignore
	void testSelectAttributeSameNameComponent() {
		fail("Not yet implemented");
	}

	@Test
	@Ignore
	void testFilterAttributesList() {
		fail("Not yet implemented");
	}

}
