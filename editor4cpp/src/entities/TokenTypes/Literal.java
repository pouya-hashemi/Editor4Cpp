package entities.TokenTypes;

import javax.swing.text.Style;

import constants.CustomStyle;
import entities.TokenType;
import enums.DataTypes;
import interfaces.HasType;

public class Literal extends TokenType implements HasType {

	protected DataTypes dataType;
	@Override
	public Style getTokenStyle() {
		return CustomStyle.defaultStyle;
	}

	@Override
	public String getError() {
		
		return "missing literal";
	}

	@Override
	public DataTypes getDataType() {

		return dataType;
	}

}
