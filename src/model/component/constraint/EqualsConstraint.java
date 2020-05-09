package model.component.constraint;

import java.util.List;

/**
 * 
 * @author Guglielmo
 *
 */
public class EqualsConstraint implements Constraint {

	private String name;
	private String value;
	
	/**
	 * 
	 * @param name
	 * @param value
	 */
	public EqualsConstraint(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}

	@Override
	public String getConstraintName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return value;
	}

	@Override
	public ConstraintType getConstraintType() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Check that every constraint with the same name have the same value
	 * @param the list of constraints that it has to respect
	 * @return true if the component will respect the constraint,false if it will
	 *         not respect the costraint
	 */
	@Override
	public boolean checkList(List<Constraint> constraints) {
		String myName = this.name;
		String myValue = this.value;
		
		for(Constraint constr : constraints) {
			if(constr.getConstraintName().equals(myName)){
				if(!constr.getValue().equals(myValue)){
					return false;
				}					
			}						
		}		
				
		return true;
	}


}
