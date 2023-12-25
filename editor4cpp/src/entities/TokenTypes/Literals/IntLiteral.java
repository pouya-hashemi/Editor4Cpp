package entities.TokenTypes.Literals;

import entities.TokenTypes.Literal;
import enums.DataTypes;

public class IntLiteral extends Literal {
	public IntLiteral() {
		dataType=DataTypes.Int;
	}
	@Override
	public String getError() {
		
		return "Expected int value.";
	}
}
