package proyectoInversiones;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import proyectoInversiones.Empresa;
import proyectoInversiones.IndicadorAuxiliar;
import proyectoInversiones.metodologias.Antiguedad;
import proyectoInversiones.metodologias.CondicionPrioritaria;
import proyectoInversiones.metodologias.CondicionTaxativa;
import proyectoInversiones.metodologias.OperacionAgregacion;
import proyectoInversiones.metodologias.OperacionRelacional;
import proyectoInversiones.metodologias.OperandoCondicion;

public class TestMetodologias {
//	private NuevoLeerArchivo archivo = new NuevoLeerArchivo();
//	private ArrayList<Empresa> empresasDelJson = new ArrayList<Empresa>();
//	
//	@Before
//	public void setUp() {
//		empresasDelJson = archivo.leerArchivo();
//	}
//
//	@Test
//	public void gulloCompanyEsMasAntiguaQueIvanCompany() {
//		Empresa ivanCompany = new Empresa("IvanCompany");
//		Empresa gulloCompany = new Empresa("GulloCompany");
//		ivanCompany.setInicioActividad(2000);
//		gulloCompany.setInicioActividad(1980);
//		OperandoCondicion opCond = new OperandoCondicion(OperacionAgregacion.Ultimo, new Antiguedad(), 1);
//		CondicionPrioritaria condPrioritaria = new CondicionPrioritaria(opCond, OperacionRelacional.Mayor);
//		assertTrue(condPrioritaria.esMejorQue(gulloCompany, ivanCompany));
//	}
//	
//	
//	@Test
//	public void gulloCompanyNOCumpleCondTaxativaIndicadorLocoMenorA500() throws IOException{
//		Empresa gulloCompany = empresasDelJson.get(0);
//		gulloCompany.setNombre("gulloCompany");
//		IndicadorAuxiliar indicadorLoco = new IndicadorAuxiliar();
//	    indicadorLoco.setExpresion("hola=DEUDA+500");
//		indicadorLoco.setNombre("indicadorLoco");
//		OperandoCondicion opCond = new OperandoCondicion(OperacionAgregacion.Ultimo, indicadorLoco, 1);
//		CondicionTaxativa condicion = new CondicionTaxativa(opCond,OperacionRelacional.Menor,500);
//		assertTrue(condicion.laCumple(gulloCompany));
//			
//	}
//	
//	
//	@Test
//	public void soloUnaEmpresasCumpleIndicadorLocoMayorA10000EnUltimos2Anios(){
//		Metodologia metodologia = new Metodologia("Una Metodologia");
//		ArrayList<Empresa> empresasParaComparacionConMetodologias = new ArrayList<Empresa>();
//		empresasParaComparacionConMetodologias.add(empresasDelJson.get(0));
//		empresasParaComparacionConMetodologias.add(empresasDelJson.get(1));
//		IndicadorAuxiliar indicadorLoco = new IndicadorAuxiliar();
//		indicadorLoco.setExpresion("hola=DEUDA+500");
//		indicadorLoco.setNombre("indicadorLoco");
//		CondicionTaxativa cond = new CondicionTaxativa(new OperandoCondicion(OperacionAgregacion.Promedio,indicadorLoco,2), OperacionRelacional.Mayor, 10000);
//		metodologia.agregarCondicionTaxativa(cond);
//		assertEquals(0,metodologia.evaluarPara(empresasParaComparacionConMetodologias).size());
//	}
//
}
