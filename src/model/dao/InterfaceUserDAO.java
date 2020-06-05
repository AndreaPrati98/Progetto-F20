package model.dao;

import model.customer.Customer;

public interface InterfaceUserDAO {

	public boolean addUsers(String name, String cognome, String email, String password, boolean isAdmin);

	public Customer getCustomer(String email);
}
