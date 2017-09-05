package proyectoInversiones.indicadores;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import proyectoInversiones.*;


@Entity
@Table(name = "Indicador")
public class Indicador extends AlgoPersistible{

	
	NuevoLeerArchivo   archivoEmpresas  = new NuevoLeerArchivo();
	
	
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cuenta_id", referencedColumnName = "id")
	private Cuenta cuentaAsociada ;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "empresa_id",referencedColumnName = "id")
    private Empresa empresa;
    @Column(name = "valorIndicador")
    private float valorIndicador;    
    @Column(name = "anio")
    private int periodo;

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
//	private String nombre;
	
	List<Float> valor_cuenta_indicador = new ArrayList<Float>(); /* ESTA LISTA ES PARA ALMACENAR LOS IDENTIFICADORES DEL USUARIO
	 																	QUE SE VAN A OBTENER DE LA CLASE IndVisitor
	 																	ESTO YA PINTA PARA UNA SUBCLASE....*/
	public int cantidadDeIndicadoresPredefinidos = 0;
	
	public List<Float> getValorCuentaIndicador() {
		return valor_cuenta_indicador;
	}

	public void setValorCuentaIndicador(ArrayList<Float> valor_cuenta_indicador) {
		this.valor_cuenta_indicador = valor_cuenta_indicador;
	}

	public int getCantidadDeIndicadoresPredefinidos() {
		return cantidadDeIndicadoresPredefinidos;
	}

	public void setCantidadDeIndicadoresPredefinidos(int cantidadDeIndicadoresPredefinidos) {
		this.cantidadDeIndicadoresPredefinidos = cantidadDeIndicadoresPredefinidos;
	}

	public Indicador(){ //Constructor vacio
		
	}
	
	public Indicador(String nombreIngresado){
		nombre = nombreIngresado;
	}
	
	public float getValorIndicador() {
		return valorIndicador;
	}

	public void setValorIndicador(float valorIndicador) {
		this.valorIndicador = valorIndicador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombreIndicador) {
		nombre = nombreIndicador;
	}
	public List<Integer> periodos(Empresa empresa){
		return archivoEmpresas.obtenerPeriodosSegunEmpresa(empresa);
	}
	public float obtenerRoeSegunPeriodo(Empresa empresa, int periodo){
		float roeAux = 0;
		return roeAux;
	}
	public float obtenerIngresoNetoSegunPeriodo(Empresa empresa, int periodo){
		float ingNetoAux = 0;
		return ingNetoAux;
	}
	
	
	public List<Integer> periodos1(Empresa empresa){
		return archivoEmpresas.obtenerPeriodosSegunEmpresa(empresa);
	}
	public int getPeriodo() {
		return periodo;
	}
	
	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}
	
	public ArrayList<Float> ingresoNeto(Empresa empresa) {
		ArrayList<Float> ingNeto = new ArrayList<Float>();
		ArrayList<Float> ingNetoOpCont = archivoEmpresas.obtenerCuentaDe(empresa, "INGNETOOPCONT");
		ArrayList<Float> ingNetoOpDis = archivoEmpresas.obtenerCuentaDe(empresa, "INGNETOOPDISC");
		// Segun PDF de ENTREGA1 => IngresoNeto = IngresoNetoContinuo +
		// IngresoNetoDiscontinuo
		for (int i = 0; i < ingNetoOpCont.size() && i < ingNetoOpDis.size(); i++) {
			ingNeto.add(ingNetoOpCont.get(i) + ingNetoOpDis.get(i));
		}
		this.setCantidadDeIndicadoresPredefinidos(this.getCantidadDeIndicadoresPredefinidos() + 1);
		return ingNeto;
	}
	public ArrayList<Float> roe(Empresa empresa){
		ArrayList<Float> roe     = new ArrayList<Float>();		
		ArrayList<Float> ingNeto = this.ingresoNeto(empresa);
	
		ArrayList<Float> capitalTotal       = archivoEmpresas.sumaDeCuentasDe(empresa);
		
		//Segun PDF de ENTREGA2 => ROE = (ingNeto - dividendos)/capitalTotal
		for(int i = 0;i<ingNeto.size();i++){
			roe.add(ingNeto.get(i)/capitalTotal.get(i)); 
		}
		this.setCantidadDeIndicadoresPredefinidos(this.getCantidadDeIndicadoresPredefinidos() + 1);
		
		return roe;
	}

	
	
	public ArrayList<Indicador> calcularIndicadorPredefinido(Empresa empresa,Indicador indicador){
		ArrayList<Indicador> indicadorPredefinido = new ArrayList<Indicador>();
		
		switch(indicador.toString()){
		
		case("Ingreso Neto"):
			this.ingresoNeto(empresa);
		case("ROE"):
			this.roe(empresa);
		}
		return indicadorPredefinido;
	}
	

	
	public static void main (String args[]){
		
	}
}
