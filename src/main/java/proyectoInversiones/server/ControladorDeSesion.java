package proyectoInversiones.server;

import spark.Request;
import spark.Response;


public class ControladorDeSesion {
	
		public static void validarLoggeo(Request req, Response res){
			if(req.session().isNew()){
				res.cookie("userTag","");
				res.cookie("idUsuario","");
				res.redirect("/");
			}
		}

}


