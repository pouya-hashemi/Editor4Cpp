package entities.TokenTypes;

import javax.swing.text.Style;

import constants.CustomStyle;

public class NumericLiteral extends Literal {
	@Override
	public Style getTokenStyle() {
		return CustomStyle.numberStyle;
	}
	@Override
	public String getError() {
		
		return "Expected Numeric Literal";
	}
}
