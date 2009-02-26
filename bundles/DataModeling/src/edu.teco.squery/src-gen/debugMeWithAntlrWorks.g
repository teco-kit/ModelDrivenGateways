grammar debugMeWithAntlrWorks;
 options{backtrack=true; memoize=true;} 



parse :
	 result=ruleQueryModel
	 // Always return the root node! This prevents that this method is called multiple times
	 // with interesting side effects.
	 EOF
;

ruleQueryModel 

:
((ruleAutomata)

(ruleAction)+
)
;

ruleAutomata 

:
(('model')

( RULE_STRING
)

(';')
)
;

ruleAction 

:
(('action')

(RULE_ID)

('on')

(ruleQuery)

(';')
)
;

ruleQuery 

:
((ruleState)

(ruleCondition)?
)
;

ruleCondition 

:
(('where')

(ruleBoolExpression)

((('||')
	|
('&&')
)

(ruleBoolExpression)
)*
)
;

ruleBoolExpression 

:
((ruleState)

(ruleOperand)

(RULE_STRING)
)
;

ruleState 

:
((RULE_ID
)
	|
(('/')

(RULE_ID
)
)+
)
;

ruleOperand 

:
(('==')
	|
('<=')
	|
('>=')
	|
('<')
	|
('>')
)
;

RULE_ID :

	 ('^')?('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*
	 
;

RULE_STRING :

	 '\"' ( '\\' ('b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\') | ~('\\'|'\"') )* '\"' |
	 '\'' ( '\\' ('b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\') | ~('\\'|'\'') )* '\''
	 
;

RULE_INT :

	 ('-')?('0'..'9')+
	 
;

RULE_WS :

	 (' '|'\t'|'\r'|'\n')+ {$channel=HIDDEN;}
	 
;

RULE_ML_COMMENT :

	 '/*' ( options {greedy=false;} : . )* '*/' {$channel=HIDDEN;}
	 
;

RULE_SL_COMMENT :

	 '//' ~('\n'|'\r')* '\r'? '\n' {$channel=HIDDEN;}
	 
;

