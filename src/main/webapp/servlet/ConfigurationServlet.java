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
import main.model.configurator.component.Component;
import main.services.persistence.PersistenceFacade;

@SuppressWarnings("serial")
public class ConfigurationServlet extends MyServlet {

	public ConfigurationServlet(String name, String path) {
		super(name, path);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String email = (String) request.getSession().getAttribute("email");
		if (email == null) {
			response.sendRedirect("/login");
			System.out.println("Utente nullo " + email);
			return;
		}

		System.out.println("Utente non nullo " + email);

		ServletController controller = (ServletController) this.getServletConfig().getServletContext()
				.getAttribute(email + "_controller");
		if (controller == null) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/logout");
			dispatcher.forward(request, response);
			return;
		}

		String confIdAsString = request.getParameter("configurationId");
		List<Component> elementOfPreexistentConfiguration = new ArrayList<Component>();
		double price = 0.0;
		double performance = 0.0;
		boolean valid = false;
		boolean errorInAutofill = false;
		if (confIdAsString == null) {
			controller.newConfiguration();
		} else {
			int confId = Integer.parseInt(confIdAsString);
			elementOfPreexistentConfiguration = controller.retrieveConfigurationComponentById(confId);
			price = controller.getConfigurationPrice();
			performance = controller.getPerformanceIndex();
			valid = controller.checkConfiguration();
			errorInAutofill = Boolean.parseBoolean(request.getParameter("errorAutofill"));
			if (elementOfPreexistentConfiguration == null)
				elementOfPreexistentConfiguration = new ArrayList<Component>();
		}

		ComponentCatalog catalog = ComponentCatalog.getInstance();
		List<String> type = PersistenceFacade.getIstance().getTypeComponent();

