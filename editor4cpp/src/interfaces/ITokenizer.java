package interfaces;

import java.util.List;

import entities.Token;

public interface ITokenizer {
	public List<Token> tokenizeString(String text, boolean formatText);
}
