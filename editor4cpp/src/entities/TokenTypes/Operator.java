package entities.TokenTypes;

import javax.swing.text.Style;

import constants.CustomStyle;
import entities.TokenType;

public class Operator extends TokenType {

	@Override
	public Style getTokenStyle() {
		return CustomStyle.punctStyle;
	}

	@Override
	public String getError() {
		return "Expected an operation";
	}

}
