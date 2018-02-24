//package proyectoInversiones;
//
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.HashSet;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Set;
//import java.lang.reflect.Type;
//import proyectoInversiones.repositorio.*;
////import java.util.List;
//
////import javax.swing.JOptionPane;
//
//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;
//import com.google.gson.stream.JsonReader;
//
//import proyectoInversiones.repositorio.Repositorio;
//
//
//public class NuevoLeerArchivo {
//
//	ArrayList<Empresa> empresaAsociada = this.leerArchivo();
//
//	public ArrayList<Empresa> leerArchivo() {
//
//		String ruta = "empresas.json";
//		ArrayList<Empresa> empresas = new ArrayList<>();
//
//		try {
//
//			FileReader fr = new FileReader(ruta);
//			Type tipoEmpresa = new TypeToken<ArrayList<Empresa>>() {}.getType();
//			Gson gson = new Gson();
//			JsonReader reader = new JsonReader(fr);
//			empresas = gson.fromJson(reader, tipoEmpresa);
//			return empresas;
//
//		} catch (FileNotFoundException e) {
//			System.out.println("No se encontro el archivo indicado. El path provisto fue:" + ruta);
//			e.printStackTrace();
//		}
//
//		return empresas;
//
//	}
//
//	public boolean validarEmpresa(String empresa) {
//
//		for (Empresa head : empresaAsociada) {
//			if (head.getNombre().equals(empresa)) {
//				return true;
//			}
//		}
//		return false;
//		
//	}
//
//	public List<Integer> obtenerPeriodosSegunEmpresa(Empresa empresa) {
//		List<Periodo> periodos = this.getPeriodos(empresa);
//		List<Integer> periodosAux = new ArrayList<Integer>();
//
//		for (Periodo head : periodos) {
//			periodosAux.add(head.getAnio());
//		}
//		return periodosAux;
//
//	}
//
//	public List<Periodo> getPeriodos(Empresa empresa) {
//		List<Periodo> periodos = new ArrayList<Periodo>();
//		String empresaAsoc = empresa.getNombre();
//		
//
//		for(int i = 0;i<empresaAsociada.size();i++){
//			if(empresaAsociada.get(i).getNombre().equals(empresa.getNombre())){
//				periodos = empresaAsociada.get(i).getPeriodos();
//			}
//		}
//		return periodos;
//	}
//
//
//	public ArrayList<Cuenta> obtenerCuentasSegunEmpresa(Empresa empresa) {
//		List<Periodo> periodos = this.getPeriodos(empresa);
//
//		ArrayList<Cuenta> cuentaQuerida = new ArrayList<Cuenta>();
//		for (int i = 0; i < periodos.size(); i++) {
//			cuentaQuerida.add(periodos.get(i).getCuentas());
//		}
//		
//		return cuentaQuerida;
//
//	}
//
//	// Obtiene una lista con la CUENTA solicitada
//	// (EBITDA,FDS,FCASHFOW,INGNETOCONT,INGNETODISC,DEUDA
//	public ArrayList<Float> obtenerCuentaDe(Empresa empresa, String nombreCuenta) {
//		ArrayList<Float> cuentaQuerida = new ArrayList<Float>();
//		ArrayList<Cuenta> cuentasAsociadas = this.obtenerCuentasSegunEmpresa(empresa);
//		for (int i = 0; i < cuentasAsociadas.size(); i++) {
//			switch (nombreCuenta) {
//			case ("EBITDA"):
//				cuentaQuerida.add(cuentasAsociadas.get(i).getEbitda());
//				break;
//			case ("FDS"):
//				cuentaQuerida.add(cuentasAsociadas.get(i).getFds());
//				break;
//			case ("FCASHFLOW"):
//				cuentaQuerida.add(cuentasAsociadas.get(i).getFCashFlow());
//				break;
//			case ("INGNETOOPCONT"):
//				cuentaQuerida.add(cuentasAsociadas.get(i).getIngNetoOpCont());
//				break;
//			case ("INGNETOOPDISC"):
//				cuentaQuerida.add(cuentasAsociadas.get(i).getIngNetoOpDiscont());
//				break;
//			case ("DEUDA"):
//				cuentaQuerida.add(cuentasAsociadas.get(i).getDeuda());
//				break;
//			case("CAPITALTOTAL"):
//				float valor = cuentasAsociadas.get(i).getEbitda() +cuentasAsociadas.get(i).getFds()+cuentasAsociadas.get(i).getFCashFlow()+cuentasAsociadas.get(i).getIngNetoOpCont()
//				+cuentasAsociadas.get(i).getIngNetoOpDiscont() -cuentasAsociadas.get(i).getDeuda();
//			cuentaQuerida.add(valor);
//				break;
//			default:
//				// Tirar excepcion
//				break;
//
//			}
//		}
//
//		return cuentaQuerida;
//	}
//
//	public float obtenerCuentaSegunPeriodo(Empresa empresa, String nombreCuenta, int periodo) {
//		float cuenta = 0;
//		ArrayList<Float> cuentas = this.obtenerCuentaDe(empresa, nombreCuenta);
//		List<Integer> periodos = this.obtenerPeriodosSegunEmpresa(empresa);
//		for (int i = 0; i < cuentas.size(); i++) {
//			if (periodos.get(i).equals(periodo)) {
//				cuenta = cuentas.get(i);
//			}
//		}
//		return cuenta;
//	}
//	
//	
//
//	// //SIRVE PARA OBTENER LAS CUENTAS DE LA EMPRESA QUE SE SOLICITE
//	public ArrayList<Float> sumaDeCuentasDe(Empresa empresa) {
//		ArrayList<Float> sumaDeCuentas = new ArrayList<Float>();
//		ArrayList<Cuenta> cuentita = this.obtenerCuentasSegunEmpresa(empresa);
//		for (int i = 0; i < cuentita.size(); i++) {
//			sumaDeCuentas.add(cuentita.get(i).capitalTotal()); // capitalTotal()
//																// esta definido
//																// en la clase
//																// Cuenta.
//
//		}
//		return sumaDeCuentas;
//	}
//
//}

