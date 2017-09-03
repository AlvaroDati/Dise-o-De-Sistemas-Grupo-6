package proyectoInversiones;

import java.util.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Periodos")
public class Periodo {
	
    @Id @GeneratedValue
	private int id;
    
	private int anio;
	@OneToMany
	private ArrayList<Cuenta> cuentas;
	
	public Periodo() {
		cuentas = new ArrayList<Cuenta>();
	}
	
	public Periodo(ArrayList<Cuenta> unasCuentas) {
		cuentas = unasCuentas;
	}
	
	public int getAnio() { 
		return anio;
	}
	public void setAnio(int anio) {
		this.anio = anio;
	}
	public ArrayList<Cuenta> getCuentas() {
		return cuentas;
	}
	public void setCuentas(ArrayList<Cuenta> unasCuentas) {
		cuentas = unasCuentas;
	}
	
	
	
}
