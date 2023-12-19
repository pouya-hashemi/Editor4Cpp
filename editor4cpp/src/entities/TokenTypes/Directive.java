package entities.TokenTypes;

import javax.swing.text.Style;

import constants.CustomStyle;
import entities.TokenType;

public class Directive extends TokenType {
	@Override
	public Style getTokenStyle() {
		return CustomStyle.directiveStyle;
	}
	@Override
	public String getError() {
		
		return "Expected Directives";
	}
}
