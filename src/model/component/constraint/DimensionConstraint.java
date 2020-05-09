package model.component.constraint;


import java.util.List;

import model.component.Component;

/**
 * 
 * @author Capici Alessandro
 *
 */
public class DimensionConstraint implements Constraint {

	private String name;
	private String value;
	private ConstraintType constraintType;

	/**
	 * 
	 * @param name
	 * @param value
	 * @param constraintType {@link ConstraintType}
	 */
	public DimensionConstraint(String name, String value, ConstraintType constraintType) {
		super();
		this.name = name;
		this.value = value;
		this.constraintType = constraintType;
	}
	
	@Override
	public String getConstraintName() {
		// TODO Auto-generated method stub
		return name;
	}

	/**
	 * 
	 */
	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return value;
	}

	@Override
	public ConstraintType getConstraintType() {
		// TODO Auto-generated method stub
		return constraintType;
	}

	/**
	 * Vede se il nuovo componente e conforme alle compatibilità ,controllando prima
	 * se deve essere inserito o deve ospitare e poi maggiorandolo o minorandolo
	 * rispetto agli opportuni valori massimi o minimi.
	 * 
	 * @param component type:{@link Component}
	 * @return true if the component will respect the constraint,false if it will not respect the costraint
	 */
	@Override
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
	}
}
