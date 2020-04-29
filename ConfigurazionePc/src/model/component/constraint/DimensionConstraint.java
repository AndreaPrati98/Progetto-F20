package model.component.constraint;

import java.util.ArrayList;
import java.util.List;

import model.component.Component;

// Capici Alessandro
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

	public int getReferenceIndex() {
		return referenceIndex;
	}

	public double getValue() {
		return value;
	}

}
