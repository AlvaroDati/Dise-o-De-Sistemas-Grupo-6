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
| INDICADOR '=' expr NEWLINE # assign
| NEWLINE # blank
;

expr: expr op=('*'|'/') expr # MulDiv
| expr op=('+'|'-') expr # SumRes
| INT # int
| INDICADOR # id
| '(' expr ')' # parens
;
		 

MUL : '*' ; 
DIV : '/' ;
SUM : '+' ;
RES : '-' ;
INDICADOR : [a-zA-Z]+ ; 
INT : [0-9]+ ; 
NEWLINE:'\r'? '\n' ; 
WS : [ \t]+ -> skip ; 