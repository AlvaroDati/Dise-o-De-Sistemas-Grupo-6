package proyectoInversiones.indicadores;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.FileInputStream;
import java.io.InputStream;

public class Indicadores {
    public static void main(String[] args) throws Exception {
        /*
         * Ac� tendr�a que ir lo que pone el usuario y de ah� que el parser lo pueda detectar
         * Ser�a el uso que tendr�a la variable "input";
         * Esta hecho en un main para probarlo por consola y luego lo pasamos a la view
         * 
         * 
         * */
        indicadoresLexer lexer = new indicadoresLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        indicadoresParser parser = new indicadoresParser(tokens);
        ParseTree tree = parser.fuente(); // parse

        IndVisitor eval = new IndVisitor();
        eval.visit(tree);
    }
}