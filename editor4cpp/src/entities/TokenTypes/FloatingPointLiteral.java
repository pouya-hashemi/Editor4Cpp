package entities.TokenTypes;

public class FloatingPointLiteral extends Literal {
	@Override
	public String getError() {
		
		return "Expected float value.";
	}
}
