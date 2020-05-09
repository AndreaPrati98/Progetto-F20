package model.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Frenkli Buzhiqi
 */

public class User {
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	
	public User(String firstName, String lastName, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}
	
	public String getFirstName () {
		return firstName;
	}
	
	public String getLastName () {
		return lastName;
	}
	
	public String getEmail () {
		return email;
	}
	
	public String getPassword () {
		return password;
	}
	
}