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

/**
 * Administrator Servlet is used to add or remove components and administrators from db
 */

@SuppressWarnings("serial")
public class AdministratorServlet extends MyServlet {
	/**
	 * @param name its name.
	 * @param path its url .
	 */
	public AdministratorServlet(String name, String path) {
		super(name, path);
	}
	
	/**
	 * 	Manage get requests.
	 *  Renders administrator.html if the user is logged in, otherwise redirects to
	 *  login, or 403 in case of errors.
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
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

	/**
	 *  Manages post requests.
	 *  Each request gets handled by an individual method. Each one of them is better documented later.
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO aggiungere metodi JsonMessage
		String typeComponent = request.getParameter("typeComp");
		PersistenceFacade pf = PersistenceFacade.getIstance();
		ComponentCatalog catalog = ComponentCatalog.getInstance();

		if (request.getPathInfo().equals("/addComp")) {
			addComponent(request, response, pf, catalog);
		} else if (request.getPathInfo().equals("/removeComp")) {
			removeComponent(request, response, pf, catalog);
		} else if (request.getPathInfo().equals("/getCompForm")) {
			getCompForm(request, response, pf, typeComponent);
		} else if (request.getPathInfo().equals("/getAllComp")) {
			getAllComp(request, response, typeComponent, catalog);
		} else if (request.getPathInfo().equals("/checkAdmin")) {
			checkIfUserExist(request, response, pf, true);
		} else if (request.getPathInfo().equals("/addAdmin")) {
			addAdmin(request, response, pf);
		} else if (request.getPathInfo().equals("/removeAdmin")) {
			removeAdmin(request, response, pf);
		}

	}
	/**
	 * Adds a component to db, using PersistenceFacade, and later updates the catalog
	 * in order to show the changes on the website.
	 * It first read the information about the component in a json (generated in javascript), 
	 * then executes the query to add a component, included in PersistenceFacade.
	 * 
	 * @see PersistenceFacade,ComponentCatalog
	 * @param request
	 * @param response
	 * @param pf
	 * @param catalog
	 * @throws IOException
	 */

	private void addComponent(HttpServletRequest request, HttpServletResponse response, PersistenceFacade pf,
			ComponentCatalog catalog) throws IOException {

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

			model = (String) j.get("name");
			type = (String) j.get("type");
			price = Double.parseDouble((String) j.get("price"));
			pf.addComponent(model, type, price);

			Iterator<String> o = j.keySet().iterator();
			String att;

			while (o.hasNext()) {
				att = o.next();
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
	 * Removes a component from db, using PersistenceFacade, and later updates the catalog.
	 * Splits information stored in a json (generated in javascript), and executes the query
	 * removing the component, included in PersistenceFacade.
	 * 
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

			pf.removeComponent(model, type);
		}

		catalog.refreshCatalog();
		response.sendRedirect("/administrator?tab=2");
	}

	/**
	 * Gets the information to dynamically generate the form used in addComponent().
	 * It first gets a list of every standard attribute of an input component (as its type)
	 * and then generates a json respones used in javascript later. 
	 * 
	 * @see PersistenceFacade
	 * @param request
	 * @param response
	 * @param pf
	 * @param typeComponent
	 * @throws IOException
	 */
	
	private void getCompForm(HttpServletRequest request, HttpServletResponse response, PersistenceFacade pf,
			String typeComponent) throws IOException {
		List<String> list = pf.getStandardAttributes(typeComponent);

		String json = "";
		json = JsonMessages.getJsonTypeComponentResponse(list);
		response.getWriter().write(json);
	}

	/**
	 * Gets the information to dynamically generate the form used in removeComponent().
	 * It first gets a list of every component having their type equal to the parameter typeComponent
	 * and then generates a json respones used in javascript later. 
	 * 
	 * @see ComponentCatalog
	 * @param request
	 * @param response
	 * @param typeComponent
	 * @param catalog
	 * @throws IOException
	 */
	
	private void getAllComp(HttpServletRequest request, HttpServletResponse response,
			String typeComponent, ComponentCatalog catalog) throws IOException {
		List<Component> list = catalog.getComponentListByType(typeComponent);
		String json = "";
		json = JsonMessages.getJsonAllTypeComponentResponse(list);
		response.getWriter().write(json);
	}

	
	/**
	 * Used to check if a user (identified by its email) exists, through an existing method in PersistenceFacade.
	 * Returns a positive json response if it exists, otherwise returns false.
	 * 
	 * @see PersistenceFacade
	 * @param request
	 * @param response
	 * @param pf
	 * @param doResponse
	 * @throws IOException
	 */
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
	 *  Used to give administrator privileges to a user.
	 *  It first checks if a given user exists using checkIfUserExist().
	 *  Grants the privileges if the user exists, shows an error if it does not.
	 *  
	 * @see PersistenceFacade, checkIfUserExist()
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
	 * Removes administrator privileges from a user.
	 * It first checks if a given user exists using checkIfUserExist().
	 * Removes the privileges if the user exists, shows an error if it does not.
	 * 
	 * @see PersistenceFacade, checkIfUserExist()
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
