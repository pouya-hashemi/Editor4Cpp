package entities.TokenTypes.Identifiers;

import entities.TokenTypes.Identifier;
import enums.DataTypes;

public class BoolIdentifier extends Identifier {
	public BoolIdentifier(String name) {
		super(name);
		dataType=DataTypes.Bool;
	}
}
