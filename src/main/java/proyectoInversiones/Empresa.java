package proyectoInversiones;
import java.util.List;
import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Column;
import javax.persistence.Table;


@Entity
@Table (name = "empresas")
//@NamedQuery(name = "buscarEmpresaPorNombre", query = "SELECT empresa FROM Empresa empresa WHERE empresa.nombre LIKE :filtro")
public class Empresa extends AlgoPersistible {
	
	@Column(name = "inicioDeActividad")
	private int inicioActividad;
	
	@OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL)
	private List<Periodo> periodos;
	
/*
   @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
   private List<Indicador> indicadores;
	
	public List<Indicador> getIndicadores() {
		return indicadores;
	}
	public void setIndicadores(List<Indicador> indicadores) {
		this.indicadores = indicadores;
	}
	public void addIndicador(Indicador indicador){
		if(indicadores == null) indicadores = new ArrayList<Indicador>();
		
		if(!this.getIndicadores().contains(indicador))indicadores.add(indicador);
	}
	*/
	
	public Empresa(){
		periodos = new ArrayList<Periodo>();
		}
	
	public Empresa(String nombreEmpresa){
		nombre = nombreEmpresa;
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


