grammar sQuery;
 options{backtrack=true; memoize=true;} 

@lexer::header {
package edu.teco.squery.parser;

import org.openarchitectureware.xtext.parser.ErrorMsg;
import org.openarchitectureware.xtext.parser.impl.AntlrUtil;

}

@parser::header {
package edu.teco.squery.parser;

import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.emf.ecore.EObject;

import org.openarchitectureware.xtext.parser.impl.AntlrUtil;
import org.openarchitectureware.xtext.XtextFile;
import org.openarchitectureware.xtext.parser.impl.EcoreModelFactory;
import org.openarchitectureware.xtext.parser.ErrorMsg;
import org.openarchitectureware.xtext.parser.model.ParseTreeManagerNeu;
import org.openarchitectureware.xtext.parser.parsetree.Node;

import edu.teco.squery.MetaModelRegistration;

}
@lexer::members {
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

@parser::members {

	private Token getLastToken() {
		return input.LT(-1);
	}

	protected Object convert(Object arg) {
		if (arg instanceof org.antlr.runtime.Token) {
			Token t = (Token) arg;
			String s = t.getText();
			if (t.getType() == sQueryLexer.RULE_ID && s.startsWith("^")) {
				return s.substring(1);
			} else if (t.getType()==sQueryLexer.RULE_STRING) {
				return s.substring(1,s.length()-1);
			} else if (t.getType()==sQueryLexer.RULE_INT) {
				return Integer.valueOf(s);
			}
			return s;
		}
		return arg;
	}


	private EcoreModelFactory factory = new EcoreModelFactory(MetaModelRegistration.getEPackage());
    private ParseTreeManagerNeu ptm = new ParseTreeManagerNeu();
	private XtextFile xtextfile = MetaModelRegistration.getXtextFile();
	
	{
			factory.addImport("automataMM", "http://www.teco.edu/automata");
		
	}

	public ParseTreeManagerNeu getResult() {
		return ptm;
	}

	private List<ErrorMsg> errors = new ArrayList<ErrorMsg>();
	public List<ErrorMsg> getErrors() {
		return errors;
	}

	@Override
	public void reportError(RecognitionException e) {
		String msg = super.getErrorMessage(e,tokenNames);
		errors.add(AntlrUtil.create(msg,e,tokenNames));
		ptm.addError(msg, e);
		// This doesn't work. ANTLR may simply report an superfluous token. In that case,
		// two nodes will be finished instead of one.
		// ptm.ruleFinished(null, end());
	}

    private boolean skipCurrentToken;
    
	@Override
	protected boolean recoverFromMismatchedElement(IntStream arg0, RecognitionException arg1, BitSet arg2) {
		skipCurrentToken = true;
		return super.recoverFromMismatchedElement(arg0, arg1, arg2);
	}
}


parse returns [Node r]:
	 result=ruleQueryModel
	 // Always return the root node! This prevents that this method is called multiple times
	 // with interesting side effects.
{
if (result != null)
  ptm.setModelElement(result);
$r = ptm.getCurrent();
ptm.ruleFinished(result);}	 EOF
;

ruleQueryModel returns [EObject result]
@init {boolean hasContent = false;}
:
			{
				$result = factory.create("", "QueryModel");
			 }
(({ptm.createNode(((EObject)((EObject)((EObject)xtextfile.eContents().get(1)).eContents().get(1)).eContents().get(0)));}temp_Automata=
ruleAutomata{if (temp_Automata != null) {
  hasContent = true;
  ptm.setModelElement(temp_Automata);
  factory.set($result,"automata",convert(temp_Automata),false);
  ptm.ruleFinished(temp_Automata);
} else {
  ptm.destroyNode();
}
}
)

({ptm.createNode(((EObject)((EObject)((EObject)xtextfile.eContents().get(1)).eContents().get(1)).eContents().get(1)));}temp_Action=
ruleAction{if (temp_Action != null) {
  hasContent = true;
  ptm.setModelElement(temp_Action);
  factory.add($result,"actions",convert(temp_Action),false);
  ptm.ruleFinished(temp_Action);
} else {
  ptm.destroyNode();
}
}
)+
)
 {if (!hasContent)
  $result = null;};
catch [RecognitionException re] {if (!hasContent)
    $result = null;
reportError(re);
recover(input,re);}

ruleAutomata returns [EObject result]
@init {boolean hasContent = false;}
:
			{
				$result = factory.create("", "Automata");
			 }
(({skipCurrentToken = false;}'model'{if (!skipCurrentToken) {
  hasContent = true;
  ptm.createNode(((EObject)((EObject)((EObject)xtextfile.eContents().get(2)).eContents().get(1)).eContents().get(0)));
  ptm.ruleFinished(getLastToken());
}})

({skipCurrentToken = false;}
 RULE_STRING
{if (!skipCurrentToken) {
  hasContent = true;
  Token temp = getLastToken();
  ptm.createNode(((EObject)((EObject)((EObject)xtextfile.eContents().get(2)).eContents().get(1)).eContents().get(1)));
  factory.set($result,"model",convert(temp),false);
  ptm.ruleFinished(temp);
}}
)

({skipCurrentToken = false;}';'{if (!skipCurrentToken) {
  hasContent = true;
  ptm.createNode(((EObject)((EObject)((EObject)xtextfile.eContents().get(2)).eContents().get(1)).eContents().get(2)));
  ptm.ruleFinished(getLastToken());
}})
)
 {if (!hasContent)
  $result = null;};
catch [RecognitionException re] {if (!hasContent)
    $result = null;
reportError(re);
recover(input,re);}

ruleAction returns [EObject result]
@init {boolean hasContent = false;}
:
			{
				$result = factory.create("", "Action");
			 }
(({skipCurrentToken = false;}'action'{if (!skipCurrentToken) {
  hasContent = true;
  ptm.createNode(((EObject)((EObject)((EObject)xtextfile.eContents().get(3)).eContents().get(1)).eContents().get(0)));
  ptm.ruleFinished(getLastToken());
}})

({skipCurrentToken = false;}
RULE_ID{if (!skipCurrentToken) {
  hasContent = true;
  Token temp = getLastToken();
  ptm.createNode(((EObject)((EObject)((EObject)xtextfile.eContents().get(3)).eContents().get(1)).eContents().get(1)));
  factory.set($result,"name",convert(temp),false);
  ptm.ruleFinished(temp);
}}
)

({skipCurrentToken = false;}'on'{if (!skipCurrentToken) {
  hasContent = true;
  ptm.createNode(((EObject)((EObject)((EObject)xtextfile.eContents().get(3)).eContents().get(1)).eContents().get(2)));
  ptm.ruleFinished(getLastToken());
}})

({ptm.createNode(((EObject)((EObject)((EObject)xtextfile.eContents().get(3)).eContents().get(1)).eContents().get(3)));}temp_Query=
ruleQuery{if (temp_Query != null) {
  hasContent = true;
  ptm.setModelElement(temp_Query);
  factory.set($result,"query",convert(temp_Query),false);
  ptm.ruleFinished(temp_Query);
} else {
  ptm.destroyNode();
}
}
)

({skipCurrentToken = false;}';'{if (!skipCurrentToken) {
  hasContent = true;
  ptm.createNode(((EObject)((EObject)((EObject)xtextfile.eContents().get(3)).eContents().get(1)).eContents().get(4)));
  ptm.ruleFinished(getLastToken());
}})
)
 {if (!hasContent)
  $result = null;};
catch [RecognitionException re] {if (!hasContent)
    $result = null;
reportError(re);
recover(input,re);}

ruleQuery returns [EObject result]
@init {boolean hasContent = false;}
:
			{
				$result = factory.create("", "Query");
			 }
(({ptm.createNode(((EObject)((EObject)((EObject)xtextfile.eContents().get(4)).eContents().get(1)).eContents().get(0)));}temp_State=
ruleState{if (temp_State != null) {
  hasContent = true;
  ptm.setModelElement(temp_State);
  factory.set($result,"st",convert(temp_State),false);
  ptm.ruleFinished(temp_State);
} else {
  ptm.destroyNode();
}
}
)

({ptm.createNode(((EObject)((EObject)((EObject)xtextfile.eContents().get(4)).eContents().get(1)).eContents().get(1)));}temp_Condition=
ruleCondition{if (temp_Condition != null) {
  hasContent = true;
  ptm.setModelElement(temp_Condition);
  factory.set($result,"condition",convert(temp_Condition),false);
  ptm.ruleFinished(temp_Condition);
} else {
  ptm.destroyNode();
}
}
)?
)
 {if (!hasContent)
  $result = null;};
catch [RecognitionException re] {if (!hasContent)
    $result = null;
reportError(re);
recover(input,re);}

ruleCondition returns [EObject result]
@init {boolean hasContent = false;}
:
			{
				$result = factory.create("", "Condition");
			 }
(({skipCurrentToken = false;}'where'{if (!skipCurrentToken) {
  hasContent = true;
  ptm.createNode(((EObject)((EObject)((EObject)xtextfile.eContents().get(5)).eContents().get(1)).eContents().get(0)));
  ptm.ruleFinished(getLastToken());
}})

({ptm.createNode(((EObject)((EObject)((EObject)xtextfile.eContents().get(5)).eContents().get(1)).eContents().get(1)));}temp_BoolExpression=
ruleBoolExpression{if (temp_BoolExpression != null) {
  hasContent = true;
  ptm.setModelElement(temp_BoolExpression);
  factory.add($result,"expressions",convert(temp_BoolExpression),false);
  ptm.ruleFinished(temp_BoolExpression);
} else {
  ptm.destroyNode();
}
}
)

((({skipCurrentToken = false;}'||'{if (!skipCurrentToken) {
  hasContent = true;
  ptm.createNode(((EObject)((EObject)((EObject)((EObject)((EObject)xtextfile.eContents().get(5)).eContents().get(1)).eContents().get(2)).eContents().get(0)).eContents().get(0)));
  ptm.ruleFinished(getLastToken());
}})
	|
({skipCurrentToken = false;}'&&'{if (!skipCurrentToken) {
  hasContent = true;
  ptm.createNode(((EObject)((EObject)((EObject)((EObject)((EObject)xtextfile.eContents().get(5)).eContents().get(1)).eContents().get(2)).eContents().get(0)).eContents().get(1)));
  ptm.ruleFinished(getLastToken());
}})
)

({ptm.createNode(((EObject)((EObject)((EObject)((EObject)xtextfile.eContents().get(5)).eContents().get(1)).eContents().get(2)).eContents().get(1)));}temp_BoolExpression=
ruleBoolExpression{if (temp_BoolExpression != null) {
  hasContent = true;
  ptm.setModelElement(temp_BoolExpression);
  factory.add($result,"expressions",convert(temp_BoolExpression),false);
  ptm.ruleFinished(temp_BoolExpression);
} else {
  ptm.destroyNode();
}
}
)
)*
)
 {if (!hasContent)
  $result = null;};
catch [RecognitionException re] {if (!hasContent)
    $result = null;
reportError(re);
recover(input,re);}

ruleBoolExpression returns [EObject result]
@init {boolean hasContent = false;}
:
			{
				$result = factory.create("", "BoolExpression");
			 }
(({ptm.createNode(((EObject)((EObject)((EObject)xtextfile.eContents().get(6)).eContents().get(1)).eContents().get(0)));}temp_State=
ruleState{if (temp_State != null) {
  hasContent = true;
  ptm.setModelElement(temp_State);
  factory.set($result,"op1",convert(temp_State),false);
  ptm.ruleFinished(temp_State);
} else {
  ptm.destroyNode();
}
}
)

({ptm.createNode(((EObject)((EObject)((EObject)xtextfile.eContents().get(6)).eContents().get(1)).eContents().get(1)));}temp_Operand=
ruleOperand{if (temp_Operand != null) {
  hasContent = true;
  ptm.setModelElement(temp_Operand);
  factory.set($result,"operand",convert(temp_Operand),false);
  ptm.ruleFinished(temp_Operand);
} else {
  ptm.destroyNode();
}
}
)

({skipCurrentToken = false;}
RULE_STRING{if (!skipCurrentToken) {
  hasContent = true;
  Token temp = getLastToken();
  ptm.createNode(((EObject)((EObject)((EObject)xtextfile.eContents().get(6)).eContents().get(1)).eContents().get(2)));
  factory.set($result,"op2",convert(temp),false);
  ptm.ruleFinished(temp);
}}
)
)
 {if (!hasContent)
  $result = null;};
catch [RecognitionException re] {if (!hasContent)
    $result = null;
reportError(re);
recover(input,re);}

ruleState returns [EObject result]
@init {boolean hasContent = false;}
:
			{
				$result = factory.create("", "State");
			 }
(({skipCurrentToken = false;}
RULE_ID
{if (!skipCurrentToken) {
  hasContent = true;
  Token temp = getLastToken();
  ptm.createNode(((EObject)((EObject)((EObject)xtextfile.eContents().get(7)).eContents().get(1)).eContents().get(0)));
  factory.add($result,"state",convert(temp),true);
  ptm.ruleFinished(temp);
}}
)
	|
(({skipCurrentToken = false;}'/'{if (!skipCurrentToken) {
  hasContent = true;
  ptm.createNode(((EObject)((EObject)((EObject)((EObject)xtextfile.eContents().get(7)).eContents().get(1)).eContents().get(1)).eContents().get(0)));
  ptm.ruleFinished(getLastToken());
}})

({skipCurrentToken = false;}
RULE_ID
{if (!skipCurrentToken) {
  hasContent = true;
  Token temp = getLastToken();
  ptm.createNode(((EObject)((EObject)((EObject)((EObject)xtextfile.eContents().get(7)).eContents().get(1)).eContents().get(1)).eContents().get(1)));
  factory.add($result,"state",convert(temp),true);
  ptm.ruleFinished(temp);
}}
)
)+
)
 {if (!hasContent)
  $result = null;};
catch [RecognitionException re] {if (!hasContent)
    $result = null;
reportError(re);
recover(input,re);}

ruleOperand returns [EObject result]
@init {boolean hasContent = false;}
:
			{
				$result = factory.create("", "Operand");
			 }
(({skipCurrentToken = false;}
'=='{if (!skipCurrentToken) {
  hasContent = true;
  Token temp = getLastToken();
  ptm.createNode(((EObject)((EObject)((EObject)xtextfile.eContents().get(8)).eContents().get(1)).eContents().get(0)));
  factory.set($result,"op",convert(temp),false);
  ptm.ruleFinished(temp);
}}
)
	|
({skipCurrentToken = false;}
'<='{if (!skipCurrentToken) {
  hasContent = true;
  Token temp = getLastToken();
  ptm.createNode(((EObject)((EObject)((EObject)xtextfile.eContents().get(8)).eContents().get(1)).eContents().get(1)));
  factory.set($result,"op",convert(temp),false);
  ptm.ruleFinished(temp);
}}
)
	|
({skipCurrentToken = false;}
'>='{if (!skipCurrentToken) {
  hasContent = true;
  Token temp = getLastToken();
  ptm.createNode(((EObject)((EObject)((EObject)xtextfile.eContents().get(8)).eContents().get(1)).eContents().get(2)));
  factory.set($result,"op",convert(temp),false);
  ptm.ruleFinished(temp);
}}
)
	|
({skipCurrentToken = false;}
'<'{if (!skipCurrentToken) {
  hasContent = true;
  Token temp = getLastToken();
  ptm.createNode(((EObject)((EObject)((EObject)xtextfile.eContents().get(8)).eContents().get(1)).eContents().get(3)));
  factory.set($result,"op",convert(temp),false);
  ptm.ruleFinished(temp);
}}
)
	|
({skipCurrentToken = false;}
'>'{if (!skipCurrentToken) {
  hasContent = true;
  Token temp = getLastToken();
  ptm.createNode(((EObject)((EObject)((EObject)xtextfile.eContents().get(8)).eContents().get(1)).eContents().get(4)));
  factory.set($result,"op",convert(temp),false);
  ptm.ruleFinished(temp);
}}
)
)
 {if (!hasContent)
  $result = null;};
catch [RecognitionException re] {if (!hasContent)
    $result = null;
reportError(re);
recover(input,re);}

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

