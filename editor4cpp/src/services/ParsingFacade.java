package services;

import java.util.ArrayList;
import java.util.List;

import common.CommonCharacters;
import entities.Token;
import entities.TokenTypes.Comment;
import entities.TokenTypes.EndOfText;
import entities.TokenTypes.WhiteSpace;
import interfaces.IParser;
import interfaces.IParsingFacade;
import interfaces.ITextFormatting;
import interfaces.ITokenHighlighter;
import interfaces.ITokenizer;

public class ParsingFacade implements IParsingFacade {

	private IParser parser;
	private ITextFormatting textFormatting;
	private ITokenizer tokenizer;
	private ITokenHighlighter tokenHighlighter;

	public ParsingFacade(ITokenHighlighter tokenHighlighter,
			ITextFormatting textFormatting,
			ITokenizer tokenizer,
			IParser parser) {
		this.parser =parser;
		this.textFormatting = textFormatting;
		this.tokenizer=tokenizer;
		this.tokenHighlighter=tokenHighlighter;
	}

	@Override
	public List<Token> ParseText(String text, boolean formatText) {
		List<Token> tokens = new ArrayList<Token>();
		
		tokenizer.setTextAndRestart(text);
		
		var token = tokenizer.getNextToken();
		boolean hasError = false;
		while (true) {

			if (hasError && CommonCharacters.isSynchronizedToken(token.value)) {
				hasError = false;
			}
			tokenHighlighter.HighlightToken(token);

			if (token.tokenType instanceof WhiteSpace || token.tokenType instanceof Comment) {
				if (!formatText)
					tokens.add(token);
				var prevToken = token;
				token = tokenizer.getNextToken();
				if (token != null)
					token.absolutePrevToken = prevToken;
				continue;
			}

			
			 parser.Parse(token);
			
			if (formatText) {

				var formattingResult = textFormatting.FormatToken(token);
				if (formattingResult.getRemoveBefore() > 0) {
					for (int i = 0; i < formattingResult.getRemoveBefore(); i++) {
						tokens.remove(tokens.size() - (i + 1));
					}
				}

				if (formattingResult.getInsertBefore().size() > 0) {
					tokens.addAll(formattingResult.getInsertBefore());
				}

				tokens.add(token);

				if (formattingResult.getInsertAfter().size() > 0) {
					tokens.addAll(formattingResult.getInsertAfter());
				}
			} else {
				tokens.add(token);
			}

			var prevToken = token;

			if (token.tokenType instanceof EndOfText) {
				break;
			}
			token = tokenizer.getNextToken();
			if (token != null)
				token.absolutePrevToken = prevToken;

		}

		return tokens;
	}

}
