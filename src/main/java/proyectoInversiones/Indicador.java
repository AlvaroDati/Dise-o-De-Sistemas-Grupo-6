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
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import java.io.IOException;
import java.io.Serializable;
import java.time.Year;
import proyectoInversiones.metodologias.Cuantificador;
import proyectoInversiones.indicadores.IndVisitor;
import proyectoInversiones.usuarios.Usuario;

@Entity
@Table(name = "indicadores")
@NamedQuery(name = "buscarIndicadorPorUsuario", query = "SELECT u FROM Indicador u WHERE u.usuario.id  =:filtro")
public class Indicador  implements Serializable{
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column(name = "nombre")
	protected String nombre;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_id", referencedColumnName = "id")
	private Usuario usuario;
	@Column(name = "valorindicador",nullable = false)
	private float valorIndicador;
	@Column(name = "anio")
	private int periodoA;
	@Column(name = "empresaasoc")	
	protected String empresaAsoc;
	
	public String getEmpresaAsoc() {//cambié esto, me parece que tiene mas sentido así; deje el setter por las dudas, por si se usa
		empresaAsoc = empresa.getNombre();
		return empresaAsoc;
	}
	
	public void setEmpresaAsoc(String unNombreDeEmpresa) {
		this.empresaAsoc = unNombreDeEmpresa; 	
		}
	@Transient
	private Empresa empresa;
	@Column(name = "expresion")
	private String expresion;
	
	@Transient
	protected  float roe;
	@Transient
	protected float ingresoNeto;
	@Transient
	private List<String> cuentas = new ArrayList<String>();
	
//	public Long getId() {
//		return id;
//	}
//	public void setId(Long id) {
//		this.id = id;
//	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
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
		if(empresa == null) empresa = new Empresa(empresa.getNombre()); //no entiendo el sentido de esto, lean el warning
	}
	
	public String getExpresion() {
		return expresion;
	}
	public void setExpresion(String expresion) {
		this.expresion = expresion;
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
		return periodoA;
	}
	public void setPeriodo(int periodo) {
		this.periodoA = periodo;
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
	
	public int evaluarEn(Empresa empresa, Year anio){
		IndVisitor unIndVisitor = new IndVisitor();
		int anioParseado = anio.getValue();
		Indicador unIndicadorAux = null;
		try {
			unIndicadorAux = unIndVisitor.obtenerResultadoIndicadorSegunEmpresa(this.getExpresion(), empresa, anioParseado);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return (int) unIndicadorAux.getValorIndicador();
	}
	
	
}
