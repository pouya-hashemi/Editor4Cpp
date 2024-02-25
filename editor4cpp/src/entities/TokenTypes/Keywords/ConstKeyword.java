package entities.TokenTypes.Keywords;

import entities.TokenTypes.Keyword;

public class ConstKeyword extends Keyword {
	@Override
	public String getError() {

		return "Expected 'const' Keyword";
	}
}
