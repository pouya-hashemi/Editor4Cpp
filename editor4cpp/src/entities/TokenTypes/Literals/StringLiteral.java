package entities.TokenTypes.Literals;

import entities.TokenTypes.TextLiteral;
import enums.DataTypes;

public class StringLiteral extends TextLiteral {
	public StringLiteral() {
		dataType=DataTypes.String;
	}
	@Override
	public String getError() {
		
		return "Expected string value.";
	}
}
