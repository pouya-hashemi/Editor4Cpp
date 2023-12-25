package entities.TokenTypes.Keywords;

import entities.TokenTypes.Keyword;

public class DoKeyword extends Keyword {
	@Override
	public String getError() {

		return "Expected 'do' Keyword";
	}

}
