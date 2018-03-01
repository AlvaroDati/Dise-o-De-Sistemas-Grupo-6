package proyectoInversiones.indicadores;

import proyectoInversiones.Empresa;
import proyectoInversiones.Indicador;
import proyectoInversiones.NuevoLeerArchivo;
import proyectoInversiones.antlr4.*;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;



public class IndVisitor extends indicadoresBaseVisitor<Integer> {
	/** "memory" for our calculator; variable/value pairs go here */

	List<Indicador> listaDeIndicador = new ArrayList<Indicador>();
	List<indicadoresParser.IdContext> cuentaIndicador = new ArrayList<indicadoresParser.IdContext>();
	Map<Indicador, List<indicadoresParser.IdContext>> mapIndicador = new HashMap<Indicador, List<indicadoresParser.IdContext>>();
	List<Indicador> indicadorAuxGlobal = new ArrayList<Indicador>();

	public Map<Indicador, List<indicadoresParser.IdContext>> getMapIndicador() {
		return mapIndicador;
	}

	public void setMapIndicador(Map<Indicador, List<indicadoresParser.IdContext>> mapIndicador) {
		this.mapIndicador = mapIndicador;
	}

	public List<indicadoresParser.IdContext> getCuentaIndicador() {
		return cuentaIndicador;
	}

	public void setCuentaIndicador(List<indicadoresParser.IdContext> cuentaIndicador) {
		this.cuentaIndicador = cuentaIndicador;
	}

	public List<Indicador> getListaDeIndicador() {
		return listaDeIndicador;
	}

	public void setListaDeIndicador(List<Indicador> listaDeIndicador) {
		this.listaDeIndicador = listaDeIndicador;
	}

	ArrayList<Float> vai = new ArrayList<Float>();
	int periodoGlobal;
	List<String> cuenta = new ArrayList<String>();
	Map<String,List<String>> map = new HashMap<String,List<String>>(); 
	/** INDICADOR '(' INDICADOR')' '=' expr NEWLINE */
	String nombreIndicadorGlobal;
	
	
	@Override
	public Integer visitAssign(indicadoresParser.AssignContext ctx) {

		List<ArmadorIndicador> indicadorUsuario = new ArrayList<ArmadorIndicador>();
		ArrayList<Float> valor_cuenta_indicador = new ArrayList<Float>();
		ArrayList<Float> vAux = new ArrayList<Float>();
		Indicador indicadorAux = new Indicador();
		int resultado = 0;
//		System.out.println("Tesxto de visitAssign: "+ctx.getText());
		String id = ctx.getText();
		// System.out.print(id);
		int periodo = 0;
		int i = id.indexOf("=");
		String nombreIndicador = id.substring(0, i);
	//	System.out.println(nombreIndicador);
//		List<String> stringAux = new ArrayList<String>();
//		map.put(nombreIndicador, stringAux);
		List<Indicador> listaUsuario = new ArrayList<Indicador>();
		nombreIndicadorGlobal = nombreIndicador;
		mapIndicador.put(indicadorAux, cuentaIndicador);
//		System.out.println("CTX.EXPR(): "+ctx.expr().getText());
		int value = visit(ctx.expr()); // compute value of expression on right
//		System.out.println("Value: "+value);
		indicadorAux.setNombre(nombreIndicador);
		indicadorAux.setValorIndicador(value);
		List<String> aux = cuenta;
		indicadorAux.setCuentas(aux);
		// cuentaIndicador = null;
		resultado = value;

		periodo = periodoGlobal;
		indicadorAux.setPeriodo(periodo);
		// indicadorAux.setEmpresa(unaEmpresa);
		// indicadorUsuario.add(indicadorAux);
		resultado = 0;
		listaUsuario.add(indicadorAux);
		listaDeIndicador.addAll(listaUsuario);
		
		// System.out.println(listaUsuario);
		return value;
	}

	/** expr NEWLINE */
	@Override
	public Integer visitPrintExpr(indicadoresParser.PrintExprContext ctx) {
		Integer value = visit(ctx.expr()); // evaluate the expr child
//		System.out.println(ctx.getText());
		/*
		 * System.out.println(value); // print the result
		 * System.out.println(resultado); // print the result
		 */
		return 0; // return dummy value
	}

	/** INT */
	@Override
	public Integer visitInt(indicadoresParser.IntContext ctx) {
//		System.out.println(ctx.INT().getText());
;		return Integer.valueOf(ctx.INT().getText());
	}

