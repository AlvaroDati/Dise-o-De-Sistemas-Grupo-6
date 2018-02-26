package proyectoInversiones.indicadores;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import proyectoInversiones.Empresa;
import proyectoInversiones.Cuenta;
import proyectoInversiones.Indicador;
import proyectoInversiones.NuevoLeerArchivo;


public class ArmadorIndicador {
	
	NuevoLeerArchivo   archivoEmpresas  = new NuevoLeerArchivo();
	
	private String nombre;
	private Empresa empresa;
    private Cuenta cuentas;
    private Indicador indicador;
	private float valorIndicador;
	private int periodo;
	public int cantidadDeIndicadoresPredefinidos = 0;
	List<Float> valor_cuenta_indicador = new ArrayList<Float>(); /* ESTA LISTA ES PARA ALMACENAR LOS IDENTIFICADORES DEL USUARIO
	 																	QUE SE VAN A OBTENER DE LA CLASE IndVisitor
	 																	ESTO YA PINTA PARA UNA SUBCLASE....*/
	private IndVisitor indVisitor = new IndVisitor();
	/*************************************************************************
	 *    INICIO
	 * CONSTRUCTORES
	 *
	 */
	public ArmadorIndicador(){ 
		
	}
	
	public ArmadorIndicador(String nombreIngresado){
		nombre = nombreIngresado;
	}
	public ArmadorIndicador(String nombre,Empresa empresa){
		if(indicador == null){
			indicador = new Indicador(nombre,empresa);
		}
	}
	public ArmadorIndicador(Indicador indicadorIngresado){
		if(indicador == null)indicador = new Indicador(indicadorIngresado.getNombre(),indicadorIngresado.getEmpresa());
	}
	/*
	 *    FIN
	 * CONSTRUCTORES
	 *
	 *********************************************************/
	
	
	/********************************************************
	 *      INICIO
	 * SETTERS Y GETTERS
	 *
	 */
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Cuenta getCuentas() {
		return cuentas;
	}

	public void setCuentas(Cuenta cuentas) {
		this.cuentas = cuentas;
	}

	public Indicador getIndicador() {
		return indicador;
	}

