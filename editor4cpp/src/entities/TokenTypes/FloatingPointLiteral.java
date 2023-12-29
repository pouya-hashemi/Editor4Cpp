package entities.TokenTypes;

import javax.swing.text.Style;

import constants.CustomStyle;
import enums.DataTypes;

public class FloatingPointLiteral extends Literal {
	public FloatingPointLiteral() {
		dataType=DataTypes.Float;
	}
	@Override
	public Style getTokenStyle() {
		return CustomStyle.numberStyle;
	}
	@Override
	public String getError() {
		
		return "Expected float value.";
	}
}
