package entities.TokenTypes.Punctuations;

import entities.TokenTypes.Punctuation;

public class OpenParenthesisType extends Punctuation{
	@Override
	public String getError() {
		
		return "Expected (";
	}
}
