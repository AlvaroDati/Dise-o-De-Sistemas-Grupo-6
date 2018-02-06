package proyectoInversiones.metodologias;

import java.time.Year;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

import proyectoInversiones.Empresa;

@Entity
public class Antiguedad extends Cuantificador {
	
	

	public Antiguedad(){}
	
	@Override
	public int evaluarEn(Empresa empresa, Year anio){
		int antiguedad = Integer.parseInt(anio.toString()) - empresa.getInicioActividad();
			return antiguedad;
		
	}

}