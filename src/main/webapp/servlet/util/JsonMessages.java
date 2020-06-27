package main.webapp.servlet.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

import main.model.configurator.component.Component;
import main.webapp.servlet.ServletController;

public class JsonMessages {

	// Chiavi e valori per la mappa della risposta
	public static final String responseString = "response";
	public static final String responseOkString = "ok";
	public static final String responseNotOkString = "not";
	public static final String responseToRedirectString = "redirect";

	// Chiave per inserire il prezzo
	public static final String responsePrice = "price";

	// Chiave per la mappa della risposta quando ho errori
	public static final String responseErrorString = "error";

	/**
	 * 
	 * generic method for making that  when there is an error plots to string

	 * 
	 * @return
	 */
	public static String getJsonNotOkResponse() {
		Map<String, Object> responseMapToSend = new HashMap<String, Object>();
		responseMapToSend.put(responseString, responseNotOkString);
		JSONObject responseJsonToSend = new JSONObject(responseMapToSend);
		return responseJsonToSend.toJSONString();
	}

	/**
	 * 
	 * 
	 * error in add method beacause break contraint  
	 * 
	 * @return
	 */
	public static String getJsonNotOkResponse(ServletController controller) {
		Map<String, Object> responseMapToSend = new HashMap<String, Object>();
		List<String> listConstraintErrors = controller.getConstraintErrors();
		double price = controller.getConfigurationPrice();
		responseMapToSend.put(responseString, responseNotOkString);
		responseMapToSend.put(responseErrorString, listConstraintErrors);
		responseMapToSend.put(responsePrice, price);
		JSONObject responseJsonToSend = new JSONObject(responseMapToSend);

		return responseJsonToSend.toJSONString();
	}

	public static String getJsonOkResponse(double price) {
		Map<String, Object> responseMapToSend = new HashMap<String, Object>();
		responseMapToSend.put(responseString, responseOkString);
		responseMapToSend.put(responsePrice, price);
		JSONObject responseJsonToSend = new JSONObject(responseMapToSend);
		return responseJsonToSend.toJSONString();
	}
	/**
	 * generic method for making that all ok 
	 * @return
	 */
	
	public static String getJsonOkResponse() {
		Map<String, Object> responseMapToSend = new HashMap<String, Object>();
		responseMapToSend.put(responseString, responseOkString);
		JSONObject responseJsonToSend = new JSONObject(responseMapToSend);
		return responseJsonToSend.toJSONString();
	}

	public static String getJsonRedirectResponse() {
		Map<String, Object> responseMapToSend = new HashMap<String, Object>();
		responseMapToSend.put(responseString, responseToRedirectString);
		JSONObject responseJsonToSend = new JSONObject(responseMapToSend);
		return responseJsonToSend.toJSONString();
	}
	
	

	public static String getJsonPerformanceResponse(double performance) {
		Map<String, Object> responseMapToSend = new HashMap<String, Object>();
		responseMapToSend.put(responseString, performance);
		JSONObject responseJsonToSend = new JSONObject(responseMapToSend);
		return responseJsonToSend.toJSONString();
	}

	public static String getJsonStringResponse(boolean flag,String message) {
		Map<String, String> responseMapToSend = new HashMap<String, String>();
		if (flag) {
			responseMapToSend.put("response", "Operazione Effettuata");
		}else {
			responseMapToSend.put("response", "Error "+message);
		}
		

		JSONObject responseJsonToSend = new JSONObject(responseMapToSend);
		return responseJsonToSend.toJSONString();
	}

	public static String getJsonRemoveConfigurationResponse(int confId) {
		Map<String, Object> responseMapToSend = new HashMap<String, Object>();
		responseMapToSend.put(responseString, confId);
		JSONObject responseJsonToSend = new JSONObject(responseMapToSend);
		return responseJsonToSend.toJSONString();
	}

	public static String getJsonTypeComponentResponse(List<String> list) {
		Map<String, Object> responseMapToSend = new HashMap<String, Object>();
		int i = 0;

		for (String nameAttr : list) {
			responseMapToSend.put("" + i + "", nameAttr);
			i++;
		}
		responseMapToSend.put("num", i);

		JSONObject responseJsonToSend = new JSONObject(responseMapToSend);
		return responseJsonToSend.toJSONString();
	}

	public static String getJsonAllTypeComponentResponse(List<Component> list) {
		Map<String, Object> responseMapToSend = new HashMap<String, Object>();
		int i = 0;

		for (i = 0; i < list.size(); i++) {
			responseMapToSend.put("" + i + "", list.get(i).getModel() + "@" + list.get(i).getTypeComponent());
		}
		responseMapToSend.put("num", i);

		JSONObject responseJsonToSend = new JSONObject(responseMapToSend);
		return responseJsonToSend.toJSONString();
	}

	public static String getJsonNewTypeComponentResponse(boolean flag) {
		Map<String, Object> responseMapToSend = new HashMap<String, Object>();

		responseMapToSend.put("Ok", flag);

		JSONObject responseJsonToSend = new JSONObject(responseMapToSend);
		return responseJsonToSend.toJSONString();
	}

	public static String getJsonAddStdAttResponse(boolean flag) {
		Map<String, Object> responseMapToSend = new HashMap<String, Object>();

		responseMapToSend.put("Ok", flag);

		JSONObject responseJsonToSend = new JSONObject(responseMapToSend);
		return responseJsonToSend.toJSONString();
	}
}
