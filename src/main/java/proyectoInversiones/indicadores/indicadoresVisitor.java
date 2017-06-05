package proyectoInversiones.indicadores;
// Generated from indicadores.g4 by ANTLR 4.7

import java.util.HashMap;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link indicadoresParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface indicadoresVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link indicadoresParser#fuente}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuente(indicadoresParser.FuenteContext ctx);
	/**
	 * Visit a parse tree produced by {@link indicadoresParser#sentencia}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSentencia(indicadoresParser.SentenciaContext ctx);
	/**
	 * Visit a parse tree produced by {@link indicadoresParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpresion(indicadoresParser.ExpresionContext ctx);
	/**
	 * Visit a parse tree produced by {@link indicadoresParser#expresionMultiple}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpresionMultiple(indicadoresParser.ExpresionMultipleContext ctx);
	/**
	 * Visit a parse tree produced by {@link indicadoresParser#operando}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperando(indicadoresParser.OperandoContext ctx);
}