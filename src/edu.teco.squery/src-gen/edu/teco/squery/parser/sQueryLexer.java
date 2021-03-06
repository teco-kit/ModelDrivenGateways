// $ANTLR 3.0 ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g 2009-02-26 08:57:37

package edu.teco.squery.parser;

import org.openarchitectureware.xtext.parser.ErrorMsg;
import org.openarchitectureware.xtext.parser.impl.AntlrUtil;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class sQueryLexer extends Lexer {
    public static final int RULE_ID=5;
    public static final int Tokens=23;
    public static final int EOF=-1;
    public static final int RULE_SL_COMMENT=9;
    public static final int T22=22;
    public static final int T21=21;
    public static final int T20=20;
    public static final int RULE_ML_COMMENT=8;
    public static final int RULE_STRING=4;
    public static final int RULE_INT=6;
    public static final int T10=10;
    public static final int T11=11;
    public static final int T12=12;
    public static final int T13=13;
    public static final int T14=14;
    public static final int RULE_WS=7;
    public static final int T15=15;
    public static final int T16=16;
    public static final int T17=17;
    public static final int T18=18;
    public static final int T19=19;

    	 private List<ErrorMsg> errors = new ArrayList<ErrorMsg>();
    	public List<ErrorMsg> getErrors() {
    		return errors;
    	}

    	public String getErrorMessage(RecognitionException e, String[] tokenNames) {
    		String msg = super.getErrorMessage(e,tokenNames);
    		errors.add(AntlrUtil.create(msg,e,tokenNames));
    		return msg;
    	}

    public sQueryLexer() {;} 
    public sQueryLexer(CharStream input) {
        super(input);
    }
    public String getGrammarFileName() { return "..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g"; }

    // $ANTLR start T10
    public void mT10() throws RecognitionException {
        try {
            int _type = T10;
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:22:7: ( 'model' )
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:22:7: 'model'
            {
            match("model"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T10

    // $ANTLR start T11
    public void mT11() throws RecognitionException {
        try {
            int _type = T11;
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:23:7: ( ';' )
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:23:7: ';'
            {
            match(';'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T11

    // $ANTLR start T12
    public void mT12() throws RecognitionException {
        try {
            int _type = T12;
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:24:7: ( 'action' )
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:24:7: 'action'
            {
            match("action"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T12

    // $ANTLR start T13
    public void mT13() throws RecognitionException {
        try {
            int _type = T13;
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:25:7: ( 'on' )
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:25:7: 'on'
            {
            match("on"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T13

    // $ANTLR start T14
    public void mT14() throws RecognitionException {
        try {
            int _type = T14;
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:26:7: ( 'where' )
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:26:7: 'where'
            {
            match("where"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T14

    // $ANTLR start T15
    public void mT15() throws RecognitionException {
        try {
            int _type = T15;
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:27:7: ( '||' )
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:27:7: '||'
            {
            match("||"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T15

    // $ANTLR start T16
    public void mT16() throws RecognitionException {
        try {
            int _type = T16;
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:28:7: ( '&&' )
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:28:7: '&&'
            {
            match("&&"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T16

    // $ANTLR start T17
    public void mT17() throws RecognitionException {
        try {
            int _type = T17;
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:29:7: ( '/' )
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:29:7: '/'
            {
            match('/'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T17

    // $ANTLR start T18
    public void mT18() throws RecognitionException {
        try {
            int _type = T18;
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:30:7: ( '==' )
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:30:7: '=='
            {
            match("=="); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T18

    // $ANTLR start T19
    public void mT19() throws RecognitionException {
        try {
            int _type = T19;
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:31:7: ( '<=' )
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:31:7: '<='
            {
            match("<="); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T19

    // $ANTLR start T20
    public void mT20() throws RecognitionException {
        try {
            int _type = T20;
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:32:7: ( '>=' )
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:32:7: '>='
            {
            match(">="); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T20

    // $ANTLR start T21
    public void mT21() throws RecognitionException {
        try {
            int _type = T21;
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:33:7: ( '<' )
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:33:7: '<'
            {
            match('<'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T21

    // $ANTLR start T22
    public void mT22() throws RecognitionException {
        try {
            int _type = T22;
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:34:7: ( '>' )
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:34:7: '>'
            {
            match('>'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T22

    // $ANTLR start RULE_ID
    public void mRULE_ID() throws RecognitionException {
        try {
            int _type = RULE_ID;
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:487:3: ( ( '^' )? ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )* )
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:487:3: ( '^' )? ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            {
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:487:3: ( '^' )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0=='^') ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:487:4: '^'
                    {
                    match('^'); 

                    }
                    break;

            }

            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recover(mse);    throw mse;
            }

            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:487:33: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>='0' && LA2_0<='9')||(LA2_0>='A' && LA2_0<='Z')||LA2_0=='_'||(LA2_0>='a' && LA2_0<='z')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:
            	    {
            	    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recover(mse);    throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RULE_ID

    // $ANTLR start RULE_STRING
    public void mRULE_STRING() throws RecognitionException {
        try {
            int _type = RULE_STRING;
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:493:3: ( '\\\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' ) | ~ ( '\\\\' | '\\\"' ) )* '\\\"' | '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' ) | ~ ( '\\\\' | '\\'' ) )* '\\'' )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0=='\"') ) {
                alt5=1;
            }
            else if ( (LA5_0=='\'') ) {
                alt5=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("491:1: RULE_STRING : ( '\\\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' ) | ~ ( '\\\\' | '\\\"' ) )* '\\\"' | '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' ) | ~ ( '\\\\' | '\\'' ) )* '\\'' );", 5, 0, input);

                throw nvae;
            }
            switch (alt5) {
                case 1 :
                    // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:493:3: '\\\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' ) | ~ ( '\\\\' | '\\\"' ) )* '\\\"'
                    {
                    match('\"'); 
                    // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:493:8: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' ) | ~ ( '\\\\' | '\\\"' ) )*
                    loop3:
                    do {
                        int alt3=3;
                        int LA3_0 = input.LA(1);

                        if ( (LA3_0=='\\') ) {
                            alt3=1;
                        }
                        else if ( ((LA3_0>='\u0000' && LA3_0<='!')||(LA3_0>='#' && LA3_0<='[')||(LA3_0>=']' && LA3_0<='\uFFFE')) ) {
                            alt3=2;
                        }


                        switch (alt3) {
                    	case 1 :
                    	    // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:493:10: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' )
                    	    {
                    	    match('\\'); 
                    	    if ( input.LA(1)=='\"'||input.LA(1)=='\''||input.LA(1)=='\\'||input.LA(1)=='b'||input.LA(1)=='f'||input.LA(1)=='n'||input.LA(1)=='r'||input.LA(1)=='t' ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse =
                    	            new MismatchedSetException(null,input);
                    	        recover(mse);    throw mse;
                    	    }


                    	    }
                    	    break;
                    	case 2 :
                    	    // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:493:54: ~ ( '\\\\' | '\\\"' )
                    	    {
                    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='!')||(input.LA(1)>='#' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFE') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse =
                    	            new MismatchedSetException(null,input);
                    	        recover(mse);    throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop3;
                        }
                    } while (true);

                    match('\"'); 

                    }
                    break;
                case 2 :
                    // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:494:3: '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' ) | ~ ( '\\\\' | '\\'' ) )* '\\''
                    {
                    match('\''); 
                    // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:494:8: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' ) | ~ ( '\\\\' | '\\'' ) )*
                    loop4:
                    do {
                        int alt4=3;
                        int LA4_0 = input.LA(1);

                        if ( (LA4_0=='\\') ) {
                            alt4=1;
                        }
                        else if ( ((LA4_0>='\u0000' && LA4_0<='&')||(LA4_0>='(' && LA4_0<='[')||(LA4_0>=']' && LA4_0<='\uFFFE')) ) {
                            alt4=2;
                        }


                        switch (alt4) {
                    	case 1 :
                    	    // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:494:10: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' )
                    	    {
                    	    match('\\'); 
                    	    if ( input.LA(1)=='\"'||input.LA(1)=='\''||input.LA(1)=='\\'||input.LA(1)=='b'||input.LA(1)=='f'||input.LA(1)=='n'||input.LA(1)=='r'||input.LA(1)=='t' ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse =
                    	            new MismatchedSetException(null,input);
                    	        recover(mse);    throw mse;
                    	    }


                    	    }
                    	    break;
                    	case 2 :
                    	    // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:494:54: ~ ( '\\\\' | '\\'' )
                    	    {
                    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='&')||(input.LA(1)>='(' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFE') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse =
                    	            new MismatchedSetException(null,input);
                    	        recover(mse);    throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop4;
                        }
                    } while (true);

                    match('\''); 

                    }
                    break;

            }
            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RULE_STRING

    // $ANTLR start RULE_INT
    public void mRULE_INT() throws RecognitionException {
        try {
            int _type = RULE_INT;
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:500:3: ( ( '-' )? ( '0' .. '9' )+ )
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:500:3: ( '-' )? ( '0' .. '9' )+
            {
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:500:3: ( '-' )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0=='-') ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:500:4: '-'
                    {
                    match('-'); 

                    }
                    break;

            }

            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:500:9: ( '0' .. '9' )+
            int cnt7=0;
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( ((LA7_0>='0' && LA7_0<='9')) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:500:10: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt7 >= 1 ) break loop7;
                        EarlyExitException eee =
                            new EarlyExitException(7, input);
                        throw eee;
                }
                cnt7++;
            } while (true);


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RULE_INT

    // $ANTLR start RULE_WS
    public void mRULE_WS() throws RecognitionException {
        try {
            int _type = RULE_WS;
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:506:3: ( ( ' ' | '\\t' | '\\r' | '\\n' )+ )
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:506:3: ( ' ' | '\\t' | '\\r' | '\\n' )+
            {
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:506:3: ( ' ' | '\\t' | '\\r' | '\\n' )+
            int cnt8=0;
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( ((LA8_0>='\t' && LA8_0<='\n')||LA8_0=='\r'||LA8_0==' ') ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:
            	    {
            	    if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recover(mse);    throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt8 >= 1 ) break loop8;
                        EarlyExitException eee =
                            new EarlyExitException(8, input);
                        throw eee;
                }
                cnt8++;
            } while (true);

            channel=HIDDEN;

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RULE_WS

    // $ANTLR start RULE_ML_COMMENT
    public void mRULE_ML_COMMENT() throws RecognitionException {
        try {
            int _type = RULE_ML_COMMENT;
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:512:3: ( '/*' ( options {greedy=false; } : . )* '*/' )
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:512:3: '/*' ( options {greedy=false; } : . )* '*/'
            {
            match("/*"); 

            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:512:8: ( options {greedy=false; } : . )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0=='*') ) {
                    int LA9_1 = input.LA(2);

                    if ( (LA9_1=='/') ) {
                        alt9=2;
                    }
                    else if ( ((LA9_1>='\u0000' && LA9_1<='.')||(LA9_1>='0' && LA9_1<='\uFFFE')) ) {
                        alt9=1;
                    }


                }
                else if ( ((LA9_0>='\u0000' && LA9_0<=')')||(LA9_0>='+' && LA9_0<='\uFFFE')) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:512:36: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);

            match("*/"); 

            channel=HIDDEN;

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RULE_ML_COMMENT

    // $ANTLR start RULE_SL_COMMENT
    public void mRULE_SL_COMMENT() throws RecognitionException {
        try {
            int _type = RULE_SL_COMMENT;
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:518:3: ( '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n' )
            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:518:3: '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n'
            {
            match("//"); 

            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:518:8: (~ ( '\\n' | '\\r' ) )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( ((LA10_0>='\u0000' && LA10_0<='\t')||(LA10_0>='\u000B' && LA10_0<='\f')||(LA10_0>='\u000E' && LA10_0<='\uFFFE')) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:518:8: ~ ( '\\n' | '\\r' )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='\uFFFE') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recover(mse);    throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);

            // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:518:22: ( '\\r' )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0=='\r') ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:518:22: '\\r'
                    {
                    match('\r'); 

                    }
                    break;

            }

            match('\n'); 
            channel=HIDDEN;

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RULE_SL_COMMENT

    public void mTokens() throws RecognitionException {
        // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:1:10: ( T10 | T11 | T12 | T13 | T14 | T15 | T16 | T17 | T18 | T19 | T20 | T21 | T22 | RULE_ID | RULE_STRING | RULE_INT | RULE_WS | RULE_ML_COMMENT | RULE_SL_COMMENT )
        int alt12=19;
        switch ( input.LA(1) ) {
        case 'm':
            {
            int LA12_1 = input.LA(2);

            if ( (LA12_1=='o') ) {
                int LA12_16 = input.LA(3);

                if ( (LA12_16=='d') ) {
                    int LA12_27 = input.LA(4);

                    if ( (LA12_27=='e') ) {
                        int LA12_31 = input.LA(5);

                        if ( (LA12_31=='l') ) {
                            int LA12_34 = input.LA(6);

                            if ( ((LA12_34>='0' && LA12_34<='9')||(LA12_34>='A' && LA12_34<='Z')||LA12_34=='_'||(LA12_34>='a' && LA12_34<='z')) ) {
                                alt12=14;
                            }
                            else {
                                alt12=1;}
                        }
                        else {
                            alt12=14;}
                    }
                    else {
                        alt12=14;}
                }
                else {
                    alt12=14;}
            }
            else {
                alt12=14;}
            }
            break;
        case ';':
            {
            alt12=2;
            }
            break;
        case 'a':
            {
            int LA12_3 = input.LA(2);

            if ( (LA12_3=='c') ) {
                int LA12_17 = input.LA(3);

                if ( (LA12_17=='t') ) {
                    int LA12_28 = input.LA(4);

                    if ( (LA12_28=='i') ) {
                        int LA12_32 = input.LA(5);

                        if ( (LA12_32=='o') ) {
                            int LA12_35 = input.LA(6);

                            if ( (LA12_35=='n') ) {
                                int LA12_38 = input.LA(7);

                                if ( ((LA12_38>='0' && LA12_38<='9')||(LA12_38>='A' && LA12_38<='Z')||LA12_38=='_'||(LA12_38>='a' && LA12_38<='z')) ) {
                                    alt12=14;
                                }
                                else {
                                    alt12=3;}
                            }
                            else {
                                alt12=14;}
                        }
                        else {
                            alt12=14;}
                    }
                    else {
                        alt12=14;}
                }
                else {
                    alt12=14;}
            }
            else {
                alt12=14;}
            }
            break;
        case 'o':
            {
            int LA12_4 = input.LA(2);

            if ( (LA12_4=='n') ) {
                int LA12_18 = input.LA(3);

                if ( ((LA12_18>='0' && LA12_18<='9')||(LA12_18>='A' && LA12_18<='Z')||LA12_18=='_'||(LA12_18>='a' && LA12_18<='z')) ) {
                    alt12=14;
                }
                else {
                    alt12=4;}
            }
            else {
                alt12=14;}
            }
            break;
        case 'w':
            {
            int LA12_5 = input.LA(2);

            if ( (LA12_5=='h') ) {
                int LA12_19 = input.LA(3);

                if ( (LA12_19=='e') ) {
                    int LA12_30 = input.LA(4);

                    if ( (LA12_30=='r') ) {
                        int LA12_33 = input.LA(5);

                        if ( (LA12_33=='e') ) {
                            int LA12_36 = input.LA(6);

                            if ( ((LA12_36>='0' && LA12_36<='9')||(LA12_36>='A' && LA12_36<='Z')||LA12_36=='_'||(LA12_36>='a' && LA12_36<='z')) ) {
                                alt12=14;
                            }
                            else {
                                alt12=5;}
                        }
                        else {
                            alt12=14;}
                    }
                    else {
                        alt12=14;}
                }
                else {
                    alt12=14;}
            }
            else {
                alt12=14;}
            }
            break;
        case '|':
            {
            alt12=6;
            }
            break;
        case '&':
            {
            alt12=7;
            }
            break;
        case '/':
            {
            switch ( input.LA(2) ) {
            case '/':
                {
                alt12=19;
                }
                break;
            case '*':
                {
                alt12=18;
                }
                break;
            default:
                alt12=8;}

            }
            break;
        case '=':
            {
            alt12=9;
            }
            break;
        case '<':
            {
            int LA12_10 = input.LA(2);

            if ( (LA12_10=='=') ) {
                alt12=10;
            }
            else {
                alt12=12;}
            }
            break;
        case '>':
            {
            int LA12_11 = input.LA(2);

            if ( (LA12_11=='=') ) {
                alt12=11;
            }
            else {
                alt12=13;}
            }
            break;
        case 'A':
        case 'B':
        case 'C':
        case 'D':
        case 'E':
        case 'F':
        case 'G':
        case 'H':
        case 'I':
        case 'J':
        case 'K':
        case 'L':
        case 'M':
        case 'N':
        case 'O':
        case 'P':
        case 'Q':
        case 'R':
        case 'S':
        case 'T':
        case 'U':
        case 'V':
        case 'W':
        case 'X':
        case 'Y':
        case 'Z':
        case '^':
        case '_':
        case 'b':
        case 'c':
        case 'd':
        case 'e':
        case 'f':
        case 'g':
        case 'h':
        case 'i':
        case 'j':
        case 'k':
        case 'l':
        case 'n':
        case 'p':
        case 'q':
        case 'r':
        case 's':
        case 't':
        case 'u':
        case 'v':
        case 'x':
        case 'y':
        case 'z':
            {
            alt12=14;
            }
            break;
        case '\"':
        case '\'':
            {
            alt12=15;
            }
            break;
        case '-':
        case '0':
        case '1':
        case '2':
        case '3':
        case '4':
        case '5':
        case '6':
        case '7':
        case '8':
        case '9':
            {
            alt12=16;
            }
            break;
        case '\t':
        case '\n':
        case '\r':
        case ' ':
            {
            alt12=17;
            }
            break;
        default:
            NoViableAltException nvae =
                new NoViableAltException("1:1: Tokens : ( T10 | T11 | T12 | T13 | T14 | T15 | T16 | T17 | T18 | T19 | T20 | T21 | T22 | RULE_ID | RULE_STRING | RULE_INT | RULE_WS | RULE_ML_COMMENT | RULE_SL_COMMENT );", 12, 0, input);

            throw nvae;
        }

        switch (alt12) {
            case 1 :
                // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:1:10: T10
                {
                mT10(); 

                }
                break;
            case 2 :
                // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:1:14: T11
                {
                mT11(); 

                }
                break;
            case 3 :
                // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:1:18: T12
                {
                mT12(); 

                }
                break;
            case 4 :
                // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:1:22: T13
                {
                mT13(); 

                }
                break;
            case 5 :
                // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:1:26: T14
                {
                mT14(); 

                }
                break;
            case 6 :
                // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:1:30: T15
                {
                mT15(); 

                }
                break;
            case 7 :
                // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:1:34: T16
                {
                mT16(); 

                }
                break;
            case 8 :
                // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:1:38: T17
                {
                mT17(); 

                }
                break;
            case 9 :
                // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:1:42: T18
                {
                mT18(); 

                }
                break;
            case 10 :
                // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:1:46: T19
                {
                mT19(); 

                }
                break;
            case 11 :
                // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:1:50: T20
                {
                mT20(); 

                }
                break;
            case 12 :
                // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:1:54: T21
                {
                mT21(); 

                }
                break;
            case 13 :
                // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:1:58: T22
                {
                mT22(); 

                }
                break;
            case 14 :
                // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:1:62: RULE_ID
                {
                mRULE_ID(); 

                }
                break;
            case 15 :
                // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:1:70: RULE_STRING
                {
                mRULE_STRING(); 

                }
                break;
            case 16 :
                // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:1:82: RULE_INT
                {
                mRULE_INT(); 

                }
                break;
            case 17 :
                // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:1:91: RULE_WS
                {
                mRULE_WS(); 

                }
                break;
            case 18 :
                // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:1:99: RULE_ML_COMMENT
                {
                mRULE_ML_COMMENT(); 

                }
                break;
            case 19 :
                // ..//edu.teco.squery/src-gen/edu/teco/squery/parser/sQuery.g:1:115: RULE_SL_COMMENT
                {
                mRULE_SL_COMMENT(); 

                }
                break;

        }

    }


 

}