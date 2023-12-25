package entities.TokenTypes;

import enums.DataTypes;

public class FloatingPointLiteral extends Literal {
	public FloatingPointLiteral() {
		dataType=DataTypes.Float;
	}
	@Override
	public String getError() {
		
		return "Expected float value.";
	}
}
