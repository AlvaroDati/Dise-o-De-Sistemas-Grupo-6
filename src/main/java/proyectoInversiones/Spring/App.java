package proyectoInversiones.Spring;

import org.springframework.boot.Banner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;

import proyectoInversiones.servlet.DataHandler;

@SpringBootApplication
@ServletComponentScan
public class App {
    public static void main(String[] args) throws URISyntaxException, IOException {

        URL empresasURL = App.class.getClassLoader().getResource("empresas.json");
        URL usersURL = App.class.getClassLoader().getResource("usuarios.properties");

        DataHandler initializer = new DataHandler();
        initializer.initEmpresas(empresasURL);

        Properties usuariosProps = new Properties();
        usuariosProps.load(new FileInputStream(new File(usersURL.toURI())));
        LoginRegistry.getInstance().setUsuarios(usuariosProps);

        // start application after config
        SpringApplication.run(App.class, args);
    }
}