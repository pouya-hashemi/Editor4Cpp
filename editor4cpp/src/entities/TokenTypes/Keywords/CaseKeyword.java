package entities.TokenTypes.Keywords;

import entities.TokenTypes.Keyword;

public class CaseKeyword extends Keyword {
	@Override
	public String getError() {

		return "Expected 'case' Keyword";
	}

}