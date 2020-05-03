package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import view.TesterFrame;
import view.TesterFrame.TypeList;

public class Tester {
	
	public static void main(String[] args) {
	
		TesterFrame pippo = new TesterFrame("Frame di test");
		pippo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pippo.setVisible(true);
		
		pippo.getAddComponentButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				pippo.addElementJList(TypeList.ADDED_COMPONENT_LIST, "Processore bellissimo");
				System.out.println("fatto 1");
				pippo.addElementJList(TypeList.NEW_COMPONENT_LIST, "Scheda madre bellissima");
				System.out.println("fatto 2");
				pippo.addElementJList(TypeList.NEW_COMPONENT_LIST, "Case RGB");
				System.out.println("fatto 3");
			}
		});
		
	}
	
	

}
