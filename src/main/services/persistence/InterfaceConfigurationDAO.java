package main.services.persistence;


import java.util.List;

import main.model.configurator.configuration.Configuration;
import main.model.people.costumer.Customer;

public interface InterfaceConfigurationDAO {

	public Configuration getConfiguration(int confId);

	public List<Configuration> getConfigurationByEmail(String email);

	public boolean addConfiguration(Configuration conf,Customer user );

	public boolean updateConfiguration(Configuration conf, Customer user);

	public boolean removeConfiguration(int confId);
	
	public String getOwnerMailByConfigurationId(int confId);
	
	public int getLastUsedId();

}
