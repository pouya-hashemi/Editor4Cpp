package entities.TokenTypes.Punctuations;

import entities.TokenTypes.Punctuation;

public class OpenBracket extends Punctuation {
	@Override
	public String getError() {
		
		return "Expected [";
	}
}
