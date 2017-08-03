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
	 * Visit a parse tree produced by {@link indicadoresParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(indicadoresParser.ProgContext ctx);
	/**
	 * Visit a parse tree produced by the {@code printExpr}
	 * labeled alternative in {@link indicadoresParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintExpr(indicadoresParser.PrintExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assign}
	 * labeled alternative in {@link indicadoresParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign(indicadoresParser.AssignContext ctx);
	/**
	 * Visit a parse tree produced by the {@code blank}
	 * labeled alternative in {@link indicadoresParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlank(indicadoresParser.BlankContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SumRes}
	 * labeled alternative in {@link indicadoresParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSumRes(indicadoresParser.SumResContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parens}
	 * labeled alternative in {@link indicadoresParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParens(indicadoresParser.ParensContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MulDiv}
	 * labeled alternative in {@link indicadoresParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulDiv(indicadoresParser.MulDivContext ctx);
	/**
	 * Visit a parse tree produced by the {@code EmpresaCuentaPeriodo}
	 * labeled alternative in {@link indicadoresParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEmpresaCuentaPeriodo(indicadoresParser.EmpresaCuentaPeriodoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code id}
	 * labeled alternative in {@link indicadoresParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitId(indicadoresParser.IdContext ctx);
	/**
	 * Visit a parse tree produced by the {@code int}
	 * labeled alternative in {@link indicadoresParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInt(indicadoresParser.IntContext ctx);
}