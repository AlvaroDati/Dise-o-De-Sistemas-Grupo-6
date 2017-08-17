package proyectoInversiones;

import java.util.*;

public class Periodo {

	private int año;
	private ArrayList<Cuenta> Cuentas;
	
	public Periodo() {
		Cuentas = new ArrayList<Cuenta>();
	}
	
	public int getAño() { 
		return año;
	}
	public void setAño(int año) {
		this.año = año;
	}
	public ArrayList<Cuenta> getCuentas() {
		return Cuentas;
	}
	public void setCuentas(ArrayList<Cuenta> cuentas) {
		Cuentas = cuentas;
	}
	
	
	
}
