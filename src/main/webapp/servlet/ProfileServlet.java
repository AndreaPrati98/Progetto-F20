package main.webapp.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rythmengine.Rythm;

import main.model.configurator.configuration.Configuration;
import main.model.customer.Customer;
import main.services.persistence.PersistenceFacade;

@SuppressWarnings("serial")
public class ProfileServlet extends MyServlet {
	
	public ProfileServlet(String name, String path) {
		super(name, path);
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		PersistenceFacade pf = PersistenceFacade.getIstance();
		String email = (String) request.getSession().getAttribute("email");
		ServletController controller = (ServletController) this.getServletConfig().getServletContext()
				.getAttribute(email + "_controller");
		
		String name = null;
		if (email != null && controller != null) {
			// Se nella sessione esiste la mail, mi salvo tutte le info e carico il profilo
			Customer c = controller.getCustomer();
			name = c.getName();
			String surname = c.getSurname();
			boolean isAdmin = c.isAdmin();
			// Da sostituire
			List<Configuration> conf;
			conf = pf.getConfigurationByEmail(email);
			response.getWriter().write(Rythm.render("profile.html", name, surname, email, isAdmin, conf, request));
		} else {
			// altrimenti reindirizzo al login
			response.sendRedirect("/logout");
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String email = (String) request.getSession().getAttribute("email");
		ServletController controller = (ServletController) this.getServletConfig().getServletContext()
				.getAttribute(email + "_controller");
		System.out.println(request.getPathInfo());
		if (request.getPathInfo().equals("/remove")) {
			remove(request, response, controller);
		}
		
		if(request.getPathInfo().equals("/unsubscribe")) {
			unsubscribe(request, response, controller);
		}
	}

	private void remove(HttpServletRequest request, HttpServletResponse response, ServletController controller) throws IOException {
		int confId = Integer.parseInt(request.getParameter("id"));
		// controller.removeConfiguration(confId);

		System.out.println(request.getParameterMap().keySet());
		
		if(controller.removeConfiguration(confId)) {
			System.out.println("Configurazione rimossa");
		}else {
			System.out.println("Configurazione non rimossa");
		}
		
		response.sendRedirect("/profile");
	}
	
	private void unsubscribe(HttpServletRequest request, HttpServletResponse response, ServletController controller) throws IOException {

		if(controller.removeUser()) {
			System.out.println("Utente disiscritto");
			response.sendRedirect("/logout");

		} else {
			System.out.println("Utente iscritto");
			response.sendRedirect("/profile");			
		}
	
	}
	
	private void changePassword(HttpServletRequest request, HttpServletResponse response, ServletController controller) throws IOException {
		String newPassword = request.getParameter("newPass")
		controller.changePassword(newPassword);
	}
	
}
