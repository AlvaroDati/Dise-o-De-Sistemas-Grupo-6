package proyectoInversiones.indicadores;
import java.util.HashMap;
import java.util.Map;


public class IndVisitor extends indicadoresBaseVisitor<Double> {
	
	Map<String,Double> memory = new HashMap<String,Double>();
	
	@Override
	public Double visitSentencia(indicadoresParser.SentenciaContext ctx){
		
		
		String otroIndicador = ctx.OTROINDICADOR().getText();
		Double valor = visit(ctx.expresion());
		memory.put(otroIndicador, valor);
		return valor;
	}
	
	@Override
	public Double visitExpresion(indicadoresParser.ExpresionContext ctx){
		
		Double left = visit(ctx.expresionMultiple(0)); //Agarra el valor de la izquierda de la subepresion
		Double right = visit(ctx.expresionMultiple(1)); //Agarra el valor de la derecha de la subepresion
		
		if(ctx.e.getText() == "+") return left + right;
		return left - right;
			
	}
	@Override
	public Double visitExpresionMultiple(indicadoresParser.ExpresionMultipleContext ctx) {
		Double left = visit(ctx.operando(0)); //Agarra el valor de la izquierda de la subepresion
		Double right = visit(ctx.operando(1)); //Agarra el valor de la derecha de la subepresion
		
		if (ctx.e.getText() == "*")	return left * right;
		return left / right; 
	}
	
	@Override
	public Double visitOperando(indicadoresParser.OperandoContext ctx){
		return visit(ctx.expresion);
	}
	
}
