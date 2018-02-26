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
;

expr: expr op=('*'|'/') expr # MulDiv
| expr op=('+'|'-') expr # SumRes
| '(' expr ')' # parens
| INT # int
| INDICADOR # id
| NEWLINE # blank
//| INDICADOR '(' INDICADOR '(' INT ')'')' #EmpresaCuentaPeriodo
//|INDICADOR '(' INDICADOR ')' #EmpresaCuenta
;
		 

MUL : '*' ; 
DIV : '/' ;
SUM : '+' ;
RES : '-' ;
INDICADOR : [a-z''A-Z'-']+ ; 
INT : [0-9]+ ; 
NEWLINE:'\r'? '\n' ; 
WS : [ \t]+ -> skip ; 