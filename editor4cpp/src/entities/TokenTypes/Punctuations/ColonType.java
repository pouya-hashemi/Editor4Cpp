package entities.TokenTypes.Punctuations;

import entities.TokenTypes.Punctuation;

public class ColonType extends Punctuation{
	@Override
	public String getError() {
		
		return "Expected :";
	}
}