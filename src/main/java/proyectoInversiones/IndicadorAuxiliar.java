package proyectoInversiones;

import java.io.IOException;
import java.time.Year;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import proyectoInversiones.Empresa;
import proyectoInversiones.Indicador;
import proyectoInversiones.indicadores.IndVisitor;
import proyectoInversiones.metodologias.*;


//@Entity
public class IndicadorAuxiliar extends Cuantificador{
	static int valo;
	private float valor;
	public float getValor() {
		return valor;
	}

	public void setValor(float f) {
		this.valor = f;
	}

	private String expresion;
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	private String nombre;
	public String getExpresion() {
		return expresion;
	}

	public void setExpresion(String expresion) {
		this.expresion = expresion;
	}

	@Override
	public int evaluarEn(Empresa empresa, Year anio){
		IndVisitor unIndVisitor = new IndVisitor();
		int anioParseado = anio.getValue();
		Indicador unIndicadorAux = new Indicador();
		
		try {
			unIndicadorAux = unIndVisitor.obtenerResultadoIndicadorSegunEmpresa(this.getExpresion(), empresa, anioParseado);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return (int) unIndicadorAux.getValorIndicador();
	}
}
