package main.services.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.model.configurator.constraint.AbstractConstraint;
import main.model.configurator.constraint.DimensionConstraint;
import main.model.configurator.constraint.EqualsConstraint;
import main.model.configurator.constraint.MaxConstraint;

public class RdbConstraintDAO implements InterfaceCostraintDAO {

	private RdbOperation dbop;

	public RdbConstraintDAO(RdbOperation dbop) {
		super();
		this.dbop = dbop;
	}
	
	/*
	@Override
	public AbstractConstraint getConstraint(String name, String typeOfComponent) {
		//  Auto-generated method stub
		return null;
	}*/

	@Override
	public List<AbstractConstraint> getAllConstraints() {
		ResultSet rs = dbop.getAllConstraints();
		List<AbstractConstraint> constraints = new ArrayList<>();
		String name, type;
		try {
			while(rs.next()) {
				AbstractConstraint c = null;
				name = rs.getString("Name");
				type = rs.getString("Type");
				if(type.equals("Max")) {
					c = new MaxConstraint(name);
				} else if (type.equals("Dimension")) {
					c = new DimensionConstraint(name);
				} else if(type.equals("Equals")) {
					c = new EqualsConstraint(name);
				}
				constraints.add(c);
			}
		} catch (SQLException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return constraints;
	}

	@Override
	public boolean addNewConstraint(String name, String type) {
		
		return dbop.addNewConstraint(name, type);
	}

	@Override
	public boolean removeConstraint(String name) {
		return dbop.RemoveConstraint(name);
	}

}
