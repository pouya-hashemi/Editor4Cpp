package entities.TokenTypes.Identifiers;

import entities.TokenTypes.Identifier;
import enums.DataTypes;

public class PointerIdentifier extends Identifier {
	public PointerIdentifier(String name) {
		super(name);
		dataType=DataTypes.Pointer;
	}
}
