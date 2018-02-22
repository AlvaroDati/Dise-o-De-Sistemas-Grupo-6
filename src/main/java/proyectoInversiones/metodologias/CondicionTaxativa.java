package proyectoInversiones.metodologias;

import javax.persistence.Entity;
import javax.persistence.Table;

import proyectoInversiones.Empresa;

@Entity
@Table(name = "condiciones_taxativas")
public class CondicionTaxativa extends Condicion{
	
	private int valor;
	
	public CondicionTaxativa() {} //Necesario para persistir la clase
	
	public CondicionTaxativa(OperandoCondicion operando, OperacionRelacional operacionRelacional, int valor) {
		this.operando = operando;
		this.operacionrelacional = operacionRelacional;
		this.valor = valor;
	}
	
	public boolean laCumple(Empresa empresa){
		return operacionrelacional.aplicarA(operando.valorPara(empresa), valor);
	}

}

