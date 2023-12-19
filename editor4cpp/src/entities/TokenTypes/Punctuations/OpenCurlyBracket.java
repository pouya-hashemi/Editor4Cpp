package entities.TokenTypes.Punctuations;

import entities.TokenTypes.Punctuation;

public class OpenCurlyBracket extends Punctuation {
	@Override
	public String getError() {
		
		return "Expected {";
	}
}
