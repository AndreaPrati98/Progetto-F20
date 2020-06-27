package main.services.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

		// Se c'è una riga (quella corretta) il primo next è ok
		try {
			if (rs.next())
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

	@Override
	public List<String> getAdmin() {
		ResultSet rs = dbop.getAdmin();
		List<String> admin = new ArrayList<String>();
		String name = null;
		try {
			while (rs.next()) {
				name = rs.getString("email");
				admin.add(name);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return admin;
	}

	@Override
	public boolean checkIfUserExist(String mail) {
		ResultSet rs = dbop.checkIfUserExist(mail);
		try {
			if (rs.getString("email") == null) {
				return false;
			} else {
				return true;
			}
		} catch (SQLException e) {
		}
		return false;
	}

	@Override
	public boolean addAdmin(String mail, boolean decision) {
		return dbop.addAdmin(mail, decision);
	}

	//TODO aggiungere uml
	@Override
	public boolean removeUser(String email) {
		boolean exist = checkIfUserExist(email);
		if (!exist) {
			return false;
		}
		
		boolean isDone = dbop.removeUser(email); 
		
		return isDone;
	}

}
