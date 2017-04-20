package proyectoInversiones;

import java.io.FileNotFoundException;
import org.uqbar.commons.utils.Observable;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JOptionPane;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

@Observable
public class NuevoLeerArchivo{
	
	public Collection<Double> leerArchivo(String empresaSolicitada){
		
		if (empresaSolicitada.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Ingrese una empresa en el cuadro de texto.", "Ingrese una empresa",JOptionPane.WARNING_MESSAGE);
			}
		String ruta = empresaSolicitada + "Json.json"; 
		Collection<Double> cuentas = new ArrayList<Double>();
	
	try {
		
		FileReader fr = new FileReader(ruta);
		JsonReader reader = new JsonReader(fr);
		Cuenta historial = new Gson().fromJson(reader, Cuenta.class);
	
		cuentas.add(historial.getEbitda());
		cuentas.add(historial.getFds());
		cuentas.add(historial.getFreeCashFlow());
		cuentas.add(historial.getIngresoNetoCont());
		cuentas.add(historial.getIngresoNetoDisc());

		return cuentas;
		
		
	} catch (FileNotFoundException e) {
		System.out.println("No se encontró el archivo indicado. El path provisto fue:" + ruta);
		e.printStackTrace();
		}
	return cuentas;
	}
	
	public static void main(String args[]){}
}
