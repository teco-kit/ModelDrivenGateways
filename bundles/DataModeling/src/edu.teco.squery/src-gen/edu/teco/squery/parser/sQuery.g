grammar sQuery;
 options{backtrack=true; memoize=true;} 

@lexer::header {
package edu.teco.squery.parser;

import org.openarchitectureware.xtext.parser.ErrorMsg;
import org.openarchitectureware.xtext.parser.impl.AntlrUtil;

}

@parser::header {
package edu.teco.squery.parser;

import org.eclipse.emf.ecore.EObject;

import org.openarchitectureware.xtext.parser.impl.AntlrUtil;
import org.openarchitectureware.xtext.XtextFile;
import org.openarchitectureware.xtext.parser.impl.EcoreModelFactory;
import org.openarchitectureware.xtext.parser.ErrorMsg;
import org.openarchitectureware.xtext.parser.model.ParseTreeManager;
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
	private Token getNextToken() {
		return input.LT(1);
	}

	private int line() {
		Token t = getNextToken();
		if (t==null)
			return 1;
		return t.getLine();
	}

	private int start() {
		Token t = getNextToken();
		if (t==null)
			return 0;
		if (t instanceof CommonToken) {
			return ((CommonToken)t).getStartIndex();
		}
		return t.getTokenIndex();
	}

	private int end() {
		Token t = getLastToken();
		if (t==null)
			return 1;
		if (t instanceof CommonToken) {
			return ((CommonToken)t).getStopIndex()+1;
		}
		return t.getTokenIndex();
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
    private ParseTreeManager ptm = new ParseTreeManager();
	private XtextFile xtextfile = MetaModelRegistration.getXtextFile();
	
	{
			factory.addImport("automataMM", "http://www.teco.edu/automata");
		
	}

	public ParseTreeManager getResult() {
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
			ptm.ruleFinished(null, end());
		}

}


parse returns [Node r]:
	 result=ruleQueryModel EOF
{ptm.ruleFinished(result,end());$r = ptm.getCurrent();};

ruleQueryModel returns [EObject result] :
			{
				$result = factory.create("", "QueryModel");
				ptm.setModelElement($result);
			 }
(({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(1)).eContents().get(1)).eContents().get(0)),line(),start());}temp_automata=ruleAutomata {factory.set($result,"automata",convert(temp_automata),false); ptm.ruleFinished(temp_automata,end()); }
)

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(1)).eContents().get(1)).eContents().get(1)),line(),start());}temp_actions=ruleAction {factory.add($result,"actions",convert(temp_actions),false); ptm.ruleFinished(temp_actions,end()); }
)+
)
;

ruleAutomata returns [EObject result] :
			{
				$result = factory.create("", "Automata");
				ptm.setModelElement($result);
			 }
(({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(2)).eContents().get(1)).eContents().get(0)),line(),start());}'model'{ptm.ruleFinished(getLastToken(),end());})

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(2)).eContents().get(1)).eContents().get(1)),line(),start());}temp_model= RULE_STRING
 {factory.set($result,"model",convert(temp_model),false); ptm.ruleFinished(temp_model,end()); }
)

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(2)).eContents().get(1)).eContents().get(2)),line(),start());}';'{ptm.ruleFinished(getLastToken(),end());})
)
;

ruleAction returns [EObject result] :
			{
				$result = factory.create("", "Action");
				ptm.setModelElement($result);
			 }
(({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(3)).eContents().get(1)).eContents().get(0)),line(),start());}'action'{ptm.ruleFinished(getLastToken(),end());})

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(3)).eContents().get(1)).eContents().get(1)),line(),start());}temp_name=RULE_ID {factory.set($result,"name",convert(temp_name),false); ptm.ruleFinished(temp_name,end()); }
)

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(3)).eContents().get(1)).eContents().get(2)),line(),start());}'on'{ptm.ruleFinished(getLastToken(),end());})

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(3)).eContents().get(1)).eContents().get(3)),line(),start());}temp_query=ruleQuery {factory.set($result,"query",convert(temp_query),false); ptm.ruleFinished(temp_query,end()); }
)

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(3)).eContents().get(1)).eContents().get(4)),line(),start());}';'{ptm.ruleFinished(getLastToken(),end());})
)
;

ruleQuery returns [EObject result] :
			{
				$result = factory.create("", "Query");
				ptm.setModelElement($result);
			 }
(({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(4)).eContents().get(1)).eContents().get(0)),line(),start());}temp_st=ruleState {factory.set($result,"st",convert(temp_st),false); ptm.ruleFinished(temp_st,end()); }
)

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(4)).eContents().get(1)).eContents().get(1)),line(),start());}temp_condition=ruleCondition {factory.set($result,"condition",convert(temp_condition),false); ptm.ruleFinished(temp_condition,end()); }
)?
)
;

