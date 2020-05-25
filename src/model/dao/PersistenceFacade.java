package model.dao;

import java.util.List;

import model.component.Component;

public class PersistenceFacade {
	
	private ComponentDAO cdao;
	private static PersistenceFacade facade;
	
	
	static public PersistenceFacade getIstance(){
		if(facade==null){
			RdbOperation rdbOp = new RdbOperation();
			facade = new PersistenceFacade(rdbOp);			
		}
		
		return facade;		
	}

	private PersistenceFacade(RdbOperation dbop) {
		this.cdao = new ComponentDAO(dbop);
	}

	

	public List<Component> getAllComponent(){
		return cdao.getAllComponent();
	}
}
