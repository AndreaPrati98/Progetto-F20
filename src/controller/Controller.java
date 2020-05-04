package controller;

import model.component.Component;
import model.configurator.Configurator;
import view.TesterFrame;

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
		String s = null;
		for (Component c : model.getCatalog().getComponentList()) {
			s = c.getTypeComponent()+"    name : " + c.getAttributesMap().get("name") + "    ";
			for (String keylist : c.getAttributesMap().keySet()) {
				if (c.getAttributesMap().get(keylist) != null && !keylist.equals("name")) {
					s = s + keylist + " : " + c.getAttributesMap().get(keylist) + "    ";
				}
			}
			view.getListModelCatalog().addElement(s);
			s = "";
		}
		addListener();

	}

	private void addListener() {
		view.getAddComponentButton().addActionListener(new Listener(view, model));
		view.getRemoveComponentButtont().addActionListener(new Listener(view, model));
	}

}
