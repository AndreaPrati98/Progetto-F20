package main.services.persistence;

import java.util.List;

import main.model.configurator.component.Component;

/**
 * Interface used to define methods related to components, that will later get implemented in RdbComponentDAO
 * @see RdbComponentDAO
 * @see Component
 */

public interface InterfaceComponentDAO {

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
