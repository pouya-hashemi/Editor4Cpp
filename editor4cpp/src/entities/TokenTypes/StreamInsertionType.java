package entities.TokenTypes;

import javax.swing.text.Style;

import constants.CustomStyle;
import entities.TokenType;

public class StreamInsertionType extends TokenType {

	@Override
	public Style getTokenStyle() {
		// TODO Auto-generated method stub
		return CustomStyle.punctStyle;
	}

	@Override
	public String getError() {
		// TODO Auto-generated method stub
		return "expected <<";
	}

}
