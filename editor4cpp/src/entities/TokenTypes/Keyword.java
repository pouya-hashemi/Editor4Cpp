package entities.TokenTypes;

import javax.swing.text.Style;

import constants.CustomStyle;
import entities.TokenType;

public class Keyword extends TokenType {
	@Override
	public Style getTokenStyle() {
		return CustomStyle.keywordStyle;
	}
	@Override
	public String getError() {
		
		return "Expected Keyword";
	}
}
