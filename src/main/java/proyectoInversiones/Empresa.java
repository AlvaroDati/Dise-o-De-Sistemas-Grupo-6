package proyectoInversiones;

import java.util.ArrayList;
import java.util.List;

public class Empresa {

	private String nombre;
	private int inicioActividad;
	private ArrayList<Cuenta> Cuentas;

	public Empresa() {
		Cuentas = new ArrayList<Cuenta>();
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

	public ArrayList<Cuenta> getCuentas() {
		return Cuentas;
	}

	public void setCuentas(ArrayList<Cuenta> Cuentas) {
		this.Cuentas = Cuentas;
	}

	public void addCuenta(Cuenta cuenta) {
		Cuentas.add(cuenta);
	}
	
	public String toString(){
		return getNombre();
	}
	
	public int cantidadDeCuentas(){
		return this.getCuentas().size();
	}
}


