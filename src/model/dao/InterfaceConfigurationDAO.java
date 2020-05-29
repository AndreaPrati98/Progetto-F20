package model.dao;


import model.configuration.Configuration;

public interface InterfaceConfigurationDAO {

	public Configuration getConfiguration(String confId);

	public Configuration getConfigurationByEmail(String email);

	public boolean addConfiguration(int id, String name, String email);

	public boolean updateConfiguration(int id, String name, String email);

	public boolean removeConfiguration(int confId);

	public boolean addUsers(String name,String cognome, String email,String i);

}