	public void setIndicador(Indicador indicador) {
		this.indicador = indicador;
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

	public int getCantidadDeIndicadoresPredefinidos() {
		return cantidadDeIndicadoresPredefinidos;
	}

	public void setCantidadDeIndicadoresPredefinidos(int cantidadDeIndicadoresPredefinidos) {
		this.cantidadDeIndicadoresPredefinidos = cantidadDeIndicadoresPredefinidos;
	}

	public List<Float> getValorCuentaIndicador() {
		return valor_cuenta_indicador;
	}

	public void setValorCuentaIndicador(List<Float> valor_cuenta_indicador) {
		this.valor_cuenta_indicador = valor_cuenta_indicador;
	}
	public List<Indicador> getIndicadoresUsuario(String archivo,Empresa empresa,int periodo) throws IOException{
		IndVisitor indVisitor = new IndVisitor();
		List<Indicador> indicador = new ArrayList<Indicador>();
		List<Indicador> indicadoresUsuario =indVisitor.obtenerResultadosIndicadoresUsuarioSegunEmpresa(archivo,empresa,periodo);
		for(int i = 0;i<indicadoresUsuario.size();i++){
			System.out.printf("Nombre Indicador: %s = %f \n",indicadoresUsuario.get(i).getNombre(),indicadoresUsuario.get(i).getValorIndicador());
		}
		return indicadoresUsuario;
	}
	
	/*
	 *        FIN
	 * SETTERS Y GETTERS
	 *
	 **********************************************************************************/
	
	/**********************************************************************************
	 * 
	 * INICIO
	 * METODOS DE ArmarIndicadores
	 * @throws IOException 
	 * 
	 * */
	public List<Integer> periodos(Empresa empresa) throws IOException {
		return archivoEmpresas.obtenerPeriodosSegunEmpresa(empresa);
	}

	public float obtenerIngresoNetoSegunPeriodo(Empresa empresa, int periodo) throws IOException {
		float ingNetoAux = 0;
		ArrayList<Float> ingNeto = this.calcularIngresoNeto(empresa);
		List<Integer> periodosDeEmpresa = this.periodos(empresa);

		for (int i = 0; i < ingNeto.size(); i++) {
			if (periodosDeEmpresa.get(i).equals(periodo))
				ingNetoAux = ingNeto.get(i);
		}
		return ingNetoAux;
	}

	public float obtenerRoeSegunPeriodo(Empresa empresa, int periodo) throws IOException {
		float roeAux = 0;
		List<Float> roe = this.calcularRoe(empresa);
		List<Integer> periodosDeEmpresa = this.periodos(empresa);
		for (int i = 0; i < roe.size(); i++) {
			if (periodosDeEmpresa.get(i).equals(periodo))
				roeAux = roe.get(i);
		}
		return roeAux;
	}

	public float obtenerValorIndicador(Indicador indicador) throws IOException {
		float indicadorDeseado = 0;
		Empresa empresa = indicador.getEmpresa();
		List<Integer> periodosDeEmpresa = this.periodos(empresa);
		for(int i = 0;i<periodosDeEmpresa.size();i++){
			if(periodosDeEmpresa.get(i).equals(indicador.getPeriodo())){				
				switch(indicador.getNombre()){
				case("Ingreso Neto"):
					indicadorDeseado = this.calcularIngresoNeto(empresa).get(i);
					break;
				case("ROE"):
					indicadorDeseado = this.calcularRoe(empresa).get(i);
					break;
				default:
					break;
				}
			}
			
		}
		return indicadorDeseado;
	}
	

	
	public ArrayList<Float> calcularIngresoNeto(Empresa empresa) throws IOException {
		ArrayList<Float> ingNeto = new ArrayList<Float>();
		ArrayList<Float> ingNetoOpCont = archivoEmpresas.obtenerCuentaDe(empresa, "INGNETOOPCONT");
		ArrayList<Float> ingNetoOpDis = archivoEmpresas.obtenerCuentaDe(empresa, "INGNETOOPDISC");
		// Segun PDF de ENTREGA1 => IngresoNeto = IngresoNetoContinuo + IngresoNetoDiscontinuo
		for (int i = 0; i < ingNetoOpCont.size() && i < ingNetoOpDis.size(); i++) {
			ingNeto.add(ingNetoOpCont.get(i) + ingNetoOpDis.get(i));
		}
		this.setCantidadDeIndicadoresPredefinidos(this.getCantidadDeIndicadoresPredefinidos() + 1);
		return ingNeto;
	}
	
	public ArrayList<Float> calcularRoe(Empresa empresa) throws IOException{
		ArrayList<Float> roe     = new ArrayList<Float>();		
		ArrayList<Float> ingNeto = this.calcularIngresoNeto(empresa);
	
		ArrayList<Float> capitalTotal       = archivoEmpresas.sumaDeCuentasDe(empresa);
		
		//Segun PDF de ENTREGA2 => ROE = (ingNeto - dividendos)/capitalTotal
		for(int i = 0;i<ingNeto.size();i++){
			roe.add(ingNeto.get(i)/capitalTotal.get(i)); 
		}
		this.setCantidadDeIndicadoresPredefinidos(this.getCantidadDeIndicadoresPredefinidos() + 1);
		
		return roe;
	}

	
	
	public ArrayList<ArmadorIndicador> calcularIndicadorPredefinido(Empresa empresa,ArmadorIndicador indicador) throws IOException{
		ArrayList<ArmadorIndicador> indicadorPredefinido = new ArrayList<ArmadorIndicador>();
		
		switch(indicador.toString()){
		
		case("Ingreso Neto"):
			this.calcularIngresoNeto(empresa);
		case("ROE"):
			this.calcularRoe(empresa);
		}
		return indicadorPredefinido;
	}
	

}
