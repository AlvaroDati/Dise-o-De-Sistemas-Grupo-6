package proyectoInversiones.indicadores;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import proyectoInversiones.*;

public class Indicador {

	NuevoLeerArchivo   archivoEmpresas  = new NuevoLeerArchivo();
	ArrayList<Empresa> empresas 	    = new ArrayList<Empresa>();
	ArrayList<Cuenta>  cuentaAsociada   = new ArrayList<Cuenta>();
	Empresa            empresaAsociada 	= new Empresa();
	
	
	private String nombre;
	private float valorIndicador;
	private int periodo;
	ArrayList<Float> valor_cuenta_indicador = new ArrayList<Float>(); /* ESTA LISTA ES PARA ALMACENAR LOS IDENTIFICADORES DEL USUARIO
	 																	QUE SE VAN A OBTENER DE LA CLASE IndVisitor
	 																	ESTO YA PINTA PARA UNA SUBCLASE....*/
	public int cantidadDeIndicadoresPredefinidos = 0;
	
	public ArrayList<Float> getValorCuentaIndicador() {
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
	public ArrayList<Integer> periodos(Empresa empresa){
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
	
	
	public ArrayList<Integer> periodos1(Empresa empresa){
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
//<<<<<<< HEAD
//			roe.add((ingNeto.get(i)/capitalTotal)*100); //DIVIDENDOS ??
//=======
			roe.add(ingNeto.get(i)/capitalTotal.get(i)); //DIVIDENDOS ??
//>>>>>>> Entrega2-2.0
		}
	
		this.setCantidadDeIndicadoresPredefinidos(this.getCantidadDeIndicadoresPredefinidos() + 1);
		
		return roe;
	}
}
	//<<<<<<< Updated upstream
	
//	
//	public float obtenerRoeSegunPeriodo(Empresa empresa, int periodo){
//		float roeAux = 0;
//		ArrayList<Float> roe = this.roe(empresa);
//		ArrayList<Integer> periodos    = this.periodos(empresa);
//		for(int i = 0;i<roe.size();i++){
//			if(periodos.get(i).equals(periodo)){
//				roeAux = roe.get(i);
//			}//ASUMO QUE TODOS LOS PERIODOS TIENEN ROE
//		}
//		return roeAux;
//	}
//	
//	public float obtenerIngresoNetoSegunPeriodo(Empresa empresa, int periodo){
//		float ingNetoAux = 0;
//		ArrayList<Float> ingresosNetos = this.ingresoNeto(empresa);
//		ArrayList<Integer> periodos    = this.periodos(empresa);
//		for(int i = 0;i<ingresosNetos.size();i++){
//			if(periodos.get(i).equals(periodo)){
//				ingNetoAux = ingresosNetos.get(i);
//			}//ASUMO QUE TODOS LOS PERIODOS TIENEN INGRESOS NETOS
//		}
//		
//		return ingNetoAux;
//	}
//=======
//	public float ingresoNeto(Empresa empresa){
//		float ingneto = 0;
//		ArrayList<Float> cuentas = new ArrayList<Float>();
//		cuentas = archivoEmpresas.obtenerCuentaDe(empresa, "INGINGNETOOPCONT");
//		cuentas = archivoEmpresas.obtenerCuentaDe(empresa, "INGINGNETOOPDIS");
//>>>>>>> Stashed changes
//	
//	public ArrayList<Float> ingresoNeto(Empresa empresa){
//		ArrayList<Float>  ingNeto         = new ArrayList<Float>();
//		ArrayList<Float>  ingNetoOpCont   = archivoEmpresas.obtenerCuentaDe(empresa, "INGNETOOPCONT");
//		ArrayList<Float>  ingNetoOpDis    = archivoEmpresas.obtenerCuentaDe(empresa, "INGNETOOPDISC");
//		//Segun PDF de ENTREGA1 => IngresoNeto = IngresoNetoContinuo + IngresoNetoDiscontinuo
//		for(int i = 0;i<ingNetoOpCont.size() && i<ingNetoOpDis.size();i++){
//			ingNeto.add(ingNetoOpCont.get(i) + ingNetoOpDis.get(i));
//		}
//		this.setCantidadDeIndicadoresPredefinidos(this.getCantidadDeIndicadoresPredefinidos() + 1);
//		return ingNeto;
//	}
//	
//	public ArrayList<Float> roe(Empresa empresa){
//		ArrayList<Float> roe     = new ArrayList<Float>();		
//		ArrayList<Float> ingNeto = this.ingresoNeto(empresa);
//	
//		ArrayList<Float> capitalTotal       = archivoEmpresas.sumaDeCuentasDe(empresa);
//		
//		//Segun PDF de ENTREGA2 => ROE = (ingNeto - dividendos)/capitalTotal
//		for(int i = 0;i<ingNeto.size();i++){
//			roe.add(ingNeto.get(i)/capitalTotal.get(i)); //DIVIDENDOS ??
//		}
//	
//		this.setCantidadDeIndicadoresPredefinidos(this.getCantidadDeIndicadoresPredefinidos() + 1);
//		
//		return roe;
//	}
//	 
//	
//	
//	public static void main (String args[]){
//		
//		Indicador indicador     = new Indicador();
//		Empresa empresaAsociada = new Empresa("America Movil");
//		
//	
//		 System.out.printf("Ingreso Neto: ");
//		 System.out.print(indicador.ingresoNeto(empresaAsociada));
//		 System.out.printf("\nRetorno sobre Capital Total (ROE): ");
//		 System.out.print(indicador.roe(empresaAsociada));
//	
//		
//	}
//
//	
//}
	
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
			//Esta super suma se deber�a delegar en Cuenta.java, vendr�a a ser el capitalTotal
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
