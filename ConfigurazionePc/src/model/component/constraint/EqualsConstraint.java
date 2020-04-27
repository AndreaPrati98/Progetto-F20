package model.component.constraint;

import java.util.List;

import model.component.Component;
//Cic
public class EqualsConstraint implements Constraint {

	private String name;
	private String value;

	public EqualsConstraint(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}

	@Override
	public String getNomeVincolo() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public boolean checkList(List<Component> components) {
		// TODO Auto-generated method stub
		
		for(Component comp : components) {
			
			List<Constraint> constraints = comp.getConstraints();
			
			
			
		}
		
		
		
		return false;
	}

}
