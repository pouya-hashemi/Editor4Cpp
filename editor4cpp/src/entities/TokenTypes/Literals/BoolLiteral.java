package entities.TokenTypes.Literals;

import javax.swing.text.Style;

import constants.CustomStyle;
import entities.TokenTypes.Literal;
import enums.DataTypes;

public class BoolLiteral extends Literal {
	public BoolLiteral() {
		dataType=DataTypes.Bool;
	}
	@Override
	public Style getTokenStyle() {
		return CustomStyle.keywordStyle;
	}
	@Override
	public String getError() {
		
		return "Expected boolean value.";
	}
}
