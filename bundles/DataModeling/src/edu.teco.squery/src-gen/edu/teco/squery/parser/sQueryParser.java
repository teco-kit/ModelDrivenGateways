// $ANTLR 3.0 ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g 2009-02-26 08:57:37

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
    public String getGrammarFileName() { return "..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g"; }



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



    // $ANTLR start parse
    // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:102:1: parse returns [Node r] : result= ruleQueryModel EOF ;
    public Node parse() throws RecognitionException {
        Node r = null;
        int parse_StartIndex = input.index();
        EObject result = null;


        try {
            if ( backtracking>0 && alreadyParsedRule(input, 1) ) { return r; }
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:103:3: (result= ruleQueryModel EOF )
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:103:3: result= ruleQueryModel EOF
            {
            pushFollow(FOLLOW_ruleQueryModel_in_parse67);
            result=ruleQueryModel();
            _fsp--;
            if (failed) return r;
            if ( backtracking==0 ) {

              if (result != null)
                ptm.setModelElement(result);
              r = ptm.getCurrent();
              ptm.ruleFinished(result);
            }
            match(input,EOF,FOLLOW_EOF_in_parse78); if (failed) return r;

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
    // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:113:1: ruleQueryModel returns [EObject result] : ( (temp_Automata= ruleAutomata ) (temp_Action= ruleAction )+ ) ;
    public EObject ruleQueryModel() throws RecognitionException {
        EObject result = null;
        int ruleQueryModel_StartIndex = input.index();
        EObject temp_Automata = null;

        EObject temp_Action = null;


        boolean hasContent = false;
        try {
            if ( backtracking>0 && alreadyParsedRule(input, 2) ) { return result; }
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:116:4: ( ( (temp_Automata= ruleAutomata ) (temp_Action= ruleAction )+ ) )
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:116:4: ( (temp_Automata= ruleAutomata ) (temp_Action= ruleAction )+ )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "QueryModel");
              			 
            }
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:119:1: ( (temp_Automata= ruleAutomata ) (temp_Action= ruleAction )+ )
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:119:2: (temp_Automata= ruleAutomata ) (temp_Action= ruleAction )+
            {
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:119:2: (temp_Automata= ruleAutomata )
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:119:3: temp_Automata= ruleAutomata
            {
            if ( backtracking==0 ) {
              ptm.createNode(((EObject)((EObject)((EObject)xtextfile.eContents().get(1)).eContents().get(1)).eContents().get(0)));
            }
            pushFollow(FOLLOW_ruleAutomata_in_ruleQueryModel107);
            temp_Automata=ruleAutomata();
            _fsp--;
            if (failed) return result;
            if ( backtracking==0 ) {
              if (temp_Automata != null) {
                hasContent = true;
                ptm.setModelElement(temp_Automata);
                factory.set(result,"automata",convert(temp_Automata),false);
                ptm.ruleFinished(temp_Automata);
              } else {
                ptm.destroyNode();
              }

            }

            }

            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:131:1: (temp_Action= ruleAction )+
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
            	    // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:131:2: temp_Action= ruleAction
            	    {
            	    if ( backtracking==0 ) {
            	      ptm.createNode(((EObject)((EObject)((EObject)xtextfile.eContents().get(1)).eContents().get(1)).eContents().get(1)));
            	    }
            	    pushFollow(FOLLOW_ruleAction_in_ruleQueryModel118);
            	    temp_Action=ruleAction();
            	    _fsp--;
            	    if (failed) return result;
            	    if ( backtracking==0 ) {
            	      if (temp_Action != null) {
            	        hasContent = true;
            	        ptm.setModelElement(temp_Action);
            	        factory.add(result,"actions",convert(temp_Action),false);
            	        ptm.ruleFinished(temp_Action);
            	      } else {
            	        ptm.destroyNode();
            	      }

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

            if ( backtracking==0 ) {
              if (!hasContent)
                result = null;
            }

            }

        }
        catch (RecognitionException re) {
            if (!hasContent)
                result = null;
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
    // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:150:1: ruleAutomata returns [EObject result] : ( ( 'model' ) ( RULE_STRING ) ( ';' ) ) ;
    public EObject ruleAutomata() throws RecognitionException {
        EObject result = null;
        int ruleAutomata_StartIndex = input.index();
        boolean hasContent = false;
        try {
            if ( backtracking>0 && alreadyParsedRule(input, 3) ) { return result; }
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:153:4: ( ( ( 'model' ) ( RULE_STRING ) ( ';' ) ) )
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:153:4: ( ( 'model' ) ( RULE_STRING ) ( ';' ) )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "Automata");
              			 
            }
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:156:1: ( ( 'model' ) ( RULE_STRING ) ( ';' ) )
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:156:2: ( 'model' ) ( RULE_STRING ) ( ';' )
            {
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:156:2: ( 'model' )
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:156:3: 'model'
            {
            if ( backtracking==0 ) {
              skipCurrentToken = false;
            }
            match(input,10,FOLLOW_10_in_ruleAutomata158); if (failed) return result;
            if ( backtracking==0 ) {
              if (!skipCurrentToken) {
                hasContent = true;
                ptm.createNode(((EObject)((EObject)((EObject)xtextfile.eContents().get(2)).eContents().get(1)).eContents().get(0)));
                ptm.ruleFinished(getLastToken());
              }
            }

            }

            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:162:1: ( RULE_STRING )
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:162:2: RULE_STRING
            {
            if ( backtracking==0 ) {
              skipCurrentToken = false;
            }
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleAutomata167); if (failed) return result;
            if ( backtracking==0 ) {
              if (!skipCurrentToken) {
                hasContent = true;
                Token temp = getLastToken();
                ptm.createNode(((EObject)((EObject)((EObject)xtextfile.eContents().get(2)).eContents().get(1)).eContents().get(1)));
                factory.set(result,"model",convert(temp),false);
                ptm.ruleFinished(temp);
              }
            }

            }

            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:173:1: ( ';' )
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:173:2: ';'
            {
            if ( backtracking==0 ) {
              skipCurrentToken = false;
            }
            match(input,11,FOLLOW_11_in_ruleAutomata176); if (failed) return result;
            if ( backtracking==0 ) {
              if (!skipCurrentToken) {
                hasContent = true;
                ptm.createNode(((EObject)((EObject)((EObject)xtextfile.eContents().get(2)).eContents().get(1)).eContents().get(2)));
                ptm.ruleFinished(getLastToken());
              }
            }

            }


            }

            if ( backtracking==0 ) {
              if (!hasContent)
                result = null;
            }

            }

        }
        catch (RecognitionException re) {
            if (!hasContent)
                result = null;
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
    // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:186:1: ruleAction returns [EObject result] : ( ( 'action' ) ( RULE_ID ) ( 'on' ) (temp_Query= ruleQuery ) ( ';' ) ) ;
    public EObject ruleAction() throws RecognitionException {
        EObject result = null;
        int ruleAction_StartIndex = input.index();
        EObject temp_Query = null;


        boolean hasContent = false;
        try {
            if ( backtracking>0 && alreadyParsedRule(input, 4) ) { return result; }
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:189:4: ( ( ( 'action' ) ( RULE_ID ) ( 'on' ) (temp_Query= ruleQuery ) ( ';' ) ) )
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:189:4: ( ( 'action' ) ( RULE_ID ) ( 'on' ) (temp_Query= ruleQuery ) ( ';' ) )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "Action");
              			 
            }
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:192:1: ( ( 'action' ) ( RULE_ID ) ( 'on' ) (temp_Query= ruleQuery ) ( ';' ) )
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:192:2: ( 'action' ) ( RULE_ID ) ( 'on' ) (temp_Query= ruleQuery ) ( ';' )
            {
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:192:2: ( 'action' )
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:192:3: 'action'
            {
            if ( backtracking==0 ) {
              skipCurrentToken = false;
            }
            match(input,12,FOLLOW_12_in_ruleAction214); if (failed) return result;
            if ( backtracking==0 ) {
              if (!skipCurrentToken) {
                hasContent = true;
                ptm.createNode(((EObject)((EObject)((EObject)xtextfile.eContents().get(3)).eContents().get(1)).eContents().get(0)));
                ptm.ruleFinished(getLastToken());
              }
            }

            }

            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:198:1: ( RULE_ID )
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:198:2: RULE_ID
            {
            if ( backtracking==0 ) {
              skipCurrentToken = false;
            }
            match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleAction222); if (failed) return result;
            if ( backtracking==0 ) {
              if (!skipCurrentToken) {
                hasContent = true;
                Token temp = getLastToken();
                ptm.createNode(((EObject)((EObject)((EObject)xtextfile.eContents().get(3)).eContents().get(1)).eContents().get(1)));
                factory.set(result,"name",convert(temp),false);
                ptm.ruleFinished(temp);
              }
            }

            }

            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:208:1: ( 'on' )
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:208:2: 'on'
            {
            if ( backtracking==0 ) {
              skipCurrentToken = false;
            }
            match(input,13,FOLLOW_13_in_ruleAction230); if (failed) return result;
            if ( backtracking==0 ) {
              if (!skipCurrentToken) {
                hasContent = true;
                ptm.createNode(((EObject)((EObject)((EObject)xtextfile.eContents().get(3)).eContents().get(1)).eContents().get(2)));
                ptm.ruleFinished(getLastToken());
              }
            }

            }

            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:214:1: (temp_Query= ruleQuery )
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:214:2: temp_Query= ruleQuery
            {
            if ( backtracking==0 ) {
              ptm.createNode(((EObject)((EObject)((EObject)xtextfile.eContents().get(3)).eContents().get(1)).eContents().get(3)));
            }
            pushFollow(FOLLOW_ruleQuery_in_ruleAction240);
            temp_Query=ruleQuery();
            _fsp--;
            if (failed) return result;
            if ( backtracking==0 ) {
              if (temp_Query != null) {
                hasContent = true;
                ptm.setModelElement(temp_Query);
                factory.set(result,"query",convert(temp_Query),false);
                ptm.ruleFinished(temp_Query);
              } else {
                ptm.destroyNode();
              }

            }

            }

            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:226:1: ( ';' )
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:226:2: ';'
            {
            if ( backtracking==0 ) {
              skipCurrentToken = false;
            }
            match(input,11,FOLLOW_11_in_ruleAction248); if (failed) return result;
            if ( backtracking==0 ) {
              if (!skipCurrentToken) {
                hasContent = true;
                ptm.createNode(((EObject)((EObject)((EObject)xtextfile.eContents().get(3)).eContents().get(1)).eContents().get(4)));
                ptm.ruleFinished(getLastToken());
              }
            }

            }


            }

            if ( backtracking==0 ) {
              if (!hasContent)
                result = null;
            }

            }

        }
        catch (RecognitionException re) {
            if (!hasContent)
                result = null;
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
    // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:239:1: ruleQuery returns [EObject result] : ( (temp_State= ruleState ) (temp_Condition= ruleCondition )? ) ;
    public EObject ruleQuery() throws RecognitionException {
        EObject result = null;
        int ruleQuery_StartIndex = input.index();
        EObject temp_State = null;

        EObject temp_Condition = null;


        boolean hasContent = false;
        try {
            if ( backtracking>0 && alreadyParsedRule(input, 5) ) { return result; }
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:242:4: ( ( (temp_State= ruleState ) (temp_Condition= ruleCondition )? ) )
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:242:4: ( (temp_State= ruleState ) (temp_Condition= ruleCondition )? )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "Query");
              			 
            }
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:245:1: ( (temp_State= ruleState ) (temp_Condition= ruleCondition )? )
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:245:2: (temp_State= ruleState ) (temp_Condition= ruleCondition )?
            {
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:245:2: (temp_State= ruleState )
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:245:3: temp_State= ruleState
            {
            if ( backtracking==0 ) {
              ptm.createNode(((EObject)((EObject)((EObject)xtextfile.eContents().get(4)).eContents().get(1)).eContents().get(0)));
            }
            pushFollow(FOLLOW_ruleState_in_ruleQuery289);
            temp_State=ruleState();
            _fsp--;
            if (failed) return result;
            if ( backtracking==0 ) {
              if (temp_State != null) {
                hasContent = true;
                ptm.setModelElement(temp_State);
                factory.set(result,"st",convert(temp_State),false);
                ptm.ruleFinished(temp_State);
              } else {
                ptm.destroyNode();
              }

            }

            }

            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:257:1: (temp_Condition= ruleCondition )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==14) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:257:2: temp_Condition= ruleCondition
                    {
                    if ( backtracking==0 ) {
                      ptm.createNode(((EObject)((EObject)((EObject)xtextfile.eContents().get(4)).eContents().get(1)).eContents().get(1)));
                    }
                    pushFollow(FOLLOW_ruleCondition_in_ruleQuery300);
                    temp_Condition=ruleCondition();
                    _fsp--;
                    if (failed) return result;
                    if ( backtracking==0 ) {
                      if (temp_Condition != null) {
                        hasContent = true;
                        ptm.setModelElement(temp_Condition);
                        factory.set(result,"condition",convert(temp_Condition),false);
                        ptm.ruleFinished(temp_Condition);
                      } else {
                        ptm.destroyNode();
                      }

                    }

                    }
                    break;

            }


            }

            if ( backtracking==0 ) {
              if (!hasContent)
                result = null;
            }

            }

        }
        catch (RecognitionException re) {
            if (!hasContent)
                result = null;
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
    // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:276:1: ruleCondition returns [EObject result] : ( ( 'where' ) (temp_BoolExpression= ruleBoolExpression ) ( ( ( '||' ) | ( '&&' ) ) (temp_BoolExpression= ruleBoolExpression ) )* ) ;
    public EObject ruleCondition() throws RecognitionException {
        EObject result = null;
        int ruleCondition_StartIndex = input.index();
        EObject temp_BoolExpression = null;


        boolean hasContent = false;
        try {
            if ( backtracking>0 && alreadyParsedRule(input, 6) ) { return result; }
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:279:4: ( ( ( 'where' ) (temp_BoolExpression= ruleBoolExpression ) ( ( ( '||' ) | ( '&&' ) ) (temp_BoolExpression= ruleBoolExpression ) )* ) )
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:279:4: ( ( 'where' ) (temp_BoolExpression= ruleBoolExpression ) ( ( ( '||' ) | ( '&&' ) ) (temp_BoolExpression= ruleBoolExpression ) )* )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "Condition");
              			 
            }
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:282:1: ( ( 'where' ) (temp_BoolExpression= ruleBoolExpression ) ( ( ( '||' ) | ( '&&' ) ) (temp_BoolExpression= ruleBoolExpression ) )* )
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:282:2: ( 'where' ) (temp_BoolExpression= ruleBoolExpression ) ( ( ( '||' ) | ( '&&' ) ) (temp_BoolExpression= ruleBoolExpression ) )*
            {
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:282:2: ( 'where' )
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:282:3: 'where'
            {
            if ( backtracking==0 ) {
              skipCurrentToken = false;
            }
            match(input,14,FOLLOW_14_in_ruleCondition340); if (failed) return result;
            if ( backtracking==0 ) {
              if (!skipCurrentToken) {
                hasContent = true;
                ptm.createNode(((EObject)((EObject)((EObject)xtextfile.eContents().get(5)).eContents().get(1)).eContents().get(0)));
                ptm.ruleFinished(getLastToken());
              }
            }

            }

            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:288:1: (temp_BoolExpression= ruleBoolExpression )
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:288:2: temp_BoolExpression= ruleBoolExpression
            {
            if ( backtracking==0 ) {
              ptm.createNode(((EObject)((EObject)((EObject)xtextfile.eContents().get(5)).eContents().get(1)).eContents().get(1)));
            }
            pushFollow(FOLLOW_ruleBoolExpression_in_ruleCondition350);
            temp_BoolExpression=ruleBoolExpression();
            _fsp--;
            if (failed) return result;
            if ( backtracking==0 ) {
              if (temp_BoolExpression != null) {
                hasContent = true;
                ptm.setModelElement(temp_BoolExpression);
                factory.add(result,"expressions",convert(temp_BoolExpression),false);
                ptm.ruleFinished(temp_BoolExpression);
              } else {
                ptm.destroyNode();
              }

            }

            }

            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:300:1: ( ( ( '||' ) | ( '&&' ) ) (temp_BoolExpression= ruleBoolExpression ) )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0>=15 && LA4_0<=16)) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:300:2: ( ( '||' ) | ( '&&' ) ) (temp_BoolExpression= ruleBoolExpression )
            	    {
            	    // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:300:2: ( ( '||' ) | ( '&&' ) )
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
            	            new NoViableAltException("300:2: ( ( '||' ) | ( '&&' ) )", 3, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt3) {
            	        case 1 :
            	            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:300:3: ( '||' )
            	            {
            	            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:300:3: ( '||' )
            	            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:300:4: '||'
            	            {
            	            if ( backtracking==0 ) {
            	              skipCurrentToken = false;
            	            }
            	            match(input,15,FOLLOW_15_in_ruleCondition360); if (failed) return result;
            	            if ( backtracking==0 ) {
            	              if (!skipCurrentToken) {
            	                hasContent = true;
            	                ptm.createNode(((EObject)((EObject)((EObject)((EObject)((EObject)xtextfile.eContents().get(5)).eContents().get(1)).eContents().get(2)).eContents().get(0)).eContents().get(0)));
            	                ptm.ruleFinished(getLastToken());
            	              }
            	            }

            	            }


            	            }
            	            break;
            	        case 2 :
            	            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:306:1: ( '&&' )
            	            {
            	            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:306:1: ( '&&' )
            	            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:306:2: '&&'
            	            {
            	            if ( backtracking==0 ) {
            	              skipCurrentToken = false;
            	            }
            	            match(input,16,FOLLOW_16_in_ruleCondition369); if (failed) return result;
            	            if ( backtracking==0 ) {
            	              if (!skipCurrentToken) {
            	                hasContent = true;
            	                ptm.createNode(((EObject)((EObject)((EObject)((EObject)((EObject)xtextfile.eContents().get(5)).eContents().get(1)).eContents().get(2)).eContents().get(0)).eContents().get(1)));
            	                ptm.ruleFinished(getLastToken());
            	              }
            	            }

            	            }


            	            }
            	            break;

            	    }

            	    // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:313:1: (temp_BoolExpression= ruleBoolExpression )
            	    // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:313:2: temp_BoolExpression= ruleBoolExpression
            	    {
            	    if ( backtracking==0 ) {
            	      ptm.createNode(((EObject)((EObject)((EObject)((EObject)xtextfile.eContents().get(5)).eContents().get(1)).eContents().get(2)).eContents().get(1)));
            	    }
            	    pushFollow(FOLLOW_ruleBoolExpression_in_ruleCondition381);
            	    temp_BoolExpression=ruleBoolExpression();
            	    _fsp--;
            	    if (failed) return result;
            	    if ( backtracking==0 ) {
            	      if (temp_BoolExpression != null) {
            	        hasContent = true;
            	        ptm.setModelElement(temp_BoolExpression);
            	        factory.add(result,"expressions",convert(temp_BoolExpression),false);
            	        ptm.ruleFinished(temp_BoolExpression);
            	      } else {
            	        ptm.destroyNode();
            	      }

            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);


            }

            if ( backtracking==0 ) {
              if (!hasContent)
                result = null;
            }

            }

        }
        catch (RecognitionException re) {
            if (!hasContent)
                result = null;
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
    // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:333:1: ruleBoolExpression returns [EObject result] : ( (temp_State= ruleState ) (temp_Operand= ruleOperand ) ( RULE_STRING ) ) ;
    public EObject ruleBoolExpression() throws RecognitionException {
        EObject result = null;
        int ruleBoolExpression_StartIndex = input.index();
        EObject temp_State = null;

        EObject temp_Operand = null;


        boolean hasContent = false;
        try {
            if ( backtracking>0 && alreadyParsedRule(input, 7) ) { return result; }
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:336:4: ( ( (temp_State= ruleState ) (temp_Operand= ruleOperand ) ( RULE_STRING ) ) )
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:336:4: ( (temp_State= ruleState ) (temp_Operand= ruleOperand ) ( RULE_STRING ) )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "BoolExpression");
              			 
            }
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:339:1: ( (temp_State= ruleState ) (temp_Operand= ruleOperand ) ( RULE_STRING ) )
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:339:2: (temp_State= ruleState ) (temp_Operand= ruleOperand ) ( RULE_STRING )
            {
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:339:2: (temp_State= ruleState )
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:339:3: temp_State= ruleState
            {
            if ( backtracking==0 ) {
              ptm.createNode(((EObject)((EObject)((EObject)xtextfile.eContents().get(6)).eContents().get(1)).eContents().get(0)));
            }
            pushFollow(FOLLOW_ruleState_in_ruleBoolExpression426);
            temp_State=ruleState();
            _fsp--;
            if (failed) return result;
            if ( backtracking==0 ) {
              if (temp_State != null) {
                hasContent = true;
                ptm.setModelElement(temp_State);
                factory.set(result,"op1",convert(temp_State),false);
                ptm.ruleFinished(temp_State);
              } else {
                ptm.destroyNode();
              }

            }

            }

            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:351:1: (temp_Operand= ruleOperand )
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:351:2: temp_Operand= ruleOperand
            {
            if ( backtracking==0 ) {
              ptm.createNode(((EObject)((EObject)((EObject)xtextfile.eContents().get(6)).eContents().get(1)).eContents().get(1)));
            }
            pushFollow(FOLLOW_ruleOperand_in_ruleBoolExpression437);
            temp_Operand=ruleOperand();
            _fsp--;
            if (failed) return result;
            if ( backtracking==0 ) {
              if (temp_Operand != null) {
                hasContent = true;
                ptm.setModelElement(temp_Operand);
                factory.set(result,"operand",convert(temp_Operand),false);
                ptm.ruleFinished(temp_Operand);
              } else {
                ptm.destroyNode();
              }

            }

            }

            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:363:1: ( RULE_STRING )
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:363:2: RULE_STRING
            {
            if ( backtracking==0 ) {
              skipCurrentToken = false;
            }
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleBoolExpression446); if (failed) return result;
            if ( backtracking==0 ) {
              if (!skipCurrentToken) {
                hasContent = true;
                Token temp = getLastToken();
                ptm.createNode(((EObject)((EObject)((EObject)xtextfile.eContents().get(6)).eContents().get(1)).eContents().get(2)));
                factory.set(result,"op2",convert(temp),false);
                ptm.ruleFinished(temp);
              }
            }

            }


            }

            if ( backtracking==0 ) {
              if (!hasContent)
                result = null;
            }

            }

        }
        catch (RecognitionException re) {
            if (!hasContent)
                result = null;
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
    // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:380:1: ruleState returns [EObject result] : ( ( RULE_ID ) | ( ( '/' ) ( RULE_ID ) )+ ) ;
    public EObject ruleState() throws RecognitionException {
        EObject result = null;
        int ruleState_StartIndex = input.index();
        boolean hasContent = false;
        try {
            if ( backtracking>0 && alreadyParsedRule(input, 8) ) { return result; }
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:383:4: ( ( ( RULE_ID ) | ( ( '/' ) ( RULE_ID ) )+ ) )
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:383:4: ( ( RULE_ID ) | ( ( '/' ) ( RULE_ID ) )+ )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "State");
              			 
            }
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:386:1: ( ( RULE_ID ) | ( ( '/' ) ( RULE_ID ) )+ )
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
                    new NoViableAltException("386:1: ( ( RULE_ID ) | ( ( '/' ) ( RULE_ID ) )+ )", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:386:2: ( RULE_ID )
                    {
                    // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:386:2: ( RULE_ID )
                    // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:386:3: RULE_ID
                    {
                    if ( backtracking==0 ) {
                      skipCurrentToken = false;
                    }
                    match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleState486); if (failed) return result;
                    if ( backtracking==0 ) {
                      if (!skipCurrentToken) {
                        hasContent = true;
                        Token temp = getLastToken();
                        ptm.createNode(((EObject)((EObject)((EObject)xtextfile.eContents().get(7)).eContents().get(1)).eContents().get(0)));
                        factory.add(result,"state",convert(temp),true);
                        ptm.ruleFinished(temp);
                      }
                    }

                    }


                    }
                    break;
                case 2 :
                    // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:397:1: ( ( '/' ) ( RULE_ID ) )+
                    {
                    // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:397:1: ( ( '/' ) ( RULE_ID ) )+
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
                    	    // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:397:2: ( '/' ) ( RULE_ID )
                    	    {
                    	    // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:397:2: ( '/' )
                    	    // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:397:3: '/'
                    	    {
                    	    if ( backtracking==0 ) {
                    	      skipCurrentToken = false;
                    	    }
                    	    match(input,17,FOLLOW_17_in_ruleState498); if (failed) return result;
                    	    if ( backtracking==0 ) {
                    	      if (!skipCurrentToken) {
                    	        hasContent = true;
                    	        ptm.createNode(((EObject)((EObject)((EObject)((EObject)xtextfile.eContents().get(7)).eContents().get(1)).eContents().get(1)).eContents().get(0)));
                    	        ptm.ruleFinished(getLastToken());
                    	      }
                    	    }

                    	    }

                    	    // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:403:1: ( RULE_ID )
                    	    // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:403:2: RULE_ID
                    	    {
                    	    if ( backtracking==0 ) {
                    	      skipCurrentToken = false;
                    	    }
                    	    match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleState506); if (failed) return result;
                    	    if ( backtracking==0 ) {
                    	      if (!skipCurrentToken) {
                    	        hasContent = true;
                    	        Token temp = getLastToken();
                    	        ptm.createNode(((EObject)((EObject)((EObject)((EObject)xtextfile.eContents().get(7)).eContents().get(1)).eContents().get(1)).eContents().get(1)));
                    	        factory.add(result,"state",convert(temp),true);
                    	        ptm.ruleFinished(temp);
                    	      }
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

            if ( backtracking==0 ) {
              if (!hasContent)
                result = null;
            }

            }

        }
        catch (RecognitionException re) {
            if (!hasContent)
                result = null;
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
    // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:422:1: ruleOperand returns [EObject result] : ( ( '==' ) | ( '<=' ) | ( '>=' ) | ( '<' ) | ( '>' ) ) ;
    public EObject ruleOperand() throws RecognitionException {
        EObject result = null;
        int ruleOperand_StartIndex = input.index();
        boolean hasContent = false;
        try {
            if ( backtracking>0 && alreadyParsedRule(input, 9) ) { return result; }
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:425:4: ( ( ( '==' ) | ( '<=' ) | ( '>=' ) | ( '<' ) | ( '>' ) ) )
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:425:4: ( ( '==' ) | ( '<=' ) | ( '>=' ) | ( '<' ) | ( '>' ) )
            {
            if ( backtracking==0 ) {

              				result = factory.create("", "Operand");
              			 
            }
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:428:1: ( ( '==' ) | ( '<=' ) | ( '>=' ) | ( '<' ) | ( '>' ) )
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
                    new NoViableAltException("428:1: ( ( '==' ) | ( '<=' ) | ( '>=' ) | ( '<' ) | ( '>' ) )", 7, 0, input);

                throw nvae;
            }

            switch (alt7) {
                case 1 :
                    // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:428:2: ( '==' )
                    {
                    // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:428:2: ( '==' )
                    // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:428:3: '=='
                    {
                    if ( backtracking==0 ) {
                      skipCurrentToken = false;
                    }
                    match(input,18,FOLLOW_18_in_ruleOperand550); if (failed) return result;
                    if ( backtracking==0 ) {
                      if (!skipCurrentToken) {
                        hasContent = true;
                        Token temp = getLastToken();
                        ptm.createNode(((EObject)((EObject)((EObject)xtextfile.eContents().get(8)).eContents().get(1)).eContents().get(0)));
                        factory.set(result,"op",convert(temp),false);
                        ptm.ruleFinished(temp);
                      }
                    }

                    }


                    }
                    break;
                case 2 :
                    // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:438:1: ( '<=' )
                    {
                    // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:438:1: ( '<=' )
                    // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:438:2: '<='
                    {
                    if ( backtracking==0 ) {
                      skipCurrentToken = false;
                    }
                    match(input,19,FOLLOW_19_in_ruleOperand561); if (failed) return result;
                    if ( backtracking==0 ) {
                      if (!skipCurrentToken) {
                        hasContent = true;
                        Token temp = getLastToken();
                        ptm.createNode(((EObject)((EObject)((EObject)xtextfile.eContents().get(8)).eContents().get(1)).eContents().get(1)));
                        factory.set(result,"op",convert(temp),false);
                        ptm.ruleFinished(temp);
                      }
                    }

                    }


                    }
                    break;
                case 3 :
                    // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:448:1: ( '>=' )
                    {
                    // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:448:1: ( '>=' )
                    // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:448:2: '>='
                    {
                    if ( backtracking==0 ) {
                      skipCurrentToken = false;
                    }
                    match(input,20,FOLLOW_20_in_ruleOperand572); if (failed) return result;
                    if ( backtracking==0 ) {
                      if (!skipCurrentToken) {
                        hasContent = true;
                        Token temp = getLastToken();
                        ptm.createNode(((EObject)((EObject)((EObject)xtextfile.eContents().get(8)).eContents().get(1)).eContents().get(2)));
                        factory.set(result,"op",convert(temp),false);
                        ptm.ruleFinished(temp);
                      }
                    }

                    }


                    }
                    break;
                case 4 :
                    // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:458:1: ( '<' )
                    {
                    // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:458:1: ( '<' )
                    // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:458:2: '<'
                    {
                    if ( backtracking==0 ) {
                      skipCurrentToken = false;
                    }
                    match(input,21,FOLLOW_21_in_ruleOperand583); if (failed) return result;
                    if ( backtracking==0 ) {
                      if (!skipCurrentToken) {
                        hasContent = true;
                        Token temp = getLastToken();
                        ptm.createNode(((EObject)((EObject)((EObject)xtextfile.eContents().get(8)).eContents().get(1)).eContents().get(3)));
                        factory.set(result,"op",convert(temp),false);
                        ptm.ruleFinished(temp);
                      }
                    }

                    }


                    }
                    break;
                case 5 :
                    // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:468:1: ( '>' )
                    {
                    // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:468:1: ( '>' )
                    // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:468:2: '>'
                    {
                    if ( backtracking==0 ) {
                      skipCurrentToken = false;
                    }
                    match(input,22,FOLLOW_22_in_ruleOperand594); if (failed) return result;
                    if ( backtracking==0 ) {
                      if (!skipCurrentToken) {
                        hasContent = true;
                        Token temp = getLastToken();
                        ptm.createNode(((EObject)((EObject)((EObject)xtextfile.eContents().get(8)).eContents().get(1)).eContents().get(4)));
                        factory.set(result,"op",convert(temp),false);
                        ptm.ruleFinished(temp);
                      }
                    }

                    }


                    }
                    break;

            }

            if ( backtracking==0 ) {
              if (!hasContent)
                result = null;
            }

            }

        }
        catch (RecognitionException re) {
            if (!hasContent)
                result = null;
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
    public static final BitSet FOLLOW_EOF_in_parse78 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAutomata_in_ruleQueryModel107 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_ruleAction_in_ruleQueryModel118 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_10_in_ruleAutomata158 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleAutomata167 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_ruleAutomata176 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_ruleAction214 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleAction222 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleAction230 = new BitSet(new long[]{0x0000000000020020L});
    public static final BitSet FOLLOW_ruleQuery_in_ruleAction240 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_ruleAction248 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleState_in_ruleQuery289 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_ruleCondition_in_ruleQuery300 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_ruleCondition340 = new BitSet(new long[]{0x0000000000020020L});
    public static final BitSet FOLLOW_ruleBoolExpression_in_ruleCondition350 = new BitSet(new long[]{0x0000000000018002L});
    public static final BitSet FOLLOW_15_in_ruleCondition360 = new BitSet(new long[]{0x0000000000020020L});
    public static final BitSet FOLLOW_16_in_ruleCondition369 = new BitSet(new long[]{0x0000000000020020L});
    public static final BitSet FOLLOW_ruleBoolExpression_in_ruleCondition381 = new BitSet(new long[]{0x0000000000018002L});
    public static final BitSet FOLLOW_ruleState_in_ruleBoolExpression426 = new BitSet(new long[]{0x00000000007C0000L});
    public static final BitSet FOLLOW_ruleOperand_in_ruleBoolExpression437 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleBoolExpression446 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleState486 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_ruleState498 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleState506 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_18_in_ruleOperand550 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_ruleOperand561 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_ruleOperand572 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_ruleOperand583 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_ruleOperand594 = new BitSet(new long[]{0x0000000000000002L});

}