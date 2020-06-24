package main.model.configurator.constraint;


import java.util.ArrayList;
import java.util.List;

import main.model.configurator.component.Attribute;
import main.model.configurator.component.Component;

/**
 * 
 * @author Capici Alessandro
 *
 */
public class DimensionConstraint extends AbstractConstraint {


	/**
	 * 
	 * @param name
	 * @param value
	 * @param constraintType {@link ConstraintType}
	 */
	public DimensionConstraint(String name) {
		super(name);
	}
	
	@Override
	public boolean checkList(List<Component> oldCheckedComponents, Component componentToCheck) {
		List<Attribute> oldAttributesAlreadyChecked = this.selectAttributeSameName(oldCheckedComponents);
		List<Attribute> newAttributesToCheck = this.selectAttributeSameName(componentToCheck);
		
		//Se una delle due liste � null, significa che una delle due non aveva componenti con attributi
		//che fossero da controllare da questo vincolo, quindi non pu� andare in conflitto con l'altra lista
		//quindi per questo vincolo � tutto ok
		if(oldAttributesAlreadyChecked == null || newAttributesToCheck == null) {
			return true;
		}
		//Dovro ciclare su liste di attributi diversi in base al fatto che il nuovo attributo 
		//che sto controllando sia internal od external
		List<Attribute> internalAttributesFilteredList = this.filterAttributesList(oldAttributesAlreadyChecked, ConstraintCategory.INTERNAL);
		List<Attribute> externalAttributesFilteredList = this.filterAttributesList(oldAttributesAlreadyChecked, ConstraintCategory.EXTERNAL);
		// Anche se una delle due liste e' vuota devo lo stesso fare i controlli. Infatti se 
		//prima avevo solo attributi external, nei nuovi attributi potrei avere un internal che viola
		//il vincolo, anche se la lista degli internal che c'erano da prima e' vuota
		
		for(Attribute newAttribute : newAttributesToCheck) {
			//Eseguo un ciclo interno diverso a seconda della categoria dell'attribute
			
			//Se il newAttribute � internal deve essere minore di tutti gli external
			if(newAttribute.getConstraintCategory() == ConstraintCategory.INTERNAL) {
				
				for(Attribute oldExternalAttribute : externalAttributesFilteredList) {
					double newInternalValue = Double.parseDouble(newAttribute.getValue()); 
					double oldExternalValue = Double.parseDouble(oldExternalAttribute.getValue()); 
					
					if(newInternalValue > oldExternalValue) {
						return false;	
					}
				}			
			}
			//Se il newAttribute � external deve essere maggiore di tutti gli intenal
			if(newAttribute.getConstraintCategory() == ConstraintCategory.EXTERNAL) {
				for(Attribute oldInternalAttribute : internalAttributesFilteredList) {
					double newExternallValue = Double.parseDouble(newAttribute.getValue()); 
					double oldInternalValue = Double.parseDouble(oldInternalAttribute.getValue()); 
					
					if(oldInternalValue > newExternallValue) {
						return false;
					}					
					
				}			
			}
		}
		
		return true;
	}
}
