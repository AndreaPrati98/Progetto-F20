package model.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.component.Component;

/**
 * @author Frenkli Buzhiqi
 */

public class SqliteDB {
	Connection c = null;
	Statement stmt = null;
	
	/**
	 * Estabilishing Connection
	 */
	public SqliteDB(){
		
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:Util/projectDatabase.sqlite");
			System.out.println("Connection done");
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error: " + e.getMessage());
		}
		
	}
	
	/**
	 * Displays user datas from "User" table in projectDatabase.sqlite
	 * @return
	 */
	public void getUsersFromDB() {
		try {
			this.stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Users");
			
			while(rs.next()) { //printing table rows until table finishes
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				String email = rs.getString("email");
				String password = rs.getString("password");
				
				System.out.println(firstName + " " + lastName + " " + " " + email + " " + password);
			}
			
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
	
	/**
	 * Inserts user to "User" table in projectDatabase.sqlite
	 * @param u type:{@link User}
	 * @return
	 */
	
	public void insertUserToDB(User u) {
		try {
			this.stmt = c.createStatement();
			stmt.executeQuery("INSERT INTO Users (firstName, lastName, email, password)" +
							  "VALUES ('" + u.getFirstName() + "','" + u.getLastName() + "','" + u.getEmail() + "','" + u.getPassword() + "')");
		} catch (Exception e) {
			//System.out.println("Error: " + e.getMessage());
		}
	}
	
	/**
	 * Closes connection
	 * @return
	 */
	
	public void closeConnection() {
		try {
			c.close();
		} catch (Exception e) {
			//error
		}
	}
	
}