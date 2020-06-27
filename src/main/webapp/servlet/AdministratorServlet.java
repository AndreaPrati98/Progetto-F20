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

import main.model.configurator.ComponentCatalog;
import main.model.configurator.component.Component;
import main.services.persistence.PersistenceFacade;
import main.webapp.servlet.util.JsonMessages;

@SuppressWarnings("serial")
public class AdministratorServlet extends MyServlet {
/**
 * Adminstrator Servlet  needs to menage the operation like add or remove component 
 * from a configuration or promote an user administrator or not.
 * this servlet communicate with model in detail catolog end comunicate with database  thanks to PersistenceFacade
 * enable the  exchange of data
 * 
 * @param name
 * @param path
 */
	public AdministratorServlet(String name, String path) {
		super(name, path);
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PersistenceFacade pf = PersistenceFacade.getIstance();
		String email = (String) request.getSession().getAttribute("email");
		ServletController controller = (ServletController) this.getServletConfig().getServletContext()
				.getAttribute(email + "_controller");
		String tab = request.getParameter("tab");

		if (email != null && controller != null) {
			boolean isAdmin = controller.getCustomer().isAdmin();

			// Se nella sessione esiste la mail, mi salvo tutte le info e carico il profilo
			if (!isAdmin) {
				response.getWriter().write(Rythm.render("403.html"));
			} else {
				response.getWriter().write(Rythm.render("administrator.html", tab, pf.getAllConstraints(),
						pf.getAdmin(), pf.getTypeComponent()));
			}
		} else {
			// altrimenti reindirizzo al login
			response.sendRedirect("/login");
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO aggiungere metodi JsonMessage
		String typeComponent = request.getParameter("typeComp");
		PersistenceFacade pf = PersistenceFacade.getIstance();
		ComponentCatalog catalog = ComponentCatalog.getInstance();
		String model = null;
		String type = null;
		double price = 0;

		if (request.getPathInfo().equals("/addComp")) {
			addComponent(request, response, pf, catalog);
		} else if (request.getPathInfo().equals("/removeComp")) {
			removeComponent(request, response, pf, catalog);
		} else if (request.getPathInfo().equals("/getCompForm")) {
			getCompForm(request, response, pf, typeComponent);
		} else if (request.getPathInfo().equals("/getAllComp")) {
			getAllComp(request, response, pf, typeComponent, catalog);
		} else if (request.getPathInfo().equals("/checkAdmin")) {
			checkIfUserExist(request, response, pf, true);
		} else if (request.getPathInfo().equals("/addAdmin")) {
			addAdmin(request, response, pf);
		} else if (request.getPathInfo().equals("/removeAdmin")) {
			removeAdmin(request, response, pf);
		}

	}
	/**
	 * add component in database thanks to PersistenceFacade 
	 * @see PersistenceFacade,ComponentCatalog
	 * @param request
	 * @param response
	 * @param pf
	 * @param catalog
	 * @throws IOException
	 */

	private void addComponent(HttpServletRequest request, HttpServletResponse response, PersistenceFacade pf,
			ComponentCatalog catalog) throws IOException {
		System.out.println("Salva");

		String model = null;
		String type = null;
		double price = 0;

		for (Entry<String, String[]> name : request.getParameterMap().entrySet()) {
			JSONObject j = null;
			JSONParser jsonParser = new JSONParser();
			try {
				j = (JSONObject) jsonParser.parse(name.getKey());
			} catch (ParseException e) {
				e.printStackTrace();
			}

			System.out.println(j);

			model = (String) j.get("name");
			type = (String) j.get("type");
			System.out.println("Tipo � " + type);
			price = Double.parseDouble((String) j.get("price"));
			pf.addComponent(model, type, price);

			Iterator<String> o = j.keySet().iterator();
			String att;

			while (o.hasNext()) {
				att = o.next();
				System.out.println(att);
				if (!att.equals("price") && !att.equals("name") && !att.equals("type")) {
					pf.addAttribute(type, model, att, (String) j.get(att));
				}
			}

			catalog.refreshCatalog();
		}
		String json = "";
		json = JsonMessages.getJsonOkResponse();
		response.getWriter().write(json);
	}
	/**
	 * remove a component from db 
	 * @see ComponentCatalog , PersistenceFacade
	 * @param request
	 * @param response
	 * @param pf
	 * @param catalog
	 * @throws IOException
	 */

	private void removeComponent(HttpServletRequest request, HttpServletResponse response, PersistenceFacade pf,
			ComponentCatalog catalog) throws IOException {

		String[] results = request.getParameterValues("checkBox");
		String model, type;

		for (String result : results) {
			model = result.split("@")[0];
			type = result.split("@")[1];

			System.out.println(model + " - " + type);

			pf.removeComponent(model, type);
		}

		catalog.refreshCatalog();
		response.sendRedirect("/administrator?tab=2");
	}

	private void getCompForm(HttpServletRequest request, HttpServletResponse response, PersistenceFacade pf,
			String typeComponent) throws IOException {
		List<String> list = pf.getStandardAttributes(typeComponent);

		String json = "";
		json = JsonMessages.getJsonTypeComponentResponse(list);
		response.getWriter().write(json);
	}

	private void getAllComp(HttpServletRequest request, HttpServletResponse response, PersistenceFacade pf,
			String typeComponent, ComponentCatalog catalog) throws IOException {
		List<Component> list = catalog.getComponentListByType(typeComponent);
		String json = "";
		json = JsonMessages.getJsonAllTypeComponentResponse(list);
		response.getWriter().write(json);
	}

	private boolean checkIfUserExist(HttpServletRequest request, HttpServletResponse response, PersistenceFacade pf,
			boolean doResponse) throws IOException {
		String mail = request.getParameter("email");
		boolean flag = pf.checkIfUserExist(mail);
		if (doResponse) {
			String json = JsonMessages.getJsonNewTypeComponentResponse(flag);
			response.getWriter().write(json);
		}
		return flag;
	}
	/**
	 *  operation for add administrator users in data base, administrator users have access rights and change the data

	 * @param request
	 * @param response
	 * @param pf
	 * @throws IOException
	 */

	private void addAdmin(HttpServletRequest request, HttpServletResponse response, PersistenceFacade pf)
			throws IOException {

		String mail, json;

		boolean userFlag = checkIfUserExist(request, response, pf, false);
		if (userFlag) {
			mail = request.getParameter("email");
			json = JsonMessages.getJsonStringResponse(pf.addAdmin(mail, true), "Admin added");
			response.getWriter().write(json);
		} else {
			json = JsonMessages.getJsonStringResponse(false, "check email");
			response.getWriter().write(json);
		}

	}
	/**
	 * remove the administeator righs from a user 
	 * @param request
	 * @param response
	 * @param pf
	 * @throws IOException
	 */

	private void removeAdmin(HttpServletRequest request, HttpServletResponse response, PersistenceFacade pf)
			throws IOException {
		String mail, json;
		boolean userFlag = checkIfUserExist(request, response, pf, false);
		if (userFlag) {
			mail = request.getParameter("email");
			json = JsonMessages.getJsonStringResponse(pf.addAdmin(mail, false), "Admin removed");
			response.getWriter().write(json);
		} else {
			json = JsonMessages.getJsonStringResponse(false, "check email");
			response.getWriter().write(json);
		}
	}
}
