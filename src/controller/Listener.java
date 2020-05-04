package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.component.Component;
import model.configurator.Configurator;
import view.TesterFrame;

/**
 * 
 * @author Ripari Irene
 *
 */
public class Listener implements ActionListener {

	private TesterFrame view;
	private Configurator model;

	/**
	 * 
	 * @param view
	 * @param model
	 */
	public Listener(TesterFrame view, Configurator model) {
		this.view = view;
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String[] bufferSplit = null;
		String buffer = null;
		String type = null;
		buffer = view.getComponentJList().getSelectedValue();
		bufferSplit = buffer.split("--");
		type = bufferSplit[0];
		buffer = bufferSplit[1];
		bufferSplit = buffer.split(":");
		if (e.getSource().equals(view.getAddComponentButton())) {
			if (model.addComponent(findComponent(type, bufferSplit[1]))) {
				System.out.println("c");
				view.getInfoLabel().setText("Componente aggiunto");
				view.getListModelAdded().addElement(view.getComponentJList().getSelectedValue());
			} else {
				view.getInfoLabel().setText("Componente incompatibile");
			}
		}

		if (e.getSource().equals(view.getRemoveComponentButtont())) {
			view.getListModelAdded().removeElementAt(view.getChosenJList().getSelectedIndex());
		}

	}

	private Component findComponent(String type,String name) {
		for (Component component : model.getCatalog().getComponentList()) {
			if (component.getTypeComponent().equals(type) && component.getAttributesMap().get("name").equals(name)) {
				return component;
			}
		}
		return null;
	}

}
