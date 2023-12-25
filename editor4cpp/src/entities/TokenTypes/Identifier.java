package entities.TokenTypes;

import javax.swing.text.Style;

import constants.CustomStyle;
import entities.TokenType;
import enums.DataTypes;
import interfaces.HasType;

public class Identifier extends TokenType implements HasType {
	private String name;
	protected DataTypes dataType;
	public Identifier(String name) {
		this.name=name;
	}
	public String getName() {
		return name;
	}
	@Override
	public Style getTokenStyle() {
		return CustomStyle.defaultStyle;
	}
	@Override
	public String getError() {
		
		return "Expected Identifier";
	}
	@Override
	public DataTypes getDataType() {
		return dataType;
	}
}
