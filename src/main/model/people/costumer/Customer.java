package main.model.people.costumer;

/**
 * 
 * @author Andrea  e stepa
 *
 */
public class Customer {

	private String name;
	private String surname;
	private String email;	
	private boolean isAdmin;

	
	public Customer(String name,String surname,String email,boolean isAdmin) {
		this.email=email;
		this.name=name;
		this.setSurname(surname);
		this.isAdmin=isAdmin;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public boolean isAdmin() {
		return isAdmin;
	}
	

	
}
