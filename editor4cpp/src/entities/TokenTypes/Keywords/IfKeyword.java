package entities.TokenTypes.Keywords;

import entities.TokenTypes.Keyword;

public class IfKeyword extends Keyword {
	@Override
	public String getError() {
		
		return "Expected 'if' Keyword";
	}
}
