package main.webapp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutServlet extends MyServlet {

	public LogoutServlet(String name, String path) {
		super(name, path);
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String email = (String) request.getSession().getAttribute("email");
		this.getServletConfig().getServletContext().removeAttribute(email+"_controller");
		request.getSession().invalidate();
		response.sendRedirect("/");
	}
}