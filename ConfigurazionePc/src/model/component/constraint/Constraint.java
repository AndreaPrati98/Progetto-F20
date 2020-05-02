package model.component.constraint;
import model.component.*;

import java.util.List;

/**
 * 
 * @author Guglielmo Cassini
 *
 */
public interface Constraint {

	String getConstraintName();
	boolean checkList(List<Component> components);
	String getValue();
	ConstraintType getConstraintType();
	
}
