package model.configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.component.Component;
import model.component.constraint.Constraint;
import model.component.constraint.ConstraintChecker;
import model.component.constraint.ConstraintType;
import model.component.constraint.InterfaceConstraintChecker;
import model.component.constraint.MaxConstraint;

/**
 * @author Capici Alessandro ,Frenkli Buzhiqi
 */

public class Configuration {
	private List<String> neededComponents; // list of required components in a configuration
											
	private List<Component> addedComponents;

	private Map<String, Boolean> singleComponents; //map containing components that can't be added more than once

	private List<Constraint> constraintErrors;

	/**
	 * 
	 * @param neededComponents
	 *            {@link Configuration}
	 * @param singleComponents
	 */
	public Configuration(List<String> neededComponents, Map<String, Boolean> singleComponents) {
		this.neededComponents = neededComponents;
		this.singleComponents = singleComponents;
		addedComponents = new ArrayList<Component>();
	}

	/**
	 * aggiunta del componente scelto nella lista dei componenti solo se compatibile
	 * 
	 * @param c
	 *            type:{@link Component} Component that you would like to add.
	 * @return true if the component have been added, false if the component haven't
	 *         been added or it was already added before
	 * @see Component
	 * 
	 */
	public boolean addComponent(Component c) {
		constraintErrors = check(c);
		if (constraintErrors.isEmpty()) {
			/**
			 * checking if the component it's a "single component or not"
			 */
			if (!getSingleComponents().containsKey(c.getTypeComponent())) {
				addedComponents.add(c);
				return true;
			} else {
				if (!getSingleComponents().get(c.getTypeComponent())) {
					addedComponents.add(c); // i can add it, only if it is a single component
					getSingleComponents().replace(c.getTypeComponent(), true);
				} else {
					/**
					 * Messaggio d'errore
					 */
					constraintErrors.add(new MaxConstraint("Single Component", "1", ConstraintType.INTERNAL));
					return false; // if the component is single, than it can't be added again
				}
			}
			return true;
		} else {
			return false;
		}

	}

	public boolean removeComponent(Component c) {
		if (addedComponents.remove(c)) {
			if (singleComponents.containsKey(c.getTypeComponent()) && singleComponents.get(c.getTypeComponent())) {
				singleComponents.replace(c.getTypeComponent(), false);
			}
			return true;
		} else {
			return false;
		}
	}

	/**
	 * se anche solo un vincolo non è rispettato esco dal ciclo restituendo false
	 * 
	 * @param c
	 *            type:{@link Component}
	 * @return true if the component will respect constraint,false if the component
	 *         will not respect constraint
	 */
	private List<Constraint> check(Component c) {
		InterfaceConstraintChecker cc = new ConstraintChecker();
		return cc.check(c, addedComponents);
	}

	/**
	 * se il numero dei componenti aggiunti ï¿½ minore del numero di quelli
	 * necessari restituisco subito false altrimenti con il doppio ciclo controllo
	 * se gli elementi di neededComponent sono contenuti tutti in added component
	 * 
	 * @return true added components contains essential components
	 */
	public boolean checkConf() {
		boolean flag = false;
		if (addedComponents.size() >= neededComponents.size()) {
			for (String nc : neededComponents) {
				for (Component ac : addedComponents) {
					if (nc.equalsIgnoreCase((ac.getTypeComponent()))) {
						flag = true;
						break;
					}
				}
				if (!flag) {
					return flag;
					//if the configuration is not working, return flag without considering other components
				}
				flag = false; //reset flag for future use
			}
			flag = true;
			return flag; //if everything is ok, return true

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

	/**
	 * 
	 * @return
	 */

	public List<Constraint> getConstraintErrors() {
		return constraintErrors;
	}

	public String getConstraintErrorsNames() {
		String s = "";
		for (Constraint c : constraintErrors) {
			s += c.getConstraintName() + " ";
		}
		return s;
	}

	/**
	 * 
	 * @return
	 */
	public Map<String, Boolean> getSingleComponents() {
		return singleComponents;
	}

	@Override
	public String toString() {
		return "Configuration [addedComponents=" + addedComponents + "]";
	}

}
