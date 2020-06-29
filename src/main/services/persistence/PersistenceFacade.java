package main.services.persistence;

import java.util.List;

import main.model.configurator.component.Component;
import main.model.configurator.configuration.Configuration;
import main.model.configurator.constraint.AbstractConstraint;
import main.model.customer.Customer;
/**
 * This class is a singleton.
 */
public class PersistenceFacade {

	private InterfaceComponentDAO cdao;
	private InterfaceConfigurationDAO confdao;
	private InterfaceCostraintDAO cosdao;
	private InterfaceUserDAO udao;

	private static PersistenceFacade facade;
/**
	 *  ritorna un istanza della PersistenceFacade rendendola facilmente raggiungibile da qualunque punto del programma 
	 *  ricordiamo che  la PersistanceFacade è un Singelton 
	 * @return PersistenceFacade
	 */
	static public PersistenceFacade getIstance() {
		if (facade == null) {
			RdbOperation rdbOp = new RdbOperation();
			facade = new PersistenceFacade(rdbOp);
		}

		return facade;
	}
	
/**
 * istanziamo un ogetto per ogni interfaccia ci serviranno per i metodi successivi 
 * @param dbop
 * @see RdbOperation,InterfaceComponentDAO,InterfaceConfigurationDAO,InterfaceCostraintDAO,InterfaceUserDAO;
 */
	private PersistenceFacade(RdbOperation dbop) {
		// Non sarebbe meglio che sia una factory a restituirmi il DAO corretto? (roba
		// puramente da pattern)
		this.cdao = new RdbComponentDAO(dbop);
		this.confdao = new RdbConfigurationDAO(dbop);
		this.cosdao = new RdbConstraintDAO(dbop);
		this.udao = new RdbUserDAO(dbop);
		
	}
/**
 * ritorna una lista di tutti i componenti 
 * @return
 */
	public List<Component> getAllComponent() {
		return cdao.getAllComponent();
	}

	
	public List<String> getAdmin() {
		return udao.getAdmin();
	}
/**
 * usiamo il InterfaceUserDAO per aggiungere un amministratore al db
 * @param mail
 * @param decision
 * @return
 */
	public boolean addAdmin(String mail,boolean decision) {
		return udao.addAdmin(mail, decision);
	}
	/**
	 * eseguiamo i controlli sull'esistenza di un utente
	 * @param mail
	 * @return
	 */
	public boolean checkIfUserExist(String mail) {
		
		return udao.checkIfUserExist(mail);
	}
/**
 * prendiamo i componenti neccesari per una configurazione
 * @return
 */
	public List<String> getNeededComponents() {
		return cdao.getNeededComponents();
	}

	public List<String> getTypeComponent() {
		return cdao.getTypeOfComponent();
	}
	
/**
 * utenti e aministratori hanno la possibilità di cambiare il nome alla propria configurazione
 * @param confId
 * @param newName
 * @see InterfaceConfigurationDAO,RdbConfigurationDAO
 * @return
 */
	public boolean changeConfName(int confId, String newName) {
		return confdao.changeConfName(confId, newName);
	}
/**
 * @see InterfaceConfigurationDAO,RdbConfigurationDAO
 * @param confId
 * @return
 */
	public String getOwnerMailByConfigurationId(int confId) {
		return confdao.getOwnerMailByConfigurationId(confId);
	}
/**
 * ho la possibilità di recuperare una configurazione dato il suo id
 * @see InterfaceConfigurationDAO,RdbConfigurationDAO
 * @param confId
 * @return
 */
	public Configuration getConfiguration(int confId) {

		return confdao.getConfiguration(confId);

	}
	/**
	 * posso recuperare una configurazione tramite mail (solo se la mail è registrata nel db)
	 * @see InterfaceConfigurationDAO,RdbConfigurationDAO
	 * @param email
	 * @return
	 */

	public List<Configuration> getConfigurationByEmail(String email) {

		return confdao.getConfigurationByEmail(email);

	}
/**
 * user operation 
 * @see InterfaceUserDAO,RdbUserDAO
 * @param oldEmail
 * @param newEmail
 * @return
 */
	public boolean changeMail(String oldEmail, String newEmail) {

		return udao.changeEmail(oldEmail, newEmail);

	}
	/**
	 * user operation 
	 * @see InterfaceUserDAO,RdbUserDAO
	 * @param email
	 * @param newPassword
	 * @return
	 */

	public boolean changePassword(String email, String newPassword) {

		return udao.changePassword(email, newPassword);

	}
/**
 *  let's add a configuration to the database
 * @see InterfaceConfigurationDAO,RdbConfigurationDAO
 * @param conf
 * @param user
 * @return
 */
	public boolean addConfiguration(Configuration conf, Customer user) {

		return confdao.addConfiguration(conf, user);

	}
	/**
	 * add user to the database
	 * @see InterfaceUserDAO,RdbUserDAO
	 * @param name
	 * @param cognome
	 * @param email
	 * @param password
	 * @param isAdmin
	 * @return
	 */

