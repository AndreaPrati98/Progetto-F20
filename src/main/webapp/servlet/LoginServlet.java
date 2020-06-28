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
		response.getWriter().write(Rythm.render("login.html", false));
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
		String email = request.getParameter("Email");
		String password = request.getParameter("Password");
		HashingPassword hashingPassword = new HashingPassword();

		ServletController controller = new ServletController();

		if (controller.login(email, hashingPassword.getHashPsw(password))) {
			request.getSession().setAttribute("email", email);
			this.getServletConfig().getServletContext().setAttribute(email + "_controller", controller); // add to
																											// application
																											// context
			response.sendRedirect("/profile");

		} else {
			response.getWriter().write(Rythm.render("login.html", true));
		}

	}

}
