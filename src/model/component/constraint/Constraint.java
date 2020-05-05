package model.component.constraint;

import java.util.List;

/**
 * 
 * @author Guglielmo Cassini
 *
 */
public interface Constraint {
	
	String getConstraintName();
	boolean checkList(List<Constraint> constraints);
	String getValue();
	ConstraintType getConstraintType();
	
}
