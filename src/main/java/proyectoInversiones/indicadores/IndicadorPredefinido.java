package proyectoInversiones.indicadores;

import proyectoInversiones.*; 

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class IndicadorPredefinido {
	
	NuevoLeerArchivo archivo = new NuevoLeerArchivo();
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
}
