package entities.TokenTypes.Literals;

import entities.TokenTypes.NumericLiteral;
import enums.DataTypes;

public class LongLiteral extends NumericLiteral {
	public LongLiteral() {
		dataType=DataTypes.Long;
	}
	@Override
	public String getError() {
		
		return "Expected long int value.";
	}
}
