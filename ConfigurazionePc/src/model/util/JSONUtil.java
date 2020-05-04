package model.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import model.component.Component;
import model.component.constraint.Constraint;
import model.component.constraint.ConstraintType;
import model.component.constraint.DimensionConstraint;
import model.component.constraint.EqualsConstraint;
import model.component.constraint.MaxConstraint;

/**
 * Class used to interact with a json file
 * @author Cristian
 *
 */

/*
 * MAPPA DEGLI ATTRIBUTI E LISTA VINCOLI
 * 
 * - CPU:
 * Vincoli: cpuSocket, ramType, power
 * _ name
 * _ cpuFrequency
 * _ ramSize
 * _ ramType
 * _ hasIntegratedGraphicCard
 * _ generation
 * _ cpuSocket
 * _ tdp
 * _ power
 * _ cacheSize
 * 
 * 
 * RAM:
 * Vincoli: ramType, ramSlotCount
 * _ name
 * _ ramType
 * _ ramMaxFrequency
 * _ ramSize
 * 
 * MOBO:
 * Vincoli: cpuSocket, ramType, formFactor, power
 * _ name
 * _ cpuSocket
 * _ supportedRam
 * _ ramMaxFrequency
 * _ pciSlotCount
 * _ usbPortsCount
 * _ hdmiPortsCount
 * _ ramSlotCount
 * _ biosVersion
 * _ formFactor
 * _ power
 * 
 * GRAPHIC CARD (gpu)
 * Vincoli: graphicCardLength, pciSlotCount
 * _ name
 * _ graphicCardFrequency
 * _ graphicCardMemoryFrequency
 * _ graphicCardLength
 * _ coreCount
 * _ graphicCardFrameBuffer
 * _ power
 * 
 * - CASE
 * Vincoli: formFactor, graphicCardLength
 * _ name
 * _ graphicCardLength
 * _ formFactor
 * 
 * - COOLER
 * Vincoli: cpuSocket
 * _ name
 * _ cpuSocket
 * _ tdp
 * 
 * - POWER
 * Vincoli: power
 * _ name
 * _ power
 * 
 * - MASSSTORAGE
 * _ name
 * _ type
 * _ portVersion
 * _ size
 */


public class JSONUtil {

	private String typeComponent;
	private List<Constraint> constraintList;
	private HashMap<String, String> map;
	private final String JSON_PATH;
	Scanner in;

	public JSONUtil() {
		constraintList = new ArrayList<Constraint>();
		map = new HashMap<>();
		in = new Scanner(System.in);
		JSON_PATH = "ConfigurazionePC/src/model/util/JSON/components.json";
	}

	public void addComponent() {
		String key, value;

		System.out.println("Inserisci il tipo di componente (usa lettere minuscole e camelCase): ");
		typeComponent = in.nextLine();

		switch (typeComponent) {
		case "cpu":
			map = addCpu();
			break;
		case "ram":
			map = addRam();
			break;
		case "mobo":
			map = addMobo();
			break;
		case "gpu":
			map = addGraphicCard();
			break;
		case "case":
			map = addCase();
			break;
		case "cooler":
			map = addCool();
			break;
		case "power":
			map = addPow();
			break;
		default:

			System.out.println("Stai aggiungendo gli attributi, per uscire scrivi \"esci\"");
			while (true) {
				System.out.println("\nAggiungi un attributo: ");
				key = in.nextLine();
				if (key.equals("esci")) {
					break;
				}
				System.out.println("Aggiungi il valore di " + key);
				value = in.nextLine();
				map.put(key, value);
			}

		}

		System.out.println("Stai aggiungendo i vincoli, per uscire scrivi \"esci\"");
		while (true) {
			System.out.println("\nAggiungi un vincolo (1_ Dimension 2_ Equals 3_ Max): ");
			value = in.nextLine();
			if (value.equals("esci")) {
				break;
			}
			switch (value) {
			case "1":
				constraintList.add(getDimCon());
				break;
			case "2":
				constraintList.add(getEquCon());
				break;
			case "3":
				constraintList.add(getMaxCon());
				break;
			}
		}

		writeToJSON(typeComponent, map, constraintList);
	}

	/**
	 * @param typeComponent - the component you want to search 
	 * @return a list containing every component that is typeComponent
	 */
	public List<Component> getComponents(String typeComponent) {
		List<Component> finalList = new ArrayList<Component>();
		List<Component> list = getComponents();
		
		for(Component c : list) {
			if(c.getTypeComponent().equals(typeComponent)) {
				finalList.add(c);
			}
		}
		return finalList;
	}
	
