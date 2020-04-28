package model.component.constraint;

import java.util.List;

import model.component.Component;

//Schimperna Irene
public class MaxConstraint implements Constraint {
	private String name;
	private double maxValue;
	
	public MaxConstraint(String name, double maxValue) {
		super();
		this.name = name;
		this.maxValue = maxValue;
	}
	
	@Override
	public String getConstraintName() {
		// TODO Auto-generated method stub
		return this.name;
	}
	
	//Questo tipo di vincolo controlla che la potenza dell'alimentatore sia sufficiente
	//e di non avere più RAM rispetto agli slot della scheda madre
	//Vincolo rispettato -> true
	//Vincolo non rispettato -> false
	@Override
	public boolean checkList(List<Component> components) {
		String attributeToCheck;
		if(name.equals("Power")) {
			attributeToCheck = "Absorption";	//l'assorbimento elettrico si chiama così in inglese??
		} else if(name.equals("Maximum RAM slot")) {
			attributeToCheck = "RAM slot";
		} else {
			//ERRORE
			//Un vincolo di massimo può ricadere solo in uno dei due casi sopra
			return false;
		}
		//Calcolo il valore da confrontare con il massimo
		Double sum = 0.0;
		for(Component c : components) {
			String val = c.getAttributesMap().get(attributeToCheck);	//Se il componente non specifica un attributo del tipo voluto questo comando ritorna null
			if(val!=null) {
				Double valD = Double.parseDouble(val);
				sum = sum + valD;
			}
		}
		if(sum<=maxValue) {
			return true;
		} else {
			return false;
		}
	}

}
