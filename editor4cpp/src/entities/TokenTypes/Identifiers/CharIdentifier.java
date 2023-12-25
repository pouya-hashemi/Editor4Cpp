package entities.TokenTypes.Identifiers;

import entities.TokenTypes.Identifier;
import enums.DataTypes;

public class CharIdentifier extends Identifier {
	public CharIdentifier(String name) {
		super(name);
		dataType=DataTypes.Char;
	}
}
