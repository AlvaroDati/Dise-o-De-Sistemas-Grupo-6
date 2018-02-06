package proyectoInversiones.metodologias;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

@SuppressWarnings("serial")
@MappedSuperclass
public abstract class Condicion implements Serializable{
	
	@Id 
	@GeneratedValue
	private Long id;
	
	@OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	protected OperandoCondicion operando;
	@Enumerated
	protected OperacionRelacional operacionRelacional;
	
	public OperandoCondicion getOperando() {
		return operando;
	}
}
