package proyectoInversiones.metodologias;

import javax.persistence.Entity;
import javax.persistence.Table;

import proyectoInversiones.Empresa;

@Entity
@Table(name = "condiciones_taxativas")
public class CondicionTaxativa extends Condicion{
	
	private int valor;
	
	private CondicionTaxativa() {}
	
	public CondicionTaxativa(OperandoCondicion operando, OperacionRelacional operacionRelacional, int valor) {
		this.operando = operando;
		this.operacionRelacional = operacionRelacional;
		this.valor = valor;
	}
	
	public boolean laCumple(Empresa empresa){
		return operacionRelacional.aplicarA(operando.valorPara(empresa), valor);
	}

}

