/**
 * Define a grammar called Hello
 */
grammar testGrammar;
 
expresion : termino OP termino ;
termino : NUMERO ;
NUMERO : DIGIT+ ; 
DIGIT : ('0' .. '9');
OP : '+' ;
WS : [ \t\r\n]+ -> skip ; // skip spaces, tabs, newlines

