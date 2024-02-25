package entities.TokenTypes.Literals;

import entities.TokenTypes.FloatingPointLiteral;

public class FloatLiteral extends FloatingPointLiteral {

	@Override
	public String getError() {
		
		return "Expected float value.";
	}
}
