package entities.TokenTypes.Keywords;

import entities.TokenTypes.Keyword;

public class DeleteKeyword extends Keyword {
	@Override
	public String getError() {

		return "Expected 'delete' Keyword";
	}

}
