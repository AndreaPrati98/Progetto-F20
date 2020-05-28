package controller;

import servlet.ApplicationServer;
import servlet.HomeServlet;

public class Tester {

	public static void main(String[] args) {
		
		try {
			new ApplicationServer(8080, new HomeServlet()).start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
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
