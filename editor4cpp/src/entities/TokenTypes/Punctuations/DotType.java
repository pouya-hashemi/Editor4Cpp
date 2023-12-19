package entities.TokenTypes.Punctuations;

import entities.TokenTypes.Punctuation;

public class DotType extends Punctuation {
	@Override
	public String getError() {
		
		return "Expected .";
	}
}
