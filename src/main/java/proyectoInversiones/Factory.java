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
	
	/**
     * Creates "BancoDTO" entries for given file in JSON format
     *
     * @param filename
     * @return
     * @throws IOException
     */
    public List<Empresa> createEmpresasFromConfig(String filename) throws IOException {
        List<Empresa> result = new ArrayList<>();
        NuevoLeerArchivo archivo = new NuevoLeerArchivo();
        
        result = archivo.leerArchivo();
        return result;
    }
	
	
}
