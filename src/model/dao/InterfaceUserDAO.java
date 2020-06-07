package model.dao;

import model.customer.Customer;

public interface InterfaceUserDAO {

	public boolean addUsers(String name, String cognome, String email, String password, boolean isAdmin);
	public boolean login(String email, String password);
	public Customer getCustomer(String email);
}
