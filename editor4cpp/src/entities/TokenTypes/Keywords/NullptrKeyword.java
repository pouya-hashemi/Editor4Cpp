package entities.TokenTypes.Keywords;

import entities.TokenTypes.Keyword;

public class NullptrKeyword extends Keyword {
	@Override
	public String getError() {

		return "Expected 'nullptr' Keyword";
	}

}