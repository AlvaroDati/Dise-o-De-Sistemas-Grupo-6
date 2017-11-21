package proyectoInversiones.controllers;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class LoginController {
	public ModelAndView login(Request req, Response res) {
		String mensajeError = req.cookie("mensajeError");
		if (mensajeError == null) return new ModelAndView(null, "WEB-INF/static/Index.html");
		Map<String, String> model = new HashMap<>();
		model.put("mensajeError", mensajeError);
		res.removeCookie("mensajeError");
		return new ModelAndView(model, "login/loginError.hbs");
	}
	
	
	public Void validate(Request req, Response res) {
		String email = req.queryParams("email");
		String password = req.queryParams("password");
		try{
//			Long idUsuario = new RepositorioUsuarios().obtenerId(email, password);
			res.cookie("email", email);
			res.cookie("idUsuario", Long.toString(35));
			res.redirect("/home");
		} catch ( Exception e ){
			res.cookie("mensajeError", e.getMessage());
			res.redirect("/");
		}
		return null;
	}
	
}
