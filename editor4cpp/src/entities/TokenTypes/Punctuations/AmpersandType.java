package entities.TokenTypes.Punctuations;

import entities.TokenTypes.Punctuation;

public class AmpersandType  extends Punctuation{
	@Override
	public String getError() {
		
		return "Expected &";
	}
}
