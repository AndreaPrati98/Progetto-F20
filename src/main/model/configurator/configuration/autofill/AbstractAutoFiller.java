package main.model.configurator.configuration.autofill;

import java.util.ArrayList;
import java.util.List;

import main.model.configurator.ComponentCatalog;
import main.model.configurator.component.Component;
import main.model.configurator.constraint.AbstractConstraint;
import main.model.configurator.constraint.ConstraintChecker;

public abstract class AbstractAutoFiller {
	
	
	abstract List<Component> completeConfiguration(List<Component> alreadyInside);
	
	/**
	 * 
	 * @param type of component
	 * @param alreadyInside
	 * @return all the component in the catalog of the given type that are compatible with 
	 * the components that are already inside the configuration
	 */
	protected List<Component> getCompatibleComponents(String type, List<Component> alreadyInside) {
		List<Component> compatibleComp = new ArrayList<>();
		ComponentCatalog compCat = new ComponentCatalog();
		List<Component> allComp = compCat.getComponentList();
		ConstraintChecker cc = new ConstraintChecker();
		
		//Valutare se aggungere nel catalog un metodo che ritorni solo le componenti di un certo tipo
		for(Component comp : allComp) {
			if(comp.getTypeComponent().equals(type)) {
				List<AbstractConstraint> violatedConstraint = cc.check(comp, alreadyInside);
				if(violatedConstraint.isEmpty()) {
					compatibleComp.add(comp);
				}
			}
		}
		
		
		return compatibleComp;
	}
}
