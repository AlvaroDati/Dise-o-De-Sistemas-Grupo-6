package proyectoInversiones.indicadores;

import proyectoInversiones.*; 

import proyectoInversiones.Ventana;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Indicador {

	
	private String nombre;
	private float valorIndicador;
	
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

	Ventana ventana = new Ventana();
	
	ventana.getIndicadorIngresado();
	
	
	Indicador indicadorDefinido = new Indicador("nombreIndicador",valor);
	 
	
}
	
	/*
	 * 
	 * ArrayList<Empresa> empresaAsociada = new ArrayList<Empresa>();
	ArrayList<Cuenta> cuentasAsociadas   = new ArrayList<Cuenta>();
	empresaAsociada = this.leerArchivo();
	 * 
	 * public ArrayList<Cuenta> obtenerCuentasSegunEmpresa(Empresa empresa){
		
		String empresaAsoc = empresa.toString();
		if(!validarEmpresa(empresaAsoc)){
			for(Empresa head:empresaAsociada){
				if(head.getNombre().equals(empresaAsoc)){
					cuentasAsociadas = head.getCuentas();
				}
			}
			
		}
	return cuentasAsociadas;
	}
	
	public Integer obtenerPeriodosDeEmpresa(Empresa empresa){
		ArrayList<Cuenta> cuentaTomada = this.
		for(Cuenta head:cuentaTomada){
		
		}
	}
	 * 
	 * 
	 * 
	 * 
	 * 
	 * NuevoLeerArchivo archivo = new NuevoLeerArchivo();
	Collection<Empresa> empresas = archivo.leerArchivo();
	List<Cuenta> cuentas = new ArrayList<Cuenta>();

	public Collection<Empresa> getEmpresas() {
		return empresas;
	}
	public void setEmpresas(Collection<Empresa> empresas) {
		this.empresas = empresas;
	}
	public List<Cuenta> getCuentas() {
		return cuentas;
	}
	public void setCuentas(List<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}

	public void inicializarCuentas(){
		int i = 0;
		List<Cuenta> asf = new ArrayList<Cuenta>();
		for(Empresa head:empresas){
			asf = head.getCuentas();
			cuentas.addAll(i, asf);;
		}
		
	}
	public boolean validarEmpresa(String empresa){
		
		for(Empresa head:empresas){
			if(head.getNombre().contains(empresa)){ 
				return true;
				}
			}
			return false;
	}
	public int calcularIngNeto(String empresa, int periodo){ //INDICADOR PREDEFINIDO POR EL USUARIO
		int ingNeto = 0 ;
		
			if(validarEmpresa(empresa))
				this.inicializarCuentas();
			for(Cuenta head2:cuentas){
				if(head2.getPeriodo() == periodo){
					ingNeto = head2.getIngNetoOpCont() + head2.getIngNetoOpDiscont();
				}
			}
	
		return ingNeto;
	} 
	
	public int calcularRoe(String empresa, int periodo){
		int roe = 0;
		if(validarEmpresa(empresa))
			this.inicializarCuentas();
		for(Cuenta head2:cuentas){
			if(head2.getPeriodo() == periodo){
				roe = this.calcularIngNeto(empresa,periodo) /
						(head2.getEbitda() +
						head2.getfCashFlow() +
						head2.getFds() + 
						head2.getIngNetoOpCont()
						+head2.getIngNetoOpDiscont());
			//Esta super suma se debería delegar en Cuenta.java, vendría a ser el capitalTotal
			}
		}
		return roe;
	}
	
	public static void main (String args[]){
		IndicadorPredefinido pre = new IndicadorPredefinido();
		System.out.println(pre.getEmpresas());
		pre.inicializarCuentas();
		System.out.println(pre.getCuentas());
		String amer = "America Movil";
		
		System.out.print(pre.calcularIngNeto(amer, 2006));
	}
}*/
