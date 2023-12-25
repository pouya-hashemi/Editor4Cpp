package entities.TokenTypes.Keywords;

import entities.TokenTypes.Keyword;

public class WhileKeyword extends Keyword {
	@Override
	public String getError() {
		
		return "Expected 'while' Keyword";
	}
}
