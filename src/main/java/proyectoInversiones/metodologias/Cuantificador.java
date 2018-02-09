package proyectoInversiones.metodologias;


import java.io.Serializable;
import java.time.Year;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.poi.ss.formula.functions.T;

import proyectoInversiones.Empresa;
import proyectoInversiones.usuarios.Usuario;

@SuppressWarnings("serial")
@Entity
@Table(name = "cuantificadores")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Cuantificador implements Serializable{

	@Id 
	@GeneratedValue//(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name = "valorIndicador",nullable = false)
	protected float valorIndicador;
	@Column(name = "anio")
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected String periodos;
	@Column(name = "empresaAsoc")	
	protected String empresaAsoc;
	
	
//	@Column(name = "empresaAsoc")	
//	protected String empresaAsoc;
	
//	public String getEmpresaAsoc() {//cambié esto, me parece que tiene mas sentido así; deje el setter por las dudas, por si se usa
//		return empresaAsoc;
//	}
//	
//	public void setEmpresaAsoc(String unNombreDeEmpresa) {
//		this.empresaAsoc = unNombreDeEmpresa; 	
//		}
	
	public float getValorIndicador() {
		return valorIndicador;
	}


	public void setValorIndicador(float valorIndicador) {
		this.valorIndicador = valorIndicador;
	}


	public String getPeriodos() {
		return periodos;
	}


	public void setPeriodos(String periodos) {
		this.periodos = periodos;
	}


	public String getEmpresaAsoc() {
		return empresaAsoc;
	}


	public void setEmpresaAsoc(String empresaAsoc) {
		this.empresaAsoc = empresaAsoc;
	}


	public abstract int evaluarEn(Empresa empresa, Year anio);
	
	
	
}