	/**
	 * @return a list containing every component
	 */
	public List<Component> getComponents() {
		List<Component> componentList = new ArrayList<Component>();
		JSONArray JSONArray = readJSONComponents();
		JSONObject tmp;

		String typeComponent;
		List<Constraint> constraintList = new ArrayList<Constraint>();
		HashMap<String, String> attributesMap = new HashMap<String, String>();

		JSONArray internalArray;
		JSONObject internalObject;
		String constraintName, constraintValue, constraint;
		ConstraintType constraintType = null;

		for (int i = 0; i < JSONArray.size(); i++) {
			constraintList = new ArrayList<Constraint>();
			tmp = (JSONObject) JSONArray.get(i);
			typeComponent = (String) tmp.get("typeComponent");

			internalArray = (JSONArray) tmp.get("constraintList");
			for (int j = 0; j < internalArray.size(); j++) {
				internalObject = (JSONObject) internalArray.get(j);
				constraintName = (String) internalObject.get("name");
				constraintValue = (String) internalObject.get("value");
				if(internalObject.get("type") != null) {
					constraintType = ConstraintType.valueOf((String) internalObject.get("type"));
				}			
				constraint = (String) internalObject.get("constraint");
				switch (constraint) {
				case "DimensionConstraint":
					constraintList.add(new DimensionConstraint(constraintName, constraintValue, constraintType));
					break;
				case "EqualsConstraint":
					constraintList.add(new EqualsConstraint(constraintName, constraintValue));
					break;
				case "MaxConstraint":
					constraintList.add(new MaxConstraint(constraintName, constraintValue, constraintType));
					break;
				default:
				}
			}

			attributesMap = (HashMap<String, String>) tmp.get("attributesMap");

			componentList.add(new Component(typeComponent, constraintList, attributesMap));
		}

		return componentList;
	}

