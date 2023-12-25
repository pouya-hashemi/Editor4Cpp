package entities.TokenTypes.Identifiers;

import entities.TokenTypes.Identifier;
import enums.DataTypes;

public class DoubleIdentifier extends Identifier {
	public DoubleIdentifier(String name) {
		super(name);
		dataType=DataTypes.Double;
	}
}
