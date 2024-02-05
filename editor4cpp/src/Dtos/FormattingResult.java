package Dtos;

import java.util.ArrayList;
import java.util.List;

import entities.Token;

public class FormattingResult {
	private List<Token> insertBefore;
	private List<Token> insertAfter;
	private int removeBefore=0;

	public FormattingResult() {
		insertBefore=new ArrayList<Token>();
		insertAfter=new ArrayList<Token>();
	}
	public void addInsertBefore(Token token) {
		insertBefore.add(token);
	}
	public List<Token> getInsertBefore() {
		return insertBefore;
	}

	
	public void addInsertAfter(Token token) {
		insertAfter.add(token);
	}
	public List<Token> getInsertAfter() {
		return insertAfter;
	}
	public void setRemoveBefore(int count) {
		removeBefore=count;
	}
	public int getRemoveBefore() {
		return removeBefore;
	}
}
