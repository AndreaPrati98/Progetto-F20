package main.services.persistence;

import java.util.List;

import main.model.configurator.component.Component;

public interface InterfaceComponentDAO {
	
	//TODO Tutti i metodi che ci sono in RdbComponentDAO devono esserci anche qui e nella
	//facade, se no il modelllo non li può usare
	List<Component> getAllComponent();
	List<String> getNeededComponents();
	List<String> getTypeOfComponent();
}
