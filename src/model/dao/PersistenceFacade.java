package model.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.component.Component;
import model.component.constraint.AbstractConstraint;
import model.component.constraint.DimensionConstraint;
import model.component.constraint.EqualsConstraint;
import model.component.constraint.MaxConstraint;
import model.configuration.Configuration;

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

	public List<AbstractConstraint> getAllConstraints() {
		// Codice di prova
		List<AbstractConstraint> list = new ArrayList<AbstractConstraint>();
		AbstractConstraint c1 = new MaxConstraint("tdp");
		AbstractConstraint c2 = new EqualsConstraint("tdp");
		AbstractConstraint c3 = new DimensionConstraint("tdp");
		list.add(c1);
		list.add(c2);
		list.add(c3);

		if (list.isEmpty())
			return null;

		return list;
	}

	public Configuration getConfiguration(String confId) {

		return confdao.getConfiguration(confId);

	}

	public Configuration getConfigurationByEmail(String email) {

		return confdao.getConfigurationByEmail(email);

	}

	public boolean addConfiguration(int id, String name, String email) {

		return confdao.addConfiguration(id, name, email);

	}
	
	public boolean addUser(String name,String cognome, String email,String password) {

		return confdao.addUsers(name, cognome, email, password);

	}

	public boolean updateConfiguration(int id, String name, String email) {

		return confdao.updateConfiguration(id, name, email);

	}

	public boolean removeConfiguration(int confId) {

		return confdao.removeConfiguration(confId);

	}
	
	// Tanto il constraintChecker legge tutti i constraint, non ne legge uno solo
	/*
	public AbstractConstraint getConstraint(String name, String typeOfComponent) {
		return cosdao.getConstraint(name, typeOfComponent);
	}*/

	public List<AbstractConstraint> getAllConstraint() {

		return cosdao.getAllConstraints();
	}

	public boolean addNewConstraint(AbstractConstraint constraint) {

		return cosdao.addNewConstraint(constraint);
	}

	public boolean removeConstraint(String name, String typeOfComponent) {

		return cosdao.removeConstraint(name, typeOfComponent);
	}

}
