package model.component.constraint;

import java.util.List;

/**
 * 
 * @author Guglielmo Cassini
 *
 */
public interface Constraint {
	
	/**
	 * The method to get the name of the object that implements the
	 * constraint interface.
	 * 
	 * @return the name of the constraint
	 */	
	String getConstraintName();
	/**
	 * Check that the Constraint given as parameters are all compatible
	 * with the contraint that is executing this method.
	 * 
	 * @param constraints - lista ({@link List}) of constraints that we want to check each others
	 * 
	 * @return true - if are all compatibles. <br>
	 * 			false - if there is at least one that's not compatible.
	 */
	boolean checkList(List<Constraint> constraints);
	String getValue();
	ConstraintType getConstraintType();
	
}
