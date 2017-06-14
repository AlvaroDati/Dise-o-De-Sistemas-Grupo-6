grammar indicadores;
@header {  
package proyectoInversiones.antlr4;
import java.util.HashMap;
}



@members {
HashMap memory = new HashMap();
}
// expresion



sentencia: expresion                                                      #Expr
		 | OTROINDICADOR '=' expresion 									  #Asignar
		 ;								  

expresion:  expresionMultiple (op=( '+'|'-') expresionMultiple)*          #SumRes
		 ;

expresionMultiple :  operando  (op=( '*'|'/') operando)*                  #MulDiv
				  ;

operando :  CUENTA 														  #Cuenta
		 | OTROINDICADOR                                                  #OtroIndicador
		 | '(' expresion ')'                                              #Parentesis
		 ;

// reglas
OTROINDICADOR : ( 'a' .. 'z' | 'A' .. 'Z' )+ ;
CUENTA:  '0' .. '9'+ ;
//SALTODELINEA: '\r' ? '\n';
MUL : '*' ; 
DIV : '/' ;
SUM : '+' ;
RES : '-' ;
WS: [ \t\r\n]+ -> skip ;