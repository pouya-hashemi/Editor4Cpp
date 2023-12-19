package entities.TokenTypes.Punctuations;

import entities.TokenTypes.Punctuation;

public class EqualType extends Punctuation {
	@Override
	public String getError() {
		
		return "Expected =";
	}
}
