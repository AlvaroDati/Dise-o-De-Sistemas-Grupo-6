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
	 * Visit a parse tree produced by the {@code Expr}
	 * labeled alternative in {@link indicadoresParser#sentencia}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(indicadoresParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Asignar}
	 * labeled alternative in {@link indicadoresParser#sentencia}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAsignar(indicadoresParser.AsignarContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SumRes}
	 * labeled alternative in {@link indicadoresParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSumRes(indicadoresParser.SumResContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MulDiv}
	 * labeled alternative in {@link indicadoresParser#expresionMultiple}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulDiv(indicadoresParser.MulDivContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Cuenta}
	 * labeled alternative in {@link indicadoresParser#operando}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCuenta(indicadoresParser.CuentaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code OtroIndicador}
	 * labeled alternative in {@link indicadoresParser#operando}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOtroIndicador(indicadoresParser.OtroIndicadorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Parentesis}
	 * labeled alternative in {@link indicadoresParser#operando}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParentesis(indicadoresParser.ParentesisContext ctx);
}