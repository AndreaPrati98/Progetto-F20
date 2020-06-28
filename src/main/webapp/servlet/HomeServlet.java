package main.webapp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rythmengine.Rythm;

import main.model.configurator.component.Component;
import main.model.configurator.configuration.Configuration;

import main.services.persistence.PersistenceFacade;

@SuppressWarnings("serial")
public class HomeServlet extends MyServlet {
 
/**
 * @author stefano
 * @param name
 * @param path
 * 
 * this servlet manage the home page of the web site and load 3 configuration from database and it write in home.html these configuration
 * 
 */
	public HomeServlet(String name, String path) {
		super(name, path);

	}

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
