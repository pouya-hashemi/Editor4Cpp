package entities.TokenTypes.Literals;

import entities.TokenTypes.TextLiteral;


public class StringLiteral extends TextLiteral {

	@Override
	public String getError() {
		
		return "Expected string value.";
	}
}
