package entities.TokenTypes.Punctuations;

import entities.TokenTypes.Punctuation;

public class CommaType extends Punctuation {
	@Override
	public String getError() {
		
		return "Expected ,";
	}
}
