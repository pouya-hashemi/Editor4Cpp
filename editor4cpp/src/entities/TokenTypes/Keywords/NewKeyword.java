package entities.TokenTypes.Keywords;

import entities.TokenTypes.Keyword;

public class NewKeyword extends Keyword {
	@Override
	public String getError() {

		return "Expected 'new' Keyword";
	}

}
