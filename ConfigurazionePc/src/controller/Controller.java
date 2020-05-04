package controller;

import model.component.Component;
import model.configurator.Configurator;
import view.TesterFrame;

/**
 * 
 * @author Irene Ripari
 * @author Capici Alessandro
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
		String s = null;
		for (Component c : model.getCatalog().getComponentList()) {
			for (String keylist : c.getAttributesMap().keySet()) {
				s = c.getTypeComponent()+"    name : " + c.getAttributesMap().get("name") + "    ";
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
		view.getRemoveComponenButtont().addActionListener(new Listener(view, model));
	}

}
