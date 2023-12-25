package entities.TokenTypes.Keywords;

import entities.TokenTypes.Keyword;

public class BreakKeyword extends Keyword {
	@Override
	public String getError() {

		return "Expected 'break' Keyword";
	}

}