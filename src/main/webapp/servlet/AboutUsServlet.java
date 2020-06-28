package main.webapp.servlet;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rythmengine.Rythm;

import main.services.persistence.PersistenceFacade;

public class AboutUsServlet extends MyServlet{
/**
 * this is a servlet, his goal is to load a web page ( abaut us page) 
 * @param name
 * @param path
 */
	public AboutUsServlet(String name, String path) {
		super(name, path);
		
	}
/**
 *  @description responds to a request ( type get) 
 *  @return void 
 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().write(Rythm.render("aboutUs.html"));
	}
}
