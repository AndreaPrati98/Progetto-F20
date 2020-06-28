package main.webapp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rythmengine.Rythm;

import main.model.configurator.component.Component;
import main.model.configurator.configuration.Configuration;

import main.services.persistence.PersistenceFacade;

/**
 * Used to show the home page, containting 3 configurations as a showcase. 
 */

@SuppressWarnings("serial")
public class HomeServlet extends MyServlet {
 
	/**
	 * Takes a name and its path (as url) as arguments.
	 * 
	 * @param name
	 * @param path
	 */
	public HomeServlet(String name, String path) {
		super(name, path);

	}

	
	/**
	 * 	Manage get requests.
	 *  Renders administrator.html if the user is logged in, otherwise redirects to
	 *  login, or 403 in case of errors.
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws ServletException
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		if (request.getPathInfo().equals("/") || request.getPathInfo().equals("/home.html")) {
			PersistenceFacade pr = PersistenceFacade.getIstance();
			Configuration conf1 = pr.getConfiguration(1); // le prime tre configurazioni sono quelle della HOME
			Configuration conf2 = pr.getConfiguration(36);
			Configuration conf3 = pr.getConfiguration(3); // dovrebbero funzionare
			
			response.getWriter().write(Rythm.render("home.html", conf1, conf2, conf3, request));
		}else {
			response.getWriter().write(Rythm.render("404.html"));
		}

	}
/**
 *  when the user to click wieew botton the web site resposnse whith configuration page or sigin in page
 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.getWriter().write(Rythm.render("sign-in.rtm"));
		response.sendRedirect("/configuration");

	}

}