	/** ID */

	
	@Override
	public Integer visitId(indicadoresParser.IdContext ctx) {
		String id = ctx.INDICADOR().getText();
//		ctx.getRuleContext().getText();
//		System.out.printf("\nctx.getStart().getText(): %s\n", ctx.getRuleContext().getText());
//		System.out.printf("ctx.parent.getText(): %s\n", ctx.parent.parent.parent.getText());
//		int ia = ctx.parent.parent.getText().indexOf("=");
//		String nombreIndicador = ctx.parent.parent.parent.getText().substring(0, ia);
		//System.out.println(1);
		String nombreIndicador = nombreIndicadorGlobal;
		if(map.containsKey(nombreIndicador)){
			//System.out.println(2);
			if(!map.get(nombreIndicador).contains(id)){
				//System.out.println(3);
				map.get(nombreIndicador).add(id);
			}
		}else{
			//System.out.println(4);
			List<String> stringAux = new ArrayList<String>();
			map.put(nombreIndicador, stringAux);
			map.get(nombreIndicador).add(id);
		}
		
		return 0;
	}

	/** expr op=('*'|'/') expr */
	@Override
	public Integer visitMulDiv(indicadoresParser.MulDivContext ctx) {
		int left = visit(ctx.expr(0)); // get value of left RESexpression
		int right = visit(ctx.expr(1)); // get value of right RESexpression
		int answer = 0;
		if (ctx.op.getType() == indicadoresParser.MUL) {
			answer = left * right;
		} else {
			answer = left / right;
		}

		return answer; // must be DIV
	}

	/** expr op=('+'|'-') expr */
	@Override
	public Integer visitSumRes(indicadoresParser.SumResContext ctx) {
		int left = visit(ctx.expr(0)); // get value of left RESexpression
		int right = visit(ctx.expr(1)); // get value of right RESexpression
		int answer = 0;
//System.out.println("visitSumRes : "+ctx.getText());
		if (ctx.op.getType() == indicadoresParser.SUM) {
			answer = left + right;
		} else {
			answer = left - right;
		}

		return answer; // must be RES
	}

	/** '(' expr ')' */
	@Override
	public Integer visitParens(indicadoresParser.ParensContext ctx) {
		return visit(ctx.expr()); // return child expr's value
	}

	
	
