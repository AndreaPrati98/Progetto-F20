package model.dao;

import java.sql.ResultSet;

import model.configuration.Configuration;

public class RdbConfigurationDAO implements InterfaceConfigurationDAO{

	private RdbOperation dbop;

	public RdbConfigurationDAO(RdbOperation dbop) {
		this.dbop = dbop;
	}

	public Configuration getConfiguration(String confId) {

		ResultSet rs = dbop.getConfiguration(confId);

		return null;

	}
	
	public Configuration getConfigurationByEmail(String email) {

		ResultSet rs = dbop.getConfigurationByEmail(email);

		return null;

	}

	public boolean addConfiguration(int id, String name, String email) {

		return dbop.addConfiguration(id, name, email);

	}

	public boolean updateConfiguration(int id, String name, String email) {

		return dbop.updateConfiguration(id, name, email);

	}

	public boolean removeConfiguration(int confId) {

		return dbop.removeConfiguration(confId);

	

	}

	@Override
	public boolean addUsers(String name, String cognome, String email, String password) {
		// TODO Auto-generated method stub
		return dbop.addUser(name, cognome, email, password);
	}

}
