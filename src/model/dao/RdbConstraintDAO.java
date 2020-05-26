package model.dao;

import java.util.List;

import model.component.constraint.AbstractConstraint;

public class RdbConstraintDAO implements InterfaceCostraintDAO {

	private RdbOperation dbop;

	public RdbConstraintDAO(RdbOperation dbop) {
		super();
		this.dbop = dbop;
	}

	@Override
	public AbstractConstraint getConstraint(String name, String typeOfComponent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AbstractConstraint> getAllConstraints() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addNewConstraint(AbstractConstraint constraint) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeConstraint(String name, String typeOfComponent) {
		// TODO Auto-generated method stub
		return false;
	}

}
