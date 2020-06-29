package main.services.persistence;

import java.util.List;

import main.model.configurator.component.Component;

public interface InterfaceComponentDAO {
/**
 * in questa interfaccia definiamo i metodi che ci serve implementare per fare le interrogazioni al database
 * 
 * 
 * @return
 */
	List<Component> getAllComponent();
	List<String> getNeededComponents();
	List<String> getTypeOfComponent();
	boolean addComponent(String model, String type, double price);
	boolean removeComponent(String model, String type);
	List<String> getStandardAttributes(String typeComponent);
	boolean addAttribute(String typeOfC, String modelOfC, String nameAtt, String attValue);
	boolean addStandardAttribute(String name, String typeOfC, String constraintName, String category, boolean isPresentable);
	boolean addTypeComponent(String type, boolean isNeeded);
}
