package main.webapp.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rythmengine.Rythm;

import main.model.configurator.ComponentCatalog;

@SuppressWarnings("serial")
public class ConfigurationServlet extends MyServlet {
	
	public ConfigurationServlet(String name, String path) {
		super(name, path);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String email = (String) request.getSession().getAttribute("email");
		if(email == null){
			response.sendRedirect("/login");		
			System.out.println("Utente nullo "+ email);
			return; 
		}else {
			System.out.println("Utente non nullo "+ email);
		}
		
		
		ComponentCatalog catalog = new ComponentCatalog();
		List<String> type=new ArrayList<String>();
		type.add("case");
		type.add("cpu");
		
		type.add("mobo");
		type.add("ram");
		type.add("massStorage");
		type.add("cooler");
		type.add("power");
		type.add("gpu");
		
		response.getWriter().write(Rythm.render("configuration.html",catalog.getComponentList(),type));
	}
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		//ServletController controller = (ServletController) request.getSession().getAttribute("controller");
		String email = (String) request.getSession().getAttribute("email");
		
		if(email == null) {
			response.sendRedirect("/login");		
			return;
		}
			
			
		ServletController controller = (ServletController) this.getServletConfig().getServletContext().getAttribute(email+"_controller");
		//Se il controller è nullo (cosa che non dovrebbe succedere poichè viene istanziato
		//durante la login, vuol dire che qualcuno sta facendo una cattiva post e quindi lo
		//redirigo alla logout
		if(controller== null) {
			response.sendRedirect("/logout");	
			return;
		}
		
		//Prende solo /add anche se il path completo è /configuration/add
				
		if(request.getPathInfo().equals("/add")){
			String modelOfComponentToInsert = request.getParameter("model");
			System.out.println("Voglio inserire il modello "+modelOfComponentToInsert);
			//Facciamo l'inserimento con i controlli con le classi che abbiamo
			boolean allOk = controller.addToConfiguration(modelOfComponentToInsert);	
			
			if(allOk){
				response.getWriter().write("Andato tutto bene");
				controller.printConf();
				System.out.println("b");
			}else{
				response.getWriter().write("Andato tutto male");
				controller.printConf();
				System.out.println("m");
			}
			
			//Ti fai restituire l'oggetto via codice, il Component
			//
		}else if(request.getPathInfo().equals("/remove")){
			String modelOfComponentToRemove = request.getParameter("model");	
			System.out.println("Sto facendo la rimozione di "+modelOfComponentToRemove);
			//Facciamo l'inserimento con i controlli con le classi che abbiamo
			boolean allOk = controller.removeFromConfiguration(modelOfComponentToRemove);
			
			if(allOk){
				response.getWriter().write("Andato tutto bene");
				controller.printConf();
				System.out.println("b");
			}else{
				response.getWriter().write("Andato tutto male");
				controller.printConf();
				System.out.println("m");
			}
		}
		//Fa altre cose
		
	}
}
