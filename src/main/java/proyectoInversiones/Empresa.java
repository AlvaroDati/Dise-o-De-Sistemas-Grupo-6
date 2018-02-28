package proyectoInversiones;
import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;

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

@SuppressWarnings("serial")
@Entity
@Table (name = "empresas")
@NamedQuery(name = "buscarEmpresaPorNombre", query = "SELECT e FROM Empresa e WHERE e.nombre LIKE :filtro")
public class Empresa implements Serializable {
	
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;
	@Column(name = "nombre")
	protected String nombre;
	
	@Column(name = "iniciodeactividad")
	private int inicioActividad;
	
	@OneToMany(mappedBy = "empresa",cascade = CascadeType.ALL)
	private List<Periodo> periodos;
	
	
	public Empresa(){
		periodos = new ArrayList<Periodo>();
		}
	
	public Empresa(String nombreEmpresa){
		nombre = nombreEmpresa;
	}
	
	
	
	
	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	

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
	
	public int cantidadDeCuentas(){
		return this.getPeriodos().size();
	}

//
//	public void addCuenta(Cuenta cuenta) {
//		Cuentas.add(cuenta);
//	}
//
//	public void addPeriodo(Periodo b) {
//		Cuentas.add(b);
//	}
	 
//	public Empresa(String nombreEmpresa, ArrayList<Periodo> periodosEmpresa){
//		nombre = nombreEmpresa;
//		Periodos = periodosEmpresa;
//		}
 
//Entrega2-2.0
	
}


