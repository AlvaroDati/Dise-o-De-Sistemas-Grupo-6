package proyectoInversiones.metodologias;

import java.time.Year;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import proyectoInversiones.Empresa;
import proyectoInversiones.usuarios.Usuario;

//@Entity
public class Antiguedad extends Cuantificador {
	
	public Antiguedad(){}

	@Override
	public int evaluarEn(Empresa empresa, Year anio){
		int antiguedad = Integer.parseInt(anio.toString()) - empresa.getInicioActividad();
			return antiguedad;
		
	}

}