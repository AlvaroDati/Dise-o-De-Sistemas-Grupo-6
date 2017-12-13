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
		return new ModelAndView(null, "Index.html");
	}
	
	
	public Void validate(Request req, Response res) {
		String userTag = req.queryParams("userTag");
		String password = req.queryParams("password"); 
		System.out.printf("userTag:%s \n", userTag);
		System.out.printf("password:%s \n", password);
		try{
			LeerUsuarios archivoUsuarios = new LeerUsuarios();
			Long idUsuario = archivoUsuarios.obtenerId(userTag,password);
			System.out.printf("id:%d \n", idUsuario);
			res.cookie("userTag", userTag);
			res.cookie("idUsuario", idUsuario.toString());
			res.redirect("/cuentas");
		} catch ( Exception e ){
			res.cookie("mensajeError", e.getMessage());
			res.redirect("/");
		}
		return null;
	}		
}
