package proyectoInversiones;

import java.io.FileNotFoundException;
import java.io.FileReader;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.lang.reflect.Type;
//import java.util.List;

//import javax.swing.JOptionPane;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

public class NuevoLeerArchivo {

	ArrayList<Empresa> empresaAsociada = this.leerArchivo();

	public ArrayList<Empresa> leerArchivo() {

		String ruta = "empresas2.txt";
		ArrayList<Empresa> empresas = new ArrayList<>();

		try {

			FileReader fr = new FileReader(ruta);
			Type tipoEmpresa = new TypeToken<ArrayList<Empresa>>() {}.getType();
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

	public boolean validarEmpresa(String empresa) {
//		String a;
//		int i;
//		i = empresa.indexOf("-");
//		a = empresa.substring(i+1,empresa.length());
		for (Empresa head : empresaAsociada) {
			if (head.getNombre().equals(empresa)) {
				return true;
			}
		}
		return false;
	}

	

//	public ArrayList<Integer> obtenerPeriodosSegunEmpresa(Empresa empresa) {
//		ArrayList<Periodo> periodos = new ArrayList<Periodo>();
//		ArrayList<Integer> periodosAux = new ArrayList<Integer>();
//
//		String empresaAsoc = empresa.toString();
//		if (validarEmpresa(empresaAsoc)) {
//			for (Empresa head : empresaAsociada) {
//				periodos = (ArrayList<Periodo>) head.getPeriodos();
//			}
//
//		}
//		for (int i = 0; i < periodos.size(); i++) {
//			periodosAux.add(periodos.get(i).getAnio());
//		}
//		return periodosAux;
	public List<Integer> obtenerPeriodosSegunEmpresa(Empresa empresa) {
		List<Periodo> periodos = new ArrayList<Periodo>();
		List<Integer> periodosAux = new ArrayList<Integer>();

		String empresaAsoc = empresa.getNombre();
		if (validarEmpresa(empresaAsoc)) {
			for (Empresa head : empresaAsociada) {
				periodos = head.getPeriodos();
			}

		}
		// for (int i = 0; i < periodos.size(); i++) {
		// periodosAux.add(periodos.get(i).getAnio());
		// }

		for (Periodo head : periodos) {
			periodosAux.add(head.getAnio());
		}
		return periodosAux;

	}

	public List<Periodo> getPeriodos(Empresa empresa) {
		List<Periodo> periodos = new ArrayList<Periodo>();
		String empresaAsoc = empresa.getNombre();
		if (validarEmpresa(empresaAsoc)) {
			for (Empresa head : empresaAsociada) {
				periodos =  head.getPeriodos();
			}
		}

		return periodos;
	}

	public int cantidadDeCuentas(Empresa empresa) {
		ArrayList<Cuenta> cuentaQuerida = this.obtenerCuentasSegunEmpresa(empresa);
		List<Periodo> periodos = this.getPeriodos(empresa);
		Cuenta cuentaQuerida2;
		int cantidad = 0;
		Iterator<Cuenta> cuentas = cuentaQuerida.iterator();
		System.out.print(cuentas.next().getDeuda());

		
		
//		System.out.printf("La cantidad de cuentas es %d\n", cantidad);
		
		return cantidad;
	}

	public ArrayList<Cuenta> obtenerCuentasSegunEmpresa(Empresa empresa) {
		List<Periodo> periodos = this.getPeriodos(empresa);

		ArrayList<Cuenta> cuentaQuerida = new ArrayList<Cuenta>();
		ArrayList<Cuenta> cuentaQuerida2 = new ArrayList<Cuenta>();
		for (int i = 0; i < periodos.size(); i++) {
			cuentaQuerida.add(periodos.get(i).getCuentas());
			System.out.printf("El size de las cuentas es \n");
			System.out.print(periodos.get(i).getCuentas());
		}
		
//			System.out.printf("El size de las cuentas es \n");
//			System.out.print(periodos.get(i).getCuentas());
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
				cuentaQuerida.add(cuentasAsociadas.get(i).getfCashFlow());
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
//		for (int i = 0; i < cuentas.size(); i++) {
//			if (periodos.get(i).equals(periodo)) {
//				cuenta = cuentas.get(i);
//			}
//		}

//		for (int i = 0; i < cuentas.size(); i++) {
//		if (periodos.get(i).equals(periodo)) {
//			cuenta = cuentas.get(i);
//		}
//	}
		for(Integer head:periodos){
			for(int i = 0;i<cuentas.size();i++){
				if(periodos.equals(periodo)){
					cuenta = cuentas.get(i);
				}
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

	public static void main(String args[]) {

	    Empresa empresaAsoc = new Empresa("America Movil");
		NuevoLeerArchivo arch = new NuevoLeerArchivo();

		System.out.printf("PERIODOS: ");
		System.out.printf("\nNombre empresa: %s\n", empresaAsoc.getNombre());
//		System.out.println(arch.getPeriodos(empresaAsoc));
		System.out.println(arch.obtenerPeriodosSegunEmpresa(empresaAsoc));

		//System.out.print(arch.obtenerCuentasSegunEmpresa(empresaAsoc));
		System.out.printf("\nEBITDA: ");
		System.out.print(arch.obtenerCuentaDe(empresaAsoc, "EBITDA"));
		System.out.print(arch.cantidadDeCuentas(empresaAsoc));

		System.out.printf("\nEBITDA: ");
		System.out.print(arch.obtenerCuentaSegunPeriodo(empresaAsoc, "EBITDA", 2006));

		
		for(Periodo head:arch.getPeriodos(empresaAsoc)){
			System.out.println(head.getEmpresa());
			head.setEmpresa(empresaAsoc);
			System.out.println(head.getEmpresa());
		}

	}
	/*
	 * SI QUIEREN UN PERIODO O UN DATO EN ESPECIFICO, SE HACE UN for(int i =
	 * 0;i<cuentita.size();i++)
	 * arch.obtenerPeriodosSegunEmpresa(empresaAsoc).get(i)
	 */
}

/*
 * JOptionPane.showMessageDialog(null,
 * "Ingrese una empresa en el cuadro de texto.",
 * "Ingrese una empresa",JOptionPane.WARNING_MESSAGE); (Ejemplo de mensaje de
 * error para usar en el futuro con indicadores
 */