ruleCondition returns [EObject result] :
			{
				$result = factory.create("", "Condition");
				ptm.setModelElement($result);
			 }
(({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(5)).eContents().get(1)).eContents().get(0)),line(),start());}'where'{ptm.ruleFinished(getLastToken(),end());})

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(5)).eContents().get(1)).eContents().get(1)),line(),start());}temp_expressions=ruleBoolExpression {factory.add($result,"expressions",convert(temp_expressions),false); ptm.ruleFinished(temp_expressions,end()); }
)

((({ptm.invokeRule(((EObject)((EObject)((EObject)((EObject)((EObject)xtextfile.eContents().get(5)).eContents().get(1)).eContents().get(2)).eContents().get(0)).eContents().get(0)),line(),start());}'||'{ptm.ruleFinished(getLastToken(),end());})
	|
({ptm.invokeRule(((EObject)((EObject)((EObject)((EObject)((EObject)xtextfile.eContents().get(5)).eContents().get(1)).eContents().get(2)).eContents().get(0)).eContents().get(1)),line(),start());}'&&'{ptm.ruleFinished(getLastToken(),end());})
)

({ptm.invokeRule(((EObject)((EObject)((EObject)((EObject)xtextfile.eContents().get(5)).eContents().get(1)).eContents().get(2)).eContents().get(1)),line(),start());}temp_expressions=ruleBoolExpression {factory.add($result,"expressions",convert(temp_expressions),false); ptm.ruleFinished(temp_expressions,end()); }
)
)*
)
;

ruleBoolExpression returns [EObject result] :
			{
				$result = factory.create("", "BoolExpression");
				ptm.setModelElement($result);
			 }
(({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(6)).eContents().get(1)).eContents().get(0)),line(),start());}temp_op1=ruleState {factory.set($result,"op1",convert(temp_op1),false); ptm.ruleFinished(temp_op1,end()); }
)

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(6)).eContents().get(1)).eContents().get(1)),line(),start());}temp_operand=ruleOperand {factory.set($result,"operand",convert(temp_operand),false); ptm.ruleFinished(temp_operand,end()); }
)

({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(6)).eContents().get(1)).eContents().get(2)),line(),start());}temp_op2=RULE_STRING {factory.set($result,"op2",convert(temp_op2),false); ptm.ruleFinished(temp_op2,end()); }
)
)
;

ruleState returns [EObject result] :
			{
				$result = factory.create("", "State");
				ptm.setModelElement($result);
			 }
(({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(7)).eContents().get(1)).eContents().get(0)),line(),start());}temp_state=RULE_ID
 {factory.add($result,"state",convert(temp_state),true); ptm.ruleFinished(temp_state,end()); }
)
	|
(({ptm.invokeRule(((EObject)((EObject)((EObject)((EObject)xtextfile.eContents().get(7)).eContents().get(1)).eContents().get(1)).eContents().get(0)),line(),start());}'/'{ptm.ruleFinished(getLastToken(),end());})

({ptm.invokeRule(((EObject)((EObject)((EObject)((EObject)xtextfile.eContents().get(7)).eContents().get(1)).eContents().get(1)).eContents().get(1)),line(),start());}temp_state=RULE_ID
 {factory.add($result,"state",convert(temp_state),true); ptm.ruleFinished(temp_state,end()); }
)
)+
)
;

ruleOperand returns [EObject result] :
			{
				$result = factory.create("", "Operand");
				ptm.setModelElement($result);
			 }
(({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(8)).eContents().get(1)).eContents().get(0)),line(),start());}temp_op='==' {factory.set($result,"op",convert(temp_op),false); ptm.ruleFinished(temp_op,end()); }
)
	|
({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(8)).eContents().get(1)).eContents().get(1)),line(),start());}temp_op='<=' {factory.set($result,"op",convert(temp_op),false); ptm.ruleFinished(temp_op,end()); }
)
	|
({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(8)).eContents().get(1)).eContents().get(2)),line(),start());}temp_op='>=' {factory.set($result,"op",convert(temp_op),false); ptm.ruleFinished(temp_op,end()); }
)
	|
({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(8)).eContents().get(1)).eContents().get(3)),line(),start());}temp_op='<' {factory.set($result,"op",convert(temp_op),false); ptm.ruleFinished(temp_op,end()); }
)
	|
({ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(8)).eContents().get(1)).eContents().get(4)),line(),start());}temp_op='>' {factory.set($result,"op",convert(temp_op),false); ptm.ruleFinished(temp_op,end()); }
)
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

