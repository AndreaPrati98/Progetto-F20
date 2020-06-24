package main.model.configurator;

import java.util.ArrayList;
import java.util.List;

import main.model.configurator.component.Component;
import main.services.persistence.PersistenceFacade;
/**
 * Here is where we have the list of all components 
 * @author Alessandro Capici
 *
 */
public class ComponentCatalog {

	private List<Component> componentList;
	private PersistenceFacade pf;
	private static ComponentCatalog catalog;
	
	
	private ComponentCatalog() {
		pf= PersistenceFacade.getIstance();
		componentList = new ArrayList<Component>();
		componentList=pf.getAllComponent();
		//componentList.addAll((new JSONUtil()).getComponents());
	}
	
	
	public static ComponentCatalog getInstance(){
		if(catalog == null)
			catalog = new ComponentCatalog();
		
		return catalog;
	}
	
	/**
	 * Downloads again the catalog from the database
	 */
	public void refreshCatalog() {
		componentList.clear();
		componentList=pf.getAllComponent();
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
	
	/**
	 * 
	 * @param typeOfC
	 * @return All the components of the given type
	 */
	public List<Component> getComponentListByType(String typeOfC){
		List<Component> compByTypeList = new ArrayList<Component>();
		
		for (Component component : componentList) {
			if(component.getTypeComponent().equals(typeOfC)) {
				compByTypeList.add(component);
			}
		}
		
		return compByTypeList;
		
	}
	
	
}
