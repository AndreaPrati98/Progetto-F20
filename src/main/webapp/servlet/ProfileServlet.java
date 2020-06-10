package main.webapp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rythmengine.Rythm;

import main.model.configurator.configuration.Configuration;
import main.model.people.costumer.Customer;
import main.services.persistence.PersistenceFacade;

public class ProfileServlet extends MyServlet{

	public ProfileServlet(String name, String path) {
		super(name, path);
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		PersistenceFacade pf = PersistenceFacade.getIstance();
		String email = (String) request.getSession().getAttribute("email");
		
		if(email != null) {
			//Se nella sessione esiste la mail, mi salvo tutte le info e carico il profilo
			Customer c = pf.getUser(email);
			String name = c.getName();
			String surname = c.getSurname();
			String mail = c.getEmail();
			//Da sostituire
			String profilePic = "https://images.unsplash.com/photo-1484557985045-edf25e08da73?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=667&q=80";
			response.getWriter().write(Rythm.render("profile.rtm", name, surname, mail, profilePic));
		}else {
			//altrimenti reindirizzo al login
			response.sendRedirect("/login");
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.getWriter().write(Rythm.render("sign-in.rtm"));
	
		
	}
}