	public String getNombreDelIndicador(String expresion){
	String id = expresion;
	int i = id.indexOf("=");
	String nombreIndicador = id.substring(0, i-1);

	return nombreIndicador;
}

public Indicador obtenerResultadosIndicadoresUsuarioSegunEmpresa2(String ruta,Empresa empresaAsociada,int per,String expresionDeUnIndicador) throws IOException {
	List<Indicador> indicadorDelUsuario = this.obtenerResultadosIndicadoresUsuarioSegunEmpresa(ruta, empresaAsociada, per);
	
	Indicador elObjetoIndicadorResultante = new Indicador();
	elObjetoIndicadorResultante.setEmpresa(empresaAsociada);
	elObjetoIndicadorResultante.setPeriodo(per);
	elObjetoIndicadorResultante.setExpresion(expresionDeUnIndicador);
	String inputANTLR = expresionDeUnIndicador; 
	String nombreIndicadorAEvaluar = getNombreDelIndicador(expresionDeUnIndicador);
	
	if (expresionDeUnIndicador.length() > 0)
		inputANTLR = expresionDeUnIndicador;

	InputStream is = new ByteArrayInputStream(inputANTLR.getBytes());;
	
	BufferedReader br = new BufferedReader(new InputStreamReader(is));
	
	@SuppressWarnings("deprecation")
	ANTLRInputStream input = new ANTLRInputStream(br.readLine() + "\n");

	indicadoresLexer lexer = new indicadoresLexer(input);

	CommonTokenStream tokens = new CommonTokenStream(lexer);

	indicadoresParser parser = new indicadoresParser(tokens);

	ParseTree tree = parser.prog(); // parse

	IndVisitor eval = new IndVisitor();

	eval.visit(tree);

	eval.getCuentaIndicador();
	NuevoLeerArchivo archivoEmpresa = new NuevoLeerArchivo(); 
	ArmadorIndicador indicador = new ArmadorIndicador();
	elObjetoIndicadorResultante = eval.getListaDeIndicador().get(0);
	elObjetoIndicadorResultante.setPeriodo(per);
		for(Map.Entry<String, List<String>> entry:eval.map.entrySet()){
			float value = 0;
			if(elObjetoIndicadorResultante.getNombre().equals(entry.getKey())){
				
				for(int j = 0;j<entry.getValue().size();j++){
					String nombreCuenta = entry.getValue().get(j);
					switch(nombreCuenta){
				case "INGRESONETO":
					value += (int) indicador.obtenerIngresoNetoSegunPeriodo(empresaAsociada, per);
					break;
				case "ROE":
					value += (int) indicador.obtenerRoeSegunPeriodo(empresaAsociada, per);
					break;
				case ("EBITDA"):
					value += (int) archivoEmpresa.obtenerCuentaSegunPeriodo(empresaAsociada, nombreCuenta, per);
					break;
				case ("FDS"):
					value += (int) archivoEmpresa.obtenerCuentaSegunPeriodo(empresaAsociada, nombreCuenta, per);
					break;
				case ("FCASHFLOW"):
					value += (int) archivoEmpresa.obtenerCuentaSegunPeriodo(empresaAsociada, nombreCuenta, per);
					break;
				case ("INGNETOOPCONT"):
					value += (int) archivoEmpresa.obtenerCuentaSegunPeriodo(empresaAsociada, nombreCuenta, per);
					break;
				case ("INGNETOOPDISC"):
					value += (int) archivoEmpresa.obtenerCuentaSegunPeriodo(empresaAsociada, nombreCuenta, per);
					break;
				case ("DEUDA"):
					value -= (int) archivoEmpresa.obtenerCuentaSegunPeriodo(empresaAsociada, nombreCuenta, per);
					break;
				default:
					for(int i = 0;i<indicadorDelUsuario.size();i++){
						if(indicadorDelUsuario.get(i).getNombre().contains(nombreIndicadorAEvaluar)){
							value += indicadorDelUsuario.get(i).getValorIndicador();
						}
					}
					break;
				}
				}
				
				elObjetoIndicadorResultante.setValorIndicador(elObjetoIndicadorResultante.getValorIndicador()+value);
				
			
			
		}
	}
		return elObjetoIndicadorResultante;
}

	
	public List<Indicador> obtenerResultadosIndicadoresUsuarioSegunEmpresa(String ruta,Empresa empresaAsociada,int per) throws IOException {

		List<Indicador> listaDeIndicador = new ArrayList<Indicador>();

		String inputFile = null; // En "Run Configurations, ponen en Java
									// Application => Arguments =>
									// Program&Arguments => {dirDel
									// indicadores.txt}"
		if (ruta.length() > 0)
			inputFile = ruta;

		InputStream is = System.in;
		if (inputFile != null)
			try {
				is = new FileInputStream(inputFile);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		@SuppressWarnings("deprecation")
		ANTLRInputStream input = null;
		try {
			input = new ANTLRInputStream(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		indicadoresLexer lexer = new indicadoresLexer(input);

		CommonTokenStream tokens = new CommonTokenStream(lexer);

		indicadoresParser parser = new indicadoresParser(tokens);

		ParseTree tree = parser.prog(); // parse

		IndVisitor eval = new IndVisitor();

		eval.visit(tree);

		eval.getCuentaIndicador();
		NuevoLeerArchivo archivoEmpresa = new NuevoLeerArchivo(); 
		ArmadorIndicador indicador = new ArmadorIndicador();

		listaDeIndicador = eval.getListaDeIndicador();
		
		for(int i = 0;i<listaDeIndicador.size();i++){
			listaDeIndicador.get(i).setPeriodo(per);
			for(Map.Entry<String, List<String>> entry:eval.map.entrySet()){
				float value = 0;
				if(listaDeIndicador.get(i).getNombre().equals(entry.getKey())){
					for(int j = 0;j<entry.getValue().size();j++){
						String nombreCuenta = entry.getValue().get(j);
						//System.out.println(nombreCuenta);
						switch(nombreCuenta){
					case "INGRESONETO":
						value += (int) indicador.obtenerIngresoNetoSegunPeriodo(empresaAsociada, per);
						break;
					case "ROE":
						value += (int) indicador.obtenerRoeSegunPeriodo(empresaAsociada, per);
						break;
					case ("EBITDA"):
						value += (int) archivoEmpresa.obtenerCuentaSegunPeriodo(empresaAsociada, nombreCuenta, per);
						break;
					case ("FDS"):
						value += (int) archivoEmpresa.obtenerCuentaSegunPeriodo(empresaAsociada, nombreCuenta, per);
						break;
					case ("FCASHFLOW"):
						value += (int) archivoEmpresa.obtenerCuentaSegunPeriodo(empresaAsociada, nombreCuenta, per);
						break;
					case ("INGNETOOPCONT"):
						value += (int) archivoEmpresa.obtenerCuentaSegunPeriodo(empresaAsociada, nombreCuenta, per);
						break;
					case ("INGNETOOPDISC"):
						value += (int) archivoEmpresa.obtenerCuentaSegunPeriodo(empresaAsociada, nombreCuenta, per);
						break;
					case ("DEUDA"):
					//	System.out.println(value);
						value -= (int) archivoEmpresa.obtenerCuentaSegunPeriodo(empresaAsociada, nombreCuenta, per);
					//	System.out.println(value);
						break;
					default:
						for(int a =0;i<listaDeIndicador.size();a++){
							if(listaDeIndicador.get(a).getNombre().equals(nombreCuenta)){
								value += listaDeIndicador.get(a).getValorIndicador();
							}
						break;
					}
					}
					}
					
					listaDeIndicador.get(i).setValorIndicador(listaDeIndicador.get(i).getValorIndicador()+value);
					
				}
				
			}
		}
		

		return listaDeIndicador;
	}
	
	
	public Indicador obtenerResultadoIndicadorSegunEmpresa(String expresionDeUnIndicador,Empresa empresaAsociada,int per) throws IOException {

		Indicador elObjetoIndicadorResultante = new Indicador();
		elObjetoIndicadorResultante.setEmpresa(empresaAsociada);
		elObjetoIndicadorResultante.setExpresion(expresionDeUnIndicador);
	
		String inputANTLR = expresionDeUnIndicador; 
		
		if (expresionDeUnIndicador.length() > 0)
			inputANTLR = expresionDeUnIndicador;

		InputStream is = new ByteArrayInputStream(inputANTLR.getBytes());;
		
		//ExprParser isa = new ExprParser();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		
		@SuppressWarnings("deprecation")
		ANTLRInputStream input = new ANTLRInputStream(br.readLine() + "\n");

		indicadoresLexer lexer = new indicadoresLexer(input);

		CommonTokenStream tokens = new CommonTokenStream(lexer);

		indicadoresParser parser = new indicadoresParser(tokens);

		ParseTree tree = parser.prog(); // parse

		IndVisitor eval = new IndVisitor();

		int valor = eval.visit(tree);
		eval.getCuentaIndicador();
		NuevoLeerArchivo archivoEmpresa = new NuevoLeerArchivo(); 
		ArmadorIndicador indicador = new ArmadorIndicador();
		elObjetoIndicadorResultante = eval.getListaDeIndicador().get(0);
	//	elObjetoIndicadorResultante.setPeriodo(per);
			for(Map.Entry<String, List<String>> entry:eval.map.entrySet()){
				float value = 0;
				elObjetoIndicadorResultante.setPeriodo(per);
				if(elObjetoIndicadorResultante.getNombre().equals(entry.getKey())){
					
					for(int j = 0;j<entry.getValue().size();j++){
						String nombreCuenta = entry.getValue().get(j);
						switch(nombreCuenta){
					case "INGRESONETO":
						value += (int) indicador.obtenerIngresoNetoSegunPeriodo(empresaAsociada, per);
						break;
					case "ROE":
						value += (int) indicador.obtenerRoeSegunPeriodo(empresaAsociada, per);
						break;
					case ("EBITDA"):
						value += (int) archivoEmpresa.obtenerCuentaSegunPeriodo(empresaAsociada, nombreCuenta, per);
						break;
					case ("FDS"):
						value += (int) archivoEmpresa.obtenerCuentaSegunPeriodo(empresaAsociada, nombreCuenta, per);
						break;
					case ("FCASHFLOW"):
						value += (int) archivoEmpresa.obtenerCuentaSegunPeriodo(empresaAsociada, nombreCuenta, per);
						break;
					case ("INGNETOOPCONT"):
						value += (int) archivoEmpresa.obtenerCuentaSegunPeriodo(empresaAsociada, nombreCuenta, per);
						break;
					case ("INGNETOOPDISC"):
						value += (int) archivoEmpresa.obtenerCuentaSegunPeriodo(empresaAsociada, nombreCuenta, per);
						break;
					case ("DEUDA"):
						value -= (int) archivoEmpresa.obtenerCuentaSegunPeriodo(empresaAsociada, nombreCuenta, per);
						break;
					default:
						break;
					}
					}
					elObjetoIndicadorResultante.setValorIndicador(elObjetoIndicadorResultante.getValorIndicador()+value);

				}
		}
//			elObjetoIndicadorResultante.setValorIndicador(elObjetoIndicadorResultante.getValorIndicador()+valor);

		return elObjetoIndicadorResultante;
	}
	
	public static void main (String args[]) throws IOException{
		IndVisitor ind = new IndVisitor();
		
		Indicador indi = new Indicador();
		indi = ind.obtenerResultadoIndicadorSegunEmpresa("hola=50+20", new Empresa("Apple"), 2016);
		System.out.println(indi.getValorIndicador());
	}
	
	
	
}