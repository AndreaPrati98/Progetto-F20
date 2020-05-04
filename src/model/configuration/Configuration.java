package model.configuration;

import java.util.ArrayList;
import java.util.List;

import model.component.Component;
import model.component.constraint.ConstraintChecker;
import model.component.constraint.InterfaceConstraintChecker;

/**
 * @author Capici Alessandro ,Frenkli Buzhiqi
 * */

public class Configuration {
	private List<String> neededComponents; // lista di elementi obbligatori per far si che un pc si possa accendere
											// (Lista da stabilire)
	private List<Component> addedComponents;
	/**
	 * 
	 * @param neededComponents
	 */
	public Configuration(List<String> neededComponents) {
		this.neededComponents = neededComponents;
		addedComponents = new ArrayList<Component>();
	}
	/**
	 * aggiunta del componente scelto nella lista dei componenti solo se compatibile
	 * 
	 * @param c type:{@link Component} Component that you would like to add.
	 * @return true if the component have been added,false if the component haven't been added
	 * @see Component 
	 * 
	 * */
	public boolean addComponent(Component c) {
		if (check(c)) {
			addedComponents.add(c);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * se anche solo un vincolo non è rispettato esco dal ciclo restituendo false 
	 * @param c type:{@link Component}
	 * @return true if the component will respect constraint,false if the component will not respect constraint
	 */
	private boolean check(Component c) {
		/*boolean flag = true; 
		List<Constraint> listConstraint = c.getConstraints();
		for (Constraint constraint : listConstraint) {
			if (!constraint.checkList(addedComponents)) {
				flag = false;
				break;
			}
		}*/
		InterfaceConstraintChecker cc = new ConstraintChecker();
		return cc.check(c, addedComponents);
	}

	/**
	 * se il numero dei componenti aggiunti è minore del numero di quelli necessari restituisco subito false
	 * altrimenti con il doppio ciclo controllo se gli elementi di neededComponent sono contenuti tutti 
	 *  in added component
	 * @return true added components contains essential components
	 */
	public boolean checkConf() { 
		boolean flag = false; 
		if (addedComponents.size() >= neededComponents.size()) { 
			for (String nc : neededComponents) {
				for (Component ac : addedComponents) { 
					if (nc.equals(ac.getTypeComponent())) {
						flag = true;
						break;
					}
				}
				if (!flag) { 
					return flag; //se la configurazione non è funzionante ritorno subito flag al programma, senza curarmi delle altre componenti
				}
				flag = false; //reimposto il flag a false per quando ripartira' il ciclo
			}
			flag = true;
			return flag; //se tutto fila liscio ritorno il flag = true

		}
		return flag;
	}
	/**
	 * 
	 * @return - neededComponents
	 */
	public List<String> getNeededComponents() {
		return neededComponents;
	}

	/**
	 * 
	 * @return addedComponents
	 */
	public List<Component> getAddedComponents() {
		return addedComponents;
	}

	@Override
	public String toString() {
		return "Configuration [addedComponents=" + addedComponents + "]";
	}

}
