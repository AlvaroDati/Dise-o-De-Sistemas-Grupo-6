// Generated from indicadores.g4 by ANTLR 4.7
   
import java.util.HashMap;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class indicadoresParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, OTROINDICADOR=4, CUENTA=5, MUL=6, DIV=7, SUM=8, 
		RES=9, WS=10;
	public static final int
		RULE_sentencia = 0, RULE_expresion = 1, RULE_expresionMultiple = 2, RULE_operando = 3;
	public static final String[] ruleNames = {
		"sentencia", "expresion", "expresionMultiple", "operando"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'='", "'('", "')'", null, null, "'*'", "'/'", "'+'", "'-'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, "OTROINDICADOR", "CUENTA", "MUL", "DIV", "SUM", 
		"RES", "WS"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "indicadores.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


	HashMap memory = new HashMap();

	public indicadoresParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class SentenciaContext extends ParserRuleContext {
		public SentenciaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sentencia; }
	 
		public SentenciaContext() { }
		public void copyFrom(SentenciaContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ExprContext extends SentenciaContext {
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public ExprContext(SentenciaContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof indicadoresVisitor ) return ((indicadoresVisitor<? extends T>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AsignarContext extends SentenciaContext {
		public TerminalNode OTROINDICADOR() { return getToken(indicadoresParser.OTROINDICADOR, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public AsignarContext(SentenciaContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof indicadoresVisitor ) return ((indicadoresVisitor<? extends T>)visitor).visitAsignar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SentenciaContext sentencia() throws RecognitionException {
		SentenciaContext _localctx = new SentenciaContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_sentencia);
		try {
			setState(12);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				_localctx = new ExprContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(8);
				expresion();
				}
				break;
			case 2:
				_localctx = new AsignarContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(9);
				match(OTROINDICADOR);
				setState(10);
				match(T__0);
				setState(11);
				expresion();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpresionContext extends ParserRuleContext {
		public ExpresionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expresion; }
	 
		public ExpresionContext() { }
		public void copyFrom(ExpresionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class SumResContext extends ExpresionContext {
		public Token op;
		public List<ExpresionMultipleContext> expresionMultiple() {
			return getRuleContexts(ExpresionMultipleContext.class);
		}
		public ExpresionMultipleContext expresionMultiple(int i) {
			return getRuleContext(ExpresionMultipleContext.class,i);
		}
		public SumResContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof indicadoresVisitor ) return ((indicadoresVisitor<? extends T>)visitor).visitSumRes(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpresionContext expresion() throws RecognitionException {
		ExpresionContext _localctx = new ExpresionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_expresion);
		int _la;
		try {
			_localctx = new SumResContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(14);
			expresionMultiple();
			setState(19);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SUM || _la==RES) {
				{
				{
				setState(15);
				((SumResContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==SUM || _la==RES) ) {
					((SumResContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(16);
				expresionMultiple();
				}
				}
				setState(21);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpresionMultipleContext extends ParserRuleContext {
		public ExpresionMultipleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expresionMultiple; }
	 
		public ExpresionMultipleContext() { }
		public void copyFrom(ExpresionMultipleContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class MulDivContext extends ExpresionMultipleContext {
		public Token op;
		public List<OperandoContext> operando() {
			return getRuleContexts(OperandoContext.class);
		}
		public OperandoContext operando(int i) {
			return getRuleContext(OperandoContext.class,i);
		}
		public MulDivContext(ExpresionMultipleContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof indicadoresVisitor ) return ((indicadoresVisitor<? extends T>)visitor).visitMulDiv(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpresionMultipleContext expresionMultiple() throws RecognitionException {
		ExpresionMultipleContext _localctx = new ExpresionMultipleContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_expresionMultiple);
		int _la;
		try {
			_localctx = new MulDivContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(22);
			operando();
			setState(27);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==MUL || _la==DIV) {
				{
				{
				setState(23);
				((MulDivContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==MUL || _la==DIV) ) {
					((MulDivContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(24);
				operando();
				}
				}
				setState(29);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperandoContext extends ParserRuleContext {
		public OperandoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operando; }
	 
		public OperandoContext() { }
		public void copyFrom(OperandoContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class OtroIndicadorContext extends OperandoContext {
		public TerminalNode OTROINDICADOR() { return getToken(indicadoresParser.OTROINDICADOR, 0); }
		public OtroIndicadorContext(OperandoContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof indicadoresVisitor ) return ((indicadoresVisitor<? extends T>)visitor).visitOtroIndicador(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ParentesisContext extends OperandoContext {
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public ParentesisContext(OperandoContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof indicadoresVisitor ) return ((indicadoresVisitor<? extends T>)visitor).visitParentesis(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CuentaContext extends OperandoContext {
		public TerminalNode CUENTA() { return getToken(indicadoresParser.CUENTA, 0); }
		public CuentaContext(OperandoContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof indicadoresVisitor ) return ((indicadoresVisitor<? extends T>)visitor).visitCuenta(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperandoContext operando() throws RecognitionException {
		OperandoContext _localctx = new OperandoContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_operando);
		try {
			setState(36);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CUENTA:
				_localctx = new CuentaContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(30);
				match(CUENTA);
				}
				break;
			case OTROINDICADOR:
				_localctx = new OtroIndicadorContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(31);
				match(OTROINDICADOR);
				}
				break;
			case T__1:
				_localctx = new ParentesisContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(32);
				match(T__1);
				setState(33);
				expresion();
				setState(34);
				match(T__2);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\f)\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\3\2\3\2\3\2\3\2\5\2\17\n\2\3\3\3\3\3\3\7\3\24\n\3\f"+
		"\3\16\3\27\13\3\3\4\3\4\3\4\7\4\34\n\4\f\4\16\4\37\13\4\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\5\5\'\n\5\3\5\2\2\6\2\4\6\b\2\4\3\2\n\13\3\2\b\t\2)\2\16\3"+
		"\2\2\2\4\20\3\2\2\2\6\30\3\2\2\2\b&\3\2\2\2\n\17\5\4\3\2\13\f\7\6\2\2"+
		"\f\r\7\3\2\2\r\17\5\4\3\2\16\n\3\2\2\2\16\13\3\2\2\2\17\3\3\2\2\2\20\25"+
		"\5\6\4\2\21\22\t\2\2\2\22\24\5\6\4\2\23\21\3\2\2\2\24\27\3\2\2\2\25\23"+
		"\3\2\2\2\25\26\3\2\2\2\26\5\3\2\2\2\27\25\3\2\2\2\30\35\5\b\5\2\31\32"+
		"\t\3\2\2\32\34\5\b\5\2\33\31\3\2\2\2\34\37\3\2\2\2\35\33\3\2\2\2\35\36"+
		"\3\2\2\2\36\7\3\2\2\2\37\35\3\2\2\2 \'\7\7\2\2!\'\7\6\2\2\"#\7\4\2\2#"+
		"$\5\4\3\2$%\7\5\2\2%\'\3\2\2\2& \3\2\2\2&!\3\2\2\2&\"\3\2\2\2\'\t\3\2"+
		"\2\2\6\16\25\35&";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}