package entities.TokenTypes;

import javax.swing.text.Style;

import constants.CustomStyle;

public class TextLiteral extends Literal {
	@Override
	public Style getTokenStyle() {
		return CustomStyle.stringStyle;
	}
	@Override
	public String getError() {
		
		return "Expected text literal";
	}
}
