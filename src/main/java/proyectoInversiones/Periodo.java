package proyectoInversiones;

import java.util.*;

public class Periodo {

	private int a�o;
	private ArrayList<Cuenta> Cuentas;
	
	public Periodo() {
		Cuentas = new ArrayList<Cuenta>();
	}
	
	public int getA�o() { 
		return a�o;
	}
	public void setA�o(int a�o) {
		this.a�o = a�o;
	}
	public ArrayList<Cuenta> getCuentas() {
		return Cuentas;
	}
	public void setCuentas(ArrayList<Cuenta> cuentas) {
		Cuentas = cuentas;
	}
	
	
	
}
