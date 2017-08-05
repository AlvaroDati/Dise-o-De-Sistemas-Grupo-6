package proyectoInversiones.indicadores;

import proyectoInversiones.antlr4.*;
import proyectoInversiones.Empresa;
import proyectoInversiones.NuevoLeerArchivo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;



public class IndVisitor extends indicadoresBaseVisitor<Integer>{
	/** "memory" for our calculator; variable/value pairs go here */
	 
	Map<String,List<Indicador>> memory = new HashMap<String, List<Indicador>>();
	ArrayList<Float> vai = new ArrayList<Float>();
	int periodoGlobal;
	int resultado = 0;
	public Map<String, List<Indicador>> getMemory() {
		return memory;
	}
	public void setMemory(Map<String, List<Indicador>> memory) {
		this.memory = memory;
	}
	/**  INDICADOR '(' INDICADOR')' '=' expr NEWLINE */
	@Override
	public Integer visitAssign(indicadoresParser.AssignContext ctx) {
		List<Indicador> indicadorUsuario = new ArrayList<Indicador>();
		ArrayList<Float> valor_cuenta_indicador = new ArrayList<Float>();
		String id = ctx.getText(); // id is left-hand side of '='
		Indicador indicadorAux           = new Indicador();
		int periodo = 0;
		int i = id.indexOf("(");
		String empresa = id.substring(0, i);
		List<Indicador> list;
		if(memory.containsKey(empresa)){
			list = memory.get(empresa);
			indicadorUsuario.addAll(list);
		}
		String restante = id.substring(i+1);
		int b = restante.indexOf(")");
		String cuenta_indicador = restante.substring(0, b); 
		indicadorAux.setNombre(cuenta_indicador);
		int value = visit(ctx.expr()); // compute value of expression on right
		indicadorAux.setValorIndicador(value);
		if(!vai.isEmpty()){
			valor_cuenta_indicador = vai;
			indicadorAux.setValorCuentaIndicador(valor_cuenta_indicador);
			vai.clear();
		}
		periodo = periodoGlobal;
		indicadorAux.setPeriodo(periodo);
		
		indicadorUsuario.add(indicadorAux);
		
		System.out.printf("Size de la lista %d\n", indicadorUsuario.size());
		
		resultado = value;
		System.out.printf("Resultado %d \n",resultado);
		resultado = 0;
		
		System.out.printf("Resultado %d \n",resultado);
		memory.put(empresa, indicadorUsuario);
		
		
		//memory.put(id, value); // store it in our memory
		System.out.printf("Value %d \n",value);
	
		periodoGlobal = 0;
		return value;
	}
	/** expr NEWLINE */
	@Override
	public Integer visitPrintExpr(indicadoresParser.PrintExprContext ctx) {
		Integer value = visit(ctx.expr()); // evaluate the expr child
		/*System.out.println(value); // print the result
		System.out.println(resultado); // print the result*/
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
		//if ( memory.containsKey(id) ) return memory.get(id);
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
		String cuenta_indicador = restante.substring(0, b); //COMO CUENTA TAMBIEN ME REFIERO A INDICADOR PREDEFINIDO, YA QUE LOS INDICADORES PREDEFINIDOS SE PUEDEN USAR PARA HACER OTROS INDICADORES
		String periodoAux = restante.substring(b+1);
		int c = periodoAux.indexOf(")");
		String periodo = periodoAux.substring(0, c);
		int per = Integer.valueOf(periodo);
	
		Empresa empresaAsociada = new Empresa(empresa); 
		Indicador indicador = new Indicador();
		String nombreCuenta = cuenta_indicador.toUpperCase();
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
		periodoGlobal = per;
		//indicadorAux.setPeriodo(per);
		return value;
	}
	
