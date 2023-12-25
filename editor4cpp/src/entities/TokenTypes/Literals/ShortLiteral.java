package entities.TokenTypes.Literals;

import entities.TokenTypes.NumericLiteral;
import enums.DataTypes;

public class ShortLiteral extends NumericLiteral {
	public ShortLiteral() {
		dataType=DataTypes.Short;
	}
	@Override
	public String getError() {
		
		return "Expected short int value.";
	}
}
