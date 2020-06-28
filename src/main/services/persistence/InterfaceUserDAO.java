package main.services.persistence;

import java.util.List;

import main.model.customer.Customer;

public interface InterfaceUserDAO {

	public boolean addUsers(String name, String cognome, String email, String password, boolean isAdmin);
	public boolean login(String email, String password);
	public Customer getCustomer(String email);
	public boolean changeEmail(String oldEmail, String newEmail);
	public String getPasswordByMail(String mail);
	public boolean changePassword(String email, String newPassword);
	public boolean checkIfUserExist(String mail);
	public List<String> getAdmin();
	public boolean addAdmin(String mail,boolean decision);
	public boolean removeUser(String email);
	
}
