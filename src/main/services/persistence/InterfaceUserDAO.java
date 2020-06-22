package main.services.persistence;

import main.model.people.costumer.Customer;

public interface InterfaceUserDAO {

	public boolean addUsers(String name, String cognome, String email, String password, boolean isAdmin);
	//TODO questo non dovrebbe prendere un oggetto Customer?
	
	public boolean login(String email, String password);
	public Customer getCustomer(String email);
	public boolean changeEmail(String oldEmail, String newEmail);
	public boolean changePassword(String oldPassword, String newPassword);
}