package proyectoInversiones;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Type;
//import java.util.List;
import java.net.URL;

//import javax.swing.JOptionPane;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;



public class NuevoLeerArchivo {

 ArrayList<Empresa> empresaAsociada = this.leerArchivo();

 String rutaGlobal = "";
 
 
 public String getRuta() {
  return rutaGlobal;
 }

 public void setRuta(String ruta) {
  this.rutaGlobal = ruta;
 }


 
 public ArrayList<Empresa> leerArchivo() {

  String ruta = "target/classes/public/empresas.json";
  //String ruta = "https://raw.githubusercontent.com/AlvaroDati/Dise-o-De-Sistemas-Grupo-6/nuevaBranch/empresas.json";
  
	 rutaGlobal=ruta;
  
  ArrayList<Empresa> empresas = new ArrayList<>();
  
  try {

   FileReader fr = new FileReader(ruta);
//   URL fr = new URL(ruta);
//  InputStreamReader in = new InputStreamReader(fr.openStream());
   Type tipoEmpresa = new TypeToken<ArrayList<Empresa>>() {}.getType();
   Gson gson = new Gson();
   JsonReader reader = new JsonReader(fr); 
   empresas = gson.fromJson(reader, tipoEmpresa);
   return empresas;

  } catch (FileNotFoundException e) {
   System.out.println("No se encontro el archivo indicado. El path provisto fue: " + ruta);
   e.printStackTrace();
  } catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

  return empresas;

 }
 
 

 public boolean validarEmpresa(String empresa) {

  for (Empresa head : empresaAsociada) {
   if (head.getNombre().equals(empresa)) {
    return true;
   }
  }
  return false;
  
 }

 public List<Integer> obtenerPeriodosSegunEmpresa(Empresa empresa) {
  List<Periodo> periodos = this.getPeriodos(empresa);
  List<Integer> periodosAux = new ArrayList<Integer>();

  for (Periodo head : periodos) {
   periodosAux.add(head.getAnio());
  }
  return periodosAux;

 }

 public List<Periodo> getPeriodos(Empresa empresa) {
  List<Periodo> periodos = new ArrayList<Periodo>();
  String empresaAsoc = empresa.getNombre();
  

  for(int i = 0;i<empresaAsociada.size();i++){
   if(empresaAsociada.get(i).getNombre().equals(empresa.getNombre())){
    periodos = empresaAsociada.get(i).getPeriodos();
   }
  }
  return periodos;
 }


