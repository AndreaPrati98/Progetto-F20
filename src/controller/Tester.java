package controller;

import model.catalog.ComponentCatalog;
import model.configurator.Configurator;
import view.TesterFrame;

public class Tester {
	
	public static void main(String[] args) {

		ComponentCatalog catalog=new ComponentCatalog();
		Configurator model=new Configurator(catalog);
		TesterFrame view=new TesterFrame("Prova");
		Controller controller=new Controller(view, model);
		
//		TesterFrame pippo = new TesterFrame("Frame di test");
//		pippo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		pippo.setVisible(true);
//		
//		pippo.getAddComponentButton().addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				
//				pippo.addElementJList(TypeList.ADDED_COMPONENT_LIST, "Processore bellissimo");
//				System.out.println("fatto 1");
//				pippo.addElementJList(TypeList.NEW_COMPONENT_LIST, "Scheda madre bellissima");
//				System.out.println("fatto 2");
//				pippo.addElementJList(TypeList.NEW_COMPONENT_LIST, "Case RGB");
//				System.out.println("fatto 3");
//			}
//		});

	}

}
