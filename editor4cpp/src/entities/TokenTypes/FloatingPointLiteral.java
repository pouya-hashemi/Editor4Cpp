package entities.TokenTypes;

import javax.swing.text.Style;

import constants.CustomStyle;

public class FloatingPointLiteral extends Literal {
	public FloatingPointLiteral() {
	}
	@Override
	public Style getTokenStyle() {
		return CustomStyle.numberStyle;
	}
	@Override
	public String getError() {
		
		return "Expected float value.";
	}
}
