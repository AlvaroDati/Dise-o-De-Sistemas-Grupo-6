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

    @SuppressWarnings("unchecked")
	public Set<Empresa> createEmpresasFromConfig(String filename) throws IOException {
        ArrayList<Empresa> result = new ArrayList<>();
        
        
        NuevoLeerArchivo archivo = new NuevoLeerArchivo();
//        result = (Set<Empresa>) archivo.leerArchivo();

//        List<Map> map = getItemsFromFile("empresas2.txt");

//        for (Map empresaMap : map) {
//            Empresa empresa = new Empresa();
//            empresa.setNombre((String)empresaMap.get("nombre"));
//            empresa.setInicioActividad((int) empresaMap.get("inicioActividad"));
//            Set periodos = new HashSet<>();
//            periodos.addAll((Collection) empresaMap.get("periodos"));
//            empresa.setPeriodos((List<Periodo>) periodos);
//            result.add(empresa);
//        }
        
        result = archivo.leerArchivo();
        
        Set<Empresa> aux = new HashSet<>();
        aux.addAll(result);
        return aux;
    }
	
    
    
    private List<Map> getItemsFromFile(String filename) throws IOException {
        final int BUFFER_SIZE = 1024;
        Charset utf8 = Charset.forName("UTF-8");
        byte[] buffer = new byte[BUFFER_SIZE];
        int count;
        StringBuilder str = new StringBuilder();
        InputStream fis = null;

        try {
            fis = new FileInputStream(filename);
            count = fis.read(buffer);
            while (count > 0) {
                str.append(new String(buffer,utf8));
                count = fis.read(buffer);
            }
        } finally {
            if (fis != null) {
                fis.close();
            }
        }

        String json = str.toString();
        JsonParser parser = JsonParserFactory.getJsonParser();
        return (List) parser.parseList(json);
    }
    
}
