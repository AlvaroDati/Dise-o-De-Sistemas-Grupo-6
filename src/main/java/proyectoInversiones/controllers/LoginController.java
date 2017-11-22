package proyectoInversiones.controllers;

import java.util.HashMap;
import java.util.Map;

import proyectoInversiones.server.Router;
import proyectoInversiones.usuarios.LeerUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.debug.DebugScreen;


public class LoginController {
	
	public ModelAndView login(Request req, Response res) {
		/*String mensajeError = req.cookie("mensajeError");
		if (mensajeError == null) return new ModelAndView(null, "Index.html");
		Map<String, String> model = new HashMap<>();
		model.put("mensajeError", mensajeError);
		res.removeCookie("mensajeError");
		return new ModelAndView(model, "errorLogin.html");*/
		
		return new ModelAndView(null, "Index.html");
	}
	
	
	public Void validate(Request req, Response res) {
		String userTag = req.queryParams("userTag");
		String password = req.queryParams("password");
		try{
			LeerUsuarios archivoUsuarios = new LeerUsuarios();
			Long idUsuario = archivoUsuarios.obtenerId(userTag,password);
			res.cookie("userTag", userTag);
			res.cookie("idUsuario", Long.toString(idUsuario));
			res.redirect("/cuentas");
		} catch ( Exception e ){
			res.cookie("mensajeError", e.getMessage());
			res.redirect("/");
		}
		return null;
	}		
}
