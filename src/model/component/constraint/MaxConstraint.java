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
	
	
	

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public ConstraintType getConstraintType() {
		return constraintType;
	}

	/**
	 * Check if this Constraint is compatible with the list of others constraints given
	 * 
	 * @param Constraint type:{@link Constraint}
	 * @return true if the component will respect the constraint,false if it will not respect the costraint
	 */	
	@Override
	public boolean checkList(List<Constraint> constraints) {
		
		String myName = this.name;
		double myValue = Double.parseDouble(this.value);
		double sum = 0;
		double max;
		//Prima controllo se il vincolo è interno o esterno
		
		if(this.constraintType == ConstraintType.EXTERNAL) {
			max = myValue;
			for(Constraint constr : constraints) {
				if(constr.getConstraintName().equals(myName) && constr.getConstraintType() == ConstraintType.INTERNAL) {
					sum = sum + Double.parseDouble(constr.getValue());
				}else if(constr.getConstraintName().equals(myName) && constr.getConstraintType() == ConstraintType.EXTERNAL) {
					//Se ci sono due vincoli esterni con lo stesso nome,
					//si tiene quello con il valore minore
					double hisMax = Double.parseDouble(constr.getValue());
					if(hisMax < max)
						max = hisMax;
				}
			}			
			
			if(max < sum) {
				return false;
			}
			
			return true;
		}else {
			//Il vincolo è interno
			max = 0;
			boolean firstTime = true;
			sum = sum + myValue;
			for(Constraint constr : constraints) {
				if(constr.getConstraintName().equals(myName)) {
					if(constr.getConstraintType() == ConstraintType.EXTERNAL){
						if(firstTime) {
							max = Double.parseDouble(constr.getValue());
							firstTime = false;
						}else if(max > Double.parseDouble(constr.getValue())) {
							max = Double.parseDouble(constr.getValue());
						}						
					} else {	
						//if constr is internal
						sum = sum + Double.parseDouble(constr.getValue());
					}
				}
			}
			
			if(max == 0)
				return true;
			
			if(max < sum) {
				return false;
			}
			
			return true;
		}		
	}

}
