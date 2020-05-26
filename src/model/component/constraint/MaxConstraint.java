package model.component.constraint;


import java.util.List;

import model.component.Attribute;
import model.component.Component;

/**
 * 
 * @author Irene Schimperna
 *
 */

public class MaxConstraint extends AbstractConstraint {
	private String name;
	
	public MaxConstraint(String name) {
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
		
		//Se una delle due liste � null, significa che una delle due non aveva componenti con attributi
		//che fossero da controllare da questo vincolo, quindi non pu� andare in conflitto con l'altra lista
		//quindi per questo vincolo � tutto ok
		if(oldAttributesAlreadyChecked == null || newAttributesToCheck == null)
			return true;
		

		//Filtro per dividerli in liste
		List<Attribute> internalAttributesFilteredList = this.filterAttributesList(oldAttributesAlreadyChecked, ConstraintCategory.INTERNAL);
		List<Attribute> externalAttributesFilteredList = this.filterAttributesList(oldAttributesAlreadyChecked, ConstraintCategory.EXTERNAL);
		
		
		//Per prima cosa controllo che nessuna delle due lista sia nulla, poich� in tal caso sicuramente
		//i componenti sono compatibili poich� o non esistono ancora internal o non esistono external
				
		if(internalAttributesFilteredList == null || externalAttributesFilteredList == null)
			return true;
		
		
		//La prima cosa che faccio � cercare qual � il vero massimizzatore prendendo il valore pi� basso
		//tra gli n massimi.
		//Per farlo ciclo su tutti gli external (sia nuovi che vecchi) per cercare il vero 
		//massimizzatore. Se il massimizzatore non c� ritorno true, quindi faccio subito questo controllo
		
		//La newAttributesToCheck � una lista, ma in realt� in un componente non posso avere  un 
		//attribute che � sia external che un internal per lo stesso MaxConstraint.
		//Di conseguenza quella lista ha un solo elemento
		
		Attribute newAttribute = newAttributesToCheck.get(0);
		
		//Copio le liste filtrate dei vecchi componenti in delle nuove (potrebbe essere inutile)
		List<Attribute> listWhereToFindTheMax =  externalAttributesFilteredList;
		List<Attribute> listOfInternalToCheckTheMax =  internalAttributesFilteredList;
		 
		//Devo capire dove collocare il nuovo attribute del nuovo componente
		if(newAttribute.getConstraintCategory() == ConstraintCategory.EXTERNAL)
			listWhereToFindTheMax.add(newAttribute);
		else if(newAttribute.getConstraintCategory() == ConstraintCategory.INTERNAL)
			listOfInternalToCheckTheMax.add(newAttribute);
			
		//Cerco l'attributo massimizzatore
		Attribute maximizzatorAttribute = listWhereToFindTheMax.get(0);
		
		for(Attribute attribute : listWhereToFindTheMax){
		   double value = Double.parseDouble(attribute.getValue());
		   double maxValue = Double.parseDouble(maximizzatorAttribute.getValue());
		   //Devo prendere il min(max1,max2)
			if(value < maxValue)
				maximizzatorAttribute = attribute;
		}		
		
		//Ora ho trovato il vero massimizzatore
		//Ora ciclo su tutti gli internal per capire se rispettano il massimizzatore
		double maxValue = Double.parseDouble(maximizzatorAttribute.getValue());
		double sum = 0.0;
		for(Attribute internalAttribute : listOfInternalToCheckTheMax){
			double internalValue = Double.parseDouble(internalAttribute.getValue());
			sum += internalValue;
		}
		
		if(sum > maxValue)
			return false; 
		
		return true;
	}

	

}
