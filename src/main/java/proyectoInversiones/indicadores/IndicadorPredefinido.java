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
	public IndicadorPredefinido(){
		
	}
	public void inicializarCuentas(){
		
		for(Empresa head:empresas){
			cuentas.addAll(head.getCuentas());
		}
		
	}
	
	public static void main (String args[]){
		IndicadorPredefinido pre = new IndicadorPredefinido();
		pre.inicializarCuentas();
		System.out.println(pre.getCuentas());
	}
}
