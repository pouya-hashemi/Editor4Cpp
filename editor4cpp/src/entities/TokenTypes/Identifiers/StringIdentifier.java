package entities.TokenTypes.Identifiers;

import entities.TokenTypes.Identifier;
import enums.DataTypes;

public class StringIdentifier extends Identifier {
	public StringIdentifier(String name) {
		super(name);
		dataType=DataTypes.String;
	}
}
