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

public class ProfileServlet extends MyServlet{

	public ProfileServlet(String name, String path) {
		super(name, path);
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		PersistenceFacade pf = PersistenceFacade.getIstance();
		String email = (String) request.getSession().getAttribute("email");

		if(email != null) {
			//Se nella sessione esiste la mail, mi salvo tutte le info e carico il profilo
			Customer c = pf.getUser(email);
			String name = c.getName();
			String surname = c.getSurname();
			//Da sostituire
			List<Configuration> conf;
			conf =pf.getConfigurationByEmail(email);
			response.getWriter().write(Rythm.render("profile.html", name, surname, email, conf, request));
		}else {
			//altrimenti reindirizzo al login
			response.sendRedirect("/login");
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		// ServletController controller = (ServletController)
		// request.getSession().getAttribute("controller");
		String email = (String) request.getSession().getAttribute("email");
		System.out.println(email);
		if (email == null) {
			response.sendRedirect("/login");
			return;
		}

		ServletController controller = new ServletController();
		// Se il controller è nullo (cosa che non dovrebbe succedere poichè viene
		// istanziato
		// durante la login, vuol dire che qualcuno sta facendo una cattiva post e
		// quindi lo
		// redirigo alla logout
		if (controller == null) {
			// Devo fare in modo di inviare di risposta come ajax un erorre
			// e se trovo quell'errore invio un json che venendo letto lato
			// client dal javascript poi forza a sloggare e poi loggare.
			System.out.println("ROBE BRUTTE");
			return;
		}

		// Prende solo /add anche se il path completo è /configuration/add
		if (request.getPathInfo().equals("/remove")) {
			System.out.println("perfect");
			remove(request, response, controller);
		}else {
			System.out.println("Fuck");
		}
	}
	
	private void remove(HttpServletRequest request, HttpServletResponse response, ServletController controller) {
		System.out.println("OTTIMO");
		String confId = request.getParameter("id");
		//controller.removeConfiguration(confId);
		
		String json = "";
		if (controller.removeConfiguration(confId)) {
			json = JsonMessages.getJsonRemoveConfigurationResponse(confId);
		} else {
			json = JsonMessages.getJsonNotOkResponse();
		}

		System.out.println(json);
	}
}
