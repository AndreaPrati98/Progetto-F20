package main.model.configurator.constraint;

import java.util.ArrayList;
import java.util.List;

import main.model.configurator.component.Component;
import main.services.persistence.PersistenceFacade;
/**
 * This class is an implementation of {@link InterfaceConstraintChecker}
 * 
 * @author Guglielmo Cassini
 * @author Irene Schimperna
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
		
		PersistenceFacade facade = PersistenceFacade.getIstance();
		List<AbstractConstraint> listOfConstraints = facade.getAllConstraints();
		List<AbstractConstraint> listOfConstraintsThatAreViolated = new ArrayList<AbstractConstraint>();
		
		for(AbstractConstraint constraint : listOfConstraints) {
			if(!constraint.checkList(oldComponents, newComponent))
				listOfConstraintsThatAreViolated.add(constraint);
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
	
	/**
	 * 
	 * @param typeComp - type of component that we are looking for
	 * @param components - List of components in which
	 * @return
	 */
	private boolean checkIfComponentIsPresent(String typeComp, List<Component> components) {
		for(Component c : components) {
			if(c.getTypeComponent().equals(typeComp)) {
				return true;
			}
		}
		return false;
	}

	
	
	
}
