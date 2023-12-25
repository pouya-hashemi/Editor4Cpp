package entities.TokenTypes.Identifiers;

import entities.TokenTypes.Identifier;
import enums.DataTypes;

public class IntIdentifier extends Identifier {
	public IntIdentifier(String name) {
		super(name);
		dataType=DataTypes.Int;
	}
}
