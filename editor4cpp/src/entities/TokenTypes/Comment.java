package entities.TokenTypes;

import javax.swing.text.Style;

import constants.CustomStyle;
import entities.TokenType;

public class Comment extends TokenType {

	@Override
	public Style getTokenStyle() {
		return CustomStyle.commentStyle;
	}

	@Override
	public String getError() {
		
		return "Expected Comment";
	}

}
