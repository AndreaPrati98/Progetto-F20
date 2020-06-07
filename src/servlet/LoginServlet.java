package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.rythmengine.Rythm;

import controller.ServletController;

public class LoginServlet extends MyServlet{

	public LoginServlet(String name, String path) {
		super(name, path);
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().write(Rythm.render("login.html"));
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String email = request.getParameter("Email");
		String password = request.getParameter("Password");
		
		ServletController controller = ServletController.getIstance();
		if(controller.login(email, password)) {
			request.getSession().setAttribute("email", email);
			response.sendRedirect("/configuration");		
		}
		else {
			response.sendRedirect("/login");
		}
		
	}

}
