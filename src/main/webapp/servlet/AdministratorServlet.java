package main.webapp.servlet;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.rythmengine.Rythm;

import main.services.persistence.PersistenceFacade;

@SuppressWarnings("serial")
public class AdministratorServlet extends MyServlet {

	public AdministratorServlet(String name, String path) {
		super(name, path);
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PersistenceFacade pf = PersistenceFacade.getIstance();
		String email = (String) request.getSession().getAttribute("email");
		ServletController controller = (ServletController) this.getServletConfig().getServletContext()
				.getAttribute(email + "_controller");
		
		boolean isAdmin = controller.getCustomer().isAdmin();
		String name = null;
		if (email != null) {
			// Se nella sessione esiste la mail, mi salvo tutte le info e carico il profilo
			if (!isAdmin) {
				response.getWriter().write(Rythm.render("403.html"));
			} else {
				response.getWriter().write(Rythm.render("administrator.html", pf.getTypeComponent()));
			}
		} else {
			// altrimenti reindirizzo al login
			response.sendRedirect("/login");
		}

	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//TODO aggiungere metodi JsonMessage
		String typeComponent = request.getParameter("typeComp");	
		PersistenceFacade pf = PersistenceFacade.getIstance();
		String model = "cristodiooooOOOOOOO";
		String type = "gpu";
		int price = 100;
		
		if (request.getPathInfo().equals("/addComp")) {
			System.out.println("Salva");

			pf.addComponent(model, type, price);


			
			for(Entry<String, String[]> name: request.getParameterMap().entrySet()) {
				//pf.addAttribute(type, model, name.getKey(), name.getValue()[0]);
				//System.out.println(name.getKey() + " - " +  name.getValue()[0]);
				JSONObject j = null;
				JSONParser jsonParser = new JSONParser();
				try {
					j = (JSONObject) jsonParser.parse(name.getKey());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				Iterator<String> o = j.keySet().iterator();
				String att;
				
				while(o.hasNext()) {
					att = o.next();
					pf.addAttribute(type, model, att, (String) j.get(att));
				}

			}
		}else {
			List<String> list = pf.getStandardAttributes(typeComponent);
			
			String json = "";
			json = JsonMessages.getJsonTypeComponentResponse(list);
			response.getWriter().write(json);
		}
	}
}
