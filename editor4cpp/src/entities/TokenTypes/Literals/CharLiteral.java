package entities.TokenTypes.Literals;

import entities.TokenTypes.TextLiteral;


public class CharLiteral extends TextLiteral {

	@Override
	public String getError() {
		
		return "Expected char value.";
	}
}
