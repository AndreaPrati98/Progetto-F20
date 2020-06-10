package main.model.configurator;

import java.util.ArrayList;
import java.util.List;

import main.model.configurator.component.Component;
import main.services.persistence.PersistenceFacade;
import main.services.persistence.RdbOperation;
/**
 * Here is where we have the list of all components 
 * @author Alessandro Capici
 *
 */
public class ComponentCatalog {

	private List<Component> componentList;
	private PersistenceFacade pf;
	
	public ComponentCatalog() {
		pf= PersistenceFacade.getIstance();
		componentList = new ArrayList<Component>();
		componentList=pf.getAllComponent();
		//componentList.addAll((new JSONUtil()).getComponents());
	}
	
	/**
	 * 
	 * @param newComponent - the component you want to add 
	 * @return false - if the componentList already conains this component or newComponent is null
	 * @return true - if is alright
	 */
	public boolean addComponent(Component newComponent){
		if(!componentList.contains(newComponent) && (newComponent != null)) {
			componentList.add(newComponent);
			return true;
		}	
		return false;
	}
	
	/**
	 * 
	 * @param remComponent - the component you want to remove 
	 * @return false - if the componentList already conains this component or newComponent is null
	 * @return true - if is alright
	 */
	public boolean removeComponent(Component remComponent) {
		if(!componentList.contains(remComponent) && (remComponent != null)) {
			componentList.remove(remComponent);
			return true;
		}	
		return false;
	}
	
	/**
	 * @return StringBuilder.toString() - the elements appended in the StringBuilder are Components.toString
	 * @see StringBuilder
	 * @see Component
	 */
	@Override
	public String toString() {
		
		StringBuilder s = new StringBuilder();
		
		for (Component elem : componentList) {
			s.append(elem.toString() + "\n");
		}
		return s.toString();
	}
	
	public List<Component> getComponentList() {
		return componentList;
	}
	
	/**
	 * 
	 * @param model
	 * @return the Component with the specified model
	 */
	public Component getComponentByModel(String model) {
		
		for(Component c : componentList) {
			if(c.getModel().equals(model)) {
				return c;
			}
		}
		
		return null;
	}
	
	
}