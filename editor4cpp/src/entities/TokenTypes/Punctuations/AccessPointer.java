package entities.TokenTypes.Punctuations;

import entities.TokenTypes.Punctuation;

public class AccessPointer extends Punctuation{
	@Override
	public String getError() {
		
		return "Expected ->";
	}
}
