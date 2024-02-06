package interfaces;

import java.util.List;

import entities.Token;

public interface IParsingFacade {
	public List<Token> ParseText(String text, boolean formatText);
}
