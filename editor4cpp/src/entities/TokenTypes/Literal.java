package entities.TokenTypes;

import javax.swing.text.Style;

import constants.CustomStyle;
import entities.TokenType;


public class Literal extends TokenType {


	@Override
	public Style getTokenStyle() {
		return CustomStyle.defaultStyle;
	}

	@Override
	public String getError() {
		
		return "missing literal";
	}



}
