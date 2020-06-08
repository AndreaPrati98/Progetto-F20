package main.services.persistence;

import java.util.List;

import main.model.configurator.component.Component;

public interface InterfaceComponentDAO {
	
	List<Component> getAllComponent();
	List<String> getNeededComponents();
}
