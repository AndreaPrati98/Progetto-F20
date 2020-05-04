package model.component.constraint;

import java.util.List;
import model.component.Component;

public interface InterfaceConstraintChecker {

	boolean check(Component newComponent, List<Component> oldComponents);
}
