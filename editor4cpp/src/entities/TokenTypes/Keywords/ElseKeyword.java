package entities.TokenTypes.Keywords;

import entities.TokenTypes.Keyword;

public class ElseKeyword extends Keyword {
	@Override
	public String getError() {
		
		return "Expected 'else' Keyword";
	}
}
