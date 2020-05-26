package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloWorldServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String message = "Happy Coding!";
		message = request.getParameter("name");
		
		
		request.setAttribute("message", message);
		request.getRequestDispatcher("WEB-INF/HelloWorld.jsp").forward(request,response);
	}
}