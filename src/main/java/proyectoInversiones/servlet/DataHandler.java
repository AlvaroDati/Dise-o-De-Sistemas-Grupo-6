package proyectoInversiones.servlet;

import proyectoInversiones.NuevoLeerArchivo;

import proyectoInversiones.Cuenta;
import proyectoInversiones.Empresa;
import proyectoInversiones.Periodo;
import proyectoInversiones.Resources;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class DataHandler {

    public void initEmpresas(URL configURL) {
        Set<Empresa> empresas = null;
//        NuevoLeerArchivo archivo = new NuevoLeerArchivo();
        try {
            String filename = configURL.getFile();
//          empresas = (Set<Empresa>) archivo.leerArchivo();
            empresas = Resources.getInstance().getFactory().createEmpresasFromConfig(filename);
        } catch (Exception e) {
            e.printStackTrace();
            empresas = Collections.emptySet();
        }
        Resources.getInstance().crearRepoEmpresas(empresas); 
    }
 
}