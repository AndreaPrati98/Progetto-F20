package model.configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.component.Component;
import model.component.constraint.Constraint;
import model.component.constraint.ConstraintChecker;
import model.component.constraint.InterfaceConstraintChecker;

/**
 * @author Capici Alessandro ,Frenkli Buzhiqi
 */

public class Configuration {
	private List<String> neededComponents; // lista di elementi obbligatori per far si che un pc si possa accendere
											// (Lista da stabilire)
	private List<Component> addedComponents;

	private Map<String, Boolean> singleComponents; // mappa con componenti che possono essere aggiunte in sovrannumero,
													// da definire meglio in futuro

	private List<Constraint> constraintErrors;

	/**
	 * 
	 * @param neededComponents {@link Configuration}
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
	 * @param c type:{@link Component} Component that you would like to add.
	 * @return true if the component have been added, false if the component
	 * 		   haven't been added or it was already added before
	 * @see Component
	 * 
	 */
	public boolean addComponent(Component c) {
		constraintErrors = check(c);

		/*
		 * if(check(c)) { addedComponents.add(c); return true; }else { return false; }
		 */
		if (constraintErrors.isEmpty()) {
			/**
			 * checking if the component it's a "single component or not"
			 */
			if (!getSingleComponents().get(c.getTypeComponent())) {
				addedComponents.add(c); //se non è un componente singolo lo posso agiungere
				getSingleComponents().replace(c.getTypeComponent(), true);
			}else {
				return false; //se quel componente è singolo ed era gia stato aggiunto non lo posso riaggiungere
			}
			return true;
		} else {
			return false;
			// return new DimensionConstraint("PROVA", "10", ConstraintType.EXTERNAL);
		}

	}

	public boolean removeComponent(Component c) {
		if (addedComponents.remove(c)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * se anche solo un vincolo non � rispettato esco dal ciclo restituendo false
	 * 
	 * @param c type:{@link Component}
	 * @return true if the component will respect constraint,false if the component
	 *         will not respect constraint
	 */
	private List<Constraint> check(Component c) {
//		boolean flag = true; 
//		List<Constraint> listConstraint = c.getConstraints();
//		for (Constraint constraint : listConstraint) {
//			if (!constraint.checkList(addedComponents)) {
//				flag = false;
//				break;
//			}
//		}
		InterfaceConstraintChecker cc = new ConstraintChecker();
		return cc.check(c, addedComponents);
	}

	/**
	 * se il numero dei componenti aggiunti � minore del numero di quelli necessari
	 * restituisco subito false altrimenti con il doppio ciclo controllo se gli
	 * elementi di neededComponent sono contenuti tutti in added component
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
					return flag; // se la configurazione non � funzionante ritorno subito flag al programma,
									// senza curarmi delle altre componenti
				}
				flag = false; // reimposto il flag a false per quando ripartira' il ciclo
			}
			flag = true;
			return flag; // se tutto fila liscio ritorno il flag = true

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
