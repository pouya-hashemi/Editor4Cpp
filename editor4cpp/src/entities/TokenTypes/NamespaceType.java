package entities.TokenTypes;

import javax.swing.text.Style;
import constants.CustomStyle;
import entities.TokenType;

public class NamespaceType extends TokenType {
private String name;
	public NamespaceType(String name) {
		this.name=name;
	}
	
	@Override
	public Style getTokenStyle() {

		return CustomStyle.defaultStyle;
	}

	@Override
	public String getError() {
		return "namespace expected";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name=name;
	}
}
