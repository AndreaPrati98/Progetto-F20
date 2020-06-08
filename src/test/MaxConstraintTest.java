package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.Ignore;
import org.junit.Test; // importante che sia org.junit.Test

import main.model.configurator.constraint.MaxConstraint;

/**
 * 
 * @author Andrea
 *
 */


public class MaxConstraintTest {
	
	@Test
	public void testConstructor() {
		MaxConstraint maxCon = new MaxConstraint("Prova");
		assertTrue("Il costruttore non ha fuznionato", maxCon != null);
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
