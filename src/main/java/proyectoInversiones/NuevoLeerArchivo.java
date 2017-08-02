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
	
	ArrayList<Empresa> empresaAsociada = this.leerArchivo();

	public ArrayList<Empresa> leerArchivo (){
		
		String ruta = "empresas.txt"; 
		ArrayList<Empresa> empresas = new ArrayList<>();
		
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
	
	
	public boolean validarEmpresa(String empresa){
		
		for(Empresa head:empresaAsociada){
			if(head.getNombre().contains(empresa)){ 
				return true;
				}
			}
			return false;
	}
	
	//Dada una empresa, me devuelve todas las cuentas que tiene asociada a ella
	 public ArrayList<Cuenta> obtenerCuentasSegunEmpresa(Empresa empresa){
		 ArrayList<Cuenta> cuentasAsociadas = new ArrayList<Cuenta>();
		String empresaAsoc = empresa.toString();
		if(validarEmpresa(empresaAsoc)){
			for(Empresa head:empresaAsociada){
				if(head.getNombre().equals(empresaAsoc)){
					cuentasAsociadas = head.getCuentas();
				}
			}
			
		}
	return cuentasAsociadas;
	}
	 
	 //Obtiene una lista con todos los periodos en los que estuvo activo la empresa que es indicada
	 public ArrayList<Integer> obtenerPeriodosSegunEmpresa(Empresa empresa){
		 ArrayList<Integer> periodos = new ArrayList<Integer>();
		 ArrayList<Cuenta> cuentasAsociadas = this.obtenerCuentasSegunEmpresa(empresa);
		 for(int i = 0; i<cuentasAsociadas.size();i++){
			 periodos.add(cuentasAsociadas.get(i).getPeriodo());
		 }
		 return periodos;
	 }
	 
	 //Obtiene una lista con la CUENTA solicitada (EBITDA,FDS,FCASHFOW,INGNETOCONT,INGNETODISC,DEUDA
	 public ArrayList<Float> obtenerCuentaDe(Empresa empresa, String nombreCuenta){
		 ArrayList<Float> cuentaQuerida = new ArrayList<Float>();
		 ArrayList<Cuenta> cuentasAsociadas = this.obtenerCuentasSegunEmpresa(empresa);
		 for(int i = 0;i<cuentasAsociadas.size();i++){
			 switch(nombreCuenta){
			 case("EBITDA"):
				 cuentaQuerida.add(cuentasAsociadas.get(i).getEbitda());
			 break;
			 case("FDS"):
				 cuentaQuerida.add(cuentasAsociadas.get(i).getFds());
			 break;
			 case("FCASHFLOW"):
				 cuentaQuerida.add(cuentasAsociadas.get(i).getfCashFlow());
			 break;
			 case("INGNETOOPCONT"):
				 cuentaQuerida.add(cuentasAsociadas.get(i).getIngNetoOpCont());
			 break;
			 case("INGNETOOPDISC"):
				 cuentaQuerida.add(cuentasAsociadas.get(i).getIngNetoOpDiscont());
			 break;
			 case("DEUDA"):
				 cuentaQuerida.add(cuentasAsociadas.get(i).getDeuda());
			 break;
			 default:
				 //Tirar excepcion
				 break;

			 }
		 }

		 return cuentaQuerida;
	 }
	 
	
	 public static void main (String args[]){
		 Empresa empresaAsoc = new Empresa("America Movil");
		 NuevoLeerArchivo arch = new NuevoLeerArchivo();

		 	 
		 
		 System.out.printf("PERIODOS: ");
		 System.out.print(arch.obtenerPeriodosSegunEmpresa(empresaAsoc));
		 System.out.printf("\nEBITDA: ");
		 System.out.print(arch.obtenerCuentaDe(empresaAsoc, "EBITDA"));
	 }
	 /*
	  * SI QUIEREN UN PERIODO O UN DATO EN ESPECIFICO, SE HACE UN for(int i = 0;i<cuentita.size();i++) 
	  * 															arch.obtenerPeriodosSegunEmpresa(empresaAsoc).get(i)		  */
}

/*JOptionPane.showMessageDialog(null, "Ingrese una empresa en el cuadro de texto.", "Ingrese una empresa",JOptionPane.WARNING_MESSAGE); (Ejemplo de mensaje
 * de error para usar en el futuro con indicadores*/