	public Integer visitEmpresaCuenta(indicadoresParser.EmpresaCuentaContext ctx){
		
		int value = resultado;
		NuevoLeerArchivo archivoEmpresa = new NuevoLeerArchivo(); //YA SE QUE NO ESTA MUY COPADO INSTANCIAR UN ARCHIVO ACA, PERO NO HAY OTRA MANERA DE SACAR LAS CUENTAS
																 //AMENOS QUE LA CLASE INDICADOR HEREDE DE NUEVOLEERARCHIVO, QUE TAMPOCO TIENE MUCHO SENTIDO	
		ArrayList<Float> valor_cuenta_indicador = new ArrayList<Float>();
		String id = ctx.getText();
		int i = id.indexOf("(");
		String empresa = id.substring(0, i);
		String resultado = id.substring(i+1);
		int b = resultado.indexOf(")");
		String cuenta_indicador = resultado.substring(0, b); //COMO CUENTA TAMBIEN ME REFIERO A INDICADOR PREDEFINIDO, YA QUE LOS INDICADORES PREDEFINIDOS SE PUEDEN USAR PARA HACER OTROS INDICADORES
		
		Empresa empresaAsociada = new Empresa(empresa); 
		Indicador indicador = new Indicador();
		String nombreCuenta = cuenta_indicador.toUpperCase();
		
		switch(nombreCuenta){
		/*
		 * INICIO INDICADORES PREDEFINIDOS
		 */
		case "INGRESONETO":
			 valor_cuenta_indicador =  indicador.ingresoNeto(empresaAsociada);
			break;
		case "ROE":
			 valor_cuenta_indicador =  indicador.roe(empresaAsociada);
			break;
		/***
		 * FIN INDICADORES PREDEFINIDOS
		 * 	
		 */
		/*
		 * INICIO CUENTAS	
		 */
		case("EBITDA"):
			 valor_cuenta_indicador = archivoEmpresa.obtenerCuentaDe(empresaAsociada, nombreCuenta);
		break;
		case("FDS"):
			 valor_cuenta_indicador = archivoEmpresa.obtenerCuentaDe(empresaAsociada, nombreCuenta);
		break;
		case("FCASHFLOW"):
			 valor_cuenta_indicador = archivoEmpresa.obtenerCuentaDe(empresaAsociada, nombreCuenta);
		break;
		case("INGNETOOPCONT"):
			 valor_cuenta_indicador = archivoEmpresa.obtenerCuentaDe(empresaAsociada, nombreCuenta);
		break;
		case("INGNETOOPDISC"):
			 valor_cuenta_indicador = archivoEmpresa.obtenerCuentaDe(empresaAsociada, nombreCuenta);
		break;
		case("DEUDA"):
			 valor_cuenta_indicador = archivoEmpresa.obtenerCuentaDe(empresaAsociada, nombreCuenta);
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
		vai = valor_cuenta_indicador;
		return 0;
	}
	
	
	
	public Map<String,List<Indicador>> obtenerIndicadoresUsuario(String ruta) throws IOException{
		
		Map<String, List<Indicador>> usuario = new HashMap<String,List<Indicador>>();

		String inputFile = null; //En "Run Configurations, ponen en Java Application => Arguments => Program&Arguments => {dirDel indicadores.txt}"
		if (ruta.length() > 0)
			inputFile = ruta;

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

		usuario = eval.getMemory();
      
		
		
		return usuario;
	}
	
	public static void main (String args[]) throws IOException{
	  	


		IndVisitor visitor = new IndVisitor();
		Map<String,List<Indicador>> usuario = visitor.obtenerIndicadoresUsuario("output.txt");

		System.out.println(usuario);
		System.out.println(usuario.size());
		
	/*
	 * 	usuario.forEach((x,y)->System.out.print(y.getNombre()));
		usuario.forEach((x,y)->System.out.print(y.getValorIndicador()));
		usuario.forEach((x,y)->System.out.print(y.getValorCuentaIndicador()));
	 */

		 for(Entry<String, List<Indicador>> entry : usuario.entrySet()){   
			 for(int i = 0;i<entry.getValue().size();i++)
		         System.out.println(entry.getKey() + " : " + entry.getValue().get(i).getPeriodo() +""+entry.getValue().get(i).getNombre() +" "+entry.getValue().get(i).getValorIndicador() +""+entry.getValue().get(i).getValorCuentaIndicador() );
		    }
		
	}

}
	/*
	si recibimos una cadena OTROINDICADOR suceda
	calculoNecesario = ctx.OTROINDICADOR.getText();
	indicadorPredefinido indi = new IndicadorPredefinido();
	indi.
	
	
	Map<campo1,campo2>; campo1 = Empresa
						campo2 = HashMap<Cuenta/Indicador,Valor>
	
	map.hasKey(campo1), se fija, campo2.hasKey(cuenta2), se fijaaaa 
	
	
	 * */