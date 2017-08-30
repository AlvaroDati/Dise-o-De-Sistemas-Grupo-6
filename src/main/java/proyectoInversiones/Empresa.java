package proyectoInversiones;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name = "Empresas")
public class Empresa {

	@Id@GeneratedValue	
	private int id;
	
	private String nombre;
	private int inicioActividad;
	private ArrayList<Periodo> Periodos;
	
	public Empresa() {
		Periodos = new ArrayList<Periodo>();
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public ArrayList<Periodo> getPeriodos() {
		return Periodos;
	}

	public void setPeriodos(ArrayList<Periodo> Cuentas) {
		this.Periodos = Cuentas;
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


