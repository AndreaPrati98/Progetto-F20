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
import main.services.util.HashingPassword;

@SuppressWarnings("serial")
public class ProfileServlet extends MyServlet {
	
	public ProfileServlet(String name, String path) {
		super(name, path);
	}
/**
 * this method is used to manage a user's login if the user exist in db redirect to profile page, else it communicate the failure of the request
 * 
 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		PersistenceFacade pf = PersistenceFacade.getIstance();
		String email = (String) request.getSession().getAttribute("email");
		ServletController controller = (ServletController) this.getServletConfig().getServletContext()
				.getAttribute(email + "_controller");
		
		String name = null;
		if (email != null && controller != null) {
			
			Customer c = controller.getCustomer();
			name = c.getName();
			String surname = c.getSurname();
			boolean isAdmin = c.isAdmin();
			
			List<Configuration> conf;
			conf = pf.getConfigurationByEmail(email);
			response.getWriter().write(Rythm.render("profile.html", name, surname, email, isAdmin, conf, request));
		} else {
			
			response.sendRedirect("/logout");
		}
	}
/**
 * a user can remove or rename  his configuration
 */
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
		
		if (request.getPathInfo().equals("/changePassword")) {
			changePassword(request, response, controller);
		}
		
		if(request.getPathInfo().equals("/changeEmail")) {
			changeEmail(request, response, controller);
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
	
	//TODO aggiungere uml
	private void unsubscribe(HttpServletRequest request, HttpServletResponse response, ServletController controller) throws IOException {

		if(controller.removeUser()) {
			System.out.println("Utente disiscritto");
			response.sendRedirect("/logout");

		} else {
			System.out.println("Utente iscritto");
			response.sendRedirect("/profile");			
		}
	
	}

	//TODO aggiungere uml
	private void changePassword(HttpServletRequest request, HttpServletResponse response, ServletController controller) throws IOException {
		//Le password non sono ancora hashate
		String oldPassword = request.getParameter("oldPass");
		String newPassword = request.getParameter("newPass");
		
		HashingPassword hashingPassword = new HashingPassword();
		
		oldPassword = hashingPassword.getHashPsw(oldPassword);
		newPassword = hashingPassword.getHashPsw(newPassword);
		//invio al controller psw vecchia e psw nuova, lui gestirà il resto
		boolean isDone = controller.changePassword(newPassword, oldPassword);
		//TODO aggiungere alert
		if (!isDone) {
			System.out.println("Password non cambiata");
			response.sendRedirect("/profile");					
		} else {
			System.out.println("Password cambiata");
			response.sendRedirect("/logout");
		}
	}

	//TODO aggiungere uml
	private void changeEmail(HttpServletRequest request, HttpServletResponse response, ServletController controller) throws IOException {
		String oldEmail = request.getParameter("oldEmail");
		String newEmail = request.getParameter("newEmail");
		
		//invio al controller mail vecchia e mail nuova, lui gestirà il resto
		boolean isDone = controller.changeEmail(newEmail, oldEmail);
		//TODO aggiungere alert
		if (!isDone) {
			System.out.println("Email non cambiata");
			response.sendRedirect("/profile");					
		} else {
			System.out.println("Email cambiata");
			response.sendRedirect("/logout");
		}
	}
}
