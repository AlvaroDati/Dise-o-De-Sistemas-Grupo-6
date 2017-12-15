package proyectoInversiones;

import static org.junit.Assert.*;

import org.junit.Test;

import proyectoInversiones.indicadores.ArmadorIndicador;
import proyectoInversiones.metodologias.*;


public class TestMetodologias {

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
	public void gulloCompanyNOCumpleCondTaxativaINDICADORMenorA(){
		
		Empresa gulloCompany = new Empresa("GulloCompany");
		
		Indicador indicador = new Indicador();
		ArmadorIndicador armador = new ArmadorIndicador();
		
		
		assertFalse(false);
	}

}
