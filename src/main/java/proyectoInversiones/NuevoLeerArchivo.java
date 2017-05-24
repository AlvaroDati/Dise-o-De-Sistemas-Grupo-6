package proyectoInversiones;

import java.io.FileNotFoundException;
import java.io.FileReader;

import java.util.ArrayList;
import java.util.Collection;
import java.lang.reflect.Type;
//import java.util.List;

//import javax.swing.JOptionPane;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;



public class NuevoLeerArchivo{
	
	public Collection<Empresa> leerArchivo (){
		
	/*	if (empresaSolicitada.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Ingrese una empresa en el cuadro de texto.", "Ingrese una empresa",JOptionPane.WARNING_MESSAGE);
			}*/
		String ruta = "empresas.txt"; 
		Collection<Empresa> empresas = new ArrayList<Empresa>();
	
	try {
		
		FileReader fr = new FileReader(ruta);
		Type tipoEmpresa = new TypeToken<ArrayList<Empresa>>() {
		}.getType();
		Gson gson = new Gson();
		JsonReader reader = new JsonReader(fr);
		empresas = gson.fromJson(reader, tipoEmpresa);
		return empresas;
		
	} catch (FileNotFoundException e) {
		System.out.println("No se encontro el archivo indicado. El path provisto fue:" + ruta);
		e.printStackTrace();
	}
	
	return empresas;
	
	}
	
	public static void main(String args[]){
		/*NuevoLeerArchivo archivo = new NuevoLeerArchivo();
		archivo.leerArchivo().forEach(empresa -> empresa.mostrarTodo());*/
		
	}
	
}
