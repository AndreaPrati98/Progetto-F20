package model.component;

import model.component.constraint.ConstraintCategory;

/**
 * 
 * @author Irene S
 * @author Andrea Prati
 *
 */
public class Attribute {
	private String name, value;
	private boolean isBinding, isPresentable;
	private ConstraintCategory constraintCategory;
	
	
	
	public Attribute(String name, String value, boolean isBinding, boolean isPresentable) {
		super();
		this.name = name;
		this.value = value;
		this.isBinding = isBinding;
		this.isPresentable = isPresentable;
	}

	public Attribute(String name, String value, boolean isBinding, boolean isPresentable,
			ConstraintCategory constraintCategory) {
		this(name, value, isBinding, isPresentable);
		this.constraintCategory = constraintCategory;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isBinding() {
		return isBinding;
	}

	public void setBinding(boolean isBinding) {
		this.isBinding = isBinding;
	}

	public boolean isPresentable() {
		return isPresentable;
	}

	public void setPresentable(boolean isPresentable) {
		this.isPresentable = isPresentable;
	}

	public ConstraintCategory getConstraintCategory() {
		return constraintCategory;
	}

	public void setConstraintCategory(ConstraintCategory constraintCategory) {
		this.constraintCategory = constraintCategory;
	}

	@Override
	public String toString() {
		return "\n\tAttribute:\n"
				+ "\t"+"name= " + name + "\n\t"+"value= " + value + "\n\t"+"isBinding= " + isBinding + "\n\t"+"isPresentable= "
				+ isPresentable + "\n\t"+"constraintCategory= " + constraintCategory + "\n";
	}
	
	
	
}
