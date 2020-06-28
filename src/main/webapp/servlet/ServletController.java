package main.webapp.servlet;

import java.util.List;

import main.model.configurator.ComponentCatalog;
import main.model.configurator.Configurator;
import main.model.configurator.component.Component;
import main.model.customer.Customer;
import main.services.persistence.PersistenceFacade;

/**
 * 
 * 
 *
 * Class that serves as a link between all servlets and our model.
 * One is instantiated for each user during the login and is then kept in the context
 * handler to make it accessible from each servlet.
 */
public class ServletController {

	private Configurator configurator;
	private Customer customer;


	public ServletController() {
		configurator = new Configurator();
	}

	/**
	 * Init a new configuration inside the configurator.
	 */
	public void newConfiguration(){
		configurator.newConfiguration();
	}
	
	/**
	 * Add the component with the given model to the configuration. 
	 * 
	 * @param model
	 * @return true if the Component with the specified model as parameter is
	 * added to the configuration.
	 */
	public boolean addToConfiguration(String model) {
		ComponentCatalog catalog = ComponentCatalog.getInstance();
		Component component = catalog.getComponentByModel(model);
		if (component == null)
			return false;
		return configurator.addComponent(component);
	}
	
	/**
	 * Second version of the method that add a specified number of the model given as
	 * parameter
	 * @param model
	 * @return true if the number of Component with the specified model as parameter is 
	 * added, otherwise none of them is added.
	 */
	public boolean addToConfiguration(String model, int number) {
		ComponentCatalog catalog = ComponentCatalog.getInstance();
		Component component = catalog.getComponentByModel(model);
		if (component == null)
			return false;
		
		for(int i=0; i< number; i++) {
			//If one of the add goes wrong, you need to remove the previous that you 
			//have added during this cicle and show that it went wrong to the caller class
			if(!configurator.addComponent(component)){			
				this.removeFromConfiguration(model);
				return false;
			}
			
		}
		
		return true;
	}

	/**
	 * Remove the component with the given model from the configuration.
	 * 
	 * @param model
	 * @return true if the Component with the specified model as parameter is 
	 * removed to the configuration. It remove all instances of the same model from the
	 * configuration.
	 */
	public boolean removeFromConfiguration(String model) {
		ComponentCatalog catalog = ComponentCatalog.getInstance();
		Component component = catalog.getComponentByModel(model);
		if (component == null)
			return false;
		return configurator.removeComponent(component);
	}

	/**
	 * Debug method for print the configuration
	 */
	public void printConf() {
		for (int i = 0; i < 5; i++) System.out.println(); //Serve a fare spazio biacno
		System.out.println(configurator.getConfiguration());

	}
	
	/**
	 * Try to log with the given email and password, and if correct retrive the Customer
	 * inside for the Configurator object.
	 * 
	 * @param email
	 * @param password
	 * @return true if the login goes right, false otherwise
	 */
	public boolean login(String email, String password){
		PersistenceFacade facade = PersistenceFacade.getIstance();		
		if(facade.login(email, password)) {				
			customer = facade.getUser(email);
			return true;
		}
		
		return false;
	}
	
	/**
	 * Check if the configuration is correct relatively at other things like 
	 * single component.
	 * 
	 * @return true if the configuration is correct 
	 */
	public boolean checkConfiguration(){
		return  configurator.checkConf();	
	}
	
	/**
	 * Get the last constraint errors that the configuration violated trying to add new
	 * component during the last add.
	 * 
	 * @return a list of String with name of the constraint violated during last add.
	 */
	public List<String> getConstraintErrors(){
		return configurator.getListStringOfConstraintErrors();
	}
	
	/**
	 * Save the current configuration inside the persistency storage.
	 * 
	 * @return true if the operation goes right, otherwise false.
	 */
	public boolean saveConfiguration(){
		return configurator.saveConfiguration(customer);
	}
	
