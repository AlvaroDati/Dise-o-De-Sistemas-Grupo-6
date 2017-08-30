package proyectoInversiones;

import java.util.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


public class Periodo {

	private int anio;
	private ArrayList<Cuenta> Cuentas;
	
	public Periodo() {
		Cuentas = new ArrayList<Cuenta>();
	}
	
	public int getAnio() { 
		return anio;
	}
	public void setAnio(int anio) {
		this.anio = anio;
	}
	public ArrayList<Cuenta> getCuentas() {
		return Cuentas;
	}
	public void setCuentas(ArrayList<Cuenta> cuentas) {
		Cuentas = cuentas;
	}
	
	
	
}
