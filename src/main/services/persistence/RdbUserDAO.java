package main.services.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

import main.model.customer.Customer;

public class RdbUserDAO implements InterfaceUserDAO {

	private RdbOperation dbop;

	public RdbUserDAO(RdbOperation dbop) {
		this.dbop = dbop;
	}

	@Override
	public boolean addUsers(String name, String cognome, String email, String password, boolean isAdmin) {
		return dbop.addUser(name, cognome, email, password, isAdmin);
	}

	@Override
	public Customer getCustomer(String email) {
		ResultSet rs = dbop.getUser(email);
		String name;
		String surname;
		int check;
		boolean isAdmin;
		try {
			name = rs.getString("firstName");
			surname = rs.getString("lastName");
			check = rs.getInt("isAdmin");
			if (check == 1) {
				isAdmin = true;
			} else {
				isAdmin = false;
			}
			return new Customer(name, surname, email, isAdmin);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public boolean login(String email, String password) {
		ResultSet rs = dbop.login(email, password);
	
		//Se c'è una riga (quella corretta) il primo next è ok
		try {
			if(rs.next())
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		return false;
	}
	public boolean changeEmail(String oldEmail, String newEmail) {
		return dbop.changeEmail(oldEmail, newEmail);
		
	}
	
	public boolean changePassword(String oldPassword, String newPassword) {
		return dbop.changePassword(oldPassword, newPassword);
		
	}
	
}
