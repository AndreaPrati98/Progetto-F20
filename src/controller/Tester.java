package controller;

import java.util.ArrayList;
import java.util.List;


import servlet.ApplicationServer;
import servlet.HomeServlet;
import servlet.MyServlet;
import servlet.RegisterServlet;

public class Tester {

	public static void main(String[] args) {
		List<MyServlet> servlet = new ArrayList<MyServlet>();
		servlet.add(new HomeServlet("home","/"));
		servlet.add(new RegisterServlet("register", "/sign-in"));
		new ApplicationServer(8080, servlet).start();
	
		
		
		
		
		
		
		
		
		
		
		
		
//		List<String> neededComponents=new ArrayList<String>();
//		neededComponents.add("cpu");
//		neededComponents.add("ram");
//		neededComponents.add("mobo");
//		
//		Map<String,Boolean> singleComponents = new HashMap<String,Boolean>();
//		singleComponents.put("cpu", false);
//		singleComponents.put("mobo", false);
//		singleComponents.put("case", false);
//		
//		ComponentCatalog catalog = new ComponentCatalog();
//		
//		for (Component c : catalog.getComponentList()) {
//			System.out.println(c.getAttributesMap().get("modello").getValue());
//		}
//		System.out.println(catalog.getComponentList().size());
//		
		
		
		
		
	}

}
