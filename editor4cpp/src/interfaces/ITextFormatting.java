package interfaces;

import Dtos.FormattingResult;
import entities.Token;

public interface ITextFormatting {
	public FormattingResult FormatToken(Token token);
}
