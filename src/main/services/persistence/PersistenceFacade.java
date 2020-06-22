package main.services.persistence;

import java.util.List;

import main.model.configurator.component.Component;
import main.model.configurator.configuration.Configuration;
import main.model.configurator.constraint.AbstractConstraint;
import main.model.people.costumer.Customer;

public class PersistenceFacade {

	private InterfaceComponentDAO cdao;
	private InterfaceConfigurationDAO confdao;
	private InterfaceCostraintDAO cosdao;
	private InterfaceUserDAO udao;

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
		this.udao = new RdbUserDAO(dbop);
	}

	public List<Component> getAllComponent() {
		return cdao.getAllComponent();
	}

	public List<String> getNeededComponents() {
		return cdao.getNeededComponents();
	}

	public List<String> getTypeComponent() {
		return cdao.getTypeOfComponent();
	}

	public String getOwnerMailByConfigurationId(int confId) {
		return confdao.getOwnerMailByConfigurationId(confId);
	}

	public Configuration getConfiguration(int confId) {

		return confdao.getConfiguration(confId);

	}

	public List<Configuration> getConfigurationByEmail(String email) {

		return confdao.getConfigurationByEmail(email);

	}
	
	public boolean changeMail(String oldEmail, String newEmail) {

		return udao.changeEmail(oldEmail, newEmail);

	}
	public boolean changePassword(String oldPassword, String newPassword) {

		return udao.changePassword(oldPassword, newPassword);

	}

	public boolean addConfiguration(Configuration conf, Customer user) {

		return confdao.addConfiguration(conf, user);

	}

	public boolean addUser(String name, String cognome, String email, String password, boolean isAdmin) {

		return udao.addUsers(name, cognome, email, password, isAdmin);

	}

	public Customer getUser(String email) {
		Customer c = udao.getCustomer(email);
		return c;

	}

	public boolean login(String email, String password) {

		return udao.login(email, password);
	}

	public boolean updateConfiguration(Configuration conf, Customer user) {

		return confdao.updateConfiguration(conf, user);

	}

	public boolean removeConfiguration(int confId) {

		return confdao.removeConfiguration(confId);

	}

	// Tanto il constraintChecker legge tutti i constraint, non ne legge uno solo
	/*
	 * public AbstractConstraint getConstraint(String name, String typeOfComponent)
	 * { return cosdao.getConstraint(name, typeOfComponent); }
	 */

	public List<AbstractConstraint> getAllConstraints() {

		return cosdao.getAllConstraints();
	}

	public boolean addNewConstraint(String name, String type) {

		return cosdao.addNewConstraint(name, type);
	}

	public boolean removeConstraint(String name) {

		return cosdao.removeConstraint(name);
	}

	/**
	 * 
	 * @return the last id used for the configuration table as an integer.
	 */
	public int getLastUsedId() {
		return confdao.getLastUsedId();
	}

}
