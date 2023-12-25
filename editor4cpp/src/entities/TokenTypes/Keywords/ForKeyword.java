package entities.TokenTypes.Keywords;

import entities.TokenTypes.Keyword;

public class ForKeyword extends Keyword {
	@Override
	public String getError() {
		
		return "Expected 'for' Keyword";
	}
}
