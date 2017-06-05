import java.util.HashMap;
import java.util.Map;

import proyectoInversiones.indicadores.IndicadorPredefinido;



public class IndVisitor extends indicadoresBaseVisitor<Double>{

	Map<String, Double> memory = new HashMap<String, Double>();
	
	@Override
	public Double visitAsignar(indicadoresParser.AsignarContext ctx) {
	String otroIndicador = ctx.OTROINDICADOR().getText(); // id is left-hand side of '='
	Double valor = visit(ctx.expresion()); // compute value of expression on right
	memory.put(otroIndicador, valor); // store it in our memory
	return valor;
	}
	
	@Override
	public Double visitExpr(indicadoresParser.ExprContext ctx){
		Double valor = visit(ctx.expresion());
		System.out.println(valor); //No es necesario este print, lo pongo a modo de "testear" por consola
		
		return 0.0; //No retorno nada, porque no sirve de mucho este simbolo noTerminal para java
	}
	
	@Override
	public Double visitSumRes(indicadoresParser.SumResContext ctx){
		Double resultado = 0.0;
		Double izquierda = visit(ctx.expresionMultiple(0));
		int i = 1;
		Double derecha;
		while(!ctx.isEmpty()){
			derecha = visit(ctx.expresionMultiple(i));
			i++;
			if(ctx.op.getType() == indicadoresParser.SUM) resultado +=  (izquierda + derecha);
			resultado +=  (izquierda - derecha);
		}
		return resultado;
	}
	
	@Override
	public Double visitMulDiv(indicadoresParser.MulDivContext ctx){
		
		Double resultado = 0.0;
		int i = 1;
		Double izquierda = visit(ctx.operando(0));
		Double derecha;
		while(!ctx.isEmpty()){
			derecha = visit(ctx.operando(i));
			i++;
			if(ctx.op.getType() == indicadoresParser.MUL) resultado += (izquierda * derecha);
			resultado += (izquierda/derecha);
		}
		return resultado;
	}
	
	@Override
	public Double visitParentesis(indicadoresParser.ParentesisContext ctx){
		return visit(ctx.expresion());
	}
	
	@Override
	public Double visitCuenta(indicadoresParser.CuentaContext ctx){
		return visit(ctx.CUENTA());
	}
	
	public Double visisOtroIndicador(indicadoresParser.OtroIndicadorContext ctx){
		/*
		IndicadorPredefinido indicador = new IndicadorPredefenido();
		indicador.indicadorSeleccionado(ctx.getText());
		 * Aca hay que ver que hacer, porque hay millones de indicadores y no podemos hacer un switch-case para cada
		 * indicador, para pensarlo..
		 */
		
		return null;
		
	}
	
	
	/*
	si recibimos una cadena OTROINDICADOR suceda
	calculoNecesario = ctx.OTROINDICADOR.getText();
	indicadorPredefinido indi = new IndicadorPredefinido();
	indi.
	 * */