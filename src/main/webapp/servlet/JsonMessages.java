package main.webapp.servlet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

public class JsonMessages {

	//Chiavi e valori per la mappa della risposta
	public static final String responseString = "response";
	public static final String responseOkString = "ok";
	public static final String responseNotOkString = "not";	
	public static final String responseToRedirectString = "redirect";	
	
	//Chiave per la mappa della risposta quando ho errori
	public static final String responseErrorString = "error";
	
	
	
	/**
	 * Metodo generico quando devo solo scrivere che � andato storto
	 * @return
	 */
	public static String getJsonNotOkResponse() {
		Map<String,Object> responseMapToSend = new HashMap<String, Object>(); 	
		responseMapToSend.put(responseString, responseNotOkString);
		JSONObject responseJsonToSend = new JSONObject(responseMapToSend);
		return  responseJsonToSend.toJSONString();
	}
	
	/**
	 * Metodo che serve per dire che l'add � andata a male perch� ha violato dei contraint
	 * @return
	 */
	public static String getJsonNotOkResponse(ServletController controller) {
		Map<String,Object> responseMapToSend = new HashMap<String, Object>(); 	
		List<String> listConstraintErrors = controller.getConstraintErrors();
		
		responseMapToSend.put(responseString, responseNotOkString);
		responseMapToSend.put(responseErrorString, listConstraintErrors);
		
		JSONObject responseJsonToSend = new JSONObject(responseMapToSend);
		
		return  responseJsonToSend.toJSONString();
	}
	
	
	
	public static String getJsonOkResponse() {
		Map<String,Object> responseMapToSend = new HashMap<String, Object>(); 	
		responseMapToSend.put(responseString, responseOkString);
		JSONObject responseJsonToSend = new JSONObject(responseMapToSend);
		return responseJsonToSend.toJSONString();		
	}

	public static String getJsonRedirectResponse() {
		Map<String,Object> responseMapToSend = new HashMap<String, Object>(); 	
		responseMapToSend.put(responseString, responseToRedirectString);
		JSONObject responseJsonToSend = new JSONObject(responseMapToSend);
		return responseJsonToSend.toJSONString();
	}

}
