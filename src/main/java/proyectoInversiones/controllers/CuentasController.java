package proyectoInversiones.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import proyectoInversiones.Cuenta;
import proyectoInversiones.DescargaDrive;
import proyectoInversiones.Empresa;
//import proyectoInversiones.NuevoLeerArchivo;
import proyectoInversiones.Periodo;
import proyectoInversiones.repositorio.RepositorioGeneral;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class CuentasController{
	
    static DescargaDrive lectorDrive = new DescargaDrive();

	public static ModelAndView listar(Request req, Response res) {
	    Map<String, List<Empresa>> model = new HashMap<>();
	    
		
		try {
			lectorDrive.obtenerEmpresas();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ArrayList<Empresa> listaEmpresas = new ArrayList<Empresa>();
		
	  	listaEmpresas = lectorDrive.getTodasLasEmpresas();
	  	
	    model.put("empresasAMostrar", listaEmpresas);
		return new ModelAndView(model, "Cuentas2.html");

	}
	
	
public static ModelAndView setearEmpresa(Request req, Response res) {
		
		String empresa = req.queryParams("Empresa");

		
		try{
			Map<String, List<Cuenta>> model = new HashMap<>();
						
			List<Periodo>periodosEmpresa =  lectorDrive.getPeriodos(new Empresa(empresa));
			List<Cuenta> cuentasDeEmpresa = setearListaCuentas(periodosEmpresa);
			
			Cuenta cuentaDeEmpresa = cuentasDeEmpresa.get(0);
		
			List<Cuenta> cuentaUnica = new ArrayList<Cuenta>();
			cuentaDeEmpresa.setEmpresaAsoc(empresa);
			cuentaUnica.add(cuentaDeEmpresa);
			model.put("cuentaUnica",cuentaUnica);		
			model.put("cuentas", cuentasDeEmpresa);
			return new ModelAndView(model, "Cuentas2.html");
			

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
