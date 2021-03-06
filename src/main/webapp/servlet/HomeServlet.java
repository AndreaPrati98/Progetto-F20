package main.webapp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rythmengine.Rythm;

import main.model.configurator.configuration.Configuration;

import main.services.persistence.PersistenceFacade;

/**
 * Used to show the home page, containing 3 configurations as a showcase for the
 * project.
 */

@SuppressWarnings("serial")
public class HomeServlet extends MyServlet {
 
	private static final String CONFIGURATION = "/configuration";
	private static final String _404_HTML = "404.html";
	private static final String HOME_HTML2 = "home.html";
	private static final String HOME_HTML = "/home.html";

	/**
	 * @param name
	 * @param path
	 */
	public HomeServlet(String name, String path) {
		super(name, path);

	}

	
	/**
	 * 	Manages get requests.
	 *  Renders home, or 404 in case of errors.
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws ServletException
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		if (request.getPathInfo().equals("/") || request.getPathInfo().equals(HOME_HTML)) {
			PersistenceFacade pr = PersistenceFacade.getIstance();
			Configuration conf1 = pr.getConfiguration(1); // le prime tre configurazioni sono quelle della HOME
			Configuration conf2 = pr.getConfiguration(36);
			Configuration conf3 = pr.getConfiguration(3); // dovrebbero funzionare
			
			response.getWriter().write(Rythm.render(HOME_HTML2, conf1, conf2, conf3, request));
		}else {
			response.getWriter().write(Rythm.render(_404_HTML));
		}

	}
	
	/**
	 *  Manages post requests.
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//response.getWriter().write(Rythm.render("sign-in.html"));
		response.sendRedirect(CONFIGURATION);

	}

}
