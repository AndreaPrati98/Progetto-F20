package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.component.Attribute;
import model.component.Component;

public class RdbComponentDAO implements InterfaceComponentDAO {

	private RdbOperation dbop;

	public RdbComponentDAO(RdbOperation dbop) {
		this.dbop = dbop;
	}

	@Override
	public List<Component> getAllComponent() {
		ResultSet rs = dbop.getAllComponents();
		List<Component> listComponent = new ArrayList<Component>();
		Map<String, Attribute> map = new HashMap<String, Attribute>();
		Attribute at = null;
		String bufferModel = "";
		String typeBuffer = "";
		String tipo, modello, prezzo, nome, valore;
		boolean first = false;
		try {
			while (rs.next()) { // printing table rows until table finishes
				tipo = rs.getString("TypeofC");
				modello = rs.getString("Model");
				prezzo = rs.getString("Price");
				nome = rs.getString("Name");
				valore = rs.getString("AttValue");
				

				if (!bufferModel.equals(modello)) {
					first = false;
					listComponent.add(new Component(typeBuffer, map));
					map = new HashMap<String, Attribute>();
					typeBuffer = tipo;
				}
				
				if (!first) {
					map.put("modello", new Attribute("modello", modello, false, true));
					map.put("prezzo", new Attribute("prezzo", prezzo, false, true));
					bufferModel = modello;
					typeBuffer = tipo;
					first = true;
				}

				
				

				at = new Attribute(nome, valore, true, true);
				map.put(nome, at);

			}
			listComponent.add(new Component(typeBuffer, map));
			listComponent.remove(0);
			return listComponent;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
