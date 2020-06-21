package main.services.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.model.configurator.ComponentCatalog;
import main.model.configurator.component.Component;
import main.model.configurator.configuration.Configuration;
import main.model.people.costumer.Customer;

@SuppressWarnings("unused")
public class RdbConfigurationDAO implements InterfaceConfigurationDAO {

	private RdbOperation dbop;

	public RdbConfigurationDAO(RdbOperation dbop) {
		this.dbop = dbop;
	}

	public Configuration getConfiguration(int confId) {

		ResultSet rs = dbop.getConfiguration(confId);
		String ModelofC;
		String name = null;
		String email;
		ComponentCatalog catalog = ComponentCatalog.getInstance();
		List<Component> addedComponent = new ArrayList<Component>();
		int cont = 0;
		try {
			while (rs.next()) {
				if (cont == 0) {
					name = rs.getString("Name");
					email = rs.getString("EmailU");
					cont++;
				}
				ModelofC = rs.getString("ModelofC");
				addedComponent.add(catalog.getComponentByModel(ModelofC));
			}
			return new Configuration(confId,addedComponent, name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Configuration> getConfigurationByEmail(String email) {

		ResultSet rs = dbop.getConfigurationByEmail(email);
		Map<Integer, Configuration> confMap = new HashMap<>();
		String modelofC;
		int id;
		String name;
		List<Configuration> configurations = null;
		ComponentCatalog catalog = ComponentCatalog.getInstance();
		Component c = null;
		try {
			while (rs.next()) {
				id = rs.getInt("Id");
				name = rs.getString("Name");
				if (!confMap.containsKey(id)) {
					confMap.put(id, new Configuration(id, name));
				}
				modelofC = rs.getString("ModelofC");
				c = catalog.getComponentByModel(modelofC);
				confMap.get(id).addComponentWithoutChecking(c);
			}

			configurations = new ArrayList<Configuration>(confMap.values());
			return configurations;
		} catch (SQLException e) {
			// Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return null;

	}

	public boolean addConfiguration(Configuration conf, Customer user) {
		List<String> type = new ArrayList<String>();
		List<String> model = new ArrayList<String>();
		Map<String,Integer> counter = new HashMap<String,Integer>();
		int count ;
		for (Component c : conf.getAddedComponents()) {
			if (!counter.containsKey(c.getModel())) {
				counter.put(c.getModel(), 1);
				type.add(c.getTypeComponent());
				model.add(c.getModel());
			}else {
				count=counter.get(c.getModel())+1;
				counter.put(c.getModel(),count);
			}
		}
		
		return dbop.addConfiguration(conf.getId(), conf.getName(), user.getEmail(), type, model,counter);

	}
	
	// TODO: Sistemare in modo che tutto viene fatto in modo atomico
	public boolean updateConfiguration(Configuration conf, Customer user) {
		boolean flag1 = this.removeConfiguration(conf.getId());
		boolean flag2 = false;
		if (flag1) {
			flag2 = this.addConfiguration(conf, user);
		}

		return flag2;

	}

	public boolean removeConfiguration(int confId) {

		return dbop.removeConfiguration(confId);

	}
	
	public int getLastUsedId() {
		ResultSet rs = dbop.getLastUsedId();
		int id = 0;		//cosi' se il result set e' vuoto perche' non ci sono id prendo 0 come ultimo id usato
		
		try {
			while (rs.next()) {
				id = rs.getInt("maxId");
			}
		} catch (SQLException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public String getOwnerMailByConfigurationId(int confId) {
		ResultSet rs=dbop.getOwnerMailByConfigurationId(confId);
		
		try {
			return rs.getString("EmailU");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
