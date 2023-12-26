package entities;

import javax.swing.text.Style;

public class Token {
	public int startIndex;
	public int endIndex;
	public String value="";
	public Style tokenStyle;
	public TokenType tokenType;
	public String error;
	public Token prevToken;
	public Token nextToken;
	public Token absoluteNextToken;
	public Token absolutePrevToken;
	public boolean isEndOfStatement;

}
