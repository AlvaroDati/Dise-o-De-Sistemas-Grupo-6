package proyectoInversiones.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import proyectoInversiones.Cuenta;
import proyectoInversiones.Empresa;
import proyectoInversiones.NuevoLeerArchivo;
import proyectoInversiones.Periodo;
import proyectoInversiones.repositorio.RepositorioGeneral;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class CuentasController{
	
	public static ModelAndView listar(Request req, Response res) {
	  
		RepositorioGeneral repoGeneral = new RepositorioGeneral();
		NuevoLeerArchivo arch = new NuevoLeerArchivo();
		repoGeneral.setEmpresas(arch.leerArchivo());
		
		
		return new ModelAndView(repoGeneral, "Cuentas2.html");
	}
	
	
public static ModelAndView setearEmpresa(Request req, Response res) {
		
		String empresa = req.queryParams("Empresa");
		System.out.println(empresa);
		try{
			RepositorioGeneral repoGeneral = new RepositorioGeneral();
			NuevoLeerArchivo arch = new NuevoLeerArchivo();
			List<Periodo>periodosEmpresa =  arch.getPeriodos(new Empresa(empresa));
			repoGeneral.setEmpresas(arch.leerArchivo());
			repoGeneral.setCuentas(setearListaCuentas(periodosEmpresa));
			repoGeneral.setEmpresaAsociada(empresa);
			for(int i = 0;i<repoGeneral.getCuentas().size();i++){
				System.out.println(repoGeneral.getCuentas().get(i).getEbitda());
			}
			return new ModelAndView(repoGeneral, "Cuentas2.html");
		}catch ( Exception e ){
			res.cookie("mensajeError", e.getMessage());
			res.redirect("/cuentas");
		}
		
		return null;
		
	}
	
	public static List<Cuenta> setearListaCuentas(List<Periodo> listaPeriodos){
		
		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		
		for (int i=0;i<listaPeriodos.size();i++){
			cuentas.add(listaPeriodos.get(i).getCuentas());
			cuentas.get(i).setPeriodoVinculado(listaPeriodos.get(i));
			//cuentas.get(i).setEmpresas(new NuevoLeerArchivo().leerArchivo());
		}
		
		return cuentas;
	}
	
}
