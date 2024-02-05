//package services;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//import entities.Token;
//import entities.TokenTypes.Comment;
//import entities.TokenTypes.WhiteSpace;
//import enums.GrammarStatus;
//import interfaces.ITokenizer;
//
//public class NewTokenizer implements ITokenizer {
//
//	private List<Token> reserveTokens;
//	private TokenHighlighter tokenHighlighter;
//	private TokenIdentifier tokenIdentifier;
//	private ErrorDetecter errorDetecter;
//
//	private boolean hasError;
//
//	public List<Token> tokenizeString(String text, boolean formatText) {
//		List<Token> tokens = new ArrayList<Token>();
//		tokenHighlighter = new TokenHighlighter();
//		tokenIdentifier = new TokenIdentifier();
//		errorDetecter = new ErrorDetecter();
//	 
//		reserveTokens = new ArrayList<Token>();
//
//		hasError = false;
//		Token prevToken = null;
//
//		text = text.replace("\r\n", "\r");
//		String regex = "\\b\\w+\\b|\\s|\\n|[^\\w\\s]";
//		Pattern pattern = Pattern.compile(regex);
//		Matcher matcher = pattern.matcher(text);
//
//		if (matcher.find()) {
//
//			Token token = new Token();
//			token.value = matcher.group();
//
//			while (matcher.find()) {
//				token.prevToken = prevToken;
//				token.nextToken = new Token();
//				token.nextToken.value = matcher.group();
//
//				reserveTokens.add(token);
//				if (!isWhiteSpace(token.nextToken.value)) {
//					tokens.addAll(parseReservedTokens(token));
//					reserveTokens.clear();
//				}
//
//				prevToken = token;
//				token = token.nextToken;
//			}
//			token.nextToken = null;
//			reserveTokens.add(token);
//			tokens.addAll(parseReservedTokens(token));
//			reserveTokens.clear();
//		}
//
//		return tokens;
//	}
//
//	private boolean isWhiteSpace(String character) {
//		return character.equals(" ") || character.equals("\r") || character.equals("\t") || character.equals("\n");
//	}
//
//	private List<Token> parseReservedTokens(Token lastToken) {
//		List<Token> tokens = new ArrayList<Token>();
//		for (int i = 0; i < reserveTokens.size(); i++) {
//			var rToken = reserveTokens.get(i);
//			if (i == 0) {
//				rToken.absoluteNextToken = lastToken.nextToken;
//				if (lastToken.nextToken != null)
//					lastToken.nextToken.absolutePrevToken = rToken;
//			}
//			rToken = tokenIdentifier.identify(rToken);
//			rToken = tokenHighlighter.HighlightToken(rToken);
//			if (!(rToken.tokenType instanceof WhiteSpace) && !hasError && !(rToken.tokenType instanceof Comment)) {
//
//				var result = errorDetecter.Parse(rToken);
//				if (result.grammarStatus == GrammarStatus.failed) {
//					errorDetecter = null;
//					errorDetecter = new ErrorDetecter();
//					rToken.error = result.error;
//					hasError = true;
//				}
//				if (result.grammarStatus == GrammarStatus.refresh_Retry) {
//
//					errorDetecter = null;
//					errorDetecter = new ErrorDetecter();
//
//					var secondResult = errorDetecter.Parse(rToken);
//
//					if (secondResult.grammarStatus == GrammarStatus.failed) {
//						errorDetecter = null;
//						errorDetecter = new ErrorDetecter();
//						rToken.error = secondResult.error;
//						hasError = true;
//					}
//				}
//			}
//
//			if (hasError && (rToken.value.equals("\n") || rToken.value.equals("\r"))) {
//				hasError = false;
//			}
//
//			tokens.add(rToken);
//		}
//		return tokens;
//	}
//}
