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
			conf1.setName("Mr BlockChain limited edition");
			System.out.println(conf1.getName());
			conf2.setName("Android edition");
			conf3.setName("Ris_OttO edition ");
			response.getWriter().write(Rythm.render("home.html", conf1, conf2, conf3, request));
		}else {
			response.getWriter().write(Rythm.render("404.html"));
		}

	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.getWriter().write(Rythm.render("sign-in.rtm"));
		response.sendRedirect("/configuration");

	}

}
