package entities.TokenTypes.Keywords;

import entities.TokenTypes.Keyword;

public class ReturnKeyword extends Keyword {
	@Override
	public String getError() {

		return "Expected 'return' Keyword";
	}

}