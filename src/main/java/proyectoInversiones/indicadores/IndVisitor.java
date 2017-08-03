package proyectoInversiones.indicadores;

import proyectoInversiones.antlr4.*;
import proyectoInversiones.Empresa;
import proyectoInversiones.NuevoLeerArchivo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap; 
import java.util.Map;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;



public class IndVisitor extends indicadoresBaseVisitor<Integer>{
	/** "memory" for our calculator; variable/value pairs go here */
	Map<String, Integer> memory = new HashMap<String, Integer>();
	int resultado = 0;
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
		System.out.println(value);
		return value;
	}
	/** expr NEWLINE */
	@Override
	public Integer visitPrintExpr(indicadoresParser.PrintExprContext ctx) {
		Integer value = visit(ctx.expr()); // evaluate the expr child
		System.out.println(value); // print the result
		System.out.println(resultado); // print the result
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
		/*Indicador indPredefinido = new Indicador();
		
		int resultado = 0;
		switch(id){
		case "ingresoneto":
			resultado = indPredefinido.ingresoNeto(empresa);
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
		int answer = 0;
		if ( ctx.op.getType() == indicadoresParser.MUL ){
			answer =  left * right;
		}else{
			answer =left / right; 
		}
		resultado += answer;
		return answer; // must be DIV
	}
	/** expr op=('+'|'-') expr */
	@Override
	public Integer visitSumRes(indicadoresParser.SumResContext ctx) {
		int left = visit(ctx.expr(0)); // get value of left RESexpression
		int right = visit(ctx.expr(1)); // get value of right RESexpression
		int answer = 0;
		if ( ctx.op.getType() == indicadoresParser.SUM ){
			answer = left + right;
		}else{
			answer = left - right;
		}
		resultado += answer;
		return answer; // must be RES
	}
	/** '(' expr ')' */
	@Override
	public Integer visitParens(indicadoresParser.ParensContext ctx) {
		return visit(ctx.expr()); // return child expr's value
	}
		
	 
	@Override 
	public Integer visitEmpresaCuentaPeriodo(indicadoresParser.EmpresaCuentaPeriodoContext ctx){
		
		int value = 0;
		NuevoLeerArchivo archivoEmpresa = new NuevoLeerArchivo(); //YA SE QUE NO ESTA MUY COPADO INSTANCIAR UN ARCHIVO ACA, PERO NO HAY OTRA MANERA DE SACAR LAS CUENTAS
																//AMENOS QUE LA CLASE INDICADOR HEREDE DE NUEVOLEERARCHIVO, QUE TAMPOCO TIENE MUCHO SENTIDO	
		String id = ctx.getText();
		int i = id.indexOf("(");
		String empresa = id.substring(0, i);
		String restante = id.substring(i+1);
		int b = restante.indexOf("(");
		String cuenta = restante.substring(0, b); //COMO CUENTA TAMBIEN ME REFIERO A INDICADOR PREDEFINIDO, YA QUE LOS INDICADORES PREDEFINIDOS SE PUEDEN USAR PARA HACER OTROS INDICADORES
		String periodoAux = restante.substring(b+1);
		int c = periodoAux.indexOf(")");
		String periodo = periodoAux.substring(0, c);
		int per = Integer.valueOf(periodo);
		
		Empresa empresaAsociada = new Empresa(empresa); 
		Indicador indicador = new Indicador();
		String nombreCuenta = cuenta.toUpperCase();
		switch(nombreCuenta){
		/*
		 * INICIO INDICADORES PREDEFINIDOS
		 */
		case "INGRESONETO":
			value = (int) indicador.obtenerIngresoNetoSegunPeriodo(empresaAsociada,per);
			break;
		case "ROE":
			value = (int) indicador.obtenerRoeSegunPeriodo(empresaAsociada,per);
			break;
		/***
		 * FIN INDICADORES PREDEFINIDOS
		 * 	
		 */
		/*
		 * INICIO CUENTAS	
		 */
		case("EBITDA"):
			value = (int) archivoEmpresa.obtenerCuentaSegunPeriodo(empresaAsociada, nombreCuenta, per);
		break;
		case("FDS"):
			value = (int) archivoEmpresa.obtenerCuentaSegunPeriodo(empresaAsociada, nombreCuenta, per);
		break;
		case("FCASHFLOW"):
			value = (int) archivoEmpresa.obtenerCuentaSegunPeriodo(empresaAsociada, nombreCuenta, per);
		break;
		case("INGNETOOPCONT"):
			value = (int) archivoEmpresa.obtenerCuentaSegunPeriodo(empresaAsociada, nombreCuenta, per);
		break;
		case("INGNETOOPDISC"):
			value = (int) archivoEmpresa.obtenerCuentaSegunPeriodo(empresaAsociada, nombreCuenta, per);
		break;
		case("DEUDA"):
			value = (int) archivoEmpresa.obtenerCuentaSegunPeriodo(empresaAsociada, nombreCuenta, per);
		break;
		default:
			/* ACA HAY DOS OPCIONES
			 * 1)Asumo que si no es un indicador predefinido, por default sea una cuenta, y solo llamo a un metodo, ó hago el case horrible que está arriba.
			 */
			//Tirar excepcion
			break;
		}
		/*
		 * FIN CUENTAS
		 */
		return value;
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