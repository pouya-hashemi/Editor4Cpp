package entities.TokenTypes.Literals;

import entities.TokenTypes.FloatingPointLiteral;


public class DoubleLiteral extends FloatingPointLiteral {

	@Override
	public String getError() {
		
		return "Expected double value.";
	}
}
