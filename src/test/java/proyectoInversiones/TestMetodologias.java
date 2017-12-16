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
	private ArrayList<Empresa> empresas = new ArrayList<Empresa>();
	
	@Before
	public void setUp() {
		empresas = archivo.leerArchivo();
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
	public void gulloCompanyNOCumpleCondTaxativaINDICADORX() throws IOException{
		IndVisitor indVisitorTest = new IndVisitor();
		Empresa gulloCompany = empresas.get(0);
		gulloCompany.setNombre("gulloCompany");
		Indicador unIndicador = indVisitorTest.obtenerResultadoIndicadorSegunEmpresa("asdf2=DEUDA+EBITDA", gulloCompany, 2016);
		System.out.println(unIndicador.getValorIndicador());
			
	}

}
