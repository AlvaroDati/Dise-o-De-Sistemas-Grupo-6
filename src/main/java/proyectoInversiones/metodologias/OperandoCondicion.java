package proyectoInversiones.metodologias;

import java.time.LocalDate;
import java.time.Year;
import java.util.stream.IntStream;

import proyectoInversiones.Empresa;
import proyectoInversiones.indicadores.IndVisitor;
  
	
	public class OperandoCondicion {
		
		private OperacionAgregacion operacionAgregacion;
		private Cuantificador indicadorOAntiguedad;
		private int aniosAEvaluar;
		
		private OperandoCondicion() {} //Necesario para persistir la clase
		
		public OperandoCondicion(OperacionAgregacion operacionAgregacion, Cuantificador indicadorOAntiguedad, int aniosAEvaluar) {
			this.operacionAgregacion = operacionAgregacion;
			this.indicadorOAntiguedad = indicadorOAntiguedad;
			this.aniosAEvaluar = aniosAEvaluar - 1; //Si aniosAEvaluar es 1, el intervalo tiene que ser (X,X), no (X-1,X)
			if (aniosAEvaluar < 0){
				throw new RuntimeException("La condiciÃ³n se tiene que evaluar al menos en un aÃ±o");
			}
		}

		public boolean sePuedeEvaluarPara(Empresa empresa){
			try{
				this.valorPara(empresa);
				return true;
			} catch (RuntimeException e) {
				return false;
			}
		}
		
		public int valorPara(Empresa empresa){
			int anioActual = LocalDate.now().getYear();
			IntStream periodoAEvaluar = IntStream.rangeClosed(anioActual - aniosAEvaluar, anioActual);
			IntStream indicesEnPeriodo = periodoAEvaluar.map(anio -> indicadorOAntiguedad.evaluarEn(empresa, Year.of(anio)));
			return operacionAgregacion.aplicarA(indicesEnPeriodo);
		}
	}

