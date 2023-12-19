package entities.TokenTypes.Literals;

import javax.swing.text.Style;

import constants.CustomStyle;
import entities.TokenTypes.Literal;

public class BoolLiteral extends Literal {
	@Override
	public Style getTokenStyle() {
		return CustomStyle.keywordStyle;
	}
	@Override
	public String getError() {
		
		return "Expected boolean value.";
	}
}
