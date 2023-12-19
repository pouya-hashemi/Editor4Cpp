package entities.TokenTypes.Literals;

import entities.TokenTypes.Literal;

public class IntLiteral extends Literal {
	@Override
	public String getError() {
		
		return "Expected int value.";
	}
}
