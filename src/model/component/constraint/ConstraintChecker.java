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
	/**Creo una lista totale di tutti i vincoli già presenti nelle vecchie componenti
	 * facendo una append alla lista iniziale vuota
	 * @return {@link Constraint}
	 */
	public List<Constraint> check(Component newComponent, List<Component> oldComponents) {

		List<Constraint> constraintErrors = new ArrayList<Constraint>();
		List<Constraint> oldConstraints = new ArrayList<Constraint>();

		for (Component c : oldComponents) {
			List<Constraint> tmpConstraintList = c.getConstraints();
			oldConstraints.addAll(tmpConstraintList);
		}

		// Passo a
		List<Constraint> listNewConstraint = newComponent.getConstraints();

		for (Constraint constr : listNewConstraint) {
			boolean correct = constr.checkList(oldConstraints);
			if (!correct) {
				// return false
				constraintErrors.add(constr);
			}
		}
		// return true
		return constraintErrors;

	}

}