	public boolean addUser(String name, String cognome, String email, String password, boolean isAdmin) {

		return udao.addUsers(name, cognome, email, password, isAdmin);

	}
	

	/**
	 * @see InterfaceUserDAO,RdbUserDAO
	 * @param email
	 * @return
	 */

	public boolean removeUser(String email) {
		return udao.removeUser(email);
	}
/**
 * @see InterfaceUserDAO,RdbUserDAO
 * @param email
 * @return
 */
	public Customer getUser(String email) {
		Customer c = udao.getCustomer(email);
		return c;

	}

//TODO aggiungere uml
	/**
	 * nel caso in cui un utente dimentichi la sua password tramite la mail possiamo recuperare la sua password
	 * @see InterfaceUserDAO,RdbUserDAO
	 * @param mail
	 * @return
	 */

	

	public String getPasswordByMail(String mail) {
		String psw = udao.getPasswordByMail(mail);
		return psw;
	}
	/**
	 * 
	 * @see InterfaceUserDAO,RdbUserDAO
	 * @param email
	 * @param password
	 * @return
	 */
	public boolean login(String email, String password) {

		return udao.login(email, password);
	}
	
/**
 * @see InterfaceConfigurationDAO,RdbConfigurationDAO
 * @param conf
 * @param user
 * @return
 */
	public boolean updateConfiguration(Configuration conf, Customer user) {

		return confdao.updateConfiguration(conf, user);

	}

	/**
	 * @see InterfaceConfigurationDAO,RdbConfigurationDAO
	 * @param confId
	 * @return
	 */
	public boolean removeConfiguration(int confId) {

		return confdao.removeConfiguration(confId);

	}
/**
 * possiamo recuperare la lista di tutti i Constraint presenti nel db
 * @see InterfaceCostraintDAO,RdbConstraintDAO
 * @return
 */
	public List<AbstractConstraint> getAllConstraints() {

		return cosdao.getAllConstraints();
	}
/**
 * @see InterfaceCostraintDAO,RdbConstraintDAO
 * @param name
 * @param type
 * @return
 */
	public boolean addNewConstraint(String name, String type) {

		return cosdao.addNewConstraint(name, type);
	}
/**
 * @see InterfaceCostraintDAO,RdbConstraintDAO
 * @param name
 * @return
 */
	public boolean removeConstraint(String name) {

		return cosdao.removeConstraint(name);
	}

/**
 * operazioni neccesarie che verranno svolte dal RdbComponentDAO per aggiungiere un componente nel Db 
 * @see InterfaceComponentDAO,RdbComponentDAO
 * @param model
 * @param type
 * @param price
 * @return
 */
	public boolean addComponent(String model, String type, double price) {
		return cdao.addComponent(model, type, price);
	}
/**
 * operazioni neccesarie che compie il RdbComponentDAO per rimuovere un componente dal db
 * @see InterfaceComponentDAO,RdbComponentDAO
 * @param model
 * @param type
 * @return
 */
	public boolean removeComponent(String model, String type) {
		return cdao.removeComponent(model, type);
	}

	/**
	 * @see InterfaceConfigurationDAO,RdbConfigurationDAO
	 * @return the last id used for the configuration table as an integer.
	 */
	public int getLastUsedId() {
		return confdao.getLastUsedId();
	}
/**
 * @see InterfaceComponentDAO,RdbComponentDAO
 * @param typeComponent
 * @return
 */
	public List<String> getStandardAttributes(String typeComponent) {
		return cdao.getStandardAttributes(typeComponent);
	}
/**
 * ogni componente di una configurazione ha degli attirbuti che devono essere salvati nel db sulla base di queste informazioni 
 * il programma rispetta se la configurazione rispetta tutti i vari vincoli e se  i componenti possono stare tra di loro 
 * @see InterfaceComponentDAO,RdbComponentDAO
 * @param typeOfC
 * @param modelOfC
 * @param nameAtt
 * @param attValue
 * @return
 */
	public boolean addAttribute(String typeOfC, String modelOfC, String nameAtt, String attValue) {
		return cdao.addAttribute(typeOfC, modelOfC, nameAtt, attValue);
	}
	/**
	 * @see InterfaceComponentDAO,RdbComponentDAO
	 * @param name
	 * @param typeOfC
	 * @param constraintName
	 * @param category
	 * @param isPresentable
	 * @return
	 */
	public boolean addStandardAttribute(String name, String typeOfC, String constraintName, String category,
			boolean isPresentable) {
		return cdao.addStandardAttribute(name, typeOfC, constraintName, category, isPresentable);
	}
/**
 * @see InterfaceComponentDAO,RdbComponentDAO
 * @param type
 * @param isNeeded
 * @return
 */
	public boolean addTypeComponent(String type, boolean isNeeded) {
		return cdao.addTypeComponent(type, isNeeded);
	}

}
