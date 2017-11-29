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
		String empresa = req.queryParams("Empresa");
		
		Map<String, List<Cuenta>> model = new HashMap<>();
		NuevoLeerArchivo arch = new NuevoLeerArchivo();
		List<Periodo>cuentasAux =  arch.getPeriodos(new Empresa(empresa));
		List<Cuenta> cuentas = setearListaCuentas(cuentasAux);
		model.put("cuentas", cuentas);
		return new ModelAndView(null, "Cuentas2.html");
	}
	
	
	public Void setearEmpresa(Request req, Response res) {
		
		String empresa = req.queryParams("Empresa");
		
		try{
			Map<String, List<Cuenta>> model = new HashMap<>();
			NuevoLeerArchivo arch = new NuevoLeerArchivo();
			List<Periodo>cuentasAux =  arch.getPeriodos(new Empresa(empresa));
			List<Cuenta> cuentas = setearListaCuentas(cuentasAux);
			model.put("cuentas", cuentas);
			res.redirect("/cuentas");
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
		}
		
		return cuentas;
	}
	
}
