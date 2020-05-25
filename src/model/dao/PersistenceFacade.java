package model.dao;

import java.util.List;

import model.component.Component;

public class PersistenceFacade {
	
	private ComponentDAO cdao;
	
	

	public PersistenceFacade(RdbOperation dbop) {
		this.cdao = new ComponentDAO(dbop);
	}



	public List<Component> getAllComponent(){
		return cdao.getAllComponent();
	}
}
