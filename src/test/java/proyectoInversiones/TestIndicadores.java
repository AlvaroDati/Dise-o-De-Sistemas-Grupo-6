package proyectoInversiones;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import proyectoInversiones.indicadores.ArmadorIndicador;
import proyectoInversiones.indicadores.IndVisitor;


public class TestIndicadores {

	NuevoLeerArchivo archivo = new NuevoLeerArchivo();
	
	
	@SuppressWarnings("deprecation")
	@Test
	public void ingresoNeto() {
		Empresa unaEmpresa = new Empresa("America Movil");
		Indicador indicador = new Indicador();
		indicador.setNombre("Ingreso Neto");
		indicador.setEmpresa(unaEmpresa);
		indicador.setPeriodo(2006);
		ArmadorIndicador indicadorArmar= new ArmadorIndicador(indicador);
		
		indicador.setValorIndicador(indicadorArmar.obtenerValorIndicador(indicador));
		assertEquals((int)3272.0,(int)indicador.getValorIndicador());
	}
	
	@Test
	public void indicadorDefinidoPorUsuario() throws IOException{
		IndVisitor indicadorVisitor = new IndVisitor();
		Empresa unaEmpresa = new Empresa("America Movil");
		List<Indicador> usuario = indicadorVisitor.obtenerIndicadoresUsuario2("output.txt");
		
		
	}
	
//	@Test
//	public void indicador_de_usuario_valido() {
//		
//		
//	}
}
