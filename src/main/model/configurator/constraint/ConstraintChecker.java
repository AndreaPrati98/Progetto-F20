package main.model.configurator.constraint;

import java.util.ArrayList;
import java.util.List;

import main.model.configurator.component.Component;
import main.services.persistence.PersistenceFacade;
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
	public List<AbstractConstraint> check(Component newComponent, List<Component> oldComponents) {
//		List<Constraint> constraintErrors = new ArrayList<Constraint>();
//		
//		//Creo una lista totale di tutti i vincoli già presenti nelle vecchie componenti
//		//facendo una append alla lista iniziale vuota
//		List<Constraint> oldConstraints = new ArrayList<Constraint>();
//		
//		for(Component c : oldComponents) {
//			List<Constraint> tmpConstraintList =  c.getConstraints();
//			oldConstraints.addAll(tmpConstraintList);
//		}			
//		
//		//Passo a 
//		List<Constraint> listNewConstraint =  newComponent.getConstraints();
//		
//		for(Constraint constr : listNewConstraint){
//			boolean correct = constr.checkList(oldConstraints);
//			if(!correct) {
//				//return false
//				constraintErrors.add(constr);
//			}
//		}		
		//return true
		
		PersistenceFacade facade = PersistenceFacade.getIstance();
		List<AbstractConstraint> listOfConstraints = facade.getAllConstraints();
		List<AbstractConstraint> listOfConstraintsThatAreViolated = new ArrayList<AbstractConstraint>();
		
		for(AbstractConstraint constraint : listOfConstraints) {
			if(!constraint.checkList(oldComponents, newComponent))
				listOfConstraintsThatAreViolated.add(constraint);
		}		
		
		if(listOfConstraintsThatAreViolated.isEmpty()) {
			System.out.println("Tutti i constraint sono rispettati");
		} else {
			for(AbstractConstraint con : listOfConstraintsThatAreViolated) {
				System.out.println("Violato constraint "+con.getConstraintName());
			}
		}

		
		return listOfConstraintsThatAreViolated;		
	}
	
	@Override
	public boolean checkIfComplete(List<Component> components) {
		PersistenceFacade pf = PersistenceFacade.getIstance();
		List<String> neededComponents = pf.getNeededComponents();
		if(neededComponents==null) {
			System.out.println("Errore nella lettura da db");
			return false;
		}
		for(String neededElem : neededComponents) {
			if(!(checkIfComponentIsPresent(neededElem, components))) {
				return false;
			}
		}
		return true;
	}
	
	private boolean checkIfComponentIsPresent(String typeComp, List<Component> components) {
		for(Component c : components) {
			if(c.getTypeComponent().equals(typeComp)) {
				return true;
			}
		}
		return false;
	}

	
	
	
}
