package entities.TokenTypes.Keywords;

import entities.TokenTypes.Keyword;

public class DefaultKeyword extends Keyword {
	@Override
	public String getError() {

		return "Expected 'default' Keyword";
	}

}