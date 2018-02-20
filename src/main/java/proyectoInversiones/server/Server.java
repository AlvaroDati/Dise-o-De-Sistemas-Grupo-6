package proyectoInversiones.server;

import java.util.Timer;
import java.util.TimerTask;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import proyectoInversiones.CargaBatchCuentas;
import proyectoInversiones.repositorio.Repositorio;
import spark.Spark;
import spark.debug.DebugScreen;


public class Server {
	
	public static void main(String[] args) {
		 
		 long cincoMin = 5*60*1000; // El timer funca en milisgundos,por eso la cuentita
		 TimerTask cargarCuentas = new CargaBatchCuentas();
		 Timer timer = new Timer(true);
		 timer.scheduleAtFixedRate(cargarCuentas, 0, cincoMin);
		Spark.port(getHerokuAssignedPort());
		DebugScreen.enableDebugScreen();
		
		Router.configure(); 
		//Router.closeDataBase();
	}
	
	   static int getHerokuAssignedPort() {
	        ProcessBuilder processBuilder = new ProcessBuilder();
	        if (processBuilder.environment().get("PORT") != null) {
	            return Integer.parseInt(processBuilder.environment().get("PORT"));
	        }
	        return 8080;
	    }
	
}