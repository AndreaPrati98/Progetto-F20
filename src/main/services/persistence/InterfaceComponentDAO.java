package main.services.persistence;

import java.util.List;

import main.model.configurator.component.Component;

public interface InterfaceComponentDAO {
	
	List<Component> getAllComponent();
	List<String> getNeededComponents();
	List<String> getTypeOfComponent();
	boolean addComponent(String model, String type, double price);
	boolean removeComponent(String model, String type);
	boolean updateComponent();
	List<String> getStandardAttributes(String typeComponent);
}
