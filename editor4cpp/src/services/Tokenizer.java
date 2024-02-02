package services;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import constants.GrammarLibrary;
import entities.NonTerminalNode;
import entities.Token;
import entities.TokenTypes.Comment;
import entities.TokenTypes.WhiteSpace;
import enums.GrammarStatus;
import interfaces.ITokenizer;

public class Tokenizer implements ITokenizer {
	private int tabCounter;
	private int currentTabNumber;

	public List<Token> tokenizeString(String text, boolean formatText) {
		List<Token> tokens = new ArrayList<Token>();
		TokenHighlighter tokenHighlighter = new TokenHighlighter();
		TokenIdentifier tokenIdentifier = new TokenIdentifier();
		ErrorDetecter errorDetecter = new ErrorDetecter();
		
		List<Token> reserveTokens = new ArrayList<Token>();
		
		boolean hasError = false;
		Token prevToken = null;
		tabCounter = 0;
		int openParenthesisCount = 0;
		currentTabNumber = 0;
		boolean equalBracket = false;
		
		text = text.replace("\r\n", "\r");
		String regex = "\\b\\w+\\b|\\s|\\n|[^\\w\\s]";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(text);
	

		if (matcher.find()) {

			Token token = new Token();
			token.value = matcher.group();

			while (matcher.find()) {
				token.prevToken = prevToken;
				token.nextToken = new Token();
				token.nextToken.value = matcher.group();
				if (formatText) {
					if (token.value.equals("("))
						openParenthesisCount++;
					if (token.value.equals(")") && openParenthesisCount > 0)
						openParenthesisCount--;

					if (token.value.equals("{") && token.prevToken != null && token.prevToken.prevToken != null
							&& token.prevToken.prevToken.value.equals("=")) {
						equalBracket = true;
					}

					if ((isNextLine(token.value) || isTab(token.value)) && token.nextToken != null
							&& token.nextToken.value.equals("{")) {
						if (isWhiteSpace(token.value)) {
							token.value = " ";
						}

					}
					if (token.value.equals("{") && prevToken != null && !isWhiteSpace(prevToken.value)) {
						var newToken = getSpaceToken(prevToken, token);
						token.prevToken = newToken;
						prevToken = token.prevToken;
						reserveTokens.add(newToken);
					}

					if (prevToken != null && prevToken.value.equals("{") && isWhiteSpace(token.value)) {
						if (isWhiteSpace(token.value)) {
							token = token.nextToken;
							continue;
						}
					}
					if (prevToken != null && prevToken.value.equals("{") && !isWhiteSpace(token.value)
							&& (prevToken.prevToken != null && prevToken.prevToken.prevToken != null
									&& !prevToken.prevToken.prevToken.value.equals("="))) {
						tabCounter++;
						var newTokens = getNextLineTokenWithTabs(prevToken, token);
						token.prevToken = newTokens.get(newTokens.size() - 1);
						prevToken = token.prevToken;
						reserveTokens.addAll(newTokens);
					}

///////////////////////////////////////////////

					if (token.value.equals("}") && prevToken != null && isWhiteSpace(prevToken.value)) {
						var i = tokens.size() - 1;
						do {
							prevToken = tokens.get(i);
							token.prevToken = prevToken;
							if (isWhiteSpace(prevToken.value)) {
								tokens.remove(i);
								prevToken = prevToken.prevToken;
								token.prevToken = prevToken;
								i--;
							} else {
								break;
							}

						} while (prevToken != null && isWhiteSpace(prevToken.value));

					}

					if (prevToken != null && prevToken.value.equals("}") && isWhiteSpace(token.value)) {
						if (isWhiteSpace(token.value)) {
							token = token.nextToken;
							continue;
						}
					}
					if (token.value.equals("}") && prevToken != null && !isWhiteSpace(prevToken.value)
							&& !equalBracket) {
						tabCounter--;
						var newTokens = getNextLineTokenWithTabs(prevToken, token);
						token.prevToken = newTokens.get(newTokens.size() - 1);
						prevToken = token.prevToken;
						reserveTokens.addAll(newTokens);
					}
					if (prevToken != null && prevToken.value.equals("}") && !isWhiteSpace(token.value)
							&& !token.value.equals(";")) {
						if (token.value.equals("}"))
							tabCounter--;
						var newTokens = getNextLineTokenWithTabs(prevToken, token);
						token.prevToken = newTokens.get(newTokens.size() - 1);
						prevToken = token.prevToken;
						reserveTokens.addAll(newTokens);
					}

					if (isWhiteSpace(token.value) && token.nextToken != null && isWhiteSpace(token.nextToken.value)
							&& prevToken != null && prevToken.value.equals(")") && openParenthesisCount == 0) {
						if (isWhiteSpace(token.value)) {
							token = token.nextToken;
							continue;
						}
					}
					/////////////////////////////////////////

					if (prevToken != null && prevToken.value.equals(";") && openParenthesisCount == 0
							&& (isSpace(token.value) || isTab(token.value))) {
						if (isWhiteSpace(token.value)) {
							token = token.nextToken;
							continue;
						}
					}
					if (prevToken != null && prevToken.value.equals(";") && openParenthesisCount == 0
							&& !isNextLine(token.value)) {
						var newTokens = getNextLineTokenWithTabs(prevToken, token);
						token.prevToken = newTokens.get(newTokens.size() - 1);
						prevToken = token.prevToken;
						reserveTokens.addAll(newTokens);
					}
					////////////////////
//					if (prevToken != null && isNextLine(prevToken.value) && !isTab(token.value)) {
//						token = token.nextToken;
//						continue;
//					}
					if (prevToken != null && isNextLine(prevToken.value) && !isTab(token.value)) {
						var newTokens = getRequiredTabToken(prevToken, token);
						if (!newTokens.isEmpty()) {
							token.prevToken = newTokens.get(0);
							prevToken = token.prevToken;
							reserveTokens.addAll(newTokens);
						}
					}

					if (isTab(token.value)) {
						currentTabNumber++;
						if (currentTabNumber > tabCounter) {
							if (isWhiteSpace(token.value)) {
								token = token.nextToken;
								continue;
							}
						}
					}
					if (!isTab(token.value)) {
						currentTabNumber = 0;
					}
					////////////////////////////////////////////////////////////////////

					if (prevToken != null && prevToken.value.equals(")") && openParenthesisCount == 0
							&& isWhiteSpace(token.value)) {
						if (isWhiteSpace(token.value)) {
							token = token.nextToken;
							continue;
						}
					}
					if (prevToken != null && prevToken.value.equals(")") && openParenthesisCount == 0
							&& !isWhiteSpace(token.value) && !token.value.equals(";")) {

						var newTokens = getNextLineTokenWithTabs(prevToken, token);
						token.prevToken = newTokens.get(newTokens.size() - 1);
						prevToken = token.prevToken;
						reserveTokens.addAll(newTokens);

					}

					////////////////////////////
					if (isSpace(token.value) && prevToken != null
							&& (isTab(prevToken.value) || isNextLine(prevToken.value))) {
						if (isWhiteSpace(token.value)) {
							token = token.nextToken;
							continue;
						}
					}
					///////////////////////

					if (token.value.equals("}") && equalBracket)
						equalBracket = false;

				}

				////////////////////////////////////////////

				reserveTokens.add(token);
				if (!isWhiteSpace(token.nextToken.value)) {
					for (int i = 0; i < reserveTokens.size(); i++) {
						var rToken = reserveTokens.get(i);
						if (i == 0) {
							rToken.absoluteNextToken = token.nextToken;
							token.nextToken.absolutePrevToken = rToken;
						}
						rToken = tokenIdentifier.identify(rToken);
						rToken = tokenHighlighter.HighlightToken(rToken);
						if (!(rToken.tokenType instanceof WhiteSpace) && !hasError
								&& !(rToken.tokenType instanceof Comment)) {

							var result = errorDetecter.Parse( rToken);
							if (result.grammarStatus == GrammarStatus.failed) {
								errorDetecter = null;
								errorDetecter = new ErrorDetecter();
								rToken.error = result.error;
								hasError = true;
							}
							if (result.grammarStatus == GrammarStatus.refresh_Retry) {

								errorDetecter = null;
								errorDetecter = new ErrorDetecter();

								var secondResult = errorDetecter.Parse( rToken);

								if (secondResult.grammarStatus == GrammarStatus.failed) {
									errorDetecter = null;
									errorDetecter = new ErrorDetecter();
									rToken.error = secondResult.error;
									hasError = true;
								}
							}
						}

						if (hasError && (rToken.value.equals("\n") || rToken.value.equals("\r"))) {
							hasError = false;
						}

						tokens.add(rToken);
					}
					reserveTokens.clear();
				}

				prevToken = token;
				token = token.nextToken;
			}

			for (int i = 0; i < reserveTokens.size(); i++) {
				var rToken = reserveTokens.get(i);
				if (i == 0) {
					rToken.absoluteNextToken = token.nextToken;
					if (token.nextToken != null)
						token.nextToken.absolutePrevToken = rToken;
				}
				rToken = tokenIdentifier.identify(rToken);
				rToken = tokenHighlighter.HighlightToken(rToken);
				if (!(rToken.tokenType instanceof WhiteSpace) && !hasError && !(rToken.tokenType instanceof Comment)) {

					var result = errorDetecter.Parse( rToken);
					if (result.grammarStatus == GrammarStatus.failed) {
						errorDetecter = null;
						errorDetecter = new ErrorDetecter();
						rToken.error = result.error;
						hasError = true;
					}
					if (result.grammarStatus == GrammarStatus.refresh_Retry) {

						errorDetecter = null;
						errorDetecter = new ErrorDetecter();

						var secondResult = errorDetecter.Parse( rToken);

						if (secondResult.grammarStatus == GrammarStatus.failed) {
							errorDetecter = null;
							errorDetecter = new ErrorDetecter();
							rToken.error = secondResult.error;
							hasError = true;
						}
					}
				}

				if (hasError && (rToken.value.equals("\n") || rToken.value.equals("\r"))) {
					hasError = false;
				}

				tokens.add(rToken);
			}

			token.prevToken = prevToken;
			token.nextToken = null;
			token = tokenIdentifier.identify(token);
			token = tokenHighlighter.HighlightToken(token);
			if (!(token.tokenType instanceof WhiteSpace) && !hasError) {
				var result = errorDetecter.Parse( token);
				if (result.grammarStatus == GrammarStatus.failed) {
					errorDetecter = null;
					errorDetecter = new ErrorDetecter();
					token.error = result.error;
					hasError = true;
				}
				if (result.grammarStatus == GrammarStatus.refresh_Retry) {

					errorDetecter = null;
					errorDetecter = new ErrorDetecter();

					var secondResult = errorDetecter.Parse( token);

					if (secondResult.grammarStatus == GrammarStatus.failed) {
						errorDetecter = null;
						errorDetecter = new ErrorDetecter();
						token.error = secondResult.error;
						hasError = true;
					}
				}

			}
			tokens.add(token);
		}

		return tokens;
	}

