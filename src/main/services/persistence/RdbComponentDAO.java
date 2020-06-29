package main.services.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import main.model.configurator.component.Attribute;
import main.model.configurator.component.Component;

/**
 *
 * @author Garau
 * @author Prati
 *
 */

public class RdbComponentDAO implements InterfaceComponentDAO {

	private RdbOperation dbop;

	public RdbComponentDAO(RdbOperation dbop) {
		this.dbop = dbop;
	}

	/**
	 * Used to fill the catalog.
	 * It first gets every component model, type and price, then gets its attributes,
	 * and return a list.
	 * 
	 * @return a list containing every component
	 * @see Component
	 * @see Attribute
	 * @see ComponentCatalog
	 */
	
	public List<Component> getAllComponent() {
		ResultSet allComp = dbop.getAllComponents();
		ResultSet comp;

		ArrayList<Component> c = new ArrayList<>();
		HashMap<String, Attribute> attributes;
		Attribute a;

		String model, typeOfComponent, stringPrice;
		String nameStdAtt, attValue, constraintName, category;
		boolean isPresentable, isBinding;
		double price = -1; // settato a -1 perchè è un valore non valido

		try {
			while (allComp.next()) {
				attributes = new HashMap<>();
				model = allComp.getString("Model");
				typeOfComponent = allComp.getString("TypeofC");
				stringPrice = allComp.getString("Price");
				comp = dbop.getAttributesByComponent(model, typeOfComponent);

				while (comp.next()) {
					nameStdAtt = comp.getString("NameStdAtt");
					attValue = comp.getString("AttValue");
					constraintName = comp.getString("ConstraintName");
					category = comp.getString("Category");
					if (comp.getInt("IsPresentable") == 1) {
						isPresentable = true;
					} else {
						isPresentable = false;
					}
					if (constraintName == null) {
						isBinding = false;

					} else {
						isBinding = true;

					}

					a = new Attribute(nameStdAtt, attValue, constraintName, isBinding, isPresentable, category);
					attributes.put(nameStdAtt, a);
				}

				try {
					price = Double.parseDouble(stringPrice);
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
				c.add(new Component(model, typeOfComponent, price, attributes));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return c;
	}
	
	public boolean addComponent(String model, String type, double price) {
		return dbop.addComponent(model, type, price);
	}
	
	public boolean removeComponent(String model, String type) {
		boolean result = dbop.removeComponent(model, type);	
		return result;
	}
		
	/**
	 * @return a list containing every component type
	 * @see Component
	 */
	
	public List<String> getTypeOfComponent(){
		ResultSet rs = dbop.getTypeComponents();
		List<String> typeComp = new ArrayList<>();
		String elem;
		try {
			while(rs.next()) {
				elem = rs.getString("type");
				typeComp.add(elem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			typeComp = null;
		}
		
		return typeComp;
	}

	
	/**
	 * @return a list containing every necessary component in a configuration
	 * @see Component
	 * @see Configuration
	 */
	@Override
	public List<String> getNeededComponents() {
		ResultSet rs = dbop.getNeededComponents();
		List<String> neededComp = new ArrayList<>();
		String elem;
		try {
			while(rs.next()) {
				elem = rs.getString("type");
				neededComp.add(elem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			neededComp = null;
		}
		
		return neededComp;
	}
	
	/**
	 * @return a list containing every standard attribute
	 * @see Component
	 * @see Attribute
	 */
	public List<String> getStandardAttributes(String typeComponent){
		ResultSet rs = dbop.getStandardAttributes(typeComponent);
		List<String> stdAtt = new ArrayList<>();
		String elem;
		try {
			while(rs.next()) {
				elem = rs.getString("Name");
				stdAtt.add(elem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			stdAtt = null;
		}
		
		return stdAtt;
	}
	
	public boolean addAttribute(String typeOfC, String modelOfC, String nameAtt, String attValue) {
		return dbop.addAttribute(typeOfC, modelOfC, nameAtt, attValue);
	}
	
	public boolean addStandardAttribute(String name, String typeOfC, String constraintName, String category, boolean isPresentable) {
		int isPres;
		if(isPresentable) {
			isPres = 1;
		} else {
			isPres = 0;
		}
		return dbop.addStandardAttribute(name, typeOfC, constraintName, category, isPres);	
	}
	
	public boolean addTypeComponent(String type, boolean isNeeded) {
		int isN;
		if(isNeeded) {
			isN = 1;
		} else {
			isN = 0;
		}
		return dbop.addTypeComponent(type, isN);
	}

}
