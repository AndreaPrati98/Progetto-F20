package main.temp;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test; // importante che sia org.junit.Test
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;

import main.model.configurator.constraint.MaxConstraint;

/**
 * 
 * @author Andrea
 *
 */

public class MaxConstraintTest {
	
	private static int c;
	
	@Before
	public void initialize() {
		c++;
		System.out.println(c);
	}
	
	@Test
	public void testConstructor() {
		MaxConstraint maxCon = new MaxConstraint("Prova");
		MaxConstraint expected = new MaxConstraint("Prova");
		assertTrue("Il costruttore non ha fuznionato", maxCon != null);
		assertSame("I due oggetti non puntano alla stessa referance", expected, maxCon);
	}
	
	@Test
	public void testEquals() {
		MaxConstraint maxCon = new MaxConstraint("Prova");
		MaxConstraint expected = new MaxConstraint("Prova");
		assertEquals(expected, maxCon, "I due oggetti non sono uguali");
	}
	
	@Test
	@Ignore
	public void testCheckList() {
		fail("Not yet implemented");
		
	}

	@Test
	@Ignore
	public void testMaxConstraint() {
		fail("Not yet implemented");
	}

	@Test
	@Ignore
	public void testAbstractConstraint() {
		fail("Not yet implemented");
	}

	@Test
	@Ignore
	public void testGetConstraintName() {
		fail("Not yet implemented");
	}

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

}
