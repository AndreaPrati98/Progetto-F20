package model.dao;


import java.util.List;

import model.component.Component;
import model.component.constraint.AbstractConstraint;
import model.configuration.Configuration;
import model.customer.Customer;

public class PersistenceFacade {

	private InterfaceComponentDAO cdao;
	private InterfaceConfigurationDAO confdao;
	private InterfaceCostraintDAO cosdao;
	private static PersistenceFacade facade;

	static public PersistenceFacade getIstance() {
		if (facade == null) {
			RdbOperation rdbOp = new RdbOperation();
			facade = new PersistenceFacade(rdbOp);
		}

		return facade;
	}

	private PersistenceFacade(RdbOperation dbop) {
		// Non sarebbe meglio che sia una factory a restituirmi il DAO corretto? (roba
		// puramente da pattern)
		this.cdao = new RdbComponentDAO(dbop);
		this.confdao = new RdbConfigurationDAO(dbop);
		this.cosdao = new RdbConstraintDAO(dbop);
	}

	public List<Component> getAllComponent() {
		return cdao.getAllComponent();
	}


	public Configuration getConfiguration(int confId) {

		return confdao.getConfiguration(confId);

	}

	public List<Configuration> getConfigurationByEmail(String email) {

		return confdao.getConfigurationByEmail(email);

	}

	public boolean addConfiguration(Configuration conf,Customer user) {

		return confdao.addConfiguration(conf,user);

	}
	
	public boolean addUser(String name,String cognome, String email,String password, boolean isAdmin) {

		return confdao.addUsers(name, cognome, email, password,isAdmin);

	}

	public boolean updateConfiguration(Configuration conf, Customer user) {

		return confdao.updateConfiguration(conf ,user);

	}

	public boolean removeConfiguration(int confId) {

		return confdao.removeConfiguration(confId);

	}
	
	// Tanto il constraintChecker legge tutti i constraint, non ne legge uno solo
	/*
	public AbstractConstraint getConstraint(String name, String typeOfComponent) {
		return cosdao.getConstraint(name, typeOfComponent);
	}*/

	public List<AbstractConstraint> getAllConstraints() {

		return cosdao.getAllConstraints();
	}

	public boolean addNewConstraint(String name, String type) {

		return cosdao.addNewConstraint(name, type);
	}

	public boolean removeConstraint(String name) {

		return cosdao.removeConstraint(name);
	}
	
	public int getLastUsedId() {
		return confdao.getLastUsedId();
	}

}
