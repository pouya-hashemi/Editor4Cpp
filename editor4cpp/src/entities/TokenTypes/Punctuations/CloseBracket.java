package entities.TokenTypes.Punctuations;

import entities.TokenTypes.Punctuation;

public class CloseBracket extends Punctuation{
	@Override
	public String getError() {
		
		return "Expected ]";
	}
}
