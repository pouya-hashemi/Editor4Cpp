package entities;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.Style;

public class Token {
	public int startIndex;
	public int endIndex;
	public String value="";
	public Style tokenStyle;
	public TokenType tokenType;
	public List<String> errors;
	public boolean isEndOfStatement;
	
	public Token(String value) {
		this.value=value;
		this.errors=new ArrayList<String>();
	}
	public Token(String value,TokenType tokenType) {
		this.value=value;
		this.tokenType=tokenType;
		this.errors=new ArrayList<String>();
	}

	public Token() {
		this.errors=new ArrayList<String>();
	}

}
