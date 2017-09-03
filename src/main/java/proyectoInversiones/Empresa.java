package proyectoInversiones;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Column;
import javax.persistence.Table;

@Entity
@Table (name = "Empresas")
public class Empresa {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL)
	private ArrayList<Periodo> periodos;
	
	
	@Column(name = "nombreEmpresa")
	private String nombre;
	@Column(name = "inicioDeActividad")
	private int inicioActividad;
	
	public Empresa(){
		}
	
	public Empresa(String nombreEmpresa){
		nombre = nombreEmpresa;
		
		}
	
	public Empresa(String nombreEmpresa,ArrayList<Periodo> periodosEmpresa){
		nombre = nombreEmpresa;
		periodos = periodosEmpresa;
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
		return periodos;
	}

	public void setPeriodos(ArrayList<Periodo> Cuentas) {
		this.periodos = Cuentas;
	}

//<<<<<<< HEAD
//	public void addCuenta(Cuenta cuenta) {
//		Cuentas.add(cuenta);
//	}
//=======
////	public void addPeriodo(Periodo b) {
////		Cuentas.add(b);
////	}
//>>>>>>> Entrega2-2.0
	
	public String toString(){
		return getNombre();
	}
	
	public int cantidadDeCuentas(){
		return this.getPeriodos().size();
	}
}


