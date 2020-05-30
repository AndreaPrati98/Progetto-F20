package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rythmengine.Rythm;

import model.catalog.ComponentCatalog;

@SuppressWarnings("serial")
public class ConfigurationServlet extends MyServlet {
	
	public ConfigurationServlet(String name, String path) {
		super(name, path);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		ComponentCatalog catalog = new ComponentCatalog();
		List<String> type=new ArrayList<String>();
		
		type.add("cpu");
		type.add("case");
		type.add("mobo");
		type.add("ram");
		type.add("massStorage");
		type.add("cooler");
		type.add("power");
		type.add("gpu");
		
		response.getWriter().write(Rythm.render("configuration.rtm",catalog.getComponentList(),type));
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.getWriter().write(Rythm.render("sign-in.rtm"));
	}
}
