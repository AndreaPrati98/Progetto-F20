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
 *        Class that serves as a link between all servlets and our model.
  *       One is instantiated for each user and is then kept in the context
  *       handler.
 */
public class ServletController {

	private Configurator configurator;
	private Customer customer;


	public ServletController() {
		configurator = new Configurator();
	}

	
	public void newConfiguration(){
		configurator.newConfiguration();
	}
	
	/**
	 * Add the component with the given model to the configuration. 
	 * 
	 * @param model
	 * @return true if the Component with the specified model as parameter is
	 * added to the configuration
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
	 * @return
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
	 * removed to the configuratio
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
	
	public List<String> getConstraintErrors(){
		return configurator.getListStringOfConstraintErrors();
	}
	
	public boolean saveConfiguration(){
		return configurator.saveConfiguration(customer);
	}
	
	public List<Component> retrieveConfigurationComponentById(int confId){
		configurator.retrieveConfigurationById(confId);
		return configurator.getAddedComponents();
	}

	
	public double getPerformanceIndex(){
		return configurator.getPerformaceIndex();
	}


	public double getConfigurationPrice() {
		return configurator.getPrice();		
	}
	
	public boolean removeConfiguration(int confId) {
		return configurator.removeConfiguration(confId);
	}
	
	
	public boolean removeUser() {
		String email = customer.getEmail();
		PersistenceFacade pf = PersistenceFacade.getIstance();
		
		return pf.removeUser(email);
		
	}
	
	/**
	 * @return true if create and saves a configuration autofilling the remaining elements
	 */
	public boolean autofill(){
		if(!configurator.autofillRandom()) 
			return false;
		
		return 	this.saveConfiguration();
	}
	
	/**
	 * @param price
	 * @return
	 */
	public boolean autofill(double price){
		if(!configurator.autofillByPrice(price)) 
			return false;
		
		return 	configurator.saveConfiguration(customer);
	}
	
	public int getConfigurationId() {
		return configurator.getConfigurationId();
	}

	
	
	public void setConfigurationName(String name) {
		configurator.setConfigurationName(name);
		
	}
	
	
	public String getConfigurationName() {
		return configurator.getConfigurationName();
	}


	public Customer getCustomer() {
		return customer;
	}
	
	//TODO aggiungere a uml
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
	
	//TODO aggiungere uml
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
