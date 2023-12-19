package interfaces;

import entities.Token;

public interface IParser {
	String parseToken(Token token);
	void resetTree();
	boolean isEnd();

}
