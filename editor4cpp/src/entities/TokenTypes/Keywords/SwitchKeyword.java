package entities.TokenTypes.Keywords;

import entities.TokenTypes.Keyword;

public class SwitchKeyword extends Keyword {
	@Override
	public String getError() {

		return "Expected 'switch' Keyword";
	}

}