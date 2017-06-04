grammar indicadores;
@header {
import java.util.HashMap;
}
@members {
HashMap memory = new HashMap();
}
// expresion
fuente: sentencia+ ;

sentencia: 
expresion SALTODELINEA {System.out.println( "\n" + $expresion.value );} 
| OTROINDICADOR '=' expresion SALTODELINEA {memory.put( $OTROINDICADOR.text , new Double( $expresion.value )
);} 
| SALTODELINEA;

expresion returns [double value] : 
e=expresionMultiple{ $value = $e.value ;} ( '+'  e=expresionMultiple{ $value += $e.value ;} 
| '-'  e=expresionMultiple{ $value -= $e.value ;} )* ;

expresionMultiple returns [double value]: 
e=operando { $value = $e.value ;} ( '*' e=operando { $value *= $e.value ;} 
| '/' e=operando{ $value /= $e.value ;} )*;

operando returns [double value]: 
  CUENTA { $value = Double.parseDouble( $CUENTA.text );} 
| OTROINDICADOR {Double ind = (Double)memory.get( $OTROINDICADOR.text ); $value = ind.doubleValue();}
| '(' expresion ')'{ $value = $expresion.value ;} ;

// reglas
OTROINDICADOR : ( 'a' .. 'z' | 'A' .. 'Z' )+ ;
CUENTA:  '0' .. '9'+ ;
SALTODELINEA: '\r' ? '\n';
WS : ( ' ' | '\t' )+ {skip();} ;