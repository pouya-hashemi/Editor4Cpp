package entities.TokenTypes.DataTypes;

import javax.swing.text.Style;

import constants.CustomStyle;
import entities.TokenTypes.DataType;

public class UserDefinedDataType extends DataType {
	public UserDefinedDataType(String name) {
		super(name);
	}
	@Override
	public Style getTokenStyle() {
		return CustomStyle.defaultStyle;
	}
}
