package proyectoInversiones.indicadores;

import org.antlr.v4.runtime.*;  
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.FileInputStream;
import java.io.InputStream;
import proyectoInversiones.antlr4.*;


public class Indicadores {

    public static void main(String[] args) throws Exception {
    	
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

        ParseTree tree = parser.sentencia(); // parse

        IndVisitor eval = new IndVisitor();

        eval.visit(tree);
        System.out.println(tree);

        

	}

}