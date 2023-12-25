package services;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import constants.GrammarLibrary;
import entities.StatementNode;
import entities.Token;

import entities.TokenTypes.WhiteSpace;
import enums.GrammarStatus;

public class Tokenizer {

	public List<Token> tokenizeString(String text) {
		text = text.replace("\r\n", "\r");
		List<Token> tokens = new ArrayList<Token>();
		TokenHighlighter tokenHighlighter = new TokenHighlighter();
		TokenIdentifier tokenIdentifier = new TokenIdentifier();
		ErrorDetecter errorDetecter = new ErrorDetecter();
		StatementNode statementNode=new StatementNode(()->GrammarLibrary.getParsingObjectsOfAll(),false);

		boolean hasError = false;
		String regex = "\\b\\w+\\b|\\s|\\n|\\p{Punct}";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(text);
		Token prevToken = null;

		if (matcher.find()) {
			Token token = new Token();
			token.startIndex = matcher.start();
			token.endIndex = matcher.end();
			token.value = matcher.group();

			while (matcher.find()) {
				token.prevToken = prevToken;
				token.nextToken = new Token();
				token.nextToken.startIndex = matcher.start();
				token.nextToken.endIndex = matcher.end();
				token.nextToken.value = matcher.group();

				token = tokenIdentifier.identify(token);
				token = tokenHighlighter.HighlightToken(token);
				if (!(token.tokenType instanceof WhiteSpace) && !hasError) {
					
					var result = errorDetecter.Parse(statementNode,token);
					if(result.grammarStatus==GrammarStatus.failed) {
						errorDetecter=null;
						errorDetecter=new ErrorDetecter();
						token.error = result.error;
						hasError = true;
					}
					if(result.grammarStatus==GrammarStatus.refresh_Retry) {
						
						errorDetecter=null;
						errorDetecter=new ErrorDetecter();
						
						var secondResult = errorDetecter.Parse(statementNode,token);
						
						if(secondResult.grammarStatus==GrammarStatus.failed) {
							errorDetecter=null;
							errorDetecter=new ErrorDetecter();
							token.error = secondResult.error;
							hasError = true;
						}
					}
				}
				
				if (hasError && (token.value.equals("\n") || token.value.equals("\r"))) {
					hasError = false;
				}

				tokens.add(token);
				prevToken = token;
				token = token.nextToken;
			}
			token.prevToken = prevToken;
			token.nextToken = null;
			token = tokenIdentifier.identify(token);
			token = tokenHighlighter.HighlightToken(token);
			if (!(token.tokenType instanceof WhiteSpace) && !hasError) {
				var result = errorDetecter.Parse(statementNode,token);
				if(result.grammarStatus==GrammarStatus.failed) {
					errorDetecter=null;
					errorDetecter=new ErrorDetecter();
					token.error = result.error;
					hasError = true;
				}
				if(result.grammarStatus==GrammarStatus.refresh_Retry) {
					
					errorDetecter=null;
					errorDetecter=new ErrorDetecter();
					
					var secondResult = errorDetecter.Parse(statementNode,token);
					
					if(secondResult.grammarStatus==GrammarStatus.failed) {
						errorDetecter=null;
						errorDetecter=new ErrorDetecter();
						token.error = secondResult.error;
						hasError = true;
					}
				}
				
				
				

			}
			tokens.add(token);
		}

		return tokens;
	}

}
