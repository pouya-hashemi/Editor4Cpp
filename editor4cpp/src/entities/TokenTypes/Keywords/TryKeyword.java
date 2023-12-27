package entities.TokenTypes.Keywords;

import entities.TokenTypes.Keyword;

public class TryKeyword extends Keyword {
	@Override
	public String getError() {

		return "Expected 'try' Keyword";
	}
}