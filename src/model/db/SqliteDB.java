package model.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Frenkli Buzhiqi
 */

public class SqliteDB {
	Connection c = null;
	Statement stmt = null;
	
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
	
	public void getUsersFromDB() {
		try {
			this.stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Users");
			
			while(rs.next()) {
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
	
	public void insertUserToDB(User u) {
		try {
			this.stmt = c.createStatement();
			stmt.executeQuery("INSERT INTO Users (firstName, lastName, email, password)" +
							  "VALUES ('" + u.getFirstName() + "','" + u.getLastName() + "','" + u.getEmail() + "','" + u.getPassword() + "')");
		} catch (Exception e) {
			//System.out.println("Error: " + e.getMessage());
		}
	}
	
	public void closeConnection() {
		try {
			c.close();
		} catch (Exception e) {
			//error
		}
	}
	
}