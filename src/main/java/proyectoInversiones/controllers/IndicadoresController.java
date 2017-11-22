package proyectoInversiones.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import proyectoInversiones.Indicador;
//import proyectoInversiones.Empresa;
//import proyectoInversiones.repos.RepoCuentas;
/*import dominio.usuarios.RepositorioUsuarios;
import dominio.usuarios.Usuario;*/

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class IndicadoresController implements WithGlobalEntityManager, TransactionalOps{
	public ModelAndView listar(Request req, Response res) {
		Map<String, List<Indicador>> model = new HashMap<>();
//		Usuario usuarioActivo = new RepositorioUsuarios().obtenerPorId(Long.parseLong(req.cookie("idUsuario")));
//		List<Indicador> indicadores = usuarioActivo.getIndicadores(); //getIndicadores lo tiene Empresa en nuestro TP, hay que ver bien como adaptarlo
//		model.put("indicadores", indicadores);
		return new ModelAndView(model, "Indicadores.html");
	}
}

