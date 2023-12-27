package entities.TokenTypes.Keywords;

import entities.TokenTypes.Keyword;

public class CatchKeyword extends Keyword {
	@Override
	public String getError() {

		return "Expected 'catch' Keyword";
	}
}