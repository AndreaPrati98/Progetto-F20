package main.webapp.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.rythmengine.Rythm;

import main.model.configurator.ComponentCatalog;

@SuppressWarnings("serial")
public class ConfigurationServlet extends MyServlet {
	
	//Chiavi e valori per la mappa della risposta
	public static final String responseString = "response";
	public static final String responseOkString = "ok";
	public static final String responseNotOkString = "not";	
	//Chiave per la mappa della risposta quando ho errori
	public static final String responseErrorString = "error";
	
	
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
		System.out.println(email);
		if(email == null) {
			response.sendRedirect("/login");		
			return;
		}
			
		//Preparo una hash map in cui inserisco i messaggi di ritorno delle funzioni.
		//Poi la convertirò in json e la invio.
		Map<String,Object> responseMapToSend = new HashMap<String, Object>(); 	
		ServletController controller = (ServletController) this.getServletConfig().getServletContext().getAttribute(email+"_controller");
		//Se il controller è nullo (cosa che non dovrebbe succedere poichè viene istanziato
		//durante la login, vuol dire che qualcuno sta facendo una cattiva post e quindi lo
		//redirigo alla logout
		if(controller== null) {
			//Devo fare in modo di inviare di risposta come ajax un erorre
			//e se trovo quell'errore invio un json che venendo letto lato
			//client dal javascript poi forza a sloggare e poi loggare.
			response.sendRedirect("/logout"); 
			return;
		}
		
		//Prende solo /add anche se il path completo è /configuration/add	
		if(request.getPathInfo().equals("/add")){
			add(request, response, controller);
		}else if(request.getPathInfo().equals("/remove")){
			remove(request, response, controller);
		}else if(request.getPathInfo().equals("/check")){
			check(request, response, controller);
		}
		//Fa altre cose
		
	}

	private void check(HttpServletRequest request, HttpServletResponse response, ServletController controller) {
		// TODO Auto-generated method stub
		
	}

	private void remove(HttpServletRequest request, HttpServletResponse response, ServletController controller) throws IOException {
		String modelOfComponentToRemove = request.getParameter("model");	
		System.out.println("Sto facendo la rimozione di "+modelOfComponentToRemove);
		
		Map<String,Object> responseMapToSend = new HashMap<String, Object>(); 	
		//Facciamo l'inserimento con i controlli con le classi che abbiamo
		boolean allOk = controller.removeFromConfiguration(modelOfComponentToRemove);
		String json = "";
		
		if(allOk){
			//Aggiungo i campi alla mappa, poi converto in stringa json
			responseMapToSend.put(responseString, responseOkString);
			JSONObject responseJsonToSend = new JSONObject(responseMapToSend);
			json = responseJsonToSend.toJSONString();					
			
			controller.printConf();
			System.out.println("b");
		}else{
			
			responseMapToSend.put(responseString, responseNotOkString);
			JSONObject responseJsonToSend = new JSONObject(responseMapToSend);
			json = responseJsonToSend.toJSONString();

			controller.printConf();
			System.out.println("m");
		}
		

		//Invio la risposta
		response.getWriter().write(json);
		
	}

	private void add(HttpServletRequest request, HttpServletResponse response, ServletController controller) throws IOException {
		//Recupero il modello
		String modelOfComponentToInsert = request.getParameter("model");
		System.out.println("Voglio inserire il modello "+modelOfComponentToInsert);
		
		Map<String,Object> responseMapToSend = new HashMap<String, Object>(); 	

		//Facciamo l'inserimento con i controlli con le classi che abbiamo
		boolean allOk = controller.addToConfiguration(modelOfComponentToInsert);	
		
		String json = "";
		
		if(allOk){
			System.out.println("Aggiunta ha funzionato");
			
			//Aggiungo i campi alla mappa, poi converto in stringa json
			responseMapToSend.put(responseString, responseOkString);
			JSONObject responseJsonToSend = new JSONObject(responseMapToSend);
			json = responseJsonToSend.toJSONString();	
			System.out.println(json);
			
			controller.printConf();
			System.out.println("b");
		}else{
			List<String> listConstraintErrors = controller.getConstraintErrors();
			responseMapToSend.put(responseString, responseNotOkString);
			responseMapToSend.put(responseErrorString, listConstraintErrors);
			JSONObject responseJsonToSend = new JSONObject(responseMapToSend);
			json = responseJsonToSend.toJSONString();					
			
			System.out.println(json);
			controller.printConf();
			System.out.println("m");
		}
		
		response.getWriter().write(json);
	}
}
