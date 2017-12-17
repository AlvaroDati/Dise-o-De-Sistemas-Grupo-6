package proyectoInversiones.repositorio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import proyectoInversiones.Indicador;
import proyectoInversiones.indicadores.IndVisitor;

public class RepositorioIndicadores {
	IndVisitor indVisitor;
	
	
	public List<Indicador> crearRepositorio(String archivo) throws IOException{
		List<Indicador> indicador = new ArrayList<Indicador>();
		if(indVisitor == null){
			indVisitor = new IndVisitor();
			indicador = indVisitor.obtenerIndicadores(archivo);
		}
		return indicador;
	}
	
}
