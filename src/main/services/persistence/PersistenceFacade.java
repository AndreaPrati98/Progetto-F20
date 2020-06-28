package main.services.persistence;

import java.util.List;

import main.model.configurator.component.Component;
import main.model.configurator.configuration.Configuration;
import main.model.configurator.constraint.AbstractConstraint;
import main.model.customer.Customer;

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

	public List<String> getAdmin() {
		return udao.getAdmin();
	}

	public boolean addAdmin(String mail,boolean decision) {
		return udao.addAdmin(mail, decision);
	}
	
	public boolean checkIfUserExist(String mail) {
		
		return udao.checkIfUserExist(mail);
	}

	public List<String> getNeededComponents() {
		return cdao.getNeededComponents();
	}

	public List<String> getTypeComponent() {
		return cdao.getTypeOfComponent();
	}

	public boolean changeConfName(int confId, String newName) {
		return confdao.changeConfName(confId, newName);
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

	public boolean changePassword(String email, String newPassword) {

		return udao.changePassword(email, newPassword);

	}

	public boolean addConfiguration(Configuration conf, Customer user) {

		return confdao.addConfiguration(conf, user);

	}

	public boolean addUser(String name, String cognome, String email, String password, boolean isAdmin) {

		return udao.addUsers(name, cognome, email, password, isAdmin);

	}
	
	//TODO aggiungere uml
	public boolean removeUser(String email) {
		return udao.removeUser(email);
	}

	public Customer getUser(String email) {
		Customer c = udao.getCustomer(email);
		return c;

	}
	
	//TODO aggiungere uml
	public String getPasswordByMail(String mail) {
		String psw = udao.getPasswordByMail(mail);
		return psw;
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

	public List<AbstractConstraint> getAllConstraints() {

		return cosdao.getAllConstraints();
	}

	public boolean addNewConstraint(String name, String type) {

		return cosdao.addNewConstraint(name, type);
	}

	public boolean removeConstraint(String name) {

		return cosdao.removeConstraint(name);
	}

	public boolean addComponent(String model, String type, double price) {
		return cdao.addComponent(model, type, price);
	}

	public boolean removeComponent(String model, String type) {
		return cdao.removeComponent(model, type);
	}

	/**
	 * 
	 * @return the last id used for the configuration table as an integer.
	 */
	public int getLastUsedId() {
		return confdao.getLastUsedId();
	}

	public List<String> getStandardAttributes(String typeComponent) {
		return cdao.getStandardAttributes(typeComponent);
	}

	public boolean addAttribute(String typeOfC, String modelOfC, String nameAtt, String attValue) {
		return cdao.addAttribute(typeOfC, modelOfC, nameAtt, attValue);
	}

	public boolean addStandardAttribute(String name, String typeOfC, String constraintName, String category,
			boolean isPresentable) {
		return cdao.addStandardAttribute(name, typeOfC, constraintName, category, isPresentable);
	}

	public boolean addTypeComponent(String type, boolean isNeeded) {
		return cdao.addTypeComponent(type, isNeeded);
	}

}
