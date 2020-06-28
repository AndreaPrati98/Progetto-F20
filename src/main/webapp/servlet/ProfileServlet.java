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

/**
 * Servlet used to show profile page, containg information about the user, such
 * as his name, and his saved configurations. The user can edit or create a
 * configuration, change his password, and unsubscribe.
 */

@SuppressWarnings("serial")
public class ProfileServlet extends MyServlet {

	private static final String NEW_EMAIL = "newEmail";
	private static final String OLD_EMAIL = "oldEmail";
	private static final String NEW_PASS = "newPass";
	private static final String OLD_PASS = "oldPass";
	private static final String PROFILE = "/profile";
	private static final String ID = "id";
	private static final String CHANGE_EMAIL = "/changeEmail";
	private static final String CHANGE_PASSWORD = "/changePassword";
	private static final String UNSUBSCRIBE = "/unsubscribe";
	private static final String REMOVE = "/remove";
	private static final String LOGOUT = "/logout";
	private static final String PROFILE_HTML = "profile.html";
	private static final String _CONTROLLER = "_controller";
	private static final String EMAIL = "email";

	public ProfileServlet(String name, String path) {
		super(name, path);
	}

	/**
	 * Manages get requests. If the user is logged in renders profile, otherwise
	 * redirects to login. Shows user name, surname, email, saved configurations. If
	 * user is also an administrator, adds a section in the html navbar that
	 * redirects to the specific page.
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws ServletException
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		PersistenceFacade pf = PersistenceFacade.getIstance();
		String email = (String) request.getSession().getAttribute(EMAIL);
		ServletController controller = (ServletController) this.getServletConfig().getServletContext()
				.getAttribute(email + _CONTROLLER);

		String name = null;
		if (email != null && controller != null) {

			Customer c = controller.getCustomer();
			name = c.getName();
			String surname = c.getSurname();
			boolean isAdmin = c.isAdmin();

			List<Configuration> conf;
			conf = pf.getConfigurationByEmail(email);
			response.getWriter().write(Rythm.render(PROFILE_HTML, name, surname, email, isAdmin, conf, request));
		} else {

			response.sendRedirect(LOGOUT);
		}
	}

	/**
	 * Manages post requests. Each request gets handled by an individual method.
	 * Each one of them is better documented later.
	 * 
	 * @see remove()
	 * @see unsubscribe()
	 * @see changePassword()
	 * @see changeEmail()
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String email = (String) request.getSession().getAttribute(EMAIL);
		ServletController controller = (ServletController) this.getServletConfig().getServletContext()
				.getAttribute(email + _CONTROLLER);
		System.out.println(request.getPathInfo());
		if (request.getPathInfo().equals(REMOVE)) {
			remove(request, response, controller);
		}

		if (request.getPathInfo().equals(UNSUBSCRIBE)) {
			unsubscribe(request, response, controller);
		}

		if (request.getPathInfo().equals(CHANGE_PASSWORD)) {
			changePassword(request, response, controller);
		}

		if (request.getPathInfo().equals(CHANGE_EMAIL)) {
			changeEmail(request, response, controller);
		}

	}

	/**
	 * Deletes a configuration, given a user and configuration id. Refresh profile
	 * if the configuration is removed correctly.
	 * 
	 * @see ServletController
	 * @param request
	 * @param response
	 * @param controller
	 * @throws IOException
	 */
	private void remove(HttpServletRequest request, HttpServletResponse response, ServletController controller)
			throws IOException {
		int confId = Integer.parseInt(request.getParameter(ID));

		if (controller.removeConfiguration(confId)) {
			response.sendRedirect(PROFILE);
		}
	}

	/**
	 * Deletes a user from db. If user is removed correctly, redirects to logout
	 * 
	 * @see ServletController
	 * @param request
	 * @param response
	 * @param controller
	 * @throws IOException
	 */
	// TODO aggiungere uml
	private void unsubscribe(HttpServletRequest request, HttpServletResponse response, ServletController controller)
			throws IOException {

		if (controller.removeUser()) {
			System.out.println("Utente disiscritto");
			response.sendRedirect(LOGOUT);

		} else {
			System.out.println("Utente iscritto");
			response.sendRedirect(PROFILE);
		}

	}

	/**
	 * Changes a user password, using ServletController methods. Passwords are
	 * encrypted using HashingPassword. Redirect to login, if the passwords were
	 * changed correctly, otherwise refresh profile
	 * 
	 * @see ServletController
	 * @see HashingPassword
	 * @param request
	 * @param response
	 * @param controller
	 * @throws IOException
	 */
	// TODO aggiungere uml
	private void changePassword(HttpServletRequest request, HttpServletResponse response, ServletController controller)
			throws IOException {
		// Le password non sono ancora hashate
		String oldPassword = request.getParameter(OLD_PASS);
		String newPassword = request.getParameter(NEW_PASS);

		HashingPassword hashingPassword = new HashingPassword();

		oldPassword = hashingPassword.getHashPsw(oldPassword);
		newPassword = hashingPassword.getHashPsw(newPassword);
		// invio al controller psw vecchia e psw nuova, lui gestirà il resto
		boolean isDone = controller.changePassword(newPassword, oldPassword);
		// TODO aggiungere alert
		if (!isDone) {
			System.out.println("Password non cambiata");
			response.sendRedirect(PROFILE);
		} else {
			System.out.println("Password cambiata");
			response.sendRedirect(LOGOUT);
		}
	}

	/**
	 * Changes a user email, using ServletController methods. Redirect to login, if
	 * the emails were changed correctly, otherwise refresh profile
	 * 
	 * @see ServletController
	 * @see HashingPassword
	 * @param request
	 * @param response
	 * @param controller
	 * @throws IOException
	 */
	// TODO aggiungere uml
	private void changeEmail(HttpServletRequest request, HttpServletResponse response, ServletController controller)
			throws IOException {
		String oldEmail = request.getParameter(OLD_EMAIL);
		String newEmail = request.getParameter(NEW_EMAIL);

		// invio al controller mail vecchia e mail nuova, lui gestirà il resto
		boolean isDone = controller.changeEmail(newEmail, oldEmail);
		// TODO aggiungere alert
		if (!isDone) {
			System.out.println("Email non cambiata");
			response.sendRedirect(PROFILE);
		} else {
			System.out.println("Email cambiata");
			response.sendRedirect(LOGOUT);
		}
	}
}
