package entities.TokenTypes.Literals;

import entities.TokenTypes.FloatingPointLiteral;
import enums.DataTypes;

public class FloatLiteral extends FloatingPointLiteral {
	public FloatLiteral() {
		dataType=DataTypes.Float;
	}
	@Override
	public String getError() {
		
		return "Expected float value.";
	}
}
