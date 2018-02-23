package proyectoInversiones.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import proyectoInversiones.Cuenta;
import proyectoInversiones.Empresa;
import proyectoInversiones.NuevoLeerArchivo;
import proyectoInversiones.Periodo;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class CuentasController{
	
	public static ModelAndView listar(Request req, Response res) {
	    Map<String, List<Empresa>> model = new HashMap<>();
	    NuevoLeerArchivo arch = new NuevoLeerArchivo();
		
		model.put("empresasAMostrar",arch.leerArchivo());
		return new ModelAndView(model, "Cuentas2.html");
	}
	
		
	public static ModelAndView setearEmpresa(Request req, Response res) {
		
		String empresa = req.queryParams("Empresa");
		//String empresa = req.queryParams("browsers");
		System.out.println(empresa);
		try{
			Map<String, List<Cuenta>> model = new HashMap<>();
			NuevoLeerArchivo arch = new NuevoLeerArchivo();
			List<Periodo>periodosEmpresa =  arch.getPeriodos(new Empresa(empresa));
			List<Cuenta> cuentasDeEmpresa = setearListaCuentas(periodosEmpresa);
			
			Cuenta cuentaDeEmpresa = cuentasDeEmpresa.get(0);
			for(int i = 0;i<cuentasDeEmpresa.size();i++){
				System.out.println(cuentasDeEmpresa.get(i).getFCashFlow());
			}
			List<Cuenta> cuentaUnica = new ArrayList<Cuenta>();
			cuentaDeEmpresa.setEmpresaAsoc(empresa);
			System.out.println("\n" +cuentaDeEmpresa.getEmpresaAsoc());
			cuentaUnica.add(cuentaDeEmpresa);
			model.put("cuentaUnica",cuentaUnica);		
			model.put("cuentas", cuentasDeEmpresa);
			return new ModelAndView(model, "Cuentas2.html");
			
		}catch ( Exception e ){
			res.cookie("mensajeError", e.getMessage());
			res.redirect("/Cuentas2.html");
		}
		
		return null;
		
	}
	
	
	public static List<Cuenta> setearListaCuentas(List<Periodo> listaPeriodos){
		
		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		
		for (int i=0;i<listaPeriodos.size();i++){
			cuentas.add(listaPeriodos.get(i).getCuentas());
			cuentas.get(i).setPeriodoVinculado(listaPeriodos.get(i));
		}
		
		return cuentas;
	}
	
}
