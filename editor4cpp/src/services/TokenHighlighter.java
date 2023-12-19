package services;

import entities.Token;

public class TokenHighlighter {

	public Token HighlightToken(Token token) {
		
		token.tokenStyle=token.tokenType.getTokenStyle();
		
		return token;

	}
}
