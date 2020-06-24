package main.webapp.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rythmengine.Rythm;

import main.model.configurator.configuration.Configuration;
import main.model.people.costumer.Customer;
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
		
		String name = null;
		if (email != null) {
			boolean isAdmin = controller.getCustomer().isAdmin();

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
		String type = request.getParameter("typeComp");
		System.out.println("POOOST" + type);
		
		String json = "";
		json = JsonMessages.getJsonTypeComponentResponse();
		response.getWriter().write(json);
		//response.getWriter().write(Rythm.render("login.html", false));
	}
}
