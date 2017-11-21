package proyectoInversiones.server;

import proyectoInversiones.spark.BooleanHelper;
import proyectoInversiones.spark.HandlebarsTemplateEngineBuilder;
import spark.ModelAndView;
import spark.Spark;
import spark.TemplateViewRoute;
import spark.debug.DebugScreen;
import spark.template.handlebars.HandlebarsTemplateEngine;


public class Server {

	public static void main(String[] args) {
		//new Bootstrap().init();
		Spark.port(8080);
		Spark.get("/hello", (req, res) -> "Hello World");
		DebugScreen.enableDebugScreen();
		
		Router.configure();
	}
	
}
