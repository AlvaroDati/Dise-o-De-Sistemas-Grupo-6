package proyectoInversiones;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import proyectoInversiones.indicadores.*;
import proyectoInversiones.Empresa;
import proyectoInversiones.Periodo;
import proyectoInversiones.Indicador;
import proyectoInversiones.metodologias.*;
import proyectoInversiones.repositorio.EmpresasRepo;


public class TestMetodologias {
	private NuevoLeerArchivo archivo = new NuevoLeerArchivo();
	private ArrayList<Empresa> empresasDelJson = new ArrayList<Empresa>();
	
	@Before
	public void setUp() {
		empresasDelJson = archivo.leerArchivo();
	}

	@Test
	public void gulloCompanyEsMasAntiguaQueIvanCompany() {
		Empresa ivanCompany = new Empresa("IvanCompany");
		Empresa gulloCompany = new Empresa("GulloCompany");

		ivanCompany.setInicioActividad(2000);
		gulloCompany.setInicioActividad(1980);
		
		
		OperandoCondicion opCond = new OperandoCondicion(OperacionAgregacion.Ultimo, new Antiguedad(), 1);
		CondicionPrioritaria condPrioritaria = new CondicionPrioritaria(opCond, OperacionRelacional.Mayor);
		assertTrue(condPrioritaria.esMejorQue(gulloCompany, ivanCompany));
	}
	
	
	@Test
	public void gulloCompanyNOCumpleCondTaxativaIndicadorLocoMenorA500() throws IOException{
		Empresa gulloCompany = empresasDelJson.get(0);
		gulloCompany.setNombre("gulloCompany");
		Indicador indicadorLoco = new Indicador();
	    indicadorLoco.setExpresion("hola=DEUDA+500");
		indicadorLoco.setNombre("indicadorLoco");
		OperandoCondicion opCond = new OperandoCondicion(OperacionAgregacion.Ultimo, indicadorLoco, 1);
		CondicionTaxativa condicion = new CondicionTaxativa(opCond,OperacionRelacional.Menor,500);
		assertFalse(condicion.laCumple(gulloCompany));
			
	}
	
	
	@Test
	public void soloUnaEmpresasCumpleIndicadorLocoMayorA10000EnUltimos2Anios(){
		Metodologia metodologia = new Metodologia("Una Metodologia");
		ArrayList<Empresa> empresasParaComparacionConMetodologias = new ArrayList<Empresa>();
		empresasParaComparacionConMetodologias.add(empresasDelJson.get(0));
		empresasParaComparacionConMetodologias.add(empresasDelJson.get(1));
		Indicador indicadorLoco = new Indicador();
		indicadorLoco.setExpresion("hola=DEUDA+500");
		indicadorLoco.setNombre("indicadorLoco");
		CondicionTaxativa cond = new CondicionTaxativa(new OperandoCondicion(OperacionAgregacion.Promedio,indicadorLoco,2), OperacionRelacional.Mayor, 10000);
		metodologia.agregarCondicionTaxativa(cond);
		assertEquals(1,metodologia.evaluarPara(empresasParaComparacionConMetodologias).size());
	}

}
