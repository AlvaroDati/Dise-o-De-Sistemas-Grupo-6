package proyectoInversiones.metodologias;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Year;
import java.util.stream.IntStream;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import proyectoInversiones.Empresa;
import proyectoInversiones.Indicador;
import proyectoInversiones.usuarios.Usuario;
  
@SuppressWarnings("serial")
@Entity
@Table(name = "operandos_condicion")
public class OperandoCondicion implements Serializable{
		
		@Id 
		@GeneratedValue
		private Long id;	
		@Enumerated
		private OperacionAgregacion operacionagregacion;
	//	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
		@Transient
		private Cuantificador indicadoroantiguedad;
		private int aniosaevaluar;
		
		public OperandoCondicion() {} //Necesario para persistir la clase
		
		public OperandoCondicion(OperacionAgregacion operacionAgregacion, Cuantificador indicadorOAntiguedad, int aniosAEvaluar) {
			this.operacionagregacion = operacionAgregacion;
			this.indicadoroantiguedad = indicadorOAntiguedad;
			this.aniosaevaluar = aniosAEvaluar - 1; //Si aniosAEvaluar es 1, el intervalo tiene que ser (X,X), no (X-1,X)
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
			IntStream periodoAEvaluar = IntStream.rangeClosed(anioActual - aniosaevaluar, anioActual);
			IntStream indicesEnPeriodo = periodoAEvaluar.map(anio -> indicadoroantiguedad.evaluarEn(empresa, Year.of(anio)));
			return operacionagregacion.aplicarA(indicesEnPeriodo);
		}
	}

