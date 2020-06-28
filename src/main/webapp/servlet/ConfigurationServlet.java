package main.webapp.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.rythmengine.Rythm;

import main.model.configurator.ComponentCatalog;
import main.model.configurator.component.Component;
import main.services.persistence.PersistenceFacade;
import main.webapp.servlet.util.JsonMessages;

@SuppressWarnings("serial")
public class ConfigurationServlet extends MyServlet {
/**
 * this servlet need to send the user request to make sure thath is possible add or remove component of a configuration  end anathoer opertion 
 * @param name
 * @param path
 */
	public ConfigurationServlet(String name, String path) {
		super(name, path);
	}
	
	/**
	 *  this method answer to user request , only component have a price and another atribute these atribute have to write in configurationv2.html beyond name of componentr
	 *  another things is important write in configurationv2.html web page the performance index end comunicate the validation or not of user component selection in a configuration 
	 *  
	 *  @see ServletController,Catalog
	 */

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String email = (String) request.getSession().getAttribute("email");
		if (email == null) {
			response.sendRedirect("/login");
			
			return;
		}

		

		ServletController controller = (ServletController) this.getServletConfig().getServletContext()
				.getAttribute(email + "_controller");
		if (controller == null) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/logout");
			dispatcher.forward(request, response);
			return;
		}

		String confIdAsString = request.getParameter("configurationId");
		List<Component> elementOfPreexistentConfiguration = new ArrayList<Component>();
		Set<Component> elementSetOfPreexistentConfiguration = new HashSet<Component>();
		double price = 0.0;
		double performance = 0.0;
		boolean valid = false;
		boolean errorInAutofill = false;
		String configurationName = "";
		if (confIdAsString == null) {
			controller.newConfiguration();
		} else {
			int confId = Integer.parseInt(confIdAsString);
			elementOfPreexistentConfiguration = controller.retrieveConfigurationComponentById(confId);
			price = controller.getConfigurationPrice();
			performance = controller.getPerformanceIndex();
			valid = controller.checkConfiguration();
			errorInAutofill = Boolean.parseBoolean(request.getParameter("errorAutofill"));
			configurationName = controller.getConfigurationName();
			System.out.println("Configuration name "+configurationName);
			elementSetOfPreexistentConfiguration = new HashSet<>(elementOfPreexistentConfiguration);
			if (elementOfPreexistentConfiguration == null) {
				elementOfPreexistentConfiguration = new ArrayList<Component>();
				elementSetOfPreexistentConfiguration =  new HashSet<Component>();
			}
		}

		ComponentCatalog catalog = ComponentCatalog.getInstance();
		List<String> type = PersistenceFacade.getIstance().getTypeComponent();
		System.out.println("I tipi sono");
		System.out.println(type);
		response.getWriter().write(Rythm.render("configurationv2.html", catalog.getComponentList(), type,
				elementOfPreexistentConfiguration, elementSetOfPreexistentConfiguration ,price, performance, valid,errorInAutofill, configurationName));
	}

	/**
	 * manage the option in configuration web page and mange the error case
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String email = (String) request.getSession().getAttribute("email");
		System.out.println(email);
		if (email == null) {
			response.sendRedirect("/login");
			return;
		}

		ServletController controller = (ServletController) this.getServletConfig().getServletContext()
				.getAttribute(email + "_controller");
		/*
		 * if the controll is null wee redirect to login 
		 */
		if (controller == null) {
			/*
			 * wee send error message (json)
			 */
			redirectToLogout(request, response);
			return;
		}

		
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
			
			autofill(request, response, controller);
		}

	}

	/**
	 * wee can use this method to make autofill and : save usere configuration, communicate if is an error in autofil,
	 * whend the user want to generate random configuration the user can select an indicative price. 
	 * @param request
	 * @param response
	 * @param controller
	 * @throws IOException
	 */
	private void autofill(HttpServletRequest request, HttpServletResponse response, ServletController controller) throws IOException {
		//The first thing is to save the configuration
		if(!controller.saveConfiguration()) {
			reloadConfigurationHtmlPage(response, controller,true);
			return;
		}
		
		
		String choice = (String) request.getParameter("groupCase");
		
		System.out.println("La scelta � "+ choice);
		if(choice == null) {
			reloadConfigurationHtmlPage(response, controller, true);
			return;
		}
		
		
		
		if(choice.equals("random")) {
			if(controller.autofill()){
				//Make redirection to same page with current conf id
				System.out.println("auto random ok");
				reloadConfigurationHtmlPage(response, controller, false);
			}else{
			
				
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

	/**
	 * wee use this method to add component in a configuration we add them if they respect the constraints , 
	 * the servlet comunicate with user thanks to configuration web page end comunicate the user's  choices to the model. 
	 * in case of error during the operation it is communicated to the user


	 * @param request
	 * @param response
	 * @param controller
	 * @throws IOException
	 */
	private void add(HttpServletRequest request, HttpServletResponse response, ServletController controller)
			throws IOException {
		
		String modelOfComponentToInsert = request.getParameter("model");
		String numberString = request.getParameter("number");
		int number = 1;
		if (numberString != null)
			number = Integer.parseInt(numberString);

		System.out.println("Voglio inserire il modello " + modelOfComponentToInsert + "volte: " + number);

		
		boolean allOk = controller.addToConfiguration(modelOfComponentToInsert, number);
		String json = "";

		if (allOk) {
			double price = this.getConfigurationPrice(controller);
			json = JsonMessages.getJsonOkResponse(price);
			
			
		} else {

			json = JsonMessages.getJsonNotOkResponse(controller);
			
			
		}

		controller.printConf();
		response.getWriter().write(json);
	}
	/**
	 * remove component from configuration 
	 * @param request
	 * @param response
	 * @param controller
	 * @throws IOException
	 */

	private void remove(HttpServletRequest request, HttpServletResponse response, ServletController controller)
			throws IOException {
		String modelOfComponentToRemove = request.getParameter("model");
		System.out.println("Sto facendo la rimozione di " + modelOfComponentToRemove);

		
		boolean allOk = controller.removeFromConfiguration(modelOfComponentToRemove);
		String json = "";

		if (allOk) {
			double price = this.getConfigurationPrice(controller);
			json = JsonMessages.getJsonOkResponse(price);

		} else {
			json = JsonMessages.getJsonNotOkResponse();
			
		}


		controller.printConf();
		response.getWriter().write(json);

	}
	/**
	 *  save configuration in database
	 * @param request
	 * @param response
	 * @param controller
	 * @throws IOException
	 */

	private void save(HttpServletRequest request, HttpServletResponse response, ServletController controller) throws IOException {
		String json = "";
		String confName = request.getParameter("name");
		System.out.println("Confname "+confName);
		if(confName != null)
			controller.setConfigurationName(confName);
		
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

	/**
	 * this method verifies that all the constraints have been respected
	 * @see ServletController
	 * 
	 */
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
