package entities.TokenTypes.Literals;

import entities.TokenTypes.NumericLiteral;


public class LongLiteral extends NumericLiteral {

	@Override
	public String getError() {
		
		return "Expected long int value.";
	}
}
