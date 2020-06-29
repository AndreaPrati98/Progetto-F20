package main.model.configurator.constraint;

import java.util.List;


import main.model.configurator.component.Attribute;
import main.model.configurator.component.Component;

/**
 * Constraint where there is a value that must be greater than the sum of the other values.
 * 
 * @author Irene Schimperna
 *
 */

public class MaxConstraint extends AbstractConstraint {
	
	public MaxConstraint(String name) {
		super(name);
	}

	/**
	 * Check if this Constraint is compatible with the list of others constraints given
	 * 
	 * @return true if the component will respect the constraint,false if it will not respect the costraint
	 */	
	@Override
	public boolean checkList(List<Component> oldCheckedComponents, Component componentToCheck) {
		List<Attribute> oldAttributesAlreadyChecked = this.selectAttributeSameName(oldCheckedComponents);
		List<Attribute> newAttributesToCheck = this.selectAttributeSameName(componentToCheck);
		
		//Se una delle due liste è null, significa che una delle due non aveva componenti con attributi
		//che fossero da controllare da questo vincolo, quindi non può andare in conflitto con l'altra lista
		//quindi per questo vincolo è tutto ok
		if(oldAttributesAlreadyChecked == null || newAttributesToCheck == null)
			return true;
		
		//Metto insieme le due liste perchè i max constraint vanno controllati sulla totalità degli attributi
		//della confugurazione
		List<Attribute> attributesToCheck = oldAttributesAlreadyChecked;
		attributesToCheck.addAll(newAttributesToCheck);
		
		//Filtro per dividerli in internal e external
		List<Attribute> internalAttributesFilteredList = this.filterAttributesList(attributesToCheck, ConstraintCategory.INTERNAL);
		List<Attribute> externalAttributesFilteredList = this.filterAttributesList(attributesToCheck, ConstraintCategory.EXTERNAL);
		
		
		//Per prima cosa controllo che nessuna delle due lista sia nulla, poichè in tal caso sicuramente
		//i componenti sono compatibili poichè o non esistono ancora internal o non esistono external
				
		if(internalAttributesFilteredList.isEmpty() || externalAttributesFilteredList.isEmpty())
			return true;
	
		//Cerco il valore massimizzatore, che e' il minimo tra tutti gli external
		double maxValue = Double.parseDouble(externalAttributesFilteredList.get(0).getValue());
		for(Attribute attribute : externalAttributesFilteredList){
		   double value = Double.parseDouble(attribute.getValue());
			if(value < maxValue)
				maxValue = value;
		}		
		
		//Ora ciclo su tutti gli internal per capire se la loro somma rispetta il massimizzatore
		double sum = 0.0;
		for(Attribute internalAttribute : internalAttributesFilteredList){
			double internalValue = Double.parseDouble(internalAttribute.getValue());
			sum += internalValue;
		}
		
		if(sum > maxValue)
			return false; 
		
		return true;
	}

	@Override
	public boolean equals(Object obj) {
		boolean areEquals = false;
		
		if(this.getConstraintName() == ((MaxConstraint)obj).getConstraintName())
			areEquals = true;
		
		return areEquals;
	}
	
	

	

}
