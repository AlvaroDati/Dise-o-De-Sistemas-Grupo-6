package proyectoInversiones.metodologias;

import javax.persistence.Entity;
import javax.persistence.Table;

import proyectoInversiones.Empresa;
import proyectoInversiones.metodologias.Condicion;
import proyectoInversiones.metodologias.OperacionRelacional;
import proyectoInversiones.metodologias.OperandoCondicion;

@Entity
@Table(name = "condiciones_prioritarias")
public class CondicionPrioritaria extends Condicion{
	
	public CondicionPrioritaria() {} //Necesario para persistir la clase
	
	public CondicionPrioritaria(OperandoCondicion operando, OperacionRelacional operacionRelacional) {
		this.operando = operando;
		this.operacionRelacional = operacionRelacional;
	}
	
	public boolean esMejorQue(Empresa empresa1, Empresa empresa2){
		return operacionRelacional.aplicarA(operando.valorPara(empresa1), operando.valorPara(empresa2));
	}
	
}
