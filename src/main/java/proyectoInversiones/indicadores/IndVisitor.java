package proyectoInversiones.indicadores;

import proyectoInversiones.antlr4.*;
import proyectoInversiones.Empresa;



import java.util.HashMap; 
import java.util.Map;



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
		int i = 0;
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
		int i = 0;
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
	
		String indicador;
		//IndicadorPredefinido indicadorPredefinido = new IndicadorPredefinido();
		
		indicador = ctx.OTROINDICADOR().getText().toLowerCase();
		
		IndicadorPredefinido indPredefinido = new IndicadorPredefinido();
		
		
		switch(indicador){
			//IndicadorPredefinido indPredefinido = new IndicadorPredefinido();
		
		case "ingresoneto":
			return (double) indPredefinido.calcularIngNeto("AmericaMovil", 2006);
			
		case "roa":
			return (double) indPredefinido.calcularRoe("AmericaMovil", 2006);
			
		default:
			//Tirar excepcion
			break;
		}
		return null;
		
	}
	
	public static void main (String args[]){
		IndVisitor ind = new IndVisitor();
		
	}

}
	/*
	si recibimos una cadena OTROINDICADOR suceda
	calculoNecesario = ctx.OTROINDICADOR.getText();
	indicadorPredefinido indi = new IndicadorPredefinido();
	indi.
	 * */