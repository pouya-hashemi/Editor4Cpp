package entities.TokenTypes.Literals;

import entities.TokenTypes.NumericLiteral;

public class ShortLiteral extends NumericLiteral {

	@Override
	public String getError() {
		
		return "Expected short int value.";
	}
}
