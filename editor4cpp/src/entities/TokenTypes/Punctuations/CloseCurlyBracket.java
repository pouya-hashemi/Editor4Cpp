package entities.TokenTypes.Punctuations;

import entities.TokenTypes.Punctuation;

public class CloseCurlyBracket extends Punctuation {
	@Override
	public String getError() {
		
		return "Expected }";
	}
}
