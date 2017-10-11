package proyectoInversiones;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;

import proyectoInversiones.NuevoLeerArchivo;

public class Factory {

    public Set<Empresa> createEmpresasFromConfig(String filename) throws IOException {
        Set<Empresa> result = new HashSet<>();
        NuevoLeerArchivo archivo = new NuevoLeerArchivo();
        
        result = (Set<Empresa>) archivo.leerArchivo();
        return result;
    }
	
}
