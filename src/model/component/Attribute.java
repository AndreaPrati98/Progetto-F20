package model.component;

import model.component.constraint.ConstraintCategory;

/**
 * 
 * @author Irene S
 * @author Andrea Prati
 *
 */
public class Attribute {
	private String name, value, constraintName;
	private boolean isBinding, isPresentable;
	private ConstraintCategory constraintCategory;
	
	//AGGIUNGERE CONSTRAINTNAME (come da db) e eventualmente togliere isBinding.
	//Di conseguemza va cambiato Component (a cui va aggiunto un metodo getAttributesByConstraintName)
	//e il ConstraintChecker, che non dovrà più usare name ma constraintName
	
	/**
	 * Constructor for not binding attributes, which has no constraintName
	 * @param name
	 * @param value
	 * @param isBinding
	 * @param isPresentable
	 */
	public Attribute(String name, String value, boolean isBinding, boolean isPresentable) {
		super();
		this.name = name;
		this.value = value;
		this.isBinding = isBinding;
		this.isPresentable = isPresentable;
	}
	
	/**
	 * Constructor for binding attribute, with a reference to an equals constraint
	 * @param name
	 * @param value
	 * @param constraintName
	 * @param isBinding
	 * @param isPresentable
	 */
	public Attribute(String name, String value, String constraintName, boolean isBinding, boolean isPresentable) {
		this(name, value, isBinding, isPresentable);
		this.constraintName=constraintName;
	}
	
	/**
	 * Constructor for binding attribute, with a reference to a dimension or max constraint
	 * @param name
	 * @param value
	 * @param constraintName
	 * @param isBinding
	 * @param isPresentable
	 * @param constraintCategory
	 */
	public Attribute(String name, String value, String constraintName, boolean isBinding, boolean isPresentable,
			String constraintCategory) {
		this(name, value, constraintName, isBinding, isPresentable);
		
		// L'ho fatto così se no avrei dovuto far vedere al CompoenntDAO l'enum ConstraintCategory
		for (ConstraintCategory elem : ConstraintCategory.values()) {
			String categoryElem = elem.name();
			if(categoryElem.equalsIgnoreCase(constraintCategory))
				this.constraintCategory = elem;
		}
		
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

	public String getConstraintName() {
		//Questo if mi serve se no ottengo NullPointerException
		if(constraintName==null) {
			return "";
		}
		return constraintName;
	}

	@Override
	public String toString() {
		return "\n\tAttribute:\n"
				+ "\t"+"name= " + name + "\n\t"+"value= " + value + "\n\t"+"isBinding= " + isBinding + "\n\t"+"isPresentable= "
				+ isPresentable + "\n\t"+"constraintCategory= " + constraintCategory + "\n\t"+"constraintName= "
				+ constraintName + "\n";
	}
	
	
	
}
