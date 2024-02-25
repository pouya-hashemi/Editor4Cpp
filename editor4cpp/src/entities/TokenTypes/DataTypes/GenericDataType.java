package entities.TokenTypes.DataTypes;

import javax.swing.text.Style;

import constants.CustomStyle;
import entities.TokenTypes.DataType;

public class GenericDataType extends DataType {

	public GenericDataType(String name) {
		super(name);
	}
	@Override
	public Style getTokenStyle() {
		return CustomStyle.defaultStyle;
	}
}
