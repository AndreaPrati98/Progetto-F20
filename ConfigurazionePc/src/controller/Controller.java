package controller;

import model.component.Component;
import model.configurator.Configurator;
import view.TesterFrame;
/**
 * 
 * @author Irene Ripari
 *
 */
public class Controller {

	private TesterFrame view;
	private Configurator model;

	/**
	 * 
	 * @param view
	 * @param model
	 */
	public Controller(TesterFrame view, Configurator model) {
		this.view = view;
		this.model = model;
		String s=null;
		for (Component c : model.getCatalog().getComponentList()) {
			s=c.getAttributesMap().get("name");
			view.getListModelCatalog().addElement(s);
		}
		addListener();
		
	}
	
	private void addListener() {
		view.getAddComponentButton().addActionListener(new Listener(view, model));
		view.getRemoveComponenButtont().addActionListener(new Listener(view, model));
	}

}
