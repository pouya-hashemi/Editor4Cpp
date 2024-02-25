package entities.TokenTypes;

import javax.swing.text.Style;

import constants.CustomStyle;
import entities.TokenType;

public class Identifier extends TokenType  {
	private String name;
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

}
