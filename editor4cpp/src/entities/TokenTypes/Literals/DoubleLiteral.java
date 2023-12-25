package entities.TokenTypes.Literals;

import entities.TokenTypes.FloatingPointLiteral;
import enums.DataTypes;

public class DoubleLiteral extends FloatingPointLiteral {
	public DoubleLiteral() {
		dataType=DataTypes.Double;
	}
	@Override
	public String getError() {
		
		return "Expected double value.";
	}
}
