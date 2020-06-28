package main.webapp.servlet;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rythmengine.Rythm;

import main.services.util.HashingPassword;

/**
 * Servlet used to handle login, saving user informations through sessions.
 */

public class LoginServlet extends MyServlet {
	private static final String PROFILE = "/profile";
	private static final String _CONTROLLER = "_controller";
	private static final String LOGIN_HTML = "login.html";
	private static final String EMAIL2 = "email";
	private static final String PASSWORD = "Password";
	private static final String EMAIL = "Email";

	/**
	 * @param name
	 * @param path
	 * 
	 */
	public LoginServlet(String name, String path) {
		super(name, path);
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().write(Rythm.render(LOGIN_HTML, false));
	}

	/**
	 *  Manages post requests.
	 *  Checks if the given email and password exists and are correct, in db.
	 *  If they do, redirect to profile, if they don't, shows an error in profile.
	 * 
	 * @see ServletController
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter(EMAIL);
		String password = request.getParameter(PASSWORD);
		HashingPassword hashingPassword = new HashingPassword();

		ServletController controller = new ServletController();

		if (controller.login(email, hashingPassword.getHashPsw(password))) {
			request.getSession().setAttribute(EMAIL2, email);
			this.getServletConfig().getServletContext().setAttribute(email + _CONTROLLER, controller); // add to
																											// application
																											// context
			response.sendRedirect(PROFILE);

		} else {
			response.getWriter().write(Rythm.render(LOGIN_HTML, true));
		}

	}

}
