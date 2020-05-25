package model.dao;

import java.util.ArrayList;
import java.util.List;

import model.component.Component;
import model.component.constraint.AbstractConstraint;
import model.component.constraint.DimensionConstraint;
import model.component.constraint.EqualsConstraint;
import model.component.constraint.MaxConstraint;

public class PersistenceFacade {
	
	private InterfaceComponentDAO cdao;
	private static PersistenceFacade facade;
	
	
	static public PersistenceFacade getIstance(){
		if(facade == null){
			RdbOperation rdbOp = new RdbOperation();
			facade = new PersistenceFacade(rdbOp);			
		}
		
		return facade;		
	}

	private PersistenceFacade(RdbOperation dbop) {
		//Non sarebbe meglio che sia una factory a restituirmi il DAO corretto? (roba puramente da pattern)
		this.cdao = new ComponentDAO(dbop);
	}

	

	public List<Component> getAllComponent(){
		return cdao.getAllComponent();
	}
	
	public List<AbstractConstraint> getAllConstraints(){
		//Codice di prova
		List<AbstractConstraint> list = new ArrayList<AbstractConstraint>();
		AbstractConstraint c1 = new MaxConstraint("tdp");
		AbstractConstraint c2 = new EqualsConstraint("tdp");
		AbstractConstraint c3 = new DimensionConstraint("tdp");
		list.add(c1);
		list.add(c2);
		list.add(c3);
		
		if(list.isEmpty())
			return null;
		
		return list;
	}
	
}
