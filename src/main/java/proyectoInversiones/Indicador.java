package proyectoInversiones;

import java.util.ArrayList;
import java.util.List;

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
import javax.persistence.Transient;

import proyectoInversiones.indicadores.ArmadorIndicador;
import proyectoInversiones.usuarios.Usuario;

@Entity
@Table(name = "indicadores")
public class Indicador extends AlgoPersistible{

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_id", referencedColumnName = "id")
	private Usuario usuario;

	@Column(name = "valorIndicador",nullable = false)
	private float valorIndicador;
	@Column(name = "anio",nullable=false)
	private int periodo;
	@Column(name = "empresaAsoc", nullable = false)	
	private String empresaAsoc;
	
	@Transient
	private Empresa empresa;
	@Transient
	private String expresion;
	
	@Transient
	protected  float roe;
	@Transient
	protected float ingresoNeto;
	@Transient
	private List<String> cuentas = new ArrayList<String>();
	
	public void agregarCuenta(String cuenta){
		this.getCuentas().add(cuenta);
	}
	public List<String> getCuentas() {
		return cuentas;
	}
	public void setCuentas(List<String> cuentas) {
		this.cuentas = cuentas;
	}
	public Indicador(){
		
	}
	public Indicador(String nombreIngresado,Empresa empresa){
		nombre = nombreIngresado;
		if(empresa == null) empresa = new Empresa(empresa.getNombre()); 
	}
	
	
	public String getEmpresaAsoc() {
		return empresaAsoc;
	}
	
	public void setEmpresaAsoc(String unNombreDeEmpresa) {
		this.empresaAsoc = unNombreDeEmpresa;
	}
	
	public Empresa getEmpresa(){
		return empresa;
	}
	
	public void setEmpresa(Empresa unaEmpresa){
		empresa = unaEmpresa;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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
	
	public void setRoe(float valorRoe) {
		this.roe = valorRoe;
	}
	public float getRoe() {
		return roe;
	}
	
	public void setIngresoNeto(float valorIngreso) {
		this.ingresoNeto = valorIngreso;
	}
	
	public float getIngresoNeto() {
		return ingresoNeto;
	}
	
	
}
