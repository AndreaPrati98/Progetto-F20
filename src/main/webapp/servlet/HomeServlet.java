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
 * 
 * @author Stefano Butera
 *
 */

@SuppressWarnings("serial")
public class HomeServlet extends MyServlet{
	
	public HomeServlet(String name, String path) {
		super(name, path);
		// TODO Auto-generated constructor stub
		
		
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
	    response.getWriter().write(Rythm.render("home.rtm"));
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		PersistenceFacade pr = PersistenceFacade.getIstance();
		Configuration conf1 = pr.getConfiguration(1); // le prime tre configurazioni sono quelle della HOME
		Configuration conf2 = pr.getConfiguration(2);
		Configuration conf3 = pr.getConfiguration(3);
		String val1 = "ste";
		response.getWriter().write(Rythm.render("sign-in.rtm",val1));
	}
	
	
	
	
		
		
	}
	

	

