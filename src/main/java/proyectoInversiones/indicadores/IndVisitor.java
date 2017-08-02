package proyectoInversiones.indicadores;

import proyectoInversiones.antlr4.*;
import proyectoInversiones.Empresa;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap; 
import java.util.Map;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;



public class IndVisitor extends indicadoresBaseVisitor<Integer>{
	/** "memory" for our calculator; variable/value pairs go here */
	Map<String, Integer> memory = new HashMap<String, Integer>();
	public Map<String, Integer> getMemory() {
		return memory;
	}
	public void setMemory(Map<String, Integer> memory) {
		this.memory = memory;
	}
	/** ID '=' expr NEWLINE */
	@Override
	public Integer visitAssign(indicadoresParser.AssignContext ctx) {
		String id = ctx.INDICADOR().getText(); // id is left-hand side of '='
		int value = visit(ctx.expr()); // compute value of expression on right
		memory.put(id, value); // store it in our memory
		return value;
	}
	/** expr NEWLINE */
	@Override
	public Integer visitPrintExpr(indicadoresParser.PrintExprContext ctx) {
		Integer value = visit(ctx.expr()); // evaluate the expr child
		System.out.println(value); // print the result
		return 0; // return dummy value
	}
	/** INT */
	@Override
	public Integer visitInt(indicadoresParser.IntContext ctx) {
		return Integer.valueOf(ctx.INT().getText());
	}
	/** ID */
	@Override
	public Integer visitId(indicadoresParser.IdContext ctx) {
		String id = ctx.INDICADOR().getText();
		//Indicadores indPredefinido = new Indicadores();
		
		/*int resultado = 0;
		switch(id){
		case "ingresoneto":
			//resultado = indPredefinido.calcularIngNeto("America Movil",2006); 
			//resultado = 3722;
			break;
		default:
			break;
		}
		return resultado;*/
		if ( memory.containsKey(id) ) return memory.get(id);
		return 0;
	}
	/** expr op=('*'|'/') expr */
	@Override
	public Integer visitMulDiv(indicadoresParser.MulDivContext ctx) {
		int left = visit(ctx.expr(0)); // get value of left RESexpression
		int right = visit(ctx.expr(1)); // get value of right RESexpression
		if ( ctx.op.getType() == indicadoresParser.MUL ) return left * right;
		return left / right; // must be DIV
	}
	/** expr op=('+'|'-') expr */
	@Override
	public Integer visitSumRes(indicadoresParser.SumResContext ctx) {
		int left = visit(ctx.expr(0)); // get value of left RESexpression
		int right = visit(ctx.expr(1)); // get value of right RESexpression
		if ( ctx.op.getType() == indicadoresParser.SUM ) return left + right;
		return left - right; // must be RES
	}
	/** '(' expr ')' */
	@Override
	public Integer visitParens(indicadoresParser.ParensContext ctx) {
		return visit(ctx.expr()); // return child expr's value
	}
		
	

	public static void main (String args[]) throws IOException{
	  	
    	String inputFile = null; //En "Run Configurations, ponen en Java Application => Arguments => Program&Arguments => {dirDel indicadores.txt}"
		if (args.length > 0)
			inputFile = args[0];
		
		InputStream is = System.in;
		if (inputFile != null)is = new FileInputStream(inputFile);

		@SuppressWarnings("deprecation")
		ANTLRInputStream input = new ANTLRInputStream(is);
   
        indicadoresLexer lexer = new indicadoresLexer(input);

        CommonTokenStream tokens = new CommonTokenStream(lexer);

        indicadoresParser parser = new indicadoresParser(tokens);

        ParseTree tree = parser.prog(); // parse

        IndVisitor eval = new IndVisitor();

        eval.visit(tree);
      
       System.out.println(tree);
       
        
		
	}

}
	/*
	si recibimos una cadena OTROINDICADOR suceda
	calculoNecesario = ctx.OTROINDICADOR.getText();
	indicadorPredefinido indi = new IndicadorPredefinido();
	indi.
	 * */