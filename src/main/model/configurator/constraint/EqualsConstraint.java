package main.model.configurator.constraint;

import java.util.List;

import main.model.configurator.component.Attribute;
import main.model.configurator.component.Component;
/**
 * 
 * @author Guglielmo
 *
 */
public class EqualsConstraint extends AbstractConstraint {
	
	/**
	 * 
	 * @param name
	 * @param value
	 */
	public EqualsConstraint(String name) {
		super(name);
	}

	/**
	 * Check if this Constraint is compatible with the list of others constraints given
	 * 
	 * @param Constraint type:{@link Constraint}
	 * @return true if the component will respect the constraint,false if it will not respect the costraint
	 */

	@Override
	public boolean checkList(List<Component> oldCheckedComponents, Component componentToCheck) {
		List<Attribute> oldAttributesAlreadyChecked = this.selectAttributeSameName(oldCheckedComponents);
		List<Attribute> newAttributesToCheck = this.selectAttributeSameName(componentToCheck);
				
		//Se una delle due liste è null, significa che una delle due non aveva componenti con attributi
		//che fossero da controllare da questo vincolo, quindi non può andare in conflitto con l'altra lista
		//quindi per questo vincolo è tutto ok
		if(oldAttributesAlreadyChecked == null || newAttributesToCheck == null) {
			return true;
		}
		//Devo fare un doppio ciclo for per iterare sulla lista dei nuovi attributi esternamente e poi
		//uno interno per iterare sulla lista di vecchi attributi.
		//Controllo i nuovi sui vecchi per ridurre le iterazioni necessarie
		//Arrivato a questo punto ho già filtrato gli attributi per nome relativo a questo vincolo, 
		//quindi devo controllare solo che abbiano valore diverso per dire che sono incompatibili
		for(Attribute newAttribute : newAttributesToCheck) {
			for(Attribute oldAttribute : oldAttributesAlreadyChecked) {
				if(!newAttribute.getValue().equals(oldAttribute.getValue())) {
					return false;	
				}
			}			
		}
		
		return true;
	}

}
