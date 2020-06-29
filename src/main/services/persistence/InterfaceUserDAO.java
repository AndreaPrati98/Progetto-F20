package main.services.persistence;

import java.util.List;

import main.model.customer.Customer;

public interface InterfaceUserDAO {
/**
 * usiamo questa interfaccia per definire un cospiquo set di istruzioni che veranno implementate dai Dao per interrogare il
 * database in particolare queste operazioni sono mirate alla coretta gestione degli user e a salvare nel db le varie informazioni a loro collegate
 * 
 */
	public boolean addUsers(String name, String cognome, String email, String password, boolean isAdmin);
	public boolean login(String email, String password);
	public Customer getCustomer(String email);
	public boolean changeEmail(String oldEmail, String newEmail);
	//TODO aggiungere uml
	public String getPasswordByMail(String mail);
	public boolean changePassword(String email, String newPassword);
	public boolean checkIfUserExist(String mail);
	public List<String> getAdmin();
	public boolean addAdmin(String mail,boolean decision);
	//TODO aggiungere uml
	public boolean removeUser(String email);
	
}
