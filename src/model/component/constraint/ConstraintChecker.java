package model.component.constraint;

import java.util.ArrayList;
import java.util.List;

import model.component.Component;
/**
 * @author Guglielmo Cassini
 * @author Irene Schimperna
 * 
 * The ConstraintChekcker is the object that extrapolates the Constraints from a list of
 * given components that are already compatible each others and check if the constraints 
 * from a new component are compatible with the list.
 * 
 */
public class ConstraintChecker implements InterfaceConstraintChecker {
	
	public ConstraintChecker() {
	}
	/**
	 * 
	 * @return List<Contraints> the list of the Constraints that are not respected from
	 * the new component with the previous components.
	 */
	@Override
	public List<Constraint> check(Component newComponent, List<Component> oldComponents) {

		List<Constraint> constraintErrors = new ArrayList<Constraint>();
		
		//Creo una lista totale di tutti i vincoli già presenti nelle vecchie componenti
		//facendo una append alla lista iniziale vuota
		List<Constraint> oldConstraints = new ArrayList<Constraint>();
		
		for(Component c : oldComponents) {
			List<Constraint> tmpConstraintList =  c.getConstraints();
			oldConstraints.addAll(tmpConstraintList);
		}			
		
		//Passo a 
		List<Constraint> listNewConstraint =  newComponent.getConstraints();
		
		for(Constraint constr : listNewConstraint){
			boolean correct = constr.checkList(oldConstraints);
			if(!correct) {
				//return false
				constraintErrors.add(constr);
			}
		}		
		//return true
		return constraintErrors;
		
		
	}

	
	
	
}
