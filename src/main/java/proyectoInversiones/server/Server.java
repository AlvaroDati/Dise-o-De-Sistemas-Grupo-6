package proyectoInversiones.server;

import java.util.Timer;
import java.util.TimerTask;

import proyectoInversiones.CargaBatchCuentas;
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
	
		long cincoMin = 2*60*1000; // El timer funca en milisgundos,por eso la cuentita

		TimerTask cargarCuentas = new CargaBatchCuentas();
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(cargarCuentas, 0, cincoMin);
				
		Spark.port(8080);
		DebugScreen.enableDebugScreen();
		Router.configure(); 
	}
}