	private boolean isWhiteSpace(String character) {
		return character.equals(" ") || character.equals("\r") || character.equals("\t") || character.equals("\n");
	}

	private boolean isSpace(String character) {
		return character.equals(" ");
	}

	private boolean isTab(String character) {
		return character.equals("\t");
	}


	private boolean isNextLine(String character) {
		return character.equals("\n") || character.equals("\r");
	}

	private Token getSpaceToken(Token prevToken, Token nextToken) {
		var token = new Token();
		token.value = " ";
		token.prevToken = prevToken;
		token.nextToken = nextToken;
		return token;
	}

	private List<Token> getNextLineTokenWithTabs(Token prevToken, Token nextToken) {
		var token = new Token();
		token.value = "\r";
		token.prevToken = prevToken;
		var tabTokens = getRequiredTabToken(token, nextToken);
		if (tabTokens.size() > 0)
			token.nextToken = tabTokens.get(0);
		else {
			token.nextToken = nextToken;
		}

		tabTokens.add(0, token);
		return tabTokens;
	}

	private List<Token> getRequiredTabToken(Token prevToken, Token nextToken) {
		var list = new ArrayList<Token>();

		for (int i = 0; i < tabCounter; i++) {
			var token = new Token();
			token.value = "\t";
			if (i == 0)
				token.prevToken = prevToken;
			else {
				token.prevToken = list.get(i - 1);
			}
			if (i > 0) {
				list.get(i - 1).nextToken = token;
			}
			if (i == tabCounter - 1) {
				token.nextToken = nextToken;
			}
			currentTabNumber++;
			list.add(token);
		}

		return list;
	}

}