	private void writeToJSON(String typeComponent, HashMap<String, String> map, List<Constraint> constraintList) {
		JSONObject JSONFinal = new JSONObject();
		JSONObject JSONComponent = new JSONObject();
		JSONObject JSONMap = new JSONObject();
		JSONArray JSONList = new JSONArray();
		JSONObject JSONListObj = new JSONObject();
		JSONArray JSONArray;

		JSONMap.putAll(map);

		for (int i = 0; i < constraintList.size(); i++) {
			JSONListObj = new JSONObject();
			JSONListObj.put("name", constraintList.get(i).getConstraintName());
			JSONListObj.put("value", constraintList.get(i).getValue());
			
			System.out.println(constraintList.get(i).getConstraintType());
			
			if(constraintList.get(i).getConstraintType() != null) {
				JSONListObj.put("type", constraintList.get(i).getConstraintType().name());
			}
			
			JSONListObj.put("constraint", constraintList.get(i).getClass().getSimpleName());
			
			JSONList.add(JSONListObj);
		}

		JSONComponent.put("typeComponent", typeComponent);
		JSONComponent.put("attributesMap", map);
		JSONComponent.put("constraintList", JSONList);

		JSONArray = readJSONComponents();
		JSONArray.add(JSONComponent);

		JSONFinal.put("components", JSONArray);

		try {
			FileWriter file = new FileWriter(JSON_PATH);
			file.write(JSONFinal.toJSONString());
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Constraint getDimCon() {
		String name, value, constraint;
		ConstraintType type;

		System.out.println("Inserisci il nome");
		name = in.nextLine();

		System.out.println("Inserisci il valore");
		value = in.nextLine();

		System.out.println("Inserisci il tipo (I o E)");
		if (in.nextLine().equals("I")) {
			type = ConstraintType.INTERNAL;
		} else {
			type = ConstraintType.EXTERNAL;
		}

		return new DimensionConstraint(name, value, type);
	}

	private Constraint getEquCon() {
		String name, value;

		System.out.println("Inserisci il nome");
		name = in.nextLine();

		System.out.println("Inserisci il valore");
		value = in.nextLine();
		return new EqualsConstraint(name, value);
	}

	private Constraint getMaxCon() {
		String name, value;
		ConstraintType type;

		System.out.println("Inserisci il nome");
		name = in.nextLine();

		System.out.println("Inserisci il valore");
		value = in.nextLine();

		System.out.println("Inserisci il tipo (I o E)");
		if (in.nextLine().equals("I")) {
			type = ConstraintType.INTERNAL;
		} else {
			type = ConstraintType.EXTERNAL;
		}

		return new MaxConstraint(name, value, type);
	}

	public JSONArray readJSONComponents() {
		FileReader file;
		JSONParser parser;
		JSONObject jsonObject = null;
		try {
			file = new FileReader(JSON_PATH);
			parser = new JSONParser();
			jsonObject = (JSONObject) parser.parse(file);
			file.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return (JSONArray) jsonObject.get("components");
		
	}

	private HashMap<String, String> addCpu() {
		HashMap<String, String> map = new HashMap<>();

		// - Nome, frequenzaCPU, maxRamConsentita
		//Vincoli: cpuSocket, ramType, power
		
		System.out.println("Inserisci il nome: ");
		map.put("name", in.nextLine());
		
		System.out.println("Inserisci la frequenza: ");
		map.put("cpuFrequency", in.nextLine());
		
		System.out.println("Inserisci la massima Ram consentita: ");
		map.put("ramSize", in.nextLine());
		
		System.out.println("Inserisci il tipo di Ram supportata: ");
		map.put("ramType", in.nextLine());

		System.out.println("Ha una scheda video integrata? (S/N): ");
		if (in.nextLine().equals("S")) {
			map.put("hasIntegratedGraphicCard", "true");
		} else {
			map.put("hasIntegratedGraphicCard", "false");
		}

		System.out.println("Inserisci la generazione (es. ottava, nona): ");
		map.put("generation", in.nextLine());

		System.out.println("Inserisci il socket: ");
		map.put("cpuSocket", in.nextLine());

		System.out.println("Inserisci il TDP: (W)");
		map.put("tdp", in.nextLine());

		System.out.println("Inserisci l'assorbimento: (W)");
		map.put("power", in.nextLine());

		System.out.println("Inserisci la dimensione cache (MB): ");
		map.put("cacheSize", in.nextLine());

		return map;
	}

	private HashMap<String, String> addRam() {
		// - Nome
		//Vincoli: ramType, slot
		HashMap<String, String> map = new HashMap<>();

		System.out.println("Inserisci il nome: ");
		map.put("name", in.nextLine());
		
		System.out.println("Inserisci il tipo di RAM: ");
		map.put("ramType", in.nextLine());

		System.out.println("Inserisci la frequenza massima: ");
		map.put("ramMaxFrequency", in.nextLine());

		System.out.println("Inserisci la dimensione: ");
		map.put("ramSize", in.nextLine());

		return map;
	}

	private HashMap<String, String> addMobo() {
		// - nome, slotPCI
		//Vincoli, cpuSocket, ramType, formFactor, power
		HashMap<String, String> map = new HashMap<>();

		System.out.println("Inserisci il nome: ");
		map.put("name", in.nextLine());
		
		System.out.println("Inserisci il socket per lo slot della cpu: ");
		map.put("cpuSocket", in.nextLine());
		
		System.out.println("Inserisci il tipo di ram supportata");
		map.put("ramType", in.nextLine());
		
		System.out.println("Inserisci la massima frequenza della ram: ");
		map.put("ramMaxFrequency", in.nextLine());

		System.out.println("Inserisci il numero di slot PCIexp");
		map.put("pciSlotCount", in.nextLine());

		System.out.println("Inserisci il numero di porte USB: ");
		map.put("usbPortsCount", in.nextLine());

		System.out.println("Inserisci il numero di porte HDMI: ");
		map.put("hdmiPortsCount", in.nextLine());

		System.out.println("Inserisci il numero di slot RAM: ");
		map.put("ramSlotCount", in.nextLine());

		System.out.println("Inserisci la versione del bios: (numVersione)");
		map.put("biosVersion", in.nextLine());

		System.out.println("Inserisci il form factor: ");
		map.put("formFactor", in.nextLine());

		System.out.println("Inserisci l'assorbimento: ");
		map.put("power", in.nextLine());

		return map;
	}

	private HashMap<String, String> addGraphicCard() {
		// - nome, frequenza, velocità memoria, PCIexpress
		//Vincoli graphicCardLength, pciexpress
		HashMap<String, String> map = new HashMap<>();

		System.out.println("Inserisci il nome: ");
		map.put("name", in.nextLine());
		
		System.out.println("Inserisci la frequenza: ");
		map.put("graphicCardFrequency", in.nextLine());
		
		System.out.println("Inserisci la velocità della memoria: ");
		map.put("graphicCardMemoryFrequency", in.nextLine());
		
		System.out.println("Inserisci la lunghezza della scheda: ");
		map.put("graphicCardLength", in.nextLine());

		System.out.println("Inserisci il numero di core: ");
		map.put("coreCount", in.nextLine());

		System.out.println("Inserisci frameBuffer della scheda: ");
		map.put("graphicCardFrameBuffer", in.nextLine());

		System.out.println("Inserisci l'assorbimento: ");
		map.put("power", in.nextLine());

		return map;
	}

	private HashMap<String, String> addCase() {
		// -Nome
		//Vincoli formFactor, grapHLength
		HashMap<String, String> map = new HashMap<>();

		System.out.println("Inserisci il nome: ");
		map.put("name", in.nextLine());
		
		System.out.println("Inserisci il form factor: ");
		map.put("formFactor", in.nextLine());

		System.out.println("Inserisci la lunghezza massima della scheda video: ");
		map.put("graphicCardLength", in.nextLine());

		return map;
	}

	private HashMap<String, String> addCool() {
		// - Nome
		//Vincoli cpuSocket
		HashMap<String, String> map = new HashMap<>();

		System.out.println("Inserisci il nome: ");
		map.put("name", in.nextLine());
		
		System.out.println("Inserisci il socket CPU: ");
		map.put("cpuSocket", in.nextLine());

		System.out.println("Inserisci il TDP: ");
		map.put("tdp", in.nextLine());

		return map;
	}

	private HashMap<String, String> addPow() {
		// - Nome
		//Vincoli power
		HashMap<String, String> map = new HashMap<>();

		System.out.println("Inserisci il nome: ");
		map.put("name", in.nextLine());
		
		System.out.println("Inserisci la potenza: ");
		map.put("power", in.nextLine());

		return map;
	}
}
