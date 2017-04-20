package proyectoInversiones;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
//import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONManager {
	

 public static void escribir(String cuenta){
		JSONObject obj = new JSONObject();
		
		JSONArray list = new JSONArray();
		list.add("Cuenta 1");
		list.add("Cuenta 2");
		list.add("Cuenta 3");
		
		obj.put("Cuentas",list);
		
		try {
			
			FileWriter file = new FileWriter("pruebaJson.json");
			file.write(obj.toJSONString());
			file.flush();
			file.close();

		} catch (IOException e) {
			System.out.print("El archivo no existe");
		}

 System.out.print(obj);
 }
 
 public static String leer(){
		JSONObject objetoLector = new JSONObject();
	
		JSONParser parser = new JSONParser();

		try {

			Object obj = parser.parse(new FileReader("pruebaJson.json"));

			JSONObject jsonObject = (JSONObject) obj;

			String cuenta1 = (String) jsonObject.get("Cuenta 1");
			System.out.println(cuenta1);
			return cuenta1;
			
	
		} catch (FileNotFoundException e) {
			//manejo de error
		} catch (IOException e) {
			//manejo de error
		} catch (ParseException e) {
			//manejo de error
		}
		
	return "";
		
}

 
	
	 public static void main(String[] args) {
/*
			JSONObject obj = new JSONObject();
			obj.put("Cuenta1", "1513131");
			obj.put("Cuenta2", "92");
			
			try {
				
				FileWriter file = new FileWriter("pruebaJson.json");
				file.write(obj.toJSONString());
				file.flush();
				file.close();

			} catch (IOException e) {
				//manejar error
			}
 

	 System.out.print(obj);
	 } */
}
	 
}
