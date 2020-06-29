package main.webapp.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rythmengine.Rythm;

import main.model.configurator.ComponentCatalog;
import main.model.configurator.component.Component;
import main.services.persistence.PersistenceFacade;
import main.webapp.servlet.util.JsonMessages;

/**
 * Servlet used to handle the creation and editing of configurations and show the relative
 * page.
 * @author Guglielmo Cassini
 *
 */

@SuppressWarnings("serial")
public class ConfigurationServlet extends MyServlet {

	private static final String NAME2 = "name";
	private static final String MODEL = "model";
	private static final String PRICE = "price";
	private static final String RANDOM = "random";
	private static final String GROUP_CASE = "groupCase";
	private static final String AUTOFILL = "/autofill";
	private static final String PERFORMANCE = "/performance";
	private static final String SAVE = "/save";
	private static final String CHECK = "/check";
	private static final String REMOVE = "/remove";
	private static final String ADD = "/add";
	private static final String CONFIGURATIONV2_HTML = "configurationv2.html";
	private static final String ERROR_AUTOFILL = "errorAutofill";
	private static final String CONFIGURATION_ID = "configurationId";
	private static final String LOGOUT = "/logout";
	private static final String LOGIN = "/login";
	private static final String _CONTROLLER = "_controller";
	private static final String EMAIL = "email";


	public ConfigurationServlet(String name, String path) {
		super(name, path);
	}
	
	/**
	 * Manges the request. If the user is logged, it renders the right page, otherwise it 
	 * returns to login page.
	 * In the right page shows inputs to make the users communicate with the servlet via ajax.
	 * If in the get is passed the parameter "configurationId" it open an existent configuration
	 * to allow users to edit it.
	 * 	 *  
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws ServletException
	 */

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String email = (String) request.getSession().getAttribute(EMAIL);
		if (email == null) {
			response.sendRedirect(LOGIN);
			return;
		}		

