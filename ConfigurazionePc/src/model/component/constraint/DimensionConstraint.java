package model.component.constraint;

import java.util.ArrayList;
import java.util.List;

import model.component.Component;

/**
 * 
 * @author Capici Alessandro
 *
 */
public class DimensionConstraint implements Constraint {

	private String name;
	private double value;
	private int referenceIndex;

	public DimensionConstraint(String name, double value, int referenceIndex) {
		super();
		this.name = name;
		this.value = value;
		this.referenceIndex = referenceIndex;
	}

	@Override
	public String getConstraintName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	/**
	 * Vede se il nuovo componente e conforme alle compatibilità ,controllando prima se deve essere inserito
	 * o deve ospitare e poi maggiorandolo o minorandolo rispetto agli  opportuni valori massimi o minimi
	 * usa sia getNestedType che getIEDimension
	 *
	 * @param component type:{@link Component}
	 * @return true if the component will respect the constraint,false if it will not respect the costraint
	 */
	public boolean checkList(List<Component> components) {
		// TODO Auto-generated method stub
		List<Constraint> lista;
		List<Double> checkvalue;
		boolean flag = true;
		String buffer = null;
		for (Component component : components) {
			lista = component.getConstraints();
			for (Constraint l : lista) {
				if (this.name.equals(l.getConstraintName())) {
					buffer = getNestedType(component);
					checkvalue = getIEDimension(components, buffer, this.referenceIndex);
					if (buffer.toUpperCase().equals("INTERNAL")) {
						for (int i = 0; i < checkvalue.size(); i++) {
							if (this.getValue() > checkvalue.get(i)) {
								flag = false;
							}
						}
					}
					if (buffer.toUpperCase().equals("EXTERNAL")) {
						for (int i = 0; i < checkvalue.size(); i++) {
							if (this.getValue() < checkvalue.get(i)) {
								flag = false;
							}
						}
					}
				}
			}
		}
		return flag;
	}
	/**
	 * Ritorna se il componente in questione deve essere inserito o deve ospitare un altro componente
	 * @param c (Component) {@link Component}
	 * @return INTERNAL or EXTERNAL
	 */
	private String getNestedType(Component c) { // sarebbe meglio creare un enum
		String buffer = null;
		String attributes = c.getAttributesMap().get(c.getTypeComponent());
		String[] split = attributes.split("\t"); // po si decidera la formattazione
		for (String s : split) {
			if (s.toUpperCase().equals("EXTERNAL") || s.toUpperCase().equals("INTERNAL")) {
				buffer = s;
				break;
			}
		}
		return buffer;

	}

	/**
	 * Ritorna tutte le dimensioni da rispettare , fa uso a sua volta di GetNestedType 
	 * @param component tyoe:{@link Component}
	 * @param b type:String
	 * @param ri type:Integer
	 * @return
	 */
	private List<Double> getIEDimension(List<Component> component, String b, int ri) {
		List<Double> dim = new ArrayList<Double>();
		List<Constraint> lista;
		for (Component c : component) {
			lista = c.getConstraints();
			String buffer = getNestedType(c);
			for (Constraint l : lista) { // questo cast si può eliminare se si cambia il modellodelle classi,chidere
											// sabato alla riunione
				if (this.name.equals(l.getConstraintName())
						&& ((DimensionConstraint) l).getReferenceIndex() == this.referenceIndex && !buffer.equals(b)) {
					Double d = ((DimensionConstraint) l).getValue(); // anche questo cast si può eliminare
					dim.add(d);
				}
			}
		}
		return dim;

	}
	/**
	 * 
	 * @return referenceIndex
	 */
	public int getReferenceIndex() {
		return referenceIndex;
	}
	/**
	 * 
	 * @return value
	 */
	public double getValue() {
		return value;
	}

}
