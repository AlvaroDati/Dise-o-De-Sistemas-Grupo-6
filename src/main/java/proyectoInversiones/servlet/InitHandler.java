package proyectoInversiones.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.net.MalformedURLException;
import java.net.URL;


public class InitHandler {

	
	private DataHandler dataHandler = new DataHandler();

    public void contextInitialized(ServletContextEvent servletContextEvent) {

        // init default data
        initEmpresas(servletContextEvent);
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    private void initEmpresas(ServletContextEvent servletContextEvent) {
        try {
            URL configURL = null;
            configURL = servletContextEvent.getServletContext().getResource("/WEB-INF/resources/empresas.json");
            this.dataHandler.initEmpresas(configURL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

  
	
	
}
