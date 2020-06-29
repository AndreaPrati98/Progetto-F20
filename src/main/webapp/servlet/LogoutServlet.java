package main.webapp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet used to handle logout, removing user informations contained in session.
 */

@SuppressWarnings("serial")
public class LogoutServlet extends MyServlet {

	private static final String _CONTROLLER = "_controller";
	private static final String EMAIL = "email";


	/**
	 * @param name
	 * @param path
	 */
	public LogoutServlet(String name, String path) {
		super(name, path);
	}

	
	/**
	 * 	Manages get requests.
	 *  Clear session, and redirects to home.
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws ServletException
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String email = (String) request.getSession().getAttribute(EMAIL);
		this.getServletConfig().getServletContext().removeAttribute(email+_CONTROLLER);
		request.getSession().invalidate();
		response.sendRedirect("/");
	}
}