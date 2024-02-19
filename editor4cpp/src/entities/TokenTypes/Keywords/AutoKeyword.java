package entities.TokenTypes.Keywords;

import entities.TokenTypes.Keyword;

public class AutoKeyword extends Keyword {
	@Override
	public String getError() {

		return "Expected 'auto' Keyword";
	}

}
