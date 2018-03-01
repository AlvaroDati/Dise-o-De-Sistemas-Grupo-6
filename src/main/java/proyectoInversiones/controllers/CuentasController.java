package proyectoInversiones.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import proyectoInversiones.Cuenta;
import proyectoInversiones.Empresa;
import proyectoInversiones.NuevoLeerArchivo;
import proyectoInversiones.Periodo;
import proyectoInversiones.DescargaDrive;
import proyectoInversiones.repositorio.RepositorioGeneral;
import proyectoInversiones.repositorio.RepositorioServicio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class CuentasController{
	
    static DescargaDrive lectorDrive = new DescargaDrive();
    static List<Empresa> empresasEnLaDB = new ArrayList<Empresa>();

	public static ModelAndView listar(Request req, Response res) {
		RepositorioGeneral repoGeneral = new RepositorioGeneral();
		RepositorioServicio repo= RepositorioServicio.getInstance();
		empresasEnLaDB = repo.obtenerTodasLasEmpresas();
		repoGeneral.setEmpresas(empresasEnLaDB);
		return new ModelAndView(repoGeneral, "Cuentas2.html");
	}
	
	
public static ModelAndView setearEmpresa(Request req, Response res) {
		String empresa = req.queryParams("Empresa");

		
		try{
			RepositorioGeneral repoGeneral = new RepositorioGeneral();		
			List<Periodo>periodosEmpresa =  new NuevoLeerArchivo().getPeriodos(new Empresa(empresa));
			repoGeneral.setEmpresas(empresasEnLaDB);
			for(int i = 0;i<repoGeneral.getEmpresas().size();i++){
				System.out.println("Empresas a mostrar en /cuentas: " + repoGeneral.getEmpresas().get(i).getNombre());
			}
			repoGeneral.setCuentas(setearListaCuentas(periodosEmpresa));
			repoGeneral.setEmpresaAsociada(empresa);
			
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
