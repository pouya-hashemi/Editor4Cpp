package entities.TokenTypes.Keywords;

import entities.TokenTypes.Keyword;

public class NullKeyword extends Keyword {
	@Override
	public String getError() {

		return "Expected 'NULL' Keyword";
	}

}
