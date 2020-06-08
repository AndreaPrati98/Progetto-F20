package main.webapp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rythmengine.Rythm;

/**
 * 
 * @author Capici Alessandro
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
		response.getWriter().write(Rythm.render("sign-in.rtm"));
	}
	
}
