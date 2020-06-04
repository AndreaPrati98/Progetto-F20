package model.dao;


import java.util.List;

import model.configuration.Configuration;
import model.customer.Customer;

public interface InterfaceConfigurationDAO {

	public Configuration getConfiguration(int confId);

	public List<Configuration> getConfigurationByEmail(String email);

	public boolean addConfiguration(Configuration conf,Customer user );

	public boolean updateConfiguration(Configuration conf, Customer user);

	public boolean removeConfiguration(int confId);

	public boolean addUsers(String name,String cognome, String email,String password);

}
