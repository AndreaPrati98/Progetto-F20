package model.component.constraint;

import java.util.List;
import model.component.Component;

public interface InterfaceConstraintChecker {

	List<Constraint> check(Component newComponent, List<Component> oldComponents);
}
