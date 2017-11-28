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
import proyectoInversiones.usuarios.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class CuentasController implements WithGlobalEntityManager, TransactionalOps {
	
	public static ModelAndView listar(Request req, Response res) {
		Map<String, List<Cuenta>> model = new HashMap<>();
		NuevoLeerArchivo arch = new NuevoLeerArchivo();
		List<Periodo>cuentasAux =  arch.getPeriodos(new Empresa("General Electric"));
		
		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		
		for (int i=0;i<cuentasAux.size();i++){
			cuentas.add(cuentasAux.get(i).getCuentas());
			cuentas.get(i).setPeriodoVinculado(cuentasAux.get(i));
		}
		
	
		model.put("cuentas", cuentas);
		
		return new ModelAndView(model, "Cuentas2.html");
	}
	
}
