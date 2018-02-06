package proyectoInversiones.metodologias;


import java.io.Serializable;
import java.time.Year;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import proyectoInversiones.Empresa;

@SuppressWarnings("serial")
@Entity
@Table(name = "cuantificadores")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Cuantificador implements Serializable{

	@Id 
	@GeneratedValue//(strategy=GenerationType.AUTO)
	private Long id;

	
	public abstract int evaluarEn(Empresa empresa, Year anio);
	
}
