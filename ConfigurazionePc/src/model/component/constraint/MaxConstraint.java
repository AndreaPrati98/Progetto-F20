package model.component.constraint;

import java.util.List;

import model.component.Component;

/**
 * 
 * @author Irene Schimperna
 *
 */

public class MaxConstraint implements Constraint {
	private String name;
	private String value;
	private ConstraintType constraintType;
	
	public MaxConstraint(String name, String value, ConstraintType constraintType) {
		super();
		this.name = name;
		this.value = value;
		this.constraintType = constraintType;
	}
	
	@Override
	public String getConstraintName() {
		return this.name;
	}
	
	/**
	 * Se questo vincolo è esterno controllo che la somma dei valori tutti i vincoli interni 
	 * dello stesso tipo sia minore o uguale del valore di questo vincolo.
	 * Se questo vincolo è interno calcolo la somma di tutti i vincoli interni di questo tipo,
	 * compreso questo. Se c'è un vincolo esterno di questo tipo confronto il valore della somma
	 * trovata con il valore del vincolo esterno.
	 * 
	 * @return true if the component will respect the constraint,false if it will
	 *         not respect the costraint
	 */
	@Override
	public boolean checkList(List<Component> components) {
		Double sum = 0.0;
		List<Constraint> constraintList;
		//distinguo i casi in base a se questo vincolo è interno o esterno
		if(this.constraintType.equals(ConstraintType.EXTERNAL)) {
			for(Component c : components) {
				constraintList = c.getConstraints();
				for(Constraint constraint : constraintList) {
					if(constraint.getConstraintName().equals(this.name) && constraint.getConstraintType().equals(ConstraintType.INTERNAL)) {
						sum = sum + Double.parseDouble(constraint.getValue());
					}
				}
			}
			if(sum<=Double.parseDouble(value)) {
				return true;
			} else {
				return false;
			}
		} else if(this.constraintType.equals(ConstraintType.INTERNAL)) {
			sum = sum + Double.parseDouble(value);
			Constraint externalConstraint=null;
			for(Component c : components) {
				constraintList = c.getConstraints();
				for(Constraint constraint : constraintList) {
					if(constraint.getConstraintName().equals(name)) {
						if(constraint.getConstraintType().equals(ConstraintType.EXTERNAL)) {
							externalConstraint = constraint;
							//suppongo che al massimo ci possa essere un vincolo esterno di ogni tipo
						} else {	//if constraint is internal
							sum = sum + Double.parseDouble(constraint.getValue());
						}
					}
				}
			}
			if(externalConstraint==null) {
				return true;	//non ci sono vincoli esterni di questo tipo
			}
			if(sum<=Double.parseDouble(externalConstraint.getValue())) {
				return true;
			} else {
				return false;
			}
		}
		// Non dovrei mai arrivare qui
		return false;
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public ConstraintType getConstraintType() {
		return constraintType;
	}

}
