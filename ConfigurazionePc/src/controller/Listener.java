package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.configurator.Configurator;
import view.TesterFrame;
/**
 * 
 * @author Ripari Irene
 *
 */
public class Listener implements ActionListener {
	
	private TesterFrame view;
	//private Configurator model;
	
	
	/**
	 * 
	 * @param view
	 * @param model
	 */
	public Listener(TesterFrame view, Configurator model) {
		this.view = view;
		//this.model = model;
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(view.getAddComponentButton())) {
			view.getListModelAdded().addElement(view.getComponentJList().getSelectedValue());
		}
		
		if(e.getSource().equals(view.getRemoveComponentButtont())) {
			view.getListModelAdded().removeElementAt(view.getChosenJList().getSelectedIndex());
		}
		
	}

}
