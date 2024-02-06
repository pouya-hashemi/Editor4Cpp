package services;

import entities.Token;
import interfaces.ITokenHighlighter;

public class TokenHighlighter implements ITokenHighlighter {

	public void HighlightToken(Token token) {
		
		token.tokenStyle=token.tokenType.getTokenStyle();

	}
}
