package proyectoInversiones.metodologias;

import java.time.Year;

import javax.persistence.Entity;

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