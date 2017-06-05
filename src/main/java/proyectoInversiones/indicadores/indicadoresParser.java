package proyectoInversiones.indicadores;
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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, OTROINDICADOR=8, 
		CUENTA=9, SALTODELINEA=10, WS=11;
	public static final int
		RULE_fuente = 0, RULE_sentencia = 1, RULE_expresion = 2, RULE_expresionMultiple = 3, 
		RULE_operando = 4;
	public static final String[] ruleNames = {
		"fuente", "sentencia", "expresion", "expresionMultiple", "operando"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'='", "'+'", "'-'", "'*'", "'/'", "'('", "')'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, "OTROINDICADOR", "CUENTA", 
		"SALTODELINEA", "WS"
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
	public static class FuenteContext extends ParserRuleContext {
		public List<SentenciaContext> sentencia() {
			return getRuleContexts(SentenciaContext.class);
		}
		public SentenciaContext sentencia(int i) {
			return getRuleContext(SentenciaContext.class,i);
		}
		public FuenteContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fuente; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof indicadoresListener ) ((indicadoresListener)listener).enterFuente(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof indicadoresListener ) ((indicadoresListener)listener).exitFuente(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof indicadoresVisitor ) return ((indicadoresVisitor<? extends T>)visitor).visitFuente(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuenteContext fuente() throws RecognitionException {
		FuenteContext _localctx = new FuenteContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_fuente);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(11); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(10);
				sentencia();
				}
				}
				setState(13); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << OTROINDICADOR) | (1L << CUENTA) | (1L << SALTODELINEA))) != 0) );
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

	public static class SentenciaContext extends ParserRuleContext {
		public ExpresionContext expresion;
		public Token OTROINDICADOR;
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public TerminalNode SALTODELINEA() { return getToken(indicadoresParser.SALTODELINEA, 0); }
		public TerminalNode OTROINDICADOR() { return getToken(indicadoresParser.OTROINDICADOR, 0); }
		public SentenciaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sentencia; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof indicadoresListener ) ((indicadoresListener)listener).enterSentencia(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof indicadoresListener ) ((indicadoresListener)listener).exitSentencia(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof indicadoresVisitor ) return ((indicadoresVisitor<? extends T>)visitor).visitSentencia(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SentenciaContext sentencia() throws RecognitionException {
		SentenciaContext _localctx = new SentenciaContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_sentencia);
		try {
			setState(26);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(15);
				((SentenciaContext)_localctx).expresion = expresion();
				setState(16);
				match(SALTODELINEA);
				System.out.println( "\n" + ((SentenciaContext)_localctx).expresion.value );
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(19);
				((SentenciaContext)_localctx).OTROINDICADOR = match(OTROINDICADOR);
				setState(20);
				match(T__0);
				setState(21);
				((SentenciaContext)_localctx).expresion = expresion();
				setState(22);
				match(SALTODELINEA);
				memory.put( (((SentenciaContext)_localctx).OTROINDICADOR!=null?((SentenciaContext)_localctx).OTROINDICADOR.getText():null) , new Double( ((SentenciaContext)_localctx).expresion.value ));
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(25);
				match(SALTODELINEA);
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
		public double value;
		public ExpresionMultipleContext e;
		public List<ExpresionMultipleContext> expresionMultiple() {
			return getRuleContexts(ExpresionMultipleContext.class);
		}
		public ExpresionMultipleContext expresionMultiple(int i) {
			return getRuleContext(ExpresionMultipleContext.class,i);
		}
		public ExpresionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expresion; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof indicadoresListener ) ((indicadoresListener)listener).enterExpresion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof indicadoresListener ) ((indicadoresListener)listener).exitExpresion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof indicadoresVisitor ) return ((indicadoresVisitor<? extends T>)visitor).visitExpresion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpresionContext expresion() throws RecognitionException {
		ExpresionContext _localctx = new ExpresionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_expresion);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(28);
			((ExpresionContext)_localctx).e = expresionMultiple();
			 ((ExpresionContext)_localctx).value =  ((ExpresionContext)_localctx).e.value ;
			setState(40);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1 || _la==T__2) {
				{
				setState(38);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__1:
					{
					setState(30);
					match(T__1);
					setState(31);
					((ExpresionContext)_localctx).e = expresionMultiple();
					 _localctx.value += ((ExpresionContext)_localctx).e.value ;
					}
					break;
				case T__2:
					{
					setState(34);
					match(T__2);
					setState(35);
					((ExpresionContext)_localctx).e = expresionMultiple();
					 _localctx.value -= ((ExpresionContext)_localctx).e.value ;
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(42);
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
		public double value;
		public OperandoContext e;
		public List<OperandoContext> operando() {
			return getRuleContexts(OperandoContext.class);
		}
		public OperandoContext operando(int i) {
			return getRuleContext(OperandoContext.class,i);
		}
		public ExpresionMultipleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expresionMultiple; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof indicadoresListener ) ((indicadoresListener)listener).enterExpresionMultiple(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof indicadoresListener ) ((indicadoresListener)listener).exitExpresionMultiple(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof indicadoresVisitor ) return ((indicadoresVisitor<? extends T>)visitor).visitExpresionMultiple(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpresionMultipleContext expresionMultiple() throws RecognitionException {
		ExpresionMultipleContext _localctx = new ExpresionMultipleContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_expresionMultiple);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(43);
			((ExpresionMultipleContext)_localctx).e = operando();
			 ((ExpresionMultipleContext)_localctx).value =  ((ExpresionMultipleContext)_localctx).e.value ;
			setState(55);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3 || _la==T__4) {
				{
				setState(53);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__3:
					{
					setState(45);
					match(T__3);
					setState(46);
					((ExpresionMultipleContext)_localctx).e = operando();
					 _localctx.value *= ((ExpresionMultipleContext)_localctx).e.value ;
					}
					break;
				case T__4:
					{
					setState(49);
					match(T__4);
					setState(50);
					((ExpresionMultipleContext)_localctx).e = operando();
					 _localctx.value /= ((ExpresionMultipleContext)_localctx).e.value ;
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(57);
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
		public double value;
		public Token CUENTA;
		public Token OTROINDICADOR;
		public ExpresionContext expresion;
		public TerminalNode CUENTA() { return getToken(indicadoresParser.CUENTA, 0); }
		public TerminalNode OTROINDICADOR() { return getToken(indicadoresParser.OTROINDICADOR, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public OperandoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operando; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof indicadoresListener ) ((indicadoresListener)listener).enterOperando(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof indicadoresListener ) ((indicadoresListener)listener).exitOperando(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof indicadoresVisitor ) return ((indicadoresVisitor<? extends T>)visitor).visitOperando(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperandoContext operando() throws RecognitionException {
		OperandoContext _localctx = new OperandoContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_operando);
		try {
			setState(67);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CUENTA:
				enterOuterAlt(_localctx, 1);
				{
				setState(58);
				((OperandoContext)_localctx).CUENTA = match(CUENTA);
				 ((OperandoContext)_localctx).value =  Double.parseDouble( (((OperandoContext)_localctx).CUENTA!=null?((OperandoContext)_localctx).CUENTA.getText():null) );
				}
				break;
			case OTROINDICADOR:
				enterOuterAlt(_localctx, 2);
				{
				setState(60);
				((OperandoContext)_localctx).OTROINDICADOR = match(OTROINDICADOR);
				Double ind = (Double)memory.get( (((OperandoContext)_localctx).OTROINDICADOR!=null?((OperandoContext)_localctx).OTROINDICADOR.getText():null) ); ((OperandoContext)_localctx).value =  ind.doubleValue();
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 3);
				{
				setState(62);
				match(T__5);
				setState(63);
				((OperandoContext)_localctx).expresion = expresion();
				setState(64);
				match(T__6);
				 ((OperandoContext)_localctx).value =  ((OperandoContext)_localctx).expresion.value ;
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\rH\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\3\2\6\2\16\n\2\r\2\16\2\17\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3\35\n\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\7\4)\n\4\f\4\16\4,\13\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\7\58\n\5\f\5\16\5;\13\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6F"+
		"\n\6\3\6\2\2\7\2\4\6\b\n\2\2\2K\2\r\3\2\2\2\4\34\3\2\2\2\6\36\3\2\2\2"+
		"\b-\3\2\2\2\nE\3\2\2\2\f\16\5\4\3\2\r\f\3\2\2\2\16\17\3\2\2\2\17\r\3\2"+
		"\2\2\17\20\3\2\2\2\20\3\3\2\2\2\21\22\5\6\4\2\22\23\7\f\2\2\23\24\b\3"+
		"\1\2\24\35\3\2\2\2\25\26\7\n\2\2\26\27\7\3\2\2\27\30\5\6\4\2\30\31\7\f"+
		"\2\2\31\32\b\3\1\2\32\35\3\2\2\2\33\35\7\f\2\2\34\21\3\2\2\2\34\25\3\2"+
		"\2\2\34\33\3\2\2\2\35\5\3\2\2\2\36\37\5\b\5\2\37*\b\4\1\2 !\7\4\2\2!\""+
		"\5\b\5\2\"#\b\4\1\2#)\3\2\2\2$%\7\5\2\2%&\5\b\5\2&\'\b\4\1\2\')\3\2\2"+
		"\2( \3\2\2\2($\3\2\2\2),\3\2\2\2*(\3\2\2\2*+\3\2\2\2+\7\3\2\2\2,*\3\2"+
		"\2\2-.\5\n\6\2.9\b\5\1\2/\60\7\6\2\2\60\61\5\n\6\2\61\62\b\5\1\2\628\3"+
		"\2\2\2\63\64\7\7\2\2\64\65\5\n\6\2\65\66\b\5\1\2\668\3\2\2\2\67/\3\2\2"+
		"\2\67\63\3\2\2\28;\3\2\2\29\67\3\2\2\29:\3\2\2\2:\t\3\2\2\2;9\3\2\2\2"+
		"<=\7\13\2\2=F\b\6\1\2>?\7\n\2\2?F\b\6\1\2@A\7\b\2\2AB\5\6\4\2BC\7\t\2"+
		"\2CD\b\6\1\2DF\3\2\2\2E<\3\2\2\2E>\3\2\2\2E@\3\2\2\2F\13\3\2\2\2\t\17"+
		"\34(*\679E";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}