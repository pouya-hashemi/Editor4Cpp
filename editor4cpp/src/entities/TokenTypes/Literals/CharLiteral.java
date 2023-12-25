package entities.TokenTypes.Literals;

import entities.TokenTypes.TextLiteral;
import enums.DataTypes;

public class CharLiteral extends TextLiteral {
	public CharLiteral() {
		dataType=DataTypes.Char;
	}
	@Override
	public String getError() {
		
		return "Expected char value.";
	}
}
