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
	
	@SuppressWarnings("deprecation")
	@Test
	public void indicadorDefinidoPorUsuario() throws IOException{
		IndVisitor indicadorVisitor = new IndVisitor();
		Empresa unaEmpresa = new Empresa("America Movil");
		List<Indicador> usuario = indicadorVisitor.obtenerResultadosIndicadoresUsuarioSegunEmpresa("IndicadoresDelUsuarioelias",unaEmpresa,2007);
		Indicador nuevoIndicador = new Indicador();
		String nombreIndicador = "Testetetete";
		Indicador otroIndicador = new Indicador();
		
		nuevoIndicador.setNombre(nombreIndicador);
		nuevoIndicador.setValorIndicador(44603);
		nuevoIndicador.setEmpresa(unaEmpresa);
		nuevoIndicador.setPeriodo(2006);
		
		
		for(int i = 0;i<usuario.size();i++){
			if(usuario.get(i).getNombre().equals(nuevoIndicador.getNombre())){
				otroIndicador.setEmpresa(usuario.get(i).getEmpresa());
				otroIndicador.setNombre(usuario.get(i).getNombre());
				otroIndicador.setValorIndicador(usuario.get(i).getValorIndicador());
				otroIndicador.setPeriodo(usuario.get(i).getPeriodo());				
			}
		}
		
		assertEquals(nuevoIndicador.getEmpresa(),otroIndicador.getEmpresa());
		assertEquals(nuevoIndicador.getNombre(),otroIndicador.getNombre());
		assertEquals((int)nuevoIndicador.getValorIndicador(),(int)otroIndicador.getValorIndicador());
		assertEquals(nuevoIndicador.getPeriodo(),otroIndicador.getPeriodo());
		
}
	
//	@Test
//	public void indicador_de_usuario_valido() {
//		
//		
//	}
}
