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
import main.services.persistence.PersistenceFacade;

@SuppressWarnings("serial")
public class ConfigurationServlet extends MyServlet {
	
	//Chiavi e valori per la mappa della risposta
	public static final String responseString = "response";
	public static final String responseOkString = "ok";
	public static final String responseNotOkString = "not";	
	public static final String responseToRedirectString = "redirect";	
	
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
		}
			
		System.out.println("Utente non nullo "+ email);
		
		ServletController controller = (ServletController) this.getServletConfig().getServletContext().getAttribute(email+"_controller");
		if(controller== null) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/logout");
		    dispatcher.forward(request, response);
		    return;
		}
		controller.newConfiguration();

		
		ComponentCatalog catalog = ComponentCatalog.getInstance();
		List<String> type= PersistenceFacade.getIstance().getTypeComponent();	
		
		response.getWriter().write(Rythm.render("configurationv2.html",catalog.getComponentList(),type));
	}
	
	//TODO: Cambiare le stringhe boiler con costanti per i percorsi ed i nomi degli attributi
	//da recuperare
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		//ServletController controller = (ServletController) request.getSession().getAttribute("controller");
		String email = (String) request.getSession().getAttribute("email");
		System.out.println(email);
		if(email == null) {
			response.sendRedirect("/login");		
			return;
		}
			
		ServletController controller = (ServletController) this.getServletConfig().getServletContext().getAttribute(email+"_controller");
		//Se il controller è nullo (cosa che non dovrebbe succedere poichè viene istanziato
		//durante la login, vuol dire che qualcuno sta facendo una cattiva post e quindi lo
		//redirigo alla logout
		if(controller== null) {
			//Devo fare in modo di inviare di risposta come ajax un erorre
			//e se trovo quell'errore invio un json che venendo letto lato
			//client dal javascript poi forza a sloggare e poi loggare.
			redirectToLogout(request,response);
			return;
		}
		

		//Prende solo /add anche se il path completo è /configuration/add	
		if(request.getPathInfo().equals("/add")){
			add(request, response, controller);
		}else if(request.getPathInfo().equals("/remove")){
			remove(request, response, controller);
		}else if(request.getPathInfo().equals("/check")){
			check(request, response, controller);
		}else if(request.getPathInfo().equals("/save")){
			save(request, response, controller);
		}
		
	}
	
	private void add(HttpServletRequest request, HttpServletResponse response, ServletController controller) throws IOException {
		//Recupero il modello
		String modelOfComponentToInsert = request.getParameter("model");
		System.out.println("Voglio inserire il modello "+modelOfComponentToInsert);
		

		//Facciamo l'inserimento con i controlli con le classi che abbiamo
		boolean allOk = controller.addToConfiguration(modelOfComponentToInsert);	
		String json = "";
		
		if(allOk){
			json = getJsonOkResponse();
			//controller.printConf();
			System.out.println("Aggiunta andata a buon fine");
		}else{
			
			json = getJsonNotOkResponse(controller);
			//controller.printConf();
			System.out.println("Aggiunta andata male");
		}
		
		controller.printConf();
		response.getWriter().write(json);
	}
	
	private void remove(HttpServletRequest request, HttpServletResponse response, ServletController controller) throws IOException {
		String modelOfComponentToRemove = request.getParameter("model");	
		System.out.println("Sto facendo la rimozione di "+modelOfComponentToRemove);
		
		//Facciamo l'inserimento con i controlli con le classi che abbiamo
		boolean allOk = controller.removeFromConfiguration(modelOfComponentToRemove);
		String json = "";
		
		if(allOk){
			json = getJsonOkResponse();				
			
			//controller.printConf();
			//System.out.println("b");
		}else{
			json = getJsonNotOkResponse();				
			//controller.printConf();
			//System.out.println("m");
		}
		
		//Invio la risposta

		controller.printConf();
		response.getWriter().write(json);
		
	}
	

	private void save(HttpServletRequest request, HttpServletResponse response, ServletController controller) throws IOException {
		String json = "";
		if(controller.saveConfiguration()) {
			json = getJsonOkResponse();
		}else {
			json = getJsonNotOkResponse();
		}
		
		System.out.println(json);
		
		response.getWriter().write(json);
	}

	private void redirectToLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String json = getJsonRedirectResponse();	
		response.getWriter().write(json);	
	}

	private void check(HttpServletRequest request, HttpServletResponse response, ServletController controller) throws IOException {
	
		String json = "";
		if(controller.checkConfiguration()) {
			json = getJsonOkResponse();
		}else{
			json = getJsonNotOkResponse();
		}
		
		response.getWriter().write(json);
	}
	
	
	/**
	 * Metodo generico quando devo solo scrivere che è andato storto
	 * @return
	 */
	private String getJsonNotOkResponse() {
		Map<String,Object> responseMapToSend = new HashMap<String, Object>(); 	
		responseMapToSend.put(responseString, responseNotOkString);
		JSONObject responseJsonToSend = new JSONObject(responseMapToSend);
		return  responseJsonToSend.toJSONString();
	}
	
	/**
	 * Metodo che serve per dire che l'add è andata a male perchè ha violato dei contraint
	 * @return
	 */
	private String getJsonNotOkResponse(ServletController controller) {
		Map<String,Object> responseMapToSend = new HashMap<String, Object>(); 	
		List<String> listConstraintErrors = controller.getConstraintErrors();
		
		responseMapToSend.put(responseString, responseNotOkString);
		responseMapToSend.put(responseErrorString, listConstraintErrors);
		
		JSONObject responseJsonToSend = new JSONObject(responseMapToSend);
		
		return  responseJsonToSend.toJSONString();
	}
	
	
	
	private String getJsonOkResponse() {
		Map<String,Object> responseMapToSend = new HashMap<String, Object>(); 	
		responseMapToSend.put(responseString, responseOkString);
		JSONObject responseJsonToSend = new JSONObject(responseMapToSend);
		return responseJsonToSend.toJSONString();		
	}

	private String getJsonRedirectResponse() {
		Map<String,Object> responseMapToSend = new HashMap<String, Object>(); 	
		responseMapToSend.put(responseString, responseToRedirectString);
		JSONObject responseJsonToSend = new JSONObject(responseMapToSend);
		return responseJsonToSend.toJSONString();
	}
	
}
