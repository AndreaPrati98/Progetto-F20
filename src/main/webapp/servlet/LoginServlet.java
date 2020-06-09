package main.webapp.servlet;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rythmengine.Rythm;

public class LoginServlet extends MyServlet {

	public LoginServlet(String name, String path) {
		super(name, path);
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().write(Rythm.render("login.html", false));
	}

	//TODO: Creare metodi privati per alleggerire il tutto
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("Email");
		String password = request.getParameter("Password");
		MessageDigest digest;

		try {
			digest = MessageDigest.getInstance("SHA-256");
			digest.reset();
			digest.update(password.getBytes("utf8"));
			password = String.format("%064x", new BigInteger(1, digest.digest()));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ServletController controller = new ServletController();
		
		if (controller.login(email, password)) {
			request.getSession().setAttribute("email", email);
		    this.getServletConfig().getServletContext().setAttribute(email+"_controller", controller); // add to application context
			response.sendRedirect("/configuration");
			
		} else {
			//response.sendRedirect("/login");
			response.getWriter().write(Rythm.render("login.html", true));

		}

	}

}
