package entities.TokenTypes;

import javax.swing.text.Style;
import constants.CustomStyle;
import entities.TokenType;

public class PointerDataType extends TokenType {

	@Override
	public Style getTokenStyle() {

		return CustomStyle.keywordStyle;
	}

	@Override
	public String getError() {
		return "expected pointer";
	}

}
