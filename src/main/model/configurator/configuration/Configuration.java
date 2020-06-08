package main.model.configurator.configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.model.configurator.component.Component;
import main.model.configurator.constraint.AbstractConstraint;
import main.model.configurator.constraint.ConstraintChecker;
import main.model.configurator.constraint.InterfaceConstraintChecker;
import main.model.configurator.constraint.MaxConstraint;

/**
 * @author Capici Alessandro ,Frenkli Buzhiqi
 * @co_author Cic
 */

public class Configuration {
	private static int lastUsedId;
	private int id;

	private String name;
	
	private List<String> neededComponents; // lista di elementi obbligatori per far si che un pc si possa accendere
											// (Lista da stabilire)
	private List<Component> addedComponents;

	private Map<String, Boolean> singleComponents; // mappa con componenti che possono essere aggiunte in sovrannumero,
													// da definire meglio in futuro

	private List<AbstractConstraint> constraintErrors;

	/**
	 * Create an empty new configuration, with a never used id
	 */
	public Configuration() {
		this.neededComponents = new ArrayList<String>();
		this.singleComponents = new HashMap<String, Boolean>();
		this.constraintErrors = new ArrayList<AbstractConstraint>();
		addedComponents = new ArrayList<Component>();
		lastUsedId++;
		this.id=lastUsedId;
	}
	
	// Questa andra' tolta quando gestiremo i neededComponent e i singleComponent tramite vincoli
	public Configuration(List<String> neededComponents, Map<String, Boolean> singleComponents) {
		this.neededComponents = neededComponents;
		this.singleComponents = singleComponents;
		this.constraintErrors = new ArrayList<AbstractConstraint>();
		addedComponents = new ArrayList<Component>();
	}
	
	/**
	 * Create an empty configuration with a given id.
	 * This constructor is used when the configuration is downloaded from the database
	 * @param id
	 */
	public Configuration(int id) {
		this.neededComponents = new ArrayList<String>();
		this.singleComponents = new HashMap<String, Boolean>();
		this.constraintErrors = new ArrayList<AbstractConstraint>();
		addedComponents = new ArrayList<Component>();
		this.id=id;
	}
	
	/**
	 * Create a configuration with a given id and some components.
	 * This constructor is used when the configuration is downloaded from the database.
	 * @param id
	 * @param addedComponents
	 */
	public Configuration(int id, List<Component> addedComponents) {
		this.addedComponents=addedComponents;
		this.neededComponents = new ArrayList<String>();
		this.singleComponents = new HashMap<String, Boolean>();
		this.constraintErrors = new ArrayList<AbstractConstraint>();
		this.id=id;
	}

	/**
	 * aggiunta del componente scelto nella lista dei componenti solo se compatibile
	 * 
	 * @param c type:{@link Component} Component that you would like to add.
	 * @return true if the component have been added, false if the component haven't
	 *         been added or it was already added before
	 * @see Component
	 * 
	 */
	public boolean addComponent(Component c) {
		//Di volta in volta tengo traccia solo degli errori avuti all'ultimo tentativo di inserimento
		constraintErrors.clear();
		
		InterfaceConstraintChecker checker = new ConstraintChecker();
		constraintErrors = checker.check(c, addedComponents);
		if(constraintErrors.isEmpty()) {
			addedComponents.add(c);
			return true;
		} else {
			return false;
		}
		
//		//constraintErrors = check(c);
//		if (constraintErrors.isEmpty()) {
//			/**
//			 * checking if the component it's a "single component or not"
//			 */
//			if (!getSingleComponents().containsKey(c.getTypeComponent())) {
//				addedComponents.add(c);
//				return true;
//			} else {
//				if (!getSingleComponents().get(c.getTypeComponent())) {
//					addedComponents.add(c); // se non è un componente singolo lo posso agiungere
//					getSingleComponents().replace(c.getTypeComponent(), true);
//				} else {
//					/**
//					 * Messaggio d'errore
//					 */
//					constraintErrors.add(new MaxConstraint("Single Component"));
//					return false; // se quel componente è singolo ed era gia stato aggiunto non lo posso
//									// riaggiungere
//				}
//			}
//			return true;
//		} else {
//			return false;
//			// return new DimensionConstraint("PROVA", "10", ConstraintType.EXTERNAL);
//		}

	}

	public boolean removeComponent(Component c) {
		if (addedComponents.remove(c)) {
//			if (singleComponents.containsKey(c.getTypeComponent()) && singleComponents.get(c.getTypeComponent())) {
//				singleComponents.replace(c.getTypeComponent(),false);
//			}
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
	private List<AbstractConstraint> check(Component c) {
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
	 * se il numero dei componenti aggiunti � minore del numero di quelli
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
	
	public boolean addComponentWithoutChecking(Component c) {
		this.addedComponents.add(c);
		return true;
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

	public List<AbstractConstraint> getConstraintErrors() {
		return constraintErrors;
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
	
	/**
	 *  ritorna il prezzo totale della configurazione i prezzi sono di amazon 
	 * @return totalPrice
	 */
	public double getTotalPrice() {
		double totalPrice=0;
		for(Component c : this.getAddedComponents()) {
			totalPrice += c.getPrice();
		}
		return totalPrice;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static void setLastUsedId(int id) {
		lastUsedId = id;
	}

	public int getId() {
		return id;
	}
	
	

}
