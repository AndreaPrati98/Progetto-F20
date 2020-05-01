package model.configuration;

import java.util.ArrayList;
import java.util.List;

import model.component.Component;
import model.component.constraint.Constraint;

//Frenkli Buzhiqi, Alessandro Capici

public class Configuration {
	private List<String> neededComponents; // lista di elementi obbligatori per far si che un pc si possa accendere
											// (Lista da stabilire)
	private List<Component> addedComponents;

	public Configuration(List<String> neededComponents) {
		this.neededComponents = neededComponents;
		addedComponents = new ArrayList<Component>();
	}

	public boolean addComponent(Component c) {
		if (check(c)) {
			addedComponents.add(c);// aggiunta del componente scelto nella lista dei componenti solo se compatibile
			return true;
		} else {
			return false;
		}
	}

	private boolean check(Component c) { // controllo compatibilità delle componenti
		boolean flag = true; // flag per salvare true o false in base alla compatibilità del componente con
								// i vincoli
		List<Constraint> listConstraint = c.getConstraints();
		for (Constraint constraint : listConstraint) {
			if (!constraint.checkList(addedComponents)) {
				flag = false; // se anche solo un vincolo non è rispettato esco dal ciclo restituendo false
				break;
			}
		}
		return flag;
	}

	public boolean checkConf() { // metodo per controllare se la mia configurazione permette di creare un pc
									// funzionante
		boolean flag = false; // salvero' true se la mia configurazione funziona, false altrimenti
		if (addedComponents.size() >= neededComponents.size()) { // se il # dei componenti aggiunti è < del # di quelli
			for (String nc : neededComponents) { // con il doppio ciclo controllo se gli elementi dell'inventario
				for (Component ac : addedComponents) { // sono tutti quelli obbligatori
					if (nc.equals(ac.getTypeComponent())) {
						flag = true;
						break;
					}
				}
				if (!flag) {
					return flag;
				}
				flag = false;
			}
			flag = true;
			return flag;

		}
		return flag;
	}

	public List<String> getNeededComponents() {
		return neededComponents;
	}

	public List<Component> getAddedComponents() {
		return addedComponents;
	}

	@Override
	public String toString() {
		return "Configuration [addedComponents=" + addedComponents + "]";
	}

}
