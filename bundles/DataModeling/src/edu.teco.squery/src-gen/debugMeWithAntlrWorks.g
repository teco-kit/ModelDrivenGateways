grammar debugMeWithAntlrWorks;
 options{backtrack=true; memoize=true;} 



parse :
	 result=ruleQueryModel EOF
;

ruleQueryModel  :
((temp_automata=ruleAutomata )

(temp_actions=ruleAction )+
)
;

ruleAutomata  :
(('model')

(temp_model= RULE_STRING
)

(';')
)
;

ruleAction  :
(('action')

(temp_name=RULE_ID )

('on')

(temp_query=ruleQuery )

(';')
)
;

ruleQuery  :
((temp_st=ruleState )

(temp_condition=ruleCondition )?
)
;

ruleCondition  :
(('where')

(temp_expressions=ruleBoolExpression )

((('||')
	|
('&&')
)

(temp_expressions=ruleBoolExpression )
)*
)
;

ruleBoolExpression  :
((temp_op1=ruleState )

(temp_operand=ruleOperand )

(temp_op2=RULE_STRING )
)
;

ruleState  :
((temp_state=RULE_ID
)
	|
(('/')

(temp_state=RULE_ID
)
)+
)
;

ruleOperand  :
((temp_op='==' )
	|
(temp_op='<=' )
	|
(temp_op='>=' )
	|
(temp_op='<' )
	|
(temp_op='>' )
)
;

RULE_ID :

	 ('^')?('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*
	 
;

RULE_STRING :

	 '"' ( '\\' ('b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\') | ~('\\'|'"') )* '"' |
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

