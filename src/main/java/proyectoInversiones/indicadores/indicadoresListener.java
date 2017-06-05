package proyectoInversiones.indicadores;
// Generated from indicadores.g4 by ANTLR 4.7

import java.util.HashMap;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link indicadoresParser}.
 */
public interface indicadoresListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link indicadoresParser#fuente}.
	 * @param ctx the parse tree
	 */
	void enterFuente(indicadoresParser.FuenteContext ctx);
	/**
	 * Exit a parse tree produced by {@link indicadoresParser#fuente}.
	 * @param ctx the parse tree
	 */
	void exitFuente(indicadoresParser.FuenteContext ctx);
	/**
	 * Enter a parse tree produced by {@link indicadoresParser#sentencia}.
	 * @param ctx the parse tree
	 */
	void enterSentencia(indicadoresParser.SentenciaContext ctx);
	/**
	 * Exit a parse tree produced by {@link indicadoresParser#sentencia}.
	 * @param ctx the parse tree
	 */
	void exitSentencia(indicadoresParser.SentenciaContext ctx);
	/**
	 * Enter a parse tree produced by {@link indicadoresParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExpresion(indicadoresParser.ExpresionContext ctx);
	/**
	 * Exit a parse tree produced by {@link indicadoresParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExpresion(indicadoresParser.ExpresionContext ctx);
	/**
	 * Enter a parse tree produced by {@link indicadoresParser#expresionMultiple}.
	 * @param ctx the parse tree
	 */
	void enterExpresionMultiple(indicadoresParser.ExpresionMultipleContext ctx);
	/**
	 * Exit a parse tree produced by {@link indicadoresParser#expresionMultiple}.
	 * @param ctx the parse tree
	 */
	void exitExpresionMultiple(indicadoresParser.ExpresionMultipleContext ctx);
	/**
	 * Enter a parse tree produced by {@link indicadoresParser#operando}.
	 * @param ctx the parse tree
	 */
	void enterOperando(indicadoresParser.OperandoContext ctx);
	/**
	 * Exit a parse tree produced by {@link indicadoresParser#operando}.
	 * @param ctx the parse tree
	 */
	void exitOperando(indicadoresParser.OperandoContext ctx);
}