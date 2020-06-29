package main.services.persistence;


import java.util.List;

import main.model.configurator.configuration.Configuration;
import main.model.customer.Customer;

public interface InterfaceConfigurationDAO {
/**
 * interfaccia  per definire un set di metodi che verrano poi implementati dai vari DAO che interrogheranno il Database
 * @param confId
 * @return
 */
	public Configuration getConfiguration(int confId);

	public List<Configuration> getConfigurationByEmail(String email);

	public boolean addConfiguration(Configuration conf,Customer user );

	public boolean updateConfiguration(Configuration conf, Customer user);

	public boolean removeConfiguration(int confId);
	
	public String getOwnerMailByConfigurationId(int confId);
	
	public int getLastUsedId();
	
	public boolean changeConfName(int confId, String newName);

}
