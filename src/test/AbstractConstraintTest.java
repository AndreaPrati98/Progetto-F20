package test;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import main.model.configurator.component.Attribute;
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
		
		return Arrays.asList(data);
	}
	
	@Before
	public void initialize() {
		Map<String, Attribute> attributeMap = new HashMap<String, Attribute>();
		Attribute att1 = new Attribute(name1, value1, constraintName1, isBinding1, isPresentable1, constraintCategory1);
		Attribute att2 = new Attribute(name2, value2, constraintName2, isBinding2, isPresentable2, constraintCategory2);
		attributeMap.put(name1, att1);
		this.oldComp = new ArrayList<Component>();
		this.newComp = new Component(""+contatore, ""+contatore, 54, attributeMap);
	}
	
	@Test
	void testCheckList() {
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