		response.getWriter().write(Rythm.render("configurationv2.html", catalog.getComponentList(), type,
				elementOfPreexistentConfiguration, price, performance, valid,errorInAutofill));
	}

	// TODO: Cambiare le stringhe boiler con costanti per i percorsi ed i nomi degli
	// attributi
	// da recuperare
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		// ServletController controller = (ServletController)
		// request.getSession().getAttribute("controller");
		String email = (String) request.getSession().getAttribute("email");
		System.out.println(email);
		if (email == null) {
			response.sendRedirect("/login");
			return;
		}

		ServletController controller = (ServletController) this.getServletConfig().getServletContext()
				.getAttribute(email + "_controller");
		// Se il controller è nullo (cosa che non dovrebbe succedere poichè viene
		// istanziato
		// durante la login, vuol dire che qualcuno sta facendo una cattiva post e
		// quindi lo
		// redirigo alla logout
		if (controller == null) {
			// Devo fare in modo di inviare di risposta come ajax un erorre
			// e se trovo quell'errore invio un json che venendo letto lato
			// client dal javascript poi forza a sloggare e poi loggare.
			redirectToLogout(request, response);
			return;
		}

		// Prende solo /add anche se il path completo è /configuration/add
		if (request.getPathInfo().equals("/add")) {
			add(request, response, controller);
		} else if (request.getPathInfo().equals("/remove")) {
			remove(request, response, controller);
		} else if (request.getPathInfo().equals("/check")) {
			check(request, response, controller);
		} else if (request.getPathInfo().equals("/save")) {
			save(request, response, controller);
		} else if (request.getPathInfo().equals("/performance")) {
			getPerformance(request, response, controller);
		}else if(request.getPathInfo().equals("/autofill")) {
			//TODO modificare anche l'uml
			System.out.println("Voglio autofillare");
			autofill(request, response, controller);
		}

	}

	private void autofill(HttpServletRequest request, HttpServletResponse response, ServletController controller) throws IOException {
		//The first thing is to save the configuration
		if(!controller.saveConfiguration()) {
			reloadConfigurationHtmlPage(response, controller,true);
			return;
		}
		
		
		String choice = (String) request.getParameter("groupCase");
		
		System.out.println("La scelta è "+ choice);
		if(choice == null) {
			reloadConfigurationHtmlPage(response, controller, true);
			return;
		}
		
		System.out.println("Scelta non nulla");
		
		if(choice.equals("random")) {
			if(controller.autofill()){
				//Make redirection to same page with current conf id
				System.out.println("auto random ok");
				reloadConfigurationHtmlPage(response, controller, false);
			}else{
				//Se arrivo qui l'autofill è fallito e la configurazione è rimasta 
				//invariata, devo dare un messaggio di errore al coso
				System.out.println("auto random fallito");
				reloadConfigurationHtmlPage(response, controller, true);
			}	
		}else if(choice.equals("price")){
			String priceString = (String) request.getParameter("priceAutofill");
			if( priceString != null){
				double price = Double.parseDouble(priceString);
				System.out.println("Prezzo pari a "+price);
				if(controller.autofill(price)) {
					System.out.println("auto price ok");
					reloadConfigurationHtmlPage(response, controller,false);
				}else{
					System.out.println("auto price fallita");
					reloadConfigurationHtmlPage(response, controller,true);
				}
			}
		}
		
	}

	/**TODO: Aggiungere all'uml
	 * @param response
	 * @param controller
	 * @param errorInAutofill 
	 * @throws IOException
	 */
	private void reloadConfigurationHtmlPage(HttpServletResponse response, ServletController controller, boolean errorInAutofill) throws IOException {
		String redirectPath = "/configuration?configurationId=";
		String otherParam = "&errorAutofill=";
		int configurationId =  controller.getConfigurationId();
		response.sendRedirect(redirectPath+configurationId+otherParam+errorInAutofill);
	}

	private void getPerformance(HttpServletRequest request, HttpServletResponse response, ServletController controller)
			throws IOException {
		System.out.println("getPerf");
		double performance = controller.getPerformanceIndex();
		System.out.println("getPerf " + performance);
		String json = JsonMessages.getJsonPerformanceResponse(performance);
		response.getWriter().write(json);
	}

	private void add(HttpServletRequest request, HttpServletResponse response, ServletController controller)
			throws IOException {
		// Recupero il modello
		String modelOfComponentToInsert = request.getParameter("model");
		String numberString = request.getParameter("number");
		int number = 1;
		if (numberString != null)
			number = Integer.parseInt(numberString);

		System.out.println("Voglio inserire il modello " + modelOfComponentToInsert + "volte: " + number);

		// Facciamo l'inserimento con i controlli con le classi che abbiamo
		boolean allOk = controller.addToConfiguration(modelOfComponentToInsert, number);
		String json = "";

		if (allOk) {
			double price = this.getConfigurationPrice(controller);
			json = JsonMessages.getJsonOkResponse(price);
			// controller.printConf();
			System.out.println("Aggiunta andata a buon fine");
		} else {

			json = JsonMessages.getJsonNotOkResponse(controller);
			// controller.printConf();
			System.out.println("Aggiunta andata male");
		}

		controller.printConf();
		response.getWriter().write(json);
	}

	private void remove(HttpServletRequest request, HttpServletResponse response, ServletController controller)
			throws IOException {
		String modelOfComponentToRemove = request.getParameter("model");
		System.out.println("Sto facendo la rimozione di " + modelOfComponentToRemove);

		// Facciamo l'inserimento con i controlli con le classi che abbiamo
		boolean allOk = controller.removeFromConfiguration(modelOfComponentToRemove);
		String json = "";

		if (allOk) {
			double price = this.getConfigurationPrice(controller);
			json = JsonMessages.getJsonOkResponse(price);

			// controller.printConf();
			// System.out.println("b");
		} else {
			json = JsonMessages.getJsonNotOkResponse();
			// controller.printConf();
			// System.out.println("m");
		}

		// Invio la risposta

		controller.printConf();
		response.getWriter().write(json);

	}

	private void save(HttpServletRequest request, HttpServletResponse response, ServletController controller) throws IOException {
		String json = "";
		if (controller.saveConfiguration()) {
			double price = this.getConfigurationPrice(controller);
			json = JsonMessages.getJsonOkResponse(price);
		} else {
			json = JsonMessages.getJsonNotOkResponse();
		}

		System.out.println(json);

		response.getWriter().write(json);
	}

	private void redirectToLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String json = JsonMessages.getJsonRedirectResponse();
		response.getWriter().write(json);
	}

	private void check(HttpServletRequest request, HttpServletResponse response, ServletController controller)
			throws IOException {

		String json = "";
		if (controller.checkConfiguration()) {
			double price = this.getConfigurationPrice(controller);
			json = JsonMessages.getJsonOkResponse(price);
		} else {
			json = JsonMessages.getJsonNotOkResponse();
		}

		response.getWriter().write(json);
	}

	private double getConfigurationPrice(ServletController controller) {
		return controller.getConfigurationPrice();
	}

}
