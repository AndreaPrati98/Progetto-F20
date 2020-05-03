package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.configurator.Configurator;
import view.TesterFrame;

public class Listener implements ActionListener {
	
	private TesterFrame view;
	private Configurator model;
	
	

	public Listener(TesterFrame view, Configurator model) {
		this.view = view;
		this.model = model;
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(view.getAddComponentButton())) {
			System.out.println("daad");
		}
		
		if(e.getSource().equals(view.getRemoveComponenButtont())) {
			
		}
		
	}

}
