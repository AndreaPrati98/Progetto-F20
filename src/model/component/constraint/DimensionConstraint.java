package model.component.constraint;


import java.util.List;

import model.component.Attribute;
import model.component.Component;

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
	
//	@Override
//	public String getConstraintName() {
//		// TODO Auto-generated method stub
//		return name;
//	}

	/**
	 * Check if this Constraint is compatible with the list of others constraints given
	 * 
	 * @param Constraint type:{@link Constraint}
	 * @return true if the component will respect the constraint,false if it will not respect the costraint
	 */
	/*@Override
	public boolean checkList(List<Constraint> constraints) {

		String myName = this.name;
		double myValue = Double.parseDouble(this.value);
		
		if(this.constraintType == ConstraintType.EXTERNAL) {
			//Se sono esterno controllo tutti gli interni
			//con lo stesso nome			
			for(Constraint constr : constraints) {
				if(constr.getConstraintName().equals(myName) && constr.getConstraintType() == ConstraintType.INTERNAL) {
					
					double hisValue = Double.parseDouble(constr.getValue());
					if(hisValue > myValue){
						return false;
					}
				}
			}
			
			return true;
		}else {
			//Se sono interno controllo tutti gli esterni con lo stesso nome
			//e controllo che devo essere più piccolo di tutti loro.
			for(Constraint constr : constraints) {
				if(constr.getConstraintName().equals(myName) && constr.getConstraintType() == ConstraintType.EXTERNAL) {
					
					double hisValue = Double.parseDouble(constr.getValue());
					if(hisValue < myValue){
						return false;
					}
				}
			}	
			
			return true;
		}
	}*/

	@Override
	public boolean checkList(List<Component> oldCheckedComponents, Component componentToCheck) {
		List<Attribute> oldAttributesAlreadyChecked = this.selectAttributeSameName(oldCheckedComponents);
		List<Attribute> newAttributesToCheck = this.selectAttributeSameName(componentToCheck);
		
		//Se una delle due liste è null, significa che una delle due non aveva componenti con attributi
		//che fossero da controllare da questo vincolo, quindi non può andare in conflitto con l'altra lista
		//quindi per questo vincolo è tutto ok
		if(oldAttributesAlreadyChecked == null || newAttributesToCheck == null)
			return true;
		
		//Dovro ciclare su liste di attributi diversi in base al fatto che il nuovo attributo 
		//che sto controllando sia internal od external
		List<Attribute> internalAttributesFilteredList = this.filterAttributesList(oldAttributesAlreadyChecked, ConstraintCategory.INTERNAL);
		List<Attribute> externalAttributesFilteredList = this.filterAttributesList(oldAttributesAlreadyChecked, ConstraintCategory.EXTERNAL);
		
		//Per prima cosa controllo che nessuna delle due lista sia nulla, poichè in tal caso sicuramente
		//i componenti sono compatibili poichè o non esistono ancora internal o non esistono external
		
		if(internalAttributesFilteredList == null || externalAttributesFilteredList == null)
			return true;
		
		for(Attribute newAttribute : newAttributesToCheck) {
			//Eseguo un ciclo interno diverso a seconda della categoria dell'attribute
			
			//Se il newAttribute è internal deve essere minore di tutti gli external
			if(newAttribute.getConstraintCategory() == ConstraintCategory.INTERNAL) {
				for(Attribute oldExternalAttribute : externalAttributesFilteredList) {
					double newInternalValue = Double.parseDouble(newAttribute.getValue()); 
					double oldExternalValue = Double.parseDouble(oldExternalAttribute.getValue()); 
					
					if(newInternalValue > oldExternalValue)
						return false;	
					
				}			
			}
			//Se il newAttribute è external deve essere maggiore di tutti gli intenal
			if(newAttribute.getConstraintCategory() == ConstraintCategory.EXTERNAL) {
				for(Attribute oldInternalAttribute : internalAttributesFilteredList) {
					double newExternallValue = Double.parseDouble(newAttribute.getValue()); 
					double oldInternalValue = Double.parseDouble(oldInternalAttribute.getValue()); 
					
					if(oldInternalValue > newExternallValue)
						return false;
										
					
				}			
			}
		}
		
	
		return true;
	}
}
