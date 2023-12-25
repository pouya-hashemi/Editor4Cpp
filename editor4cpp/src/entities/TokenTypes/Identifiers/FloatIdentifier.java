package entities.TokenTypes.Identifiers;

import entities.TokenTypes.Identifier;
import enums.DataTypes;

public class FloatIdentifier extends Identifier {
	public FloatIdentifier(String name) {
		super(name);
		dataType=DataTypes.Float;
	}
}
