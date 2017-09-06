package proyectoInversiones;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "indicadores")
public class Indicador extends AlgoPersistible{

	
	public Indicador(){
		
	}
	public Indicador(String nombreIngresado,Empresa empresa){
		nombre = nombreIngresado;
		if(empresa == null) empresa = new Empresa(empresa.getNombre()); 
	}
	

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "empresa_id", referencedColumnName = "id")
	private Empresa empresa;
	@Column(name = "valorIndicador",nullable = false)
	private float valorIndicador;
	@Column(name = "anio",nullable=false)
	private int periodo;
	
	
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public float getValorIndicador() {
		return valorIndicador;
	}
	public void setValorIndicador(float valorIndicador) {
		this.valorIndicador = valorIndicador;
	}
	public int getPeriodo() {
		return periodo;
	}
	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}

}
