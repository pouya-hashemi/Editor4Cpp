package entities.TokenTypes.Identifiers;

import entities.TokenTypes.Identifier;
import enums.DataTypes;

public class LongIdentifier extends Identifier {
	public LongIdentifier(String name) {
		super(name);
		dataType=DataTypes.Long;
	}
}
