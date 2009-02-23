// $ANTLR 3.0 ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g 2008-12-18 21:54:25

package edu.teco.squery.parser;

import org.eclipse.emf.ecore.EObject;

import org.openarchitectureware.xtext.parser.impl.AntlrUtil;
import org.openarchitectureware.xtext.XtextFile;
import org.openarchitectureware.xtext.parser.impl.EcoreModelFactory;
import org.openarchitectureware.xtext.parser.ErrorMsg;
import org.openarchitectureware.xtext.parser.model.ParseTreeManager;
import org.openarchitectureware.xtext.parser.parsetree.Node;

import edu.teco.squery.MetaModelRegistration;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
public class sQueryParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_STRING", "RULE_ID", "RULE_INT", "RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "'model'", "';'", "'action'", "'on'", "'where'", "'||'", "'&&'", "'/'", "'=='", "'<='", "'>='", "'<'", "'>'"
    };
    public static final int RULE_ID=5;
    public static final int RULE_STRING=4;
    public static final int RULE_INT=6;
    public static final int RULE_WS=7;
    public static final int RULE_SL_COMMENT=9;
    public static final int EOF=-1;
    public static final int RULE_ML_COMMENT=8;

        public sQueryParser(TokenStream input) {
            super(input);
            ruleMemo = new HashMap[19+1];
         }
        

    public String[] getTokenNames() { return tokenNames; }
    public String getGrammarFileName() { return "..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g"; }



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




    // $ANTLR start parse
    // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:122:1: parse returns [Node r] : result= ruleQueryModel EOF ;
    public Node parse() throws RecognitionException {
        Node r = null;
        int parse_StartIndex = input.index();
        EObject result = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 1) ) { return r; }
            // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:123:3: (result= ruleQueryModel EOF )
            // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:123:3: result= ruleQueryModel EOF
            {
            pushFollow(FOLLOW_ruleQueryModel_in_parse67);
            result=ruleQueryModel();
            _fsp--;
            if (failed) return r;
            match(input,EOF,FOLLOW_EOF_in_parse69); if (failed) return r;
            if ( backtracking==0 ) {
              ptm.ruleFinished(result,end());r = ptm.getCurrent();
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 1, parse_StartIndex); }
        }
        return r;
    }
    // $ANTLR end parse


    // $ANTLR start ruleQueryModel
    // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:126:1: ruleQueryModel returns [EObject result] : ( (temp_automata= ruleAutomata ) (temp_actions= ruleAction )+ ) ;
    public EObject ruleQueryModel() throws RecognitionException {
        EObject result = null;
        int ruleQueryModel_StartIndex = input.index();
        EObject temp_automata = null;

        EObject temp_actions = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 2) ) { return result; }
            // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:127:4: ( ( (temp_automata= ruleAutomata ) (temp_actions= ruleAction )+ ) )
            // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:127:4: ( (temp_automata= ruleAutomata ) (temp_actions= ruleAction )+ )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "QueryModel");
              				ptm.setModelElement(result);
              			 
            }
            // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:131:1: ( (temp_automata= ruleAutomata ) (temp_actions= ruleAction )+ )
            // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:131:2: (temp_automata= ruleAutomata ) (temp_actions= ruleAction )+
            {
            // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:131:2: (temp_automata= ruleAutomata )
            // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:131:3: temp_automata= ruleAutomata
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(1)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            pushFollow(FOLLOW_ruleAutomata_in_ruleQueryModel93);
            temp_automata=ruleAutomata();
            _fsp--;
            if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"automata",convert(temp_automata),false); ptm.ruleFinished(temp_automata,end()); 
            }

            }

            // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:134:1: (temp_actions= ruleAction )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==12) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:134:2: temp_actions= ruleAction
            	    {
            	    if ( backtracking==0 ) {
            	      ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(1)).eContents().get(1)).eContents().get(1)),line(),start());
            	    }
            	    pushFollow(FOLLOW_ruleAction_in_ruleQueryModel104);
            	    temp_actions=ruleAction();
            	    _fsp--;
            	    if (failed) return result;
            	    if ( backtracking==0 ) {
            	      factory.add(result,"actions",convert(temp_actions),false); ptm.ruleFinished(temp_actions,end()); 
            	    }

            	    }
            	    break;

            	default :
            	    if ( cnt1 >= 1 ) break loop1;
            	    if (backtracking>0) {failed=true; return result;}
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 2, ruleQueryModel_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleQueryModel


    // $ANTLR start ruleAutomata
    // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:139:1: ruleAutomata returns [EObject result] : ( ( 'model' ) (temp_model= RULE_STRING ) ( ';' ) ) ;
    public EObject ruleAutomata() throws RecognitionException {
        EObject result = null;
        int ruleAutomata_StartIndex = input.index();
        Token temp_model=null;

        try {
            if ( backtracking>0 && alreadyParsedRule(input, 3) ) { return result; }
            // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:140:4: ( ( ( 'model' ) (temp_model= RULE_STRING ) ( ';' ) ) )
            // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:140:4: ( ( 'model' ) (temp_model= RULE_STRING ) ( ';' ) )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "Automata");
              				ptm.setModelElement(result);
              			 
            }
            // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:144:1: ( ( 'model' ) (temp_model= RULE_STRING ) ( ';' ) )
            // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:144:2: ( 'model' ) (temp_model= RULE_STRING ) ( ';' )
            {
            // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:144:2: ( 'model' )
            // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:144:3: 'model'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(2)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            match(input,10,FOLLOW_10_in_ruleAutomata132); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:146:1: (temp_model= RULE_STRING )
            // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:146:2: temp_model= RULE_STRING
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(2)).eContents().get(1)).eContents().get(1)),line(),start());
            }
            temp_model=(Token)input.LT(1);
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleAutomata142); if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"model",convert(temp_model),false); ptm.ruleFinished(temp_model,end()); 
            }

            }

            // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:150:1: ( ';' )
            // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:150:2: ';'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(2)).eContents().get(1)).eContents().get(2)),line(),start());
            }
            match(input,11,FOLLOW_11_in_ruleAutomata152); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 3, ruleAutomata_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleAutomata


    // $ANTLR start ruleAction
    // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:154:1: ruleAction returns [EObject result] : ( ( 'action' ) (temp_name= RULE_ID ) ( 'on' ) (temp_query= ruleQuery ) ( ';' ) ) ;
    public EObject ruleAction() throws RecognitionException {
        EObject result = null;
        int ruleAction_StartIndex = input.index();
        Token temp_name=null;
        EObject temp_query = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 4) ) { return result; }
            // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:155:4: ( ( ( 'action' ) (temp_name= RULE_ID ) ( 'on' ) (temp_query= ruleQuery ) ( ';' ) ) )
            // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:155:4: ( ( 'action' ) (temp_name= RULE_ID ) ( 'on' ) (temp_query= ruleQuery ) ( ';' ) )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "Action");
              				ptm.setModelElement(result);
              			 
            }
            // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:159:1: ( ( 'action' ) (temp_name= RULE_ID ) ( 'on' ) (temp_query= ruleQuery ) ( ';' ) )
            // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:159:2: ( 'action' ) (temp_name= RULE_ID ) ( 'on' ) (temp_query= ruleQuery ) ( ';' )
            {
            // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:159:2: ( 'action' )
            // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:159:3: 'action'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(3)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            match(input,12,FOLLOW_12_in_ruleAction177); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:161:1: (temp_name= RULE_ID )
            // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:161:2: temp_name= RULE_ID
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(3)).eContents().get(1)).eContents().get(1)),line(),start());
            }
            temp_name=(Token)input.LT(1);
            match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleAction186); if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"name",convert(temp_name),false); ptm.ruleFinished(temp_name,end()); 
            }

            }

            // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:164:1: ( 'on' )
            // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:164:2: 'on'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(3)).eContents().get(1)).eContents().get(2)),line(),start());
            }
            match(input,13,FOLLOW_13_in_ruleAction195); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:166:1: (temp_query= ruleQuery )
            // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:166:2: temp_query= ruleQuery
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(3)).eContents().get(1)).eContents().get(3)),line(),start());
            }
            pushFollow(FOLLOW_ruleQuery_in_ruleAction204);
            temp_query=ruleQuery();
            _fsp--;
            if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"query",convert(temp_query),false); ptm.ruleFinished(temp_query,end()); 
            }

            }

            // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:169:1: ( ';' )
            // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:169:2: ';'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(3)).eContents().get(1)).eContents().get(4)),line(),start());
            }
            match(input,11,FOLLOW_11_in_ruleAction213); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 4, ruleAction_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleAction


    // $ANTLR start ruleQuery
    // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:173:1: ruleQuery returns [EObject result] : ( (temp_st= ruleState ) (temp_condition= ruleCondition )? ) ;
    public EObject ruleQuery() throws RecognitionException {
        EObject result = null;
        int ruleQuery_StartIndex = input.index();
        EObject temp_st = null;

        EObject temp_condition = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 5) ) { return result; }
            // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:174:4: ( ( (temp_st= ruleState ) (temp_condition= ruleCondition )? ) )
            // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:174:4: ( (temp_st= ruleState ) (temp_condition= ruleCondition )? )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "Query");
              				ptm.setModelElement(result);
              			 
            }
            // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:178:1: ( (temp_st= ruleState ) (temp_condition= ruleCondition )? )
            // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:178:2: (temp_st= ruleState ) (temp_condition= ruleCondition )?
            {
            // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:178:2: (temp_st= ruleState )
            // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:178:3: temp_st= ruleState
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(4)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            pushFollow(FOLLOW_ruleState_in_ruleQuery240);
            temp_st=ruleState();
            _fsp--;
            if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"st",convert(temp_st),false); ptm.ruleFinished(temp_st,end()); 
            }

            }

            // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:181:1: (temp_condition= ruleCondition )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==14) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:181:2: temp_condition= ruleCondition
                    {
                    if ( backtracking==0 ) {
                      ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(4)).eContents().get(1)).eContents().get(1)),line(),start());
                    }
                    pushFollow(FOLLOW_ruleCondition_in_ruleQuery251);
                    temp_condition=ruleCondition();
                    _fsp--;
                    if (failed) return result;
                    if ( backtracking==0 ) {
                      factory.set(result,"condition",convert(temp_condition),false); ptm.ruleFinished(temp_condition,end()); 
                    }

                    }
                    break;

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 5, ruleQuery_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleQuery


    // $ANTLR start ruleCondition
    // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:186:1: ruleCondition returns [EObject result] : ( ( 'where' ) (temp_expressions= ruleBoolExpression ) ( ( ( '||' ) | ( '&&' ) ) (temp_expressions= ruleBoolExpression ) )* ) ;
    public EObject ruleCondition() throws RecognitionException {
        EObject result = null;
        int ruleCondition_StartIndex = input.index();
        EObject temp_expressions = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 6) ) { return result; }
            // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:187:4: ( ( ( 'where' ) (temp_expressions= ruleBoolExpression ) ( ( ( '||' ) | ( '&&' ) ) (temp_expressions= ruleBoolExpression ) )* ) )
            // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:187:4: ( ( 'where' ) (temp_expressions= ruleBoolExpression ) ( ( ( '||' ) | ( '&&' ) ) (temp_expressions= ruleBoolExpression ) )* )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "Condition");
              				ptm.setModelElement(result);
              			 
            }
            // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:191:1: ( ( 'where' ) (temp_expressions= ruleBoolExpression ) ( ( ( '||' ) | ( '&&' ) ) (temp_expressions= ruleBoolExpression ) )* )
            // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:191:2: ( 'where' ) (temp_expressions= ruleBoolExpression ) ( ( ( '||' ) | ( '&&' ) ) (temp_expressions= ruleBoolExpression ) )*
            {
            // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:191:2: ( 'where' )
            // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:191:3: 'where'
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(5)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            match(input,14,FOLLOW_14_in_ruleCondition279); if (failed) return result;
            if ( backtracking==0 ) {
              ptm.ruleFinished(getLastToken(),end());
            }

            }

            // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:193:1: (temp_expressions= ruleBoolExpression )
            // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:193:2: temp_expressions= ruleBoolExpression
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(5)).eContents().get(1)).eContents().get(1)),line(),start());
            }
            pushFollow(FOLLOW_ruleBoolExpression_in_ruleCondition288);
            temp_expressions=ruleBoolExpression();
            _fsp--;
            if (failed) return result;
            if ( backtracking==0 ) {
              factory.add(result,"expressions",convert(temp_expressions),false); ptm.ruleFinished(temp_expressions,end()); 
            }

            }

            // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:196:1: ( ( ( '||' ) | ( '&&' ) ) (temp_expressions= ruleBoolExpression ) )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0>=15 && LA4_0<=16)) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:196:2: ( ( '||' ) | ( '&&' ) ) (temp_expressions= ruleBoolExpression )
            	    {
            	    // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:196:2: ( ( '||' ) | ( '&&' ) )
            	    int alt3=2;
            	    int LA3_0 = input.LA(1);

            	    if ( (LA3_0==15) ) {
            	        alt3=1;
            	    }
            	    else if ( (LA3_0==16) ) {
            	        alt3=2;
            	    }
            	    else {
            	        if (backtracking>0) {failed=true; return result;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("196:2: ( ( '||' ) | ( '&&' ) )", 3, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt3) {
            	        case 1 :
            	            // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:196:3: ( '||' )
            	            {
            	            // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:196:3: ( '||' )
            	            // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:196:4: '||'
            	            {
            	            if ( backtracking==0 ) {
            	              ptm.invokeRule(((EObject)((EObject)((EObject)((EObject)((EObject)xtextfile.eContents().get(5)).eContents().get(1)).eContents().get(2)).eContents().get(0)).eContents().get(0)),line(),start());
            	            }
            	            match(input,15,FOLLOW_15_in_ruleCondition299); if (failed) return result;
            	            if ( backtracking==0 ) {
            	              ptm.ruleFinished(getLastToken(),end());
            	            }

            	            }


            	            }
            	            break;
            	        case 2 :
            	            // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:198:1: ( '&&' )
            	            {
            	            // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:198:1: ( '&&' )
            	            // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:198:2: '&&'
            	            {
            	            if ( backtracking==0 ) {
            	              ptm.invokeRule(((EObject)((EObject)((EObject)((EObject)((EObject)xtextfile.eContents().get(5)).eContents().get(1)).eContents().get(2)).eContents().get(0)).eContents().get(1)),line(),start());
            	            }
            	            match(input,16,FOLLOW_16_in_ruleCondition308); if (failed) return result;
            	            if ( backtracking==0 ) {
            	              ptm.ruleFinished(getLastToken(),end());
            	            }

            	            }


            	            }
            	            break;

            	    }

            	    // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:201:1: (temp_expressions= ruleBoolExpression )
            	    // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:201:2: temp_expressions= ruleBoolExpression
            	    {
            	    if ( backtracking==0 ) {
            	      ptm.invokeRule(((EObject)((EObject)((EObject)((EObject)xtextfile.eContents().get(5)).eContents().get(1)).eContents().get(2)).eContents().get(1)),line(),start());
            	    }
            	    pushFollow(FOLLOW_ruleBoolExpression_in_ruleCondition319);
            	    temp_expressions=ruleBoolExpression();
            	    _fsp--;
            	    if (failed) return result;
            	    if ( backtracking==0 ) {
            	      factory.add(result,"expressions",convert(temp_expressions),false); ptm.ruleFinished(temp_expressions,end()); 
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 6, ruleCondition_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleCondition


    // $ANTLR start ruleBoolExpression
    // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:207:1: ruleBoolExpression returns [EObject result] : ( (temp_op1= ruleState ) (temp_operand= ruleOperand ) (temp_op2= RULE_STRING ) ) ;
    public EObject ruleBoolExpression() throws RecognitionException {
        EObject result = null;
        int ruleBoolExpression_StartIndex = input.index();
        Token temp_op2=null;
        EObject temp_op1 = null;

        EObject temp_operand = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 7) ) { return result; }
            // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:208:4: ( ( (temp_op1= ruleState ) (temp_operand= ruleOperand ) (temp_op2= RULE_STRING ) ) )
            // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:208:4: ( (temp_op1= ruleState ) (temp_operand= ruleOperand ) (temp_op2= RULE_STRING ) )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "BoolExpression");
              				ptm.setModelElement(result);
              			 
            }
            // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:212:1: ( (temp_op1= ruleState ) (temp_operand= ruleOperand ) (temp_op2= RULE_STRING ) )
            // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:212:2: (temp_op1= ruleState ) (temp_operand= ruleOperand ) (temp_op2= RULE_STRING )
            {
            // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:212:2: (temp_op1= ruleState )
            // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:212:3: temp_op1= ruleState
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(6)).eContents().get(1)).eContents().get(0)),line(),start());
            }
            pushFollow(FOLLOW_ruleState_in_ruleBoolExpression351);
            temp_op1=ruleState();
            _fsp--;
            if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"op1",convert(temp_op1),false); ptm.ruleFinished(temp_op1,end()); 
            }

            }

            // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:215:1: (temp_operand= ruleOperand )
            // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:215:2: temp_operand= ruleOperand
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(6)).eContents().get(1)).eContents().get(1)),line(),start());
            }
            pushFollow(FOLLOW_ruleOperand_in_ruleBoolExpression362);
            temp_operand=ruleOperand();
            _fsp--;
            if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"operand",convert(temp_operand),false); ptm.ruleFinished(temp_operand,end()); 
            }

            }

            // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:218:1: (temp_op2= RULE_STRING )
            // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:218:2: temp_op2= RULE_STRING
            {
            if ( backtracking==0 ) {
              ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(6)).eContents().get(1)).eContents().get(2)),line(),start());
            }
            temp_op2=(Token)input.LT(1);
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleBoolExpression373); if (failed) return result;
            if ( backtracking==0 ) {
              factory.set(result,"op2",convert(temp_op2),false); ptm.ruleFinished(temp_op2,end()); 
            }

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 7, ruleBoolExpression_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleBoolExpression


    // $ANTLR start ruleState
    // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:223:1: ruleState returns [EObject result] : ( (temp_state= RULE_ID ) | ( ( '/' ) (temp_state= RULE_ID ) )+ ) ;
    public EObject ruleState() throws RecognitionException {
        EObject result = null;
        int ruleState_StartIndex = input.index();
        Token temp_state=null;

        try {
            if ( backtracking>0 && alreadyParsedRule(input, 8) ) { return result; }
            // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:224:4: ( ( (temp_state= RULE_ID ) | ( ( '/' ) (temp_state= RULE_ID ) )+ ) )
            // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:224:4: ( (temp_state= RULE_ID ) | ( ( '/' ) (temp_state= RULE_ID ) )+ )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "State");
              				ptm.setModelElement(result);
              			 
            }
            // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:228:1: ( (temp_state= RULE_ID ) | ( ( '/' ) (temp_state= RULE_ID ) )+ )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==RULE_ID) ) {
                alt6=1;
            }
            else if ( (LA6_0==17) ) {
                alt6=2;
            }
            else {
                if (backtracking>0) {failed=true; return result;}
                NoViableAltException nvae =
                    new NoViableAltException("228:1: ( (temp_state= RULE_ID ) | ( ( '/' ) (temp_state= RULE_ID ) )+ )", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:228:2: (temp_state= RULE_ID )
                    {
                    // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:228:2: (temp_state= RULE_ID )
                    // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:228:3: temp_state= RULE_ID
                    {
                    if ( backtracking==0 ) {
                      ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(7)).eContents().get(1)).eContents().get(0)),line(),start());
                    }
                    temp_state=(Token)input.LT(1);
                    match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleState402); if (failed) return result;
                    if ( backtracking==0 ) {
                      factory.add(result,"state",convert(temp_state),true); ptm.ruleFinished(temp_state,end()); 
                    }

                    }


                    }
                    break;
                case 2 :
                    // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:232:1: ( ( '/' ) (temp_state= RULE_ID ) )+
                    {
                    // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:232:1: ( ( '/' ) (temp_state= RULE_ID ) )+
                    int cnt5=0;
                    loop5:
                    do {
                        int alt5=2;
                        int LA5_0 = input.LA(1);

                        if ( (LA5_0==17) ) {
                            alt5=1;
                        }


                        switch (alt5) {
                    	case 1 :
                    	    // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:232:2: ( '/' ) (temp_state= RULE_ID )
                    	    {
                    	    // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:232:2: ( '/' )
                    	    // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:232:3: '/'
                    	    {
                    	    if ( backtracking==0 ) {
                    	      ptm.invokeRule(((EObject)((EObject)((EObject)((EObject)xtextfile.eContents().get(7)).eContents().get(1)).eContents().get(1)).eContents().get(0)),line(),start());
                    	    }
                    	    match(input,17,FOLLOW_17_in_ruleState415); if (failed) return result;
                    	    if ( backtracking==0 ) {
                    	      ptm.ruleFinished(getLastToken(),end());
                    	    }

                    	    }

                    	    // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:234:1: (temp_state= RULE_ID )
                    	    // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:234:2: temp_state= RULE_ID
                    	    {
                    	    if ( backtracking==0 ) {
                    	      ptm.invokeRule(((EObject)((EObject)((EObject)((EObject)xtextfile.eContents().get(7)).eContents().get(1)).eContents().get(1)).eContents().get(1)),line(),start());
                    	    }
                    	    temp_state=(Token)input.LT(1);
                    	    match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleState424); if (failed) return result;
                    	    if ( backtracking==0 ) {
                    	      factory.add(result,"state",convert(temp_state),true); ptm.ruleFinished(temp_state,end()); 
                    	    }

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt5 >= 1 ) break loop5;
                    	    if (backtracking>0) {failed=true; return result;}
                                EarlyExitException eee =
                                    new EarlyExitException(5, input);
                                throw eee;
                        }
                        cnt5++;
                    } while (true);


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 8, ruleState_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleState


    // $ANTLR start ruleOperand
    // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:241:1: ruleOperand returns [EObject result] : ( (temp_op= '==' ) | (temp_op= '<=' ) | (temp_op= '>=' ) | (temp_op= '<' ) | (temp_op= '>' ) ) ;
    public EObject ruleOperand() throws RecognitionException {
        EObject result = null;
        int ruleOperand_StartIndex = input.index();
        Token temp_op=null;

        try {
            if ( backtracking>0 && alreadyParsedRule(input, 9) ) { return result; }
            // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:242:4: ( ( (temp_op= '==' ) | (temp_op= '<=' ) | (temp_op= '>=' ) | (temp_op= '<' ) | (temp_op= '>' ) ) )
            // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:242:4: ( (temp_op= '==' ) | (temp_op= '<=' ) | (temp_op= '>=' ) | (temp_op= '<' ) | (temp_op= '>' ) )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "Operand");
              				ptm.setModelElement(result);
              			 
            }
            // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:246:1: ( (temp_op= '==' ) | (temp_op= '<=' ) | (temp_op= '>=' ) | (temp_op= '<' ) | (temp_op= '>' ) )
            int alt7=5;
            switch ( input.LA(1) ) {
            case 18:
                {
                alt7=1;
                }
                break;
            case 19:
                {
                alt7=2;
                }
                break;
            case 20:
                {
                alt7=3;
                }
                break;
            case 21:
                {
                alt7=4;
                }
                break;
            case 22:
                {
                alt7=5;
                }
                break;
            default:
                if (backtracking>0) {failed=true; return result;}
                NoViableAltException nvae =
                    new NoViableAltException("246:1: ( (temp_op= '==' ) | (temp_op= '<=' ) | (temp_op= '>=' ) | (temp_op= '<' ) | (temp_op= '>' ) )", 7, 0, input);

                throw nvae;
            }

            switch (alt7) {
                case 1 :
                    // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:246:2: (temp_op= '==' )
                    {
                    // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:246:2: (temp_op= '==' )
                    // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:246:3: temp_op= '=='
                    {
                    if ( backtracking==0 ) {
                      ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(8)).eContents().get(1)).eContents().get(0)),line(),start());
                    }
                    temp_op=(Token)input.LT(1);
                    match(input,18,FOLLOW_18_in_ruleOperand457); if (failed) return result;
                    if ( backtracking==0 ) {
                      factory.set(result,"op",convert(temp_op),false); ptm.ruleFinished(temp_op,end()); 
                    }

                    }


                    }
                    break;
                case 2 :
                    // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:249:1: (temp_op= '<=' )
                    {
                    // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:249:1: (temp_op= '<=' )
                    // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:249:2: temp_op= '<='
                    {
                    if ( backtracking==0 ) {
                      ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(8)).eContents().get(1)).eContents().get(1)),line(),start());
                    }
                    temp_op=(Token)input.LT(1);
                    match(input,19,FOLLOW_19_in_ruleOperand470); if (failed) return result;
                    if ( backtracking==0 ) {
                      factory.set(result,"op",convert(temp_op),false); ptm.ruleFinished(temp_op,end()); 
                    }

                    }


                    }
                    break;
                case 3 :
                    // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:252:1: (temp_op= '>=' )
                    {
                    // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:252:1: (temp_op= '>=' )
                    // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:252:2: temp_op= '>='
                    {
                    if ( backtracking==0 ) {
                      ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(8)).eContents().get(1)).eContents().get(2)),line(),start());
                    }
                    temp_op=(Token)input.LT(1);
                    match(input,20,FOLLOW_20_in_ruleOperand483); if (failed) return result;
                    if ( backtracking==0 ) {
                      factory.set(result,"op",convert(temp_op),false); ptm.ruleFinished(temp_op,end()); 
                    }

                    }


                    }
                    break;
                case 4 :
                    // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:255:1: (temp_op= '<' )
                    {
                    // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:255:1: (temp_op= '<' )
                    // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:255:2: temp_op= '<'
                    {
                    if ( backtracking==0 ) {
                      ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(8)).eContents().get(1)).eContents().get(3)),line(),start());
                    }
                    temp_op=(Token)input.LT(1);
                    match(input,21,FOLLOW_21_in_ruleOperand496); if (failed) return result;
                    if ( backtracking==0 ) {
                      factory.set(result,"op",convert(temp_op),false); ptm.ruleFinished(temp_op,end()); 
                    }

                    }


                    }
                    break;
                case 5 :
                    // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:258:1: (temp_op= '>' )
                    {
                    // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:258:1: (temp_op= '>' )
                    // ..//edu.teco.squery/src-gen//edu/teco/squery/parser/sQuery.g:258:2: temp_op= '>'
                    {
                    if ( backtracking==0 ) {
                      ptm.invokeRule(((EObject)((EObject)((EObject)xtextfile.eContents().get(8)).eContents().get(1)).eContents().get(4)),line(),start());
                    }
                    temp_op=(Token)input.LT(1);
                    match(input,22,FOLLOW_22_in_ruleOperand509); if (failed) return result;
                    if ( backtracking==0 ) {
                      factory.set(result,"op",convert(temp_op),false); ptm.ruleFinished(temp_op,end()); 
                    }

                    }


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( backtracking>0 ) { memoize(input, 9, ruleOperand_StartIndex); }
        }
        return result;
    }
    // $ANTLR end ruleOperand


 

    public static final BitSet FOLLOW_ruleQueryModel_in_parse67 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_parse69 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAutomata_in_ruleQueryModel93 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_ruleAction_in_ruleQueryModel104 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_10_in_ruleAutomata132 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleAutomata142 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_ruleAutomata152 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_ruleAction177 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleAction186 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleAction195 = new BitSet(new long[]{0x0000000000020020L});
    public static final BitSet FOLLOW_ruleQuery_in_ruleAction204 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_ruleAction213 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleState_in_ruleQuery240 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_ruleCondition_in_ruleQuery251 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_ruleCondition279 = new BitSet(new long[]{0x0000000000020020L});
    public static final BitSet FOLLOW_ruleBoolExpression_in_ruleCondition288 = new BitSet(new long[]{0x0000000000018002L});
    public static final BitSet FOLLOW_15_in_ruleCondition299 = new BitSet(new long[]{0x0000000000020020L});
    public static final BitSet FOLLOW_16_in_ruleCondition308 = new BitSet(new long[]{0x0000000000020020L});
    public static final BitSet FOLLOW_ruleBoolExpression_in_ruleCondition319 = new BitSet(new long[]{0x0000000000018002L});
    public static final BitSet FOLLOW_ruleState_in_ruleBoolExpression351 = new BitSet(new long[]{0x00000000007C0000L});
    public static final BitSet FOLLOW_ruleOperand_in_ruleBoolExpression362 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleBoolExpression373 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleState402 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_ruleState415 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleState424 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_18_in_ruleOperand457 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_ruleOperand470 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_ruleOperand483 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_ruleOperand496 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_ruleOperand509 = new BitSet(new long[]{0x0000000000000002L});

}