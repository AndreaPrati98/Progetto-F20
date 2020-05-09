package model.component.constraint;

import java.util.ArrayList;
import java.util.List;

import model.component.Component;


/**
 * @author Guglielmo
 * @author Irene S.
 *
 */
public class ConstraintChecker implements InterfaceConstraintChecker {

	public ConstraintChecker() {
	}

	@Override
	/**
	 * Creates a list containing every constraint already contained in old components,
	 * starting from an empty list
	 * @return {@link Constraint}
	 */
	public List<Constraint> check(Component newComponent, List<Component> oldComponents) {

		List<Constraint> constraintErrors = new ArrayList<Constraint>();
		List<Constraint> oldConstraints = new ArrayList<Constraint>();

		for (Component c : oldComponents) {
			List<Constraint> tmpConstraintList = c.getConstraints();
			oldConstraints.addAll(tmpConstraintList);
		}

		List<Constraint> listNewConstraint = newComponent.getConstraints();

		for (Constraint constr : listNewConstraint) {
			boolean correct = constr.checkList(oldConstraints);
			if (!correct) {
				constraintErrors.add(constr);
			}
		}
		return constraintErrors;

	}

}
