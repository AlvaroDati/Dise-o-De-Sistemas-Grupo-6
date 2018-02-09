grammar indicadores;
@header {
   
package proyectoInversiones.antlr4;
import java.util.HashMap;
}
//



@members {
HashMap memory = new HashMap();
}

prog: stat+ ;
stat: expr NEWLINE # printExpr
|  INDICADOR '=' expr NEWLINE # assign
| NEWLINE # blank
;

expr: expr op=('*'|'/') expr # MulDiv
| expr op=('+'|'-') expr # SumRes
| INT expr# int
| INDICADOR # id
| '(' expr ')' # parens
| INDICADOR '(' INDICADOR '(' INT ')'')' #EmpresaCuentaPeriodo
|INDICADOR '(' INDICADOR ')' #EmpresaCuenta
;
		 

MUL : '*' ; 
DIV : '/' ;
SUM : '+' ;
RES : '-' ;
INDICADOR : [a-z' 'A-Z'-']+ ; 
INT : [0-9]+ ; 
NEWLINE:'\r'? '\n' ; 
WS : [ \t]+ -> skip ; 