package model.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import model.component.Component;

/**
 * 
 * @author Alessandro Capici
 * @author Cristian Garau
 *
 */

public class RdbOperation {
	private Connection c;
	private Statement stmt;

	public RdbOperation(Connection c, Statement stmt) {
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

	public void setC(Connection c) {
		this.c = c;
	}

	public Statement getStmt() {
		return stmt;
	}

	public void setStmt(Statement stmt) {
		this.stmt = stmt;
	}
	
	public Component getComponent() {
		
		return null;
		
	}

}