	/**
	 * Retrieve the component of the preexisting configuration of the given id.s 
	 * 
	 * @param confId
	 * @return a List of Component.
	 */
	public List<Component> retrieveConfigurationComponentById(int confId){
		configurator.retrieveConfigurationById(confId);
		return configurator.getAddedComponents();
	}

	/**
	 * Getter the performance index of the configuration.
	 * 
	 * @return the performance index as double.
	 */
	public double getPerformanceIndex(){
		return configurator.getPerformaceIndex();
	}

	/**
	 * Getter the price of the configuration.
	 * 
	 * @return the configuration price as double.
	 */
	public double getConfigurationPrice() {
		return configurator.getPrice();		
	}
	
	/**
	 * Remove the configuration with the given id from the persistency saved configurations.
	 * 
	 * @param confId
	 * @return true if the operation goes right, otherwise false.
	 */
	public boolean removeConfiguration(int confId) {
		return configurator.removeConfiguration(confId);
	}
	
	/**
	 * Remove the Customer from the saved customer in persistency.
	 * 
	 * @return true if the action goes right, otherwise false.
	 */
	public boolean removeUser() {
		String email = customer.getEmail();
		PersistenceFacade pf = PersistenceFacade.getIstance();
		
		return pf.removeUser(email);
		
	}
	
	/**
	 * Autofill the configuration using the random algorithm.
	 * 
	 * @return true if create and saves a configuration autofilling the remaining elements,
	 * otherwise false.
	 */
	public boolean autofill(){
		if(!configurator.autofillRandom()) 
			return false;
		
		return this.saveConfiguration();
	}
	
	/**
	 * Autofill the configuration using the by price algorithm.
	 * 
	 * @param price
	 * @return true if create and saves a configuration autofilling the remaining elements,
	 * otherwise false.
	 */
	public boolean autofill(double price){
		if(!configurator.autofillByPrice(price)) 
			return false;
		
		return 	this.saveConfiguration();
	}
	
	/**
	 * Getter the id of the configuration.
	 * 
	 * @return the configuration id as int.
	 */
	public int getConfigurationId() {
		return configurator.getConfigurationId();
	}

	
	/**
	 * Set name of the current configuration.
	 * 
	 * @param name
	 */
	public void setConfigurationName(String name) {
		configurator.setConfigurationName(name);
	}
	
	/**
	 * Getter of the configuration name.
	 * 
	 * @return the name of the configuration as a String.
	 */
	public String getConfigurationName() {
		return configurator.getConfigurationName();
	}

	/**
	 * Getter the Customer of the configuration.
	 * 
	 * @return the customer  as Customer.
	 */
	public Customer getCustomer() {
		return customer;
	}
	
	/**
	 * Changes the password of the customer.
	 * 
	 * @param newPassword
	 * @param oldPassword
	 * @return true if the password is changed, otherwise false.
	 */
	public boolean changePassword(String newPassword, String oldPassword) {	
		String email = customer.getEmail();
		PersistenceFacade pf = PersistenceFacade.getIstance();
		String oldPswFromDb = pf.getPasswordByMail(email);
		
		boolean isDone = false;
		if (oldPswFromDb.equals(oldPassword)) {
			System.out.println("La password vecchia è giusta, procedo a cambiarla");
			isDone = pf.changePassword(email, newPassword);
		} else {
			System.out.println("La vecchia password non è corretta");
		}
		
		return isDone;
	}
	
	/**
	 * Changes the email of the customer.
	 * 
	 * @param newEmail
	 * @param oldEmail
	 * @return true if the email is changed.
	 */
	public boolean changeEmail(String newEmail, String oldEmail) {
		String emailOfThisUser = customer.getEmail();
		PersistenceFacade pf = PersistenceFacade.getIstance();		
		
		boolean isDone = false;
		if (emailOfThisUser.equals(oldEmail)) {
			System.out.println("Procedo a cambiare la mail");
			isDone = pf.changeMail(oldEmail, newEmail);
		} else {
			System.out.println("La vecchia mail è sbagliata");
		}
		
		return isDone;
		
	}
	
	
}
