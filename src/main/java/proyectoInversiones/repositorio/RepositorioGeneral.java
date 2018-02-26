package proyectoInversiones.repositorio;

import java.util.ArrayList;
import java.util.List;

import proyectoInversiones.Cuenta;
import proyectoInversiones.Empresa;
import proyectoInversiones.Indicador;

public class RepositorioGeneral {
	List<Empresa> empresas = new ArrayList<Empresa>();
	List<Cuenta> cuentas = new ArrayList<Cuenta>();
	List<Indicador> indicadores = new ArrayList<Indicador>();
	List<Indicador> indicadoresUsuario = new ArrayList<Indicador>();
	public List<Indicador> getIndicadoresUsuario() {
		return indicadoresUsuario;
	}
	public void setIndicadoresUsuario(List<Indicador> indicadoresUsuario) {
		this.indicadoresUsuario = indicadoresUsuario;
	}
	String empresaAsociada = null;
	private static RepositorioGeneral repositorioGeneral;
	public RepositorioGeneral(){
	
	}
	public String getEmpresaAsociada() {
		return empresaAsociada;
	}
	public void setEmpresaAsociada(String empresaAsociada) {
		this.empresaAsociada = empresaAsociada;
	}
	public static RepositorioGeneral getInstance(){
		if(repositorioGeneral == null) new RepositorioGeneral();
		return repositorioGeneral;
	}
	public List<Empresa> getEmpresas() {
		return empresas;
	}
	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}
	public List<Cuenta> getCuentas() {
		return cuentas;
	}
	public void setCuentas(List<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}
	public List<Indicador> getIndicadores() {
		return indicadores;
	}
	public void setIndicadores(List<Indicador> indicadores) {
		this.indicadores = indicadores;
	}
	
	
	
	
	
}
