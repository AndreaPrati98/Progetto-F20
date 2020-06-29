package main.webapp.servlet;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rythmengine.Rythm;


@SuppressWarnings("serial")
public class AboutUsServlet extends MyServlet{
	
/**
 * Simple Servlet used to show the about us page.
 * @param name
 * @param path
 */
	public AboutUsServlet(String name, String path) {
		super(name, path);
		
	}
/**
 *  Renders the aboutUs html
 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().write(Rythm.render("aboutUs.html", request));
	}
}
