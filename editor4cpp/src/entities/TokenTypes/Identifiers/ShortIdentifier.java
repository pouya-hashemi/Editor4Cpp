package entities.TokenTypes.Identifiers;

import entities.TokenTypes.Identifier;
import enums.DataTypes;

public class ShortIdentifier extends Identifier {
	public ShortIdentifier(String name) {
		super(name);
		dataType=DataTypes.Short;
	}
}
