package proyectoInversiones;

import java.util.HashSet;
import java.util.Set;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Column;
import javax.persistence.Table;

@Entity
@Table (name = "empresas")
//@NamedQuery(name = "buscarEmpresaPorNombre", query = "SELECT p FROM mpresas p WHERE p.nombre LIKE :pnombre")
public class Empresa extends AlgoPersistible {
	@OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL)
	private Set<Periodo> periodos;
	@Column(name = "inicioDeActividad")
	private int inicioActividad;
	
	public Empresa(){
		}
	
	/* 
	public Empresa(String nombreEmpresa, ArrayList<Periodo> periodosEmpresa){
		nombre = nombreEmpresa;
		periodos = periodosEmpresa;
		}
*/ 
	
	
	public int getInicioActividad() {
		return inicioActividad;
	}

	public void setInicioActividad(int unInicioActividad) {
		inicioActividad = unInicioActividad;
	}

	public Set<Periodo> getPeriodos() {
		return periodos;
	}

	public void setPeriodos(Set<Periodo> cuentas) {
		this.periodos = cuentas;
	}
	
	public void addPeriodo(Periodo unPeriodo){
		if (periodos == null){
			periodos = new HashSet<Periodo>();
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


