package entities.TokenTypes;

import javax.swing.text.Style;

import constants.CustomStyle;
import entities.TokenType;

public class NamespaceMemberType extends TokenType {

	@Override
	public Style getTokenStyle() {
		// TODO Auto-generated method stub
		return CustomStyle.defaultStyle;
	}

	@Override
	public String getError() {
		// TODO Auto-generated method stub
		return "namespace's member";
	}

}
