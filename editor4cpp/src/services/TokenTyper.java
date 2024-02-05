//package services;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import entities.Token;
//import entities.TokenTypes.Comment;
//import entities.TokenTypes.DataType;
//import entities.TokenTypes.DataTypes.BoolType;
//import entities.TokenTypes.DataTypes.CharType;
//import entities.TokenTypes.DataTypes.DoubleType;
//import entities.TokenTypes.DataTypes.FloatType;
//import entities.TokenTypes.DataTypes.IntType;
//import entities.TokenTypes.DataTypes.LongType;
//import entities.TokenTypes.DataTypes.ShortType;
//import entities.TokenTypes.DataTypes.StringType;
//import entities.TokenTypes.Literals.CharLiteral;
//import entities.TokenTypes.Literals.StringLiteral;
//import enums.BufferMode;
//import enums.CommentMode;
//import enums.TextMode;
//
//public class TokenTyper {
//
//	Map<String, DataType> dataTypes = new HashMap<>();
//
//	public TokenTyper() {
//		dataTypes.putIfAbsent("int", new IntType());
//		dataTypes.putIfAbsent("char", new CharType());
//		dataTypes.putIfAbsent("string", new StringType());
//		dataTypes.putIfAbsent("short", new ShortType());
//		dataTypes.putIfAbsent("long", new LongType());
//		dataTypes.putIfAbsent("float", new FloatType());
//		dataTypes.putIfAbsent("double", new DoubleType());
//		dataTypes.putIfAbsent("bool", new BoolType());
//	}
//
//	public void tokenize(Token token) {
//
//		if (isComment(token))
//			return;
//		if (isString(token))
//			return;
//		if (isChar(token))
//			return;
//		if(isStarType(token))
//			return;
//		if (isDataType(token))
//			return;
//
//	}
//
//	private boolean isComment(Token token) {
//		switch (TokenBuffer.getBufferMode()) {
//		// we are not in middle of writing a comment
//		case stringLiteral:
//		case charLiteral:
//			return false;
//		case normal: {
//			var nextToken = TokenBuffer.lookAhead();
//			// detecting the start of a single-line Comment
//			if (token.value.equals("/") && nextToken != null && nextToken.value.equals("/")) {
//				TokenBuffer.setBufferMode(BufferMode.singleLineComment);
//				token.tokenType = new Comment();
//				return true;
//			}
//			// detecting the start of a multi-line comment
//			if (token.value.equals("/") && nextToken != null && nextToken.value.equals("*")) {
//				TokenBuffer.setBufferMode (BufferMode.multiLineComment);
//				token.tokenType = new Comment();
//				return true;
//			}
//			// no comment detected
//			return false;
//		}
//		// we are writing a single-line comment
//		case singleLineComment: {
//			// detecting end of the single-line comment handled by buffer
//			// the single-line comment continue
//			token.tokenType = new Comment();
//			return true;
//		}
//		// we are writing a multi-line comment
//		case multiLineComment: {
//			var prevToken = TokenBuffer.lookBehind();
//			// detecting the end of the multi-line comment
//			if (token.value.equals("/") && prevToken != null && prevToken.value.equals("*")) {
//				TokenBuffer.setBufferMode(BufferMode.normal);
//			}
//			// multi-line comment continue
//			token.tokenType = new Comment();
//			return true;
//		}
//		default:
//			return false;
//		}
//
//	}
//
//	private boolean isChar(Token token) {
//
//		switch (TokenBuffer.getBufferMode()) {
//		case singleLineComment:
//		case multiLineComment:
//		case stringLiteral:
//			return false;
//		case normal: {
//			// detecting the start of a char
//			if (token.value.equals("\'")) {
//				TokenBuffer.setBufferMode(BufferMode.charLiteral);
//				token.tokenType = new CharLiteral();
//				return true;
//			}
//			// no text detected
//			return false;
//		}
//		// we are writing a character
//		case charLiteral: {
//			// detecting the end of the character
//			var prevToken = TokenBuffer.lookBehind();
//			if (token.value.equals("\'") && prevToken != null && !prevToken.value.equals("\\")) {
//				TokenBuffer.setBufferMode(BufferMode.normal);
//			}
//			// character continue
//			token.tokenType = new CharLiteral();
//			return true;
//		}
//		default:
//			return false;
//		}
//	}
//
//	private boolean isString(Token token) {
//
//		switch (TokenBuffer.getBufferMode()) {
//		case singleLineComment:
//		case multiLineComment:
//		case charLiteral:
//			return false;
//		case normal: {
//			// detecting the start of a string
//			if (token.value.equals("\"")) {
//				TokenBuffer.setBufferMode(BufferMode.stringLiteral);
//				token.tokenType = new StringLiteral();
//				return true;
//			}
//			// no text detected
//			return false;
//		}
//		// we are writing a string
//		case stringLiteral: {
//			// detecting end of the string
//			var prevToken = TokenBuffer.lookBehind();
//			if (token.value.equals("\"") && prevToken != null && !prevToken.value.equals("\\")) {
//				TokenBuffer.setBufferMode(BufferMode.normal);
//			}
//			// the string continue
//			token.tokenType = new StringLiteral();
//			return true;
//		}
//
//		default:
//			return false;
//		}
//	}
//
//	private boolean isDataType(Token token) {
//		var dataType = dataTypes.getOrDefault(token.value, null);
//		if (dataType == null)
//			return false;
//		token.tokenType = dataType;
//		return true;
//	}
//
//	private boolean isStarType(Token token) {
//		if (TokenBuffer.getBufferMode()==BufferMode.normal) {
//			return false;
//		}
//		var prevToken=TokenBuffer.lookBehind();
//		if (token.value.equals("*")&& !TokenBuffer.hadWhiteSpace() 
//				&& prevToken != null && prevToken.tokenType instanceof DataType) {
////			identifierInProgress = true;
////			prevDataType = token.prevToken.tokenType;
//			return true;
//		}
//
//		return false;
//
//	}
//}
