package main.services.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * 
 * @author Alessandro Capici
 * @author Cristian Garau
 * @author Andrea
 *
 */

public class RdbOperation {
	private Connection c;
	private Statement stmt;

	public RdbOperation() {
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:Util/projectDatabase.sqlite");
			System.out.println("Connection done");

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error: " + e.getMessage());
		}
	}

	public Connection getC() {
		return c;
	}

	public Statement getStmt() {
		return stmt;
	}

	/*
	 * QUERY COMPONENT RELATION
	 */
	public ResultSet getAllComponents() {
		ResultSet rs = null;
		Statement s;
		try {
			s = c.createStatement();
			rs = s.executeQuery("SELECT * FROM Component");
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return rs;

	}

	public boolean removeComponent(String model, String type) {
		String sql = "DELETE FROM Component WHERE TypeofC = ? AND Model= ?";

		try (PreparedStatement pstmt = c.prepareStatement(sql)) {

			// set the corresponding param
			pstmt.setString(1, type);
			pstmt.setString(2, model);
			// execute the delete statement
			if(pstmt.executeUpdate()==1) {
				return true;
			}	
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return false;

	}

	/*
	 * QUERY ATTRIBUTE RELATION
	 */

	public ResultSet getAttributesByComponent(String model, String typeOfComponent) {
		Statement s;
		String sql = "select attribute.TypeofC, Attribute.ModelofC, Attribute.NameStdAtt, Attribute.AttValue, StandardAttribute.ConstraintName, StandardAttribute.Category, StandardAttribute.IsPresentable\r\n"
				+ "from Attribute, StandardAttribute\r\n" + "where attribute.TypeofC = '" + typeOfComponent
				+ "' and attribute.ModelofC = '" + model + "'\r\n" + "and StandardAttribute.TypeOfComponent = '"
				+ typeOfComponent + "'\r\n" + "and StandardAttribute.Name = Attribute.NameStdAtt\r\n";
		ResultSet rs = null;
		try {
			s = c.createStatement();
			rs = s.executeQuery(sql);

		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Error: " + e.getMessage());
		}

		return rs;
	}

	public ResultSet getConfiguration(int confId) {
		try {
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT*\n" + "FROM ElementConfiguration NATURAL join Configuration\n" + "where Id=" + confId);
			return rs;
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		return null;
	}

	public ResultSet getConfigurationByEmail(String email) {
		try {
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT*\n" + "FROM ElementConfiguration NATURAL join Configuration\n" + "where EmailU=" + email);
			return rs;
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		return null;
	}

	public boolean addConfiguration(int id, String name, String email, List<String> Type, List<String> Model) {
		String sql = "INSERT INTO Configuration(Id,Name,EmailU) VALUES(?,?,?)";
		PreparedStatement ps;
		try {
			ps = c.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setString(3, email);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		sql = "INSERT INTO ElementConfiguration(TypeofC, ModelofC, Id) VALUES(?,?,?)";
		for (int i = 0; i < Type.size(); i++) {
			try {
				ps = c.prepareStatement(sql);
				ps.setString(1, Type.get(i));
				ps.setString(2, Model.get(i));
				ps.setInt(3, id);
				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		return true;

	}
	
	
	public ResultSet getUser(String email) {
		try {
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT*\n" + "FROM User where email='" + email +"'");
			return rs;
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		return null;
	}
	
	public ResultSet login(String email, String password) {
		String sql = "SELECT * FROM User WHERE email = ? AND password = ?";
		PreparedStatement ps;
		
		try {
			ps = c.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, password);
			return ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	

	public boolean addUser(String name, String cognome, String email, String password, boolean isAdmin) {
		String sql = "INSERT INTO User(firstName,lastName,email,password,isAdmin) VALUES(?,?,?,?,?)";
		PreparedStatement ps;
		try {
			ps = c.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, cognome);
			ps.setString(3, email);
			ps.setString(4, password);
			if (isAdmin) {
				ps.setInt(5, 1);
			} else {
				ps.setInt(5, 0);
			}
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	public boolean removeConfiguration(int id) {
		String sql = "DELETE FROM ElementConfiguration WHERE Id = ?";
		try (PreparedStatement pstmt = c.prepareStatement(sql)) {

			// set the corresponding param
			pstmt.setInt(1, id);
			// execute the delete statement
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}

		sql = "DELETE FROM Configuration WHERE Id = ?";
		try (PreparedStatement pstmt = c.prepareStatement(sql)) {

			// set the corresponding param
			pstmt.setInt(1, id);
			// execute the delete statement
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return false;

	}

	public ResultSet getAllConstraints() {
		try {
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Bound");
			return rs;
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}

		return null;

	}

	public boolean addNewConstraint(String name, String type) {
		String sql = "INSERT INTO Bound(Name,Type) VALUES(?,?)";
		PreparedStatement ps;
		try {
			ps = c.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, type);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean RemoveConstraint(String name) {
		String sql = "DELETE FROM Bound WHERE Name = ?";

		try (PreparedStatement pstmt = c.prepareStatement(sql)) {

			// set the corresponding param
			pstmt.setString(1, name);
			// execute the delete statement
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	public ResultSet getLastUsedId() {
		String sql = "SELECT max(Id) as maxId\r\nFROM Configuration";
		try {
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			return rs;
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		return null;

	}
}
