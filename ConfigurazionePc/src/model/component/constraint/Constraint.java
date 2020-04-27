package model.component.constraint;
import model.component.*;

import java.util.List;

//GC
public interface Constraint {

	String getConstraintName();
	boolean checkList(List<Component> components);
	
}
