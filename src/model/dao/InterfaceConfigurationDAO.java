package model.dao;


import java.util.List;

import model.configuration.Configuration;

public interface InterfaceConfigurationDAO {

	public Configuration getConfiguration(String confId);

	public List<Configuration> getConfigurationByEmail(String email);

	public boolean addConfiguration(int id, String name, String email);

	public boolean updateConfiguration(int id, String name, String email);

	public boolean removeConfiguration(int confId);

	public boolean addUsers(String name,String cognome, String email,String password);

}
