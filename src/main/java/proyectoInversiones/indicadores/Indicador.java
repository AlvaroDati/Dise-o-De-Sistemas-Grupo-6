package proyectoInversiones.indicadores;

import proyectoInversiones.*; 

import proyectoInversiones.Ventana;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Indicador {

	NuevoLeerArchivo   archivoEmpresas  = new NuevoLeerArchivo();
	ArrayList<Empresa> empresas 	    = new ArrayList<Empresa>();
	ArrayList<Cuenta>  cuentaAsociada   = new ArrayList<Cuenta>();
	Empresa            empresaAsociada 	= new Empresa();
	
	
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

	public ArrayList<Float> ingresoNeto(Empresa empresa){
		ArrayList<Float>  ingNeto         = new ArrayList<Float>();
		ArrayList<Float>  ingNetoOpCont   = archivoEmpresas.obtenerCuentaDe(empresa, "INGNETOOPCONT");
		ArrayList<Float>  ingNetoOpDis    = archivoEmpresas.obtenerCuentaDe(empresa, "INGNETOOPDISC");
		//Segun PDF de ENTREGA1 => IngresoNeto = IngresoNetoContinuo + IngresoNetoDiscontinuo
		for(int i = 0;i<ingNetoOpCont.size() && i<ingNetoOpDis.size();i++){
			ingNeto.add(ingNetoOpCont.get(i) + ingNetoOpDis.get(i));
		}
		return ingNeto;
	}
	
	public ArrayList<Float> roe(Empresa empresa){
		ArrayList<Float> roe     = new ArrayList<Float>();		
		ArrayList<Float> ingNeto = this.ingresoNeto(empresa);
	
		float capitalTotal       = archivoEmpresas.sumaDeCuentasDe(empresa);
		
		//Segun PDF de ENTREGA2 => ROE = (ingNeto - dividendos)/capitalTotal
		for(int i = 0;i<ingNeto.size();i++){
			roe.add(ingNeto.get(i)/capitalTotal); //DIVIDENDOS ??
		}
	
		
		
		return roe;
	}
	 
	
	
	public static void main (String args[]){
		
		Indicador indicador     = new Indicador();
		Empresa empresaAsociada = new Empresa("America Movil");
		
	
		 System.out.printf("Ingreso Neto: ");
		 System.out.print(indicador.ingresoNeto(empresaAsociada));
		 System.out.printf("\nRetorno sobre Capital Total (ROE): ");
		 System.out.print(indicador.roe(empresaAsociada));
	
		
	}
	
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
