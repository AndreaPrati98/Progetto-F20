package model;

import javax.swing.JFrame;

import view.TesterFrame;

public class Tester {
	public static void main(String[] args) {
	
		System.out.println("prova stefano "); // prova
		
		TesterFrame pippo = new TesterFrame("Frame di test");
		pippo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pippo.setVisible(true);
		
	}
}
