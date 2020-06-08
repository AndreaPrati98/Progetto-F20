package main.services.persistence;

import main.model.people.costumer.Customer;

public interface InterfaceUserDAO {

	public boolean addUsers(String name, String cognome, String email, String password, boolean isAdmin);
	public boolean login(String email, String password);
	public Customer getCustomer(String email);
}
