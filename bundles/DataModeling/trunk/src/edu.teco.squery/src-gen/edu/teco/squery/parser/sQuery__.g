lexer grammar sQuery;
@members {
	 private List<ErrorMsg> errors = new ArrayList<ErrorMsg>();
	public List<ErrorMsg> getErrors() {
		return errors;
	}

	public String getErrorMessage(RecognitionException e, String[] tokenNames) {
		String msg = super.getErrorMessage(e,tokenNames);
		errors.add(AntlrUtil.create(msg,e,tokenNames));
		return msg;
	}
}
@header {
package edu.teco.squery.parser;

import org.openarchitectureware.xtext.parser.ErrorMsg;
import org.openarchitectureware.xtext.parser.impl.AntlrUtil;

}

T10 : 'model' ;
T11 : ';' ;
T12 : 'action' ;
T13 : 'on' ;
T14 : 'where' ;
T15 : '||' ;
T16 : '&&' ;
T17 : '/' ;
T18 : '==' ;
T19 : '<=' ;
T20 : '>=' ;
T21 : '<' ;
T22 : '>' ;

// $ANTLR src "..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g" 485
RULE_ID :

	 ('^')?('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*
	 
;

// $ANTLR src "..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g" 491
RULE_STRING :

	 '\"' ( '\\' ('b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\') | ~('\\'|'\"') )* '\"' |
	 '\'' ( '\\' ('b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\') | ~('\\'|'\'') )* '\''
	 
;

// $ANTLR src "..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g" 498
RULE_INT :

	 ('-')?('0'..'9')+
	 
;

// $ANTLR src "..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g" 504
RULE_WS :

	 (' '|'\t'|'\r'|'\n')+ {$channel=HIDDEN;}
	 
;

// $ANTLR src "..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g" 510
RULE_ML_COMMENT :

	 '/*' ( options {greedy=false;} : . )* '*/' {$channel=HIDDEN;}
	 
;

// $ANTLR src "..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g" 516
RULE_SL_COMMENT :

	 '//' ~('\n'|'\r')* '\r'? '\n' {$channel=HIDDEN;}
	 
;

