package proyectoInversiones;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Column;
import javax.persistence.Table;

@Entity
@Table (name = "empresas")
//@NamedQuery(name = "buscarEmpresaPorNombre", query = "SELECT p FROM Empresas p WHERE p.nombre LIKE :pnombre")
public class Empresa  {
	
	
	private String nombre;
	@Column(name = "inicioDeActividad")
	private int inicioActividad;
	@OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL)
	private List<Periodo> periodos;
	
	public Empresa(){
		periodos = new ArrayList<Periodo>();
		}
	public Empresa(String nombreEmpresa){
		nombre = nombreEmpresa;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/* 
	public Empresa(String nombreEmpresa, ArrayList<Periodo> periodosEmpresa){
		nombre = nombreEmpresa;
		Periodos = periodosEmpresa;
		}
*/ 
	
	
	
	
	public int getInicioActividad() {
		return inicioActividad;
	}

	public void setInicioActividad(int unInicioActividad) {
		inicioActividad = unInicioActividad;
	}

	public List<Periodo> getPeriodos() {
		return periodos;
	}

	public void setPeriodos(List<Periodo> cuentas) {
		this.periodos = cuentas;
	}
	
	public void addPeriodo(Periodo unPeriodo){
		if (periodos == null){
			periodos = new ArrayList<Periodo>();
		}
		periodos.add(unPeriodo);
	}
	

//
//	public void addCuenta(Cuenta cuenta) {
//		Cuentas.add(cuenta);
//	}
//
//	public void addPeriodo(Periodo b) {
//		Cuentas.add(b);
//	}
//Entrega2-2.0
	
	
	public int cantidadDeCuentas(){
		return this.getPeriodos().size();
	}
}


