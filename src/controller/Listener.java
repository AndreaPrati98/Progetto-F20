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
		
		if (e.getSource().equals(view.getAddComponentButton())) {
			buffer = view.getComponentJList().getSelectedValue();
			bufferSplit = buffer.split("--");
			type = bufferSplit[0];
			buffer = bufferSplit[1];
			bufferSplit = buffer.split(":");
			if (model.addComponent(findComponent(type, bufferSplit[1]))) {
				view.getInfoLabel().setText("Componente aggiunto");
				view.getListModelAdded().addElement(view.getComponentJList().getSelectedValue());
			} else {
			//	view.getInfoLabel().setText("Componente incompatibile: " + model.getConfiguration().getConstraintErrorsNames());
			}
		}	
		
		if (e.getSource().equals(view.getRemoveComponentButtont())) {
			buffer = view.getChosenJList().getSelectedValue();
			bufferSplit = buffer.split("--");
			type = bufferSplit[0];
			buffer = bufferSplit[1];
			bufferSplit = buffer.split(":");
			System.out.println(findComponent(type, bufferSplit[1]));
			model.getConfiguration().removeComponent(findComponent(type, bufferSplit[1]));
			view.getListModelAdded().removeElementAt(view.getChosenJList().getSelectedIndex());
		}
		
		if (e.getSource().equals(view.getCheckButton())) {
			if (model.checkConf()) {
				view.getInfoLabel().setText("CONFIGURAZIONE VALIDA");
			}else {
				view.getInfoLabel().setText("CONFIGURAZIONE NON VALIDA");
			}
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
