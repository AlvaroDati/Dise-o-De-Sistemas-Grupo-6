package proyectoInversiones;

import java.util.*;

public class Periodo {

	private int año;
	private List<Cuenta> Cuentas;
	
	
	public int getAño() { 
		return año;
	}
	public void setAño(int año) {
		this.año = año;
	}
	public List<Cuenta> getCuentas() {
		return Cuentas;
	}
	public void setCuentas(List<Cuenta> cuentas) {
		Cuentas = cuentas;
	}
	
	
	
}
