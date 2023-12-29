package entities.TokenTypes;

import javax.swing.text.Style;

import constants.CustomStyle;
import entities.TokenType;

public class ObjectIdentifier extends TokenType {

	@Override
	public Style getTokenStyle() {

		return CustomStyle.defaultStyle;
	}

	@Override
	public String getError() {

		return "Expected object name.";
	}
}