		ServletController controller = (ServletController) this.getServletConfig().getServletContext()
				.getAttribute(email + _CONTROLLER);
		if (controller == null) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(LOGOUT);
			dispatcher.forward(request, response);
			return;
		}

		String confIdAsString = request.getParameter(CONFIGURATION_ID);
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
			if(performance == -1)
				performance = 0.0;
			valid = controller.checkConfiguration();
			errorInAutofill = Boolean.parseBoolean(request.getParameter(ERROR_AUTOFILL));
			configurationName = controller.getConfigurationName();
			elementSetOfPreexistentConfiguration = new HashSet<>(elementOfPreexistentConfiguration);
			if (elementOfPreexistentConfiguration == null) {
				elementOfPreexistentConfiguration = new ArrayList<Component>();
				elementSetOfPreexistentConfiguration =  new HashSet<Component>();
			}
		}

		ComponentCatalog catalog = ComponentCatalog.getInstance();
		List<String> type = PersistenceFacade.getIstance().getTypeComponent();
		response.getWriter().write(Rythm.render(CONFIGURATIONV2_HTML, catalog.getComponentList(), type,
				elementOfPreexistentConfiguration, elementSetOfPreexistentConfiguration ,price, performance, valid,errorInAutofill, configurationName, request));
	}

	
	/**
	 * Manages ajax request made from the web page. 
	 * If the user is logged, handles the ajax request of: addition of a component, remotion of a component, calculation of performance,
	 * the check of the configuration relative to the fact it is valid or not and the saving of the configuration.
	 * Also handles post request for autofill that instead reload the page.
	 * 
	 * If the user is not logged, it redirects to login.
	 * 
	 * @see add, autofill, check, getPerformance, redirect to logout, remove, save.
	 *  
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws ServletException
	 * 
	 * */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String email = (String) request.getSession().getAttribute(EMAIL);
		if (email == null) {
			response.sendRedirect("/login");
			return;
		}

		ServletController controller = (ServletController) this.getServletConfig().getServletContext()
				.getAttribute(email + _CONTROLLER);
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

		
		if (request.getPathInfo().equals(ADD)) {
			add(request, response, controller);
		} else if (request.getPathInfo().equals(REMOVE)) {
			remove(request, response, controller);
		} else if (request.getPathInfo().equals(CHECK)) {
			check(request, response, controller);
		} else if (request.getPathInfo().equals(SAVE)) {
			save(request, response, controller);
		} else if (request.getPathInfo().equals(PERFORMANCE)) {
			getPerformance(request, response, controller);
		}else if(request.getPathInfo().equals(AUTOFILL)) {
			
			autofill(request, response, controller);
		}

	}

	/**
	 * This methods handles the autofill of a configuration calling the relative controller method.
	 * It choice between the random autofill and the autofill by price using the parameter
	 * "groupCase".
	 * Before performing the autofill, it saves the configuration. 
	 * After the autofill operation is concluded, it uses the reloadConfigurationHtmlPage method.
	 * 
	 * @see ServletController
	 * 
	 * @param request
	 * @param response
	 * @param controller
	 * @throws IOException
	 */
	private void autofill(HttpServletRequest request, HttpServletResponse response, ServletController controller) throws IOException {
				
		String choice = (String) request.getParameter(GROUP_CASE);
		
		if(choice == null) {
			reloadConfigurationHtmlPage(response, controller, true);
			return;
		}
		
		
		
		if(choice.equals(RANDOM)) {
			if(controller.autofill()){
				//Make redirection to same page with current conf id
				reloadConfigurationHtmlPage(response, controller, false);
			}else{				
				reloadConfigurationHtmlPage(response, controller, true);
			}	
		}else if(choice.equals(PRICE)){
			String priceString = (String) request.getParameter("priceAutofill");
			if( priceString != null){
				double price = Double.parseDouble(priceString);
				if(controller.autofill(price)) {
					reloadConfigurationHtmlPage(response, controller,false);
				}else{
					reloadConfigurationHtmlPage(response, controller,true);
				}
			}
		}
		
	}

	/**TODO: Aggiungere all'uml
	 * 
	 * This method is called after the autofill. It reload the current page with as parameters the current
	 * configuration id, to keep editing this configuration, and a boolean value to tell the page if the 
	 * autofill went wrong.
	 *
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

	/**
	 * This method calculate the performance index of the configuration and return 
	 * it to the client via a json message. If the performance index is not calculable it
	 * values -1.
	 * 
	 * @param request
	 * @param response
	 * @param controller
	 * @throws IOException
	 */
	private void getPerformance(HttpServletRequest request, HttpServletResponse response, ServletController controller)
			throws IOException {
		double performance = controller.getPerformanceIndex();
		if(performance == -1)
			performance = 0.0;
		String json = JsonMessages.getJsonPerformanceResponse(performance);
		response.getWriter().write(json);
	}

	/**
	 * This methods add a component to the configuration with the specified model as a parameter of the request,
	 * answering the client with a json containing the new price of the configuration.
	 * If it fails the message contains the name of the constraint that the configuration
	 * does not respect if it would contain the new element.
	 * 
	 * @param request
	 * @param response
	 * @param controller
	 * @throws IOException
	 */
	private void add(HttpServletRequest request, HttpServletResponse response, ServletController controller)
			throws IOException {
		
		String modelOfComponentToInsert = request.getParameter(MODEL);
		String numberString = request.getParameter("number");
		int number = 1;
		if (numberString != null)
			number = Integer.parseInt(numberString);
		
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
	 * This method remove the specified model as parameter of the request.
	 * Answer to the client with a json containing the new price of the request.
	 * 	 * 
	 * @param request
	 * @param response
	 * @param controller
	 * @throws IOException
	 */

	private void remove(HttpServletRequest request, HttpServletResponse response, ServletController controller)
			throws IOException {
		String modelOfComponentToRemove = request.getParameter(MODEL);
		
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
	 * This metod save the configuration. It answer with a json containing the result
	 * of this action.
	 * 
	 * @param request
	 * @param response
	 * @param controller
	 * @throws IOException
	 */

	private void save(HttpServletRequest request, HttpServletResponse response, ServletController controller) throws IOException {
		String json = "";
		String confName = request.getParameter(NAME2);
		if(confName != null)
			controller.setConfigurationName(confName);
		
		if (controller.saveConfiguration()) {
			double price = this.getConfigurationPrice(controller);
			json = JsonMessages.getJsonOkResponse(price);
		} else {
			json = JsonMessages.getJsonNotOkResponse();
		}

		response.getWriter().write(json);
	}

	/**
	 * A support method used to tell the client to redirect the page to login. It il called 
	 * when some client is trying to make a post or a get request when the user is not logged.
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void redirectToLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String json = JsonMessages.getJsonRedirectResponse();
		response.getWriter().write(json);
	}

	/**
	 * This method verify if configuration is valid or not using the controller method.
	 * It answer with a json containing the result of this action.
	 * 
	 * @see ServletController
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

	
	/**
	 * A private support method used to calculate the price of the current configuration.
	 * 
	 * @param controller
	 * @return the price of the configuration
	 */
	private double getConfigurationPrice(ServletController controller) {
		return controller.getConfigurationPrice();
	}

}
