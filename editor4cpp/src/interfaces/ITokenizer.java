package interfaces;

import entities.Token;

public interface ITokenizer {
	public Token getNextToken();
	public void setTextAndRestart(String text);
}
