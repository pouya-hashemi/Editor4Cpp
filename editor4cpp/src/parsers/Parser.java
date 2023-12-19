package parsers;

import entities.ParsingNode;
import entities.Token;
import entities.TokenTypes.WhiteSpace;
import entities.TokenTypes.Punctuations.CloseParenthesisType;
import entities.TokenTypes.Punctuations.CommaType;
import entities.TokenTypes.Punctuations.OpenParenthesisType;
import entities.TokenTypes.Punctuations.SemicolonType;
import interfaces.IParser;

public class Parser implements IParser {
	protected ParsingNode root;
	protected ParsingNode currentNode;
	protected int openParenthesis = 0;

	@Override
	public String parseToken(Token token) {
		if (token.tokenType instanceof WhiteSpace)
			return null;
		if (token.tokenType instanceof OpenParenthesisType)
			openParenthesis++;

		if (token.tokenType instanceof CloseParenthesisType && openParenthesis <= 0)
			return "Too many close parenthesis";

		if (token.tokenType instanceof CloseParenthesisType && openParenthesis > 0) {
			openParenthesis--;
		}
		if (token.tokenType instanceof SemicolonType && openParenthesis > 0) {
			return "Expected ')'";
		}
		if (token.tokenType instanceof CommaType && openParenthesis > 0) {
			return "Expected ')'";
		}

		var firstNodeOptional = currentNode.getNextNodes().stream().findFirst();
//		var optional = currentNode.getNextNodes().stream().filter(x -> x.getTokenType().getClass()==  token.tokenType.getClass()).findFirst();
		var optional = currentNode.getNextNodes().stream()
				.filter(x -> x.getTokenType().getClass().isInstance(token.tokenType)).findFirst();
		if (optional.isPresent()) {
			currentNode = optional.get();
		} else {
			if (firstNodeOptional.isPresent())
				return firstNodeOptional.get().getTokenType().getError();
			else {
				return null;
			}
		}
		return null;
	}

	@Override
	public void resetTree() {
		currentNode = root;

	}

	@Override
	public boolean isEnd() {
		// TODO Auto-generated method stub
		return false;
	}

}
