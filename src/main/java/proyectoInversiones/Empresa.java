package proyectoInversiones;

import java.util.ArrayList;
import java.util.List;

public class Empresa {

	private String nombre;
	private int inicioActividad;
	private ArrayList<Periodo> Periodos;

	public Empresa() {
		Periodos = new ArrayList<Periodo>();
	}
	
	public Empresa(String nombreEmpresa){
		nombre = nombreEmpresa;
	}

	public String getNombre() {
		return nombre;
	}

	public String setNombre(String arg) {
		return nombre = arg;
	}

	public int getInicioActividad() {
		return inicioActividad;
	}

	public void setInicioActividad(int inicoActividad) {
		inicioActividad = inicoActividad;
	}

	public ArrayList<Periodo> getPeriodos() {
		return Periodos;
	}

	public void setPeriodos(ArrayList<Periodo> Cuentas) {
		this.Periodos = Cuentas;
	}

//	public void addPeriodo(Periodo b) {
//		Cuentas.add(b);
//	}
	
	public String toString(){
		return getNombre();
	}
	
	public int cantidadDeCuentas(){
		return this.getPeriodos().size();
	}
}


