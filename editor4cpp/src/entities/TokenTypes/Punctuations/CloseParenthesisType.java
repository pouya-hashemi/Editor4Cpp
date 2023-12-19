package entities.TokenTypes.Punctuations;

import entities.TokenTypes.Punctuation;

public class CloseParenthesisType extends Punctuation{
	@Override
	public String getError() {
		
		return "Expected )";
	}
}
