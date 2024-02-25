package entities.TokenTypes.DataTypes;

import javax.swing.text.Style;
import constants.CustomStyle;
import entities.TokenTypes.DataType;

public class PointerDataType extends DataType {

	public PointerDataType(String name) {
		super(name);
	}
	@Override
	public Style getTokenStyle() {

		return CustomStyle.keywordStyle;
	}

	@Override
	public String getError() {
		return "expected pointer";
	}

}
