package entities.TokenTypes.Keywords;

import entities.TokenTypes.Keyword;

public class ThisKeyword extends Keyword {
	@Override
	public String getError() {

		return "Expected 'this' Keyword";
	}

}