 public ArrayList<Cuenta> obtenerCuentasSegunEmpresa(Empresa empresa) {
  List<Periodo> periodos = this.getPeriodos(empresa);

  ArrayList<Cuenta> cuentaQuerida = new ArrayList<Cuenta>();
  for (int i = 0; i < periodos.size(); i++) {
   cuentaQuerida.add(periodos.get(i).getCuentas());
  }
  
  return cuentaQuerida;

 }

 // Obtiene una lista con la CUENTA solicitada
 // (EBITDA,FDS,FCASHFOW,INGNETOCONT,INGNETODISC,DEUDA
 public ArrayList<Float> obtenerCuentaDe(Empresa empresa, String nombreCuenta) {
  ArrayList<Float> cuentaQuerida = new ArrayList<Float>();
  ArrayList<Cuenta> cuentasAsociadas = this.obtenerCuentasSegunEmpresa(empresa);
  for (int i = 0; i < cuentasAsociadas.size(); i++) {
   switch (nombreCuenta) {
   case ("EBITDA"):
    cuentaQuerida.add(cuentasAsociadas.get(i).getEbitda());
    break;
   case ("FDS"):
    cuentaQuerida.add(cuentasAsociadas.get(i).getFds());
    break;
   case ("FCASHFLOW"):
    cuentaQuerida.add(cuentasAsociadas.get(i).getFCashFlow());
    break;
   case ("INGNETOOPCONT"):
    cuentaQuerida.add(cuentasAsociadas.get(i).getIngNetoOpCont());
    break;
   case ("INGNETOOPDISC"):
    cuentaQuerida.add(cuentasAsociadas.get(i).getIngNetoOpDiscont());
    break;
   case ("DEUDA"):
    cuentaQuerida.add(cuentasAsociadas.get(i).getDeuda());
    break;
   case("CAPITALTOTAL"):
    float valor = cuentasAsociadas.get(i).getEbitda() +cuentasAsociadas.get(i).getFds()+cuentasAsociadas.get(i).getFCashFlow()+cuentasAsociadas.get(i).getIngNetoOpCont()
    +cuentasAsociadas.get(i).getIngNetoOpDiscont() -cuentasAsociadas.get(i).getDeuda();
   cuentaQuerida.add(valor);
    break;
   default:
    // Tirar excepcion
    break;

   }
  }

  return cuentaQuerida;
 }

 public float obtenerCuentaSegunPeriodo(Empresa empresa, String nombreCuenta, int periodo) {
  float cuenta = 0;
  ArrayList<Float> cuentas = this.obtenerCuentaDe(empresa, nombreCuenta);
  List<Integer> periodos = this.obtenerPeriodosSegunEmpresa(empresa);
  for (int i = 0; i < cuentas.size(); i++) {
   if (periodos.get(i).equals(periodo)) {
    cuenta = cuentas.get(i);
   }
  }
  return cuenta;
 }
 
 

 // //SIRVE PARA OBTENER LAS CUENTAS DE LA EMPRESA QUE SE SOLICITE
 public ArrayList<Float> sumaDeCuentasDe(Empresa empresa) {
  ArrayList<Float> sumaDeCuentas = new ArrayList<Float>();
  ArrayList<Cuenta> cuentita = this.obtenerCuentasSegunEmpresa(empresa);
  for (int i = 0; i < cuentita.size(); i++) {
   sumaDeCuentas.add(cuentita.get(i).capitalTotal()); // capitalTotal()
                // esta definido
                // en la clase
                // Cuenta.

  }
  return sumaDeCuentas;
 }

// public static void main (String args[]){
//		ArrayList<Empresa> empresas = new NuevoLeerArchivo().leerArchivo();
//		
//		System.out.println(empresas);
//		
//	}
	 

 
 /*
  * SI QUIEREN UN PERIODO O UN DATO EN ESPECIFICO, SE HACE UN for(int i =
  * 0;i<cuentita.size();i++)
  * arch.obtenerPeriodosSegunEmpresa(empresaAsoc).get(i)
  */
}


