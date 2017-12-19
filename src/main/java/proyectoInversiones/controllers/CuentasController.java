package proyectoInversiones.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import proyectoInversiones.Cuenta;
import proyectoInversiones.Empresa;
import proyectoInversiones.Indicador;
import proyectoInversiones.NuevoLeerArchivo;
import proyectoInversiones.Periodo;
import proyectoInversiones.repos.RepoCuentas;
import proyectoInversiones.usuarios.LeerUsuarios;
import proyectoInversiones.usuarios.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class CuentasController implements WithGlobalEntityManager, TransactionalOps {
	
	public static ModelAndView listar(Request req, Response res) {
	    Map<String, List<Cuenta>> model = new HashMap<>();
		return new ModelAndView(model, "Cuentas2.html");
	}
	
		
	public static ModelAndView setearEmpresa(Request req, Response res) {
		
		String empresa = req.queryParams("Empresa");
		System.out.println(empresa);
		
		try{
			Map<String, List<Cuenta>> model = new HashMap<>();
			NuevoLeerArchivo arch = new NuevoLeerArchivo();
			List<Periodo>periodosEmpresa =  arch.getPeriodos(new Empresa(empresa));
			List<Cuenta> cuentasDeEmpresa = setearListaCuentas(periodosEmpresa);
			
//			Cuenta cuentaDeEmpresa = cuentasDeEmpresa.get(0);
//			System.out.printf("\n");
//			System.out.println(cuentaDeEmpresa.getEmpresaAsoc());
//			List<Cuenta> cuentaUnica = new ArrayList<Cuenta>();
//			cuentaUnica.add(cuentaDeEmpresa);
//			model.put("cuentaUnica",cuentaUnica);	--> faltaría modificar el html		
			model.put("cuentas", cuentasDeEmpresa);
			return new ModelAndView(model, "Cuentas2.html");
			
		}catch ( Exception e ){
			res.cookie("mensajeError", e.getMessage());
			res.redirect("/");
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
