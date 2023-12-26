package entities.TokenTypes;

import javax.swing.text.Style;

import constants.CustomStyle;
import entities.TokenType;

public class FunctionIdentifier extends TokenType {

	@Override
	public Style getTokenStyle() {
		
		return CustomStyle.funcStyle;
	}

	@Override
	public String getError() {
		// TODO Auto-generated method stub
		return "Expected function name";
	}

}
